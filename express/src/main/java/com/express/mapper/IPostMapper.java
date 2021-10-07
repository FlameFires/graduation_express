package com.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.mapper.entity.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostMapper extends BaseMapper<Post> {
}
