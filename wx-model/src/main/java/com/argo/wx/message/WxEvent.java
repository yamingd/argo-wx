package com.argo.wx.message;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * http://mp.weixin.qq.com/wiki/2/5baf56ce4947d35003b86a9805634b1e.html
 * Created by Yaming on 2014/12/17.
 */
public class WxEvent extends WxMessage {

    private String event;
    private String eventKey;
    private String ticket;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String getMsgTypeKey() {
        return this.getMsgType()+":"+this.getEvent();
    }

    @Override
    public Element parseFrom(Document document){
        Element root = super.parseFrom(document);
        this.event = root.getChildText("Event");
        this.eventKey = root.getChildText("EventKey");
        this.ticket = root.getChildText("Ticket");
        return root;
    }

    @Override
    public String toString() {
        return "WxEvent{" + super.toString() +
                ", event='" + event + '\'' +
                ", eventKey='" + eventKey + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
