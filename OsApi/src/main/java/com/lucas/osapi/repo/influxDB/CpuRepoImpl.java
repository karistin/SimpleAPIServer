package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.ne;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.time;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : CpuRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Repository
public class CpuRepoImpl implements CpuRepo {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.cpu-table.name}")
    private String tableName;

    @Value("${spring.influxdbRepo.cpu-table.tag}")
    private String tagKey;

    @Value("${spring.influxdbRepo.cpu-table.usage}")
    private String mainCol;

//    Dot data

    @Override
    public List<CpuUsage> findListUsage() {
        Query query = select("cpuUsage")
                .from(influxDBTemplate.getDatabase(),tableName)
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.query(query);
        List<CpuUsage> cpuUsage = resultMapper.toPOJO(queryResult, CpuUsage.class);
        return cpuUsage;
    }

    //    Dot data
    @Override
    public List<CpuInfo> findList() {
        Query query = select("*")
                .from(influxDBTemplate.getDatabase(),tableName)
                .groupBy(tagKey)
                .orderBy(desc())
                .limit(1);

        QueryResult queryResult = influxDBTemplate.query(query);
        List<CpuInfo> cpuInfo = resultMapper.toPOJO(queryResult, CpuInfo.class);
        if (cpuInfo == null) {
            throw new RepoException();
        }
        return cpuInfo;
    }



    //    Dot data
    @Override
    public CpuInfo findById(String key) {
        Query query = select("*")
                .from(influxDBTemplate.getDatabase(),tableName)
                .where(eq(tagKey,key))
                .orderBy(desc())
                .limit(1);
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid ='"+key+"' order by time desc limit 1",influxDBTemplate.getDatabase()));
        CpuInfo cpuInfo = resultMapper.toPOJO(queryResult, CpuInfo.class).get(0);
        if (cpuInfo == null) {
            throw new RepoException();
        }
        return cpuInfo;
    }


//    Range Data

    @Override
    public List<CpuInfo> findByIdRange(String key, String time) {
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid ='"+key+"' and time > now()-"+time+"m",influxDBTemplate.getDatabase()));
        List<CpuInfo> queryResultList = resultMapper.toPOJO(queryResult, CpuInfo.class);
        if (queryResultList == null) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public List<CpuUsage> findbyIdRangeUsage(String key, String time) {
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query("select cpuUsage from CpuInfo where uid ='"+key+"' and time > now()-"+time+"m",influxDBTemplate.getDatabase()));
        List<CpuUsage> queryResultList = resultMapper.toPOJO(queryResult, CpuUsage.class);
        if (queryResultList == null) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }

//    TODO : just for Testing
    public Map<String, String> getDetail()
    {
        return Map.of(
                "name", this.tableName,
                "tag", this.tagKey,
                "usage", this.mainCol
        );
    }
}
