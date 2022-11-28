package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.CpuInfo;
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


//    Dot data
    @Override
    public QueryResult findList() {
        return influxDBTemplate.getConnection().query(new Query("select * from CpuInfo group by uid order by time desc limit 1",influxDBTemplate.getDatabase()));
    }


    //    Dot data
    @Override
    public QueryResult findById(String key){
        return influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid ='"+key+"' order by time desc limit",influxDBTemplate.getDatabase()));
    }

//    Range Data

    @Override
    public QueryResult findByIdRange(String key, String time) {
        return influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid ='serverA' and time > now()-"+time+"m",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
