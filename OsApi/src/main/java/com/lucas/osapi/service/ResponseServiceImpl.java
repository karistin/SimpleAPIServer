package com.lucas.osapi.service;

import com.lucas.osapi.model.response.CommonResult;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import org.springframework.stereotype.Service;

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

@Service
public class ResponseServiceImpl implements ResponseService{


//    단일 성공 처리
    @Override
    public <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

//    다중 성공 처리
    @Override
    public <T> ListResult<T> getListResult(List<T> list) {
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }

//    성공 결과 처리
    @Override
    public CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

//    실페 결과 처리
    @Override
    public CommonResult getFailResult() {
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }

    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
