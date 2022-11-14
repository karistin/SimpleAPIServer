package com.lucas.osapi.random;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * packageName    : com.lucas.osapi.random
 * fileName       : RandomTest
 * author         : lucas
 * date           : 2022-11-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-14        lucas       최초 생성
 */
@Slf4j
public class RandomTest {

    @Test
    public void randFloor(){
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        Double doubleValue = rand.nextDouble()*100;
        log.info(String.valueOf(doubleValue));
        log.info(String.valueOf(rand.nextFloat()*100));
        log.info(String.valueOf(Math.floor(doubleValue*100)/100));

    }
}
