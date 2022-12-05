package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
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
public class MemRepoImpl implements MemRepo{

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.mem-table.name}")
    private String tableName;
    @Value("${spring.influxdbRepo.mem-table.tag}")
    private String tagKey;
    @Value("${spring.influxdbRepo.mem-table.usage}")
    private String mainCol;

    @Override
    public List<MemUsage> findListUsage() {
        Query query = select(mainCol)
                .from(influxDBTemplate.getDatabase(),tableName)
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<MemUsage> memUsages = resultMapper.toPOJO(queryResult, MemUsage.class);
        return memUsages;
    }

    @Override
    public List<MemInfo> findList() {
        return null;
    }

    @Override
    public MemInfo findById(String key) {
        Query query = select("*")
                .from(influxDBTemplate.getDatabase(),tableName)
                .where(eq(tagKey,key))
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        MemInfo memInfo = resultMapper.toPOJO(queryResult, MemInfo.class).get(0);
        if (memInfo == null) {
            throw new RepoException();
        }
        return memInfo;
    }

    @Override
    public List<MemInfo> findByIdRange(String key, Long time) {
        return null;
    }

    @Override
    public List<MemInfo> findByIdRange(String key, long time) {
        return null;
    }

    @Override
    public QueryResult query(String query) {
        return  influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
