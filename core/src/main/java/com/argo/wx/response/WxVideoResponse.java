package com.argo.wx.response;

import org.jdom2.Document;

/**
 * Created by Yaming on 2014/12/17.
 */
public class WxVideoResponse extends WxResponse {

    /*
    <xml>
    <ToUserName><![CDATA[toUser]]></ToUserName>
    <FromUserName><![CDATA[fromUser]]></FromUserName>
    <CreateTime>12345678</CreateTime>
    <MsgType><![CDATA[video]]></MsgType>
    <Video>
    <MediaId><![CDATA[media_id]]></MediaId>
    <Title><![CDATA[title]]></Title>
    <Description><![CDATA[description]]></Description>
    </Video>
    </xml>
     */

    private WxMedia video;

    public WxVideoResponse(WxMedia video) {
        this.video = video;
        this.setMsgType("video");
    }

    public WxMedia getVideo() {
        return video;
    }

    public void setVideo(WxMedia video) {
        this.video = video;
    }

    @Override
    public Document toXml() {
        Document doc = super.toXml();
        this.video.appendTo(doc.getRootElement(), "Video");
        return doc;
    }

}
