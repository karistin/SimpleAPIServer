package com.lucas.osapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

/*
*  Mapping with influxdb POJOs
*  https://www.baeldung.com/java-influxdb
* */

@Getter
@Setter
@Measurement(name = "MemoryInfo", database = "TimeSeries", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class MemoryInfo {
    @Column(name = "host", tag = true)
    private String hostname;

    @Column(name = "memoryUsage")
    private float memoryUsage;

    @Column(name = "memoryfree")
    private float memoryfree;

    @Column(name = "memoryused")
    private float memoryused;

}
