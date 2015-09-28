package com.argo.wx.model;

import com.argo.annotation.Column;
import com.argo.annotation.Table;
import org.msgpack.annotation.MessagePackMessage;

import java.io.Serializable;
import java.util.Date;


/**
 * 微信用户记录表
 * Created by  on 2015-09-28 15:36.
 */
@Table("wx_user")
@MessagePackMessage
public class WxUser implements Serializable {

    /**
     * 记录id
     * int(11) unsigned
     */
    @Column(pk = true, autoIncrement=true)
    private Integer id;
    /**
     * wx用户id
     * varchar(32)
     */
    @Column(maxLength="32")
    private String openId;
    /**
     * wx用户Token
     * varchar(255)
     */
    @Column(maxLength="255")
    private String openToken;
    /**
     * 记录创建日期
     * datetime
     */
    @Column
    private Date createTime;
    /**
     * 绑定时间
     * datetime
     */
    @Column
    private Date bindTime;
    /**
     * 业务用户id
     * int(11)
     */
    @Column
    private Integer userId;
    /**
     * 最后访问时间
     * datetime
     */
    @Column
    private Date lastAccessTime;
    /**
     * 状态(0:创建,1:绑定,2:解绑)
     * tinyint(1) 默认为: 0
     */
    @Column(nullable = false, defaultVal = "0")
    private Byte statusId;
    /**
     * 解绑时间
     * datetime
     */
    @Column
    private Date rmBindTime;

    /**
     * 记录id
     *
     */
    public Integer getId(){
        return this.id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    /**
     * wx用户id
     *
     */
    public String getOpenId(){
        return this.openId;
    }
    public void setOpenId(String openId){
        this.openId = openId;
    }
    /**
     * wx用户Token
     *
     */
    public String getOpenToken(){
        return this.openToken;
    }
    public void setOpenToken(String openToken){
        this.openToken = openToken;
    }
    /**
     * 记录创建日期
     *
     */
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     * 绑定时间
     *
     */
    public Date getBindTime(){
        return this.bindTime;
    }
    public void setBindTime(Date bindTime){
        this.bindTime = bindTime;
    }
    /**
     * 业务用户id
     *
     */
    public Integer getUserId(){
        return this.userId;
    }
    public void setUserId(Integer userId){
        this.userId = userId;
    }
    /**
     * 最后访问时间
     *
     */
    public Date getLastAccessTime(){
        return this.lastAccessTime;
    }
    public void setLastAccessTime(Date lastAccessTime){
        this.lastAccessTime = lastAccessTime;
    }
    /**
     * 状态(0:创建,1:绑定,2:解绑)
     * 默认为: 0
     */
    public Byte getStatusId(){
        return this.statusId;
    }
    public void setStatusId(Byte statusId){
        this.statusId = statusId;
    }
    /**
     * 解绑时间
     *
     */
    public Date getRmBindTime(){
        return this.rmBindTime;
    }
    public void setRmBindTime(Date rmBindTime){
        this.rmBindTime = rmBindTime;
    }

    /****引用实体****/


}
