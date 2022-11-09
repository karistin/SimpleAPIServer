package com.lucas.osapi.service;

import com.lucas.osapi.model.response.CommonResult;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : ResponseServiceImpl
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */
public class ResponseServiceImpl implements ResponseService{

    @Override
    public <T> SingleResult<T> getSingleResult(T data) {

        return null;
    }

    @Override
    public <T> ListResult<T> getListResult(List<T> list) {
        return null;
    }

    @Override
    public CommonResult getSuccessResult() {
        return null;
    }

    @Override
    public CommonResult getFailResult() {
        return null;
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
