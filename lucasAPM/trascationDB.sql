DROP SCHEMA if EXISTS transactionDB;
CREATE SCHEMA transactionDB DEFAULT CHARACTER SET UTF8;
use transactionDB;
# CREATE SEQUENCE sequencetxid START with 1 INCREMENT by 1
#     maxvalue 99999
#     CYCLE;


create table transaction
(
    domain varchar(20),
    instance   varchar(20),

    txid       varchar(32),
    clientIp   INT unsigned,
    clientID    long,
    start_time TIMESTAMP(3),
    end_time   TIME,
    collect_time TIME,

    response_time long default 0,
    sql_time long default 0,
    sql_count long default 0,
    fetch_time long default 0,
    external_time long default 0,
    cpu_time long default 0,

# errorList가 있으면
    ERROELIST json,
#    쿠키가 있으면
    applicaion varchar(20)

);


# HH:MM:SS.ssssss
# insert into transaction(domain, instance,clientIp, clientID, start_time, end_time, collect_time, response_time, sql_time, sql_count, fetch_time, external_time, cpu_time, ERROELIST, applicaion)
# values ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	4,	'["SERVICE_EXCEPTION","HTTP_EXCEPTION"]',	'/simula.jsp'),
#        ('ENV-TEST' , 	'tom9_jdk11' , INET_ATON('192.168.0.8'), 5612977210093630000, '14:27:11.826', '14:27:17.068', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	0,	'["HTTP_EXCEPTION"]',	'/simula.jsp'),
#        ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	1,	'["IO_EXCEPTION"]',	'/simula.jsp'),
#        ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	1,	null,	'/main.jsp'),
#        ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	2,	null,	'/login.jsp'),
#        ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', 5368 ,	1848	,8,	0	, 338, 	3,	null,	'/login.jsp');
#
# insert into transaction(domain, instance,clientIp, clientID, start_time, end_time, collect_time, ERROELIST, applicaion)
# values ('ENV-TEST' , 	'tom8_jdk9' , INET_ATON('192.168.0.8'), -7035355647317760000, '14:27:06.049', '14:27:11.417', '14:27:17.076', null,	'/simula.jsp');
#
# insert into transaction(txid,clientIp,response_time)
# values('2b2d26cfd32b4ef5ac7f4d14ec07bdd7',INET_ATON('127.0.0.1'),1664900415323);
#
# select domain, instance, txid, clientID, INET_NTOA(clientIp), start_time, end_time, collect_time, response_time, sql_time, sql_count, fetch_time, external_time, cpu_time, ERROELIST, applicaion from transaction;
# # select INET_NTOA(clientIp) from transaction;
INSERT INTO transaction(txid,clientIp,start_time,response_time) values('2b0baf04f7b248efaf9584769a4d4def',INET_ATON('127.0.0.1'),'2022-10-05 11:23:43.34354',1664936623344);
select * from transaction