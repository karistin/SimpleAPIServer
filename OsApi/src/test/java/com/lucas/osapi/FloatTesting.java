package com.lucas.osapi;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * packageName    : com.lucas.osapi
 * fileName       : FloatTesting
 * author         : lucas
 * date           : 2022-11-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-10        lucas       최초 생성
 */
@Slf4j
public class FloatTesting {

    @Test
    public void testingFlot(){
        float cpuUsage =  15.3f;
        log.info(String.valueOf(cpuUsage));
    }
}
