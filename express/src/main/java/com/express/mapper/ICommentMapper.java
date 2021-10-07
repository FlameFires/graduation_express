package com.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.mapper.entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentMapper extends BaseMapper<Comment> {
}
