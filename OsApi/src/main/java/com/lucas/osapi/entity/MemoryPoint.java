package com.lucas.osapi.entity;

import java.time.Instant;

/*
*  Mapping with influxdb POJOs
*  https://www.baeldung.com/java-influxdb
* */
public class MemoryPoint {
    private Instant time;
    private Long memoryUsage;

    private Long free;
    private Long cache;
    private Long buffer;
    private Long used;

    private Long swapUsed;
    private Long slab;
    private Long pageFault;
}
