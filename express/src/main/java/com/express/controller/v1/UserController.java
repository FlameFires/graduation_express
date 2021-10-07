package com.express.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.SessionHelper;
import com.express.mapper.IAdminUserMapper;
import com.express.mapper.ICourierMapper;
import com.express.mapper.ICustomerMapper;
import com.express.mapper.dto.CourierDto;
import com.express.mapper.dto.CustomerDto;
import com.express.mapper.dto.UserDTO;
import com.express.mapper.entity.AdminUser;
import com.express.mapper.entity.BaseEntity;
import com.express.mapper.entity.Courier;
import com.express.mapper.entity.Customer;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.AfterLogin;
import com.express.mapper.power.CourierPower;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import com.express.model.ServerUserInfo;
import com.express.model.UserType;
import com.express.model.VerifyCode;
import com.express.service.IUserService;
import com.express.service.IVerifyCodeGen;
import com.express.service.implement.SimpleCharVerifyCodeGenImpl;
import com.express.sms.yun.SmsSingleSender;
import com.express.sms.yun.SmsSingleSenderResult;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.util.List;

@Api(tags = "用户模块接口")
@RestController
@Validated
@RequestMapping("/api/v1/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ICustomerMapper customerMapper;
    @Autowired
    private ICourierMapper ICourierMapper;
    @Autowired
    private IAdminUserMapper IAdminUserMapper;
    @Autowired
    private IUserService userService;

    // {t} c 用户,a 管理员, d 送单员
//     switch (t){
//        case "c":
//            break;
//        case "d":
//            break;
//        case "a":
//            break;
//        default:
//            return ResultMsg.error("错误请求");
//    }

//    paramType：表示参数放在哪个地方
//        header-->请求参数的获取：@RequestHeader(代码中接收注解)
//        query-->请求参数的获取：@RequestParam(代码中接收注解)
//        path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
//        body-->请求参数的获取：@RequestBody(代码中接收注解)
//        form（不常用）

    /**
     * 登录
     *
     * @param t
     * @param name
     * @param pwd
     * @param request
     * @return
     */
    @GetMapping("/{t}/login")
    @ApiOperation("/登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "查询类型，c ：用户；d ：送单员", paramType = "path", required = true, examples = @Example({@ExampleProperty(value = "c"), @ExampleProperty(value = "d")})),
            @ApiImplicitParam(name = "name", value = "账号", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    ResultMsg login(@PathVariable("t") @NotNull(message = "类型不能为空") String t,
                    @RequestParam @NotNull String name,
                    @RequestParam @NotNull String pwd,
                    HttpServletRequest request) {

        ServerUserInfo serverUserInfo;
        UserType userType;
        switch (t.strip()) {
            case "c":
                userType = UserType.Customer;
                break;
            case "d":
                userType = UserType.Courier;
                break;
            case "a":
                userType = UserType.AdminUser;
                break;
            default:
                return ResultMsg.error("参数错误");
        }
        if (!userService.exist(name, userType)) {
            return ResultMsg.error("账号不存在");
        } else {
            var obj = userService.login(name, pwd, userType);
            if (obj == null) {
                return ResultMsg.error("账号或密码错误");
            }
            serverUserInfo = ServerUserInfo.builder().userType(userType).id(((BaseEntity) obj).getId()).userObject(obj).build();
            SessionHelper.setLoginUserInfo(request, serverUserInfo);
            return ResultMsg.ok("登录成功");
        }
    }

    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            log.info(code);
            //将VerifyCode绑定session
            SessionHelper.setSessionByName(request, "VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            log.info("", e);
        }
    }

    // 发送短信 https://www.kewail.com/console/accessKey
    @ApiOperation(value = "发送短信")
    @GetMapping("/sms")
    ResultMsg sendSms(String code, String phone, HttpServletRequest request, HttpServletResponse response) {

        var ocode = SessionHelper.getSessionByName(request, "VerifyCode");
        if (ocode.equals(code)) {
            try {
                //请根据实际 accesskey 和 secretkey 进行开发，以下只作为演示 sdk 使用
                String accesskey = "603e03823ba82412b26a644c";
                String secretkey = "406310c3e4b141769ad208aebbfb6785";
                //手机号码
                String phoneNumber = phone;
                //初始化单发
                SmsSingleSender singleSender = new SmsSingleSender(accesskey, secretkey);
                SmsSingleSenderResult singleSenderResult;

                IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
                VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
                String ycode = verifyCode.getCode();

                singleSenderResult = singleSender.send(0, "86", phoneNumber, "【速递】尊敬的用户：您的验证码：" + ycode + "。", "", "");
                System.out.println(singleSenderResult);
                if (singleSenderResult.result == 0) {
                    return ResultMsg.ok();
                } else {
                    return ResultMsg.error(singleSenderResult.errMsg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResultMsg.error("发送失败");
        } else {
            return ResultMsg.error("验证码错误");
        }
    }

    /**
     * 注册
     *
     * @param t
     * @param phone
     * @param pwd
     * @return
     */
    @GetMapping("/{t}/regist")
    @ApiOperation("注册账号")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "t", value = "查询类型，c ：用户；d ：送单员", paramType = "path", required = true, examples = @Example({@ExampleProperty(value = "c"), @ExampleProperty(value = "d")})),
//            @ApiImplicitParam(name = "code", value = "验证码", required = true, paramType = "body", dataType = "String"),
//            @ApiImplicitParam(name = "phone", value = "手机号", required = true, paramType = "body", dataType = "String"),
//            @ApiImplicitParam(name = "pwd", value = "密码", required = true, paramType = "body", dataType = "String")
//    })
    ResultMsg regist(
            @PathVariable("t") @Pattern(regexp = "^(d|c)$", message = "参数有误") String t,
            @RequestParam @NotNull(message = "参数有误") String code,
            @RequestParam @NotNull(message = "参数有误") String phone,
            @RequestParam @NotNull(message = "参数有误") String pwd,
            HttpServletRequest request) {

        var ocode = SessionHelper.getSessionByName(request, "VerifyCode");
        if (ocode == null || !ocode.toString().equalsIgnoreCase(code)) {
            return ResultMsg.error("验证码错误");
        }

        switch (t) {
            case "c":
                QueryWrapper<Customer> paras1 = new QueryWrapper<Customer>(Customer.builder().phoneNum(phone).build());
                var customers = customerMapper.selectList(paras1);
                if (customers.size() > 0) {
                    return ResultMsg.ok("账号已存在");
                } else {
                    Customer customer = Customer.builder().userName(phone).phoneNum(phone).password(pwd).build();
                    customer.setId(0);
                    int id = customerMapper.insert(customer);
                    return ResultMsg.judge(id, "注册失败！");
                }
            case "d":
                QueryWrapper<Courier> paras2 = new QueryWrapper<Courier>(Courier.builder().phoneNum(phone).build());
                var couriers = ICourierMapper.selectList(paras2);
                if (couriers.size() > 0) {
                    return ResultMsg.ok("账号已存在");
                } else {
                    Courier courier = Courier.builder().userName(phone).phoneNum(phone).password(pwd).build();
                    courier.setId(0);
                    int id = ICourierMapper.insert(courier);
                    return ResultMsg.judge(id, "注册失败！");
                }
            default:
                return ResultMsg.error("错误请求");
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @AfterLogin
    @GetMapping("/exit")
    @ApiOperation("退出登录")
    ResultMsg exit(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
        return ResultMsg.ok("退出登录成功");
    }

    /**
     * 获取用户列表
     *
     * @param t
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/{t}/list")
    @Valid
    @AdminPower
    @ApiOperation(value = "分页获取人员数据", notes = "可以获取用户，管理员的人员数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "查询类型，c ：用户；d ：送单员", paramType = "path", required = true, examples = @Example({@ExampleProperty(value = "c"), @ExampleProperty(value = "d")})),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页获取数量", paramType = "query", defaultValue = "10")})
    ResultMsg getList(@PathVariable("t") @NotNull(message = "类型不能为空") String t,
                      @RequestParam(defaultValue = "1") @NotNull @Min(1) Integer pageNo,
                      @RequestParam(defaultValue = "10") @NotNull @Min(1) Integer pageSize) {
        System.out.println("-------------------------------------------- 开始处理 -----------------------------------------------");
        List<?> list;
        UserType userType = null;
        if (t.equals("C")) {
            userType = UserType.Customer;
        } else if (t.equals("d")) {
            userType = UserType.Courier;
        } else
            return ResultMsg.error("错误请求");

        list = userService.selectByPage(pageNo, pageSize, null, userType);
        return ResultMsg.judge(list, "获取数据失败");

    }

    @AfterLogin
    @GetMapping("/selfInfo")
    @ApiOperation(value = "获取用户信息")
    ResultMsg getSelfInfo(HttpServletRequest request) {
        var uobj = SessionHelper.getLoginUserInfo(request);
        if (uobj.getUserType() == UserType.Customer) {
            var u = userService.getCustomer(uobj.getId());
            CustomerDto info = CustomerDto.builder()
                    .id(u.getId())
                    .name(u.getUserName())
                    .phone(u.getPhoneNum())
                    .address(u.getAddress())
                    .gender(u.getGender())
                    .build();
            return ResultMsg.ok(info);
        }
        if (uobj.getUserType() == UserType.Courier) {
            var u = userService.getCourier(uobj.getId());
            CustomerDto info = CustomerDto.builder()
                    .id(u.getId())
                    .name(u.getUserName())
                    .phone(u.getPhoneNum())
                    .gender(u.getGender())
                    .build();
            return ResultMsg.ok(info);
        }
        if (uobj.getUserType() == UserType.AdminUser) {
            var u = userService.getAdminUser(uobj.getId());
            CustomerDto info = CustomerDto.builder()
                    .id(u.getId())
                    .name(u.getAccount())
                    .build();
            return ResultMsg.ok(info);
        }

        return ResultMsg.error("请求错误！");
    }


    @CustomerPower
    @AdminPower
    @PostMapping("/saveCInfo")
    @ApiOperation(value = "用户保存信息")
    ResultMsg saveCustomerInfo(@RequestBody CustomerDto data, HttpServletRequest request) {
        var user = SessionHelper.getLoginUserInfo(request);
        if (user.getUserType() == UserType.Customer) {
            var c = Customer.builder().userName(data.getName())
                    .gender(data.getGender())
                    .address(data.getAddress())
                    .build();
            c.setId(user.getId());
            var num = userService.update(user.getUserType(), c);
            if(num > 0){
                ServerUserInfo serverUserInfo = ServerUserInfo.builder().userType(user.getUserType()).id(user.getId()).userObject(c).build();
                SessionHelper.setLoginUserInfo(request, serverUserInfo);
            }
            return ResultMsg.judge(num, "保存失败");
        }
//        if (user.getUserType() == UserType.AdminUser) {
//            var c = AdminUser.builder().account(data.getName()).build();
//            c.setId(user.getId());
//            var num = userService.update(user.getUserType(), c);
//            if(num > 0){
//                ServerUserInfo serverUserInfo = ServerUserInfo.builder().userType(user.getUserType()).id(user.getId()).userObject(c).build();
//                SessionHelper.setLoginUserInfo(request, serverUserInfo);
//            }
//            return ResultMsg.judge(num, "保存失败");
//        }
        return ResultMsg.error("请求错误");
    }

    @CourierPower
    @AdminPower
    @PostMapping("/saveDInfo")
    @ApiOperation(value = "送单员保存信息")
    ResultMsg saveCourierInfo(@RequestBody CourierDto data, HttpServletRequest request) {
        var user = SessionHelper.getLoginUserInfo(request);
        if (user.getUserType() == UserType.Courier) {
            var c = Courier.builder().userName(data.getName())
                    .gender(data.getGender())
                    .build();
            c.setId(user.getId());
            var num = userService.update(user.getUserType(), c);
            if(num > 0){
                ServerUserInfo serverUserInfo = ServerUserInfo.builder().userType(user.getUserType()).id(user.getId()).userObject(c).build();
                SessionHelper.setLoginUserInfo(request, serverUserInfo);
            }
            return ResultMsg.judge(num, "保存失败");
        }
        return ResultMsg.error("请求错误");
    }


    // 修改密码
    @Valid
    @AfterLogin
    @GetMapping("/modpwd")
    @ApiOperation(value = "修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "np", value = "新密码", paramType = "query", required = true),
            @ApiImplicitParam(name = "np2", value = "确认新密码", paramType = "query", required = true)})
    ResultMsg updatePwd(HttpServletRequest request,
                        @RequestParam("np") @NotNull @Valid String np,
                        @RequestParam("np2") @NotNull @Valid String np2) {
        boolean isEqual = np.strip().equals(np2.strip());
        var user = SessionHelper.getLoginUserInfo(request);
        if (isEqual) { // 确定密码成功
            if (!userService.isPwdEqual(user.getId(), np, user.getUserType())) {
                return ResultMsg.ok("修改成功");
            } else {
                return ResultMsg.error("不能设置跟以前一样的密码");
            }
        } else {
            return ResultMsg.error("密码不一致");
        }
    }

    // 修改信息
    ResultMsg updateInfo(@PathVariable("t") @NotNull(message = "类型不能为空") String t,
                         @RequestBody @NotNull @Valid UserDTO userDTO) {
        return ResultMsg.ok();
    }

}
