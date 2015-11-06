package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxImageResponse extends WxResponse {

    private WxMedia image;

    public WxImageResponse(WxMedia image) {
        this.image = image;
        this.setMsgType("image");
    }

    public WxMedia getImage() {
        return image;
    }

    public void setImage(WxMedia image) {
        this.image = image;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.image.appendTo(doc.getRootElement(), "Image");
        return doc;
    }
}
