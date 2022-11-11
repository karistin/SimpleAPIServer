package com.lucas.osapi.entity;

import lombok.Builder;
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
@Builder
@Measurement(name = "MemoryInfo", database = "TimeSeries", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class MemoryInfo {
    @Column(name = "host", tag = true)
    private String uid;

    @Column(name = "host", tag = true)
    private String hostname;

    @Column(name = "memoryTotal")
    private long memoryTotal;

    @Column(name = "memoryUsed")
    private long memoryUsed;

    @Column(name = "memoryFree")
    private long memoryFree;

    @Column(name = "available")
    private long memoryAvailable;

    @Column(name = "buffCache")
    private long memoryBuffCache;

    @Column(name = "swapTotal")
    private long memorySwapTotal;

    @Column(name = "swapUsed")
    private long memorySwapUsed;

    @Column(name = "swapFree")
    private long memorySwapFree;

    @Override
    public String toString() {
        return "MemoryInfo{" +
                "uid='" + uid + '\'' +
                ", hostname='" + hostname + '\'' +
                ", memoryTotal=" + memoryTotal +
                ", memoryUsed=" + memoryUsed +
                ", memoryFree=" + memoryFree +
                ", memoryAvailable=" + memoryAvailable +
                ", memoryBuffCache=" + memoryBuffCache +
                ", memorySwapTotal=" + memorySwapTotal +
                ", memorySwapUsed=" + memorySwapUsed +
                ", memorySwapFree=" + memorySwapFree +
                '}';
    }
}
