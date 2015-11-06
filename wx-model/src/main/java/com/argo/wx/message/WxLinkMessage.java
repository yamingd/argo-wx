package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxLinkMessage extends WxMessage {

    private String title;
    private String description;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public Element parseFrom(Document document){
        Element ele = super.parseFrom(document);
        this.title = ele.getChildText("Title");
        this.description = ele.getChildText("Description");
        this.url = ele.getChildText("Url");
        return ele;
    }
}
