package com.express.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.SessionHelper;
import com.express.mapper.ICommentMapper;
import com.express.mapper.entity.Comment;
import com.express.mapper.entity.Customer;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.AfterLogin;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Api(tags = "评论模块接口")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private ICommentMapper commentMapper;

    // region 评论系统

    // 查看评论 (一级)
    @AfterLogin
    @GetMapping("/query")
    @ApiOperation("查看评论")
    ResultMsg commentQuery() {
        var wrapper = new QueryWrapper<Comment>();
        wrapper.orderByDesc("createDate");
        var list =commentMapper.selectList(wrapper);
        return ResultMsg.judgeGo(list,"还没有任何评论呢");
    }

    // 发表评论
    @CustomerPower
    @PostMapping("/put")
    @ApiOperation("发表评论")
    ResultMsg commentPut(@RequestBody @NotBlank(message = "不能为空") @Valid String msg,
                         HttpServletRequest request) {
        var user = SessionHelper.getLoginUserInfo(request);
        var userObj = (Customer)user.getUserObject();

        Comment comment = Comment.builder().name(userObj.getUserName()).content(msg).customerId(user.getId()).build();
        comment.setId(0);
        var num = commentMapper.insert(comment);
        return ResultMsg.judge(num,"评论成功");
    }

    // 删除评论（管理员）
    @AdminPower
    @DeleteMapping("/del")
    @ApiOperation("填写寄件信息")
    ResultMsg commentDel(@RequestParam("id") @NotNull @Valid Integer id) {
        var model = commentMapper.selectById(id);
        if(model != null){
            var num = commentMapper.deleteById(id);
            return ResultMsg.judge(num,"删除失败");
        }
        return ResultMsg.error("不存在这条评论");
    }

    // endregion
}
