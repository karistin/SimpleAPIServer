package config;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : config fileName       : ApplicationConfig author         : lucas date
 * : 2022-12-15 description    : =========================================================== DATE
 *           AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
@Getter
@Setter
public class ApplicationConfig {

    public ApplicationConfig() {
    }

    private influxConfig influxdb;
    private dummyConfig dummy;

    @Override
    public String toString() {
        return "ApplicationConfig{" +
            ", influxdb=" + influxdb +
            ", dummy=" + dummy +
            '}';
    }
}
