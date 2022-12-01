package com.lucas.osapi.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * packageName    : com.lucas.osapi.model.response
 * fileName       : CommonResult
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */
@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "API서버 시간값(UTC)")
    private long time;

    @ApiModelProperty(value = "응답 성공 여부 : true/false")
    private boolean success;

    @ApiModelProperty(value = "응답 코드 번호 : >= 0 정상, < 0 비정상")
    private int code;

    @ApiModelProperty(value = "응답 메세지")
    private String msg;

}
