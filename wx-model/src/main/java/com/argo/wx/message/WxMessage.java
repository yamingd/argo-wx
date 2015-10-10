package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

import java.io.Serializable;

/**
 * http://mp.weixin.qq.com/wiki/10/79502792eef98d6e0c6e1739da387346.html#.E8.AF.AD.E9.9F.B3.E6.B6.88.E6.81.AF
 * Created by Yaming on 2014/12/17.
 */
public class WxMessage implements Serializable {

    protected Element rootElement;
    protected Document document;

    private String msgId;
    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private String msgType;

    public WxMessage() {
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgTypeKey(){
        return this.msgType;
    }

    public Element parseFrom(Document document){
        // jdomDocument is the JDOM2 Object
        this.document = document;
        this.rootElement = this.document.getRootElement();
        this.toUserName = rootElement.getChildText("ToUserName");
        this.fromUserName = rootElement.getChildText("FromUserName");
        this.createTime = Long.parseLong(rootElement.getChildText("CreateTime"));
        this.msgType = rootElement.getChildText("MsgType");
        this.msgId = rootElement.getChildText("MsgId");
        return rootElement;
    }

    @Override
    public String toString() {
        return "WxMessage{" +
                "rootElement=" + rootElement +
                ", document=" + document +
                ", msgId='" + msgId + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
