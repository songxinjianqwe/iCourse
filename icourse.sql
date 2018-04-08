/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : icourse

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2018-04-08 18:16:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `alipay`
-- ----------------------------
DROP TABLE IF EXISTS `alipay`;
CREATE TABLE `alipay` (
  `user_id` bigint(50) DEFAULT NULL,
  `alipay_username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `balance` double DEFAULT NULL,
  `payment_password` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`alipay_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of alipay
-- ----------------------------
INSERT INTO `alipay` VALUES ('1', 'alipay_admin', '625', '$2a$10$TK1o7JAlSdGU.IYnXHx4yOFoLfAxKEAl9BSxKcbjD/wni.YvRZHnm');
INSERT INTO `alipay` VALUES ('1000010', 'alipay_IT修真院', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `alipay` VALUES ('1000000000', 'alipay_student1', '1700', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES (null, 'alipay_student2', '30000', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES ('1000000008', 'alipay_student3', '5360', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES (null, 'alipay_student4', '4000', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES (null, 'alipay_student5', '10000', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES (null, 'alipay_student6', '2000', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES (null, 'alipay_student7', '30000', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS');
INSERT INTO `alipay` VALUES ('1000001', 'alipay_传智播客', '1375', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `alipay` VALUES (null, 'alipay_兄弟连', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `alipay` VALUES (null, 'alipay_尚学堂', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `alipay` VALUES ('1000004', 'alipay_尚硅谷', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `alipay` VALUES (null, 'alipay_黑马程序员', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');

-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `threshold` int(11) DEFAULT NULL,
  `current_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_course_id` (`course_id`),
  CONSTRAINT `fk_class_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '大班', '39', '300', '100', '1');
INSERT INTO `class` VALUES ('2', '小班', '39', '500', '30', '1');
INSERT INTO `class` VALUES ('3', '大班', '40', '200', '100', '1');
INSERT INTO `class` VALUES ('4', '小班', '40', '800', '20', '1');
INSERT INTO `class` VALUES ('5', '大班', '41', '500', '100', '1');
INSERT INTO `class` VALUES ('6', '小班', '41', '1000', '50', '1');
INSERT INTO `class` VALUES ('7', '大班', '42', '200', '100', '1');
INSERT INTO `class` VALUES ('8', '小班', '42', '400', '50', '1');
INSERT INTO `class` VALUES ('9', '大班', '43', '200', '100', '1');
INSERT INTO `class` VALUES ('10', '小班', '43', '100', '50', '1');
INSERT INTO `class` VALUES ('11', '大班', '44', '200', '100', '1');
INSERT INTO `class` VALUES ('12', '小班', '44', '500', '20', '0');
INSERT INTO `class` VALUES ('13', '大班', '45', '200', '100', '1');
INSERT INTO `class` VALUES ('14', '小班', '45', '500', '20', '0');
INSERT INTO `class` VALUES ('15', '大班', '46', '200', '100', '0');
INSERT INTO `class` VALUES ('16', '小班', '46', '500', '20', '0');
INSERT INTO `class` VALUES ('17', '大班', '47', '200', '100', '0');
INSERT INTO `class` VALUES ('18', '小班', '47', '500', '20', '0');
INSERT INTO `class` VALUES ('19', '大班', '48', '300', '50', '1');
INSERT INTO `class` VALUES ('20', '小班', '48', '500', '30', '1');
INSERT INTO `class` VALUES ('21', '大班', '49', '200', '100', '0');
INSERT INTO `class` VALUES ('22', '小班', '50', '100', '20', '0');
INSERT INTO `class` VALUES ('23', '大班', '51', '500', '90', '0');
INSERT INTO `class` VALUES ('24', '小班', '51', '900', '20', '1');
INSERT INTO `class` VALUES ('25', '大班', '52', '200', '100', '0');
INSERT INTO `class` VALUES ('26', '小班', '52', '500', '40', '1');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` smallint(4) DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `place_time` datetime DEFAULT NULL,
  `institution_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_institution_id` (`institution_id`),
  CONSTRAINT `fk_course_institution_id` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('39', 'Spring框架实战', '0', 'Spring框架的实战教学与源码分析', '2018-04-04 20:00:00', '2018-04-06 19:31:43', '1000001');
INSERT INTO `course` VALUES ('40', 'Vue实战', '2', 'Vue电商项目实战', '2018-04-10 20:00:00', '2018-04-06 19:36:36', '1000001');
INSERT INTO `course` VALUES ('41', '概率论与数理统计', '3', '概率论与数理统计 数学基础', '2018-04-05 20:00:00', '2018-04-06 19:39:45', '1000001');
INSERT INTO `course` VALUES ('42', 'SQL从入门到精通', '1', 'Generic SQL', '2018-04-04 20:00:00', '2018-04-06 19:55:22', '1000001');
INSERT INTO `course` VALUES ('43', 'Hadoop架构实战', '0', 'Hadoop HDFS ', '2018-04-02 20:00:00', '2018-04-07 18:02:08', '1000001');
INSERT INTO `course` VALUES ('44', 'Redis实战', '0', 'Redis Distributed Cache System', '2018-04-06 20:00:00', '2018-04-07 19:16:59', '1000001');
INSERT INTO `course` VALUES ('45', 'SpringCloud 微服务架构实战', '0', 'Hystrix Enruka Consul COnfig Bus', '2018-04-05 20:00:00', '2018-04-07 19:17:58', '1000001');
INSERT INTO `course` VALUES ('46', 'Dubbo 分布式RPC实战', '0', 'Distributed RPC', '2018-04-03 20:00:00', '2018-04-07 19:27:08', '1000001');
INSERT INTO `course` VALUES ('47', 'Netty 实战', '0', 'Java网络编程实战 从NIO到Netty', '2018-04-08 20:00:00', '2018-04-07 19:27:54', '1000001');
INSERT INTO `course` VALUES ('48', 'Zookeeper实战', '0', 'ZK Client,Curator等工具实战', '2018-04-07 10:01:09', '2018-04-07 22:01:30', '1000004');
INSERT INTO `course` VALUES ('49', 'Servlet + JSP ', '0', 'JavaWeb基础', '2018-04-07 10:02:28', '2018-04-07 22:02:41', '1000004');
INSERT INTO `course` VALUES ('50', 'Spring 全家桶', '0', 'Spring,Spring MVC,Spring Data,Spring Security', '2018-04-19 12:00:00', '2018-04-07 22:04:01', '1000004');
INSERT INTO `course` VALUES ('51', 'MySQL从入门到精通', '0', 'MySQL', '2018-04-03 10:04:57', '2018-04-07 22:05:24', '1000004');
INSERT INTO `course` VALUES ('52', 'EJB3.0', '0', 'EJB', '2018-04-10 12:00:00', '2018-04-08 18:03:17', '1000010');

-- ----------------------------
-- Table structure for `institution`
-- ----------------------------
DROP TABLE IF EXISTS `institution`;
CREATE TABLE `institution` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `password` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000011 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of institution
-- ----------------------------
INSERT INTO `institution` VALUES ('1000001', '传智播客', '南京', '全国领先的IT培训机构!!!', '1', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `institution` VALUES ('1000002', '尚学堂', '北京', '专注Java的IT培训机构', '0', '$2a$10$GWLbIyczkO.L/t9E17TD5eBC1l48x.2IIsXMED01mWsGTzGJzxeEe');
INSERT INTO `institution` VALUES ('1000004', '尚硅谷', '北京', '老学员一致推荐，有口皆碑的Java培训、HTML5前端培训、大数据培训、Python培训、区块链培训', '1', '$2a$10$f8wMEp1ylgwlUpAQpJc82.tgF.tvjbzktFax.OuaQI0EQGrTj2elq');
INSERT INTO `institution` VALUES ('1000005', '北大青鸟', '北京', '北大青鸟', '0', '$2a$10$zv.T2ryM0driNkWF2Ie2A.dfSbWqektceZGl3ZaOQRWfII7jthDL.');
INSERT INTO `institution` VALUES ('1000006', '达内', '北京', '达内', '1', '$2a$10$nzBW2FQzDnsIKpSqiVllxuLUIqLaOsd0RR0stEly0i6m9C9I6Abb6');
INSERT INTO `institution` VALUES ('1000007', '蜗牛学院', '北京', '蜗牛学院', '1', '$2a$10$.1TyqNqxAzPOM/LbA.RAm.jQ0Huo1SBT.77JfRn3Ba8SjcFR9T3WK');
INSERT INTO `institution` VALUES ('1000008', '优效学院', '北京', '优效学院', '0', '$2a$10$lcBrMFcqpMc54N0yIgA6Gu.egYYurzxoxQ4DbTzVs44aFWms.B9BG');
INSERT INTO `institution` VALUES ('1000009', '龙果学员', '北京', '龙果学员', '1', '$2a$10$PnDIN0N.pdnS8R.gHkFdi.0XBhyjheEafaF7ImkBSbPwCfJlcHury');
INSERT INTO `institution` VALUES ('1000010', 'IT修真院', '北京', '66612312313', '1', '$2a$10$P5rfIpeOyJVAPA75aN/ywuplabgSZtBrZjGKdQh7QTcffYKnNE/4a');

-- ----------------------------
-- Table structure for `institution_settlement`
-- ----------------------------
DROP TABLE IF EXISTS `institution_settlement`;
CREATE TABLE `institution_settlement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `institution_id` bigint(20) DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_institution_d` (`institution_id`),
  KEY `fk_institution_settlement_orderId` (`order_id`),
  CONSTRAINT `fk_institution_d` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`id`),
  CONSTRAINT `fk_institution_settlement_orderId` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of institution_settlement
-- ----------------------------
INSERT INTO `institution_settlement` VALUES ('1', '1000001', '0.5', '2018-04-07 00:01:30', '30');
INSERT INTO `institution_settlement` VALUES ('2', '1000001', '0.8', '2018-04-08 17:40:35', '39');
INSERT INTO `institution_settlement` VALUES ('3', '1000001', '0.8', '2018-04-08 17:40:35', '42');
INSERT INTO `institution_settlement` VALUES ('4', '1000001', '0.5', '2018-04-08 18:14:09', '37');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(60) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', '$2a$10$TK1o7JAlSdGU.IYnXHx4yOFoLfAxKEAl9BSxKcbjD/wni.YvRZHnm');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `place_time` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `status` smallint(4) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `pay_type` smallint(6) DEFAULT NULL,
  `institution_id` bigint(20) DEFAULT NULL,
  `is_settled` tinyint(4) unsigned zerofill NOT NULL DEFAULT '0000',
  PRIMARY KEY (`id`),
  KEY `fk_order_class_id` (`class_id`),
  KEY `fk_order_institution_id` (`institution_id`),
  KEY `uniq_order_class_student_id` (`student_id`,`class_id`),
  CONSTRAINT `fk_order_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `fk_order_institution_id` FOREIGN KEY (`institution_id`) REFERENCES `institution` (`id`),
  CONSTRAINT `fk_order_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('30', '1000000000', '1', '2018-04-06 20:33:22', '300', '1', '1', '0', '1000001', '0001');
INSERT INTO `order` VALUES ('31', '1000000000', '6', '2018-04-06 21:26:17', '1000', '3', '1', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('32', '1000000000', '4', '2018-04-06 21:43:30', '720', '3', '0.9', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('33', '1000000000', '7', '2018-04-06 21:50:47', '160', '3', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('34', '1000000000', '8', '2018-04-06 22:20:29', '400', '3', '1', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('35', '1000000000', '5', '2018-04-06 22:22:48', '100', '3', '1', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('36', '1000000008', '4', '2018-04-07 15:55:10', '800', '1', '1', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('37', '1000000008', '2', '2018-04-07 17:36:03', '450', '1', '0.9', '0', '1000001', '0001');
INSERT INTO `order` VALUES ('38', '1000000008', '7', '2018-04-07 17:37:21', '180', '1', '0.9', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('39', '1000000008', '5', '2018-04-07 17:38:28', '450', '1', '0.9', '0', '1000001', '0001');
INSERT INTO `order` VALUES ('40', '1000000008', '8', '2018-04-07 17:40:05', '360', '1', '0.9', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('41', '1000000008', '3', '2018-04-07 17:43:10', '160', '1', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('42', '1000000008', '6', '2018-04-07 17:59:58', '800', '1', '0.8', '0', '1000001', '0001');
INSERT INTO `order` VALUES ('43', '1000000008', '9', '2018-04-07 18:02:23', '160', '1', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('44', '1000000008', '10', '2018-04-07 18:05:26', '80', '1', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('45', '1000000008', '13', '2018-04-07 19:18:12', '160', '1', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('46', '1000000008', '14', '2018-04-07 19:19:47', '400', '3', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('47', '1000000008', '11', '2018-04-07 19:22:01', '160', '1', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('48', '1000000008', '12', '2018-04-07 19:25:17', '400', '3', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('49', '1000000008', '17', '2018-04-07 19:28:13', '160', '3', '0.8', '0', '1000001', '0000');
INSERT INTO `order` VALUES ('50', '1000000008', '23', '2018-04-07 22:16:13', '400', '3', '0.8', '0', '1000004', '0000');
INSERT INTO `order` VALUES ('51', '1000000008', '22', '2018-04-07 22:34:40', '80', '3', '0.8', '0', '1000004', '0000');
INSERT INTO `order` VALUES ('52', '1000000008', '21', '2018-04-07 22:35:19', '160', '3', '0.8', '0', '1000004', '0000');
INSERT INTO `order` VALUES ('54', '1000000008', '19', '2018-04-08 10:34:18', '720', '1', '0.8', '1', '1000004', '0000');
INSERT INTO `order` VALUES ('55', '1000000008', '24', '2018-04-08 10:44:51', '720', '1', '0.8', '1', '1000004', '0000');
INSERT INTO `order` VALUES ('56', '1000000008', '20', '2018-04-08 10:47:32', '720', '1', '0.8', '1', '1000004', '0000');
INSERT INTO `order` VALUES ('57', '1000000009', '26', '2018-04-08 18:03:39', '500', '1', '1', '1', '1000010', '0000');
INSERT INTO `order` VALUES ('58', '1000000008', '25', '2018-04-08 18:04:12', '160', '3', '0.8', '0', '1000010', '0000');
INSERT INTO `order` VALUES ('59', '1000000008', '26', '2018-04-08 18:04:36', '400', '2', '0.8', '0', '1000010', '0000');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_MANAGER');
INSERT INTO `role` VALUES ('2', 'ROLE_STUDENT');
INSERT INTO `role` VALUES ('3', 'ROLE_INSTITUTION');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(60) NOT NULL,
  `reg_time` datetime DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `status` smallint(6) unsigned zerofill NOT NULL DEFAULT '000000',
  `consumptions` double unsigned NOT NULL DEFAULT '0',
  `vip_level` int(11) NOT NULL DEFAULT '1',
  `nickname` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000010 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1000000000', 'student1', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS', '2018-04-05 20:18:31', '151070063@smail.nju.edu.cn', '000001', '300', '1', '学生AAA');
INSERT INTO `student` VALUES ('1000000001', 'student2', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS', '2018-04-06 17:38:46', '151070063@smail.nju.edu.cn', '000000', '0', '1', '学生BBB');
INSERT INTO `student` VALUES ('1000000008', 'student3', '$2a$10$mjc2MUaVhXmJgJiDWy2RdeNZE0g0K2Rr3gsKvtCAzZokNyC2gdDMS', '2018-04-07 14:02:50', '151070063@smail.nju.edu.cn', '000001', '3760', '3', '学生CCC1231');
INSERT INTO `student` VALUES ('1000000009', 'student4', '$2a$10$aUkSr5JIsTi/rR09m4oXqOF6LHnAEM4SqwI4nGlfsxKLwk52Pl1I2', '2018-04-08 17:54:02', '151070063@smail.nju.edu.cn', '000001', '0', '1', 'sss12312');

-- ----------------------------
-- Table structure for `study_record`
-- ----------------------------
DROP TABLE IF EXISTS `study_record`;
CREATE TABLE `study_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `class_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `normal_score` double DEFAULT NULL,
  `total_score` double DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_class_student` (`class_id`,`student_id`),
  KEY `fk_study_record_student_id` (`student_id`),
  KEY `uniq_study_record_order_id` (`order_id`),
  CONSTRAINT `fk_study_record_class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `fk_study_record_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
  CONSTRAINT `fk_study_record_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of study_record
-- ----------------------------
INSERT INTO `study_record` VALUES ('1', '1', '1000000000', '50', '0', '0', '30');
INSERT INTO `study_record` VALUES ('2', '6', '1000000000', '0', '0', '1', '31');
INSERT INTO `study_record` VALUES ('3', '4', '1000000000', '0', '0', '1', '32');
INSERT INTO `study_record` VALUES ('4', '8', '1000000000', '0', '0', '1', '34');
INSERT INTO `study_record` VALUES ('5', '5', '1000000000', '0', '0', '1', '35');
INSERT INTO `study_record` VALUES ('6', '4', '1000000008', '0', '0', '0', '36');
INSERT INTO `study_record` VALUES ('7', '2', '1000000008', '0', '0', '0', '37');
INSERT INTO `study_record` VALUES ('8', '7', '1000000008', '0', '0', '0', '38');
INSERT INTO `study_record` VALUES ('9', '5', '1000000008', '0', '0', '0', '39');
INSERT INTO `study_record` VALUES ('10', '8', '1000000008', '0', '0', '0', '40');
INSERT INTO `study_record` VALUES ('11', '3', '1000000008', '0', '0', '0', '41');
INSERT INTO `study_record` VALUES ('12', '6', '1000000008', '0', '0', '0', '42');
INSERT INTO `study_record` VALUES ('13', '9', '1000000008', '0', '0', '0', '43');
INSERT INTO `study_record` VALUES ('14', '10', '1000000008', '0', '0', '0', '44');
INSERT INTO `study_record` VALUES ('15', '13', '1000000008', '0', '0', '0', '45');
INSERT INTO `study_record` VALUES ('16', '14', '1000000008', '0', '0', '1', '46');
INSERT INTO `study_record` VALUES ('17', '11', '1000000008', '0', '0', '0', '47');
INSERT INTO `study_record` VALUES ('18', '12', '1000000008', '0', '0', '1', '48');
INSERT INTO `study_record` VALUES ('19', '23', '1000000008', '0', '0', '1', '50');
INSERT INTO `study_record` VALUES ('20', '19', '1000000008', '80', '0', '0', '54');
INSERT INTO `study_record` VALUES ('21', '24', '1000000008', '70', '0', '0', '55');
INSERT INTO `study_record` VALUES ('22', '20', '1000000008', '0', '0', '0', '56');
INSERT INTO `study_record` VALUES ('23', '26', '1000000009', '30', '0', '0', '57');
INSERT INTO `study_record` VALUES ('24', '25', '1000000008', '0', '0', '1', '58');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_role_role_id` (`role_id`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('1000000000', '2');
INSERT INTO `user_role` VALUES ('1000000001', '2');
INSERT INTO `user_role` VALUES ('1000000003', '2');
INSERT INTO `user_role` VALUES ('1000000005', '2');
INSERT INTO `user_role` VALUES ('1000000006', '2');
INSERT INTO `user_role` VALUES ('1000000007', '2');
INSERT INTO `user_role` VALUES ('1000000008', '2');
INSERT INTO `user_role` VALUES ('1000000009', '2');
INSERT INTO `user_role` VALUES ('1', '3');
INSERT INTO `user_role` VALUES ('1000001', '3');
INSERT INTO `user_role` VALUES ('1000002', '3');
INSERT INTO `user_role` VALUES ('1000003', '3');
INSERT INTO `user_role` VALUES ('1000004', '3');
INSERT INTO `user_role` VALUES ('1000005', '3');
INSERT INTO `user_role` VALUES ('1000006', '3');
INSERT INTO `user_role` VALUES ('1000007', '3');
INSERT INTO `user_role` VALUES ('1000008', '3');
INSERT INTO `user_role` VALUES ('1000009', '3');
INSERT INTO `user_role` VALUES ('1000010', '3');

-- ----------------------------
-- Table structure for `vip_discount`
-- ----------------------------
DROP TABLE IF EXISTS `vip_discount`;
CREATE TABLE `vip_discount` (
  `vip_level` int(11) NOT NULL,
  `discount` double NOT NULL DEFAULT '0',
  `least_consumptions` double DEFAULT NULL,
  PRIMARY KEY (`vip_level`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of vip_discount
-- ----------------------------
INSERT INTO `vip_discount` VALUES ('1', '1', '0');
INSERT INTO `vip_discount` VALUES ('2', '0.9', '500');
INSERT INTO `vip_discount` VALUES ('3', '0.8', '2000');
INSERT INTO `vip_discount` VALUES ('4', '0.7', '5000');
INSERT INTO `vip_discount` VALUES ('5', '0.6', '10000');
