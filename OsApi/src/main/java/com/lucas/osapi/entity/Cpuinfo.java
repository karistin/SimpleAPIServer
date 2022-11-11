package com.lucas.osapi.entity;

import lombok.Builder;
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
@Builder
@Measurement(name = "CpuInfo", database = "TimeSeries", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class Cpuinfo {

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "host", tag = true)
    private String hostname;

    @Column(name = "cpuUsage")
    private float cpuUsage;

    @Column(name = "userUsage")
    private float userUsage;

    @Column(name = "sysUsage")
    private float sysUsage;

    @Column(name = "niceUsage")
    private float niceUsage;

    @Column(name = "idleUsage")
    private float idleUsage;

    @Column(name = "waitIoUsage")
    private float waitIoUsage;

    @Column(name = "stealUsage")
    private float stealUage;

    @Column(name = "irqUsage")
    private float irqUsage;

    @Column(name = "sofrIrqUsage")
    private float softIrqUsage;


    @Override
    public String toString() {
        return "Cpuinfo{" +
                "uid='" + uid + '\'' +
                ", hostname='" + hostname + '\'' +
                ", cpuUsage=" + cpuUsage +
                ", userUsage=" + userUsage +
                ", sysUsage=" + sysUsage +
                ", niceUsage=" + niceUsage +
                ", idleUsage=" + idleUsage +
                ", waitIoUsage=" + waitIoUsage +
                ", stealUage=" + stealUage +
                ", irqUsage=" + irqUsage +
                ", softIrqUsage=" + softIrqUsage +
                '}';
    }
}
