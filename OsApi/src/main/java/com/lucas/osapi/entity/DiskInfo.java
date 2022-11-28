package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : DiskInfo
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
@Measurement(name = "DiskInfo", database = "OsData")
public class DiskInfo {
    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "diskDeviceId", tag = true)
    private String diskDeviceId;

    @Column(name = "diskFileSystem")
    private String diskFileSystem;

    @Column(name = "diskMountPoint")
    private String diskMountPoint;

    @Column(name = "diskMountOptions")
    private String diskMountOptions;

    @Column(name = "diskBlockSize")
    private String diskBlockSize;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "diskUsage")
    private double diskUsage;

    @Column(name = "diskIOPSWrite")
    private double diskIOPSWrite;

    @Column(name = "diskIOPSRead")
    private double diskIOPSRead;

    @Column(name = "diskBpsWrite")
    private String diskBpsWrite;

    @Column(name = "diskBpsRead")
    private String diskBpsRead;


    @Column(name = "diskUsedSpace")
    private double diskUsedSpace;

    @Column(name = "diskUsedSpaceByte")
    private String diskUsedSpaceByte;

    @Column(name = "diskQueuelength")
    private double diskQueuelength;

    @Column(name = "diskInodeUsed")
    private double diskInodeUsed;

    @Column(name = "diskFreeSpacePercentage")
    private double diskFreeSpacePercentage;

    @Column(name = "diskFreeSpaceByte")
    private String diskFreeSpaceByte;



}
