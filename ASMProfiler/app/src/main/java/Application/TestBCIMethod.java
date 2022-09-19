package Application;

import java.util.Arrays;

/**
 * packageName    : Application
 * fileName       : TestBCIMethod
 * author         : lucas
 * date           : 2022-08-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-29        lucas       최초 생성
 */
public class TestBCIMethod {
    private static Object[] parameterData = null;

    public static Object[] getParameterData() {
        return parameterData;
    }

    public static void setParameterData(Object[] parameterData) {
        TestBCIMethod.parameterData = parameterData;
    }

    public static void MethodStats(Object[] parameter){
        parameterData = parameter;
//        System.out.println(Arrays.toString(parameter));
    }
}
