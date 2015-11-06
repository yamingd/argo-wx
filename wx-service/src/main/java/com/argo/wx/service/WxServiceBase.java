package com.argo.wx.service;

import com.argo.db.exception.EntityNotFoundException;
import com.argo.security.UserIdentity;
import com.argo.service.ServiceException;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yamingd on 9/20/15.
 */
public interface WxServiceBase<T extends Serializable, PK extends Comparable> {
    /**
     * @param user 当前用户
     * @param id 主键
     * @return T 目标实体
     * @throws EntityNotFoundException EntityNotFoundException
     */
    T find(UserIdentity user, PK id) throws EntityNotFoundException;

    /**
     * @param user 当前用户
     * @param ids 主键
     * @return List
     */
    List<T> findList(UserIdentity user, PK... ids);

    /**
     * 新建记录
     * @param user 当前用户
     * @param item 目标实体
     * @return T 目标实体
     * @throws ServiceException ServiceException
     */
    T create(UserIdentity user, T item) throws ServiceException;

    /**
     * 保存更新
     * @param user 当前用户
     * @param item 目标实体
     * @return T 目标实体
     * @throws ServiceException ServiceException
     */
    T save(UserIdentity user, T item) throws ServiceException;

    /**
     * 删除记录
     * @param user 当前用户
     * @param id 用户标示
     * @return boolean 是否成功删除
     * @throws ServiceException ServiceException
     */
    boolean removeBy(UserIdentity user, PK id) throws ServiceException;

    /**
     * 删除记录
     * @param user 当前用户
     * @param item 目标实体
     * @return boolean 是否删除成功
     * @throws ServiceException ServiceException
     */
    boolean remove(UserIdentity user, T item) throws ServiceException;
}
