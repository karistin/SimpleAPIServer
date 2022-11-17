package com.lucas.osapi.service;

import com.lucas.osapi.entity.ServerUsage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : ServerService
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
public interface ServerService {

    @Getter
    @AllArgsConstructor
    enum osTypeData{
        WINDOW( "Window",0),
        LINUX("Linux",0),
        UNIX("Unix",0),
        OTHERS("Others",0);

        String msg;
        int count;
    }
    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
    Optional<Integer> getServerCount();
    Optional<Integer> getServerCore();
    Optional<String> getServerType();
    Optional<List<ServerUsage>> getServerInfo();
    Optional<ServerUsage> getServerInfoById(String id);

}
