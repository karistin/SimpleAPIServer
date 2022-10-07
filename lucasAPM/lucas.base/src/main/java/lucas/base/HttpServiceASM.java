package lucas.base;

import lucas.base.asm.IASM;
import org.objectweb.asm.ClassVisitor;

/**
 * packageName    : lucas.base
 * fileName       : HttpServiceASM
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class HttpServiceASM implements IASM {
    @Override
    public ClassVisitor transform(ClassVisitor cv, String className, ClassDesc classDesc) {
        return null;
    }
}
