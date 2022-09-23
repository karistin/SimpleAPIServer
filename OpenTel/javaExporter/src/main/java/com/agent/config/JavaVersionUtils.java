package com.agent.config;

/**
 * packageName    : com.agent.config
 * fileName       : JavaVersionUtils
 * author         : lucas
 * date           : 2022-09-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-23        lucas       최초 생성
 */
public class JavaVersionUtils {

    public static String getJavaSpecificationVersion() {
        return System.getProperty("java.specification.version", "");
    }
}
