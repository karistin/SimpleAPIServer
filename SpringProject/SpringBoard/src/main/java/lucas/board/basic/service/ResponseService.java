package lucas.board.basic.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lucas.board.basic.model.response.CommonResult;
import lucas.board.basic.model.response.ListResult;
import lucas.board.basic.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : lucas.board.basic.service
 * fileName       : ResponseService
 * author         : lucas
 * date           : 2022-11-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-04        lucas       최초 생성
 */
@Service
public class ResponseService {

    @Getter
    @AllArgsConstructor
    enum CommonResponse{
        SUCCESS(0, "성공하였습니다."),
        FAIL(-1, "실패하였습니다.");

        int code;
        String msg;
    }

//    단일건 결과를 처리하는 메소드
    public  <T> SingleResult<T> getSingleResult(T data){
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

//    다중건 결과를 처리하는 메소드
    public <T> ListResult<T> getListResult(List<T> list){
        ListResult<T> result = new ListResult<>();
        result.setList(list);
        setSuccessResult(result);
        return result;
    }
//    성공 결과만 처리하는 메소드
    public CommonResult getSuccessResult(){
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

//  실페 결과만 처리하는 메소드
    public CommonResult getFailResult(){
        CommonResult result = new CommonResult();
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
        return result;
    }
//    public CommonResult getFailResult(int code, String msg){
//    CommonResult result = new CommonResult();
//    result.setSuccess(false);
//    result.setCode(code);
//    result.setMsg(msg);
//    return result;
//}



//    결과 모델에 API 요청 성공 데이터를 세팅해주는 메소드
    private void setSuccessResult(CommonResult result){
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}
