package lucas.board.basic.model.response;

import lombok.Getter;
import lombok.Setter;

/**
 * packageName    : lucas.board.basic.model.response
 * fileName       : SingleResult
 * author         : lucas
 * date           : 2022-11-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-04        lucas       최초 생성
 */
@Getter
@Setter
public class SingleResult<T> extends CommonResult {
    private T data;
}
