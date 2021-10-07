package com.express.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.mapper.INoticeMapper;
import com.express.mapper.entity.Notice;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.sql.Timestamp;

@Api(tags = "公告/活动模块接口")
@RestController
@Validated
@RequestMapping("/api/v1/notice")
public class NoticeController {

    @Autowired
    private INoticeMapper noticeMapper;

    // region 公告 /notice

    // 添加公告
    @AdminPower
    @PostMapping("/add")
    @ApiOperation("添加公告")
    ResultMsg noticeAdd(@RequestBody @Validated Notice model) {
        model.setId(0);
        return ResultMsg.judge(noticeMapper.insert(model), "添加失败");
    }

    @AdminPower
    @PostMapping("/upd")
    @ApiOperation("修改公告")
    ResultMsg noticeUpd(@RequestBody @Validated Notice model) {
        var wrapper = new QueryWrapper(Notice.builder().id(model.getId()));
        var entity = noticeMapper.selectOne(wrapper);
        if (entity == null) {
            return ResultMsg.error("修改失败，为获取到此数据");
        }

        entity = model;
        entity.setCreateDate(new Timestamp(System.currentTimeMillis()));
        return ResultMsg.judge(noticeMapper.update(entity, wrapper), "修改成功");
    }

    // 删除公告
    @AdminPower
    @DeleteMapping("/del")
    @ApiOperation("删除公告")
    ResultMsg noticeDel(@RequestParam @Min(value = 1, message = "参数有误") Integer id) {
        return ResultMsg.judge(noticeMapper.deleteById(id), "删除失败");
    }

    // 查看指定公告
    @AdminPower
    @CustomerPower
    @GetMapping("/check")
    @ApiOperation("查看指定公告")
    ResultMsg noticeCheck(@RequestParam @Min(value = 1, message = "参数有误") Integer id) {
        return ResultMsg.judgeGo(noticeMapper.selectById(id), "查看失败");
    }

    // 获取公告
    @AdminPower
    @CustomerPower
    @GetMapping("/query")
    @ApiOperation("获取公告")
    ResultMsg noticeQuery() {
        var wrapper = new QueryWrapper();
        wrapper.orderByDesc("createDate");
        return ResultMsg.judgeGo(noticeMapper.selectList(wrapper), "没有任何数据");
    }

    // endregion
}
