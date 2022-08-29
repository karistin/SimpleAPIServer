package Com.Agent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : Com.Agent
 * fileName       : MethodParameter
 * author         : lucas
 * date           : 2022-08-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-29        lucas       최초 생성
 */
public class MethodParameter {
    private static  Map<String ,Object[]> parameterData = new HashMap<>();

    public Map<String, Object[]> getParameterData() {
        return parameterData;
    }

    public void setParameterData(Map<String, Object[]> parameterData) {
        MethodParameter.parameterData = parameterData;
    }

    public static void MethodStats(String methodName, Object[] parameter) {
//        parameterData = parameter;
//        System.out.println(Arrays.toString(parameter));
        parameterData.put(methodName, parameter);
    }

}
