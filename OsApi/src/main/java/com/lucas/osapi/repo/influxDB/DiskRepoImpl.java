package com.lucas.osapi.repo.influxDB;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : DiskRepoImpl
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Repository
public class DiskRepoImpl implements DiskRepo{

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    private String tableName = "DiskInfo";
    private String tagKey = "uid";
    private String mainCol = "diskUsage";

    @Override
    public QueryResult findList() {
//        Query query = select().mean(mainCol).from(influxDBTemplate.getDatabase(), tableName)
//                              .groupBy(tagKey).limit(2);
        return influxDBTemplate.getConnection().query(new Query("select mean(diskUsage) from (select diskUsage from DiskInfo group by uid order by time DESC limit 2 ) group by uid order by time DESC",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findbyIdIops(String key) {
        return null;
    }

    @Override
    public QueryResult findbyIdInode(String key) {
        return influxDBTemplate.getConnection().query(new Query("select mean(diskUsage) from (select diskUsage from DiskInfo where uid='"+key+"' order by time DESC limit 2 )  order by time desc",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findbyIdUsage(String key) {
        return influxDBTemplate.getConnection().query(new Query("select mean(diskUsage) from (select diskUsage from DiskInfo where uid='"+key+"' order by time DESC limit 2 )  order by time desc",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findById(String key) {
//        Query query = select().from(influxDBTemplate.getDatabase(), tableName)
//                              .where(eq(tagKey,key)).limit(1);
        return influxDBTemplate.getConnection().query(new Query("select * from MemInfo where uid ='"+key+"'order by time desc limit 1",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findByIdRange(String key, String time) {
        return null;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
