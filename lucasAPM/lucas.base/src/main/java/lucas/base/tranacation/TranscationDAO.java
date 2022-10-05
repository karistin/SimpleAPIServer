package lucas.base.tranacation;

import java.util.List;

/**
 * packageName    : lucas.base.tranacation
 * fileName       : TranscationDAO
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class TranscationDAO {
    private static List<Transcation> transcationList  = null;

    public static List<Transcation> getInstance(){
        return transcationList;
    }



}
