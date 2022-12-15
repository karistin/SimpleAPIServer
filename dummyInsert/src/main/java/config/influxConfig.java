package config;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : config fileName       : influxConfig author         : lucas date           :
 * 2022-12-15 description    : =========================================================== DATE
 *         AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
@Getter
@Setter
public class influxConfig {

    private String url = System.getProperty("influxdb.url");
    private String port = "8086";
    private String dbname = "OsData";
    private String username = "root";
    private String password = "root";

    @Override
    public String toString() {
        return "influxConfig{" +
            "driver='" + url + '\'' +
            ", port=" + port +
            ", dbname='" + dbname + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
