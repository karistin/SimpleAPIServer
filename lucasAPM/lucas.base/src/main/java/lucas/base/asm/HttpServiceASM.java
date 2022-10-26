package lucas.base.asm;

import lucas.base.ClassDesc;
import lucas.base.MainClassFileTransformer;
import lucas.base.asm.IASM;
import lucas.base.trace.TraceMain;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.HashSet;

import static org.objectweb.asm.Opcodes.ASM9;

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
 *  Filter 와 servlet 를 파악하는 classvisitor
 */
public class HttpServiceASM implements IASM {
    public HashSet<String> servlets = new HashSet<String>();

    public HttpServiceASM() {
        servlets.add("javax/servlet/http/HttpServlet");
//        servlets.add("weblogic/servlet/jsp/JspBase");
    }

    @Override
    public ClassVisitor transform(ClassVisitor cv, String className, ClassDesc classDesc) {
//        org.apache.catalina.core.ApplicationHttpRequest 이자식
        if (className.equals("org/apache/catalina/core/ApplicationFilterChain")) {
            return cv;
        }
        if ( className.equals("javax/servlet/http/HttpServlet") || classDesc.checkInterfaces("javax/servlet/Filter")) {
            return new HttpServiceCV(cv, className);
        }
//        if ( className.equals("javax/servlet/http/HttpServlet") ) {
//            return new HttpServiceCV(cv, className);
//        }

        return cv;
    }
}

class HttpServiceCV extends ClassVisitor {
        private static String TARGET_SERVICE = "service";
        private static String TARGET_DOFILTER = "doFilter";

//        private static String TARGET_SIGNATURE_1 = "(Ljavax/servlet/http/HttpServletRequest;Ljavax/servlet/http/HttpServletResponse";
        private static String TARGET_SIGNATURE_2 = "(Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;";
        private String className;
        public HttpServiceCV(ClassVisitor cv, String className) {
            super(ASM9, cv);
            this.className = className;

        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (mv == null) {
                return mv;
            }

            if (desc.startsWith(TARGET_SIGNATURE_2))
            {
                if (TARGET_SERVICE.equals(name)) {
                    System.out.println("HTTP " + className);
                    return new HttpServiceMV(access, desc, className, mv, true);
                } else if (TARGET_DOFILTER.equals(name)) {
                    System.out.println("FILTER " + className);
                    return new HttpServiceMV(access, desc,className,  mv, false);
                }
            }
            return mv;
        }
    }

class HttpServiceMV extends AdviceAdapter implements Opcodes {
        private static final String TRACEMAIN = TraceMain.class.getName().replace('.', '/');
        private final static String START_SERVICE = "startHttpService";
        private final static String START_FILTER = "startHttpFilter";
        private static final String START_SIGNATURE = "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";
        private final static String END_METHOD = "endHttpService";
        private static final String END_SIGNATURE = "()V";
        private final static String REJECT = "reject";
        private static final String REJECT_SIGNATURE = "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;";
        private boolean httpservlet;

        private int statIdx;
        private Label startFinally = new Label();
        public HttpServiceMV(int access, String desc,String name, MethodVisitor mv, boolean httpservlet) {
            super(ASM9, mv,access,name ,desc);
            this.httpservlet = httpservlet;
        }


        @Override
        public void visitCode() {

            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.ALOAD, 2);
            if (httpservlet) {
                mv.visitMethodInsn(INVOKESTATIC, TRACEMAIN, START_SERVICE , START_SIGNATURE, false);
            }else{
                mv.visitMethodInsn(INVOKESTATIC , TRACEMAIN , START_FILTER , START_SIGNATURE , false);
            }

            statIdx = newLocal(Type.getType(Object.class));
            mv.visitVarInsn(Opcodes.ASTORE, statIdx);
            super.visitCode();
        }



//            mv.visitLabel(startFinally);
//            mv.visitVarInsn(Opcodes.ALOAD, statIdx);

//            mv.visitVarInsn(Opcodes.ALOAD, 1);
//            mv.visitVarInsn(Opcodes.ALOAD, 2);
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, TRACEMAIN, REJECT, REJECT_SIGNATURE, false);
//            Label end = new Label();
//            mv.visitJumpInsn(IFNULL, end);
//            mv.visitInsn(Opcodes.RETURN);
//            mv.visitLabel(end);



    @Override
        protected void onMethodExit(int opcode) {
            MainClassFileTransformer.writing = true;

//            mv.visitVarInsn(Opcodes.ALOAD, statIdx);
//            mv.visitInsn(Opcodes.ACONST_NULL);
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, TRACEMAIN, END_METHOD, END_SIGNATURE, false);
            super.onMethodExit(opcode);
        }
}

