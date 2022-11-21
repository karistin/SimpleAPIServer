package entity;

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

    @Column(name = "host")
    private String hostname;

    @Column(name = "memUsageByteAll")
    private String memUsageByteAll;

    @Column(name = "memUsageByteFree")
    private String memUsageByteFree;

}
