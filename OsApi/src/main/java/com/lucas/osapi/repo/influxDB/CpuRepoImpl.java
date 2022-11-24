package com.lucas.osapi.repo.influxDB;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.addTime;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.now;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.time;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : CpuRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Repository
public class CpuRepoImpl implements CpuRepo {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    private String tableName = "CpuInfo";
    private String tagKey = "uid";
    private String mainCol = "cpuUsage";

    @Override
    public QueryResult findList() {
//        select mean(cpuUsage) from CpuInfo where time > now() - 30m group by time(10s), uid
        Query query = select().mean(mainCol).from(influxDBTemplate.getDatabase(), tableName)
                .where("time > now() - 1h").groupBy("time(10s), "+tagKey);
        return influxDBTemplate.getConnection().query(new Query("select mean(cpuUsage) from CpuInfo where time > now() - 30m group by time(10s), uid",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findByIdUsage(String key) {
//        Query query = select().from(influxDBTemplate.getDatabase(), tableName)
//                              .where(eq(tagKey,key)).limit(1);
//        return influxDBTemplate.getConnection().query(new Query("select mean(cpuUsage) from (select cpuUsage from CpuInfo where uid='"+key+"' order by time DESC limit 2 )  order by time desc",influxDBTemplate.getDatabase()));
        return influxDBTemplate.getConnection().query(new Query("select mean(cpuUsage) from CpuInfo where uid ='"+key+"' and time > now() - 1h group by time(10s) ",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findById(String key){
//        select * from CpuInfo where uid ='serverA'order by time desc limit 1
        return influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid ='"+key+"'order by time desc limit 1",influxDBTemplate.getDatabase()));
    }


    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
