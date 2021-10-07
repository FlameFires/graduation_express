package com.express.service.implement;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.service.IBaseService;

public class BaseServerImpl<T> implements IBaseService<T> {


    BaseMapper baseMapper;

    BaseServerImpl(BaseMapper mapper) {
        this.baseMapper = mapper;
    }

    @Override
    public T getObject(Integer id) {
        return (T) baseMapper.selectById(id);
    }

    @Override
    public Integer delObject(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public Integer updObject(T model) {
        return baseMapper.updateById(model);
    }

    @Override
    public Integer addObject(T model) {
        return baseMapper.insert(model);
    }
}
