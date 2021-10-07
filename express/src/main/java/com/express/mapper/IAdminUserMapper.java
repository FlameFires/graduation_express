package com.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.mapper.entity.AdminUser;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminUserMapper extends BaseMapper<AdminUser> {
}
