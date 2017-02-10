package com.zjk.fireflynews.data.video;

import java.io.Serializable;

/**
 * Created by FireFly on 2016/10/8 20:46.
 */
public class VideoListData implements Serializable {
    private static final long serialVersionUID = 10000L;
    /**
     * 自己加的记录测出来的宽高
     */
    private int picWidth = -1;
    private int picHeight = -1;

    public int getPicHeight() {
        return picHeight;
    }

    public void setPicHeight(int picHeight) {
        this.picHeight = picHeight;
    }

    public int getPicWidth() {
        return picWidth;
    }

    public void setPicWidth(int picWidth) {
        this.picWidth = picWidth;
    }

    /**
     * topicImg : http://vimg3.ws.126.net/image/snapshot/2016/9/J/7/VBV15U2J7.jpg
     * videosource : 新媒体
     * mp4Hd_url : http://flv2.bn.netease.com/videolib3/1610/08/rByeW7939/HD/rByeW7939-mobile.mp4
     * topicDesc : 法治中国60分账号依托电视节目《法治中国60&amp;#39;》汇集全国法治电视精英，社会热点案件、事件第一时间播报。
     * topicSid : VBV15U2J3
     * cover : http://vimg1.ws.126.net/image/snapshot/2016/10/U/I/VC1UUOJUI.jpg
     * title : 男子因欠债2万元竟付出如此沉重代价
     * playCount : 0
     * replyBoard : video_bbs
     * videoTopic : {"alias":"传播法治最强音","tname":"法治中国60分","ename":"T1472781956473","tid":"T1472781956473"}
     * sectiontitle :
     * replyid : C1UC8FV5008535RB
     * description :
     * mp4_url : http://flv2.bn.netease.com/videolib3/1610/08/rByeW7939/SD/rByeW7939-mobile.mp4
     * length : 180
     * playersize : 1
     * m3u8Hd_url : http://flv2.bn.netease.com/videolib3/1610/08/rByeW7939/HD/movie_index.m3u8
     * vid : VC1UC8FV5
     * m3u8_url : http://flv2.bn.netease.com/videolib3/1610/08/rByeW7939/SD/movie_index.m3u8
     * ptime : 2016-10-08 20:26:28
     * topicName : 法治中国60分
     */

    private String topicImg;
    private String videosource;
    private String mp4Hd_url;
    private String topicDesc;
    private String topicSid;
    private String cover;
    private String title;
    private int playCount;
    private String replyBoard;
    /**
     * alias : 传播法治最强音
     * tname : 法治中国60分
     * ename : T1472781956473
     * tid : T1472781956473
     */

    private VideoTopicBean videoTopic;
    private String sectiontitle;
    private String replyid;
    private String description;
    private String mp4_url;
    private int length;
    private int playersize;
    private String m3u8Hd_url;
    private String vid;
    private String m3u8_url;
    private String ptime;
    private String topicName;

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getTopicSid() {
        return topicSid;
    }

    public void setTopicSid(String topicSid) {
        this.topicSid = topicSid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public VideoTopicBean getVideoTopic() {
        return videoTopic;
    }

    public void setVideoTopic(VideoTopicBean videoTopic) {
        this.videoTopic = videoTopic;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPlayersize() {
        return playersize;
    }

    public void setPlayersize(int playersize) {
        this.playersize = playersize;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public static class VideoTopicBean implements Serializable {
        private static final long serialVersionUID = 10001L;
        private String alias;
        private String tname;
        private String ename;
        private String tid;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
