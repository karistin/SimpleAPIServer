package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.ServerInfo;
import java.util.List;

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
public interface ServerRepo extends InfluxDBRepo<ServerInfo>{

    List<ServerInfo> findByIdRange(String key, long time);
}
