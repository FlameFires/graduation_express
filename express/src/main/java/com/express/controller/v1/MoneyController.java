package com.express.controller.v1;

import com.express.mapper.IMakeMoneyMapper;
import com.express.mapper.dto.MoneyDto;
import com.express.mapper.entity.Notice;
import com.express.mapper.power.AdminPower;
import com.express.model.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "金额模块接口")
@RestController
@Validated
@RequestMapping("/api/v1/money")
public class MoneyController{

    @Autowired
    IMakeMoneyMapper makeMoneyMapper;

    // 添加公告
    @AdminPower
    @GetMapping("/query")
    @ApiOperation("获取赚取金额")
    ResultMsg moneyQuery() {
        var today = makeMoneyMapper.getTodayMoney();
        var total = makeMoneyMapper.getTotalMoney();
        return ResultMsg.judgeGo(MoneyDto.builder().today(today).total(total).build(), "获取失败");
    }
}
