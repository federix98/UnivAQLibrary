-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: progettodb
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autore` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `index2` (`Cognome`),
  KEY `index3` (`Nome`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (1,'Stendhal',NULL),(2,'Zerocalcare',NULL),(3,'Italo','Calvino'),(4,'Stefano','Benni'),(5,'Italo','Svevo'),(6,'Stephen','King'),(7,'Agatha','Christie'),(8,'Dante','Alighieri'),(9,'Oscar','Wilde'),(10,'Giovanni','Pascoli'),(11,'Giacomo','Leopardi'),(12,'Margaret','Atwood'),(13,'Natasha','Pulley');
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datimoderatore`
--

DROP TABLE IF EXISTS `datimoderatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datimoderatore` (
  `IDUtente` int(10) unsigned NOT NULL,
  `NumPubb` int(10) unsigned NOT NULL DEFAULT '0',
  `DataPromozione` date NOT NULL,
  `Promotore` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`IDUtente`),
  KEY `fk_Promotore_idx` (`Promotore`),
  CONSTRAINT `fk_DatiModeratori_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Promotore` FOREIGN KEY (`Promotore`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datimoderatore`
--

LOCK TABLES `datimoderatore` WRITE;
/*!40000 ALTER TABLE `datimoderatore` DISABLE KEYS */;
INSERT INTO `datimoderatore` VALUES (1,0,'2000-00-00',NULL),(10,1,'2019-07-18',1),(11,1,'2019-07-18',1),(12,11,'2019-07-18',1),(15,0,'2019-10-16',1),(16,0,'2019-10-16',1),(17,0,'2019-10-24',1),(18,0,'2019-10-16',10),(21,0,'2019-11-28',28),(22,0,'2019-10-16',1),(28,0,'2019-10-24',1);
/*!40000 ALTER TABLE `datimoderatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datiutente`
--

DROP TABLE IF EXISTS `datiutente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datiutente` (
  `IDUtente` int(10) unsigned NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Cognome` varchar(45) NOT NULL,
  `DataNascita` date NOT NULL,
  `LuogoNascita` varchar(100) NOT NULL,
  `Residenza` varchar(100) NOT NULL,
  `Email` varchar(254) NOT NULL,
  PRIMARY KEY (`IDUtente`),
  UNIQUE KEY `DatiUtentecol_UNIQUE` (`Email`),
  CONSTRAINT `fk_DatiUtente_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datiutente`
--

LOCK TABLES `datiutente` WRITE;
/*!40000 ALTER TABLE `datiutente` DISABLE KEYS */;
INSERT INTO `datiutente` VALUES (1,'Admin','Sistem','1993-02-02','Avellino','Chieti, Italia','admin@gmail.com'),(10,'Mario','Rossi','1993-02-05','Roma, Italia','Chieti, Italia','rossi.mario@student.univaq.it'),(11,'Francesco','Rossi','1993-02-05','Chieti, Italia','Milano, Italia','francesco.utente@gmail.com'),(12,'riicardo','mariani','1993-02-05','Chieti, Italia','Chieti, Italia','ricky@gmail.com'),(15,'Sandro','Costa','1992-01-07','Roma, Italia','L\'Aquila, Italia','sandro@gmail.com'),(16,'Flavio','Salvo','1993-02-05','Chieti, Italia','Milano, Italia','doge@gmail.com'),(17,'Mario','Verdi','1992-01-07','Roma, Italia','L\'Aquila, Italia','mario@gmail.com'),(18,'Carmelo','Creati','1993-02-05','Chieti, Italia','Milano, Italia','carmelo@gmail.com'),(19,'Kyle','Warren','1992-01-07','Roma, Italia','L\'Aquila, Italia','Kyle@gmail.com'),(20,'Dario','Monti','1993-02-05','Chieti, Italia','Milano, Italia','darius@student.univaq.it'),(21,'Morgana','Tenaglia','1992-01-07','Roma, Italia','L\'Aquila, Italia','Morgana@student.univaq.it'),(22,'Giorgio','Mancia','1993-02-05','Chieti, Italia','Milano, Italia','garen@student.univaq.it'),(23,'nome','cogn','2000-11-11','chietiroma','chietiroma','email1'),(24,'dwd','d23s3','2019-10-30','Barletta-Andria-Trani, Italia','Ancona, Italia','swq@grwe.it'),(25,'ProvaNome','ProvaCognome','2019-10-23','Barletta-Andria-Trani, Italia','Asti, Italia','prova@prova.it'),(26,'Francesca','Santoferrara','1997-04-14','Chieti, Italia','Chieti, Italia','email@email.it'),(27,'Daniele','Di Desidero','1998-03-10','L\'Aquila, Italia','Campobasso, Italia','dani_dd-98@live.it'),(28,'Alessandra','Battaglia','2019-10-15','Milano, Italia','Chieti, Italia','alebattaglia98@gmail.com'),(29,'prova','xxx','2019-11-04','Avellino, Italia','Belluno, Italia','myemail@email.com'),(30,'PRova','avorp','2019-05-08','Firenze, Italia','Crotone, Italia','prova@pro.va'),(31,'dwqqwqd','dewdw','2019-12-16','Ascoli Piceno, Italia','Belluno, Italia','em@em.it');
/*!40000 ALTER TABLE `datiutente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like`
--

DROP TABLE IF EXISTS `like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Timestamp` timestamp NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente1_Utente1_idx` (`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente1_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Utente1_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente1_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like`
--

LOCK TABLES `like` WRITE;
/*!40000 ALTER TABLE `like` DISABLE KEYS */;
INSERT INTO `like` VALUES (3,1,'2019-10-24 12:37:26'),(3,11,'2019-07-18 15:03:37'),(3,17,'2019-07-18 15:03:36'),(3,18,'2019-07-18 15:03:37'),(3,28,'2019-10-18 18:33:33'),(4,1,'2019-12-11 10:42:14'),(4,12,'2019-07-18 15:03:37'),(4,14,'2019-07-18 15:03:38'),(4,16,'2019-07-18 15:03:38'),(5,15,'2019-07-18 15:03:39'),(5,16,'2019-07-18 15:03:39'),(5,17,'2019-07-18 15:03:39'),(5,29,'2019-11-29 12:48:54'),(6,10,'2019-10-17 15:03:26'),(6,18,'2019-07-18 15:03:40'),(6,19,'2019-07-18 15:03:40'),(7,14,'2019-07-18 15:03:36'),(7,15,'2019-07-18 15:03:36'),(7,16,'2019-07-18 15:03:36'),(7,21,'2019-07-18 15:03:41'),(7,24,'2019-10-10 10:51:26'),(8,10,'2019-10-17 11:21:17'),(8,20,'2019-07-18 15:03:41'),(8,24,'2019-10-10 10:51:34'),(9,1,'2019-10-24 17:37:25'),(9,12,'2019-10-10 10:21:15'),(9,21,'2019-07-18 15:03:41'),(9,24,'2019-10-10 10:44:27'),(10,10,'2019-10-16 17:36:32'),(14,24,'2019-10-10 10:51:31'),(15,10,'2019-10-17 12:04:26'),(16,28,'2019-10-25 18:32:11');
/*!40000 ALTER TABLE `like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metadati`
--

DROP TABLE IF EXISTS `metadati`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metadati` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `Npag` smallint(5) unsigned NOT NULL,
  `Lingua` varchar(45) NOT NULL,
  `Descrizione` text NOT NULL,
  `Indice` text NOT NULL,
  PRIMARY KEY (`IDPubblicazione`),
  CONSTRAINT `fk_Metadati_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metadati`
--

LOCK TABLES `metadati` WRITE;
/*!40000 ALTER TABLE `metadati` DISABLE KEYS */;
INSERT INTO `metadati` VALUES (3,122,'Italiano','Descrizione...','INDICE'),(4,109,'Italiano','Descrizione...','INDICE'),(5,224,'Italiano','Descrizione...','INDICE'),(6,640,'Italiano','Descrizione...','INDICE'),(7,461,'Italiano','Descrizione...','INDICE'),(8,328,'Italiano','Descrizione...','INDICE'),(9,192,'Italiano','Descrizione...0','INDICE'),(10,140,'Italiano','L\'auto su cui sta viaggiando Tinebra insieme ai suoi genitori frena bruscamente\nsulla neve: in mezzo alla strada, tre ragazzi hanno catturato una lupa e la stanno picchiando.','INDICE'),(11,196,'Italiano','Descrizione...','INDICE'),(12,666,'arabo','descrivo','indicizzo'),(13,222,'Italiano','Descrizione...','INDICE'),(14,42,'italiano','descrizione','indice'),(15,502,'Italiano','Descrizione','-- INDICE --'),(16,378,'Italiano','Libro di fantascienza','--');
/*!40000 ALTER TABLE `metadati` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parolachiave`
--

DROP TABLE IF EXISTS `parolachiave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parolachiave` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ParolaChiave` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Parola Chiave_UNIQUE` (`ParolaChiave`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parolachiave`
--

LOCK TABLES `parolachiave` WRITE;
/*!40000 ALTER TABLE `parolachiave` DISABLE KEYS */;
INSERT INTO `parolachiave` VALUES (11,'animali'),(9,'comico'),(14,'fantascienza'),(13,'fantasy'),(10,'fummetto'),(2,'giallo'),(7,'gossip'),(6,'letteratura'),(4,'music'),(5,'poema'),(1,'romantico'),(3,'sci-fi'),(8,'storico');
/*!40000 ALTER TABLE `parolachiave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `province`
--

DROP TABLE IF EXISTS `province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `province` (
  `id_province` int(16) NOT NULL AUTO_INCREMENT,
  `nome_province` varchar(128) NOT NULL,
  `sigla_province` varchar(5) NOT NULL,
  `regione_province` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id_province`)
) ENGINE=MyISAM AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `province`
--

LOCK TABLES `province` WRITE;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` VALUES (1,'Agrigento','AG','Sicilia'),(2,'Alessandria','AL','Piemonte'),(3,'Ancona','AN','Marche'),(4,'Arezzo','AR','Toscana'),(5,'Ascoli Piceno','AP','Marche'),(6,'Asti','AT','Piemonte'),(7,'Avellino','AV','Campania'),(8,'Bari','BA','Puglia'),(9,'Barletta-Andria-Trani','BT','Puglia'),(10,'Belluno','BL','Veneto'),(11,'Benevento','BN','Campania'),(12,'Bergamo','BG','Lombardia'),(13,'Biella','BI','Piemonte'),(14,'Bologna','BO','Emilia-Romagna'),(15,'Bolzano','BZ','Trentino-Alto Adige'),(16,'Brescia','BS','Lombardia'),(17,'Brindisi','BR','Puglia'),(18,'Cagliari','CA','Sardegna'),(19,'Caltanissetta','CL','Sicilia'),(20,'Campobasso','CB','Molise'),(21,'Carbonia-Iglesias','CI','Sardegna'),(22,'Caserta','CE','Campania'),(23,'Catania','CT','Sicilia'),(24,'Catanzaro','CZ','Calabria'),(25,'Chieti','CH','Abruzzo'),(26,'Como','CO','Lombardia'),(27,'Cosenza','CS','Calabria'),(28,'Cremona','CR','Lombardia'),(29,'Crotone','KR','Calabria'),(30,'Cuneo','CN','Piemonte'),(31,'Enna','EN','Sicilia'),(32,'Fermo','FM','Marche'),(33,'Ferrara','FE','Emilia-Romagna'),(34,'Firenze','FI','Toscana'),(35,'Foggia','FG','Puglia'),(36,'Forlì-Cesena','FC','Emilia-Romagna'),(37,'Frosinone','FR','Lazio'),(38,'Genova','GE','Liguria'),(39,'Gorizia','GO','Friuli-Venezia Giulia'),(40,'Grosseto','GR','Toscana'),(41,'Imperia','IM','Liguria'),(42,'Isernia','IS','Molise'),(43,'L\'Aquila','AQ','Abruzzo'),(44,'La Spezia','SP','Liguria'),(45,'Latina','LT','Lazio'),(46,'Lecce','LE','Puglia'),(47,'Lecco','LC','Lombardia'),(48,'Livorno','LI','Toscana'),(49,'Lodi','LO','Lombardia'),(50,'Lucca','LU','Toscana'),(51,'Macerata','MC','Marche'),(52,'Mantova','MN','Lombardia'),(53,'Massa e Carrara','MS','Toscana'),(54,'Matera','MT','Basilicata'),(55,'Medio Campidano','VS','Sardegna'),(56,'Messina','ME','Sicilia'),(57,'Milano','MI','Lombardia'),(58,'Modena','MO','Emilia-Romagna'),(59,'Monza e Brianza','MB','Lombardia'),(60,'Napoli','NA','Campania'),(61,'Novara','NO','Piemonte'),(62,'Nuoro','NU','Sardegna'),(63,'Ogliastra','OG','Sardegna'),(64,'Olbia-Tempio','OT','Sardegna'),(65,'Oristano','OR','Sardegna'),(66,'Padova','PD','Veneto'),(67,'Palermo','PA','Sicilia'),(68,'Parma','PR','Emilia-Romagna'),(69,'Pavia','PV','Lombardia'),(70,'Perugia','PG','Umbria'),(71,'Pesaro e Urbino','PU','Marche'),(72,'Pescara','PE','Abruzzo'),(73,'Piacenza','PC','Emilia-Romagna'),(74,'Pisa','PI','Toscana'),(75,'Pistoia','PT','Toscana'),(76,'Pordenone','PN','Friuli-Venezia Giulia'),(77,'Potenza','PZ','Basilicata'),(78,'Prato','PO','Toscana'),(79,'Ragusa','RG','Sicilia'),(80,'Ravenna','RA','Emilia-Romagna'),(81,'Reggio Calabria(metropolitana)','RC','Calabria'),(82,'Reggio Emilia','RE','Emilia-Romagna'),(83,'Rieti','RI','Lazio'),(84,'Rimini','RN','Emilia-Romagna'),(85,'Roma','RM','Lazio'),(86,'Rovigo','RO','Veneto'),(87,'Salerno','SA','Campania'),(88,'Sassari','SS','Sardegna'),(89,'Savona','SV','Liguria'),(90,'Siena','SI','Toscana'),(91,'Siracusa','SR','Sicilia'),(92,'Sondrio','SO','Lombardia'),(93,'Taranto','TA','Puglia'),(94,'Teramo','TE','Abruzzo'),(95,'Terni','TR','Umbria'),(96,'Torino','TO','Piemonte'),(97,'Trapani','TP','Sicilia'),(98,'Trento','TN','Trentino-Alto Adige'),(99,'Treviso','TV','Veneto'),(100,'Trieste','TS','Friuli-Venezia Giulia'),(101,'Udine','UD','Friuli-Venezia Giulia'),(102,'Aosta','AO','Valle d\'Aosta'),(103,'Varese','VA','Lombardia'),(104,'Venezia','VE','Veneto'),(105,'Verbano-Cusio-Ossola','VB','Piemonte'),(106,'Vercelli','VC','Piemonte'),(107,'Verona','VR','Veneto'),(108,'Vibo Valentia','VV','Calabria'),(109,'Vicenza','VI','Veneto'),(110,'Viterbo','VT','Lazio'),(111,'STATO ESTERO','',NULL);
/*!40000 ALTER TABLE `province` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pubblicazione`
--

DROP TABLE IF EXISTS `pubblicazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pubblicazione` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ISBN` char(13) NOT NULL,
  `Titolo` varchar(100) NOT NULL DEFAULT 'Sconosciuto',
  `Editore` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `NumLike` int(11) NOT NULL DEFAULT '0',
  `NumRec` int(11) NOT NULL DEFAULT '0',
  `DataPubblicazione` date NOT NULL,
  `DataUltimaModifica` date NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`),
  KEY `bytitle` (`Titolo`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pubblicazione`
--

LOCK TABLES `pubblicazione` WRITE;
/*!40000 ALTER TABLE `pubblicazione` DISABLE KEYS */;
INSERT INTO `pubblicazione` VALUES (3,'9788817138789','Limbo. Pensieri inversi','Rizzoli',5,3,'2019-07-02','2019-07-18'),(4,'9788893990172','Quasi è il mio nome','96 Rue De La Fontaine Edizioni',4,3,'2019-06-22','2019-07-18'),(5,'9788830101821','Un mondo migliore. Ritratti','Bompiani',4,3,'2019-06-19','2019-07-18'),(6,'9788809869097','L\'eredità di Agneta. Le signore di Lowenhof','Giunti Editore',3,3,'2019-07-10','2019-07-19'),(7,'9788865946732','Il visitatore 2','Nutrimenti',5,1,'2019-06-27','2019-10-16'),(8,'9788868369989','Mister Napoleone','Piemme',3,2,'2019-06-18','2019-07-18'),(9,'9788870915686','Norvegia. The passenger','Rizzoli',4,2,'2019-06-18','2019-10-24'),(10,'9788856671254','Cuore di lupo','Mondadori',1,4,'2019-07-09','2019-10-16'),(11,'9788817141086','Falso in bilancia','Mondadori',0,0,'2019-07-02','2019-07-18'),(12,'2','caio','edit',1,0,'1999-06-06','2019-07-19'),(13,'9788893255653','La vendetta di Oreste.','Rizzoli',0,0,'2019-07-11','2019-07-18'),(14,'3247969999999','Bar Sport','Rizzoli',2,0,'2000-11-11','2019-07-18'),(15,'9788833312415','I testamenti','Ponte Alle Grazie',1,0,'2019-10-09','2019-10-17'),(16,'9788845283369','L\'orologiaio di Filigree Street','Bompiani',1,0,'2017-03-14','2019-10-18');
/*!40000 ALTER TABLE `pubblicazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Descrizione` text NOT NULL,
  `FlagApprovazione` bit(1) NOT NULL DEFAULT b'0',
  `ApprovatoDa` int(10) unsigned DEFAULT NULL,
  `Timestamp` timestamp NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente_Utente1_idx` (`IDUtente`),
  KEY `fk_Pubblicazione_has_Utente_Pubblicazione_idx` (`IDPubblicazione`),
  KEY `fk_approva_idx` (`ApprovatoDa`),
  CONSTRAINT `fk_Pubblicazione_has_Utente_Pubblicazione` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente_Utente1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_approva` FOREIGN KEY (`ApprovatoDa`) REFERENCES `utente` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (3,11,'testo',_binary '',11,'2019-07-17 22:00:00'),(3,12,'testo',_binary '',11,'2019-07-17 22:00:00'),(3,19,'testo',_binary '\0',NULL,'2019-07-17 22:00:00'),(3,28,'Testo ben formulato e utile\nlo consiglio',_binary '',28,'2019-10-18 18:34:21'),(4,11,'testo',_binary '',11,'2019-07-17 22:00:00'),(4,14,'testo',_binary '',11,'2019-07-17 22:00:00'),(4,19,'testo',_binary '',24,'2019-07-17 22:00:00'),(5,11,'testo',_binary '',11,'2019-07-17 22:00:00'),(5,14,'testo',_binary '',11,'2019-07-17 22:00:00'),(5,20,'testo',_binary '',1,'2019-07-17 22:00:00'),(6,12,'testo',_binary '\0',NULL,'2019-07-17 22:00:00'),(6,14,'testo',_binary '',1,'2019-07-17 22:00:00'),(6,20,'testo',_binary '',1,'2019-07-17 22:00:00'),(6,24,'Ciaociao',_binary '',12,'2019-10-09 21:07:56'),(7,12,'testo',_binary '',1,'2019-07-17 22:00:00'),(7,15,'testo',_binary '\0',NULL,'2019-07-17 22:00:00'),(8,12,'testo',_binary '',11,'2019-07-17 22:00:00'),(8,16,'testo',_binary '',11,'2019-07-17 22:00:00'),(9,12,'testo',_binary '',11,'2019-07-17 22:00:00'),(9,17,'testo',_binary '',11,'2019-07-17 22:00:00'),(10,10,'ti prego',_binary '\0',NULL,'2019-07-19 12:14:38'),(10,12,'testo',_binary '',11,'2019-07-17 22:00:00'),(10,14,'testo commento',_binary '',1,'2019-07-18 22:00:00'),(10,15,'3333',_binary '',24,'2019-07-19 12:32:38'),(10,18,'testo',_binary '',11,'2019-07-17 22:00:00');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ristampa`
--

DROP TABLE IF EXISTS `ristampa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ristampa` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `DataRistampa` date NOT NULL,
  `Numero` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_RISTAMPA_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `Ordine Tempo` (`DataRistampa` DESC) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_RISTAMPA_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ristampa`
--

LOCK TABLES `ristampa` WRITE;
/*!40000 ALTER TABLE `ristampa` DISABLE KEYS */;
INSERT INTO `ristampa` VALUES (8,3,'2003-11-11',1),(9,3,'2004-11-11',2),(10,3,'2005-11-11',3),(11,4,'2006-11-11',1),(12,4,'2007-11-11',2),(13,4,'2008-11-11',3),(14,5,'2009-11-11',1),(15,5,'2010-11-11',2),(16,5,'2011-11-11',3),(17,5,'2012-11-11',4),(18,6,'2011-11-11',5),(19,6,'2010-11-11',4),(21,6,'2000-11-11',3),(22,6,'2022-11-11',23),(23,15,'2019-10-17',2);
/*!40000 ALTER TABLE `ristampa` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validazioneInserimentoRistampeTrigger` BEFORE INSERT ON `ristampa` FOR EACH ROW BEGIN
      DECLARE idPubb INTEGER;
      SET idPubb = NEW.`IdPubblicazione`;
        
        IF NOT EXISTS 	(SELECT * FROM Ristampa R
			WHERE R.IDPubblicazione = idPubb AND R.Numero = NEW.Numero)
		THEN
			IF ( SELECT MAX(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
            			WHERE R.IDPubblicazione = idPubb AND R.Numero < NEW.Numero ) > NEW.DataRistampa
				THEN
					SIGNAL SQLSTATE '45000' SET message_text = "ERRORE: Esiste una ristampa di numero inferiore con data superiore";
				ELSE IF ( SELECT MIN(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
						WHERE R.IDPubblicazione = idPubb AND R.Numero > NEW.Numero ) < NEW.DataRistampa
					THEN
						SIGNAL SQLSTATE '45000' SET message_text = "ERRORE: Esiste una ristampa di numero superiore con data inferiore";
				END IF;
			END IF;
		ELSE 
			SIGNAL SQLSTATE '45000' SET message_text = "La ristampa esiste già";
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `validazioneAggiornamentoRistampeTrigger` BEFORE INSERT ON `ristampa` FOR EACH ROW BEGIN
      DECLARE idPubb INTEGER;
      SET idPubb = NEW.`IdPubblicazione`;
        
        
      IF ( SELECT MAX(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
            	WHERE R.IDPubblicazione = idPubb AND R.Numero < NEW.Numero ) > NEW.DataRistampa
          THEN
	SIGNAL SQLSTATE '45000' SET message_text = 
"ERRORE: Esiste una ristampa di numero inferiore con data superiore";
          ELSE IF ( SELECT MIN(R.DataRistampa) as RistampaPrecedente FROM Ristampa R
		WHERE R.IDPubblicazione = idPubb AND R.Numero > NEW.Numero ) < NEW.DataRistampa
	          THEN
		SIGNAL SQLSTATE '45000' SET message_text = 
"ERRORE: Esiste una ristampa di numero superiore con data inferiore";
           END IF;
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `ruolo`
--

DROP TABLE IF EXISTS `ruolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ruolo` (
  `ID` tinyint(3) unsigned NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Descrizione` text,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Nome_UNIQUE` (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ruolo`
--

LOCK TABLES `ruolo` WRITE;
/*!40000 ALTER TABLE `ruolo` DISABLE KEYS */;
INSERT INTO `ruolo` VALUES (1,'passivo','Utente passivo'),(2,'attivo','Utente attivo'),(3,'moderatore','Utente moderatore'),(4,'amministratore','Utente amministratore'),(5,'superAmministratore','Utente super amministratore');
/*!40000 ALTER TABLE `ruolo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scritto`
--

DROP TABLE IF EXISTS `scritto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scritto` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDAutore` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDAutore`),
  KEY `fk_Pubblicazione_has_Autore_Autore1_idx` (`IDAutore`),
  KEY `fk_Pubblicazione_has_Autore_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Autore_Autore1` FOREIGN KEY (`IDAutore`) REFERENCES `autore` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Autore_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scritto`
--

LOCK TABLES `scritto` WRITE;
/*!40000 ALTER TABLE `scritto` DISABLE KEYS */;
INSERT INTO `scritto` VALUES (3,1),(8,1),(3,2),(9,2),(4,3),(10,3),(4,4),(11,4),(4,5),(5,6),(6,7),(7,8),(8,8),(8,9),(15,12),(16,13);
/*!40000 ALTER TABLE `scritto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorgente`
--

DROP TABLE IF EXISTS `sorgente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sorgente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `URI` varchar(2083) NOT NULL,
  `Tipo` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `Formato` varchar(45) NOT NULL DEFAULT 'Sconosciuto',
  `Descrizione` text,
  PRIMARY KEY (`ID`),
  KEY `fk_Sorgente_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `indiceSuTipo` (`Tipo`),
  CONSTRAINT `fk_Sorgente_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorgente`
--

LOCK TABLES `sorgente` WRITE;
/*!40000 ALTER TABLE `sorgente` DISABLE KEYS */;
INSERT INTO `sorgente` VALUES (2,3,'https://duckduckgo.com/','download','.pdf','descrizione'),(3,3,'https://duckduckgo.com/','immagine','.png','descrizione'),(4,3,'https://duckduckgo.com/','video','.mp4','descrizione'),(5,4,'https://duckduckgo.com/','download','.pdf','descrizione'),(6,4,'https://duckduckgo.com/','video','.mp4','descrizione'),(7,5,'https://duckduckgo.com/','download','.pdf','descrizione'),(8,5,'https://duckduckgo.com/','immagine','.png','descrizione'),(9,6,'https://duckduckgo.com/','download','.pdf','descrizione'),(10,7,'https://duckduckgo.com/','download','.pdf','descrizione'),(11,7,'https://duckduckgo.com/','immagine','.png','descrizione'),(12,8,'https://duckduckgo.com/','video','.mp4','descrizione'),(13,9,'https://duckduckgo.com/','video','.mp4','descrizione'),(14,10,'https://duckduckgo.com/','download','.pdf','descrizione'),(15,11,'https://duckduckgo.com/','immagine','.png','descrizione'),(16,11,'https://duckduckgo.com/','video','.mp4','descrizione'),(17,12,'https://duckduckgo.com/','immagine','.png','descrizione'),(18,13,'https://duckduckgo.com/','immagine','.png','descrizione'),(19,13,'https://duckduckgo.com/','download','.pdf','descrizione'),(20,15,'http://google.com','SitoWeb','NULL','-- PROVA --');
/*!40000 ALTER TABLE `sorgente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storia`
--

DROP TABLE IF EXISTS `storia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `storia` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IDPubblicazione` int(10) unsigned DEFAULT NULL,
  `IDUtente` int(10) unsigned NOT NULL,
  `Timestamp` timestamp NOT NULL,
  `Descrizione` varchar(250) NOT NULL,
  `Tipo` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_Pubblicazione_has_Utente_Utente2_idx` (`IDUtente`) /*!80000 INVISIBLE */,
  KEY `fk_Pubblicazione_has_Utente_Pubblicazione1_idx` (`IDPubblicazione`),
  KEY `indextipo` (`Tipo`),
  CONSTRAINT `fk_Pubblicazione_has_Utente_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Utente_Utente2` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storia`
--

LOCK TABLES `storia` WRITE;
/*!40000 ALTER TABLE `storia` DISABLE KEYS */;
INSERT INTO `storia` VALUES (1,3,11,'2019-07-18 14:35:57','franz94 ha inserito la pubblicazione titolata: Limbo. Pensieri inversi',1),(2,4,12,'2019-07-18 14:35:58','ricky66 ha inserito la pubblicazione titolata: Quasi è il mio nome',1),(3,5,12,'2019-07-18 14:35:58','ricky66 ha inserito la pubblicazione titolata: Un mondo migliore. Ritratti',1),(4,6,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: L\'eredità di Agneta. Le signore di Lowenhof',1),(5,7,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: Il visitatore',1),(6,8,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: Mister Napoleone',1),(7,9,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: Norvegia. The passenger',1),(8,10,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: Cuore di lupo',1),(9,11,12,'2019-07-18 14:35:59','ricky66 ha inserito la pubblicazione titolata: Falso in bilancia',1),(10,12,12,'2019-07-18 14:36:00','ricky66 ha inserito la pubblicazione titolata: Come una notte a Bali',1),(11,13,12,'2019-07-18 14:36:00','ricky66 ha inserito la pubblicazione titolata: La vendetta di Oreste.',1),(12,NULL,1,'2019-07-18 14:47:03','Admin ha aggiunto una ristampa alla pubblicazione : Senzanima. Buio.',3),(13,NULL,1,'2019-07-18 14:47:24','Admin ha aggiunto una ristampa alla pubblicazione : Senzanima. Buio.',3),(14,NULL,1,'2019-07-18 14:49:11','Admin ha aggiunto una ristampa alla pubblicazione : Senzanima. Buio.',3),(15,NULL,1,'2019-07-18 14:49:11','Admin ha aggiunto una ristampa alla pubblicazione : Senzanima. Buio.',3),(16,3,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Limbo. Pensieri inversi',3),(17,3,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Limbo. Pensieri inversi',3),(18,3,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Limbo. Pensieri inversi',3),(19,4,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Quasi è il mio nome',3),(20,4,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Quasi è il mio nome',3),(21,4,1,'2019-07-18 14:50:01','Admin ha aggiunto una ristampa alla pubblicazione : Quasi è il mio nome',3),(22,5,1,'2019-07-18 14:50:02','Admin ha aggiunto una ristampa alla pubblicazione : Un mondo migliore. Ritratti',3),(23,5,1,'2019-07-18 14:50:02','Admin ha aggiunto una ristampa alla pubblicazione : Un mondo migliore. Ritratti',3),(24,5,1,'2019-07-18 14:50:02','Admin ha aggiunto una ristampa alla pubblicazione : Un mondo migliore. Ritratti',3),(25,NULL,10,'2019-07-18 14:51:09','Mario92 ha aggiunto una sorgente alla pubblicazione : Senzanima. Buio.',3),(26,3,10,'2019-07-18 14:53:46','Mario92 ha aggiunto una sorgente alla pubblicazione : Limbo. Pensieri inversi',3),(27,3,10,'2019-07-18 14:53:47','Mario92 ha aggiunto una sorgente alla pubblicazione : Limbo. Pensieri inversi',3),(28,3,10,'2019-07-18 14:53:47','Mario92 ha aggiunto una sorgente alla pubblicazione : Limbo. Pensieri inversi',3),(29,4,10,'2019-07-18 14:53:47','Mario92 ha aggiunto una sorgente alla pubblicazione : Quasi è il mio nome',3),(30,4,10,'2019-07-18 14:53:47','Mario92 ha aggiunto una sorgente alla pubblicazione : Quasi è il mio nome',3),(31,5,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Un mondo migliore. Ritratti',3),(32,5,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Un mondo migliore. Ritratti',3),(33,6,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',3),(34,7,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Il visitatore',3),(35,7,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Il visitatore',3),(36,8,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Mister Napoleone',3),(37,9,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Norvegia. The passenger',3),(38,10,10,'2019-07-18 14:53:48','Mario92 ha aggiunto una sorgente alla pubblicazione : Cuore di lupo',3),(39,11,10,'2019-07-18 14:53:49','Mario92 ha aggiunto una sorgente alla pubblicazione : Falso in bilancia',3),(40,11,10,'2019-07-18 14:53:49','Mario92 ha aggiunto una sorgente alla pubblicazione : Falso in bilancia',3),(41,12,10,'2019-07-18 14:53:49','Mario92 ha aggiunto una sorgente alla pubblicazione : Come una notte a Bali',3),(42,13,10,'2019-07-18 14:53:49','Mario92 ha aggiunto una sorgente alla pubblicazione : La vendetta di Oreste.',3),(43,13,10,'2019-07-18 14:53:49','Mario92 ha aggiunto una sorgente alla pubblicazione : La vendetta di Oreste.',3),(44,NULL,1,'2019-07-18 14:54:21','Admin ha aggiunto l\'autore 1 alla pubblicazione : Senzanima. Buio.',3),(45,3,10,'2019-07-18 14:55:57','Mario92 ha aggiunto l\'autore 1 alla pubblicazione : Limbo. Pensieri inversi',3),(46,3,10,'2019-07-18 14:55:57','Mario92 ha aggiunto l\'autore 2 alla pubblicazione : Limbo. Pensieri inversi',3),(47,4,10,'2019-07-18 14:55:57','Mario92 ha aggiunto l\'autore 3 alla pubblicazione : Quasi è il mio nome',3),(48,4,11,'2019-07-18 14:55:57','franz94 ha aggiunto l\'autore 4 alla pubblicazione : Quasi è il mio nome',3),(49,4,11,'2019-07-18 14:55:57','franz94 ha aggiunto l\'autore 5 alla pubblicazione : Quasi è il mio nome',3),(50,5,11,'2019-07-18 14:55:57','franz94 ha aggiunto l\'autore 6 alla pubblicazione : Un mondo migliore. Ritratti',3),(51,6,12,'2019-07-18 14:55:58','ricky66 ha aggiunto l\'autore 7 alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',3),(52,7,12,'2019-07-18 14:55:58','ricky66 ha aggiunto l\'autore 8 alla pubblicazione : Il visitatore',3),(53,8,12,'2019-07-18 14:55:58','ricky66 ha aggiunto l\'autore 8 alla pubblicazione : Mister Napoleone',3),(54,8,1,'2019-07-18 14:55:58','Admin ha aggiunto l\'autore 9 alla pubblicazione : Mister Napoleone',3),(55,8,11,'2019-07-18 14:55:58','franz94 ha aggiunto l\'autore 1 alla pubblicazione : Mister Napoleone',3),(56,9,12,'2019-07-18 14:55:58','ricky66 ha aggiunto l\'autore 2 alla pubblicazione : Norvegia. The passenger',3),(57,10,11,'2019-07-18 14:55:59','franz94 ha aggiunto l\'autore 3 alla pubblicazione : Cuore di lupo',3),(58,11,12,'2019-07-18 14:55:59','ricky66 ha aggiunto l\'autore 4 alla pubblicazione : Falso in bilancia',3),(59,3,10,'2019-07-18 14:57:37','Mario92 ha aggiunto una parola chiave alla pubblicazione : Limbo. Pensieri inversi',3),(60,3,10,'2019-07-18 14:57:37','Mario92 ha aggiunto una parola chiave alla pubblicazione : Limbo. Pensieri inversi',3),(61,4,10,'2019-07-18 14:57:37','Mario92 ha aggiunto una parola chiave alla pubblicazione : Quasi è il mio nome',3),(62,4,11,'2019-07-18 14:57:37','franz94 ha aggiunto una parola chiave alla pubblicazione : Quasi è il mio nome',3),(63,4,11,'2019-07-18 14:57:37','franz94 ha aggiunto una parola chiave alla pubblicazione : Quasi è il mio nome',3),(64,5,11,'2019-07-18 14:57:38','franz94 ha aggiunto una parola chiave alla pubblicazione : Un mondo migliore. Ritratti',3),(65,6,12,'2019-07-18 14:57:38','ricky66 ha aggiunto una parola chiave alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',3),(66,7,12,'2019-07-18 14:57:38','ricky66 ha aggiunto una parola chiave alla pubblicazione : Il visitatore',3),(67,8,12,'2019-07-18 14:57:38','ricky66 ha aggiunto una parola chiave alla pubblicazione : Mister Napoleone',3),(68,8,1,'2019-07-18 14:57:38','Admin ha aggiunto una parola chiave alla pubblicazione : Mister Napoleone',3),(69,8,11,'2019-07-18 14:57:38','franz94 ha aggiunto una parola chiave alla pubblicazione : Mister Napoleone',3),(70,9,12,'2019-07-18 14:57:39','ricky66 ha aggiunto una parola chiave alla pubblicazione : Norvegia. The passenger',3),(71,10,11,'2019-07-18 14:57:39','franz94 ha aggiunto una parola chiave alla pubblicazione : Cuore di lupo',3),(72,11,12,'2019-07-18 14:57:39','ricky66 ha aggiunto una parola chiave alla pubblicazione : Falso in bilancia',3),(73,NULL,1,'2019-07-18 14:59:40','Admin ha messo like alla pubblicazione:  Senzanima. Buio.',7),(74,NULL,14,'2019-07-18 15:03:35','erin33 ha messo like alla pubblicazione:  Senzanima. Buio.',7),(75,7,14,'2019-07-18 15:03:36','erin33 ha messo like alla pubblicazione:  Il visitatore',7),(76,7,15,'2019-07-18 15:03:36','sandro23 ha messo like alla pubblicazione:  Il visitatore',7),(77,7,16,'2019-07-18 15:03:36','doge99 ha messo like alla pubblicazione:  Il visitatore',7),(78,3,17,'2019-07-18 15:03:36','Mario96 ha messo like alla pubblicazione:  Limbo. Pensieri inversi',7),(79,3,18,'2019-07-18 15:03:37','carmelo33 ha messo like alla pubblicazione:  Limbo. Pensieri inversi',7),(80,3,11,'2019-07-18 15:03:37','franz94 ha messo like alla pubblicazione:  Limbo. Pensieri inversi',7),(81,4,12,'2019-07-18 15:03:37','ricky66 ha messo like alla pubblicazione:  Quasi è il mio nome',7),(82,4,16,'2019-07-18 15:03:38','doge99 ha messo like alla pubblicazione:  Quasi è il mio nome',7),(83,4,14,'2019-07-18 15:03:38','erin33 ha messo like alla pubblicazione:  Quasi è il mio nome',7),(84,5,15,'2019-07-18 15:03:39','sandro23 ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(85,5,16,'2019-07-18 15:03:39','doge99 ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(86,5,17,'2019-07-18 15:03:39','Mario96 ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(87,6,18,'2019-07-18 15:03:40','carmelo33 ha messo like alla pubblicazione:  L\'eredità di Agneta. Le signore di Lowenhof',7),(88,6,19,'2019-07-18 15:03:40','kyle96 ha messo like alla pubblicazione:  L\'eredità di Agneta. Le signore di Lowenhof',7),(89,7,21,'2019-07-18 15:03:41','morgana22 ha messo like alla pubblicazione:  Il visitatore',7),(90,8,20,'2019-07-18 15:03:41','darius88 ha messo like alla pubblicazione:  Mister Napoleone',7),(91,9,21,'2019-07-18 15:03:41','morgana22 ha messo like alla pubblicazione:  Norvegia. The passenger',7),(92,3,11,'2019-07-18 15:12:15','è stata approvata la recensione di: franz94 del libro: Limbo. Pensieri inversi',4),(93,3,11,'2019-07-18 15:12:15','è stata approvata la recensione di: ricky66 del libro: Limbo. Pensieri inversi',4),(94,4,11,'2019-07-18 15:12:15','è stata approvata la recensione di: franz94 del libro: Quasi è il mio nome',4),(95,4,11,'2019-07-18 15:12:15','è stata approvata la recensione di: erin33 del libro: Quasi è il mio nome',4),(96,5,11,'2019-07-18 15:12:16','è stata approvata la recensione di: franz94 del libro: Un mondo migliore. Ritratti',4),(97,5,11,'2019-07-18 15:12:16','è stata approvata la recensione di: erin33 del libro: Un mondo migliore. Ritratti',4),(98,8,11,'2019-07-18 15:12:16','è stata approvata la recensione di: ricky66 del libro: Mister Napoleone',4),(99,8,11,'2019-07-18 15:12:17','è stata approvata la recensione di: doge99 del libro: Mister Napoleone',4),(100,9,11,'2019-07-18 15:12:17','è stata approvata la recensione di: ricky66 del libro: Norvegia. The passenger',4),(101,9,11,'2019-07-18 15:12:18','è stata approvata la recensione di: Mario96 del libro: Norvegia. The passenger',4),(102,10,11,'2019-07-18 15:12:19','è stata approvata la recensione di: ricky66 del libro: Cuore di lupo',4),(103,10,11,'2019-07-18 15:12:20','è stata approvata la recensione di: carmelo33 del libro: Cuore di lupo',4),(104,14,12,'2019-07-18 15:31:45','ricky66 ha inserito la pubblicazione titolata: titolo',1),(105,14,10,'2019-07-18 19:15:48','Mario92 ha cambiato il titolo della pubblicazione in : Bar Sport',3),(106,14,12,'2019-07-19 06:44:30','ricky66 ha inserito una recensione alla pubblicazione : Bar Sport',3),(107,14,10,'2019-07-19 06:50:02','è stata approvata la recensione di: ricky66 del libro: Bar Sport',4),(108,14,12,'2019-07-19 06:52:54','ricky66 ha messo like alla pubblicazione:  Bar Sport',7),(109,14,10,'2019-07-19 06:58:58','Mario92 ha eliminato la recensione diricky66per la pubblicazione:  Bar Sport',6),(112,12,12,'2019-07-19 07:09:43','ricky66 ha messo like alla pubblicazione:  Come una notte a Bali',7),(114,12,12,'2019-07-19 07:13:13','ricky66 ha messo like alla pubblicazione:  Come una notte a Bali',7),(115,12,12,'2019-07-19 07:19:45','ricky66 ha rimosso il like alla pubblicazione : Come una notte a Bali',8),(116,12,1,'2019-07-19 12:07:55','Admin ha cambiato il titolo della pubblicazione in : caio',3),(117,12,1,'2019-07-19 12:08:34','Admin ha cambiato il codice ISBN della pubblicazione : caio in 2',3),(118,12,1,'2019-07-19 12:08:58','Admin ha cambiato l’editore della pubblicazione : caio in edit',3),(119,12,1,'2019-07-19 12:09:10','Admin ha cambiato l’indice della pubblicazione : caio in indicizzo',3),(120,12,1,'2019-07-19 12:09:22','Admin ha cambiato la descrizione della pubblicazione : caio in descrivo',3),(121,12,1,'2019-07-19 12:10:02','Admin ha cambiato la data di pubblicazione di  : caio in 1999-06-06',3),(122,12,1,'2019-07-19 12:10:14','Admin ha cambiato il numero di pagine della pubblicazione : caio in 666',3),(123,12,12,'2019-07-19 12:11:16','ricky66 ha cambiato la lingua della pubblicazione : caio in arabo',3),(124,10,1,'2019-07-19 12:12:28','Admin ha aggiunto una parola chiave alla pubblicazione : Cuore di lupo',3),(125,10,1,'2019-07-19 12:12:48','Admin ha rimosso una parola chiave alla pubblicazione : Cuore di lupo',3),(126,10,10,'2019-07-19 12:14:38','Mario92 ha inserito una recensione alla pubblicazione : Cuore di lupo',3),(127,10,1,'2019-07-19 12:17:58','Admin ha aggiunto l\'autore 10 alla pubblicazione : Cuore di lupo',3),(128,10,1,'2019-07-19 12:18:21','Admin ha rimosso un autore alla pubblicazione : Cuore di lupo',3),(129,10,1,'2019-07-19 12:21:10','Admin ha aggiunto una ristampa alla pubblicazione : Cuore di lupo',3),(130,NULL,1,'2019-07-19 12:24:36','Admin ha rimosso una sorgente della pubblicazione : Senzanima. Buio.',3),(131,10,1,'2019-07-19 12:29:33','Admin ha aggiunto una ristampa alla pubblicazione : Cuore di lupo',3),(132,10,1,'2019-07-19 12:29:54','Admin ha rimosso una ristampa della pubblicazione : Cuore di lupo',3),(133,10,15,'2019-07-19 12:32:38','sandro23 ha inserito una recensione alla pubblicazione : Cuore di lupo',5),(134,NULL,1,'2019-07-19 12:35:47','Admin ha eliminato la pubblicazione titolata : Senzanima. Buio.',2),(135,6,1,'2019-07-19 12:37:50','Admin ha aggiunto una ristampa alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',3),(136,6,1,'2019-07-19 12:38:07','Admin ha aggiunto una ristampa alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',3),(137,6,24,'2019-10-09 21:07:56','swq ha inserito una recensione alla pubblicazione : L\'eredità di Agneta. Le signore di Lowenhof',5),(138,6,12,'2019-10-09 21:08:41','è stata approvata la recensione di: swq del libro: L\'eredità di Agneta. Le signore di Lowenhof',4),(139,9,12,'2019-10-10 10:21:15','ricky66 ha messo like alla pubblicazione:  Norvegia. The passenger',7),(140,9,24,'2019-10-10 10:44:27','swq ha messo like alla pubblicazione:  Norvegia. The passenger',7),(141,11,24,'2019-10-10 10:46:19','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(142,11,24,'2019-10-10 10:46:20','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(143,11,24,'2019-10-10 10:46:20','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(144,11,24,'2019-10-10 10:46:21','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(145,11,24,'2019-10-10 10:46:21','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(146,11,24,'2019-10-10 10:46:22','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(147,11,24,'2019-10-10 10:46:22','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(148,11,24,'2019-10-10 10:46:23','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(149,11,24,'2019-10-10 10:46:23','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(150,11,24,'2019-10-10 10:46:23','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(151,11,24,'2019-10-10 10:46:36','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(152,11,24,'2019-10-10 10:46:37','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(153,11,24,'2019-10-10 10:46:45','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(154,11,24,'2019-10-10 10:47:03','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(155,11,24,'2019-10-10 10:47:08','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(156,7,24,'2019-10-10 10:51:14','swq ha messo like alla pubblicazione:  Il visitatore',7),(157,7,24,'2019-10-10 10:51:16','swq ha rimosso il like alla pubblicazione : Il visitatore',8),(158,7,24,'2019-10-10 10:51:19','swq ha messo like alla pubblicazione:  Il visitatore',7),(159,7,24,'2019-10-10 10:51:23','swq ha rimosso il like alla pubblicazione : Il visitatore',8),(160,7,24,'2019-10-10 10:51:26','swq ha messo like alla pubblicazione:  Il visitatore',7),(161,14,24,'2019-10-10 10:51:31','swq ha messo like alla pubblicazione:  Bar Sport',7),(162,8,24,'2019-10-10 10:51:34','swq ha messo like alla pubblicazione:  Mister Napoleone',7),(163,10,24,'2019-10-10 10:54:06','è stata approvata la recensione di: sandro23 del libro: Cuore di lupo',4),(164,6,1,'2019-10-10 11:00:20','è stata approvata la recensione di: erin33 del libro: L\'eredità di Agneta. Le signore di Lowenhof',4),(165,7,1,'2019-10-10 11:00:21','è stata approvata la recensione di: ricky66 del libro: Il visitatore',4),(166,11,24,'2019-10-10 12:13:35','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(167,11,24,'2019-10-10 12:13:37','swq ha messo like alla pubblicazione:  Falso in bilancia',7),(168,11,24,'2019-10-10 12:13:38','swq ha rimosso il like alla pubblicazione : Falso in bilancia',8),(169,4,24,'2019-10-10 12:27:46','è stata approvata la recensione di: kyle96 del libro: Quasi è il mio nome',4),(170,10,1,'2019-10-10 12:32:27','è stata approvata la recensione di: erin33 del libro: Cuore di lupo',4),(171,5,1,'2019-10-10 12:32:46','Admin ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(172,5,1,'2019-10-10 12:32:53','Admin ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(173,5,1,'2019-10-10 12:32:55','Admin ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(174,5,1,'2019-10-10 12:33:01','Admin ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(175,5,1,'2019-10-10 12:33:05','Admin ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(176,5,1,'2019-10-10 12:33:05','Admin ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(177,7,10,'2019-10-16 17:33:18','ciccio ha cambiato il titolo della pubblicazione in : Il visitatore 2',3),(178,10,10,'2019-10-16 17:36:32','ciccio ha messo like alla pubblicazione:  Cuore di lupo',7),(179,10,10,'2019-10-16 20:49:05','ciccio ha cambiato la descrizione della pubblicazione : Cuore di lupo in L\'auto su cui sta viaggiando Tinebra insieme ai suoi genitori frena bruscamente\nsulla neve: in mezzo alla strada, tre ragazzi hanno catturato una lupa e la stanno picchiando.',3),(180,8,10,'2019-10-17 11:21:17','ciccio ha messo like alla pubblicazione:  Mister Napoleone',7),(181,15,10,'2019-10-17 12:04:08','ciccio ha inserito la pubblicazione titolata: I testamenti',1),(182,15,10,'2019-10-17 12:04:09','ciccio ha aggiunto l\'autore 12 alla pubblicazione : I testamenti',3),(183,15,10,'2019-10-17 12:04:09','ciccio ha aggiunto una parola chiave alla pubblicazione : I testamenti',3),(184,15,10,'2019-10-17 12:04:09','ciccio ha aggiunto una parola chiave alla pubblicazione : I testamenti',3),(185,15,10,'2019-10-17 12:04:09','ciccio ha aggiunto una ristampa alla pubblicazione : I testamenti',3),(186,15,10,'2019-10-17 12:04:09','ciccio ha aggiunto una sorgente alla pubblicazione : I testamenti',3),(187,15,10,'2019-10-17 12:04:26','ciccio ha messo like alla pubblicazione:  I testamenti',7),(188,6,1,'2019-10-17 12:34:43','è stata approvata la recensione di: darius88 del libro: L\'eredità di Agneta. Le signore di Lowenhof',4),(189,6,10,'2019-10-17 15:03:26','ciccio ha messo like alla pubblicazione:  L\'eredità di Agneta. Le signore di Lowenhof',7),(190,3,28,'2019-10-18 18:33:33','Ale ha messo like alla pubblicazione:  Limbo. Pensieri inversi',7),(191,3,28,'2019-10-18 18:34:21','Ale ha inserito una recensione alla pubblicazione : Limbo. Pensieri inversi',5),(192,16,28,'2019-10-18 18:39:50','Ale ha inserito la pubblicazione titolata: L\'orologiaio di Filigree Street',1),(193,16,28,'2019-10-18 18:39:50','Ale ha aggiunto l\'autore 13 alla pubblicazione : L\'orologiaio di Filigree Street',3),(194,16,28,'2019-10-18 18:39:51','Ale ha aggiunto una parola chiave alla pubblicazione : L\'orologiaio di Filigree Street',3),(195,3,28,'2019-10-23 20:17:44','è stata approvata la recensione di: Ale del libro: Limbo. Pensieri inversi',4),(196,3,1,'2019-10-24 12:37:26','Admin ha messo like alla pubblicazione:  Limbo. Pensieri inversi',7),(197,9,1,'2019-10-24 17:37:25','Admin ha messo like alla pubblicazione:  Norvegia. The passenger',7),(198,9,1,'2019-10-24 17:37:40','Admin ha cambiato la descrizione della pubblicazione : Norvegia. The passenger in Descrizione...0',3),(199,16,28,'2019-10-25 18:32:11','Ale ha messo like alla pubblicazione:  L\'orologiaio di Filigree Street',7),(200,7,28,'2019-10-25 18:35:24','Ale ha messo like alla pubblicazione:  Il visitatore 2',7),(201,7,28,'2019-10-25 18:35:29','Ale ha rimosso il like alla pubblicazione : Il visitatore 2',8),(202,7,28,'2019-10-25 18:35:38','Ale ha messo like alla pubblicazione:  Il visitatore 2',7),(203,7,28,'2019-10-25 18:35:43','Ale ha rimosso il like alla pubblicazione : Il visitatore 2',8),(204,7,1,'2019-11-26 21:34:02','Admin ha messo like alla pubblicazione:  Il visitatore 2',7),(205,7,1,'2019-11-26 21:34:05','Admin ha rimosso il like alla pubblicazione : Il visitatore 2',8),(206,5,29,'2019-11-29 12:48:37','provax ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(207,5,29,'2019-11-29 12:48:46','provax ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(208,5,29,'2019-11-29 12:48:47','provax ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(209,5,29,'2019-11-29 12:48:47','provax ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(210,5,29,'2019-11-29 12:48:49','provax ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(211,5,29,'2019-11-29 12:48:50','provax ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(212,5,29,'2019-11-29 12:48:52','provax ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(213,5,29,'2019-11-29 12:48:54','provax ha rimosso il like alla pubblicazione : Un mondo migliore. Ritratti',8),(214,5,29,'2019-11-29 12:48:54','provax ha messo like alla pubblicazione:  Un mondo migliore. Ritratti',7),(215,10,1,'2019-12-11 10:41:57','Admin ha messo like alla pubblicazione:  Cuore di lupo',7),(216,10,1,'2019-12-11 10:41:59','Admin ha rimosso il like alla pubblicazione : Cuore di lupo',8),(217,4,1,'2019-12-11 10:42:14','Admin ha messo like alla pubblicazione:  Quasi è il mio nome',7),(218,5,1,'2019-12-13 12:57:04','è stata approvata la recensione di: darius88 del libro: Un mondo migliore. Ritratti',4);
/*!40000 ALTER TABLE `storia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `IDPubblicazione` int(10) unsigned NOT NULL,
  `IDParolaChiave` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDPubblicazione`,`IDParolaChiave`),
  KEY `fk_Pubblicazione_has_Parole Chiave_Parole Chiave1_idx` (`IDParolaChiave`),
  KEY `fk_Pubblicazione_has_Parole Chiave_Pubblicazione1_idx` (`IDPubblicazione`),
  CONSTRAINT `fk_Pubblicazione_has_Parole Chiave_Parole Chiave1` FOREIGN KEY (`IDParolaChiave`) REFERENCES `parolachiave` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Pubblicazione_has_Parole Chiave_Pubblicazione1` FOREIGN KEY (`IDPubblicazione`) REFERENCES `pubblicazione` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (7,1),(8,2),(8,3),(8,4),(9,5),(10,6),(15,6),(4,7),(5,7),(6,7),(11,7),(4,8),(4,9),(3,10),(3,11),(15,13),(16,14);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Nickname` varchar(45) NOT NULL,
  `Password` varchar(100) DEFAULT NULL,
  `Ruolo` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `UtenteEliminato` bit(1) NOT NULL DEFAULT b'0',
  `DataUltimaRecensione` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email_UNIQUE` (`Nickname`),
  KEY `indextipo` (`Ruolo`) /*!80000 INVISIBLE */
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Admin','$2a$10$sG8tb2dZP1JT6I.p41D0jONLScIODMCij96wqVDicfiPpuRGj.0hC',5,_binary '\0',NULL),(10,'ciccio','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',4,_binary '\0','2019-07-19'),(11,'franz94','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '\0','2019-07-18'),(12,'ricky66','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',4,_binary '\0','2019-07-19'),(14,'erin33','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '','2019-07-19'),(15,'sandro23','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',4,_binary '\0','2019-07-19'),(16,'doge99','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '\0','2019-07-18'),(17,'Mario96','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',4,_binary '\0','2019-07-18'),(18,'carmelo33','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '\0','2019-07-18'),(19,'kyle96','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',1,_binary '\0','2019-07-18'),(20,'darius88','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',1,_binary '\0','2019-07-18'),(21,'morgana22','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '\0',NULL),(22,'garen11','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',3,_binary '\0',NULL),(23,'nick1','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',1,_binary '\0',NULL),(24,'swq','$2a$10$PGx3yrB/bfmORRAPoo6.xOXZ7zq2TDhcPvreLsxlN4zg5wU7LQ6l6',1,_binary '\0','2019-10-09'),(25,'Prova','$2a$10$FU2DW0Rc5LMxUqTU5kBRXuF/MA9mAI5StIsaDrbperSo61DBBA3J6',1,_binary '\0',NULL),(26,'Francy','$2a$10$uXKT1xya1gFDQeiBjiEpM.5zGGY8BKOhEFnpwK4huBC9wR2aZc7Uy',1,_binary '\0',NULL),(27,'danieledd','$2a$10$18NTS.210fmcXM2K76jyHeSK78U9z9uxjC05wDYFq5p0tAMu9yb1C',1,_binary '\0',NULL),(28,'Ale','$2a$10$unY.5EJz5ndNjrX6qpWBduYySqGZcATl1Nxz06eOWh3MDgCHL8v6q',4,_binary '\0','2019-10-18'),(29,'provax','$2a$10$VQXErGjQq3FOFoAwqFowpOvHbHxsidecbmC8w6QG1qkiKypcxju8a',1,_binary '\0',NULL),(30,'prova1','$2a$10$v3.nTz9MEiT8.fW7l9JNWuLM1K9y7GHeHF0shy1Dc0zR8R1SlQiMK',1,_binary '\0',NULL),(31,'nick2','$2a$10$WJriFPfwihzvxIfSX.0HxOHCaOf8ElWB4sm.FYKP3tZHK0/PoPDQm',1,_binary '\0',NULL);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'progettodb'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `PassaggioAttivoPassivo` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = '+02:00' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `PassaggioAttivoPassivo` ON SCHEDULE EVERY 1 WEEK STARTS '2019-07-18 03:00:00' ON COMPLETION NOT PRESERVE ENABLE DO UPDATE utente SET Ruolo = 1 WHERE Ruolo = 2 AND datediff(CURDATE(), DataUltimaRecensione ) > 60 */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'progettodb'
--
/*!50003 DROP PROCEDURE IF EXISTS `ApprovazioneRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ApprovazioneRecensione`( IDAppr INT, IDPubb INT, IDUtnt INT)
BEGIN 
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
SELECT NickName into nick FROM utente WHERE ID = IDUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	
IF (  ( SELECT FlagApprovazione FROM Recensione r WHERE  r.IDPubblicazione = IDPubb AND r.IDUtente = IDUtnt ) = 0 ) THEN
UPDATE Recensione r SET r.FlagApprovazione = 1 WHERE r.IDPubblicazione = IDPubb AND r.IDUtente = IDUtnt;
UPDATE Recensione r SET r.ApprovatoDa = IDAppr WHERE r.IDPubblicazione = IDPubb       AND r.IDUtente = IDUtnt;

INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) 	VALUES (IDPubb, IDAppr, now(), concat( "è stata approvata la recensione di: ", nick, " del libro: ", tit ), 4 );

	UPDATE Pubblicazione P SET P.NumRec = P.NumRec + 1 WHERE P.ID = IDPubb;
END IF;	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delAutore`( idPubb INT, ida INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM scritto WHERE scritto.IDPubblicazione = idPubb AND scritto.IDAutore = ida;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso un autore alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delCascadeAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delCascadeAutore`(IN idAut INT, IN idUtente INT)
BEGIN
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_idPubb INT;
    DECLARE v_idAutore INT;
    DECLARE cursore CURSOR FOR (SELECT * FROM Scritto s WHERE idAut = s.IDAutore);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cursore;
    
    ciclo: LOOP
		FETCH cursore INTO v_idPubb, v_idAutore;
			IF(done = TRUE) THEN 
				LEAVE ciclo;
			END IF;
            
			-- eliminazione degli autori e scrittura su storia
			CALL delAutore(v_idPubb, idAut, idUtente);
	END LOOP;
    CLOSE cursore;    
    DELETE FROM Autore WHERE ID = idAut;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delCascadeTag` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delCascadeTag`(IN idPC INT, IN idUtente INT)
BEGIN		
    DECLARE done INT DEFAULT FALSE;
    DECLARE v_idPubb INT;
    DECLARE v_idPC INT;
    DECLARE cursore CURSOR FOR (SELECT * FROM Tag t WHERE idPC = t.IDParolaChiave);
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
    OPEN cursore;
    
    ciclo: LOOP
		FETCH cursore INTO v_idPubb, v_idPC;
			IF(done = TRUE) THEN 
				LEAVE ciclo;
			END IF;
            
			-- eliminazione degli autori e scrittura su storia
			CALL delParolaChiave(v_idPubb, idPC, idUtente);
	END LOOP;
    CLOSE cursore;
    
    DELETE FROM ParolaChiave WHERE ID = idPC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delLike` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delLike`( idUtnt INT , idPubb INT )
BEGIN 
	
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);

SELECT NickName into nick FROM utente WHERE ID = idUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

DELETE FROM `Like` WHERE `Like`.IDUtente = idUtnt AND `Like`.IDPubblicazione = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtnt, now(), CONCAT( nick, " ha rimosso il like alla pubblicazione : ", tit), 8);

UPDATE Pubblicazione P SET P.NumLike = P.NumLike - 1 WHERE P.ID = idPubb;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delParolaChiave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delParolaChiave`( idPubb INT, idpc INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
DECLARE stamp DATETIME;
SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	DELETE FROM tag WHERE tag.IDPubblicazione = idPubb AND tag.IDParolaChiave = idpc;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una parola chiave alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delPubb`( idPubb INT, idUtente INT )
BEGIN
DECLARE nick VARCHAR(45);
DECLARE tit VARCHAR(100);
DECLARE idIns INT;

SELECT NickName into nick FROM utente WHERE ID = idUtente;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
SELECT IDUtente into idIns FROM storia WHERE Tipo = 1 AND IDPubblicazione = idPubb;

UPDATE datiModeratore D SET D.NumPubb = D.NumPubb - 1 WHERE D.IDUtente = idIns;

DELETE FROM pubblicazione WHERE ID = idPubb;

INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
( NULL, idUtente, NOW(), CONCAT( nick, " ha eliminato la pubblicazione titolata : ", tit), 2);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delRecensione`( idUtnt INT, idPubb INT, idElimin INT )
BEGIN 
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
DECLARE nickDel VARCHAR(45);
DECLARE approvato BIT;
SELECT NickName into nick FROM utente WHERE ID = idUtnt;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
SELECT NickName into nickDel FROM utente WHERE ID = idElimin;
SELECT FlagApprovazione into approvato FROM recensione WHERE IDPubblicazione = idPubb AND IDUtente = idUtnt;

DELETE FROM recensione WHERE IDPubblicazione = idPubb AND IDUtente = idUtnt;

IF ( approvato = 1 ) THEN
UPDATE pubblicazione SET NumRec = NumRec - 1 WHERE ID = idPubb;
END IF;
IF ( idUtnt = idElimin ) THEN
INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) VALUES 
(idPubb, idElimin, now(), CONCAT( nick, " ha eliminato la propria recensione per la pubblicazione:  ",tit ),6 );
ELSE
INSERT INTO Storia (IDPubblicazione, IDUtente, `Timestamp`, Descrizione, Tipo) VALUES 
(idPubb, idElimin, now(), CONCAT( nickDel, " ha eliminato la recensione di", nick, "per la pubblicazione:  ",tit ),6 );
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delRistampa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delRistampa`( idPubb INT, idRistampa INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM ristampa WHERE ID = idRistampa;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una ristampa della pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delSorgente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delSorgente`( idPubb INT, idSorgente INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp TIMESTAMP;
	SET stamp= NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	DELETE FROM sorgente WHERE ID = idSorgente;
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha rimosso una sorgente della pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delUtente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delUtente`( idUtnt INT )
BEGIN
UPDATE utente SET UtenteEliminato = 1 WHERE ID = idUtnt;
DELETE FROM datiUtente WHERE IDUtente = idUtnt;
DELETE FROM datiModeratore WHERE IDUtente = idUtnt;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoAutore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoAutore`( idPubb INT, ida INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	INSERT INTO scritto(IDPubblicazione,IDAutore) VALUES ( idPubb, ida );
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto l'autore ", ida, " alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoLike` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoLike`( idUtente INT , idPubb INT )
BEGIN 
	
DECLARE ora TIMESTAMP;
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);

SET ora = now();
SELECT NickName into nick FROM utente WHERE ID = idUtente;
SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

INSERT INTO `Like` VALUES (idPubb, idUtente, ora);

INSERT INTO Storia (IDPubblicazione, IDUtente, Timestamp, Descrizione, Tipo) VALUES 
(idPubb, idUtente, ora, 
CONCAT( nick, " ha messo like alla pubblicazione:  ",tit ),7 );

UPDATE Pubblicazione P SET P.NumLike = P.NumLike + 1 WHERE P.ID = idPubb;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoParolaChiave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoParolaChiave`( idPubb INT, idpc INT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	INSERT INTO tag(IDPubblicazione, IDParolaChiave) VALUES ( idPubb, idpc );
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una parola chiave alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoPubb`(IN idUtnt INT, IN ISBNPubb  CHAR(13), IN titl VARCHAR(45), IN editr VARCHAR(45), 
IN dataPubb DATE, IN nPg INT, IN ling VARCHAR(45) , IN descrzn TEXT, IN indc TEXT, OUT idPubb INT )
BEGIN
    DECLARE nick VARCHAR(45);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
    
    SELECT Nickname FROM utente WHERE ID = idUtnt INTO nick;

    UPDATE datimoderatore SET NumPubb = NumPubb + 1 WHERE IDUtente = idUtnt  ;
    
    INSERT INTO pubblicazione ( `ISBN`,`Titolo`,`Editore`,`DataUltimaModifica`,`DataPubblicazione`)
    VALUES (ISBNPubb,titl,editr,DATE(stamp),dataPubb);
    SET idPubb = LAST_INSERT_ID();
    INSERT INTO metadati ( `IDPubblicazione`,`Npag`,`Lingua`,`Descrizione`,`Indice`)
    VALUES (idPubb,nPg,ling,descrzn,indc);
    
    INSERT INTO storia ( `IDPubblicazione`,`IDUtente`,`Timestamp`,`Descrizione`,`Tipo` )
    VALUES ( idPubb,idUtnt,stamp, concat( nick, " ha inserito la pubblicazione titolata: ", titl), 1);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoRecensione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoRecensione`( idUtente INT, idPubb INT, Des TEXT)
BEGIN 	
DECLARE dataoggi DATE;
DECLARE tit VARCHAR(100);
DECLARE nick VARCHAR(45);
DECLARE stamp DATETIME;

SET stamp = NOW();
SELECT NickName INTO nick FROM utente WHERE ID = idUtente;
SELECT Titolo INTO tit FROM pubblicazione WHERE ID = idPubb;

INSERT INTO Recensione ( `IDPubblicazione`, `IDUtente`, `Descrizione`, `Timestamp` ) VALUES (IDPubb, IDUtente, Des, stamp);

INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
( idPubb, idUtente, stamp, CONCAT( nick, " ha inserito una recensione alla pubblicazione : ", tit), 5);

UPDATE utente SET utente.DataUltimaRecensione = DATE(stamp) WHERE ID = idUtente;
UPDATE utente SET Ruolo = 2 WHERE ID = idUtente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoRistampa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoRistampa`( idPubb INT, dtRis DATE, num TINYINT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	INSERT INTO ristampa(IDPubblicazione, DataRistampa, Numero) VALUES ( IDPubb, dtRis, num );
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una ristampa alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoSorgente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoSorgente`( idPubb INT, ur VARCHAR(2083), tp VARCHAR(45), frmt VARCHAR(45), des TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
	SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	INSERT INTO sorgente( IDPubblicazione, URI, Tipo, Formato, Descrizione ) VALUES ( IDPubb, ur, tp, frmt, des );
	 
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha aggiunto una sorgente alla pubblicazione : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `inserimentoUtente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `inserimentoUtente`(nick VARCHAR(45), email VARCHAR(254), pass VARCHAR(100), nome VARCHAR(45), cogn VARCHAR(45), dNascita DATE, lNascita VARCHAR(100), residenza VARCHAR(200), OUT IDUtente INT )
BEGIN

INSERT INTO utente ( `Nickname`, `Password` ) VALUES ( nick, pass );
SET IDUtente = LAST_INSERT_ID();
INSERT INTO datiutente( `IDUtente`, `Nome`, `Cognome`, `DataNascita`, `LuogoNascita`, `Residenza`,`Email`)
VALUES ( IDUtente, nome, cogn, dNascita, lNascita, residenza,email );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaDataPubb` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaDataPubb`( idPubb INT, dt DATE, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.DataPubblicazione = dt WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la data di pubblicazione di  : ", tit, " in ", dt) , 3 );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaDescrizione` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaDescrizione`( idPubb INT, des TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
	DECLARE stamp DATETIME;
    	SET stamp = NOW();
    
	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;
	UPDATE metadati m SET m.Descrizione = des WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la descrizione della pubblicazione : ", tit, " in ", des), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaEditore` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaEditore`( idPubb INT, Edit VARCHAR(45), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.Editore = Edit WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato l’editore della pubblicazione : ", tit, " in ", Edit) , 3 );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaIndice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaIndice`( idPubb INT, ind TEXT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.Indice = ind WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato l’indice della pubblicazione : ", tit, " in ", ind), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaISBN` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaISBN`( idPubb INT, codiceisbn CHAR(13), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE pubblicazione p SET p.ISBN = codiceisbn WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = CURDATE() WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, NOW(), CONCAT( nick, " ha cambiato il codice ISBN della pubblicazione : ", tit, " in ", codiceisbn) , 3 );
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaLingua` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaLingua`( idPubb INT, lin VARCHAR(45), idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    	DECLARE stamp DATETIME;
    	SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.Lingua = lin WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;
	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato la lingua della pubblicazione : ", tit, " in ", lin), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaNPag` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaNPag`( idPubb INT, np SMALLINT, idUtente INT )
BEGIN
	DECLARE nick VARCHAR(45);
	DECLARE tit VARCHAR(100);
    DECLARE stamp DATETIME;
    SET stamp = NOW();

	SELECT NickName into nick FROM utente WHERE ID = idUtente;
	SELECT Titolo into tit FROM pubblicazione WHERE ID = idPubb;

	UPDATE metadati m SET m.NPag = np WHERE m.IDPubblicazione = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato il numero di pagine della pubblicazione : ", tit, " in ", np), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificaTitolo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificaTitolo`( idPubb INT, tit VARCHAR(100), idUtente INT )
BEGIN
	DECLARE stamp DATETIME;
	DECLARE nick VARCHAR(45);
    SET stamp = NOW();
	SELECT NickName into nick FROM utente WHERE ID = idUtente;

	UPDATE pubblicazione p SET p.Titolo = tit WHERE p.ID = idPubb;
	UPDATE pubblicazione p SET p.DataUltimaModifica = DATE(stamp) WHERE p.ID = idPubb;

	INSERT INTO storia ( `IDPubblicazione`, `IDUtente`, `Timestamp`, `Descrizione`, `Tipo` ) VALUES
	( idPubb, idUtente, stamp, CONCAT( nick, " ha cambiato il titolo della pubblicazione in : ", tit), 3);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `promModer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `promModer`( idProm INT,  idUtente INT )
BEGIN

UPDATE utente u SET u.Ruolo = 3 WHERE u.ID = idUtente;
INSERT INTO datimoderatore( IDUtente, NumPubb, DataPromozione, Promotore) 
VALUES ( IDUtente, 0, CURDATE(), idProm );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ricercaAutori` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricercaAutori`( nm VARCHAR(45), cgnm VARCHAR(45) )
BEGIN 
	IF ( nm IS NULL  ) THEN
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.cognome = cgnm;
	ELSEIF( cgnm IS NULL ) THEN
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.nome = nm;
	ELSE
		SELECT p.* 
        FROM pubblicazione p
		INNER JOIN scritto s ON s.IDPubblicazione = p.ID
		INNER JOIN autore a ON s.IDAutore = a.ID
		WHERE a.nome = nm AND a.cognome = cgnm;
	END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-05 19:52:29
