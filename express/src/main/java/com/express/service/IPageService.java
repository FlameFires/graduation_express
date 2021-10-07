package com.express.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface IPageService<T> {
    List<T> query(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper);
}
