package com.express.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.express.common.MybatisplusHelper;
import com.express.mapper.IAdminUserMapper;
import com.express.mapper.ICourierMapper;
import com.express.mapper.ICustomerMapper;
import com.express.mapper.entity.AdminUser;
import com.express.mapper.entity.Courier;
import com.express.mapper.entity.Customer;
import com.express.model.UserType;
import com.express.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private ICourierMapper ICourierMapper;
    @Autowired
    private IAdminUserMapper IAdminUserMapper;

    @Override
    public Object login(String name, String pwd, UserType userType) {
        QueryWrapper queryWrapper;
        switch (userType) {
            case Customer:
                queryWrapper = new QueryWrapper(Customer.builder().phoneNum(name).password(pwd).build());
                return customerMapper.selectOne(queryWrapper);
            case Courier:
                queryWrapper = new QueryWrapper(Courier.builder().phoneNum(name).password(pwd).build());
                return ICourierMapper.selectOne(queryWrapper);
            case AdminUser:
                queryWrapper = new QueryWrapper(AdminUser.builder().account(name).password(pwd).build());
                return IAdminUserMapper.selectOne(queryWrapper);
        }
        return null;
    }

    @Override
    public Integer register(String name, String pwd, UserType userType) {
        Integer num = 0;
        switch (userType) {
            case Customer:
                var mod = Customer.builder().userName(name).password(pwd).build();
                mod.setId(0);
                num = customerMapper.insert(mod);
                break;
            case Courier:
                var mod2 = Courier.builder().userName(name).password(pwd).build();
                mod2.setId(0);
                num = ICourierMapper.insert(mod2);
                break;
            case AdminUser:
                var mod3 = AdminUser.builder().account(name).password(pwd).build();
                mod3.setId(0);
                num = IAdminUserMapper.insert(mod3);
                break;
        }
        return num;
    }

    @Override
    public Integer update(UserType type, Object data){
        if (type == UserType.Customer) {
            var u = (Customer) data;
            return customerMapper.updateById(u);
        }
        if (type == UserType.Courier) {
            var u = (Courier) data;
            return ICourierMapper.updateById(u);
        }
        if (type == UserType.AdminUser) {
            var u = (AdminUser) data;
            return IAdminUserMapper.updateById(u);
        }
        return 0;
    }

    @Override
    public AdminUser getAdminUser(Integer id) {
        return IAdminUserMapper.selectById(id);
    }

    @Override
    public Courier getCourier(Integer id) {
        return ICourierMapper.selectById(id);
    }

    @Override
    public Customer getCustomer(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public boolean exist(String name, UserType userType) {
        boolean isExist = false;
        QueryWrapper queryWrapper;
        switch (userType) {
            case Customer:
                queryWrapper = new QueryWrapper(Customer.builder().phoneNum(name).build());
                isExist = customerMapper.selectOne(queryWrapper) != null;
                break;
            case Courier:
                queryWrapper = new QueryWrapper(Courier.builder().phoneNum(name) .build());
                isExist = ICourierMapper.selectOne(queryWrapper) != null;
                break;
            case AdminUser:
                queryWrapper = new QueryWrapper(AdminUser.builder().account(name).build());
                isExist = IAdminUserMapper.selectOne(queryWrapper) != null;
                break;
        }
        return isExist;
    }

    @Override
    public boolean isPwdEqual(Integer id, String pwd, UserType userType) {
        boolean r = false;
        QueryWrapper wrapper;
        if (userType == UserType.Customer) {
            var customer = Customer.builder().password(pwd).build();
            customer.setId(id);
            wrapper = new QueryWrapper(customer);
            r = customerMapper.selectOne(wrapper) != null;
        }
        if (userType == UserType.Courier) {
            var courier = Courier.builder().password(pwd).build();
            courier.setId(id);
            wrapper = new QueryWrapper(courier);
            r = ICourierMapper.selectOne(wrapper) != null;
        }
        if (userType == UserType.AdminUser) {
            var adminUser = AdminUser.builder().password(pwd).build();
            adminUser.setId(id);
            wrapper = new QueryWrapper(adminUser);
            r = IAdminUserMapper.selectOne(wrapper) != null;
        }
        return r;
    }

    @Override
    public Integer updatePwd(Integer id, String pwd, UserType userType) {
        Integer num = 0;
        if (userType == UserType.Customer) {
            var customer = Customer.builder().password(pwd).build();
            customer.setId(id);
            num = customerMapper.updateById(customer);
        }
        if (userType == UserType.Courier) {
            var courier = Courier.builder().password(pwd).build();
            courier.setId(id);
            num = ICourierMapper.updateById(courier);
        }
        if (userType == UserType.AdminUser) {
            var adminUser = AdminUser.builder().password(pwd).build();
            adminUser.setId(id);
            num = IAdminUserMapper.updateById(adminUser);
        }
        return num;
    }

    @Override
    public List<?> selectByPage(Integer pageNo, Integer pageSize, String key, UserType userType) {
        Page page;
        List<?> list = null;
        switch (userType) {
            case Customer:
                page = customerMapper.selectPage(MybatisplusHelper.getPageInstance(pageNo, pageSize),
                        MybatisplusHelper.getWrapperInstance(Customer.builder().userName(key)));
                list = page.getRecords();
                break;
            case Courier:
                page = ICourierMapper.selectPage(MybatisplusHelper.getPageInstance(pageNo, pageSize),
                        MybatisplusHelper.getWrapperInstance(Courier.builder().userName(key)));
                list = page.getRecords();
                break;
            case AdminUser:
                page = IAdminUserMapper.selectPage(MybatisplusHelper.getPageInstance(pageNo, pageSize),
                        MybatisplusHelper.getWrapperInstance(AdminUser.builder().account(key)));
                list = page.getRecords();
                break;
        }
        return list;
    }

    @Override
    public boolean isCourier(Integer id) {
        Courier courier = new Courier();
        courier.setId(id);
        QueryWrapper<Courier> courierQueryWrapper = new QueryWrapper<>(courier);
        var val = ICourierMapper.selectOne(courierQueryWrapper);
        return val != null;
    }

    @Override
    public boolean isCoustomer(Integer id) {
        var customer = new Customer();
        customer.setId(id);
        var queryWrapper = new QueryWrapper<Customer>(customer);
        var val = customerMapper.selectOne(queryWrapper);
        return val != null;
    }

    @Override
    public boolean isAdmin(Integer id) {
        var adminUser = new AdminUser();
        adminUser.setId(id);
        var queryWrapper = new QueryWrapper<AdminUser>(adminUser);
        var val = IAdminUserMapper.selectOne(queryWrapper);
        return val != null;
    }
}
