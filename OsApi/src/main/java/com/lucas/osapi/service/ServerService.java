package com.lucas.osapi.service;

import com.lucas.osapi.entity.ServerInfo;
import java.util.Map;

import java.util.List;
import java.util.Map.Entry;
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
    Entry<String, Integer> count();
    Optional<Integer> getServerCore();
    Map<String, Integer> getServerType();
    List<ServerInfo> getList();
    ServerInfo getServerInfoById(String id);

}
