package com.express.service;

import com.express.mapper.entity.Post;

public interface IPostService extends IPageService<Post>, IBaseService<Post> {
    // 分页获取帖子（附加条件：时间排序，关键字，）

    // 发布帖子

    // 删除帖子（管理员）

}
