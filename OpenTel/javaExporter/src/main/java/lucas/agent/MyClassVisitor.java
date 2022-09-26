package lucas.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.ASM9;

/**
 * packageName    : lucas.agent
 * fileName       : MyClassVisitor
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */
public class MyClassVisitor extends ClassVisitor {


    protected MyClassVisitor(final ClassVisitor cv) {
        super(ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        if( !name.equals("<init>") && !name.equals("<client>")) {

            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

            MyMethodAdapter adapter = new MyMethodAdapter(ASM9, mv, access, name, descriptor);

            return adapter;
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
