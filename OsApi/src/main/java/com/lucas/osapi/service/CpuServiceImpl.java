package com.lucas.osapi.service;


import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.model.response.ListResult;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : CpuService
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */

@Slf4j
@Service
public class CpuServiceImpl implements CpuService {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public Cpuinfo getCpuAllByUid(long uid) {
        return null;
    }

    @Override
    public List<Cpuinfo> getCpuUsageByuid(long uid) {
//        QueryResult queryResult = influxDBTemplate.query(new Query("select * from cpu where uid="+uid+" order by desc LIMIT 1"));

        return null;
    }

    @Override
    public ListResult<Cpuinfo> findTopCpuUsage() {

        influxDBTemplate.createDatabase();

        QueryResult queryResult = influxDBTemplate.query(new Query("select * from CpuInfo where uid = 'serverA' order by time desc limit 5", influxDBTemplate.getDatabase()));
//        influxDBTemplate.query(q, 10, queryResult -> logger.info(queryResult.toString()));
        log.info(queryResult.toString());
//        List<QueryResult.Result> queryList= queryResult.getResults();
//        final Query q = new Query("SELECT * FROM cpu GROUP BY tenant", influxDBTemplate.getDatabase());
        InfluxDBResultMapper mapper = new InfluxDBResultMapper();
//        queryResult.toString();
        List<Cpuinfo> cpuinfoList = mapper.toPOJO(queryResult, Cpuinfo.class);
//        log.info(String.valueOf(cpuinfo.get(0).getCpuUsage()));
//        for (Cpuinfo cpuinfo : cpuinfoList) {
//            log.info(String.valueOf(cpuinfo.getCpuUsage()));
//        }
        return null;
    }

    @Override
    public ListResult<Cpuinfo> findAllCpuUsage() {
        return null;
    }
}
