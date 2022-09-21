DROP DATABASE if EXISTS swaig ;
CREATE DATABASE swaig DEFAULT CHARACTER SET UTF8;
USE swaig ;
CREATE TABLE Example (
                         `oid` INT(11) NOT NULL AUTO_INCREMENT,
                         `title` VARCHAR(100) DEFAULT NULL,
                         `content` VARCHAR(100) DEFAULT NULL,
                         `date` DATE DEFAULT NULL, PRIMARY KEY (`oid`)
) ;
INSERT INTO Example(oid, title, content, DATE) VALUES(1,'title1','content1','2021-06-26');
INSERT INTO Example(title, content, DATE) VALUES('title2','content2','2021-06-27');
INSERT INTO Example(title, content, DATE) VALUES('title3','content3','2021-06-28');