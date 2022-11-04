package lucas.board.basic.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * packageName    : lucas.board.basic.model.response
 * fileName       : ListResult
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
public class ListResult<T> extends CommonResult {
    private List<T> list;
}
