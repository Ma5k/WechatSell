# Host: 192.168.0.116  (Version 5.7.17)
# Date: 2019-02-19 23:43:41
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "order_detail"
#

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL,
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '当前价格,单位分',
  `product_quantity` int(11) NOT NULL COMMENT '数量',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "order_detail"
#

INSERT INTO `order_detail` VALUES ('1546526080372104279','1546526080313281354','111','宫保鸡丁',33.00,1,'http://xxxx.jpg','2019-01-03 14:34:39','2019-01-03 14:34:39'),('1546526080404255244','1546526080313281354','333','炒饭',15.00,2,'x.jpg','2019-01-03 14:34:39','2019-01-03 14:34:39'),('1546618205363806048','1546618204977293498','123456','鱼香肉丝',23.00,2,'http://xxxx.jpg','2019-01-04 16:10:03','2019-01-04 16:10:03'),('1546764589606473549','1546764589533150672','123456','鱼香肉丝',23.00,2,'http://xxxx.jpg','2019-01-06 08:49:47','2019-01-06 08:49:47');

#
# Structure for table "order_master"
#

DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8,2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态, 默认为新下单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态, 默认未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "order_master"
#

INSERT INTO `order_master` VALUES ('1546526080313281354','mask','133234545','武汉','110110',63.00,1,1,'2019-01-03 14:34:39','2019-01-20 07:42:46'),('1546618204977293498','张三','18868822111','慕课网总部',' ew3euwhd7sjw9diwkq',46.00,2,0,'2019-01-04 16:10:03','2019-01-20 07:37:52'),('1546764589533150672','张三','18868822111','慕课网总部',' ew3euwhd7sjw9diwkq',46.00,0,0,'2019-01-06 08:49:47','2019-01-20 08:01:12');

#
# Structure for table "product_category"
#

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) NOT NULL COMMENT '类目名字',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

#
# Data for table "product_category"
#

INSERT INTO `product_category` VALUES (1,'热门美食',1,'2019-01-01 00:00:00','2019-01-01 00:00:00'),(3,'女生最爱',2,'2019-01-02 12:26:41','2019-01-02 12:26:41'),(5,'烧烤专区',3,'2019-01-02 12:27:15','2019-01-02 12:27:15'),(6,'深夜食堂',5,'2019-01-20 09:58:33','2019-01-20 09:58:33');

#
# Structure for table "product_info"
#

DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(64) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '单价',
  `product_stock` int(11) NOT NULL COMMENT '库存',
  `product_description` varchar(64) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) DEFAULT NULL COMMENT '小图',
  `product_status` tinyint(3) DEFAULT '0' COMMENT '商品状态,0正常1下架',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "product_info"
#

INSERT INTO `product_info` VALUES ('111','宫保鸡丁',33.00,101,'带劲','http://xxxx.jpg',0,1,'2019-01-02 14:03:02','2019-01-20 09:31:58'),('123456','鱼香肉丝',23.00,104,'美味','http://xxxx.jpg',0,1,'2019-01-02 13:30:05','2019-01-20 07:55:25'),('1547977044767968645','黄焖鸡米饭',25.00,150,'土鸡肉','xxx',0,2,'2019-01-20 09:37:24','2019-01-20 09:37:24'),('222','土豆丝',13.00,300,'好吃','xx.jpg',0,1,'2019-01-03 13:55:35','2019-01-03 13:55:35'),('333','炒饭',15.00,102,'好吃','x.jpg',0,1,'2019-01-03 13:56:41','2019-01-04 09:38:46');

#
# Structure for table "seller_info"
#

DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `openid` varchar(64) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='卖家信息表';

#
# Data for table "seller_info"
#

INSERT INTO `seller_info` VALUES ('1550590664610187809','admin','admin','abc','2019-02-19 15:37:43','2019-02-19 15:37:43');
