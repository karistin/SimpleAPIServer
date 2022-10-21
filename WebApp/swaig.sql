DROP DATABASE if EXISTS swaig ;
CREATE DATABASE swaig DEFAULT CHARACTER SET UTF8;
USE swaig ;
CREATE TABLE Example (
                         `oid` INT(11) NOT NULL AUTO_INCREMENT,
                         `title` VARCHAR(100) DEFAULT NULL,
                         `content` VARCHAR(100) DEFAULT NULL,
                         `date` DATE DEFAULT NULL, PRIMARY KEY (`oid`)
) ;


/**/
INSERT INTO Example(oid, title, content, DATE) VALUES(1,'title1','content1','2021-06-26');
INSERT INTO Example(title, content, DATE) VALUES('title2','content2','2021-06-27');
INSERT INTO Example(title, content, DATE) VALUES('title3','content3','2021-06-28');

DELIMITER ;;
CREATE PROCEDURE auto_inset(IN count INT)
BEGIN
    declare i INT default 1;
    while(i < count) do
        insert into Example(title, content, date) values (concat('title',i), concat('content',i) , NOW());
        set i = i + 1;
        end while ;
end;;
delimiter ;

call auto_inset(2000)

