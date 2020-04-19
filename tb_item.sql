/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : fastkill

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 19/04/2020 21:11:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `item_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `item_stock` int(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `item_code_index`(`item_code`) USING BTREE COMMENT '商品code'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES (1, 'item_001', '惜玥洗面奶', 69.00, 0);
INSERT INTO `tb_item` VALUES (2, 'item_002', '亚瑟士跑鞋', 720.00, 100);

SET FOREIGN_KEY_CHECKS = 1;
