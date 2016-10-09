package com.zjk.fireflynews.http.manager;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.zjk.fireflynews.app.App;
import com.zjk.fireflynews.data.NewsListData;
import com.zjk.fireflynews.data.VideoListData;
import com.zjk.fireflynews.http.RestApi.Api;
import com.zjk.fireflynews.http.RestApi.HostType;
import com.zjk.fireflynews.http.RestApi.RequestApi;
import com.zjk.fireflynews.http.SchedulerTransformer;
import com.zjk.fireflynews.utils.LogUtil;
import com.zjk.fireflynews.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by FireFly on 2016/9/23.
 */

public class RetrofitManager {
    private RequestApi mRequestApi;
    private OkHttpClient mOkHttpClient;
    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> sInstanceManager = new SparseArray<>(HostType.TYPE_COUNT);

    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_NETWORK = "max-age=0";

    // 云端响应头拦截器，用来配置缓存策略
    private Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            log("请求网址: " + request.url());

            if (!NetUtil.isConnected(App.getInstance())) {
                log("没有网络");
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isConnected(App.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder().header("Cache-Control", cacheControl).removeHeader("Pragma").build();
            } else {
                return originalResponse.newBuilder().header("Cache-Control", "public, only-if-cached," + CACHE_STALE_SEC).removeHeader("Pragma").build();
            }
        }
    };

    // 打印返回的json数据拦截器
    private Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            final Request request = chain.request();
            final Response response = chain.proceed(request);

            final ResponseBody responseBody = response.body();
            final long contentLength = responseBody.contentLength();

            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = responseBody.contentType();
            if (contentType != null) {
                try {
                    charset = contentType.charset(charset);
                } catch (UnsupportedCharsetException e) {
                    log("");
                    log("Couldn't decode the response body; charset is likely malformed.");
                    return response;
                }
            }

            if (contentLength != 0) {
                log("--------------------------------------------开始打印返回数据----------------------------------------------------");
                log(buffer.clone().readString(charset));
                log("--------------------------------------------结束打印返回数据----------------------------------------------------");
            }

            return response;
        }
    };

    private void log(String msg) {
        LogUtil.logd(RetrofitManager.class.getSimpleName(), msg);
    }

    private RetrofitManager() {
    }

    /**
     * 获取单例
     *
     * @param hostType host类型
     * @return 实例
     */
    public static RetrofitManager getInstance(int hostType) {
        RetrofitManager instance = sInstanceManager.get(hostType);
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            sInstanceManager.put(hostType, instance);
        }
        return instance;
    }

    /**
     * 根据网络状况获取缓存的策略
     *
     * @return
     */
    @NonNull
    private String getCacheControl() {
        return NetUtil.isConnected(App.getInstance()) ? CACHE_CONTROL_NETWORK : CACHE_CONTROL_CACHE;
    }

    private RetrofitManager(@HostType.HostTypeChecker int hostType) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.getHost(hostType)).client(getOkHttpClient()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        mRequestApi = retrofit.create(RequestApi.class);
    }

    // 配置OkHttpClient
    private OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (mOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
                    Cache cache = new Cache(new File(App.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder().cache(cache).addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            // .addInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor).retryOnConnectionFailure(true).connectTimeout(30, TimeUnit.SECONDS).build();

                }
            }
        }
        return mOkHttpClient;
    }

    /**
     * 网易新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     * <p>
     * 对API调用了observeOn(MainThread)之后，线程会跑在主线程上，包括onComplete也是，
     * unsubscribe也在主线程，然后如果这时候调用call.cancel会导致NetworkOnMainThreadException
     * 加一句unsubscribeOn(io)
     */
    public Observable<Map<String, List<NewsListData>>> getNewsListObservable(String type, String id, int startPage) {
        return mRequestApi.getNewList(getCacheControl(), type, id, startPage).compose(new SchedulerTransformer<Map<String, List<NewsListData>>>());
    }

    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    public Observable<Map<String, List<VideoListData>>> getVideoListObservable(String id, int startPage) {
        return mRequestApi.getVideoList(getCacheControl(), id, startPage).compose(new SchedulerTransformer<Map<String, List<VideoListData>>>());
    }

}
