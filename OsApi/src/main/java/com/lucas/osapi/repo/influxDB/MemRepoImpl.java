package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import lombok.extern.slf4j.Slf4j;
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
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.subTime;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : MemRepoImpl
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Repository
@Slf4j
public class MemRepoImpl implements MemRepo{

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.mem-table.name}")
    private String tableName;
    @Value("${spring.influxdbRepo.mem-table.tag}")
    private String tagKey;


    @Override
    public List<MemUsage> findListUsage() {
        Query query = select("uid", "memUsage")
            .from(influxDBTemplate.getDatabase(),tableName)
            .groupBy(tagKey)
            .orderBy(desc())
            .limit(1);

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        return resultMapper.toPOJO(queryResult, MemUsage.class);
    }


    @Override
    public List<MemInfo> findList() {
        Query query = select()
            .from(influxDBTemplate.getDatabase(),tableName)
            .groupBy(tagKey)
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<MemInfo> memInfo = resultMapper.toPOJO(queryResult, MemInfo.class);
        if (memInfo == null) {
            throw new RepoException();
        }
        return memInfo;
    }

    @Override
    public MemInfo findById(String key) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(),tableName)
            .where(eq(tagKey,key))
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<MemInfo> memInfo = resultMapper.toPOJO(queryResult, MemInfo.class);
        if (memInfo == null || memInfo.isEmpty()) {
            throw new RepoException();
        }
        return memInfo.get(0);
    }

    @Override
    public List<MemInfo> findByIdRange(String key, Long time) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey, key)).and(gt("time",subTime(time, MINUTE)));

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<MemInfo>  memInfo = resultMapper.toPOJO(queryResult, MemInfo.class);
        if (memInfo == null) {
            throw new RepoException();
        }
        return memInfo;
    }

    @Override
    public List<MemUsage> findbyIdRangeUsage(String key, Long time) {
        Query query = select("uid", "memUsage")
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey,key))
            .and(gt("time",subTime(
                time, MINUTE)));
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<MemUsage> queryResultList = resultMapper.toPOJO(queryResult, MemUsage.class);
        if (queryResultList == null) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public QueryResult query(String query) {
        return  influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
