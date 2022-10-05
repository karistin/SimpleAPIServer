package lucas.base.visitor.filtervisitor;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.TypePath;

import static lucas.base.Constants.SPECIAL_CLIENT;
import static lucas.base.Constants.SPECIAL_CONSTRUCTOR;
import static org.objectweb.asm.Opcodes.ASM9;

/**
 * packageName    : lucas.base.asmExtend
 * fileName       : FilterVisitor
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class FilterVisitor extends ClassVisitor {

    ClassVisitor cw;
    String className;

    public FilterVisitor(final ClassVisitor classVisitor, String className) {
        super(ASM9, classVisitor);
        this.className = className;
        this.cw = classVisitor;

    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("descriptor : "+ descriptor + " visible : "+ visible);

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(name.equals("doFilter"))
        {
            return new DoFilterVisitor(ASM9, mv, access, name, descriptor);
        }
        return mv;
    }
}
