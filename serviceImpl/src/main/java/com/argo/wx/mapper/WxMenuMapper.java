package com.argo.wx.mapper;

import com.argo.db.Values;
import com.argo.db.mysql.TableContext;
import com.argo.db.template.MySqlMapper;
import com.argo.wx.model.WxMenu;
import com.google.common.base.Preconditions;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by  on 2015-09-28 15:36.
 */
@Repository
public class WxMenuMapper extends MySqlMapper<WxMenu, Integer> {
    
    public static WxMenuMapper instance;

    public static final String N_tableName = "wx_menu";
    public static final String N_pkColumnName = "id";

    public static final String SQL_FIELDS = "id, title, parentId, orderNo, codeName, pageUrl, createTime, updateTime, statusId, wxSync, wxBind";
    public static final List<String> columnList = new ArrayList<String>();
    public static final boolean pkAutoIncr = true;

    static {
        columnList.add("id");
        columnList.add("title");
        columnList.add("parentId");
        columnList.add("orderNo");
        columnList.add("codeName");
        columnList.add("pageUrl");
        columnList.add("createTime");
        columnList.add("updateTime");
        columnList.add("statusId");
        columnList.add("wxSync");
        columnList.add("wxBind");
    }
    @Override
    public void prepare() {
        instance = this;
    }

    @Override
    public String getTableName() {
        return N_tableName;
    }

    @Override
    public String getTableName(TableContext context) {
        return null == context ? getTableName() : context.getName();
    }

    @Override
    public String getPKColumnName() {
        return N_pkColumnName;
    }

    @Override
    public String getSelectedColumns() {
        return SQL_FIELDS;
    }

    @Override
    public List<String> getColumnList() {
        return columnList;
    }

    @Override
    public Class<WxMenu> getRowClass() {
        return WxMenu.class;
    }

    @Override
    protected void setPKValue(WxMenu item, KeyHolder holder) {
        item.setId(holder.getKey().intValue());
    }

    @Override
    protected Integer getPkValue(WxMenu item) {
        return item.getId();
    }

    @Override
    protected boolean isPKAutoIncr() {
        return pkAutoIncr;
    }

    @Override
    protected WxMenu mapRow(ResultSet rs, int rowIndex) throws SQLException {
        WxMenu item = new WxMenu();

        Integer id0 = Values.getResultSetValue(rs, 1, Integer.class);
        item.setId(id0);

        String title0 = Values.getResultSetValue(rs, 2, String.class);
        item.setTitle(title0);

        Integer parentId0 = Values.getResultSetValue(rs, 3, Integer.class);
        item.setParentId(parentId0);

        Short orderNo0 = Values.getResultSetValue(rs, 4, Short.class);
        item.setOrderNo(orderNo0);

        String codeName0 = Values.getResultSetValue(rs, 5, String.class);
        item.setCodeName(codeName0);

        String pageUrl0 = Values.getResultSetValue(rs, 6, String.class);
        item.setPageUrl(pageUrl0);

        Date createTime0 = Values.getResultSetValue(rs, 7, Date.class);
        item.setCreateTime(createTime0);

        Date updateTime0 = Values.getResultSetValue(rs, 8, Date.class);
        item.setUpdateTime(updateTime0);

        Short statusId0 = Values.getResultSetValue(rs, 9, Short.class);
        item.setStatusId(statusId0);

        Byte wxSync0 = Values.getResultSetValue(rs, 10, Byte.class);
        item.setWxSync(wxSync0);

        Byte wxBind0 = Values.getResultSetValue(rs, 11, Byte.class);
        item.setWxBind(wxBind0);

        
        return item;
    }

