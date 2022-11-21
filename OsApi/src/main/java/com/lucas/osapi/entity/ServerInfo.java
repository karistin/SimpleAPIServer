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

    @Column(name = "core")
    private int core;

    @Column(name = "hostname")
    private String hostName;

    @Column(name = "osType")
    private String osType;

    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Column(name = "menUsage")
    private double menUsage;

    @Column(name = "diskUsage")
    private double diskUsage;

    @Column(name = "diskIO")
    private double diskIO;

    @Column(name = "networkTraffic")
    private String networkTraffic;

    @Column(name = "privateIP")
    private String ip;
}
