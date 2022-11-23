package lucas.board.basic.entity;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * packageName    : lucas.board.basic.entity
 * fileName       : UserTest
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */
public class UserTest extends TestCase {

    @Test
    public void builderTest(){
        User user = User.builder().build();
    }

}