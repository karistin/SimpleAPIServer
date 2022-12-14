package com.lucas.osapi.advice;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.model.response.CommonResult;
import com.lucas.osapi.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : com.lucas.osapi.advice
 * fileName       : ExceptionAdvice
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
//        return responseService.getFailResult();
//    }

    @ExceptionHandler(RepoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult repoException(HttpServletRequest request, Exception e){
        return responseService.getFailResult();
    }

}
