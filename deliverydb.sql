/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : deliverydb

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 25/03/2021 17:18:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwdSalt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (1, '2021-02-03 23:14:09', 'admin', '123456', NULL);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customerId` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '2021-03-24 21:57:12', '{\"msg\":\"4214214214\"}', 3, '阿银啊');
INSERT INTO `comment` VALUES (2, '2021-03-24 22:04:25', '我放假啦的刷卡机了f', 3, '阿银啊');
INSERT INTO `comment` VALUES (3, '2021-03-25 16:23:20', '快来试试吧', 1, '卡特呀14');
INSERT INTO `comment` VALUES (4, '2021-03-25 16:29:40', '我请你房间里的思考', 1, '卡特呀14');
INSERT INTO `comment` VALUES (5, '2021-03-25 16:30:27', '范德萨发达', 1, '卡特呀14');
INSERT INTO `comment` VALUES (6, '2021-03-25 16:31:02', '尽量克服的数量', 1, '卡特呀14');
INSERT INTO `comment` VALUES (7, '2021-03-25 16:32:16', '一不小心拖节奏了', 1, '卡特呀14');

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `issuerId` int(0) NOT NULL COMMENT '发放人id（管理员',
  `conditionPrice` int(0) NOT NULL COMMENT '条件，比如要满足多少元才可使用',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠价格',
  `validDate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isUse` bit(1) NOT NULL COMMENT '是否使用',
  `customerId` int(0) NOT NULL COMMENT '拥有者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for courier
-- ----------------------------
DROP TABLE IF EXISTS `courier`;
CREATE TABLE `courier`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `headPic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwdSalt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未知',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of courier
-- ----------------------------
INSERT INTO `courier` VALUES (1, '2021-03-01 23:50:44', 'test123', '18244445555', NULL, '123456', NULL, '男');
INSERT INTO `courier` VALUES (2, '2021-03-24 22:08:18', '小儿啊', '15645451212', NULL, '123456', NULL, '男');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headPic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phoneNum` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwdSalt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '未知',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, '2021-02-04 12:53:21', '卡特呀14', NULL, '18270643064', '广东深圳龙华区', '123456', NULL, '女');
INSERT INTO `customer` VALUES (2, '2021-03-02 19:07:01', '卡特', NULL, '19874524545', NULL, '123456', NULL, '未知');
INSERT INTO `customer` VALUES (3, '2021-03-23 17:06:15', '阿银啊', NULL, '13245456666', '203栋宿舍', '123456', NULL, '女');

-- ----------------------------
-- Table structure for delivery_point
-- ----------------------------
DROP TABLE IF EXISTS `delivery_point`;
CREATE TABLE `delivery_point`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_point
-- ----------------------------
INSERT INTO `delivery_point` VALUES (2, '2021-02-28 20:51:21', '避暑山庄');
INSERT INTO `delivery_point` VALUES (3, '2021-02-28 20:51:38', '角落口');
INSERT INTO `delivery_point` VALUES (4, '2021-03-01 12:17:39', '学校门口');

-- ----------------------------
-- Table structure for delivery_type
-- ----------------------------
DROP TABLE IF EXISTS `delivery_type`;
CREATE TABLE `delivery_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of delivery_type
-- ----------------------------
INSERT INTO `delivery_type` VALUES (1, '2021-03-01 12:16:23', '中通');
INSERT INTO `delivery_type` VALUES (2, '2021-03-01 12:16:34', '圆通');
INSERT INTO `delivery_type` VALUES (3, '2021-03-01 12:16:46', '韵达');
INSERT INTO `delivery_type` VALUES (4, '2021-03-01 12:16:57', '邮政');
INSERT INTO `delivery_type` VALUES (5, '2021-03-01 12:17:07', '顺风');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for make_money
-- ----------------------------
DROP TABLE IF EXISTS `make_money`;
CREATE TABLE `make_money`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `customerId` int(0) NOT NULL,
  `money` decimal(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of make_money
-- ----------------------------
INSERT INTO `make_money` VALUES (1, '2021-03-18 22:57:54', 1, 2.00);
INSERT INTO `make_money` VALUES (2, '2021-03-18 22:58:55', 1, 2.00);
INSERT INTO `make_money` VALUES (3, '2021-03-18 22:58:59', 1, 2.00);
INSERT INTO `make_money` VALUES (4, '2021-03-18 23:00:59', 1, 2.00);
INSERT INTO `make_money` VALUES (5, '2021-03-18 23:05:30', 1, 2.00);
INSERT INTO `make_money` VALUES (6, '2021-03-18 23:06:53', 1, 2.00);
INSERT INTO `make_money` VALUES (7, '2021-03-23 17:28:42', 3, 2.00);
INSERT INTO `make_money` VALUES (8, '2021-03-25 13:19:49', 3, 2.00);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '公告',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (3, '2021-03-22 22:25:05', '活动', '123', '反对撒阿迪斯反对撒阿迪斯反对撒阿迪斯反对撒阿迪斯fdsa fads发大水了科技发达是；发大水发桉树', '');
INSERT INTO `notice` VALUES (8, '2021-03-03 11:19:39', '公告', '反对撒阿迪斯', '反对撒阿迪斯反对撒阿迪斯反对撒阿迪斯反对撒阿迪斯', '');
INSERT INTO `notice` VALUES (10, '2021-03-25 16:40:26', '公告', '大反馈', '基于大家的热爱，新增和修改了许多小细节', '');

-- ----------------------------
-- Table structure for order_use_coupon
-- ----------------------------
DROP TABLE IF EXISTS `order_use_coupon`;
CREATE TABLE `order_use_coupon`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `orderId` int(0) NOT NULL,
  `couponId` int(0) NULL DEFAULT NULL,
  `isTake` bit(1) NOT NULL COMMENT '是取件 true 还是寄件 false',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `customerId` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, '2021-03-02 22:46:01', '你好啊', '飞机卢卡斯的建立房间阿斯顿 ', 1);
