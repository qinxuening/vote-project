/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.104:3306
 Source Server Type    : MySQL
 Source Server Version : 100407
 Source Host           : 192.168.1.104:3306
 Source Schema         : vote

 Target Server Type    : MySQL
 Target Server Version : 100407
 File Encoding         : 65001

 Date: 16/09/2022 00:12:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `candidate_full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '候选人全名',
  `id_number` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '候选人身份证号',
  `candidate_nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '候选人昵称',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(1) NOT NULL COMMENT '性别(1男，2女)',
  `campaign_slogan` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '竞选口号',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加候选人时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '候选人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for email_send_error
-- ----------------------------
DROP TABLE IF EXISTS `email_send_error`;
CREATE TABLE `email_send_error`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `response_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '失败原因',
  `send_email_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发送邮箱数据',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发送失败记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_settings
-- ----------------------------
DROP TABLE IF EXISTS `sys_settings`;
CREATE TABLE `sys_settings`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `group_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组code',
  `field_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `field_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '字段数值',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1启用，2关闭），默认1',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `group_code`(`group_code`, `field_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_settings
-- ----------------------------
INSERT INTO `sys_settings` VALUES (1, 'vote', 'do_election', '', 1, '默认为空，1开始，2结束', '2022-09-13 22:56:46', '2022-09-15 12:30:38');

-- ----------------------------
-- Table structure for vote_details
-- ----------------------------
DROP TABLE IF EXISTS `vote_details`;
CREATE TABLE `vote_details`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `candidate_id` int(11) NOT NULL COMMENT '候选人id',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票者邮箱',
  `id_number` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票者身份证号',
  `vote_time` datetime(0) NOT NULL COMMENT '投票时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `candidate_id`(`candidate_id`) USING BTREE,
  UNIQUE INDEX `id_number`(`id_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投票详情表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
