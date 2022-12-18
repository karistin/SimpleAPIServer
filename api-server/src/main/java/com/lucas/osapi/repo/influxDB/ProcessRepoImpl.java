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
//         select top(cpuUsage, 10) as cpuUsage , * from ProcessInfo where time > now() - 5s
//        Query query = select().from(influxDBTemplate.getDatabase(), tableName)
//                .groupBy(tagKey)
//                .orderBy(desc())
//                .limit(1);
        String query = " select top(cpuUsage, 10) as cpuUsage , * from ProcessInfo where time > now() - 10s";
        log.info(query);
        QueryResult queryResult = influxDBTemplate.query(new Query(query, influxDBTemplate.getDatabase()));
        List<ProcessInfo> processInfoList =  resultMapper.toPOJO(queryResult, ProcessInfo.class);
        if (processInfoList.isEmpty()) {
            throw new RepoException();
        }
        return processInfoList;
    }


    @Override
    public List<ProcessInfo> findCpuList() {
//        select top(cpuUsage, 5) as cpuUsage , processName , uid from ProcessInfo where time > now() - 5s
        String query = "select top(cpuUsage, 5) as cpuUsage , * from ProcessInfo where time > now() - 10s";
        log.info(query);
        QueryResult queryResult = influxDBTemplate.query(new Query(query, influxDBTemplate.getDatabase()));
        List<ProcessInfo> processInfoList = resultMapper.toPOJO(queryResult, ProcessInfo.class);
        if (processInfoList.isEmpty()) {
            throw new RepoException();
        }
        return processInfoList;
    }

    @Override
    public List<ProcessInfo> findMemList() {
        String query = "select top(memUsage, 5) as memUsage , * from ProcessInfo where time > now() - 10s ";
        log.info(query);
        QueryResult queryResult = influxDBTemplate.query(new Query(query, influxDBTemplate.getDatabase()));
        List<ProcessInfo> processInfoList = resultMapper.toPOJO(queryResult, ProcessInfo.class);
        if (processInfoList.isEmpty()) {
            throw new RepoException();
        }
        return processInfoList;
    }
}
