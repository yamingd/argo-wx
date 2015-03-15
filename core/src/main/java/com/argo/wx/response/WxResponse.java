package com.argo.wx.response;

import com.argo.wx.message.WxEvent;
import org.jdom2.CDATA;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Date;

/**
 * http://mp.weixin.qq.com/wiki/9/2c15b20a16019ae613d413e30cac8ea1.html
 * Created by Yaming on 2014/12/17.
 */
public class WxResponse implements Serializable {

    protected Document document;
    protected Element root;

    private String toUserName;
    private String fromUserName;
    private Long createTime;
    private String msgType;

    public WxResponse() {
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


    public Document toXml() {
        if (this.createTime == null){
            this.createTime = new Date().getTime() / 1000;
        }
        this.document = new Document();
        this.root = new Element("xml");
        this.document.setRootElement(root);
        this.root.addContent(new Element("ToUserName").setContent(new CDATA(this.toUserName)));
        this.root.addContent(new Element("FromUserName").setContent(new CDATA(this.fromUserName)));
        this.root.addContent(new Element("CreateTime").setText(this.createTime + ""));
        this.root.addContent(new Element("MsgType").setContent(new CDATA(this.msgType)));
        return this.document;
    }

    public void wrapSessionInfo(WxEvent event){
        this.setFromUserName(event.getToUserName());
        this.setToUserName(event.getFromUserName());
    }

    @Override
    public String toString() {
        StringWriter sw = new StringWriter();
        XMLOutputter xmOut = new XMLOutputter();
        try {
            xmOut.output(this.document, sw);
            return this.getClass().getName() + "@" + sw.toString();
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
