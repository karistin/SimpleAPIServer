package lucas.board.basic.controller.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lucas.board.basic.advice.exception.CEmailSigninFailedException;
import lucas.board.basic.config.security.JwtTokenProvider;
import lucas.board.basic.entity.User;
import lucas.board.basic.model.response.CommonResult;
import lucas.board.basic.model.response.SingleResult;
import lucas.board.basic.repo.UserJpaRepo;
import lucas.board.basic.service.ResponseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * packageName    : lucas.board.basic.controller.v1
 * fileName       : SignController
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */
@Api(tags = {"1. Sign"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class SignController {
    private final UserJpaRepo userJpaRepo;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/signin")
    public SingleResult<String> signin(@ApiParam(value = "회원 ID : 이메일", required = true) @RequestParam String id,
            @ApiParam(value = "비밀번호", required = true)@RequestParam String password){
        User user = userJpaRepo.findByUid(id).orElseThrow(CEmailSigninFailedException::new);
        if( !passwordEncoder.matches(password, user.getPassword()))
            throw new CEmailSigninFailedException();
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getMrsl()), user.getRoles()));
    }

    @ApiOperation(value = "가입", notes = "회원가입을 한다.")
    @PostMapping
    public CommonResult signup(@ApiParam(value = "회원ID : 이메일", required = true) @RequestParam String id,
            @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
            @ApiParam(value = "이름", required = true) @RequestParam String name) {

        userJpaRepo.save(User.builder()
                             .uid(id)
                             .password(passwordEncoder.encode(password))
                             .name(name)
                             .roles(Collections.singletonList("ROLE_USER"))
                             .build());

        return responseService.getSuccessResult();
    }
}