INSERT INTO `post` VALUES (2, '2021-03-02 23:41:11', '测试放大镜看但是', '过分了肯定是咖啡\n    范德萨垃圾分类计法\n夫卡的时间里卡的时间\n打飞机啊', 1);
INSERT INTO `post` VALUES (3, '2021-03-13 16:44:47', 'hello', '你号飓风琳达发大水刮大风安东', 1);

-- ----------------------------
-- Table structure for send_order
-- ----------------------------
DROP TABLE IF EXISTS `send_order`;
CREATE TABLE `send_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `customerId` int(0) NOT NULL COMMENT '下单人',
  `courierId` int(0) NULL DEFAULT 0 COMMENT '接单人',
  `senderName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人姓名',
  `senderPhoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件人手机号',
  `receiverName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人姓名',
  `receiverPhoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人手机号',
  `takeAddress` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '取件地址',
  `sendAddress` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '寄件地址',
  `orderStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态:进行中/已取消/已完成',
  `goodsStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物品状态:已寄件/未寄件/已送达 如果不是已送达那么就是未送达',
  `payStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付状态:已支付/未支付',
  `takeTimes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '取件时间 12:00~13:30',
  `goodsTypeId` int(0) NULL DEFAULT NULL COMMENT '物品类型',
  `deliveryPointId` int(0) NULL DEFAULT NULL COMMENT '快递点',
  `deliveryTypeId` int(0) NULL DEFAULT NULL COMMENT '快递类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of send_order
-- ----------------------------
INSERT INTO `send_order` VALUES (6, '2021-03-01 00:00:00', 1, 1, '发给大哥的撒', '325432523', '个地方官府', '18546454213', '发大水哈算法', '很尬尴的撒', '已取消', '寄件中', '已支付', '12:00~14:00', 1, 3, 1);
INSERT INTO `send_order` VALUES (7, '2021-03-01 00:00:00', 1, 1, '发大水发', '18546454213', '玩大话', '18456454565', '发生大', '放大发大', '进行中', '寄件中', '已支付', '12:00~14:00', 2, 3, 2);
INSERT INTO `send_order` VALUES (11, '2021-03-25 13:19:49', 3, 0, '阿银啊', '13245456666', '小风', '13145645555', '203栋宿舍', '北京广州是会计连接方式的', '进行中', '寄件中', '已支付', '12:00~14:00', 2, 3, 2);

-- ----------------------------
-- Table structure for take_order
-- ----------------------------
DROP TABLE IF EXISTS `take_order`;
CREATE TABLE `take_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `customerId` int(0) NOT NULL COMMENT '下单人',
  `courierId` int(0) NULL DEFAULT 0 COMMENT '接单人',
  `takerName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '取件昵称',
  `phoneNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '取件地址',
  `takeCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '取件码',
  `orderStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单状态:进行中/已取消/已完成',
  `payStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '支付状态:已支付/未支付',
  `goodsStatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物品状态:已取件/未取件/已送达 如果不是已送达那么就是未送达',
  `deliveryPointId` int(0) NOT NULL COMMENT '快递点',
  `deliveryTypeId` int(0) NOT NULL COMMENT '快递类型 （这个是新加的）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of take_order
