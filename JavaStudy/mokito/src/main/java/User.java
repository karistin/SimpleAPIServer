import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : User
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String pwd;
}
