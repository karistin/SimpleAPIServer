package com.lucas.osapi.entity;

import java.time.Instant;

public class CpuPoint {
    private Instant time;
    private Long systemUsage;
    private Long userUsage;
    private Long idleUsage;
    private Long niceUsage;
    private Long ioWaitUsage;
    private Long stealUsage;
    private Long irqUsage;
    private Long softirqUsage;
    private Long cpuLoad;
}
