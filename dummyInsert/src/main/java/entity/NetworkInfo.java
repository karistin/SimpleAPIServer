package entity;

import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import org.influxdb.annotation.TimeColumn;

/**
 * packageName    : entity
 * fileName       : NetworkInfo
 * author         : lucas
 * date           : 2022-11-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-28        lucas       최초 생성
 */
@Getter
@Setter
@Measurement(name = "NetworkInfo", database = "OsData")
public class NetworkInfo {
    @TimeColumn
    @Column(name = "time")
    private Instant time;

    @Column(name = "uid", tag = true)
    private String uid;

    @Column(name = "hostname")
    private String hostname;

    @Column(name = "networkInterface", tag = true)
    private String networkInterface;

    @Column(name = "trafficIn")
    private String trafficIn;

    @Column(name = "trafficOut")
    private String trafficOut;

    @Column(name = "packetIn")
    private double packetIn;

    @Column(name = "packetOut")
    private double packetOut;

    @Column(name = "errorIn")
    private double errorIn;

    @Column(name = "errorOut")
    private double errorOut;

    @Column(name = "dropIn")
    private double dropIn;

    @Column(name = "dropOut")
    private double dropOut;

}
