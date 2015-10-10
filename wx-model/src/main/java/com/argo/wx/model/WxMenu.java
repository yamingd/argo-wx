package com.argo.wx.model;

import com.argo.annotation.Column;
import com.argo.annotation.Table;
import org.msgpack.annotation.MessagePackMessage;

import java.io.Serializable;
import java.util.Date;


/**
 * 微信菜单表
 * Created by  on 2015-09-28 15:36.
 */
@Table("wx_menu")
@MessagePackMessage
public class WxMenu implements Serializable {

    /**
     * 记录id
     * int(11)
     */
    @Column(pk = true, autoIncrement=true)
    private Integer id;
    /**
     * 菜单名称
     * varchar(255)
     */
    @Column(maxLength="255", nullable = false)
    private String title;
    /**
     * 上级菜单名称
     * int(11) 默认为: 0
     */
    @Column(nullable = false, defaultVal = "0")
    private Integer parentId;
    /**
     * 显示顺序
     * smallint(6) 默认为: 100
     */
    @Column(nullable = false, defaultVal = "100")
    private Short orderNo;
    /**
     * 英文代号
     * varchar(255)
     */
    @Column(maxLength="255")
    private String codeName;
    /**
     * 跳转到页面路径
     * varchar(255)
     */
    @Column(maxLength="255")
    private String pageUrl;
    /**
     *
     * datetime
     */
    @Column
    private Date createTime;
    /**
     *
     * timestamp
     */
    @Column
    private Date updateTime;
    /**
     * 状态(0:正常,1:已发布,9:删除)
     * smallint(2) 默认为: 0
     */
    @Column(nullable = false, defaultVal = "0")
    private Short statusId;
    /**
     * 同步到微信(1:已同步,0:未同步)
     * tinyint(1) 默认为: 0
     */
    @Column(nullable = false, defaultVal = "0")
    private Byte wxSync;
    /**
     * 是否需要绑定微信帐号(1/0)
     * tinyint(1) 默认为: 0
     */
    @Column(nullable = false, defaultVal = "0")
    private Byte wxBind;

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
     * 菜单名称
     *
     */
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    /**
     * 上级菜单名称
     * 默认为: 0
     */
    public Integer getParentId(){
        return this.parentId;
    }
    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }
    /**
     * 显示顺序
     * 默认为: 100
     */
    public Short getOrderNo(){
        return this.orderNo;
    }
    public void setOrderNo(Short orderNo){
        this.orderNo = orderNo;
    }
    /**
     * 英文代号
     *
     */
    public String getCodeName(){
        return this.codeName;
    }
    public void setCodeName(String codeName){
        this.codeName = codeName;
    }
    /**
     * 跳转到页面路径
     *
     */
    public String getPageUrl(){
        return this.pageUrl;
    }
    public void setPageUrl(String pageUrl){
        this.pageUrl = pageUrl;
    }
    /**
     *
     *
     */
    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    /**
     *
     *
     */
    public Date getUpdateTime(){
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    /**
     * 状态(0:正常,1:已发布,9:删除)
     * 默认为: 0
     */
    public Short getStatusId(){
        return this.statusId;
    }
    public void setStatusId(Short statusId){
        this.statusId = statusId;
    }
    /**
     * 同步到微信(1:已同步,0:未同步)
     * 默认为: 0
     */
    public Byte getWxSync(){
        return this.wxSync;
    }
    public void setWxSync(Byte wxSync){
        this.wxSync = wxSync;
    }
    /**
     * 是否需要绑定微信帐号(1/0)
     * 默认为: 0
     */
    public Byte getWxBind(){
        return this.wxBind;
    }
    public void setWxBind(Byte wxBind){
        this.wxBind = wxBind;
    }

    /****引用实体****/


}
