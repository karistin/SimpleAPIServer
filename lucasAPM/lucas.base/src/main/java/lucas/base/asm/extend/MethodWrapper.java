package lucas.base.asm.extend;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.CodeSizeEvaluator;
import org.objectweb.asm.tree.MethodNode;

/**
 * packageName    : lucas.base.asm
 * fileName       : MethodWrapper
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class MethodWrapper extends CodeSizeEvaluator {
    public MethodVisitor mvWrapper;
    public MethodNode method;

    public MethodWrapper(final MethodVisitor mv) {
        this(Opcodes.ASM9, mv);
    }


    public MethodWrapper(int api, MethodVisitor mv) {
        super(api, mv);

        this.mvWrapper = mv;
        this.method = (MethodNode) mv;
    }

    public String getNameWithDescription(){
        return method.name + method.desc;}
}
