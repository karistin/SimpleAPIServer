package com.lucas.osapi.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : com.lucas.osapi.model
 * fileName       : SingleResult
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
public class SingleResult<T> extends CommonResult{
    private T data;
}
