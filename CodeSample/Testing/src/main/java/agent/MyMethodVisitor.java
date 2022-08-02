package agent;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * packageName    : agent
 * fileName       : MyMethodVisitor
 * author         : lucas
 * date           : 2022-07-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-21        lucas       최초 생성
 */
public class MyMethodVisitor extends MethodVisitor implements Opcodes {

    String className;

    public MyMethodVisitor(final MethodVisitor methodVisitor, String className) {
        super(ASM9, methodVisitor);
        this.className = className;
    }

    @Override
    public void visitLineNumber(int line, Label start) {

        CodeCoverageAPI.addLine(className,line);  //asm
        //jvm
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(line);
        mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
        mv.visitMethodInsn(INVOKESTATIC, "agent/CodeCoverageAPI", "markLineExecuted", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        super.visitLineNumber(line, start);
    }

    @Override
    public void visitEnd() {
        // Send all the line number (Set) to a global static class

        super.visitEnd();
    }
}
