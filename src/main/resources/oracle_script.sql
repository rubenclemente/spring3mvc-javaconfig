/* Formatted on 13/04/2013 15:23:14 (QP5 v5.139.911.3011) */
/*
Schema = "APPUSERS"
Database = Oracle XE
*/

-- DROPS

DROP TABLE role;
DROP TABLE suser;
/*
DROP SEQUENCE role_seq;
DROP SEQUENCE suser_seq;
*/
DROP SEQUENCE hibernate_sequence;

-- Table ROLE

CREATE TABLE APPUSERS.ROLE
(
   ID         NUMBER (19) NOT NULL,
   ROLE       NUMBER (10),
   SUSER_ID   NUMBER (19)
);

ALTER TABLE ROLE ADD (
  CONSTRAINT PK_ROLE
 PRIMARY KEY
 (ID)
 );

/*
CREATE SEQUENCE role_seq
   START WITH 1
   INCREMENT BY 1
   NOMAXVALUE;

CREATE TRIGGER role_trigger
   BEFORE INSERT
   ON role
   FOR EACH ROW
BEGIN
   SELECT role_seq.NEXTVAL INTO :new.id FROM DUAL;
END;
*/

INSERT INTO APPUSERS.ROLE (id, role, suser_id)
     VALUES (1, 1, 1);

INSERT INTO APPUSERS.ROLE (id, role, suser_id)
     VALUES (2, 2, 2);

INSERT INTO APPUSERS.ROLE (id, role, suser_id)
     VALUES (3, 1, 3);

INSERT INTO APPUSERS.ROLE (id, role, suser_id)
     VALUES (4, 1, 4);

-- Table SUSER

CREATE TABLE APPUSERS.SUSER
(
   ID          NUMBER (19) NOT NULL,
   FIRSTNAME   VARCHAR2 (255 BYTE),
   LASTNAME    VARCHAR2 (255 BYTE),
   PASSWORD    VARCHAR2 (255 BYTE),
   USERNAME    VARCHAR2 (255 BYTE)
);

ALTER TABLE SUSER ADD (
  CONSTRAINT PK_suser
 PRIMARY KEY
 (ID)
 );

/*
CREATE SEQUENCE suser_seq
   START WITH 1
   INCREMENT BY 1
   NOMAXVALUE;

CREATE TRIGGER suser_trigger
   BEFORE INSERT
   ON suser
   FOR EACH ROW
BEGIN
   SELECT suser_seq.NEXTVAL INTO :new.id FROM DUAL;
END;
*/

INSERT INTO APPUSERS.SUSER (id,
                            firstName,
                            lastName,
                            password,
                            username)
     VALUES (1,
             'User1Name',
             'User1Lastname',
             'pass1',
             'user1');

INSERT INTO APPUSERS.SUSER (id,
                            firstName,
                            lastName,
                            password,
                            username)
     VALUES (2,
             'User2Name',
             'User2Lastname',
             'pass2',
             'user2');

INSERT INTO APPUSERS.SUSER (id,
                            firstName,
                            lastName,
                            password,
                            username)
     VALUES (3,
             'User3Name',
             'User3Lastname',
             'pass3',
             'user3');

INSERT INTO APPUSERS.SUSER (id,
                            firstName,
                            lastName,
                            password,
                            username)
     VALUES (4,
             'User4Name',
             'User4Lastname',
             'pass4',
             'user4');

ALTER TABLE APPUSERS.SUSER ADD (
 CONSTRAINT FK_role_suser
 FOREIGN KEY (suser_id) REFERENCES suser (id)
);

/*
CREATE SEQUENCE hibernate_sequence
   START WITH 1
   INCREMENT BY 1
   NOMAXVALUE;
*/
