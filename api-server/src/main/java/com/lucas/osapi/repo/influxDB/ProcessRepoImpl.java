package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.entity.ProcessInfo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

@Repository
@Slf4j
public class ProcessRepoImpl implements ProcessRepo{
    private final InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.process-table.name}")
    private String tableName;

    @Value("${spring.influxdbRepo.process-table.tag}")
    private String tagKey;


    public ProcessRepoImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }

    @Override
    public List<ProcessInfo> findList() {
//        select * from ProcessInfo group by uid order by desc limit 1
        Query query = select().from(influxDBTemplate.getDatabase(), tableName)
                .groupBy(tagKey)
                .orderBy(desc())
                .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<ProcessInfo> processInfoList =  resultMapper.toPOJO(queryResult, ProcessInfo.class);
        if (processInfoList.isEmpty()) {
            throw new RepoException();
        }
        return processInfoList;
    }

    @Override
    public List<ProcessInfo> findCpuList() {
        return null;
    }

    @Override
    public List<ProcessInfo> findMemList() {
        return null;
    }
}
