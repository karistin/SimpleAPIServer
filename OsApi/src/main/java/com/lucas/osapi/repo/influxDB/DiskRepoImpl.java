package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.subTime;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;

/**
 * packageName    : com.lucas.OsApi.repo.influxDB
 * fileName       : DiskRepoImpl
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Slf4j
@Repository
public class DiskRepoImpl implements DiskRepo {

    private final InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.disk-table.name}")
    private String tableName;
    @Value("${spring.influxdbRepo.disk-table.tagKey}")
    private String tagKey;

    public DiskRepoImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }


    @Override
    public List<DiskUsage> findListUsage() {
        Query query = select("uid","diskUsage","diskIOPS","diskInodeUsed")
            .from(influxDBTemplate.getDatabase(),tableName)
            .groupBy(tagKey)
            .orderBy(desc())
            .limit(1);

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<DiskUsage> diskInfo =  resultMapper.toPOJO(queryResult, DiskUsage.class);
        if (diskInfo.isEmpty()) {
            throw new RepoException();
        }
        return diskInfo;
    }

    //    Dot data
    @Override
    public List<DiskInfo> findList() {
        Query query = select()
            .from(influxDBTemplate.getDatabase(),tableName)
            .groupBy(tagKey)
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<DiskInfo> diskInfo = resultMapper.toPOJO(queryResult, DiskInfo.class);
        log.info(String.valueOf(diskInfo.size()));
        if (diskInfo.isEmpty()) {
            throw new RepoException();
        }
        return diskInfo;
    }



    //    Dot data
    @Override
    public DiskInfo findById(String key) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(),tableName)
            .where(eq(tagKey,key))
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<DiskInfo> diskInfo = resultMapper.toPOJO(queryResult, DiskInfo.class);
        if (diskInfo.isEmpty()) {
            throw new RepoException();
        }
        return diskInfo.get(0);
    }


//    Range Data

    @Override
    public List<DiskInfo> findByIdRange(String key, Long time) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey, key)).and(gt("time",subTime(time, MINUTE)));

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<DiskInfo> diskInfo = resultMapper.toPOJO(queryResult, DiskInfo.class);
        if (diskInfo.isEmpty()) {
            throw new RepoException();
        }
        return diskInfo;
    }


    @Override
    public List<DiskUsage> findByIdRangeUsage(String key, Long time) {
        Query query = select("uid","diskUsage","diskIOPS","diskInodeUsed")
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey,key))
            .and(gt("time",subTime(
                time, MINUTE)));
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<DiskUsage> queryResultList = resultMapper.toPOJO(queryResult, DiskUsage.class);
        log.info(query.getCommand());
        if (queryResultList.isEmpty()) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
