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
    private String memUsageByteAll;

    @Column(name = "memUsageByteFree")
    private String memUsageByteFree;

    @Column(name = "memUsageByteCached")
    private String memUsageByteCached;

    @Column(name = "memUsageByteBuffers")
    private String memUsageByteBuffers;

    @Column(name = "memUsageByteUsed")
    private String memUsageByteUsed;

    @Column(name = "memAvilable")
    private String memAvilable;


    @Column(name = "memSReclaimable")
    private String memSReclaimable;

    @Column(name = "memSUnreclaim")
    private String memSUnreclaim;

    @Column(name = "memSlab")
    private String memSlab;

    @Column(name = "memSwapUsed")
    private double memSwapUsed;

    @Column(name = "memSwapUsedByte")
    private String memSwapUsedByte;

    @Column(name = "memPageFault")
    private String memPageFault;

}
