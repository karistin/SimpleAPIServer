package lucas.base.asm;

import lucas.base.ClassDesc;
import org.objectweb.asm.ClassVisitor;

/**
 * packageName    : lucas.base.asm
 * fileName       : IASM
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public interface IASM {
    public ClassVisitor transform(ClassVisitor cv, String className, ClassDesc classDesc);
}
