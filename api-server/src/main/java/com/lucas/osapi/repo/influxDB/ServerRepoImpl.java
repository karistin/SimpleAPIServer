package com.lucas.osapi.repo.influxDB;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.ServerInfo;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
 * 서버의 정보를 가져오는 Repo
 */
@Repository
@Slf4j
public class ServerRepoImpl implements ServerRepo{

    private final InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.server-table.name}")
    private String tableName;

    @Value("${spring.influxdbRepo.server-table.tag}")
    private String tagKey;

    public ServerRepoImpl(InfluxDBTemplate<Point> influxDBTemplate) {
        this.influxDBTemplate = influxDBTemplate;
    }

//    [Result [series=[Series [name=ServerInfo, tags=null, columns=[key, value], values=[[uid, serverA], [uid, serverB], [uid, serverC], [uid, serverD], [uid, serverE], [uid, serverF], [uid, serverG], [uid, serverH]]]], error=null]]
    @Override
    public Map<String, Integer> findCount() {
        String query = "show tag values from ServerInfo with key = uid";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));

        int size = queryResult.getResults().get(0).getSeries().get(0).getValues().size();
//        TODO : if size null ERROR

        return Stream.of(new Object[][]{
            {"count", size}
        }).collect(Collectors.toMap(item -> (String) item[0], item -> (Integer) item[1]));

    }

    @Override
    public List<ServerInfo> findList() {
        Query query = select()
            .from(influxDBTemplate.getDatabase(), tableName)
            .groupBy(tagKey)
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<ServerInfo> serverInfoList = resultMapper.toPOJO(queryResult, ServerInfo.class);
        if (serverInfoList.isEmpty()) {
            throw new RepoException();
        }
        return serverInfoList;
    }

    @Override
    public ServerInfo findById(String id) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey, id))
            .orderBy(desc())
            .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<ServerInfo> serverInfoList = resultMapper.toPOJO(queryResult, ServerInfo.class);
        if (serverInfoList.isEmpty()) {
            throw new RepoException();
        }
        return serverInfoList.get(0);
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }
}
