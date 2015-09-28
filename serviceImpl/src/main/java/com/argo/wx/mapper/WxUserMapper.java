package com.argo.wx.mapper;

import com.argo.db.Values;
import com.argo.db.mysql.TableContext;
import com.argo.db.template.MySqlMapper;
import com.argo.wx.model.WxUser;
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
public class WxUserMapper extends MySqlMapper<WxUser, Integer> {
    
    public static WxUserMapper instance;

    public static final String N_tableName = "wx_user";
    public static final String N_pkColumnName = "id";

    public static final String SQL_FIELDS = "id, openId, openToken, createTime, bindTime, userId, lastAccessTime, statusId, rmBindTime";
    public static final List<String> columnList = new ArrayList<String>();
    public static final boolean pkAutoIncr = true;

    static {
        columnList.add("id");
        columnList.add("openId");
        columnList.add("openToken");
        columnList.add("createTime");
        columnList.add("bindTime");
        columnList.add("userId");
        columnList.add("lastAccessTime");
        columnList.add("statusId");
        columnList.add("rmBindTime");
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
    public Class<WxUser> getRowClass() {
        return WxUser.class;
    }

    @Override
    protected void setPKValue(WxUser item, KeyHolder holder) {
        item.setId(holder.getKey().intValue());
    }

    @Override
    protected Integer getPkValue(WxUser item) {
        return item.getId();
    }

    @Override
    protected boolean isPKAutoIncr() {
        return pkAutoIncr;
    }

    @Override
    protected WxUser mapRow(ResultSet rs, int rowIndex) throws SQLException {
        WxUser item = new WxUser();

        Integer id0 = Values.getResultSetValue(rs, 1, Integer.class);
        item.setId(id0);

        String openId0 = Values.getResultSetValue(rs, 2, String.class);
        item.setOpenId(openId0);

        String openToken0 = Values.getResultSetValue(rs, 3, String.class);
        item.setOpenToken(openToken0);

        Date createTime0 = Values.getResultSetValue(rs, 4, Date.class);
        item.setCreateTime(createTime0);

        Date bindTime0 = Values.getResultSetValue(rs, 5, Date.class);
        item.setBindTime(bindTime0);

        Integer userId0 = Values.getResultSetValue(rs, 6, Integer.class);
        item.setUserId(userId0);

        Date lastAccessTime0 = Values.getResultSetValue(rs, 7, Date.class);
        item.setLastAccessTime(lastAccessTime0);

        Byte statusId0 = Values.getResultSetValue(rs, 8, Byte.class);
        item.setStatusId(statusId0);

        Date rmBindTime0 = Values.getResultSetValue(rs, 9, Date.class);
        item.setRmBindTime(rmBindTime0);

        
        return item;
    }

    @Override
    protected void setInsertStatementValues(PreparedStatement ps, WxUser item) throws SQLException {

        Integer id = item.getId();
        ps.setInt(1, null == id ? null : id);

        String openId = item.getOpenId();
        ps.setString(2, null == openId ? null : openId);

        String openToken = item.getOpenToken();
        ps.setString(3, null == openToken ? null : openToken);

        Date createTime = item.getCreateTime();
        ps.setDate(4, null == createTime ? null : new java.sql.Date(createTime.getTime()));

        Date bindTime = item.getBindTime();
        ps.setDate(5, null == bindTime ? null : new java.sql.Date(bindTime.getTime()));

        Integer userId = item.getUserId();
        ps.setInt(6, null == userId ? null : userId);

        Date lastAccessTime = item.getLastAccessTime();
        ps.setDate(7, null == lastAccessTime ? null : new java.sql.Date(lastAccessTime.getTime()));

        Byte statusId = item.getStatusId();
        ps.setByte(8, null == statusId ? null : statusId);

        Date rmBindTime = item.getRmBindTime();
        ps.setDate(9, null == rmBindTime ? null : new java.sql.Date(rmBindTime.getTime()));

    }

    @Override
    public List<WxUser> selectRows(TableContext context, final List<Integer> args, final boolean ascending) throws DataAccessException{
        Preconditions.checkNotNull(args);
        return super.selectRows(context, args.toArray(new Integer[0]), ascending);
    }

    @Override
    @WxUserTx
    public boolean insert(TableContext context, WxUser item) throws DataAccessException {
        return super.insert(context, item);
    }

    @Override
    @WxUserTx
    public boolean insertBatch(TableContext context, List<WxUser> list) throws DataAccessException {
        return super.insertBatch(context, list);
    }

    @Override
    @WxUserTx
    public boolean update(TableContext context, WxUser item) throws DataAccessException {
        Preconditions.checkNotNull(item);

        final StringBuilder s = new StringBuilder(128);
        s.append(UPDATE).append(getTableName(context)).append(SET);

        final List<Object> args = new ArrayList<Object>();

        if (null != item.getOpenId()){
            s.append("openId=?, ");
            args.add(item.getOpenId());
        }
        if (null != item.getOpenToken()){
            s.append("openToken=?, ");
            args.add(item.getOpenToken());
        }
        if (null != item.getCreateTime()){
            s.append("createTime=?, ");
            args.add(item.getCreateTime());
        }
        if (null != item.getBindTime()){
            s.append("bindTime=?, ");
            args.add(item.getBindTime());
        }
        if (null != item.getUserId()){
            s.append("userId=?, ");
            args.add(item.getUserId());
        }
        if (null != item.getLastAccessTime()){
            s.append("lastAccessTime=?, ");
            args.add(item.getLastAccessTime());
        }
        if (null != item.getStatusId()){
            s.append("statusId=?, ");
            args.add(item.getStatusId());
        }
        if (null != item.getRmBindTime()){
            s.append("rmBindTime=?, ");
            args.add(item.getRmBindTime());
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
    @WxUserTx
    public boolean update(String sql, List<Object> args) {
        return super.update(sql, args);
    }

    @Override
    @WxUserTx
    public boolean update(TableContext context, String values, String where, Object... args) throws DataAccessException {
        return super.update(context, values, where, args);
    }

    @Override
    @WxUserTx
    public boolean delete(TableContext context, WxUser item) throws DataAccessException {
        return super.delete(context, item);
    }

    @Override
    @WxUserTx
    public boolean deleteBy(TableContext context, Integer id) throws DataAccessException {
        return super.deleteBy(context, id);
    }

    @Override
    @WxUserTx
    public boolean deleteBy(TableContext context, String where, Object... args) throws DataAccessException {
        return super.deleteBy(context, where, args);
    }

    /********分隔线*******/


}
