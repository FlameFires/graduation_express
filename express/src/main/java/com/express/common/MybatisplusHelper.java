package com.express.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class MybatisplusHelper {
    public static QueryWrapper getWrapperInstance(Object obj) {
        return new QueryWrapper(obj);
    }

    public static Page getPageInstance(Integer current, Integer size) {
        return new Page(current, size);
    }
}
