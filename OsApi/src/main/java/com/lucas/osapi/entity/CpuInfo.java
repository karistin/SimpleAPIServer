package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : CpuInfo
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 *  Tags are automatically converted to strings, since tags are strings to influxdb Supported values for fields are boolean,
 *  int, long, double, Boolean, Integer, Long, Double. The time field should be of type instant.
 */

@Getter
@Setter
@Measurement(name = "CpuInfo", database = "OsData")
public class CpuInfo {
//    time, cpuUsage, host, idleUsage, irqUsage, niceUsage, sofrIrqUsage, stealUsage, sysUsage, uid, userUsage, waitIoUsage

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Column(name = "uid", tag = true)
    private String uid;
    @Column(name = "hostname")
    private String hostname;

    @Column(name = "idleUsage")
    private double idleUsage;


    @Column(name = "irqUsage")
    private double irqUsage;

    @Column(name = "niceUsage")
    private double niceUsage;

    @Column(name = "softIrqUsage")
    private double softIrqUsage;

    @Column(name = "stealUsage")
    private double stealUage;

    @Column(name = "sysUsage")
    private double sysUsage;

    @Column(name = "cpuLoad15min")
    private double cpuLoad15;

    @Column(name = "cpuLoad1min")
    private double cpuLoad1;

    @Column(name = "cpuLoad5min")
    private double cpuLoad5;

    @Column(name = "userUsage")
    private double userUsage;


    @Column(name = "waitIoUsage")
    private double waitIoUsage;

}
