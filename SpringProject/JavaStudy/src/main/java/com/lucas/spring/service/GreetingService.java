package com.lucas.spring.service;

import org.springframework.stereotype.Service;

/**
 * packageName    : com.lucas.spring.service
 * fileName       : GreetingService
 * author         : lucas
 * date           : 2022-11-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-18        lucas       최초 생성
 */
@Service
public class GreetingService {
    public String greet() {
        return "Hello, World";
    }
}
