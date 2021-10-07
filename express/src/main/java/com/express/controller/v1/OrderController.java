package com.express.controller.v1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.express.common.SessionHelper;
import com.express.mapper.*;
import com.express.mapper.constant.GoodsStatus;
import com.express.mapper.constant.OrderStatus;
import com.express.mapper.constant.PayStatus;
import com.express.mapper.dto.*;
import com.express.mapper.entity.*;
import com.express.mapper.power.AdminPower;
import com.express.mapper.power.AfterLogin;
import com.express.mapper.power.CourierPower;
import com.express.mapper.power.CustomerPower;
import com.express.model.ResultMsg;
import com.express.model.UserType;
import com.express.service.ISendOrderService;
import com.express.service.ITakeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Api(tags = "订单模块接口")
@RestController
@Validated
@RequestMapping("/api/v1/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    private final ITakeOrderService takeOrderService;
    private final ISendOrderService sendOrderService;

    private IGoodsTypeMapper goodsTypeMapper;
    private IDeliveryPointMapper deliveryPointMapper;
    private IDeliveryTypeMapper deliveryTypeMapper;
    private final ITakeOrderMapper takeOrderMapper;
    private final ISendOrderMapper sendOrderMapper;
    private IMakeMoneyMapper makeMoneyMapper;

    public OrderController(ITakeOrderService takeOrderService, ISendOrderService sendOrderService, IGoodsTypeMapper goodsTypeMapper, IDeliveryPointMapper deliveryPointMapper, IDeliveryTypeMapper deliveryTypeMapper, ITakeOrderMapper takeOrderMapper, ISendOrderMapper sendOrderMapper, IMakeMoneyMapper makeMoneyMapper) {
        this.takeOrderService = takeOrderService;
        this.sendOrderService = sendOrderService;

        this.goodsTypeMapper = goodsTypeMapper;
        this.deliveryPointMapper = deliveryPointMapper;
        this.deliveryTypeMapper = deliveryTypeMapper;
        this.takeOrderMapper = takeOrderMapper;
        this.sendOrderMapper = sendOrderMapper;
        this.makeMoneyMapper = makeMoneyMapper;
    }

    //region 登陆后
    // 订单查询（分页 + 模糊）
    @AfterLogin
    @GetMapping("/queryAll")
    @ApiOperation("订单查询（分页 + 模糊）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "订单类型 s / t", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", required = true, defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", paramType = "query", required = true, defaultValue = "10")
    })
    ResultMsg orderQuery(
            @RequestParam(name = "t") @Pattern(regexp = "^(s|t)$", message = "参数有误") @NotBlank(message = "参数有误") String type,
            Date date) {

        QueryWrapper wrapper = null;
        if (date != null) {
            wrapper = new QueryWrapper();
            wrapper.eq("createDate", date);
        }

        if (type.equals("s")) { // send
            var list = sendOrderMapper.selectList(wrapper);
            String msg = list == null ? "未获取到任何数据" : "";
            return ResultMsg.ok(msg, list);

        }
        if (type.equals("t")) { // take
            var list = takeOrderMapper.selectList(wrapper);
            String msg = list == null ? "未获取到任何数据" : "";
            return ResultMsg.ok(msg, list);
        }
        return ResultMsg.error("请求参数错误");
    }

    // 获取订单信息
    @AdminPower
    @CustomerPower
    @GetMapping("/orderList")
    @ApiOperation("获取订单信息")
    ResultMsg getOrderList(
            @RequestParam(name = "t") @Pattern(regexp = "^(s|t)$", message = "参数有误") @NotBlank(message = "参数有误") String type,
            HttpServletRequest request) {
        var user = SessionHelper.getLoginUserInfo(request);
        if (type.equals("t")) {
            var mod = new TakeOrder();
            mod.setCustomerId(user.getId());
            var wrapper = new QueryWrapper(mod);
            wrapper.orderByDesc("createDate");
            var list = takeOrderMapper.selectList(wrapper);
            return ResultMsg.judgeGo(list, "没有获取到任何数据");
        }
        if (type.equals("s")) {
            var mod = new SendOrder();
            mod.setCustomerId(user.getId());
            var wrapper = new QueryWrapper(mod);
            wrapper.orderByDesc("createDate");
            var list = sendOrderMapper.selectList(wrapper);
            return ResultMsg.judgeGo(list, "没有获取到任何数据");
        }
        return ResultMsg.error("参数错误");
    }

    // 查看订单
    @AfterLogin
    @GetMapping("/check")
    @ApiOperation("查看订单")
    ResultMsg orderCheck(
            @RequestParam(name = "t") @Pattern(regexp = "^(s|t)$", message = "参数有误") @NotBlank String type,
            @RequestParam @NotNull(message = "参数有误") Integer id) {
        if (type.equals("s")) { // send
            var obj = sendOrderService.getObject(id);
            var nobj = new SendOrderDetailDto();
            BeanUtils.copyProperties(obj, nobj);
            var point = deliveryPointMapper.selectOne(new QueryWrapper(DeliveryPoint.builder().id(obj.getDeliveryPointId()).build()));
            var pointName = point == null ? "未知" : point.getName();
            var dtype = deliveryTypeMapper.selectOne(new QueryWrapper(DeliveryType.builder().id(obj.getDeliveryTypeId()).build()));
            var dtypeName = dtype == null ? "未知" : dtype.getName();
            var goods = goodsTypeMapper.selectOne(new QueryWrapper(GoodsType.builder().id(obj.getGoodsTypeId()).build()));
            var goodsName = goods == null ? "其他" : goods.getName();
            nobj.setDPointName(pointName);
            nobj.setDTypeName(dtypeName);
            nobj.setGoodsName(goodsName);
            return ResultMsg.judgeGo(nobj, "获取失败");
        }
        if (type.equals("t")) { // take
            var obj = takeOrderService.getObject(id);
            var nobj = new TakeOrderDetailDto();
            BeanUtils.copyProperties(obj, nobj);
            var point = deliveryPointMapper.selectOne(new QueryWrapper(DeliveryPoint.builder().id(obj.getDeliveryPointId()).build()));
            var pointName = point == null ? "未知" : point.getName();
            var dtype = deliveryTypeMapper.selectOne(new QueryWrapper(DeliveryType.builder().id(obj.getDeliveryTypeId()).build()));
            var dtypeName = dtype == null ? "未知" : dtype.getName();
            nobj.setPointName(pointName);
            nobj.setDTypeName(dtypeName);
            return ResultMsg.judgeGo(nobj, "获取失败");
        }
        return ResultMsg.error("请求参数错误");
    }

    // 获取标签
    @AfterLogin
    @GetMapping("/tags")
    @ApiOperation("获取标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "标签类型 tt / dt / dp", required = true, paramType = "query", type = "Integer")
    })
    ResultMsg getAction(
            @RequestParam(required = true) @Pattern(regexp = "^(dt|tt|dp)$", message = "参数有误") String t) {
        return ResultMsg.judgeGo(sendOrderService.getList(t), "没有获取到任何数据");
    }

    @AfterLogin
    @GetMapping("/taglist")
    @ApiOperation("获取标签信息")
    ResultMsg getAction() {
        var listData = TagList.builder()
                .dpoints(sendOrderService.getList("dp"))
                .dtypes(sendOrderService.getList("dt"))
                .times(sendOrderService.getList("tt"))
                .build();
        return ResultMsg.ok(listData);
    }
    //endregion

    //region 用户
    // 取消订单（用户）
    @CustomerPower
    @GetMapping("/cancel")
    @ApiOperation("取消订单（用户）")
    ResultMsg orderCancel(
            @RequestParam(name = "t") @Pattern(regexp = "^(s|t)$", message = "参数有误") @NotBlank String type,
            @RequestParam @Min(value = 1, message = "参数有误") Integer id) {


        if (type.equals("s")) { // send
            return ResultMsg.judge(sendOrderService.cancelOrderById(id), "取消订单失败");
        }
        if (type.equals("t")) { //
            return ResultMsg.judge(takeOrderService.cancelOrderById(id), "取消订单失败");
        }
        return ResultMsg.error("请求参数错误");
    }

    // 添加取货订单
    @CustomerPower
    @PostMapping("/t_add")
    @ApiOperation("添加取件订单（用户）")
    ResultMsg takeOrderAdd(
            @Validated @RequestBody TakeOrderDto order,
            HttpServletRequest request) {
        if (order.getDpointid() == null || order.getDtypeid() == null) {
            return ResultMsg.error("参数有误");
        }


        var user = SessionHelper.getLoginUserInfo(request);
        var customer = (Customer) user.getUserObject();

        var ord = TakeOrder.builder()
                .customerId(user.getId())
                .takerName(order.getName())
                .phoneNum(order.getPhone())
                .address(order.getAddress())
                .takeCode(order.getTcode())
                .orderStatus(OrderStatus.Ongoing.getVal())
                .payStatus(PayStatus.Ok.getVal())
                .goodsStatus(GoodsStatus.Ongoing.getVal())
                .deliveryTypeId(order.getDtypeid())
                .deliveryPointId(order.getDpointid())
                .build();
        ord.setId(0);
        var num = takeOrderService.addObject(ord);
        if(num > 0){
            var m = MakeMoney.builder().id(0).money(new BigDecimal(2)).customerId(user.getId()).build();
            makeMoneyMapper.insert(m);
        }
        return ResultMsg.judge(num, "下单失败");
    }

    // 添加寄件订单
    @CustomerPower
    @PostMapping("/s_add")
    @ApiOperation("添加寄件订单（用户）")
    ResultMsg sendOrderAdd(
            @RequestBody @Validated SendOrderDto order,
            HttpServletRequest request) {
        if (order.getDpointid() == null || order.getDtypeid() == null) {
            return ResultMsg.error("参数有误");
        }

        var user = SessionHelper.getLoginUserInfo(request);
        var customer = (Customer) user.getUserObject();
        var ord = SendOrder.builder()
                .customerId(user.getId())
                .receiverName(order.getRname())
                .senderName(order.getSname())
                .receiverPhoneNum(order.getRphone())
                .senderPhoneNum(order.getSphone())
                .takeAddress(order.getTaddress())
                .sendAddress(order.getSaddress())
                .orderStatus(OrderStatus.Ongoing.getVal())
                .payStatus(PayStatus.Ok.getVal())
                .goodsStatus(GoodsStatus.Ongoing.getVal())
                .goodsTypeId(order.getDtypeid())
                .takeTimes(order.getTdate())
                .deliveryTypeId(order.getDtypeid())
                .deliveryPointId(order.getDpointid())
                .build();
        ord.setId(0);
        var num = sendOrderService.addObject(ord);
        if(num > 0){
            var m = MakeMoney.builder().id(0).money(new BigDecimal(2)).customerId(user.getId()).build();
            makeMoneyMapper.insert(m);
        }
        return ResultMsg.judge(num, "下单失败");
    }


    //endregion

    //region 管理员

    // 管理员获取订单数据
    @AdminPower
    @GetMapping("/aorder")
    ResultMsg getOrder(@RequestParam(name = "t") @NotNull String t,
                       String key,
                       @RequestParam String date) {
        List<?> list = null;
        if (t.equals("t")) {
            list = takeOrderService.getOrderByAdmin(date,key);
        }
        if (t.equals("s")) {
            list = sendOrderService.getOrderByAdmin(date,key);
        }
        return ResultMsg.judgeGo(list, true, "没有获取到任何数据");
    }

    // 删除订单（管理员，用户）
    @AdminPower
    @CustomerPower
    @DeleteMapping("/delOrder")
    @ApiOperation("删除订单（管理员）")
    ResultMsg orderDel(@RequestParam(name = "t") @NotNull String type,
                       @RequestParam @Min(value = 1, message = "参数有误") Integer id,
                       HttpServletRequest request) {
        // TODO 未测试
        var user = SessionHelper.getLoginUserInfo(request);
        if (user.getUserType() == UserType.AdminUser) {
            if (type.equals("s")) { // send
                return ResultMsg.judge(sendOrderService.delObject(id), "删除失败");
            }
            if (type.equals("t")) { // take
                return ResultMsg.judge(takeOrderService.delObject(id), "删除失败");
            }
        } else {
            var customerId = user.getId();
            if (type.equals("s")) { // send
                var wrapper = new QueryWrapper<SendOrder>();
                wrapper.eq("customerId", customerId);
                wrapper.eq("id",id);
                var obj = sendOrderMapper.selectOne(wrapper);
                if(obj == null){
                    return ResultMsg.error("不存此数据");
                }
                var num = sendOrderMapper.deleteById(obj.getId());
                return ResultMsg.judge(num, "删除失败");
            }
            if (type.equals("t")) { // take
                var wrapper = new QueryWrapper<TakeOrder>();
                wrapper.eq("customerId", customerId);
                wrapper.eq("id",id);
                var obj = takeOrderMapper.selectOne(wrapper);
                if(obj == null){
                    return ResultMsg.error("不存此数据");
                }
                var num = takeOrderMapper.deleteById(obj.getId());
                return ResultMsg.judge(num, "删除失败");
            }
        }


        return ResultMsg.error("请求参数错误");
    }

    // 添加标签
    @AdminPower
    @GetMapping("/addTag")
    @ApiOperation("添加标签数据（管理员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "标签类型 tt / dt / dp", required = true, paramType = "query", type = "Integer"),
            @ApiImplicitParam(name = "name", value = "名称", required = true)
    })
    ResultMsg addAction(
            @RequestParam(required = true) @Pattern(regexp = "^(dt|tt|dp)$", message = "参数有误") String t,
            @RequestParam @NotBlank(message = "名称不能为空") String name) {

        if (sendOrderService.isExist(t, name)) {
            return ResultMsg.error("数据已存在");
        }

        Integer num = 0;
        if (t.equals("tt")) {
            var mod = TakeTime.builder().name(name).build();
            mod.setId(0);
            num = sendOrderService.addTakeTime(mod);
        }
        if (t.equals("dt")) {
            var mod = DeliveryType.builder().name(name).build();
            mod.setId(0);
            num = sendOrderService.addDeliveryType(mod);
        }
        if (t.equals("dp")) {
            var mod = DeliveryPoint.builder().name(name).build();
            mod.setId(0);
            num = sendOrderService.addPoint(mod);
        }
        return ResultMsg.judge(num, "参数输入错误");
    }

    // 更新标签
    @AdminPower
    @GetMapping("/updTag")
    @ApiOperation("修改标签数据（管理员）")
    ResultMsg updateAction(
            @RequestParam(required = true) @Pattern(regexp = "^(dt|tt|dp)$", message = "参数有误") String t,
            @RequestParam @Min(value = 1, message = "id输入有误") Integer id,
            @RequestParam @NotBlank(message = "名称不能为空") String name) {
        Integer num = 0;
        if (t.equals("tt")) {
            var mod = TakeTime.builder().name(name).build();
            mod.setId(id);
            num = sendOrderService.updateTakeTime(mod);
        }
        if (t.equals("dt")) {
            var mod = DeliveryType.builder().name(name).build();
            mod.setId(id);
            num = sendOrderService.updateDeliveryType(mod);
        }
        if (t.equals("dp")) {
            var mod = DeliveryPoint.builder().name(name).build();
            mod.setId(id);
            num = sendOrderService.updatePoint(mod);
        }
        return ResultMsg.judge(num, "参数输入错误");
    }

    // 删除标签
    @AdminPower
    @DeleteMapping("/delTag")
    @ApiOperation("删除标签数据（管理员）")
    ResultMsg delAction(
            @RequestParam(required = true) @Pattern(regexp = "^(dt|tt|dp)$", message = "参数有误") String t,
            @RequestParam @Min(value = 1, message = "id输入有误") Integer id) {
        Integer num = 0;
        if (t.equals("tt")) {
            num = sendOrderService.delTakeTime(id);
        }
        if (t.equals("dt")) {
            num = sendOrderService.delDeliveryType(id);
        }
        if (t.equals("dp")) {
            num = sendOrderService.delPoint(id);
        }
        return ResultMsg.judge(num, "参数输入错误");
    }
    //endregion

    //region 接单人
    @CourierPower
    @GetMapping("/accOrder")
    @ApiOperation("接受订单（接单人）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "s 寄件/ t 取件"),
            @ApiImplicitParam(name = "id")
    })
    ResultMsg acceptOrder(@RequestParam @Pattern(regexp = "^(s|t)$", message = "参数有误") String t,
                          @RequestParam(name = "id") @Min(value = 1, message = "参数有误") Integer orderId,
                          HttpServletRequest request) {
        // TODO 报错 NullPointerException
        var user = SessionHelper.getLoginUserInfo(request);
        var num = 0;
        if (t.equals("t")) {
            num = takeOrderService.recOrderById(orderId, user.getId());
        }
        if (t.equals("s")) {
            num = sendOrderService.recOrderById(orderId, user.getId());
        }
        return ResultMsg.judge(num, "接单失败");
    }

    /**
     * 获取订单
     * @param t
     * @param date
     * @param key
     * @param request
     * @return
     */
    @CourierPower
    @AdminPower
    @GetMapping("/recList")
    @ApiOperation("获取订单（接单人）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "t", value = "s 寄件/ t 取件"),
            @ApiImplicitParam(name = "date", value = "日期"),
            @ApiImplicitParam(name = "key", value = "查询值"),
    })
    ResultMsg getRecList(@RequestParam @Pattern(regexp = "^(s|t)$", message = "参数有误") String t,
                         @RequestParam String date,
                         @RequestParam String key,
                         HttpServletRequest request) {
        // TODO 未测试

        var user = SessionHelper.getLoginUserInfo(request);
        List list = null;
        if (t.equals("t")) {
            list = takeOrderService.getCanAcceOrder(user.getId(), key, date);
        }
        if (t.equals("s")) {
            list = sendOrderService.getCanAcceOrder(user.getId(), key, date);
        }
        return ResultMsg.judgeGo(list, "没有获取到任何数据");
    }

    @CourierPower
    @AdminPower
    @GetMapping("/sucOrder")
    @ApiOperation("完成订单（接单人）")
    ResultMsg getRecList(@RequestParam @Pattern(regexp = "^(s|t)$", message = "参数有误") String t,
                         @RequestParam(name = "id") @Min(value = 1, message = "参数有误") Integer orderId,
                         HttpServletRequest request) {
        var num = 0;
        if (t.equals("t")) {
            var w = new QueryWrapper<TakeOrder>().lambda();
            w.eq(TakeOrder::getId, orderId);
            var obj = takeOrderMapper.selectOne(w);
            obj.setOrderStatus(OrderStatus.Completed.getVal());
            obj.setGoodsStatus(GoodsStatus.Taked.getVal());
            num = takeOrderMapper.update(obj, w);
        }
        if (t.equals("s")) {
            var w = new QueryWrapper<SendOrder>().lambda();
            w.eq(SendOrder::getId, orderId);
            var obj = sendOrderMapper.selectOne(w);
            obj.setOrderStatus(OrderStatus.Completed.getVal());
            obj.setGoodsStatus(GoodsStatus.Taked.getVal());
            num = sendOrderMapper.update(obj, w);
        }
        return ResultMsg.judge(num, "没有获取到任何数据");
    }
    //endregion
}
