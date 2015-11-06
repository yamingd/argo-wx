package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxVoiceMessage extends WxMessage {

    private String mediaId;
    private String format;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public Element parseFrom(Document document){
        Element ele = super.parseFrom(document);
        this.mediaId = ele.getChildText("MediaId");
        this.format = ele.getChildText("Format");
        return ele;
    }
}
