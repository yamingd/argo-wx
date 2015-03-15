package com.argo.wx;

import com.argo.core.annotation.Column;
import com.argo.core.annotation.EntityDef;
import com.argo.core.base.BaseEntity;
import org.msgpack.annotation.MessagePackMessage;

import java.util.Date;

/**
 * 微信菜单表
 * Created by $User on 2014-12-05 17:57.
 */
@EntityDef(table = "wx_user")
@MessagePackMessage
public class WxUser extends BaseEntity {

    @Column private Long id;

    @Column private String openId;

    @Column private String openToken;

    @Column private Date createTime;

    @Column private Date bindTime;

    @Column private Long userId;

    @Column private Date lastAccessTime;

    @Column private Integer statusId;

    @Column private Date rmBindTime;

    @Override
    public String getPK() {
        return ":" + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenToken() {
        return openToken;
    }

    public void setOpenToken(String openToken) {
        this.openToken = openToken;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public Date getRmBindTime() {
        return rmBindTime;
    }

    public void setRmBindTime(Date rmBindTime) {
        this.rmBindTime = rmBindTime;
    }
}
