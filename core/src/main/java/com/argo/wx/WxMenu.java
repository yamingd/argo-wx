package com.argo.wx;

import com.argo.core.annotation.Column;
import com.argo.core.annotation.EntityDef;
import com.argo.core.annotation.PK;
import com.argo.core.base.BaseEntity;
import org.msgpack.annotation.MessagePackMessage;

import java.util.Date;

/**
 * 微信菜单表
 * Created by $User on 2014-12-05 17:57.
 */
@EntityDef(table = "wx_menu")
@MessagePackMessage
public class WxMenu extends BaseEntity {
    
    /**
     * 记录id
     * int(11) 
     */
    @PK("id")
	@Column private Integer id;
    /**
     * 菜单名称
     * varchar(255) 
     */
    @Column private String title;
    /**
     * 上级菜单名称
     * int(11) 默认为: 0
     */
    @Column private Integer parentId;
    /**
     * 显示顺序
     * smallint(6) 默认为: 100
     */
    @Column private Integer orderNo;
    /**
     * 英文代号
     * varchar(255) 
     */
    @Column private String codeName;
    /**
     * 跳转到页面路径
     * varchar(255) 
     */
    @Column private String pageUrl;
    /**
     * 
     * datetime 
     */
    @Column private Date createTime;
    /**
     * 
     * timestamp 
     */
    @Column private Date updateTime;
    /**
     * 状态(0:正常,1:已发布,9:删除)
     * smallint(2) 默认为: 0
     */
    @Column private Integer statusId;

    /**
     * 同步到微信(1:已同步,0:未同步)
     */
    @Column private Boolean wxSync;

    /**
     * 是否需要绑定微信帐号(1/0)
     */
    @Column private Boolean wxBind;


    @Override
    public String getPK() {
        return  ":" + id ;
    }

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
    public Integer getOrderNo(){
        return this.orderNo;
    }
    public void setOrderNo(Integer orderNo){
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
    public Integer getStatusId(){
        return this.statusId;
    }
    public void setStatusId(Integer statusId){
        this.statusId = statusId;
    }

    public boolean getWxSync() {
        return wxSync;
    }

    public void setWxSync(boolean wxSync) {
        this.wxSync = wxSync;
    }

    public Boolean getWxBind() {
        return wxBind;
    }

    public void setWxBind(Boolean wxBind) {
        this.wxBind = wxBind;
    }
}