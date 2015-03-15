package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/18.
 */
public class WxMusicResponse extends WxResponse {

    private WxMusicItem musicItem;

    public WxMusicResponse(WxMusicItem musicItem) {
        this.musicItem = musicItem;
    }

    public WxMusicItem getMusicItem() {
        return musicItem;
    }

    public void setMusicItem(WxMusicItem musicItem) {
        this.musicItem = musicItem;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.musicItem.appendTo(doc.getRootElement());
        return doc;
    }

}
