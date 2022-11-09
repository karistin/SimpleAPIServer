package com.lucas.osapi.repo;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.impl.InfluxDBImpl;
import org.influxdb.impl.InfluxDBMapper;

/**
 * packageName    : com.lucas.osapi.repo
 * fileName       : CpuMapper
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 * https://github.com/100milliongold/influxDB-Test/tree/master/influxdb-spring/src/main
 * https://github.com/miwurster/spring-data-influxdb
 * 매우 좋아보이는 방법
 */
public class CpuMapper {
    private InfluxDB influxDB;
    private InfluxDBMapper influxDBMapper;

    private InfluxDB connectDatabase(){
        return InfluxDBFactory.connect("http://127.0.0.1", "admin", "admin");
    }
}
