package com.lucas.influxdb.entity;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : CpuInfo
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 *  Tags are automatically converted to strings, since tags are strings to com.lucas.influxdb Supported values for fields are boolean,
 *  int, long, double, Boolean, Integer, Long, Double. The time field should be of type instant.
 */


@Measurement(name = "CpuInfo", database = "OsData")
public class CpuInfo {
//    time, cpuUsage, host, idleUsage, irqUsage, niceUsage, sofrIrqUsage, stealUsage, sysUsage, uid, userUsage, waitIoUsage

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public double getIdleUsage() {
        return idleUsage;
    }

    public void setIdleUsage(double idleUsage) {
        this.idleUsage = idleUsage;
    }

    public double getIrqUsage() {
        return irqUsage;
    }

    public void setIrqUsage(double irqUsage) {
        this.irqUsage = irqUsage;
    }

    public double getNiceUsage() {
        return niceUsage;
    }

    public void setNiceUsage(double niceUsage) {
        this.niceUsage = niceUsage;
    }

    public double getSoftIrqUsage() {
        return softIrqUsage;
    }

    public void setSoftIrqUsage(double softIrqUsage) {
        this.softIrqUsage = softIrqUsage;
    }

    public double getStealUage() {
        return stealUage;
    }

    public void setStealUage(double stealUage) {
        this.stealUage = stealUage;
    }

    public double getSysUsage() {
        return sysUsage;
    }

    public void setSysUsage(double sysUsage) {
        this.sysUsage = sysUsage;
    }

    public double getUserUsage() {
        return userUsage;
    }

    public void setUserUsage(double userUsage) {
        this.userUsage = userUsage;
    }

    public double getWaitIoUsage() {
        return waitIoUsage;
    }

    public void setWaitIoUsage(double waitIoUsage) {
        this.waitIoUsage = waitIoUsage;
    }

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Column(name = "uid", tag = true)
    private String uid;
    @Column(name = "host")
    private String hostname;

    @Column(name = "idleUsage")
    private double idleUsage;


    @Column(name = "irqUsage")
    private double irqUsage;

    @Column(name = "niceUsage")
    private double niceUsage;

    @Column(name = "sofrIrqUsage")
    private double softIrqUsage;

    @Column(name = "stealUsage")
    private double stealUage;

    @Column(name = "sysUsage")
    private double sysUsage;



    @Column(name = "userUsage")
    private double userUsage;


    @Column(name = "waitIoUsage")
    private double waitIoUsage;

    @Override
    public String toString() {
        return "CpuInfo{" +
                "time=" + time +
                ", cpuUsage=" + cpuUsage +
                ", hostname='" + hostname + '\'' +
                ", idleUsage=" + idleUsage +
                ", irqUsage=" + irqUsage +
                ", niceUsage=" + niceUsage +
                ", softIrqUsage=" + softIrqUsage +
                ", stealUage=" + stealUage +
                ", sysUsage=" + sysUsage +
                ", uid='" + uid + '\'' +
                ", userUsage=" + userUsage +
                ", waitIoUsage=" + waitIoUsage +
                '}';
    }
}
