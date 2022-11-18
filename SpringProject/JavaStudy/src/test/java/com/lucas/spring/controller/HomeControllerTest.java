package com.lucas.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.lucas.spring.controller
 * fileName       : HomeControllerTest
 * author         : lucas
 * date           : 2022-11-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-18        lucas       최초 생성
 */
@SpringBootTest
class HomeControllerTest {

    @Autowired
    private HomeController homeController;

    @Test
    public void contextLoads() throws Exception{
        assertThat(homeController).isNotNull();
    }
}