/*
 Navicat Premium Data Transfer

 Source Server         : buctOj
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : 222.199.230.62:3306
 Source Schema         : algorithm_contest_data_collect

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 13/04/2024 17:36:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for intelligent_training_generate_function
-- ----------------------------
DROP TABLE IF EXISTS `intelligent_training_generate_function`;
CREATE TABLE `intelligent_training_generate_function`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of intelligent_training_generate_function
-- ----------------------------
INSERT INTO `intelligent_training_generate_function` VALUES (5, 'ratingBasedRandomGenerateFunc', '根据分数随机推荐题目');
INSERT INTO `intelligent_training_generate_function` VALUES (6, 'lessTagSolveBasedGenerateFunc', '推荐补充专题');
INSERT INTO `intelligent_training_generate_function` VALUES (7, 'hardTagBasedFunc', '根据专题推荐');
INSERT INTO `intelligent_training_generate_function` VALUES (8, 'cfContestBaseGenerateFunc', '根据CF比赛推荐');

SET FOREIGN_KEY_CHECKS = 1;
