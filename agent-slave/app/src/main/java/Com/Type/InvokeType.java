package Com.Type;

import org.objectweb.asm.Opcodes;

import java.util.HashMap;

/**
 * packageName    : Type
 * fileName       : InvokeType
 * author         : lucas
 * date           : 2022-07-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-26        lucas       최초 생성
 */
public class InvokeType {
    private static HashMap<Integer, String> invokeMap = new HashMap<>(){
        {
            put(Opcodes.INVOKEVIRTUAL,"INVOKEVIRTUAL");
            put(Opcodes.INVOKESPECIAL,"INVOKESPECIAL");
            put(Opcodes.INVOKESTATIC,"INVOKESTATIC");
            put(Opcodes.INVOKEINTERFACE,"INVOKEINTERFACE");
            put(Opcodes.INVOKEDYNAMIC,"INVOKEDYNAMIC");

        }
    };
    /*
     * 함수 호출 지시어 다른 함수를 불러온다.
     * 주의 ) lambda 의 경우 다른 곳에 있다.
     * opcode :  INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
     * INVOKEVIRTUAL  c1 : c1이 가르키는 메소드 가상 디스패치를 통해 호출  (정적 디스패치 / 동적 디스패치 )
     * INVOKESPECIAL c1 : c1이 가르키는 메소드 특별한 디스패치를 통해 호출 init / client
     * INVOKESTATIC c1 : c1이 가르키는 정적 메소드 호출
     * INVOKEINTERFACE c1 , count, 0: c1이 가르키는 인터페이스 메소들ㄹ 오프셋 룩업을 통해 가르킨다.
     * owner : 실행하는 놈의 주인 클라스 이름 보기
     * isInterface : owner가 interface인지
     * */

    public static String getInvokeMap(int opcode) {
        return invokeMap.get(opcode);
    }

}
