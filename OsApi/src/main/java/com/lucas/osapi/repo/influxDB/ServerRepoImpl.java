package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.ServerInfo;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : ServerRepoImpl
 * author         : lucas
 * date           : 2022-11-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-30        lucas       최초 생성
 */
public class ServerRepoImpl implements ServerRepo{

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public List<ServerInfo> findList() {
        return null;
    }

    @Override
    public ServerInfo findById(String key) {
        return null;
    }

    @Override
    public List<ServerInfo> findByIdRange(String key, String time) {
        return null;
    }

    @Override
    public QueryResult query(String query) {
        return null;
    }
}
