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

 Date: 13/04/2024 17:37:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for problem_tag
-- ----------------------------
DROP TABLE IF EXISTS `problem_tag`;
CREATE TABLE `problem_tag`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag_name_s`(`tag_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of problem_tag
-- ----------------------------
INSERT INTO `problem_tag` VALUES (30, '*special');
INSERT INTO `problem_tag` VALUES (36, '2-sat');
INSERT INTO `problem_tag` VALUES (17, 'binary search');
INSERT INTO `problem_tag` VALUES (6, 'bitmasks');
INSERT INTO `problem_tag` VALUES (1, 'brute force');
INSERT INTO `problem_tag` VALUES (29, 'chinese remainder theorem');
INSERT INTO `problem_tag` VALUES (16, 'combinatorics');
INSERT INTO `problem_tag` VALUES (3, 'constructive algorithms');
INSERT INTO `problem_tag` VALUES (12, 'data structures');
INSERT INTO `problem_tag` VALUES (19, 'dfs and similar');
INSERT INTO `problem_tag` VALUES (26, 'divide and conquer');
INSERT INTO `problem_tag` VALUES (7, 'dp');
INSERT INTO `problem_tag` VALUES (13, 'dsu');
INSERT INTO `problem_tag` VALUES (37, 'expression parsing');
INSERT INTO `problem_tag` VALUES (22, 'fft');
INSERT INTO `problem_tag` VALUES (24, 'flows');
INSERT INTO `problem_tag` VALUES (21, 'games');
INSERT INTO `problem_tag` VALUES (31, 'geometry');
INSERT INTO `problem_tag` VALUES (25, 'graph matchings');
INSERT INTO `problem_tag` VALUES (4, 'graphs');
INSERT INTO `problem_tag` VALUES (9, 'greedy');
INSERT INTO `problem_tag` VALUES (27, 'hashing');
INSERT INTO `problem_tag` VALUES (18, 'implementation');
INSERT INTO `problem_tag` VALUES (10, 'interactive');
INSERT INTO `problem_tag` VALUES (14, 'math');
INSERT INTO `problem_tag` VALUES (32, 'matrices');
INSERT INTO `problem_tag` VALUES (2, 'meet-in-the-middle');
INSERT INTO `problem_tag` VALUES (28, 'number theory');
INSERT INTO `problem_tag` VALUES (23, 'probabilities');
INSERT INTO `problem_tag` VALUES (35, 'schedules');
INSERT INTO `problem_tag` VALUES (5, 'shortest paths');
INSERT INTO `problem_tag` VALUES (11, 'sortings');
INSERT INTO `problem_tag` VALUES (34, 'string suffix structures');
INSERT INTO `problem_tag` VALUES (15, 'strings');
INSERT INTO `problem_tag` VALUES (33, 'ternary search');
INSERT INTO `problem_tag` VALUES (8, 'trees');
INSERT INTO `problem_tag` VALUES (20, 'two pointers');

SET FOREIGN_KEY_CHECKS = 1;
