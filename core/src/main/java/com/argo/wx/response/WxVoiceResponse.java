package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxVoiceResponse extends WxResponse {

    /*
    <xml>
    <ToUserName><![CDATA[toUser]]></ToUserName>
    <FromUserName><![CDATA[fromUser]]></FromUserName>
    <CreateTime>12345678</CreateTime>
    <MsgType><![CDATA[voice]]></MsgType>
    <Voice>
    <MediaId><![CDATA[media_id]]></MediaId>
    </Voice>
    </xml>
     */

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
