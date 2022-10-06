package lucas.base.visitor.filtervisitor;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * packageName    : lucas.base.visitor.filtervisitor
 * fileName       : DoFilterVisitor
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class DoFilterVisitor extends AdviceAdapter {
    public DoFilterVisitor(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
    }

    @Override
    protected void onMethodEnter() {
//        mv.visitMethodInsn();

        super.onMethodEnter();
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
    }
}
