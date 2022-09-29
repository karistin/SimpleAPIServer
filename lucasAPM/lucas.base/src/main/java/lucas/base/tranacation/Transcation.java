package lucas.base.tranacation;

/**
 * packageName    : lucas.base.Dao
 * fileName       : Transcation
 * author         : lucas
 * date           : 2022-09-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-29        lucas       최초 생성
 */
public class Transcation {

    String domain;
    String instance;
//    primary key
    String txid;
    String clientIP;
    String clientID;

//   HH:MM:SS.ssssss
    long startTime;



}
