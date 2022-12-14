package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : ServerInfo
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@Getter
@Setter
@Measurement(name = "ServerInfo", database = "OsData")
public class ServerInfo {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "hostname")
    private String hostName;

    @Column(name = "osType")
    private String osType;

    @Column(name = "osVersion")
    private String osVersion;

//    TODO : change String -> Long
    @Column(name = "totalMem")
    private String totalMem;

    @Column(name = "core")
    private int core;

    @Column(name = "ipAdress")
    private String ipadress;

    @Column(name = "agent")
    private String agentVersion;

    public ServerInfo setServerInfo(Instant time, String uid, String hostName, String osType, String osVersion,
        String totalMem, int core, String ipadress, String agentVersion) {
        this.time = time;
        this.uid = uid;
        this.hostName = hostName;
        this.osType = osType;
        this.osVersion = osVersion;
        this.totalMem = totalMem;
        this.core = core;
        this.ipadress = ipadress;
        this.agentVersion = agentVersion;
        return this;
    }
}
