package com.zjk.fireflynews.data.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FireFly on 2017/2/8 09:46.
 */
public class GankData implements Serializable {
    private static final long serialVersionUID = 10011L;

    private boolean error;
    private ResultsBean results;
    private List<String> category;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ResultsBean getResults() {
        return results;
    }

    public void setResults(ResultsBean results) {
        this.results = results;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class ResultsBean {

        private List<GankGirl> 福利;
        private List<GankItemDesc> iOS;
        private List<GankItemDesc> Android;
        private List<GankItemDesc> 休息视频;

        public List<GankItemDesc> getAndroid() {
            return Android;
        }

        public void setAndroid(List<GankItemDesc> android) {
            Android = android;
        }

        public List<GankItemDesc> getiOS() {
            return iOS;
        }

        public void setiOS(List<GankItemDesc> iOS) {
            this.iOS = iOS;
        }

        public List<GankItemDesc> get休息视频() {
            return 休息视频;
        }

        public void set休息视频(List<GankItemDesc> 休息视频) {
            this.休息视频 = 休息视频;
        }

        public List<GankGirl> get福利() {
            return 福利;
        }

        public void set福利(List<GankGirl> 福利) {
            this.福利 = 福利;
        }
    }
}
