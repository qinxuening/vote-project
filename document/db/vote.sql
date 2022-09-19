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

 Date: 19/09/2022 22:31:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for candidate
-- ----------------------------
DROP TABLE IF EXISTS `candidate`;
CREATE TABLE `candidate`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `voting_topic_id` int(11) NOT NULL COMMENT '投票场次id',
  `candidate_full_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '候选人全名',
  `id_number` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '候选人身份证号',
  `candidate_nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '候选人昵称',
  `age` int(3) NULL DEFAULT NULL COMMENT '年龄',
  `gender` tinyint(1) NOT NULL COMMENT '性别(1男，2女)',
  `campaign_slogan` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '竞选口号',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加候选人时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `voting_topic_id`(`voting_topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '候选人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for email_send_error
-- ----------------------------
DROP TABLE IF EXISTS `email_send_error`;
CREATE TABLE `email_send_error`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `voting_topic_id` int(11) NULL DEFAULT NULL COMMENT '投票场次id',
  `response_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '失败原因',
  `send_email_json` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '发送邮箱数据',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `voting_topic_id`(`voting_topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件发送失败记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for vote_details
-- ----------------------------
DROP TABLE IF EXISTS `vote_details`;
CREATE TABLE `vote_details`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `voting_topic_id` int(11) NOT NULL COMMENT '投票场次id',
  `candidate_id` int(11) NOT NULL COMMENT '候选人id',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票者邮箱',
  `id_number` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '投票者身份证号',
  `vote_time` datetime(0) NOT NULL COMMENT '投票时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id_number`(`id_number`) USING BTREE,
  INDEX `candidate_id`(`candidate_id`) USING BTREE,
  INDEX `voting_topic_id`(`voting_topic_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投票详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for voting_topic
-- ----------------------------
DROP TABLE IF EXISTS `voting_topic`;
CREATE TABLE `voting_topic`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `topic_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '场次主题名称',
  `topic_describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '场次主题描述',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态（0未开始，1启用，2关闭），默认0',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '投票开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '投票结束时间',
  `create_time` datetime(0) NOT NULL COMMENT '添加场次时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '投票主题（场次）表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
