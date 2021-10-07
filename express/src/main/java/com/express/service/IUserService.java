package com.express.service;

import com.express.mapper.entity.AdminUser;
import com.express.mapper.entity.Courier;
import com.express.mapper.entity.Customer;
import com.express.model.UserType;

import java.util.List;

public interface IUserService {

    /**
     * 登录
     *
     * @param name
     * @param pwd
     * @return
     */
    Object login(String name, String pwd, UserType userType);

    /**
     * 注册账号
     *
     * @param name
     * @param pwd
     * @param userType
     * @return
     */
    Integer register(String name, String pwd, UserType userType);

    /**
     * 判断账号是否存在
     *
     * @param name
     * @param userType
     * @return
     */
    boolean exist(String name, UserType userType);

    /**
     * 判断密码是否一样
     *
     * @param id
     * @param pwd
     * @param userType
     * @return
     */
    boolean isPwdEqual(Integer id, String pwd, UserType userType);

    /**
     * 修改密码
     *
     * @param id
     * @param pwd
     * @param userType
     * @return
     */
    Integer updatePwd(Integer id, String pwd, UserType userType);

    /**
     * 分页获取数据
     *
     * @param pageNo
     * @param pageSize
     * @param key
     * @param userType
     * @return
     */
    List<?> selectByPage(Integer pageNo, Integer pageSize, String key, UserType userType);

    /**
     * 管理员账号登录失败添加次数
     * @param name
     * @return
     */
//    Integer adminLoginErrorNumAddOne(String name);

    /**
     * 管理员登录后记录最后登录时间
     *
     * @param id
     * @return
     */
//    Integer adminLoginLastDate(Integer id);

    Integer update(UserType type, Object data);

    AdminUser getAdminUser(Integer id);

    Courier getCourier(Integer id);

    Customer getCustomer(Integer id);

    boolean isCourier(Integer id);

    boolean isCoustomer(Integer id);

    boolean isAdmin(Integer id);
}
