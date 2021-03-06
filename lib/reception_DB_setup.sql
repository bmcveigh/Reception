/* -------------- Set up tables portion ---------------- */
DROP TABLE IF EXISTS `RESERVATION`;
DROP TABLE IF EXISTS `GUEST`;
DROP TABLE IF EXISTS `ROOM`;
DROP TABLE IF EXISTS `ROOM_CATEGORY`;
DROP TABLE IF EXISTS ADMIN;

CREATE TABLE IF NOT EXISTS `GUEST` (`G_ID` int(10) PRIMARY KEY,`G_FIRST` CHAR(50),`G_LAST` CHAR(50),`G_ADDRESS` CHAR(50),`G_CITY` CHAR(50),`G_STATE` CHAR(2),`G_ZIP` CHAR(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ROOM_CATEGORY`(`RC_ID` int(10) PRIMARY KEY, `RC_NAME` CHAR(50), `RC_RATE` int(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ROOM` (`R_ID` int(10) PRIMARY KEY,`RC_ID` int(10),`R_AVAILABLE` int(1),`R_PIN` int(6)) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE IF NOT EXISTS `RESERVATION` (`RESERV_ID` int(10) PRIMARY KEY,`G_ID` int(10),`R_ID` int(10),`RESERV_DATE`DATE,`RESERV_STATUS` int(1),`RESERV_NIGHT` int(10)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `ADMIN` (`A_NAME` CHAR(10) PRIMARY KEY,`A_PASS` CHAR(10)) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* ----------------- Insert data portion ---------------------- */
INSERT INTO ROOM_CATEGORY VALUES(1,'Standard',100);
INSERT INTO ROOM_CATEGORY VALUES(2,'Family',300);
INSERT INTO ROOM_CATEGORY VALUES(3,'Suite',600);
INSERT INTO ROOM_CATEGORY VALUES(4,'Studio',800);

INSERT INTO Room VALUES(1,'Standard',0,321654);
INSERT INTO Room VALUES(2,'Standard',0,NULL);
INSERT INTO Room VALUES(3,'Standard',1,NULL);
INSERT INTO Room VALUES(4,'Standard',1,NULL);
INSERT INTO Room VALUES(5,'Standard',0,NULL);
INSERT INTO Room VALUES(6,'Standard',1,NULL);
INSERT INTO Room VALUES(7,'Family',1,NULL);
INSERT INTO Room VALUES(8,'Family',1,789456);
INSERT INTO Room VALUES(9,'Family',0,123789);
INSERT INTO Room VALUES(10,'Family',1,987654);
INSERT INTO Room VALUES(11,'Family',1,NULL);
INSERT INTO Room VALUES(12,'Family',0,654789);
INSERT INTO Room VALUES(13,'Suite',1,654789);
INSERT INTO Room VALUES(14,'Suite',0,456789);
INSERT INTO Room VALUES(15,'Suite',1,123789);
INSERT INTO Room VALUES(16,'Suite',1,987789);
INSERT INTO Room VALUES(17,'Suite',1,123789);
INSERT INTO Room VALUES(18,'Suite',1,978789);
INSERT INTO Room VALUES(19,'Studio',1,789456);
INSERT INTO Room VALUES(20,'Studio',0,456456);
INSERT INTO Room VALUES(21,'Studio',0,123456);
INSERT INTO Room VALUES(22,'Studio',0,741456);
INSERT INTO Room VALUES(23,'Studio',0,852456);
INSERT INTO Room VALUES(24,'Studio',1,963456);


INSERT INTO GUEST VALUES(1,'Ayyas','Akhtar','123 Desha','New York','NY','78945');
INSERT INTO GUEST VALUES(2,'Vishal','Balani','465 Bradley','Las Angeles','CA','45645');
INSERT INTO GUEST VALUES(3,'Dean','Kearney','654 Jefferson','Chicago','IL','65412');
INSERT INTO GUEST VALUES(4,'Kevin','Le','789 Lincoln','Huston','TX','95412');
INSERT INTO GUEST VALUES(5,'Chai','Lee','987 Drew','Philadelphia','PA','32145');
INSERT INTO GUEST VALUES(6,'James','Mayer','654 Clark','Phoenix','AZ','74185');
INSERT INTO GUEST VALUES(7,'John','McClellan','321 Union','San Antonio','TX','965485');
INSERT INTO GUEST VALUES(8,'Tara','McKeown','021 Columbia','San Diego','CA','74154');
INSERT INTO GUEST VALUES(9,'Brian','McVeigh','147 Miller','Dallas','TX','65478');
INSERT INTO GUEST VALUES(10,'Eduardo','Mendoza','258 Garland','San Jose','CA','65478');
INSERT INTO GUEST VALUES(11,'Benjamin','Peizer','369 Hot Spring','Jacksonville','FL','98745');
INSERT INTO GUEST VALUES(12,'Daniel','Prouty','852 Montgomery','Indianapolis','IN','41021');
INSERT INTO GUEST VALUES(13,'Haleema','Saud','741 Jefferson','Austin','TX','98701');
INSERT INTO GUEST VALUES(14,'Edy','Thenedy','014 Lee','Detroit','MI','64578');
INSERT INTO GUEST VALUES(15,'Alexandr','Verkhovtsev','025 Cross','El Paso','TX','87945');
INSERT INTO GUEST VALUES(16,'Shayna','Winkel','258 Fulton','Seatle','WA','12365');

INSERT INTO RESERVATION VALUES (1,10,1,STR_TO_DATE('13/02/13','DD/MM/YY'),2,7);
INSERT INTO RESERVATION VALUES (2,11,5,STR_TO_DATE('13/02/13','DD/MM/YY'),2,7);
INSERT INTO RESERVATION VALUES (3,12,9,STR_TO_DATE('14/03/13','DD/MM/YY'),2,5);
INSERT INTO RESERVATION VALUES (4,13,12,STR_TO_DATE('14/03/13','DD/MM/YY'),2,5);
INSERT INTO RESERVATION VALUES (5,14,20,STR_TO_DATE('14/04/13','DD/MM/YY'),2,5);
INSERT INTO RESERVATION VALUES (6,15,21,STR_TO_DATE('14/04/13','DD/MM/YY'),2,5);
INSERT INTO RESERVATION VALUES (7,16,22,STR_TO_DATE('14/04/13','DD/MM/YY'),2,5);
INSERT INTO RESERVATION VALUES (8,1,3,STR_TO_DATE('10/05/13','DD/MM/YY'),1,10);
INSERT INTO RESERVATION VALUES (9,2,4,STR_TO_DATE('10/05/13','DD/MM/YY'),1,10);
INSERT INTO RESERVATION VALUES (10,3,6,STR_TO_DATE('10/05/13','DD/MM/YY'),1,11);
INSERT INTO RESERVATION VALUES (11,4,7,STR_TO_DATE('11/05/13','DD/MM/YY'),1,13);
INSERT INTO RESERVATION VALUES (12,5,8,STR_TO_DATE('11/05/13','DD/MM/YY'),1,12);
INSERT INTO RESERVATION VALUES (13,6,10,STR_TO_DATE('12/05/13','DD/MM/YY'),1,8);
INSERT INTO RESERVATION VALUES (14,7,11,STR_TO_DATE('12/05/13','DD/MM/YY'),1,8);
INSERT INTO RESERVATION VALUES (15,8,13,STR_TO_DATE('12/05/13','DD/MM/YY'),1,7);
INSERT INTO RESERVATION VALUES (16,9,15,STR_TO_DATE('12/05/13','DD/MM/YY'),1,7);
INSERT INTO RESERVATION VALUES (17,10,16,STR_TO_DATE('13/05/13','DD/MM/YY'),0,7);
INSERT INTO RESERVATION VALUES (18,11,17,STR_TO_DATE('13/05/13','DD/MM/YY'),0,7);
INSERT INTO RESERVATION VALUES (19,12,18,STR_TO_DATE('14/05/13','DD/MM/YY'),0,5);
INSERT INTO RESERVATION VALUES (20,13,19,STR_TO_DATE('14/05/13','DD/MM/YY'),0,5);
INSERT INTO RESERVATION VALUES (21,14,24,STR_TO_DATE('14/05/13','DD/MM/YY'),0,5);

COMMIT;