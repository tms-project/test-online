/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : test_online_system

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 18/04/2019 23:29:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tos_admin
-- ----------------------------
DROP TABLE IF EXISTS `tos_admin`;
CREATE TABLE `tos_admin`  (
  `adid` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `adname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardnumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tos_admin
-- ----------------------------
INSERT INTO `tos_admin` VALUES ('123', '大大佬', '男', '123456789', '123', '123456789');

-- ----------------------------
-- Table structure for tos_choicequestion
-- ----------------------------
DROP TABLE IF EXISTS `tos_choicequestion`;
CREATE TABLE `tos_choicequestion`  (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `cquestion` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `choicea` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choiceb` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choicec` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `choiced` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `canswer` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tos_choicequestion
-- ----------------------------
INSERT INTO `tos_choicequestion` VALUES (1, '1+1=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (2, '1+2=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (3, '1+3=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (4, '1+4=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (5, '1+5=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (6, '1+6=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (7, '1+7=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (8, '1+8=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (9, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (10, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (11, '1+1=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (12, '1+2=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (13, '1+3=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (14, '1+4=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (15, '1+5=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (16, '1+6=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (17, '1+7=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (18, '1+8=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (19, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (20, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (21, '1+1=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (22, '1+2=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (23, '1+3=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (24, '1+4=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (25, '1+5=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (26, '1+6=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (27, '1+7=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (28, '1+8=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (29, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (30, '1+9=', '2', '3', '4', '5', 'A');
INSERT INTO `tos_choicequestion` VALUES (31, '0123_56789,差什么？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (32, '123_56789,差什么啊？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (33, '012345_7890,差什么啊？', '6', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (34, '0123_56789,差什么？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (35, '123_56789,差什么啊？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (36, '012345_7890,差什么啊？', '6', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (37, '0123_56789,差什么？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (38, '123_56789,差什么啊？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (39, '012345_7890,差什么啊？', '6', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (40, '0123_56789,差什么？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (41, '123_56789,差什么啊？', '4', NULL, NULL, NULL, NULL);
INSERT INTO `tos_choicequestion` VALUES (42, '012345_7890,差什么啊？', '6', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tos_fillquestion
-- ----------------------------
DROP TABLE IF EXISTS `tos_fillquestion`;
CREATE TABLE `tos_fillquestion`  (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fquestion` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `fanswer` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tos_fillquestion
-- ----------------------------
INSERT INTO `tos_fillquestion` VALUES (1, '0123_56789,差什么？', '4');
INSERT INTO `tos_fillquestion` VALUES (2, '123_56789,差什么啊？', '4');
INSERT INTO `tos_fillquestion` VALUES (7, '012345_7890,差什么啊？', '6');
INSERT INTO `tos_fillquestion` VALUES (10, '0123_56789,差什么？', '4');
INSERT INTO `tos_fillquestion` VALUES (11, '123_56789,差什么啊？', '4');
INSERT INTO `tos_fillquestion` VALUES (12, '012345_7890,差什么啊？', '6');

-- ----------------------------
-- Table structure for tos_student
-- ----------------------------
DROP TABLE IF EXISTS `tos_student`;
CREATE TABLE `tos_student`  (
  `sid` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardnumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tos_student
-- ----------------------------
INSERT INTO `tos_student` VALUES ('123', 'cai', '男', '2505', '123', 'sh', '2016');
INSERT INTO `tos_student` VALUES ('1234', 'chen', NULL, NULL, '1234', NULL, '123456789');

-- ----------------------------
-- Table structure for tos_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tos_teacher`;
CREATE TABLE `tos_teacher`  (
  `tid` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tname` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cardnumber` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`tid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tos_teacher
-- ----------------------------
INSERT INTO `tos_teacher` VALUES ('1', 'chen', '男', '2505', '123', 'sh', '2016');
INSERT INTO `tos_teacher` VALUES ('2', 'chen', '男', '2505', '123', 'sh', '2016');
INSERT INTO `tos_teacher` VALUES ('4', 'cai', '男', '2505', '123', 'sh', '2016');

SET FOREIGN_KEY_CHECKS = 1;
