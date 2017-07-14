DROP TABLE IF EXISTS `contact_interest`;
CREATE TABLE `contact_interest` (
  `contact_id` int(11) NOT NULL,
  `interest_id` int(11) NOT NULL,
  KEY `fk_contc_id_idx` (`contact_id`),
  KEY `fk_inter_id_idx` (`interest_id`),
  CONSTRAINT `fk_contc_id` FOREIGN KEY (`contact_id`) REFERENCES `my_contacts` (`contact_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inter_id` FOREIGN KEY (`interest_id`) REFERENCES `interests` (`interest_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `interests`;
CREATE TABLE `interests` (
  `interest_id` int(11) NOT NULL,
  `interest` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`interest_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `my_contacts`;
CREATE TABLE `my_contacts` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `birhday` date DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `prof_id` int(11) NOT NULL,
  `zip_code` int(11) NOT NULL,
  PRIMARY KEY (`contact_id`),
  KEY `fk_zip_idx` (`zip_code`),
  KEY `fk_profession_idx` (`prof_id`),
  CONSTRAINT `fk_profession` FOREIGN KEY (`prof_id`) REFERENCES `professions` (`prof_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_zip` FOREIGN KEY (`zip_code`) REFERENCES `zip_code` (`zip_code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `professions`;
CREATE TABLE `professions` (
  `prof_id` int(11) NOT NULL AUTO_INCREMENT,
  `profession` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`prof_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zip_code`;
CREATE TABLE `zip_code` (
  `zip_code` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`zip_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