-- ----------------------------
INSERT INTO `take_order` VALUES (1, '2021-03-02 00:00:00', 1, 1, '卡特', '18270643064', '管理卡发动机', '1234', '已取消', '已支付', '寄件中', 2, 2);
INSERT INTO `take_order` VALUES (3, '2021-03-14 22:48:50', 1, 0, 'hello', '18345456565', '付款就拉到江东父老就发的发答', '6445', '进行中', '未支付', '寄件中', 3, 3);
INSERT INTO `take_order` VALUES (4, '2021-03-18 22:52:15', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '1234', '进行中', '已支付', '寄件中', 3, 1);
INSERT INTO `take_order` VALUES (5, '2021-03-18 22:54:55', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '4343', '进行中', '已支付', '寄件中', 2, 3);
INSERT INTO `take_order` VALUES (6, '2021-03-18 22:56:20', 1, 2, '卡特呀14', '18270643064', '广东深圳龙华区', '5353', '已完成', '已支付', '已取件', 3, 2);
INSERT INTO `take_order` VALUES (7, '2021-03-18 22:57:11', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '3535', '进行中', '已支付', '寄件中', 4, 4);
INSERT INTO `take_order` VALUES (8, '2021-03-18 22:57:53', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '3535', '进行中', '已支付', '寄件中', 4, 4);
INSERT INTO `take_order` VALUES (9, '2021-03-18 22:58:54', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '3535', '进行中', '已支付', '寄件中', 4, 4);
INSERT INTO `take_order` VALUES (10, '2021-03-18 22:58:59', 1, 2, '卡特呀14', '18270643064', '广东深圳龙华区', '24242', '已完成', '已支付', '已取件', 2, 3);
INSERT INTO `take_order` VALUES (11, '2021-03-18 23:00:58', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '2341', '已取消', '已支付', '寄件中', 3, 2);
INSERT INTO `take_order` VALUES (12, '2021-03-18 23:05:30', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '4343', '进行中', '已支付', '寄件中', 3, 3);
INSERT INTO `take_order` VALUES (13, '2021-03-18 23:06:53', 1, 0, '卡特呀14', '18270643064', '广东深圳龙华区', '4344', '进行中', '已支付', '寄件中', 2, 3);
INSERT INTO `take_order` VALUES (14, '2021-03-23 17:28:42', 3, 2, '阿银啊', '13245456666', '203栋宿舍', '123456', '已完成', '已支付', '已取件', 2, 3);

-- ----------------------------
-- Table structure for take_time
-- ----------------------------
DROP TABLE IF EXISTS `take_time`;
CREATE TABLE `take_time`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `createDate` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of take_time
-- ----------------------------
INSERT INTO `take_time` VALUES (1, '2021-03-01 12:15:45', '12:00~14:00');
INSERT INTO `take_time` VALUES (2, '2021-03-01 12:16:03', '16:00~18:00');

SET FOREIGN_KEY_CHECKS = 1;
