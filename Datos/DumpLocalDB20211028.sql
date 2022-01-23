CREATE DATABASE  IF NOT EXISTS `dg` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `dg`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: dg
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulos`
--

DROP TABLE IF EXISTS `articulos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulos` (
  `id_artic` int NOT NULL AUTO_INCREMENT,
  `nombre_artic` varchar(30) NOT NULL,
  `stock_artic` int NOT NULL,
  `fecha_vence` date NOT NULL,
  `id_categoria` int NOT NULL,
  `precio_artic` float NOT NULL,
  `id_estado` int NOT NULL,
  PRIMARY KEY (`id_artic`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulos`
--

LOCK TABLES `articulos` WRITE;
/*!40000 ALTER TABLE `articulos` DISABLE KEYS */;
INSERT INTO `articulos` VALUES (1,'ARTICULO 1',85,'2021-10-17',1,1630,1),(2,'Articulo 2',100,'2022-01-15',1,1230,1),(3,'Articulo 3',100,'2022-01-15',1,1200,1),(4,'Articulo 4',100,'2022-01-15',1,120,1),(5,'Articulo 5',120,'2022-01-15',2,126,1),(6,'ARTICULO 6',100,'2021-10-17',1,100,1),(7,'Articulo 7',132,'2022-01-15',2,130,2),(8,'Articulo 8',100,'2022-01-15',2,130,2),(9,'Articulo 9',100,'2022-01-15',2,1230,2),(10,'ARTICULO 10',90,'2021-11-25',1,1250,1),(11,'Articulo 11',130,'2022-01-15',3,1230,3),(12,'Articulo 12',130,'2022-01-15',3,230,3),(13,'Articulo 13',100,'2022-01-15',3,40,3),(14,'Articulo 14',146,'2022-01-15',3,430,4),(15,'Articulo 15',130,'2022-01-15',4,430,4),(16,'Articulo 16',100,'2022-01-15',4,430,4),(17,'Articulo 17',130,'2022-01-15',5,430,5),(18,'Articulo 18',600,'2022-01-15',5,430,5),(19,'Articulo 19',170,'2022-01-15',5,140,5),(20,'Articulo 20',170,'2022-01-15',5,230,5),(21,'Articulo 21',100,'2022-01-15',1,1230,1),(22,'Articulo 22',100,'2022-01-15',1,1230,1),(23,'Articulo 23',100,'2022-01-15',1,1200,1),(24,'Articulo 24',100,'2022-01-15',1,120,1),(25,'Articulo 25',120,'2022-01-15',2,126,1),(26,'Articulo 26',100,'2022-01-15',2,10,1),(27,'Articulo 27',132,'2022-01-15',2,130,2),(28,'Articulo 28',100,'2022-01-15',2,130,2),(29,'Articulo 29',100,'2022-01-15',2,1230,2),(30,'Articulo 30',100,'2022-01-15',3,1230,2),(31,'Articulo 31',130,'2022-01-15',3,1230,3),(32,'Articulo 32',130,'2022-01-15',3,230,3),(33,'Articulo 33',100,'2022-01-15',3,40,3),(34,'Articulo 34',146,'2022-01-15',3,430,4),(35,'Articulo 35',130,'2022-01-15',4,430,4),(36,'Articulo 36',100,'2022-01-15',4,430,4),(37,'Articulo 37',130,'2022-01-15',5,430,5),(38,'Articulo 38',600,'2022-01-15',5,430,5),(39,'Articulo 39',170,'2022-01-15',5,140,5),(40,'Articulo 40',170,'2022-01-15',5,230,5),(41,'ARTICULO 41',1,'2021-10-17',1,1,1),(42,'ARTICULO 120',43,'2021-10-28',1,444,1),(43,'ZZZZ',1,'2022-03-31',2,100,12),(44,'YJHGHJGHJG',6,'2022-03-31',3,6,13),(45,'ZZZZZZZZZZZZZZZZ',1,'2022-03-31',3,1,13),(46,'SSSSSSSSSSSSSSSSSS',1,'2021-11-01',2,1,3),(47,'DDDDDDDDDDDDDDDDDDDDD',2,'2021-11-01',1,2,4),(48,'RRRRRRRRRRRRRRRRRRRRRRRRR',3,'2023-11-30',1,3,1),(49,'DFRDFDFDFD',3,'2021-11-30',1,3,1),(50,'ARTICULO 123',100,'2021-10-28',1,2150,1);
/*!40000 ALTER TABLE `articulos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bancos`
--

DROP TABLE IF EXISTS `bancos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bancos` (
  `id_banco` int NOT NULL AUTO_INCREMENT,
  `nombre_banco` varchar(30) NOT NULL,
  `id_estado` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_banco`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bancos`
--

LOCK TABLES `bancos` WRITE;
/*!40000 ALTER TABLE `bancos` DISABLE KEYS */;
INSERT INTO `bancos` VALUES (1,'BANCO DE CHILE',1),(2,'BANCO EDWARDS/CITI',1),(3,'BANCO CREDICHILE',1),(4,'BANCO INTERNACIONAL',1),(5,'BANCO SCOTIABANK CHILE',1),(6,'BANCO BANCODESARROLLO',1),(7,'BANCO DE CREDITO E INVERSIONES',1),(8,'BANCO TBanc',1),(9,'BANCO NOVA',1),(10,'BANCO BICE',1),(11,'BANCO HSBC BANK (CHILE)',1),(12,'BANCO SANTANDER-CHILE',1),(13,'BANCO BANEFE',1),(14,'BANCO ITAU CORPBANCA',1),(15,'BANCO SECURITY',1),(16,'BANCO FALABELLA',1),(17,'BANCO RIPLEY',1),(18,'BANCO CONSORCIO',1),(19,'BANCO SCOTIABANK AZUL (BBVA)',1),(20,'BANCO BTG PACTUAL CHILE',1),(21,'BANCO DO BRASIL S.A.',1),(22,'JP MORGAN CHASE BANK',1),(23,'BANCO DE LA NACION ARGENTINA',1),(24,'MUFG Bank, Ltd.',1),(25,'CHINA CONSTRUCTION BANK',1),(26,'BANK OF CHINA',1),(27,'BANCO DEL ESTADO DE CHILE',1),(28,'BANK OF AMERICA',2);
/*!40000 ALTER TABLE `bancos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `id_categoria` int NOT NULL AUTO_INCREMENT,
  `nombre_categoria` varchar(30) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Categoria 1'),(2,'Categoria 2'),(3,'Categoria 3'),(4,'CATEGORIA 4'),(5,'CATEGORIA 5'),(6,'CATEGORIA 6');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `rut_cliente` varchar(9) NOT NULL,
  `nombre_cliente` varchar(20) NOT NULL,
  `apellido_cliente` varchar(40) NOT NULL,
  `dir_cliente` varchar(50) NOT NULL,
  `fono_cliente` varchar(11) NOT NULL,
  `correo_cliente` varchar(50) NOT NULL,
  `fenac_cliente` date DEFAULT NULL,
  `id_estado` int DEFAULT '1',
  PRIMARY KEY (`rut_cliente`),
  UNIQUE KEY `rut_cliente` (`rut_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('10214564k','VIVIANA JACQUELINE','ALARCON','zenteno','967942567','vivianajacke@gmail.com','1994-07-14',1),('10421164K','max','perdio','quewuajiro ','912356789','','1990-11-08',1),('109417599','Carlos','Chandia','Los rosales 3444 casa 2, La Florida','225222569','chandia@chile.cl','1965-01-01',1),('120393367','Mauricio','Galvez','El Amanecer 2254, Pob Santa Julia, Macul','556897256','galvez122@larronde.com','1979-07-22',1),('121653249','Ana','ZAVALA','Merced','976325373','anazava@gmail.com','1983-11-11',1),('140043893','valerio','Alvarez diaz','el golf','965254782','valealva@gmail.com','1986-06-05',1),('15494012k','soledad','Gallejos','Matta 99','954783635','solellejos@gmail.com','1990-11-01',1),('223474793','helen','neyra','malaquias','964527823','helenneyra@mail.com','1994-07-14',1),('243479325','johan','Nery','santiago concha','987346965','JohanNery@gmail.com','1995-10-04',1),('257264971','Ricardo ','Ruiz','Santa Elena','912345678','creadorclientes@creeclientes.cl','2021-10-28',0),('36236329','juan','perez','los boldos 3344','345354456','no tengo@notengo.cl','2009-09-17',1),('3781561-k','ALARCON','Cabeza','santiaguillo 200','9726354725','alarconcabeza@gmail.com','1994-07-14',1),('55246378','Juana','Muñoz','Las Gardenias 565','656565656','notiene@sincorreo.cl','1950-09-23',1),('9402560-5','EMA','ALFARO','los matorrales','987762567','emaalfaro@gmail.com','1988-11-09',1);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id_compra` int NOT NULL AUTO_INCREMENT,
  `ordenes_id_oc` int NOT NULL,
  `numfactura` int NOT NULL,
  `fecha_compra` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_compra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunas`
--

DROP TABLE IF EXISTS `comunas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comunas` (
  `id_comuna` int NOT NULL AUTO_INCREMENT,
  `nombre_comuna` varchar(30) NOT NULL,
  PRIMARY KEY (`id_comuna`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunas`
--

LOCK TABLES `comunas` WRITE;
/*!40000 ALTER TABLE `comunas` DISABLE KEYS */;
INSERT INTO `comunas` VALUES (1,'Santiago'),(2,'Cerrillos'),(3,'Cerro Navia'),(4,'CONCHALÍ'),(5,'El Bosque'),(6,'ESTACIÓN CENTRAL'),(7,'Huechuraba'),(8,'Independencia'),(9,'La Cisterna'),(10,'La Florida'),(11,'La Granja'),(12,'La Pintana'),(13,'La Reina'),(14,'Las Condes'),(15,'Lo Barnechea'),(16,'Lo Espejo'),(17,'Lo Prado'),(18,'Macul'),(19,'MAIPÚ'),(20,'ÑUÑOA'),(21,'Pedro Aguirre Cerda'),(22,'PEÑALOLÉN'),(23,'Providencia'),(24,'Pudahuel'),(25,'Quilicura'),(26,'Quinta Normal'),(27,'Recoleta'),(28,'Renca'),(29,'San JoaquÃ­n'),(30,'San Miguel'),(31,'San RamÃ³n'),(32,'Vitacura'),(33,'Puente Alto'),(34,'Pirque'),(35,'San JosÃ© de Maipo'),(36,'Colina'),(37,'Lampa'),(38,'Tiltil'),(39,'San Bernardo'),(40,'Buin'),(41,'Calera de Tango'),(42,'Paine'),(43,'Melipilla'),(44,'ALHUÉ'),(45,'CURACAVÍ'),(46,'MarÃ­a Pinto'),(47,'San Pedro'),(48,'Talagante'),(49,'El Monte'),(50,'Isla de Maipo'),(51,'Padre Hurtado'),(52,'PEÑAFLOR');
/*!40000 ALTER TABLE `comunas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_compra`
--

DROP TABLE IF EXISTS `detalle_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_compra` (
  `id_com` int NOT NULL AUTO_INCREMENT,
  `articulos_id_artic` int NOT NULL,
  `compras_id_compra` int NOT NULL,
  `cant_art` int NOT NULL,
  `precio_art` int NOT NULL,
  `fecha_venc` date NOT NULL,
  PRIMARY KEY (`id_com`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_compra`
--

LOCK TABLES `detalle_compra` WRITE;
/*!40000 ALTER TABLE `detalle_compra` DISABLE KEYS */;
INSERT INTO `detalle_compra` VALUES (1,6,1,2,1200,'2021-10-28'),(2,5,1,6,1000,'2021-10-27'),(3,7,2,2,1000,'2021-10-31'),(4,8,2,9,1200,'2021-11-26'),(5,7,3,2,1200,'2021-11-03'),(6,5,3,6,600,'2021-11-17'),(7,3,3,1,600,'2021-11-25'),(8,9,4,6,1118,'2021-12-31'),(9,10,4,6,1118,'2022-01-31');
/*!40000 ALTER TABLE `detalle_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_ordenes`
--

DROP TABLE IF EXISTS `detalle_ordenes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_ordenes` (
  `id_detalleoc` int NOT NULL AUTO_INCREMENT,
  `ordenes_id_oc` int NOT NULL,
  `articulos_id_artic` int NOT NULL,
  `cantidad_artic` int NOT NULL,
  PRIMARY KEY (`id_detalleoc`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_ordenes`
--

LOCK TABLES `detalle_ordenes` WRITE;
/*!40000 ALTER TABLE `detalle_ordenes` DISABLE KEYS */;
INSERT INTO `detalle_ordenes` VALUES (1,1202,8,2),(2,1202,3,1),(3,1202,7,5),(4,1203,6,36),(5,1203,3,10),(6,1203,7,10),(7,1203,4,0),(8,1204,4,2),(9,1204,5,3),(10,1204,3,4),(11,1204,9,0),(12,1205,10,10),(13,1205,3,0),(14,1206,10,3),(15,1206,8,6),(16,1206,7,1),(17,1207,6,2),(18,1207,8,1),(19,1207,5,0),(20,1208,7,2),(21,1208,5,6),(22,1208,3,2),(23,1209,9,6),(24,1209,10,6);
/*!40000 ALTER TABLE `detalle_ordenes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estados`
--

DROP TABLE IF EXISTS `estados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estados` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(30) NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estados`
--

LOCK TABLES `estados` WRITE;
/*!40000 ALTER TABLE `estados` DISABLE KEYS */;
INSERT INTO `estados` VALUES (1,'ACTIVO'),(2,'INACTIVO'),(3,'PENDIENTE PAGO'),(4,'PAGADO'),(5,'EN PREPARACIÓN'),(6,'DESPACHADO'),(7,'RECHAZADO'),(9,'DEVUELTO'),(10,'ROBADO'),(11,'SIN STOCK'),(12,'EN BODEGA'),(13,'DESCONTINUADO');
/*!40000 ALTER TABLE `estados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes_compra`
--

DROP TABLE IF EXISTS `ordenes_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes_compra` (
  `id_oc` int NOT NULL AUTO_INCREMENT,
  `fecha_orden` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estados_id_estado` int NOT NULL DEFAULT '1',
  `proveedores_id_proveedor` int NOT NULL,
  PRIMARY KEY (`id_oc`)
) ENGINE=InnoDB AUTO_INCREMENT=1210 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes_compra`
--

LOCK TABLES `ordenes_compra` WRITE;
/*!40000 ALTER TABLE `ordenes_compra` DISABLE KEYS */;
INSERT INTO `ordenes_compra` VALUES (1200,'2021-10-23 18:51:33',3,12),(1201,'2021-10-23 18:51:33',2,11),(1202,'2021-10-24 18:01:54',3,13),(1203,'2021-10-24 20:50:49',1,12),(1204,'2021-10-24 20:55:33',1,14),(1205,'2021-10-24 20:59:09',1,15),(1206,'2021-10-24 21:09:35',2,13),(1207,'2021-10-25 22:16:46',2,11),(1208,'2021-10-28 03:27:14',2,19),(1209,'2021-10-28 05:34:47',2,15);
/*!40000 ALTER TABLE `ordenes_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packs`
--

DROP TABLE IF EXISTS `packs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packs` (
  `id_pack` int NOT NULL AUTO_INCREMENT,
  `nombre_pack` varchar(30) NOT NULL,
  `precio_pack` float NOT NULL,
  `stock_pack` int NOT NULL,
  `id_tipo_pack` int NOT NULL,
  `id_estado` int DEFAULT '1',
  PRIMARY KEY (`id_pack`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packs`
--

LOCK TABLES `packs` WRITE;
/*!40000 ALTER TABLE `packs` DISABLE KEYS */;
INSERT INTO `packs` VALUES (1,'Pack Nro. 1',15200,100,1,1),(2,'Pack Nro. 2',13400,100,6,1),(3,'Pack Nro. 3',14500,100,1,1),(4,'Pack Nro. 4',10550,100,4,1),(5,'Pack Nro. 5',12300,100,5,1),(6,'Pack Nro. 6',17600,100,2,1),(7,'Pack Nro. 7',21400,100,1,1),(8,'Pack Nro. 8',56000,100,3,1),(9,'Pack Nro. 9',16715,100,1,1),(10,'Pack Nro. 10',10000,100,4,1),(16,'coombi',65434,3,2,1),(17,'Combo Variado',5430,2,3,1),(26,'nuevo',14000,1,2,1),(30,'Combo 44',15000,2,6,1),(31,'combo nuevo',16000,2,6,1);
/*!40000 ALTER TABLE `packs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packs_artics`
--

DROP TABLE IF EXISTS `packs_artics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packs_artics` (
  `id_pack` int NOT NULL,
  `id_artic` int NOT NULL,
  `artixpack` int NOT NULL,
  UNIQUE KEY `idx_packs_artics_id_artic_id_pack` (`id_artic`,`id_pack`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packs_artics`
--

LOCK TABLES `packs_artics` WRITE;
/*!40000 ALTER TABLE `packs_artics` DISABLE KEYS */;
INSERT INTO `packs_artics` VALUES (4,2,1),(58,2,1),(78,2,2),(2,3,1),(57,3,1),(100,3,1),(106,3,1),(110,3,2),(3,4,6),(1,5,2),(105,5,2),(109,5,1),(5,6,12),(59,6,2),(77,6,2),(108,6,2),(98,7,4),(104,7,2),(99,8,1),(107,8,1),(97,9,2);
/*!40000 ALTER TABLE `packs_artics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packs_tipo`
--

DROP TABLE IF EXISTS `packs_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packs_tipo` (
  `id_tipo_pack` int NOT NULL AUTO_INCREMENT,
  `nombre_tipo` varchar(30) NOT NULL,
  `id_estado` int NOT NULL,
  PRIMARY KEY (`id_tipo_pack`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packs_tipo`
--

LOCK TABLES `packs_tipo` WRITE;
/*!40000 ALTER TABLE `packs_tipo` DISABLE KEYS */;
INSERT INTO `packs_tipo` VALUES (1,'Enamorados 1',1),(2,'Enamorados 2',1),(3,'ENAMORADOS 7',3),(4,'Cumpleaños 1',1),(5,'Cumpleaños 2',1),(6,'Cumpleaños 3',1),(7,'Nacimiento 1',1),(8,'Nacimiento 2',1),(9,'Nacimiento 3',1),(10,'Navidad 1',1),(11,'Navidad 2',1),(12,'Navidad 3',1),(13,'DIA DE LA MADRE 1',1),(14,'DIA DEL PADRE 1',2),(15,'DIA DE LA MADRE 2',1),(16,'DIA DEL PADRE 2',1),(17,'DIA DEL AMIGO 1',1),(18,'DIA DEL PADRE 5',2),(19,'DESPEDIDA DE SOLTERO',1);
/*!40000 ALTER TABLE `packs_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos_clientes`
--

DROP TABLE IF EXISTS `pedidos_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos_clientes` (
  `id_pedido` int NOT NULL AUTO_INCREMENT,
  `rut_cliente` varchar(9) NOT NULL,
  `fecha_pedido` date NOT NULL,
  `id_red` int NOT NULL,
  `id_pack` int NOT NULL,
  `precio_venta` float NOT NULL,
  `id_estado` int NOT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `id_banco` int DEFAULT NULL,
  `codigo_transfer` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_pedido`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos_clientes`
--

LOCK TABLES `pedidos_clientes` WRITE;
/*!40000 ALTER TABLE `pedidos_clientes` DISABLE KEYS */;
INSERT INTO `pedidos_clientes` VALUES (1,'55246378','2021-10-17',1,10,15200,4,'2021-10-23 00:00:00',20,'54545554455'),(2,'109417599','2021-10-20',1,2,18000,3,'2000-01-01 00:00:00',0,''),(3,'36236329','2021-10-24',15,10,21400,1,'0006-06-23 00:00:00',6,''),(4,'109417599','2021-10-17',3,1,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(5,'55246378','2021-10-17',3,1,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(6,'109417599','2021-10-19',3,1,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(7,'55246378','2021-10-19',3,3,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(8,'36236329','2021-10-17',3,4,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(9,'55246378','2021-11-05',3,3,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(10,'109417599','2021-10-17',3,4,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(11,'36236329','2021-10-17',3,5,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(12,'109417599','2021-10-17',3,6,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(13,'109417599','2021-11-05',3,7,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(14,'109417599','2021-10-17',3,7,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(15,'55246378','2021-10-17',3,7,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(16,'109417599','2021-10-17',3,7,10000,1,'2021-10-17 00:00:00',5,'5554466999'),(17,'55246378','2021-11-05',3,7,10000,1,'2021-11-21 00:00:00',5,'5554466999'),(18,'36236329','2021-10-17',3,7,10000,1,'2021-11-21 00:00:00',5,'5554466999');
/*!40000 ALTER TABLE `pedidos_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedidos_destinos`
--

DROP TABLE IF EXISTS `pedidos_destinos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedidos_destinos` (
  `id_pedido` int NOT NULL,
  `nombre_destino` varchar(45) DEFAULT NULL,
  `direccion_destino` varchar(50) NOT NULL,
  `id_comuna` int NOT NULL,
  `fono_destino` varchar(15) DEFAULT NULL,
  `correo_destino` varchar(50) NOT NULL,
  `fecha_envio` datetime DEFAULT NULL,
  `fecha_recibido` datetime DEFAULT NULL,
  `mensaje` varchar(250) DEFAULT NULL,
  `id_estado` int NOT NULL,
  `hora_ini` varchar(5) NOT NULL DEFAULT '00:00',
  `hora_fin` varchar(5) DEFAULT '00:00',
  PRIMARY KEY (`id_pedido`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedidos_destinos`
--

LOCK TABLES `pedidos_destinos` WRITE;
/*!40000 ALTER TABLE `pedidos_destinos` DISABLE KEYS */;
INSERT INTO `pedidos_destinos` VALUES (1,'Juanita Destino','Las Bandurrias 0897',22,'556321987','juanita@chile.cl','2021-10-20 18:00:00','2000-01-01 00:00:00','Mensaje para destinatario',1,'12:00','13:00'),(2,'Pedrito Destino','Los rododendros 33, block4, depto 11',20,'562553233','pedrito@chile.cl','2021-10-25 17:00:00','2000-01-01 00:00:00','Este es otro mensaje para el destinatario',1,'11:00','16:00'),(3,'perico de los palotes','los notros 33, depto 1170, block 1',44,'545454','notiene@correo.cl','2021-10-24 00:00:00',NULL,'comprate un auto perico',0,'0900','1800');
/*!40000 ALTER TABLE `pedidos_destinos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedores`
--

DROP TABLE IF EXISTS `proveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedores` (
  `id_proveedor` int NOT NULL AUTO_INCREMENT,
  `rut_prove` varchar(10) NOT NULL,
  `nombre_prove` varchar(50) NOT NULL,
  `dir_prove` varchar(50) NOT NULL,
  `comunas_id_comuna` int NOT NULL,
  `fono_prove` varchar(11) NOT NULL,
  `correo_prove` varchar(50) NOT NULL,
  `es_activo_prove` tinyint DEFAULT '1',
  PRIMARY KEY (`id_proveedor`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedores`
--

LOCK TABLES `proveedores` WRITE;
/*!40000 ALTER TABLE `proveedores` DISABLE KEYS */;
INSERT INTO `proveedores` VALUES (11,'76611050-9','Inge Limitada','calle 22',27,'2564207','contacto@inge.cl',1),(12,'15349449-8','fabrica de peras','calle 66',4,'25252525','contacto@chile.com',1),(13,'15948849-5','Fruna Ltda','camino a melipilla 777',17,'111111111','fruna@fruna.cl',0),(14,'12120555-2','Fabrica de gatos','calle 12',1,'6661222','info@gatos.cl',1),(15,'12152125-2','Gallina Ltda','camino 666',10,'222222222','info@dino.cl',1),(16,'11252147-2','Pollos asados LTDA','camino 2020',5,'251252555','info@dino.cl',1),(17,'75215454-5','Fabrica peluche','calle gato 123',9,'','info@hotmail.com',0),(18,'15458849-5','savory','camino a melipilla 111111',4,'121545122','2020@savory.cl',0),(19,'12852521-2','fabrica dulces 1','camino 999',20,'958552212','info@dino.cl',1),(20,'9123456-2','Licores XP','calle 25',6,'252125225','info@licores.cl',1);
/*!40000 ALTER TABLE `proveedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `redes`
--

DROP TABLE IF EXISTS `redes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `redes` (
  `id_red` int NOT NULL AUTO_INCREMENT,
  `nombre_red` varchar(30) NOT NULL,
  `id_estado` int NOT NULL,
  PRIMARY KEY (`id_red`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `redes`
--

LOCK TABLES `redes` WRITE;
/*!40000 ALTER TABLE `redes` DISABLE KEYS */;
INSERT INTO `redes` VALUES (1,'FACEBOOK',1),(2,'Twitter',1),(3,'Instagram',1),(4,'Tik Tok',1),(5,'Twitch',1),(6,'Slack',1),(7,'Redit',1),(8,'Snapchat',1),(9,'TUMBLR',1),(10,'Tinder',1),(11,'SIGNAL',1),(12,'TELEGRAM',1),(13,'WAZAP',1),(14,'LINIO',3),(15,'CHINA SOCIAL',2);
/*!40000 ALTER TABLE `redes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` varchar(15) NOT NULL,
  `nombre_usuario` varchar(40) NOT NULL,
  `clave_usuario` varchar(15) NOT NULL,
  `id_estado` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('caarlos','Carlos Thierme','123',1),('user1','JPEREZ','123456',2),('user2','MACHUCADO 2','1236',2),('user3','USUARIO 3','jPasswordField1',2),('user4','User 4','123',1),('user5','User 5','123',1),('user6','User 6','123',1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-28 16:33:05
