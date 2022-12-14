package com.lucas.osapi.controller.temp;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.lucas.osapi.controller fileName       : HelloController author         :
 * lucas date           : 2022-12-12 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2022-12-12        lucas       최초 생성
 */
@RestController()
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("")
    public Map<String, Integer> testcall(){
        return Stream.of(new Object[][]{
            {"kim", 1},
            {"dav", 4},
            {"part", 10}
        }).collect(Collectors.toMap(item -> (String)item[0], item -> (Integer) item[1]));
    }
}
