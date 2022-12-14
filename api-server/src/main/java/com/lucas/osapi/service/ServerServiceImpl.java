package com.lucas.osapi.service;

import com.lucas.osapi.entity.ServerInfo;
import com.lucas.osapi.repo.influxDB.ServerRepo;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
public class ServerServiceImpl implements ServerService {

    private final ServerRepo serverRepo;

    public ServerServiceImpl(ServerRepo serverRepo) {
        this.serverRepo = serverRepo;
    }

    @Override
    public Entry<String, Integer> count() {
        List<ServerInfo> serverInfoList = serverRepo.findList();
        return new AbstractMap.SimpleEntry<>("ServerCout", serverInfoList.size());
    }

    @Override
    public Optional<Integer> getServerCore() {
        List<ServerInfo> serverInfoList = serverRepo.findList();
        int sum = 0;
        for (ServerInfo server : serverInfoList) {
            sum += server.getCore();
        }
        return Optional.of(sum);
    }

    @Override
    public Map<String, Integer> getServerType() {
        List<ServerInfo> serverInfoList = serverRepo.findList();
        Map<String, Integer> result = new HashMap<>();
        for (ServerInfo server : serverInfoList) {
            String key = server.getOsType();
            int count = result.getOrDefault(key, 0);
            result.put(key, count + 1);
        }
        return result;
    }

    @Override
    public List<ServerInfo> getList() {
        return serverRepo.findList();
    }

    @Override
    public ServerInfo getServerInfoById(String id) {
        return serverRepo.findById(id);
    }
}
