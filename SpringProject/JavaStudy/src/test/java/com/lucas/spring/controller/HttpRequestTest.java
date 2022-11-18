package com.lucas.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.lucas.spring.controller
 * fileName       : HttpRequestTest
 * author         : lucas
 * date           : 2022-11-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-18        lucas       최초 생성
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception{
        assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello World");
    }
}
