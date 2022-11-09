package com.lucas.osapi.service;

import com.lucas.osapi.model.response.CommonResult;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : ResponseService
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */

@Service
public interface ResponseService  {


    @Getter
    @AllArgsConstructor
    enum CommonResponse{
        SUCCESS(0, "Success"),
        FAIL(-1,"Fail");

        int code;
        String msg;
    }

//    SingResult
    <T> SingleResult<T> getSingleResult(T data);

//    ListResult
    <T> ListResult<T> getListResult(List<T> list);

//    Success Result
    CommonResult getSuccessResult();

    //    Fail Result
    /*
    *  Upgrade Excepition Advice
    * */
    CommonResult getFailResult();

}
