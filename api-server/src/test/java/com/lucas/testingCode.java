package com.lucas;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * packageName    : com.lucas fileName       : testingCode author         : lucas date           :
 * 2022-12-15 description    : =========================================================== DATE
 *         AUTHOR             NOTE -----------------------------------------------------------
 * 2022-12-15        lucas       최초 생성
 */
public class testingCode {

    @Test
    public void arraySlice() {
        assertArrayEquals(new int[]{2, 3, 4}, solution(new int[]{1, 2, 3, 4, 5}, 1, 3));
        assertArrayEquals(new int[]{3, 5}, solution(new int[]{1, 3, 5}, 1, 2));
    }

    private int[] solution(int[] numbers, int start, int end) {
        int[] arr = Arrays.copyOfRange(numbers, start, end + 1);
        return arr;
    }
}
