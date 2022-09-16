package lucas.agent;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

/**
 * packageName    : lucas.agent
 * fileName       : MyMethodAdapter
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */
public class MyMethodAdapter extends AdviceAdapter {
    /**
     * Constructs a new {@link AdviceAdapter}.
     *
     * @param api           the ASM API version implemented by this visitor. Must be one of the {@code
     *                      ASM}<i>x</i> values in {@link Opcodes}.
     * @param methodVisitor the method visitor to which this adapter delegates calls.
     * @param access        the method's access flags (see {@link Opcodes}).
     * @param name          the method's name.
     * @param descriptor    the method's descriptor (see {@link Type Type}).
     */

    private String methodName;

    protected MyMethodAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(api, methodVisitor, access, name, descriptor);
        this.methodName = name;
    }

    @Override
    protected void onMethodEnter() {

        System.out.println(methodName + " find");
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(INVOKESTATIC, "lucas/agent/MethodHook","starthook","(Ljava/lang/String;)V", false);
        super.onMethodEnter();
    }

    @Override
    protected void onMethodExit(int opcode) {

        mv.visitMethodInsn(INVOKESTATIC, "lucas/agent/MethodHook","endhook","()V", false);
        super.onMethodExit(opcode);
    }
}
