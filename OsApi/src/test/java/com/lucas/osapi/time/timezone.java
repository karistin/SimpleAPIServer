package com.lucas.osapi.time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertTrue;

/**
 * packageName    : com.lucas.osapi.time
 * fileName       : timezone
 * author         : lucas
 * date           : 2022-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-11        lucas       최초 생성
 */
@Slf4j
public class timezone {

    @Test
    public void testTimeZone(){
        Calendar calendar = Calendar.getInstance();
        log.info(String.valueOf(calendar.getTimeZone()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");
        System.out.println(simpleDateFormat.format(new Date()));
        log.info(simpleDateFormat.format(System.currentTimeMillis()));
    }
}
