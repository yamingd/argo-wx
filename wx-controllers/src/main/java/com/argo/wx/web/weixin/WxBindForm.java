package com.argo.wx.web.weixin;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.context.annotation.Scope;

/**
 * Created by yamingd on 9/30/15.
 */
@Scope("prototype")
public class WxBindForm {

    @NotBlank(message = "name_blank")
    private String name;

    @NotBlank(message = "passwd_blank")
    private String passwd;

    @NotBlank(message = "openid_blank")
    private String openid;

    @NotBlank(message = "opentoken_blank")
    private String opentoken;

    private String to;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpentoken() {
        return opentoken;
    }

    public void setOpentoken(String opentoken) {
        this.opentoken = opentoken;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
