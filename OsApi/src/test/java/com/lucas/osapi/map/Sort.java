package com.lucas.osapi.map;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ArraySortedAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * packageName    : com.lucas.osapi.map
 * fileName       : Sort
 * author         : lucas
 * date           : 2022-11-14
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-14        lucas       최초 생성
 */
@Slf4j
public class Sort {

    @Test
    public void arraySort(){

        int[] arr = { 1, 43, 345, 67, 4, 7, 234, 4353, 1232 };
        int[] sortArr = { 1, 4, 7, 43, 67, 234, 345, 1232, 4353 };
        Arrays.sort(arr);
        Assert.assertEquals("Array is no sorted", arr, sortArr);

    }

}
