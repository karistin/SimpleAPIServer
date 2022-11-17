package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

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
@Measurement(name = "DiskInfo", database = "OsData")
public class DiskUsage {
    @Column(name = "mean")
    private double mean;

    @Column(name = "diskinfo")
    private String diskinfo;

    @Column(name = "uid")
    private String uid;
}
