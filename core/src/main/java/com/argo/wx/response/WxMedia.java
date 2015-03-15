package com.argo.wx.response;

import org.jdom2.CDATA;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxMedia {

    /*
    <MediaId><![CDATA[media_id]]></MediaId>
    <Title><![CDATA[title]]></Title>
    <Description><![CDATA[description]]></Description>
     */

    private String mediaId;
    private String title;
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

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

    public Element appendTo(Element root, String name){
        Element item = new Element(name);
        item.addContent(new Element("MediaId").setContent(new CDATA(this.mediaId)));
        if (this.title != null) {
            item.addContent(new Element("Title").setContent(new CDATA(this.title)));
        }
        if (this.description != null) {
            item.addContent(new Element("Description").setContent(new CDATA(this.description)));
        }
        root.addContent(item);
        return root;
    }

    public static WxMedia create(Element root){
        WxMedia media = new WxMedia();
        media.setDescription(root.getChildText("Description"));
        media.setMediaId(root.getChildText("MediaId"));
        media.setTitle(root.getChildText("Title"));
        return media;
    }

    @Override
    public String toString() {
        return "WxMedia{" +
                "mediaId='" + mediaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
