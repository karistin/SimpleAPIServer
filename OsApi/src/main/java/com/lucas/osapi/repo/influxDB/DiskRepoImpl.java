package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
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
public class DiskRepoImpl implements DiskRepo {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.disk-table.name}")
    private String tableName;
    @Value("${spring.influxdbRepo.disk-table.tagKey}")
    private String tagKey;
    @Value("${spring.influxdbRepo.disk-table.usage}")
    private String mainCol;



    @Override
    public QueryResult findbyIdIops(String key) {
        return null;
    }

    @Override
    public QueryResult findbyIdInode(String key) {
        return influxDBTemplate.getConnection().query(new Query("select mean(diskUsage) from (select diskUsage from DiskInfo where uid='"+key+"' order " +
                "by time DESC limit 2 )  order by time desc",influxDBTemplate.getDatabase()));
    }

    @Override
    public QueryResult findbyIdUsage(String key) {
        return influxDBTemplate.getConnection().query(new Query("select mean(diskUsage) from (select diskUsage from DiskInfo where uid='"+key+"' order by time DESC limit 2 )  order by time desc",influxDBTemplate.getDatabase()));
    }

    @Override
    public List<DiskUsage> findListUsage() {
        Query query = select(mainCol)
                .from(influxDBTemplate.getDatabase(),tableName)
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<DiskUsage> diskUsage = resultMapper.toPOJO(queryResult, DiskUsage.class);
        return diskUsage;
    }

    @Override
    public List<DiskInfo> findList() {
        Query query = select("*")
                .from(influxDBTemplate.getDatabase(),tableName)
                .orderBy(desc())
                .limit(1);

        return null;
    }

    @Override
    public DiskInfo findById(String key) {
        Query query = select("*")
                .from(influxDBTemplate.getDatabase(),tableName)
                .where(eq(tagKey,key))
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        DiskInfo diskInfo = resultMapper.toPOJO(queryResult, DiskInfo.class).get(0);
        if (diskInfo == null) {
            throw new RepoException();
        }
        return diskInfo;
    }

    @Override
    public List<DiskInfo> findByIdRange(String key, String time) {
        return null;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
