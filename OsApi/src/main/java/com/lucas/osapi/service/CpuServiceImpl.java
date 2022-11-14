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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

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

    /*
     * Time now()
     * uid가 가지고 있는 Cpu data 출력
     *  */
    @Override
    public Cpuinfo getCpuAllByUid(long uid) {
        String query =  "select * from CpuInfo where uid='"+uid+"' limit 1 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        return resultMapper.toPOJO(queryResult, Cpuinfo.class).get(0);
    }

    /*
     * Time now()
     * 특정 uid가 사용하는 CPU 사용량 구하기
     * */
    @Override
    public Map<String, Double> getCpuUsageByuid(long uid) {
        String query =  "select cpuUsage from CpuInfo where uid='"+uid+"' limit 1 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        Cpuinfo cpuinfo = resultMapper.toPOJO(queryResult, Cpuinfo.class).get(0);

        return new HashMap<>(cpuinfo.getUid(), cpuinfo.getCpuUsage());
    }



    /*
    * 최신 CPU 사용량 TOP 5 가져오기
    * TODO : queryBuilder를 사용한 쿼리문 제작하기
    *  https://github.com/influxdata/influxdb-java/blob/master/QUERY_BUILDER.md
    * */
    @Override
    public List<Cpuinfo> findTopCpuUsage() {
        String query =  "select cpuUsage from CpuInfo group by uid limit 1 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

//        List<Cpuinfo> cpuList = resultMapper.toPOJO(queryResult, Cpuinfo.class);
//        List<Cpuinfo> cpuTopUsageList = new ArrayList<>();
//        Map<String, Double> cpuTopList = new LinkedHashMap<>();
//
//        for (Cpuinfo cpuInfo : cpuList) {
//            if (cpuTopList.size() < 6) {
//                cpuTopList.put(cpuInfo.getUid() , cpuInfo.getCpuUsage() );
//            }else{
                /*
                 * TODO : Map내에서 최소값 대체
                 * https://hianna.tistory.com/577
                 * java Collections
                 * sortedMap
                 * TreeMap
                 * */
//                if(cpuInfo.getCpuUsage() > Collections.min(cpuTopList.values()))
//                {
//                    cpuTopList.remove()
//                    cpuTopList.replace(cpuTopList.)
//                }
//            }
//        }
        /*
        *  TODO : cpuTodoList 정렬해서 보낼것인가???
        *   https://stackoverflow.com/questions/12184378/sorting-linkedhashmap
        * */
//        cpuTopList.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .forEach(entry -> );
        return resultMapper.toPOJO(queryResult, Cpuinfo.class);
    }

    @Override
    public ListResult<Cpuinfo> findAllCpuUsage() {
        return null;
    }
}
