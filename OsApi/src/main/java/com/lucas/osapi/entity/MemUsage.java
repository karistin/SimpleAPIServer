package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : MemUsage
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
@Measurement(name = "MemInfo", database = "OsData")
public class MemUsage {

    /*
    * Memory 10s average
    * */

    @TimeColumn
    @Column(name = "time")
    private Instant time;


    @Column(name = "memUsage")
    private double memUsage;

    @Column(name = "uid")
    private String uid;


}
