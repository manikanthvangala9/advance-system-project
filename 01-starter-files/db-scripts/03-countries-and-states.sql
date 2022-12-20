USE `full-stack-ecommerce`;

SET foreign_key_checks = 0;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;

CREATE TABLE `country` (
  `id` smallint unsigned NOT NULL,
  `code` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

--
-- Data for table `country`
--

INSERT INTO `country` VALUES 
(1,'IN','India'),
(2,'US','United States');

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `country_id` smallint unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_country` (`country_id`),
  CONSTRAINT `fk_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1;

--
-- Dumping data for table `state`
--

INSERT INTO `state` VALUES 
(1,'Andhra Pradesh',1),
(2,'Arunachal Pradesh',1),
(3,'Assam',1),
(4,'Bihar',1),
(5,'Chhattisgarh',1),
(6,'Goa',1),
(7,'Gujarat',1),
(8,'Haryana',1),
(9,'Himachal Pradesh',1),
(10,'Jammu & Kashmir',1),
(11,'Jharkhand',1),
(12,'Karnataka',1),
(13,'Kerala',1),
(14,'Madhya Pradesh',1),
(15,'Maharashtra',1),
(16,'Manipur',1),
(17,'Meghalaya',1),
(18,'Mizoram',1),
(19,'Nagaland',1),
(20,'Odisha',1),
(21,'Punjab',1),
(22,'Rajasthan',1),
(23,'Sikkim',1),
(24,'Tamil Nadu',1),
(25,'Telangana',1),
(26,'Tripura',1),
(27,'Uttar Pradesh',1),
(28,'Uttarakhand',1),
(29,'West Bengal',1),
(30,'Andaman and Nicobar Islands',1),
(31,'Chandigarh',1),
(32,'Dadra and Nagar Haveli',1),
(33,'Daman & Diu',1),
(34,'Lakshadweep',1),
(35,'Puducherry',1),
(36,'The Government of NCT of Delhi',1),
(37,'Alabama',2),
(38,'Alaska',2),
(39,'Arizona',2),
(40,'Arkansas',2),
(41,'California',2),
(42,'Colorado',2),
(43,'Connecticut',2),
(44,'Delaware',2),
(45,'District Of Columbia',2),
(46,'Florida',2),
(47,'Georgia',2),
(48,'Hawaii',2),
(49,'Idaho',2),
(50,'Illinois',2),
(51,'Indiana',2),
(52,'Iowa',2),
(53,'Kansas',2),
(54,'Kentucky',2),
(55,'Louisiana',2),
(56,'Maine',2),
(57,'Maryland',2),
(58,'Massachusetts',2),
(59,'Michigan',2),
(60,'Minnesota',2),
(61,'Mississippi',2),
(62,'Missouri',2),
(63,'Montana',2),
(64,'Nebraska',2),
(65,'Nevada',2),
(66,'New Hampshire',2),
(67,'New Jersey',2),
(68,'New Mexico',2),
(69,'New York',2),
(70,'North Carolina',2),
(71,'North Dakota',2),
(72,'Ohio',2),
(73,'Oklahoma',2),
(74,'Oregon',2),
(75,'Pennsylvania',2),
(76,'Rhode Island',2),
(77,'South Carolina',2),
(78,'South Dakota',2),
(79,'Tennessee',2),
(80,'Texas',2),
(81,'Utah',2),
(82,'Vermont',2),
(83,'Virginia',2),
(84,'Washington',2),
(85,'West Virginia',2),
(86,'Wisconsin',2),
(87,'Wyoming',2);

SET foreign_key_checks = 1;