-- MySQL dump 10.13  Distrib 8.0.25, for macos11.3 (x86_64)
--
-- Host: 127.0.0.1    Database: fm_cems
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bus`
--

DROP TABLE IF EXISTS `bus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bus` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `place_name` varchar(255) NOT NULL COMMENT '地点名称',
  `time` bigint NOT NULL COMMENT '班车时间',
  `arrangement` varchar(255) DEFAULT NULL COMMENT '车辆安排',
  `comment` varchar(255) DEFAULT NULL COMMENT '行程备注',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bus`
--

LOCK TABLES `bus` WRITE;
/*!40000 ALTER TABLE `bus` DISABLE KEYS */;
INSERT INTO `bus` VALUES (16,'市北车站',1622550480000,'2辆大巴','从东赛场往返市北车站','2021-05-13 20:28:51.227','2021-05-13 20:28:51.227'),(17,'城南车站',1621820108118,'3辆20座大巴','往返赛场与城南','2021-05-24 09:35:28.881','2021-05-24 09:35:28.881'),(18,'城市机场',1621820256827,'1辆2座大巴','往返城市机场与东赛场','2021-05-24 09:38:00.123','2021-05-24 09:38:00.123'),(19,'城市机场',1621820284018,'1辆20座大巴','往返城市机场与西赛场','2021-05-24 09:38:22.432','2021-05-24 09:38:22.432'),(20,'格林豪泰酒店',1621824000000,'3辆大巴','往返格林豪泰酒店与西赛场','2021-05-24 09:39:16.854','2021-05-24 09:39:16.854'),(21,'东赛场',1621823400000,'3辆30座大巴','往返东赛场与城市机场','2021-05-24 09:48:40.472','2021-05-24 09:48:40.472'),(22,'南赛场',1621820922503,'3辆30座大巴','往返南赛场与城市客运中心','2021-05-24 09:49:04.508','2021-05-24 09:49:04.508'),(23,'西赛场',1621820948829,'2辆30座大巴','往返西赛场与城市客运中心','2021-05-24 09:49:28.736','2021-05-24 09:49:28.736');
/*!40000 ALTER TABLE `bus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `competition_info`
--

DROP TABLE IF EXISTS `competition_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `competition_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '赛项id\n',
  `name` varchar(255) NOT NULL COMMENT '赛项名称',
  `start_time` bigint NOT NULL COMMENT '比赛开始时间',
  `end_time` bigint NOT NULL COMMENT '比赛结束时间',
  `place_id` bigint NOT NULL COMMENT '比赛场地id',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='赛项表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `competition_info`
--

LOCK TABLES `competition_info` WRITE;
/*!40000 ALTER TABLE `competition_info` DISABLE KEYS */;
INSERT INTO `competition_info` VALUES (1,'机器人足球赛',1620180000000,1620187200000,1,'2021-05-05 09:59:48.981','2021-05-05 09:59:48.981'),(2,'机器人竞速赛',1620180000000,1620187200000,2,'2021-05-05 09:59:59.740','2021-05-05 09:59:59.740'),(3,'机器人服务赛',1620180000000,1620187200000,3,'2021-05-05 10:00:03.465','2021-05-05 10:00:03.465'),(4,'光电比赛',1620884342201,1620884342201,6,'2021-05-13 13:47:10.353','2021-05-13 13:47:10.353'),(5,'机器人拳击赛',1620905520000,1620905520000,2,'2021-05-13 19:32:12.010','2021-05-13 19:32:12.010'),(6,'水中探物赛',1620994800000,1621081200000,3,'2021-05-13 20:21:06.908','2021-05-13 20:21:06.908'),(8,'家庭服务机器人赛-实物',1621816321000,1621819921264,2,'2021-05-24 09:32:27.569','2021-05-24 09:32:27.569'),(9,'家庭仿真比赛',1621823400000,1621827151000,2,'2021-05-24 09:33:08.549','2021-05-24 09:33:08.549'),(10,'水中救援赛',1621906391000,1621909991000,1,'2021-05-24 09:33:41.803','2021-05-24 09:33:41.803'),(11,'2D机器人赛',1621913624000,1621917224000,3,'2021-05-24 09:34:17.964','2021-05-24 09:34:17.964'),(12,'3D足球赛',1622165677000,1622172877000,2,'2021-05-24 09:34:58.141','2021-05-24 09:34:58.141'),(13,'2D救援机器人赛',1621850400000,1621857600000,2,'2021-05-24 21:31:18.944','2021-05-24 21:31:18.944'),(14,'机器人搏击半决赛',1622023200000,1622030400000,1,'2021-05-25 20:23:30.600','2021-05-25 20:23:30.600');
/*!40000 ALTER TABLE `competition_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `healthyInfo`
--

DROP TABLE IF EXISTS `healthyInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `healthyInfo` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id\n',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `temperature` double NOT NULL COMMENT '体温',
  `is_cough` tinyint(1) NOT NULL COMMENT '是否咳嗽',
  `is_history` tinyint(1) DEFAULT NULL COMMENT '是否有患病史',
  `is_touch` tinyint(1) NOT NULL COMMENT '是否有接触史',
  `time` mediumtext NOT NULL COMMENT '登记时间',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='健康信息上报表\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthyInfo`
--

LOCK TABLES `healthyInfo` WRITE;
/*!40000 ALTER TABLE `healthyInfo` DISABLE KEYS */;
INSERT INTO `healthyInfo` VALUES (1,1,36.7,0,0,0,'1620106105000','2021-05-04 13:24:31.605','2021-05-04 13:24:31.605'),(2,5,36.7,0,0,0,'1620106105000','2021-05-04 13:24:41.754','2021-05-04 13:24:41.754'),(3,3,38,0,0,1,'1620106530162','2021-05-04 13:35:30.528','2021-05-04 13:35:30.528'),(4,4,38,0,0,1,'1620106535185','2021-05-04 13:35:35.186','2021-05-04 13:35:35.186'),(6,1,36.2,0,0,0,'1620632088201','2021-05-10 15:34:48.202','2021-05-10 15:34:48.202'),(7,5,36.6,1,0,0,'1620632135053','2021-05-10 15:35:35.446','2021-05-10 15:35:35.446'),(9,1,37,0,0,0,'1621517333253','2021-05-20 21:28:53.254','2021-05-20 21:28:53.254'),(10,1,37.3,1,0,0,'1621738281900','2021-05-23 10:51:21.902','2021-05-23 10:51:21.902'),(11,1,37.1,0,1,0,'1621738642358','2021-05-23 10:57:22.359','2021-05-23 10:57:22.359'),(12,1,36.9,0,1,0,'1621738704141','2021-05-23 10:58:24.142','2021-05-23 10:58:24.142'),(13,3,37.4,0,0,0,'1621773452695','2021-05-23 20:37:32.696','2021-05-23 20:37:32.696'),(14,3,36.9,1,0,0,'1621773493840','2021-05-23 20:38:13.840','2021-05-23 20:38:13.840'),(15,3,36.7,0,0,0,'1621774029385','2021-05-23 20:47:09.385','2021-05-23 20:47:09.385'),(16,11,37.1,0,0,0,'1621774221726','2021-05-23 20:50:21.726','2021-05-23 20:50:21.726'),(17,18,36,0,0,1,'1621774662588','2021-05-23 20:57:42.588','2021-05-23 20:57:42.588'),(18,18,36.9,1,0,0,'1621819005207','2021-05-24 09:16:45.207','2021-05-24 09:16:45.207'),(19,1,37,0,0,1,'1621945284665','2021-05-25 20:21:24.666','2021-05-25 20:21:24.666');
/*!40000 ALTER TABLE `healthyInfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `notice` varchar(255) NOT NULL,
  `time` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'来自安徽大学的李亚州健康状态发生异常，请管理人员及时处理突发状况!',1621738620407),(2,'来自安徽大学的李亚州健康状态发生异常，请管理人员及时处理突发状况!',1621738704152),(3,'来自安徽大学的方勇健康状态发生异常，请管理人员及时处理突发状况!',1621773493846),(4,'来自东南大学的谢思宇健康状态发生异常，请管理人员及时处理突发状况!',1621774662595),(5,'来自东南大学的谢思宇健康状态发生异常，请管理人员及时处理突发状况!',1621819005212),(6,'来自安徽大学的李亚州健康状态发生异常，请管理人员及时处理突发状况!',1621945284684);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `organize_arrangement`
--

DROP TABLE IF EXISTS `organize_arrangement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organize_arrangement` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `userId` bigint NOT NULL COMMENT '组织人员id',
  `place_id` bigint NOT NULL COMMENT '场地id',
  `start_time` bigint DEFAULT NULL COMMENT '组织活动开始时间',
  `end_time` bigint NOT NULL COMMENT '组织活动结束时间',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='组织活动安排';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `organize_arrangement`
--

LOCK TABLES `organize_arrangement` WRITE;
/*!40000 ALTER TABLE `organize_arrangement` DISABLE KEYS */;
INSERT INTO `organize_arrangement` VALUES (1,15,2,1621675399000,1621682599129,'2021-05-22 19:23:28.619','2021-05-22 19:23:28.619'),(6,15,6,1620215836000,1621684636653,'2021-05-22 19:57:26.010','2021-05-22 19:57:26.010'),(7,16,3,1620907048000,1621684648928,'2021-05-22 19:57:36.253','2021-05-22 19:57:36.253'),(8,21,6,1621814439000,1621821039172,'2021-05-24 09:50:59.461','2021-05-24 09:50:59.461'),(9,21,3,1621908000000,1621915200000,'2021-05-24 09:51:28.672','2021-05-24 09:51:28.672'),(10,15,6,1621731600000,1621738800000,'2021-05-24 09:52:06.012','2021-05-24 09:52:06.012'),(11,16,2,1621990800000,1621825200000,'2021-05-24 09:52:47.572','2021-05-24 09:52:47.572');
/*!40000 ALTER TABLE `organize_arrangement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participants_info`
--

DROP TABLE IF EXISTS `participants_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participants_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` bigint unsigned NOT NULL COMMENT '用户id',
  `name` varchar(255) NOT NULL COMMENT '用户姓名',
  `unit` varchar(255) NOT NULL COMMENT '所属单位',
  `id_number` varchar(255) NOT NULL COMMENT '身份证号',
  `come_from` varchar(255) NOT NULL COMMENT '来源地',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ID_number` (`id_number`),
  UNIQUE KEY `participants_info_user_id_uindex` (`user_id`),
  UNIQUE KEY `participants_info_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参赛人员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participants_info`
--

LOCK TABLES `participants_info` WRITE;
/*!40000 ALTER TABLE `participants_info` DISABLE KEYS */;
INSERT INTO `participants_info` VALUES (2,1,'李亚州','安徽大学','342921199811062712','广州','2021-04-22 23:30:02.974','2021-04-22 23:30:02.974'),(1,3,'方勇','安徽大学','342921199811062711','安徽','2021-04-22 23:29:31.062','2021-04-22 23:29:31.062'),(3,4,'陈欣','北京大学','342921199811062722','北京','2021-05-03 22:28:50.129','2021-05-03 22:28:50.129'),(5,5,'方伦','西安电子科技大学','342921199811062732','西安','2021-05-03 22:32:01.662','2021-05-03 22:32:01.662'),(6,10,'文伟','安徽大学','342921199811062733','广西','2021-05-10 10:33:02.836','2021-05-10 10:33:02.836'),(7,11,'高云庆','安徽大学','342921199812062722','河南','2021-05-10 10:35:03.506','2021-05-10 10:35:03.506'),(8,12,'紫川清明','安徽大学赛事委员会','34292119980507271X','安徽','2021-05-18 13:12:03.774','2021-05-18 13:12:03.774'),(11,15,'高庆','安徽大学志愿者中心','342921199812062729','云南','2021-05-22 19:12:56.139','2021-05-22 19:12:56.139'),(12,16,'梓潼','安徽大学志愿者中心','342921199812062770','上海','2021-05-22 19:44:56.079','2021-05-22 19:44:56.079'),(14,18,'谢思宇','东南大学','34199119990408271X','安徽','2021-05-22 20:27:43.376','2021-05-22 20:27:43.376'),(15,19,'鲍峰','东南大学','31199119960203271','广东','2021-05-22 20:33:46.762','2021-05-22 20:33:46.762'),(16,20,'诸葛锋','浙江大学','3419970601271X','安徽','2021-05-23 20:22:25.518','2021-05-23 20:22:25.518'),(17,21,'许彬','安徽大学志愿者中心','34192119990708211','贵州','2021-05-24 09:50:32.886','2021-05-24 09:50:32.886'),(18,22,'诸葛平','安徽大学','34212119960408291','浙江','2021-05-25 20:20:13.266','2021-05-25 20:20:13.266');
/*!40000 ALTER TABLE `participants_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `place_info`
--

DROP TABLE IF EXISTS `place_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `place_info` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '场地id\n',
  `name` varchar(255) NOT NULL COMMENT '场地名称',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`),
  UNIQUE KEY `place_info_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='场地表信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `place_info`
--

LOCK TABLES `place_info` WRITE;
/*!40000 ALTER TABLE `place_info` DISABLE KEYS */;
INSERT INTO `place_info` VALUES (1,'东赛场','2021-05-05 09:57:09.145','2021-05-05 09:57:09.145'),(2,'西赛场','2021-05-05 09:57:15.570','2021-05-05 09:57:15.570'),(3,'南赛场','2021-05-05 09:57:21.102','2021-05-05 09:57:21.102'),(6,'北赛场','2021-05-10 16:03:14.711','2021-05-10 16:03:14.711');
/*!40000 ALTER TABLE `place_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registry`
--

DROP TABLE IF EXISTS `registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registry` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增id\n',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `competition_id` bigint NOT NULL COMMENT '赛项id',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报名表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registry`
--

LOCK TABLES `registry` WRITE;
/*!40000 ALTER TABLE `registry` DISABLE KEYS */;
INSERT INTO `registry` VALUES (1,1,2,'2021-05-05 10:15:50.603','2021-05-05 10:15:50.603'),(2,3,1,'2021-05-05 10:15:57.711','2021-05-05 10:15:57.711'),(3,4,2,'2021-05-05 10:16:04.496','2021-05-05 10:16:04.496'),(5,1,3,'2021-05-21 09:35:25.601','2021-05-21 09:35:25.601'),(12,1,6,'2021-05-24 09:35:49.314','2021-05-24 09:35:49.314'),(13,3,6,'2021-05-24 09:35:55.862','2021-05-24 09:35:55.862'),(14,3,8,'2021-05-24 09:36:00.047','2021-05-24 09:36:00.047'),(15,4,9,'2021-05-24 09:37:08.865','2021-05-24 09:37:08.865'),(16,4,1,'2021-05-24 09:37:13.060','2021-05-24 09:37:13.060'),(17,4,2,'2021-05-24 09:37:16.627','2021-05-24 09:37:16.627'),(18,4,3,'2021-05-24 09:37:21.080','2021-05-24 09:37:21.080'),(19,4,4,'2021-05-24 09:37:25.824','2021-05-24 09:37:25.824'),(31,18,1,'2021-05-25 15:59:51.527','2021-05-25 15:59:51.527'),(35,18,9,'2021-05-25 16:23:26.028','2021-05-25 16:23:26.028'),(36,22,1,'2021-05-26 12:45:35.388','2021-05-26 12:45:35.388');
/*!40000 ALTER TABLE `registry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone_number` varchar(20) NOT NULL COMMENT '电话号码',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `type` tinyint unsigned NOT NULL COMMENT '用户类型',
  `dbctime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
  `dbutime` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone_number_uindex` (`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'12345678','jim123',1,'2021-03-05 19:30:15.076','2021-03-05 19:30:15.076'),(3,'18992333112','123456',1,'2021-03-14 17:19:52.845','2021-03-14 17:19:52.845'),(4,'18992333113','11110',1,'2021-05-03 12:17:36.389','2021-05-03 12:17:36.389'),(5,'18992333114','11111',1,'2021-05-03 12:17:46.254','2021-05-03 12:17:46.254'),(6,'18992333115','11113',2,'2021-05-03 12:17:53.493','2021-05-03 12:17:53.493'),(7,'18992333116','11114',1,'2021-05-03 12:18:01.516','2021-05-03 12:18:01.516'),(8,'18992333117','11115',1,'2021-05-03 12:18:06.488','2021-05-03 12:18:06.488'),(9,'18992333118','11116',1,'2021-05-03 12:18:12.320','2021-05-03 12:18:12.320'),(10,'18992333119','1111111',1,'2021-05-10 10:33:02.808','2021-05-10 10:33:02.808'),(11,'18992333120','222222',1,'2021-05-10 10:35:03.498','2021-05-10 10:35:03.498'),(12,'18255555555','123456',3,'2021-05-18 13:11:08.342','2021-05-18 13:11:08.342'),(15,'18992333111','123456',2,'2021-05-22 19:12:56.130','2021-05-22 19:12:56.130'),(16,'123456789','123456',3,'2021-05-22 19:44:56.073','2021-05-22 19:44:56.073'),(18,'18922211111','123456',1,'2021-05-22 20:27:43.364','2021-05-22 20:27:43.364'),(19,'18955550908','123456',1,'2021-05-22 20:33:46.755','2021-05-22 20:33:46.755'),(20,'13955608968','123456',1,'2021-05-23 20:22:25.499','2021-05-23 20:22:25.499'),(21,'18722291312','123456',2,'2021-05-24 09:50:32.884','2021-05-24 09:50:32.884'),(22,'18297956220','123456',1,'2021-05-25 20:20:13.248','2021-05-25 20:20:13.248');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warn_info`
--

DROP TABLE IF EXISTS `warn_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `warn_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `is_cough` tinyint(1) NOT NULL,
  `is_history` tinyint(1) NOT NULL,
  `is_touch` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `warn_info_user_id_uindex` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warn_info`
--

LOCK TABLES `warn_info` WRITE;
/*!40000 ALTER TABLE `warn_info` DISABLE KEYS */;
INSERT INTO `warn_info` VALUES (2,3,1,0,0),(4,18,1,0,0);
/*!40000 ALTER TABLE `warn_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-02 23:14:07
