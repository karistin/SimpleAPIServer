package com.lucas.osapi.service;

import com.lucas.osapi.entity.ServerUsage;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : ServerListServiceImpl
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 *
 */
@Slf4j
@Service
public class ServerListServiceImpl implements ServerListService {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public Optional<Integer> getServerCount() {
        String query = "show tag values from ServerInfo with key = uid";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<QueryResult.Result> results = queryResult.getResults();
//        List<QueryResult.Series>
        for(QueryResult.Result result:results){

        }
        return Optional.of(results.size());
    }

    @Override
    public Optional<Integer> getServerCore() {
        String query = "select core from ServerInfo group by uid limit 1";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<ServerUsage> serverUsages = resultMapper.toPOJO(queryResult , ServerUsage.class);
        if (serverUsages.isEmpty()) {
            return Optional.empty();
        }
        int sum = 0;
        for (ServerUsage serverUsage : serverUsages) {
            sum += serverUsage.getCore();
        }
        return Optional.of(sum);
    }

    @Override
    public Optional<String> getServerType() {
        String query = "select osType from ServerInfo group by uid limit 1";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<ServerUsage> serverUsages = resultMapper.toPOJO(queryResult , ServerUsage.class);
        if (serverUsages.isEmpty()) {
            return Optional.empty();
        }
        List<osTypeData> osTypeDataList = new ArrayList<>();
        /*
        * TODO: https://m.blog.naver.com/tmondev/220353774803
        * */

        for (ServerUsage serverUsage : serverUsages) {
            osTypeData.valueOf(serverUsage.getOsType());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<ServerUsage>> getServerInfo() {
        return Optional.empty();
    }

    @Override
    public Optional<ServerUsage> getServerInfoById(String id) {
        return Optional.empty();
    }
}
