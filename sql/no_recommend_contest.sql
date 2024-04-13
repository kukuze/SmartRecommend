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

 Date: 13/04/2024 17:37:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for no_recommend_contest
-- ----------------------------
DROP TABLE IF EXISTS `no_recommend_contest`;
CREATE TABLE `no_recommend_contest`  (
  `cid` bigint UNSIGNED NOT NULL COMMENT '比赛id\r\n',
  `reason` tinyint NOT NULL COMMENT '1:difficulty=0\r\n2:专门语言比赛不推荐\r\n3.无法提交或无法查看该比赛',
  PRIMARY KEY (`cid`, `reason`) USING BTREE,
  CONSTRAINT `fk_nrc_cf_contest` FOREIGN KEY (`cid`) REFERENCES `cf_contest` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of no_recommend_contest
-- ----------------------------
INSERT INTO `no_recommend_contest` VALUES (64, 2);
INSERT INTO `no_recommend_contest` VALUES (72, 2);
INSERT INTO `no_recommend_contest` VALUES (100, 2);
INSERT INTO `no_recommend_contest` VALUES (130, 2);
INSERT INTO `no_recommend_contest` VALUES (153, 2);
INSERT INTO `no_recommend_contest` VALUES (162, 2);
INSERT INTO `no_recommend_contest` VALUES (170, 3);
INSERT INTO `no_recommend_contest` VALUES (188, 2);
INSERT INTO `no_recommend_contest` VALUES (206, 3);
INSERT INTO `no_recommend_contest` VALUES (211, 3);
INSERT INTO `no_recommend_contest` VALUES (247, 3);
INSERT INTO `no_recommend_contest` VALUES (308, 3);
INSERT INTO `no_recommend_contest` VALUES (324, 3);
INSERT INTO `no_recommend_contest` VALUES (326, 3);
INSERT INTO `no_recommend_contest` VALUES (345, 2);
INSERT INTO `no_recommend_contest` VALUES (390, 1);
INSERT INTO `no_recommend_contest` VALUES (391, 1);
INSERT INTO `no_recommend_contest` VALUES (392, 1);
INSERT INTO `no_recommend_contest` VALUES (393, 1);
INSERT INTO `no_recommend_contest` VALUES (394, 1);
INSERT INTO `no_recommend_contest` VALUES (396, 1);
INSERT INTO `no_recommend_contest` VALUES (397, 1);
INSERT INTO `no_recommend_contest` VALUES (398, 1);
INSERT INTO `no_recommend_contest` VALUES (399, 1);
INSERT INTO `no_recommend_contest` VALUES (419, 3);
INSERT INTO `no_recommend_contest` VALUES (457, 3);
INSERT INTO `no_recommend_contest` VALUES (470, 2);
INSERT INTO `no_recommend_contest` VALUES (530, 2);
INSERT INTO `no_recommend_contest` VALUES (531, 2);
INSERT INTO `no_recommend_contest` VALUES (532, 3);
INSERT INTO `no_recommend_contest` VALUES (537, 3);
INSERT INTO `no_recommend_contest` VALUES (541, 3);
INSERT INTO `no_recommend_contest` VALUES (562, 3);
INSERT INTO `no_recommend_contest` VALUES (636, 3);
INSERT INTO `no_recommend_contest` VALUES (640, 3);
INSERT INTO `no_recommend_contest` VALUES (642, 3);
INSERT INTO `no_recommend_contest` VALUES (646, 3);
INSERT INTO `no_recommend_contest` VALUES (647, 3);
INSERT INTO `no_recommend_contest` VALUES (648, 3);
INSERT INTO `no_recommend_contest` VALUES (649, 3);
INSERT INTO `no_recommend_contest` VALUES (661, 2);
INSERT INTO `no_recommend_contest` VALUES (662, 3);
INSERT INTO `no_recommend_contest` VALUES (683, 2);
INSERT INTO `no_recommend_contest` VALUES (684, 3);
INSERT INTO `no_recommend_contest` VALUES (693, 3);
INSERT INTO `no_recommend_contest` VALUES (695, 3);
INSERT INTO `no_recommend_contest` VALUES (726, 3);
INSERT INTO `no_recommend_contest` VALUES (728, 3);
INSERT INTO `no_recommend_contest` VALUES (751, 3);
INSERT INTO `no_recommend_contest` VALUES (823, 3);
INSERT INTO `no_recommend_contest` VALUES (826, 3);
INSERT INTO `no_recommend_contest` VALUES (857, 3);
INSERT INTO `no_recommend_contest` VALUES (865, 3);
INSERT INTO `no_recommend_contest` VALUES (874, 3);
INSERT INTO `no_recommend_contest` VALUES (885, 3);
INSERT INTO `no_recommend_contest` VALUES (904, 3);
INSERT INTO `no_recommend_contest` VALUES (905, 3);
INSERT INTO `no_recommend_contest` VALUES (951, 3);
INSERT INTO `no_recommend_contest` VALUES (1001, 2);
INSERT INTO `no_recommend_contest` VALUES (1002, 2);
INSERT INTO `no_recommend_contest` VALUES (1014, 3);
INSERT INTO `no_recommend_contest` VALUES (1044, 3);
INSERT INTO `no_recommend_contest` VALUES (1048, 3);
INSERT INTO `no_recommend_contest` VALUES (1049, 3);
INSERT INTO `no_recommend_contest` VALUES (1050, 3);
INSERT INTO `no_recommend_contest` VALUES (1052, 3);
INSERT INTO `no_recommend_contest` VALUES (1094, 3);
INSERT INTO `no_recommend_contest` VALUES (1112, 3);
INSERT INTO `no_recommend_contest` VALUES (1115, 2);
INSERT INTO `no_recommend_contest` VALUES (1116, 1);
INSERT INTO `no_recommend_contest` VALUES (1116, 2);
INSERT INTO `no_recommend_contest` VALUES (1145, 1);
INSERT INTO `no_recommend_contest` VALUES (1160, 1);
INSERT INTO `no_recommend_contest` VALUES (1164, 3);
INSERT INTO `no_recommend_contest` VALUES (1170, 1);
INSERT INTO `no_recommend_contest` VALUES (1170, 2);
INSERT INTO `no_recommend_contest` VALUES (1171, 3);
INSERT INTO `no_recommend_contest` VALUES (1192, 1);
INSERT INTO `no_recommend_contest` VALUES (1193, 1);
INSERT INTO `no_recommend_contest` VALUES (1211, 2);
INSERT INTO `no_recommend_contest` VALUES (1212, 3);
INSERT INTO `no_recommend_contest` VALUES (1222, 3);
INSERT INTO `no_recommend_contest` VALUES (1224, 3);
INSERT INTO `no_recommend_contest` VALUES (1226, 3);
INSERT INTO `no_recommend_contest` VALUES (1235, 3);
INSERT INTO `no_recommend_contest` VALUES (1258, 3);
INSERT INTO `no_recommend_contest` VALUES (1259, 3);
INSERT INTO `no_recommend_contest` VALUES (1273, 3);
INSERT INTO `no_recommend_contest` VALUES (1274, 3);
INSERT INTO `no_recommend_contest` VALUES (1275, 1);
INSERT INTO `no_recommend_contest` VALUES (1297, 1);
INSERT INTO `no_recommend_contest` VALUES (1297, 2);
INSERT INTO `no_recommend_contest` VALUES (1298, 3);
INSERT INTO `no_recommend_contest` VALUES (1302, 1);
INSERT INTO `no_recommend_contest` VALUES (1308, 3);
INSERT INTO `no_recommend_contest` VALUES (1309, 3);
INSERT INTO `no_recommend_contest` VALUES (1319, 3);
INSERT INTO `no_recommend_contest` VALUES (1331, 1);
INSERT INTO `no_recommend_contest` VALUES (1346, 2);
INSERT INTO `no_recommend_contest` VALUES (1347, 3);
INSERT INTO `no_recommend_contest` VALUES (1356, 1);
INSERT INTO `no_recommend_contest` VALUES (1356, 2);
INSERT INTO `no_recommend_contest` VALUES (1357, 1);
INSERT INTO `no_recommend_contest` VALUES (1357, 2);
INSERT INTO `no_recommend_contest` VALUES (1376, 3);
INSERT INTO `no_recommend_contest` VALUES (1377, 3);
INSERT INTO `no_recommend_contest` VALUES (1378, 3);
INSERT INTO `no_recommend_contest` VALUES (1410, 3);
INSERT INTO `no_recommend_contest` VALUES (1412, 3);
INSERT INTO `no_recommend_contest` VALUES (1414, 3);
INSERT INTO `no_recommend_contest` VALUES (1431, 2);
INSERT INTO `no_recommend_contest` VALUES (1432, 3);
INSERT INTO `no_recommend_contest` VALUES (1441, 3);
INSERT INTO `no_recommend_contest` VALUES (1460, 3);
INSERT INTO `no_recommend_contest` VALUES (1488, 2);
INSERT INTO `no_recommend_contest` VALUES (1489, 3);
INSERT INTO `no_recommend_contest` VALUES (1522, 3);
INSERT INTO `no_recommend_contest` VALUES (1524, 3);
INSERT INTO `no_recommend_contest` VALUES (1531, 1);
INSERT INTO `no_recommend_contest` VALUES (1532, 1);
INSERT INTO `no_recommend_contest` VALUES (1532, 2);
INSERT INTO `no_recommend_contest` VALUES (1533, 1);
INSERT INTO `no_recommend_contest` VALUES (1533, 2);
INSERT INTO `no_recommend_contest` VALUES (1544, 3);
INSERT INTO `no_recommend_contest` VALUES (1563, 3);
INSERT INTO `no_recommend_contest` VALUES (1570, 3);
INSERT INTO `no_recommend_contest` VALUES (1571, 2);
INSERT INTO `no_recommend_contest` VALUES (1576, 1);
INSERT INTO `no_recommend_contest` VALUES (1595, 3);
INSERT INTO `no_recommend_contest` VALUES (1596, 3);
INSERT INTO `no_recommend_contest` VALUES (1597, 3);
INSERT INTO `no_recommend_contest` VALUES (1639, 1);
INSERT INTO `no_recommend_contest` VALUES (1645, 3);
INSERT INTO `no_recommend_contest` VALUES (1652, 3);
INSERT INTO `no_recommend_contest` VALUES (1662, 1);
INSERT INTO `no_recommend_contest` VALUES (1663, 1);
INSERT INTO `no_recommend_contest` VALUES (1723, 3);
INSERT INTO `no_recommend_contest` VALUES (1724, 3);
INSERT INTO `no_recommend_contest` VALUES (1751, 3);
INSERT INTO `no_recommend_contest` VALUES (1752, 3);
INSERT INTO `no_recommend_contest` VALUES (1755, 3);
INSERT INTO `no_recommend_contest` VALUES (1756, 3);
INSERT INTO `no_recommend_contest` VALUES (1812, 1);
INSERT INTO `no_recommend_contest` VALUES (1812, 3);
INSERT INTO `no_recommend_contest` VALUES (1813, 3);
INSERT INTO `no_recommend_contest` VALUES (1865, 3);
INSERT INTO `no_recommend_contest` VALUES (1871, 3);
INSERT INTO `no_recommend_contest` VALUES (1880, 3);
INSERT INTO `no_recommend_contest` VALUES (1885, 1);
INSERT INTO `no_recommend_contest` VALUES (1885, 3);
INSERT INTO `no_recommend_contest` VALUES (1908, 3);
INSERT INTO `no_recommend_contest` VALUES (1910, 2);
INSERT INTO `no_recommend_contest` VALUES (1910, 3);
INSERT INTO `no_recommend_contest` VALUES (1911, 3);
INSERT INTO `no_recommend_contest` VALUES (1939, 1);
INSERT INTO `no_recommend_contest` VALUES (1940, 1);
INSERT INTO `no_recommend_contest` VALUES (1942, 1);
INSERT INTO `no_recommend_contest` VALUES (1947, 3);
INSERT INTO `no_recommend_contest` VALUES (1949, 1);
INSERT INTO `no_recommend_contest` VALUES (1950, 1);
INSERT INTO `no_recommend_contest` VALUES (1951, 1);
INSERT INTO `no_recommend_contest` VALUES (1952, 1);
INSERT INTO `no_recommend_contest` VALUES (1953, 3);
INSERT INTO `no_recommend_contest` VALUES (1954, 1);
INSERT INTO `no_recommend_contest` VALUES (1955, 1);
INSERT INTO `no_recommend_contest` VALUES (1956, 3);
INSERT INTO `no_recommend_contest` VALUES (1957, 3);
INSERT INTO `no_recommend_contest` VALUES (1958, 3);
INSERT INTO `no_recommend_contest` VALUES (1959, 3);

SET FOREIGN_KEY_CHECKS = 1;
