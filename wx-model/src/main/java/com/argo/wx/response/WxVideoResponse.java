package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxVideoResponse extends WxResponse {

    private WxMedia video;

    public WxVideoResponse(WxMedia video) {
        this.video = video;
        this.setMsgType("video");
    }

    public WxMedia getVideo() {
        return video;
    }

    public void setVideo(WxMedia video) {
        this.video = video;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.video.appendTo(doc.getRootElement(), "Video");
        return doc;
    }

}
