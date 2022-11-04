package lucas.board.basic.repo;

import lucas.board.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : lucas.board.basic.repo
 * fileName       : UserJpaRepo
 * author         : lucas
 * date           : 2022-11-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-04        lucas       최초 생성
 */
public interface UserJpaRepo extends JpaRepository<User, Long> {
}
