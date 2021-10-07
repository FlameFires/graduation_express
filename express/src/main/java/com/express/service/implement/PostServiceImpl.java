package com.express.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.MybatisplusHelper;
import com.express.mapper.IPostMapper;
import com.express.mapper.entity.Post;
import com.express.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements IPostService {

    @Autowired
    private IPostMapper postMapper;

    @Override
    public Post getObject(Integer id) {
        return postMapper.selectById(id);
    }

    @Override
    public Integer delObject(Integer id) {
        return postMapper.deleteById(id);
    }

    @Override
    public Integer updObject(Post model) {
        Post post = new Post();
        post.setId(model.getId());
        return postMapper.update(model, MybatisplusHelper.getWrapperInstance(post));
    }

    @Override
    public Integer addObject(Post model) {
        return postMapper.insert(model);
    }

    @Override
    public List<Post> query(Integer pageNo, Integer pageSize, QueryWrapper queryWrapper) {
        return postMapper.selectPage(MybatisplusHelper.getPageInstance(pageNo, pageSize),
                queryWrapper).getRecords();
    }
}
