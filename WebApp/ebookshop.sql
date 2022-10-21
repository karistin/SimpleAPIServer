

use swaig;

drop table if exists books;
create table books (
                       id     int,
                       title  varchar(50),
                       author varchar(50),
                       price  float,
                       qty    int,
                       primary key (id));

insert into books values (1001, 'Java for dummies', 'Tan Ah Teck', 11.11, 11);
insert into books values (1002, 'More Java for dummies', 'Tan Ah Teck', 22.22, 22);
insert into books values (1003, 'More Java for more dummies', 'Mohammad Ali', 33.33, 33);
insert into books values (1004, 'A Cup of Java', 'Kumar', 55.55, 55);
insert into books values (1005, 'A Teaspoon of Java', 'Kevin Jones', 66.66, 66);

select * from books;