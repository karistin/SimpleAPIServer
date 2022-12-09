package com.lucas.osapi.entity;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : DiskUsage
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
@AllArgsConstructor
@Measurement(name = "DiskInfo", database = "OsData")
public class DiskUsage {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "diskInodeUsed")
    private double diskInodeUsed;

    @Column(name = "diskIOPS")
    private double diskIOPS;

    @Column(name = "diskUsage")
    private double diskUsage;

    @Column(name = "uid")
    private String uid;


}
