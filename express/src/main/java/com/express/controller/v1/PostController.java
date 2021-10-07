package com.express.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.SessionHelper;
import com.express.mapper.IAdminUserMapper;
import com.express.mapper.ICustomerMapper;
import com.express.mapper.IPostMapper;
import com.express.mapper.dto.PostDTO;
import com.express.mapper.entity.Post;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;

@Api(tags = "社区模块接口")
@RestController
@Validated
@RequestMapping("/api/v1/post")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private IAdminUserMapper adminUserMapper;
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private IPostMapper postMapper;


    // 查询社区： 分页查询 + 条件（模糊匹配，）
    @CustomerPower
    @AdminPower
    @GetMapping("/query")
    @ApiOperation("查询社区")
    ResultMsg postQuery(
            @RequestParam(value = "k", required = false) String key) {

        QueryWrapper wrapper = null;

        if (key != null && key.length() > 0) {
            wrapper = new QueryWrapper();
            wrapper.like("title", key);
        }

        var list = postMapper.selectList(wrapper);
        return ResultMsg.judgeGo(list, "没有获取任何数据");
    }

    // 查看帖子
    @AdminPower
    @CustomerPower
    @GetMapping("/check")
    @ApiOperation("查看帖子")
    ResultMsg postQuery(@RequestParam @Min(value = 1, message = "参数有误") Integer id) {
        var obj = postMapper.selectById(id);
        return ResultMsg.judgeGo(obj, "查看帖子势必");
    }

    // 发布帖子
    @AdminPower
    @CustomerPower
    @PostMapping("/add")
    @ApiOperation("发布帖子")
    ResultMsg postPut(@RequestBody @Validated PostDTO model,
                      HttpServletRequest request) {
        var user = SessionHelper.getLoginUserInfo(request);
        var mod = Post.builder().title(model.getTitle()).content(model.getContent()).customerId(user.getId()).build();
        mod.setId(0);
        var num = postMapper.insert(mod);
        return ResultMsg.judgeGo(num, "发布失败");
    }

    // 删除帖子
    @AdminPower
    @CustomerPower
    @DeleteMapping("/del")
    @ApiOperation("删除帖子")
    ResultMsg postDel(@RequestParam @Min(value = 1, message = "参数有误") Integer id) {
        var num = postMapper.deleteById(id);
        return ResultMsg.judgeGo(num, "删除失败");
    }
}
