package entity;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : ServerInfo
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
@Measurement(name = "ServerInfo", database = "OsData")
public class ServerInfo {

    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "hostname")
    private String hostName;

    @Column(name = "osType")
    private String osType;

    @Column(name = "osVersion")
    private String osVersion;

    @Column(name = "totalMem")
    private String totalMem;

    @Column(name = "core")
    private int core;

    @Column(name = "ipAdress")
    private String ipadress;

    @Column(name = "agent")
    private String agentVersion;
}
