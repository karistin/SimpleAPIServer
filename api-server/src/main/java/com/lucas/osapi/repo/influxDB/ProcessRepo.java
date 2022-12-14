package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.ProcessInfo;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;

public interface ProcessRepo {

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    QueryResult query(String query);

    List<ProcessInfo> findList();


    List<ProcessInfo> findCpuList();

    List<ProcessInfo> findMemList();


}
