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

 Date: 13/04/2024 17:37:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for intelligent_training_strategy
-- ----------------------------
DROP TABLE IF EXISTS `intelligent_training_strategy`;
CREATE TABLE `intelligent_training_strategy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `use_function` json NULL,
  `strategy_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of intelligent_training_strategy
-- ----------------------------
INSERT INTO `intelligent_training_strategy` VALUES (1, '[{\"num\": 2, \"funcName\": \"ratingBasedRandomGenerateFunc\"}, {\"num\": 2, \"funcName\": \"lessTagSolveBasedGenerateFunc\"}, {\"num\": 3, \"funcName\": \"hardTagBasedFunc\"}]', '111');
INSERT INTO `intelligent_training_strategy` VALUES (2, '[{\"num\": 3, \"funcName\": \"ratingBasedRandomGenerateFunc\"}, {\"num\": 2, \"funcName\": \"lessTagSolveBasedGenerateFunc\"}, {\"num\": 2, \"funcName\": \"hardTagBasedFunc\"}, {\"num\": 3, \"funcName\": \"cfContestBaseGenerateFunc\"}]', '2023年一星队员周训练1');
INSERT INTO `intelligent_training_strategy` VALUES (3, '[{\"num\": 3, \"funcName\": \"ratingBasedRandomGenerateFunc\"}, {\"num\": 2, \"funcName\": \"lessTagSolveBasedGenerateFunc\"}]', '2023CISE研究生智能训练');
INSERT INTO `intelligent_training_strategy` VALUES (4, '[{\"num\": 3, \"funcName\": \"ratingBasedRandomGenerateFunc\"}, {\"num\": 3, \"funcName\": \"lessTagSolveBasedGenerateFunc\"}, {\"num\": 2, \"funcName\": \"hardTagBasedFunc\"}, {\"num\": 2, \"funcName\": \"cfContestBaseGenerateFunc\"}]', '2023-ACM队员训练周赛');
INSERT INTO `intelligent_training_strategy` VALUES (5, '[{\"num\": 7, \"funcName\": \"ratingBasedRandomGenerateFunc\", \"countAboveScore\": 5}, {\"num\": 1, \"funcName\": \"lessTagSolveBasedGenerateFunc\"}, {\"num\": 1, \"funcName\": \"hardTagBasedFunc\"}, {\"num\": 1, \"funcName\": \"cfContestBaseGenerateFunc\"}]', '2023-ACM队员训练周赛-多rating');
INSERT INTO `intelligent_training_strategy` VALUES (6, '[{\"num\": 10, \"funcName\": \"ratingBasedRandomGenerateFunc\"}]', '2023-ACM队员训练周赛-rating');

SET FOREIGN_KEY_CHECKS = 1;
