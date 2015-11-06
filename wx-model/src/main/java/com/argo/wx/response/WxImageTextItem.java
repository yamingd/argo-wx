package com.argo.wx.response;

import org.jdom2.CDATA;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxImageTextItem {

    private String title;
    private String description;
    /**
     * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     */
    private String picUrl;
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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Element appendTo(Element root){
        Element item = new Element("item");
        item.addContent(new Element("Title").setContent(new CDATA(this.title)));
        if (this.picUrl != null) {
            item.addContent(new Element("PicUrl").setContent(new CDATA(this.picUrl)));
        }
        if (this.url != null) {
            item.addContent(new Element("Url").setContent(new CDATA(this.url)));
        }
        if (this.description != null) {
            item.addContent(new Element("Description").setContent(new CDATA(this.description)));
        }
        root.addContent(item);
        return root;
    }

    @Override
    public String toString() {
        return "WxImageTextItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
