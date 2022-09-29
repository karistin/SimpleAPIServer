package lucas.base.tranacation;

import java.util.UUID;

/**
 * packageName    : lucas.base.tranacation
 * fileName       : TranscationId
 * author         : lucas
 * date           : 2022-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-29        lucas       최초 생성
 */
public class TranscationId {
    private TranscationId(){}
    public static String generateID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
