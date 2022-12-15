package config;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : config fileName       : InfluxConfig author         : lucas date           :
 * 2022-12-15 description    : =========================================================== DATE
 *         AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
@Getter
@Setter
public class InfluxConfig {

    private String url;
    private String port;
    private String dbname;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "InfluxConfig{" +
            "driver='" + url + '\'' +
            ", port=" + port +
            ", dbname='" + dbname + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
