package com.express.service;

public interface IBaseService<T> {
    /**
     * 获取一个实体
     *
     * @param id
     * @return
     */
    T getObject(Integer id);

    /**
     * 删除实体
     *
     * @param id
     * @return
     */
    Integer delObject(Integer id);

    /**
     * 更新实体
     *
     * @param model
     * @return
     */
    Integer updObject(T model);

    /**
     * 插入一个实体
     *
     * @param model
     * @return
     */
    Integer addObject(T model);
}
