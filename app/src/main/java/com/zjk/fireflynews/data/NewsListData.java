package com.zjk.fireflynews.data;

import java.util.List;

/**
 * Created by FireFly on 2016/9/23 17:18.
 */
public class NewsListData {

    /**
     * postid : B8MGE5E100964JJM
     * url_3w : http://help.3g.163.com/15/1118/07/B8MGE5E100964JJM.html
     * votecount : 0
     * replyCount : 4
     * skipID : S1439793755179
     * ltitle : BZDSJ
     * digest : 王尼玛、唐马儒、张全蛋带你看一周大事！每周五更新。
     * skipType : special
     * specialextra : [{"url_3w":"http://news.163.com/16/0930/10/C274AQ2900015C49.html","postid":"C274AQ2900015C49","docid":"C274AQ2900015C49","title":"暴走大事件(第四季63集):我和老哥杨永信的故事","source":"暴走大事件","priority":60,"ltitle":"暴走大事件(第四季63集):我和老哥杨永信的故事","imgsrc":"http://cms-bucket.nosdn.127.net/fea0b324e61b4166ab801b0166c20fe920160930105057.jpeg","subtitle":"","boardid":"news2_bbs","digest":"来自氪金网红的心声，投入不能小于产出；职场侵犯不应软弱，今天不是你死就是你亡；美国大选，美国人民喜大普奔已玩嗨；国产又出巨制神作，迷之剧情叫板爵迹；杨永信故事剧","ptime":"2016-09-30 10:49:33","url":"http://3g.163.com/news/16/0930/10/C274AQ2900015C49.html"},{"url_3w":"http://news.163.com/16/0916/12/C138789O00015C49.html","postid":"C138789O00015C49","docid":"C138789O00015C49","title":"暴走大事件(第四季61集)：铁柱跌入爱情悬崖","source":"轻松一刻工作室","priority":60,"ltitle":"暴走大事件(第四季61集)：铁柱跌入爱情悬崖","imgsrc":"http://cms-bucket.nosdn.127.net/7846db8f3445409082723de5fef264b720160916125903.jpeg","subtitle":"","boardid":"news2_bbs","digest":"　　　　　　　　　　　　　　　　　　　　","ptime":"2016-09-16 12:24:52","url":"http://3g.163.com/news/16/0916/12/C138789O00015C49.html"},{"url_3w":"http://news.163.com/16/0909/11/C0H2TVNL00015C49.html","postid":"C0H2TVNL00015C49","docid":"C0H2TVNL00015C49","title":"暴走大事件(第四季60集)","source":"轻松一刻工作室","priority":60,"ltitle":"暴走大事件(第四季60集)","imgsrc":"http://cms-bucket.nosdn.127.net/0d50438615ec47ff906c0f0fd6aee8f420160909111104.jpeg","subtitle":"","boardid":"news2_bbs","digest":"　　　　　　　　　　　　　　　　　　　　","ptime":"2016-09-09 11:06:05","url":"http://3g.163.com/news/16/0909/11/C0H2TVNL00015C49.html"}]
     * specialtip : 查看往期
     * url : http://3g.163.com/ntes/15/1118/07/B8MGE5E100964JJM.html
     * specialID : S1439793755179
     * docid : B8MGE5E100964JJM_special
     * title : BZDSJ
     * source : 暴走漫画
     * speciallogo : http://img1.cache.netease.com/3g/2015/8/21/201508211340599f50f.png
     * priority : 76
     * lmodify : 2016-10-02 10:22:28
     * boardid : 3g_bbs
     * imgsrc : http://img3.cache.netease.com/3g/2016/4/29/20160429114642deff5.jpg
     * subtitle : 暴走大事件
     * ptime : 2015-11-18 07:21:35
     * specialadlogo :
     */

    private String postid;
    private String url_3w;
    private int votecount;
    private int replyCount;
    private String skipID;
    private String ltitle;
    private String digest;
    private String skipType;
    private String specialtip;
    private String url;
    private String specialID;
    private String docid;
    private String title;
    private String source;
    private String speciallogo;
    private int priority;
    private String lmodify;
    private String boardid;
    private String imgsrc;
    private String subtitle;
    private String ptime;
    private String specialadlogo;
    /**
     * url_3w : http://news.163.com/16/0930/10/C274AQ2900015C49.html
     * postid : C274AQ2900015C49
     * docid : C274AQ2900015C49
     * title : 暴走大事件(第四季63集):我和老哥杨永信的故事
     * source : 暴走大事件
     * priority : 60
     * ltitle : 暴走大事件(第四季63集):我和老哥杨永信的故事
     * imgsrc : http://cms-bucket.nosdn.127.net/fea0b324e61b4166ab801b0166c20fe920160930105057.jpeg
     * subtitle :
     * boardid : news2_bbs
     * digest : 来自氪金网红的心声，投入不能小于产出；职场侵犯不应软弱，今天不是你死就是你亡；美国大选，美国人民喜大普奔已玩嗨；国产又出巨制神作，迷之剧情叫板爵迹；杨永信故事剧
     * ptime : 2016-09-30 10:49:33
     * url : http://3g.163.com/news/16/0930/10/C274AQ2900015C49.html
     */

    private List<SpecialextraBean> specialextra;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getLtitle() {
        return ltitle;
    }

    public void setLtitle(String ltitle) {
        this.ltitle = ltitle;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSpecialtip() {
        return specialtip;
    }

    public void setSpecialtip(String specialtip) {
        this.specialtip = specialtip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpecialID() {
        return specialID;
    }

    public void setSpecialID(String specialID) {
        this.specialID = specialID;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSpeciallogo() {
        return speciallogo;
    }

    public void setSpeciallogo(String speciallogo) {
        this.speciallogo = speciallogo;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getLmodify() {
        return lmodify;
    }

    public void setLmodify(String lmodify) {
        this.lmodify = lmodify;
    }

    public String getBoardid() {
        return boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getSpecialadlogo() {
        return specialadlogo;
    }

    public void setSpecialadlogo(String specialadlogo) {
        this.specialadlogo = specialadlogo;
    }

    public List<SpecialextraBean> getSpecialextra() {
        return specialextra;
    }

    public void setSpecialextra(List<SpecialextraBean> specialextra) {
        this.specialextra = specialextra;
    }

    public static class SpecialextraBean {
        private String url_3w;
        private String postid;
        private String docid;
        private String title;
        private String source;
        private int priority;
        private String ltitle;
        private String imgsrc;
        private String subtitle;
        private String boardid;
        private String digest;
        private String ptime;
        private String url;

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public String getPostid() {
            return postid;
        }

        public void setPostid(String postid) {
            this.postid = postid;
        }

        public String getDocid() {
            return docid;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getLtitle() {
            return ltitle;
        }

        public void setLtitle(String ltitle) {
            this.ltitle = ltitle;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
