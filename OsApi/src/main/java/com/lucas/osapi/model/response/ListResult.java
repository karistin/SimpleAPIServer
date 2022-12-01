package com.lucas.osapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.model.response
 * fileName       : ListResult
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
public class ListResult<T> extends CommonResult{
    private List<T> list;
}
