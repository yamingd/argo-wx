package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxVideoMessage extends WxMessage {

    private String mediaId;
    private String thumbMediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @Override
    public Element parseFrom(Document document){
        Element ele = super.parseFrom(document);
        this.mediaId = ele.getChildText("MediaId");
        this.thumbMediaId = ele.getChildText("ThumbMediaId");
        return ele;
    }
}
