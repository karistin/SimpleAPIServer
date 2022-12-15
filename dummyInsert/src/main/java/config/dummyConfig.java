package config;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : config fileName       : dummyConfig author         : lucas date           :
 * 2022-12-15 description    : =========================================================== DATE
 *         AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
@Getter
@Setter
public class dummyConfig {

    private long intervel = 5000;

    @Override
    public String toString() {
        return "dummyConfig{" +
            "intervel=" + intervel +
            '}';
    }
}
