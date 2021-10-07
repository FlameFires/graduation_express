package com.express.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.express.mapper.entity.GoodsType;
import com.express.mapper.entity.MakeMoney;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface IMakeMoneyMapper extends BaseMapper<MakeMoney> {
    @Select(value = "select sum(money) from make_money")
    BigDecimal getTotalMoney();

    @Select(value = "select sum(money) from make_money where date(createDate) = curdate()")
    BigDecimal getTodayMoney();
}
