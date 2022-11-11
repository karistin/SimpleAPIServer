package com.lucas.osapi.service;


import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.entity.ServerPoint;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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



//    @Override
//    public ListResult<Cpuinfo> findTopCpuUsage() {
//        log.info(influxDBTemplate.ping().getVersion());
//        log.info(influxDBTemplate.getDatabase());
//        influxDBTemplate.createDatabase();
//        Cpuinfo cpuinfo = Cpuinfo.builder()
//                                 .uid(123123123)
//                                 .hostname("Server1")
//                                 .sysUsage(13.2f)
//                                 .userUsage(23.3f)
//                                 .niceUsage(34.3f)
//                                 .idleUsage(33.2f)
//                                         .build();
//
//        final Point p = Point.measurementByPOJO(cpuinfo.getClass()).addFieldsFromPOJO(cpuinfo).build();
//        influxDBTemplate.write(p);
//        return null;
//    }

    @Override
    public Cpuinfo getCpuAllByUid(long uid) {
//        QueryResult query = influxDBTemplate.query("select * from cpu where uid");
        return null;
    }

    @Override
    public List<Cpuinfo> getCpuUsageByuid(long uid) {
        return null;
    }

    @Override
    public ListResult<Cpuinfo> findTopCpuUsage() {
        return null;
    }

    @Override
    public ListResult<Cpuinfo> findAllCpuUsage() {
        return null;
    }
}
