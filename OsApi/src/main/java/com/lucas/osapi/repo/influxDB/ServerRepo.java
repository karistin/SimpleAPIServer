package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.ServerInfo;
import java.util.List;
import java.util.Map;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : ServerRepo
 * author         : lucas
 * date           : 2022-11-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-30        lucas       최초 생성
 */
public interface ServerRepo{

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    QueryResult query(String query);

    Map<String, Integer> findCount();

    List<ServerInfo> findList();
    ServerInfo findById(String id);




}
