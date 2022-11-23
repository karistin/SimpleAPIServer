package lucas.board.basic.service.security;

import lombok.RequiredArgsConstructor;
import lucas.board.basic.advice.exception.CUserNotFoundException;
import lucas.board.basic.repo.UserJpaRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * packageName    : lucas.board.basic.service.security
 * fileName       : CustomUserDetailService
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;


    @Override
    public UserDetails loadUserByUsername(String userPk){
        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}
