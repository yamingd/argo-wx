package com.argo.wx.token;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.msgpack.annotation.MessagePackMessage;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * Created by user on 3/16/15.
 */
@MessagePackMessage
public class WxJsSignToken {

    private long timestamp;
    private String noncestr;
    private String ticket;
    private String url;

    private String sign;

    public WxJsSignToken() {
    }

    public WxJsSignToken(String url, String ticket) {
        this.url = url;
        this.ticket = ticket;
        this.timestamp = new Date().getTime()/1000;
        this.noncestr = String.format("wx%06d", new Random().nextInt(Integer.MAX_VALUE));

        this.build();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getTicket() {
        return ticket;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public String getUrl() {
        return url;
    }

    public String getSign() {
        return sign;
    }

    private void build(){
        String[] a = {"noncestr=" + this.noncestr, "timestamp=" + timestamp, "jsapi_ticket=" + this.ticket, "url=" + this.url};
        Arrays.sort(a);//数组排序
        String str = StringUtils.join(a, "&");
        this.sign = DigestUtils.sha1Hex(str);//SHA1加密
    }

    @Override
    public String toString() {
        return "WxJsSignToken{" +
                "timestamp=" + timestamp +
                ", noncestr='" + noncestr + '\'' +
                ", ticket='" + ticket + '\'' +
                ", url='" + url + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
