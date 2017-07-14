LOCK TABLES `zip_code` WRITE;
INSERT INTO `zip_code` VALUES (1,'Тверь','Тверская область'),(2,'Москва','Московская область'),(3,'Санкт-Петербург','Ленинградская область');
UNLOCK TABLES;

LOCK TABLES `professions` WRITE;
INSERT INTO `professions` VALUES (1,'Разработчик'),(2,'Дизайнер'),(3,'Банкир'),(4,'Архитектор');
UNLOCK TABLES;

LOCK TABLES `interests` WRITE;
INSERT INTO `interests` VALUES (1,'Программирование'),(2,'Дизайн'),(3,'Животные'),(4,'Путешествие');
UNLOCK TABLES;

LOCK TABLES `my_contacts` WRITE;
INSERT INTO `my_contacts` VALUES (1,'Харди','Эн','am86@b.com','Ж','1989-10-08','777888999',1,1),(2,'Инне','Элизабет','eliz94@b.com','Ж','1994-09-07','555666442',3,2),(3,'Нолан','Кристоффер','god@b.com','М','1995-07-07','333222111',1,1),(4,'Харди','Том','azimov@b.com','M','1983-05-04','555999777',3,3),(8,'Элизабет','Шоу','elizabeth@googl.com','Ж','1989-09-07','892018899',1,1);
UNLOCK TABLES;

LOCK TABLES `contact_interest` WRITE;
INSERT INTO `contact_interest` VALUES (1,1),(1,2),(2,3),(2,4),(3,1),(3,2),(4,4);
UNLOCK TABLES;
