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

commit ;

select * from t_member;


