/*
 Navicat Premium Data Transfer

 Source Server         : dtgz.xyz
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : 47.95.204.207:3306
 Source Schema         : sql

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 10/05/2020 22:17:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for answer
-- ----------------------------
DROP TABLE IF EXISTS `answer`;
CREATE TABLE `answer`  (
  `aid` int(20) NOT NULL AUTO_INCREMENT COMMENT '学生输入的答案主键',
  `pid` int(20) NOT NULL COMMENT '回答的题目',
  `sid` int(20) NOT NULL COMMENT '回答的学生编号',
  `Language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '数据库语言',
  `input` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生输入的SQL语句',
  `output` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '学生输入的SQL语句返回的语句',
  `score` double(5, 2) NULL DEFAULT NULL COMMENT '成绩',
  `createDate` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '答题的时间',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` int(20) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `categoryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '问题分类的名字',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '数据操纵实验', NULL, NULL);
INSERT INTO `category` VALUES (2, '事务控制实验', NULL, NULL);
INSERT INTO `category` VALUES (3, '事务实验', NULL, NULL);

-- ----------------------------
-- Table structure for problem
-- ----------------------------
DROP TABLE IF EXISTS `problem`;
CREATE TABLE `problem`  (
  `pid` int(20) NOT NULL AUTO_INCREMENT COMMENT '题目的主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标题',
  `Description` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '题目表述',
  `input` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '老师发布的SQL答案',
  `output` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '标准答案',
  `hint` varchar(5000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '提示',
  `createDate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '发布的老师',
  `Language` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '对应的数据库',
  `cid` int(20) NULL DEFAULT NULL COMMENT '对应的分类',
  `exampleinput` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '输入的例子',
  `exampleoutput` varchar(1000) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '输出的例子',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (5, '测试添加', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', '默认升序', '2020-05-10 22:09:35', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (6, '测试删除', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-10 21:55:49', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (7, '测试修改', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-10 21:56:06', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (8, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (9, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (10, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (11, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (12, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (13, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (14, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (15, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (16, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 1, NULL, NULL);
INSERT INTO `problem` VALUES (17, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (18, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (19, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (20, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (21, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (22, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (23, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (24, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (25, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (26, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (27, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 2, NULL, NULL);
INSERT INTO `problem` VALUES (28, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (29, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (30, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (31, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (32, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (33, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (34, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (35, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (36, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (37, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (38, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (39, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (40, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);
INSERT INTO `problem` VALUES (41, '测试', '查询全部数据', 'SELECT * FROM sql_course', '[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]', NULL, '2020-05-04 12:18:59', 'mysql', 3, NULL, NULL);

-- ----------------------------
-- Table structure for sql_course
-- ----------------------------
DROP TABLE IF EXISTS `sql_course`;
CREATE TABLE `sql_course`  (
  `Cno` int(11) NOT NULL,
  `Cname` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`Cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sql_course
-- ----------------------------
INSERT INTO `sql_course` VALUES (1, '数据库');
INSERT INTO `sql_course` VALUES (3, '信息系统');
INSERT INTO `sql_course` VALUES (4, '操作系统');
INSERT INTO `sql_course` VALUES (5, '数据结构');
INSERT INTO `sql_course` VALUES (6, '离散数学');

-- ----------------------------
-- Table structure for sql_sc
-- ----------------------------
DROP TABLE IF EXISTS `sql_sc`;
CREATE TABLE `sql_sc`  (
  `Sno` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Cno` int(11) NULL DEFAULT NULL,
  `Grade` int(255) NULL DEFAULT NULL,
  INDEX `fk_sc_student`(`Sno`) USING BTREE,
  INDEX `fk_sc_course`(`Cno`) USING BTREE,
  CONSTRAINT `fk_sc_course` FOREIGN KEY (`Cno`) REFERENCES `sql_course` (`Cno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sc_student` FOREIGN KEY (`Sno`) REFERENCES `sql_student` (`Sno`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sql_sc
-- ----------------------------
INSERT INTO `sql_sc` VALUES ('201215121', 3, 88);
INSERT INTO `sql_sc` VALUES ('201215122', 3, 75);
INSERT INTO `sql_sc` VALUES ('201215123', 3, 86);
INSERT INTO `sql_sc` VALUES ('201215125', 3, 78);

-- ----------------------------
-- Table structure for sql_student
-- ----------------------------
DROP TABLE IF EXISTS `sql_student`;
CREATE TABLE `sql_student`  (
  `Sno` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Sname` varchar(5) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Ssex` varchar(2) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `Sage` smallint(6) NULL DEFAULT NULL,
  PRIMARY KEY (`Sno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sql_student
-- ----------------------------
INSERT INTO `sql_student` VALUES ('201215121', '李勇', '男', 20);
INSERT INTO `sql_student` VALUES ('201215122', '刘晨', '女', 19);
INSERT INTO `sql_student` VALUES ('201215123', '王敏', '女', 18);
INSERT INTO `sql_student` VALUES ('201215125', '张立', '男', 19);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(20) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `createDate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生权限',
  PRIMARY KEY (`sid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'admin', 'admin', 'admin@gmail.com', '2020-05-10 18:19:50', 'ROLE_STU');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `tid` int(20) NOT NULL AUTO_INCREMENT COMMENT '工号',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `createDate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '教师的权限',
  PRIMARY KEY (`tid`, `nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, 'admin', 'admin', 'admin@gmail.com', '2020-05-04 10:11:50', 'ROLE_ADMIN');

SET FOREIGN_KEY_CHECKS = 1;
