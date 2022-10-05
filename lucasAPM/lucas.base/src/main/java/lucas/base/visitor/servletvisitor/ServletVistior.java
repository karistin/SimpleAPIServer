package lucas.base.visitor.servletvisitor;

import lucas.base.servlet.HttpServletMethodName;
import lucas.base.visitor.servletvisitor.ServletMethodVisitor;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static lucas.base.Constants.SPECIAL_CLIENT;
import static lucas.base.Constants.SPECIAL_CONSTRUCTOR;
import static org.objectweb.asm.Opcodes.ASM9;

/**
 * packageName    : lucas.base
 * fileName       : ServletVistior
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class ServletVistior extends ClassVisitor {
    ClassVisitor cw;
    String className;

    private ServletVistior(int api) {
        super(api);
    }

    public ServletVistior(final ClassVisitor classVisitor, String className) {
        super(ASM9, classVisitor);
        this.cw = classVisitor;
        this.className = className;
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("descriptor : "+ descriptor + " visible : "+ visible);

        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if(!name.equals(SPECIAL_CONSTRUCTOR) && !name.equals(SPECIAL_CLIENT))
        {

            if(HttpServletMethodName.isHttpServletMethod(name))
            {
                ServletMethodVisitor servletMethodVisitor = new ServletMethodVisitor(ASM9,mv, access,name, descriptor);
                return servletMethodVisitor;
            }
//            generi method
        }


        return mv;
    }
}
