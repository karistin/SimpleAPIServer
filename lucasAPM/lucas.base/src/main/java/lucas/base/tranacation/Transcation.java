package lucas.base.tranacation;

import java.sql.Time;

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
    long clientID;
//    사용자 구분 ( IP , 쿠키 응용)
    Time responseTime;


//   HH:MM:SS.ssssss
    long startTime;



}
