package com.zjk.fireflynews.http.RestApi;

import com.zjk.fireflynews.data.gank.GankData;
import com.zjk.fireflynews.data.news.NewsDetailData;
import com.zjk.fireflynews.data.news.NewsListData;
import com.zjk.fireflynews.data.video.VideoListData;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by FireFly on 2016/9/23.
 */

public interface RequestApi {
    /**
     * 请求新闻列表 例子：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     *
     * @param type      新闻类别：headline为头条,local为北京本地,fangchan为房产,list为其他
     * @param id        新闻类别id
     * @param startPage 开始的页码
     * @return 被观察对象
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsListData>>> getNewList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type, @Path("id") String id,
            @Path("startPage") int startPage);

    /**
     * 新闻详情：例子：http://c.m.163.com/nc/article/BFNFMVO800034JAU/full.html
     *
     * @param postId 新闻详情的id
     * @return 被观察对象
     */
    @GET("nc/article/{postId}/full.html")
    Observable<Map<String, NewsDetailData>> getNewsDetail(@Path("postId") String postId);

    /**
     * 网易视频列表 例子：http://c.m.163.com/nc/video/list/V9LG4B3A0/n/0-10.html
     *
     * @param id        视频类别id
     * @param startPage 开始的页码
     * @return 被观察者
     */
    @GET("nc/video/list/{id}/n/{startPage}-10.html")
    Observable<Map<String, List<VideoListData>>> getVideoList(
            @Header("Cache-Control") String cacheControl, @Path("id") String id, @Path("startPage") int startPage);


    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Header("Cache-Control") String cacheControl,
                                     @Path("year") int year, @Path("month") int month, @Path("day") int day);
}
