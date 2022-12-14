package com.lucas.osapi.entity;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * packageName    : entity
 * fileName       : process
 * author         : lucas
 * date           : 2022-11-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-28        lucas       최초 생성
 */
@Getter
@Setter
@Measurement(name = "ProcessInfo", database = "OsData")
public class ProcessInfo {
    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "processUser")
    private String processUser;

    @Column(name = "processName", tag = true)
    private String processName;

    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Column(name = "memUsage")
    private double memUsage;

    @Column(name = "diskUsage")
    private double diskUsage;


}
