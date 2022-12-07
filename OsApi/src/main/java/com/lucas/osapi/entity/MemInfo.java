package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : MemInfo
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
public class MemInfo {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "memUsage")
    private double memUsage;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "memUsageByteAll")
    private double memUsageByteAll;

    @Column(name = "memUsageByteFree")
    private double memUsageByteFree;

    @Column(name = "memUsageByteCached")
    private double memUsageByteCached;

    @Column(name = "memUsageByteBuffers")
    private double memUsageByteBuffers;

    @Column(name = "memUsageByteUsed")
    private double memUsageByteUsed;

    @Column(name = "memAvilable")
    private double memAvilable;


    @Column(name = "memSReclaimable")
    private double memSReclaimable;

    @Column(name = "memSUnreclaim")
    private double memSUnreclaim;

    @Column(name = "memSlab")
    private double memSlab;

    @Column(name = "memSwapUsed")
    private double memSwapUsed;

    @Column(name = "memSwapUsedByte")
    private double memSwapUsedByte;

    @Column(name = "memPageFault")
    private double memPageFault;

}