    @Override
    protected void setInsertStatementValues(PreparedStatement ps, WxMenu item) throws SQLException {

        Integer id = item.getId();
        ps.setInt(1, null == id ? null : id);

        String title = item.getTitle();
        ps.setString(2, null == title ? null : title);

        Integer parentId = item.getParentId();
        ps.setInt(3, null == parentId ? null : parentId);

        Short orderNo = item.getOrderNo();
        ps.setShort(4, null == orderNo ? null : orderNo);

        String codeName = item.getCodeName();
        ps.setString(5, null == codeName ? null : codeName);

        String pageUrl = item.getPageUrl();
        ps.setString(6, null == pageUrl ? null : pageUrl);

        Date createTime = item.getCreateTime();
        ps.setDate(7, null == createTime ? null : new java.sql.Date(createTime.getTime()));

        Date updateTime = item.getUpdateTime();
        ps.setTimestamp(8, null == updateTime ? null : new java.sql.Timestamp(updateTime.getTime()));

        Short statusId = item.getStatusId();
        ps.setShort(9, null == statusId ? null : statusId);

        Byte wxSync = item.getWxSync();
        ps.setByte(10, null == wxSync ? null : wxSync);

        Byte wxBind = item.getWxBind();
        ps.setByte(11, null == wxBind ? null : wxBind);

    }

    @Override
    public List<WxMenu> selectRows(TableContext context, final List<Integer> args, final boolean ascending) throws DataAccessException{
        Preconditions.checkNotNull(args);
        return super.selectRows(context, args.toArray(new Integer[0]), ascending);
    }

    @Override
    @WxMenuTx
    public boolean insert(TableContext context, WxMenu item) throws DataAccessException {
        return super.insert(context, item);
    }

    @Override
    @WxMenuTx
    public boolean insertBatch(TableContext context, List<WxMenu> list) throws DataAccessException {
        return super.insertBatch(context, list);
    }

    @Override
    @WxMenuTx
    public boolean update(TableContext context, WxMenu item) throws DataAccessException {
        Preconditions.checkNotNull(item);

        final StringBuilder s = new StringBuilder(128);
        s.append(UPDATE).append(getTableName(context)).append(SET);

        final List<Object> args = new ArrayList<Object>();

        if (null != item.getTitle()){
            s.append("title=?, ");
            args.add(item.getTitle());
        }
        if (null != item.getParentId()){
            s.append("parentId=?, ");
            args.add(item.getParentId());
        }
        if (null != item.getOrderNo()){
            s.append("orderNo=?, ");
            args.add(item.getOrderNo());
        }
        if (null != item.getCodeName()){
            s.append("codeName=?, ");
            args.add(item.getCodeName());
        }
        if (null != item.getPageUrl()){
            s.append("pageUrl=?, ");
            args.add(item.getPageUrl());
        }
        if (null != item.getCreateTime()){
            s.append("createTime=?, ");
            args.add(item.getCreateTime());
        }
        if (null != item.getUpdateTime()){
            s.append("updateTime=?, ");
            args.add(item.getUpdateTime());
        }
        if (null != item.getStatusId()){
            s.append("statusId=?, ");
            args.add(item.getStatusId());
        }
        if (null != item.getWxSync()){
            s.append("wxSync=?, ");
            args.add(item.getWxSync());
        }
        if (null != item.getWxBind()){
            s.append("wxBind=?, ");
            args.add(item.getWxBind());
        }
 

        if (args.size() == 0){
            logger.warn("Nothing to update. ");
            return false;
        }

        s.setLength(s.length() - 2);
        s.append(WHERE).append(N_pkColumnName).append(S_E_Q);
        args.add(getPkValue(item));

        boolean ret = super.update(s.toString(), args);

        super.afterUpdate(context, item);

        return false;
    }

    @Override
    @WxMenuTx
    public boolean update(String sql, List<Object> args) {
        return super.update(sql, args);
    }

    @Override
    @WxMenuTx
    public boolean update(TableContext context, String values, String where, Object... args) throws DataAccessException {
        return super.update(context, values, where, args);
    }

    @Override
    @WxMenuTx
    public boolean delete(TableContext context, WxMenu item) throws DataAccessException {
        return super.delete(context, item);
    }

    @Override
    @WxMenuTx
    public boolean deleteBy(TableContext context, Integer id) throws DataAccessException {
        return super.deleteBy(context, id);
    }

    @Override
    @WxMenuTx
    public boolean deleteBy(TableContext context, String where, Object... args) throws DataAccessException {
        return super.deleteBy(context, where, args);
    }

    /********分隔线*******/


}
