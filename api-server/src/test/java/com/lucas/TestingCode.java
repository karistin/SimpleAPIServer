package com.lucas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestingCode {

    @Test
    public void arraySlice() {
        assertArrayEquals(new int[]{2, 3, 4}, solution(new int[]{1, 2, 3, 4, 5}, 1, 3));
        assertArrayEquals(new int[]{3, 5}, solution(new int[]{1, 3, 5}, 1, 2));
    }

    private int[] solution(int[] numbers, int start, int end) {
        int[] arr = Arrays.copyOfRange(numbers, start, end + 1);
        return arr;
    }

    @Test
    public void codeTest() {
        int age = 1234;
        char[] lang = "abcdefghij".toCharArray();
        String val = String.valueOf(age);
        StringBuilder result = new StringBuilder();

        for(char ch: val.toCharArray())
            result.append(lang[Character.getNumericValue(ch) - 1]);

        System.out.println(result);
    }

    @Test
    public void logTest() {
//        java.util.logging.Logger log = new java.util.logging.Logger();
        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Hello World");
    }

    @Test
    public void sum() {
        IntStream intStream = IntStream.range(1, 10);
    }
}
