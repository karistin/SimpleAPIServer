package com.lucas.osapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : CpuUsage
 * author         : lucas
 * date           : 2022-11-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-16        lucas       최초 생성
 */
@Getter
@Setter
@Measurement(name = "CpuInfo",database = "OsData")
public class CpuUsage {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Column(name = "userUsage")
    private double userUsage;

    @Column(name = "sysUsage")
    private double sysUsage;

    @Column(name = "uid")
    private String uid;

}
