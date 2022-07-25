package agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * packageName    : agent
 * fileName       : TimeClassVistor
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class TimeClassVistor extends ClassVisitor implements Opcodes {
    public TimeClassVistor(ClassVisitor visitor) {
        super(ASM9, visitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = cv.visitMethod(access, name, descriptor, signature, exceptions);
        if ( ((access & Opcodes.ACC_STATIC) == Opcodes.ACC_STATIC) )
            return methodVisitor;

        return new TimeAdviceAdapter(ASM9, methodVisitor,access , name, descriptor);
    }


}
