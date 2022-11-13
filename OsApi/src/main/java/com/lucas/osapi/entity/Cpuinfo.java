package com.lucas.osapi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;
import java.util.concurrent.TimeUnit;


/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : Cpuinfo
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */
@Getter
@Setter
@Builder
@Measurement(name = "CpuInfo", database = "OsData", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class Cpuinfo {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "cpuUsage")
    private float cpuUsage;

    @Column(name = "host")
    private String hostname;

    @Column(name = "idleUsage")
    private float idleUsage;


    @Column(name = "irqUsage")
    private float irqUsage;

    @Column(name = "niceUsage")
    private float niceUsage;

    @Column(name = "sofrIrqUsage")
    private float softIrqUsage;

    @Column(name = "stealUsage")
    private float stealUage;

    @Column(name = "sysUsage")
    private float sysUsage;


    @Column(name = "uid")
    private String uid;

    @Column(name = "userUsage")
    private float userUsage;


    @Column(name = "waitIoUsage")
    private float waitIoUsage;


}
