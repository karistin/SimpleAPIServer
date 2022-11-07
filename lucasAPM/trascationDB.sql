DROP SCHEMA if EXISTS transactionDB;
CREATE SCHEMA transactionDB DEFAULT CHARACTER SET UTF8;
use transactionDB;
# CREATE SEQUENCE sequencetxid START with 1 INCREMENT by 1
#     maxvalue 99999
#     CYCLE;

drop table if exists transactionprofile ;
drop table if exists transaction;

create table transactionprofile
(
    TPxid              varchar(30) primary key,
    startTime         time,
    collectTime        time,
    endTime time,
    CpuTime         long,
    ResponseTime       long,
    serviceName       varchar(30),
    remoteIp          varchar(30),
    error             int,
    http_method       varchar(30),
    http_query        varchar(30),
    http_content_type varchar(30),
    sqlCount          int,
    sqlTime           long,
    sqlText           varchar(30)
);

create table transaction
(
    txid         varchar(30) primary key,
    ResponseTime long,
    CpuTime      long,
    sqlTime      long,
    serviceName  varchar(30),
    error        int
);


# insert into transaction (txid, ResponseTime, CpuTime, SqlTime, serviceName, remoteIp, endTime ,Error) values(?, ?, ?, ?, ?, ?);

#
select * from transaction;
select * from transactionprofile;
# insert into