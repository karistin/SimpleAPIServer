package config;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : config fileName       : DummyConfig author         : lucas date           :
 * 2022-12-15 description    : =========================================================== DATE
 *         AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
@Getter
@Setter
public class DummyConfig {

    private long intervel;

    @Override
    public String toString() {
        return "DummyConfig{" +
            "intervel=" + intervel +
            '}';
    }
}
