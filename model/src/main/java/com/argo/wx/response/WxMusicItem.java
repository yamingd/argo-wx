package com.argo.wx.response;

import org.jdom2.CDATA;
import org.jdom2.Element;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxMusicItem {

    /**
     * <xml>
     <ToUserName><![CDATA[toUser]]></ToUserName>
     <FromUserName><![CDATA[fromUser]]></FromUserName>
     <CreateTime>12345678</CreateTime>
     <MsgType><![CDATA[music]]></MsgType>
     <Music>
     <Title><![CDATA[TITLE]]></Title>
     <Description><![CDATA[DESCRIPTION]]></Description>
     <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>
     <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>
     <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>
     </Music>
     </xml>
     */

    private String title;
    private String description;
    private String musicUrl;
    private String hqMusicUrl;
    private String thumbMediaId;

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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public Element appendTo(Element root){
        Element item = new Element("Music");
        item.addContent(new Element("Title").setContent(new CDATA(this.title)));
        if (this.musicUrl != null) {
            item.addContent(new Element("MusicUrl").setContent(new CDATA(this.musicUrl)));
        }
        if (this.hqMusicUrl != null) {
            item.addContent(new Element("HQMusicUrl").setContent(new CDATA(this.hqMusicUrl)));
        }
        if (this.description != null) {
            item.addContent(new Element("Description").setContent(new CDATA(this.description)));
        }
        if (this.thumbMediaId != null) {
            item.addContent(new Element("ThumbMediaId").setContent(new CDATA(this.thumbMediaId)));
        }
        root.addContent(item);
        return root;
    }

    @Override
    public String toString() {
        return "WxMusicItem{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", hqMusicUrl='" + hqMusicUrl + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                '}';
    }
}
