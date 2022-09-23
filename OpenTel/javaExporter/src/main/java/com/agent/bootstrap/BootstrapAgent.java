package com.agent.bootstrap;

import com.agent.config.JavaVersionUtils;

import java.lang.instrument.Instrumentation;

/**
 * packageName    : com.agent
 * fileName       : BootstrapAgent
 * author         : lucas
 * date           : 2022-09-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-23        lucas       최초 생성
 */
public class BootstrapAgent {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.specification.version", ""));
        System.out.println(System.getProperty("java.version", ""));
    }
    private static void premain(String agentArgs, Instrumentation inst){

        /*
        * version test
        * */
        String javaSpecVersion = JavaVersionUtils.getJavaSpecificationVersion();

        startAgent(agentArgs, inst);
    }

    private static void startAgent(String agentArgs, Instrumentation inst) {

            long startTime = System.currentTimeMillis();
            String javaVersion = System.getProperty("java.version", "");



    }
}
