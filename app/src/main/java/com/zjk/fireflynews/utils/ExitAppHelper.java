package com.zjk.fireflynews.utils;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

import com.zjk.fireflynews.MainActivity;
import com.zjk.fireflynews.R;

import java.util.concurrent.TimeUnit;

/**
 * Created by FireFly on 2016/9/26 16:48.
 */

public class ExitAppHelper {
    private Activity mActivity;
    private final int MSG_EXIT = 1;
    private final int MSG_WAIT_EXIT = MSG_EXIT + 1;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case MSG_EXIT:
                    if(mHandler.hasMessages(MSG_WAIT_EXIT)){
                        mActivity.moveTaskToBack(true);
                        mActivity.finish();
                    }else {
                        ((MainActivity)mActivity).toast(mActivity.getString(R.string.str_exit_msg));
                        mHandler.sendEmptyMessageDelayed(MSG_WAIT_EXIT, TimeUnit.MILLISECONDS.convert(2,TimeUnit.SECONDS));
                    }
                    break;
                case MSG_WAIT_EXIT:
                    break;
                default:
                    break;
            }
            return false;
        }
    });
    public ExitAppHelper(Activity activity){
        mActivity = activity;
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            mHandler.sendEmptyMessage(MSG_EXIT);
            return true;
        }else{
            return false;
        }
    }
}
