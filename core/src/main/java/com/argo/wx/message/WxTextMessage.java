package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxTextMessage extends WxMessage {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Element parseFrom(Document document){
        Element root = super.parseFrom(document);
        this.content = root.getChildText("Content");
        return root;
    }
}
