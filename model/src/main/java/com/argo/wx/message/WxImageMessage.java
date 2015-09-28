package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxImageMessage extends WxMessage {

    /**
     * <PicUrl><![CDATA[this is a url]]></PicUrl>
     <MediaId><![CDATA[media_id]]></MediaId>
     */

    private String picUrl;
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public Element parseFrom(Document document){
        Element root = super.parseFrom(document);
        this.picUrl = root.getChildText("PicUrl");
        this.mediaId = root.getChildText("MediaId");
        return root;
    }
}
