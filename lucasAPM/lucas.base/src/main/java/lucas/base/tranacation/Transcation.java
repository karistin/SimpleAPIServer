package lucas.base.tranacation;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

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

    long ThreadId = Thread.currentThread().getId();

    String domain;
    String instance;

//    primary key
    String txid;
    String clientIP;
    String ClientID;

//    YYYY-MM-DD HH:MM:SS.ffffff
    Timestamp startTime;
    Timestamp endTime;
//    transaction End
    Timestamp collectTime;
//    send to DB


//    사용자 구분 ( IP , 쿠키 응용)
    long responseTime;
    long sqlTime;
    long sqlCount;
    long fetchTime;
    long externalTime;
    long cpuTime;

    List<String> errorList;
    String application;

}
