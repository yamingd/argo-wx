package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxVoiceResponse extends WxResponse {

    private WxMedia voice;

    public WxVoiceResponse(WxMedia voice) {
        this.voice = voice;
        this.setMsgType("voice");
    }

    public WxMedia getVoice() {
        return voice;
    }

    public void setVoice(WxMedia voice) {
        this.voice = voice;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.voice.appendTo(doc.getRootElement(), "Voice");
        return doc;
    }

}
