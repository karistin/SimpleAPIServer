package lucas.base.asm.util;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * packageName    : lucas.base.asm.util
 * fileName       : AsmUtil
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ASM Util
 * class desc 확인용도
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class AsmUtil implements Opcodes {
    public static boolean isStatic(int access){
        return (access & ACC_STATIC) != 0;
    }
    public static boolean isPublic(int access){
        return (access & ACC_PUBLIC) != 0;
    }
    public static boolean isInterface(int access){
        return (access & ACC_INTERFACE) != 0;
    }

    public static boolean isabstract(int access) {
        return (access & ACC_ABSTRACT) != 0;
    }
    public static Type stringType = Type.getType(String.class);

}
