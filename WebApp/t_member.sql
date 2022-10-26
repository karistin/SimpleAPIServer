use swaig;

drop table  if exists t_member;


create table t_member
(
    id    varchar(10) not null PRIMARY KEY,
    pwd   varchar(10),
    name  varchar(50),
    email varchar(50),
    joinDate date default sysdate()
);

insert into t_member values ('HUR', '1234', '허준', 'HUR@naver.com', sysdate());
insert into t_member values ('PARK', '0000', '박지성', 'PARK@gamil.com', sysdate());
insert into t_member values ('SON', '1111', '손흥민', 'SON@naver.com', sysdate());

DELIMITER ;;
CREATE PROCEDURE auto_memberInsert(IN count INT)
BEGIN
    declare i INT default 1;
    while(i < count) do
            insert into t_member(id,pwd,name,email,joinDate) values
                                                                 (concat('dummy',i), concat('pwd',i) , concat('nickname',i), concat('dummyMail',i,'@gamil.com') , NOW());
            set i = i + 1;
        end while ;
end;;
delimiter ;




call auto_memberInsert(300);

select * from t_member;

# select * from t_member where id='sdfsd';
#
# delete from t_member where id='werwe';


