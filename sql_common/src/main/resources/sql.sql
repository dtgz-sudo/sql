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

 Date: 21/05/2020 15:58:51
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
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of answer
-- ----------------------------
INSERT INTO `answer` VALUES (7, 1, 1, 'mysql', 'select * from  sql_course order by Cno desc', '{\"num\":6,\"resultSet\":[{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":1,\"Cname\":\"数据库\"}]}', 70.00, '2020-05-21 08:59:36');
INSERT INTO `answer` VALUES (8, 1, 1, 'mysql', 'select * from  sql_course order by Cno asc', '{\"num\":6,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]}', 100.00, '2020-05-21 09:01:23');
INSERT INTO `answer` VALUES (9, 1, 1, 'mysql', 'select * from  sql_course order by Cno asc', '{\"num\":6,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]}', 100.00, '2020-05-21 09:15:35');
INSERT INTO `answer` VALUES (10, 1, 1, 'mysql', 'select * from  sql_course order by Cno desc', '{\"num\":6,\"resultSet\":[{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":1,\"Cname\":\"数据库\"}]}', 70.00, '2020-05-21 09:17:19');
INSERT INTO `answer` VALUES (11, 1, 1, 'mysql', 'select * from  sql_course', '{\"num\":6,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]}', 100.00, '2020-05-21 09:17:30');
INSERT INTO `answer` VALUES (12, 52, 1, 'mysql', 'SELECT Cname FROM sql_course where Cno = 2', '{\"num\":1,\"resultSet\":[{\"Cname\":\"数学\"}]}', 70.00, '2020-05-21 09:23:50');
INSERT INTO `answer` VALUES (13, 52, 1, 'mysql', 'SELECT * FROM sql_course where Cno = 1', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"}]}', 70.00, '2020-05-21 09:24:47');
INSERT INTO `answer` VALUES (14, 52, 1, 'mysql', 'SELECT name FROM sql_course where Cno = 1', NULL, 0.00, '2020-05-21 09:24:55');
INSERT INTO `answer` VALUES (15, 52, 1, 'mysql', 'SELECT cname FROM sql_course where Cno = 1', '{\"num\":1,\"resultSet\":[{\"Cname\":\"数据库\"}]}', 100.00, '2020-05-21 09:25:01');
INSERT INTO `answer` VALUES (19, 55, 1, 'mysql', 'select * from sql_course', NULL, 0.00, '2020-05-21 15:06:07');
INSERT INTO `answer` VALUES (20, 55, 1, 'mysql', 'select * from sql_course', NULL, 0.00, '2020-05-21 15:06:17');
INSERT INTO `answer` VALUES (21, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:02');
INSERT INTO `answer` VALUES (22, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:02');
INSERT INTO `answer` VALUES (23, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:12:56');
INSERT INTO `answer` VALUES (24, 55, 1, 'mysql', 'INSERT INTO sql.sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:18');
INSERT INTO `answer` VALUES (25, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:01');
INSERT INTO `answer` VALUES (26, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:02');
INSERT INTO `answer` VALUES (27, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:13:02');
INSERT INTO `answer` VALUES (28, 55, 1, 'mysql', 'INSERT INTO sql.sql_course(Cname,Cno) VALUES(\"软件工程\",520)', NULL, 0.00, '2020-05-21 15:14:19');
INSERT INTO `answer` VALUES (29, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', 100.00, '2020-05-21 15:44:08');
INSERT INTO `answer` VALUES (30, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', 100.00, '2020-05-21 15:46:38');
INSERT INTO `answer` VALUES (31, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', 100.00, '2020-05-21 15:48:09');
INSERT INTO `answer` VALUES (32, 55, 1, 'mysql', 'INSERT INTO sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', 100.00, '2020-05-21 15:48:11');
INSERT INTO `answer` VALUES (33, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno，Cname) VALUES(520,\"软件工程\")', NULL, 0.00, '2020-05-21 15:48:35');
INSERT INTO `answer` VALUES (34, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno,Cname) VALUES(520,\"软件工程\")', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', 100.00, '2020-05-21 15:49:20');
INSERT INTO `answer` VALUES (35, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno,Cname) VALUES(521,\"软件工程\")', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":521,\"Cname\":\"软件工程\"}]}', 0.00, '2020-05-21 15:51:09');
INSERT INTO `answer` VALUES (36, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno,Cname) VALUES(521,\"软件工程\")', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":521,\"Cname\":\"软件工程\"}]}', 0.00, '2020-05-21 15:51:20');
INSERT INTO `answer` VALUES (37, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno,Cname) VALUES(521,\"软件工程\")', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":521,\"Cname\":\"软件工程\"}]}', 0.00, '2020-05-21 15:52:36');
INSERT INTO `answer` VALUES (38, 55, 1, 'mysql', 'INSERT INTO sql_course(Cno,Cname) VALUES(521,\"软件工程\")', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":521,\"Cname\":\"软件工程\"}]}', 70.00, '2020-05-21 15:56:00');

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
INSERT INTO `category` VALUES (1, '数据操纵实验', '2020-05-10 22:18:04', '2021-01-01 22:18:07');
INSERT INTO `category` VALUES (2, '事务控制实验', '2020-05-10 22:18:17', '2029-01-10 22:18:19');
INSERT INTO `category` VALUES (3, '事务实验', '2020-05-10 22:18:33', '2033-12-31 22:18:36');

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
  `tablename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '操作的数据库',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of problem
-- ----------------------------
INSERT INTO `problem` VALUES (1, '查询全部数据', '\n查询出此数据库的全部数据   CREATE TABLE `sql_course` (\n  `Cno` int(11) NOT NULL,\n  `Cname` varchar(10) COLLATE utf8_bin DEFAULT NULL,\n  PRIMARY KEY (`Cno`)\n) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;', 'select * from  sql_course order by Cno asc', '{\"num\":6,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"}]}', '默认升序排列', '2020-05-21 10:14:08', 'mysql', 1, '不告诉你', '自己研究', 'sql_course');
INSERT INTO `problem` VALUES (52, '查询出课程名字', '表名:sql_course\n课程名字：Cname\n课程ID：Cno\n\n要求查询出Cno=1的课程名字', 'SELECT Cname FROM sql_course where Cno = 1', '{\"num\":1,\"resultSet\":[{\"Cname\":\"数据库\"}]}', NULL, '2020-05-21 10:14:11', 'mysql', 1, 'select xxx from xxx  (xxx)', 'xxx', 'sql_course');
INSERT INTO `problem` VALUES (55, '新增课程', '新增一条课程记录\nCno:课程ID\nCname:课程名称\n\n要求插入一条数据：\nCno:520\nname:软件工程', 'INSERT INTO sql.sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', '无', '2020-05-21 11:35:14', 'mysql', 1, 'xxx', 'xxx', 'sql_course');
INSERT INTO `problem` VALUES (56, '新增课程', '新增一条课程记录\nCno:课程ID\nCname:课程名称\n\n要求插入一条数据：\nCno:520\nname:软件工程', 'INSERT INTO sql.sql_course(Cname,Cno) VALUES(\"软件工程\",520)', '{\"num\":1,\"resultSet\":[{\"Cno\":1,\"Cname\":\"数据库\"},{\"Cno\":2,\"Cname\":\"数学\"},{\"Cno\":3,\"Cname\":\"信息系统\"},{\"Cno\":4,\"Cname\":\"操作系统\"},{\"Cno\":5,\"Cname\":\"数据结构\"},{\"Cno\":6,\"Cname\":\"离散数学\"},{\"Cno\":520,\"Cname\":\"软件工程\"}]}', '无', '2020-05-21 15:29:21', 'mysql', 1, 'xss', 'xxx', 'sql_course');

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
INSERT INTO `sql_course` VALUES (2, '数学');
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
INSERT INTO `sql_student` VALUES ('201215125', '张立', '男', 19);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sid` int(20) NOT NULL AUTO_INCREMENT COMMENT '学号',
  `nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '电子邮箱',
  `createDate` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `permission` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生权限',
  `tid` int(20) NULL DEFAULT NULL COMMENT '所属教师工号',
  PRIMARY KEY (`sid`, `nickname`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, 'admin', 'admin', 'admin@gmail.com', '2020-05-18 09:10:32', 'ROLE_STU', 1);
INSERT INTO `student` VALUES (2, '吴', '123', '123@', '2020-05-18 16:35:46', 'ROLE_STU', 1);
INSERT INTO `student` VALUES (3, '熊大', '123456', 'xd@gamil.com', '2020-05-20 14:29:20', 'ROLE_STU', 1);

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
