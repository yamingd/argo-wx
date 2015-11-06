package com.argo.wx.response;

import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxTextResponse extends WxResponse {

    private String content;

    public WxTextResponse(String content) {
        this.content = content;
        this.setMsgType("text");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.root.addContent(new Element("Content").setContent(new CDATA(this.content)));
        return doc;
    }

}
