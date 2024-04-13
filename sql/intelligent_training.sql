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

 Date: 13/04/2024 17:36:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for intelligent_training
-- ----------------------------
DROP TABLE IF EXISTS `intelligent_training`;
CREATE TABLE `intelligent_training`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `strategy_id` int NULL DEFAULT NULL,
  `start_timestamp` timestamp NULL DEFAULT NULL,
  `end_timestamp` timestamp NULL DEFAULT NULL,
  `p_num` int NULL DEFAULT NULL,
  `s_num` int NULL DEFAULT NULL,
  `s_rating` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `training_strategy_for`(`strategy_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of intelligent_training
-- ----------------------------
INSERT INTO `intelligent_training` VALUES (5, '2023ACM队员周训练-1', 4, '2023-06-29 00:00:00', '2023-07-09 22:00:00', 44, 145, 184400);
INSERT INTO `intelligent_training` VALUES (6, '2023ACM队员周训练-2', 2, '2023-07-10 08:00:00', '2023-07-16 22:00:00', 27, 94, 119900);
INSERT INTO `intelligent_training` VALUES (7, '2023ACM队员周训练-3', 2, '2023-07-17 08:00:00', '2023-07-23 22:00:00', 12, 35, 48800);
INSERT INTO `intelligent_training` VALUES (8, '2023ACM队员周训练-4', 4, '2023-07-24 08:00:00', '2023-07-30 22:00:00', 25, 137, 193800);
INSERT INTO `intelligent_training` VALUES (9, '2023ACM队员周训练-5', 4, '2023-07-31 08:00:00', '2023-08-06 22:00:00', 34, 155, 216000);
INSERT INTO `intelligent_training` VALUES (10, '2023ACM队员周训练-6', 4, '2023-08-07 08:00:00', '2023-08-13 22:00:00', 28, 143, 196900);
INSERT INTO `intelligent_training` VALUES (11, '2023ACM队员周训练-7', 4, '2023-08-14 08:00:00', '2023-08-20 22:00:00', 29, 130, 175100);
INSERT INTO `intelligent_training` VALUES (12, '2023ACM队员周训练-8', 4, '2023-08-21 08:00:00', '2023-08-27 22:00:00', 19, 83, 116100);
INSERT INTO `intelligent_training` VALUES (13, '2023ACM队员周训练-9', 4, '2023-08-28 08:00:00', '2023-09-03 22:00:00', 14, 38, 50000);
INSERT INTO `intelligent_training` VALUES (14, '2023ACM队员周训练-10', 4, '2023-09-04 08:00:00', '2023-09-10 22:00:00', 49, 171, 230500);
INSERT INTO `intelligent_training` VALUES (15, '2023ACM队员周训练-11', 4, '2023-09-11 08:00:00', '2023-09-17 22:00:00', 39, 125, 168700);
INSERT INTO `intelligent_training` VALUES (16, '2023ACM队员周训练-12', 5, '2023-09-18 08:00:00', '2023-09-24 22:00:00', 30, 77, 105600);
INSERT INTO `intelligent_training` VALUES (17, '2023ACM队员周训练-13', 6, '2023-09-25 08:00:00', '2023-10-01 22:00:00', 20, 71, 108100);
INSERT INTO `intelligent_training` VALUES (18, '2023ACM队员周训练-14', 6, '2023-10-09 08:00:00', '2023-10-15 22:00:00', 13, 49, 70300);
INSERT INTO `intelligent_training` VALUES (19, '2023ACM队员周训练-15', 5, '2023-10-16 00:00:00', '2023-10-22 00:00:00', 11, 21, 30300);
INSERT INTO `intelligent_training` VALUES (20, '2023ACM队员周训练-16', 4, '2023-10-23 08:00:00', '2023-10-29 22:00:00', 14, 56, 82000);
INSERT INTO `intelligent_training` VALUES (21, '2023ACM队员周训练-17', 5, '2023-10-30 08:00:00', '2023-11-05 22:00:00', 10, 42, 54800);
INSERT INTO `intelligent_training` VALUES (22, '2023ACM队员周训练-18', 5, '2023-11-06 08:00:00', '2023-11-12 22:00:00', 10, 22, 31300);
INSERT INTO `intelligent_training` VALUES (23, '2023ACM队员周训练-19', 5, '2023-11-13 08:00:00', '2023-11-19 22:00:00', 4, 9, 11600);
INSERT INTO `intelligent_training` VALUES (24, '2023ACM队员周训练-20', 6, '2023-11-20 08:00:00', '2023-11-26 22:00:00', 18, 87, 86500);
INSERT INTO `intelligent_training` VALUES (25, '2023ACM队员周训练-21', 5, '2023-11-27 08:00:00', '2023-12-03 22:00:00', 9, 26, 29100);
INSERT INTO `intelligent_training` VALUES (26, '2023ACM队员周训练-22', 5, '2023-12-04 08:00:00', '2023-12-10 22:00:00', 9, 28, 32000);
INSERT INTO `intelligent_training` VALUES (27, '2023ACM队员周训练-23', 5, '2023-12-11 08:00:00', '2023-12-17 22:00:00', 4, 7, 8200);
INSERT INTO `intelligent_training` VALUES (28, '2023ACM队员周训练-24', 5, '2023-12-18 08:00:00', '2023-12-24 22:00:00', 4, 14, 16800);
INSERT INTO `intelligent_training` VALUES (30, '2023ACM队员周训练-25', 5, '2023-12-25 08:00:00', '2023-12-31 22:00:00', 2, 2, 2100);
INSERT INTO `intelligent_training` VALUES (31, '2024ACM队员周训练-1', 5, '2024-01-08 08:00:00', '2024-01-14 22:00:00', 4, 6, 7600);
INSERT INTO `intelligent_training` VALUES (33, '2024ACM队员周训练-2', 5, '2024-01-15 08:00:00', '2024-01-21 22:00:00', 17, 73, 104600);
INSERT INTO `intelligent_training` VALUES (34, '2024ACM队员周训练-3', 5, '2024-01-22 08:00:00', '2024-01-28 22:00:00', 10, 30, 44000);
INSERT INTO `intelligent_training` VALUES (35, '2024ACM队员周训练-4', 5, '2024-01-29 08:00:00', '2024-02-04 22:00:00', 8, 24, 31200);
INSERT INTO `intelligent_training` VALUES (36, '2024ACM队员周训练-5', 5, '2024-02-05 08:00:00', '2024-02-11 22:00:00', 3, 5, 6600);
INSERT INTO `intelligent_training` VALUES (37, '2024ACM队员周训练-6', 5, '2024-02-12 08:00:00', '2024-02-18 22:00:00', 1, 1, 1300);
INSERT INTO `intelligent_training` VALUES (38, '2024ACM队员周训练-7', 5, '2024-02-19 08:00:00', '2024-02-25 22:00:00', 1, 0, 0);
INSERT INTO `intelligent_training` VALUES (39, '2024ACM队员周训练-8', 5, '2024-02-26 08:00:00', '2024-03-03 22:00:00', 26, 127, 148700);
INSERT INTO `intelligent_training` VALUES (40, '2024ACM队员周训练-9', 5, '2024-03-04 08:00:00', '2024-03-10 22:00:00', 26, 123, 139400);
INSERT INTO `intelligent_training` VALUES (41, '2024ACM队员周训练-10', 5, '2024-03-11 08:00:00', '2024-03-17 22:00:00', 27, 119, 151000);
INSERT INTO `intelligent_training` VALUES (42, '2024ACM队员周训练-11', 5, '2024-03-18 08:00:00', '2024-03-24 22:00:00', 31, 164, 173500);
INSERT INTO `intelligent_training` VALUES (43, '2024ACM队员周训练-12', 5, '2024-03-25 08:00:00', '2024-03-31 22:00:00', 29, 123, 140400);
INSERT INTO `intelligent_training` VALUES (44, '2024ACM队员周训练-13', 5, '2024-04-01 08:00:00', '2024-04-07 22:00:00', 25, 113, 115000);
INSERT INTO `intelligent_training` VALUES (45, '2024ACM队员周训练-14', 5, '2024-04-08 08:00:00', '2024-04-14 22:00:00', NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
