package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

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
@Measurement(name = "CpuInfo", database = "TimeSeries", retentionPolicy = "autogen", timeUnit = TimeUnit.SECONDS)
public class Cpuinfo {
    @Column(name = "time")
    private Instant time;

    @Column(name = "host", tag = true)
    private String hostname;

    /* unsigend int */
    @Column(name = "user")
    private int user;

    @Column(name = "nice")
    private int nice;

    @Column(name = "system")
    private int system;

    @Column(name = "idle")
    private int idle;

    @Column(name = "iowait")
    private int iowait;

    @Column(name = "irq")
    private int irq;

    @Column(name = "softirq")
    private int softirq;

    @Column(name = "steal")
    private int steal;

    @Column(name = "guest")
    private int guest;

    @Column(name = "guest_nice")
    private int guest_nice;

    @Column(name = "cpuUsage")
    private float cpuUsage;

    @Column(name = "cpuSysUsage")
    private float cpuSysUsage;

    @Column(name = "cpuUserUsage")
    private float cpuUserUage;

    @Column(name = "cpuIdleUsage")
    private float cpuIdleUsage;
}
