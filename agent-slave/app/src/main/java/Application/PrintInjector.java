package Application;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * packageName    : Application
 * fileName       : PrintInjector
 * author         : lucas
 * date           : 2022-08-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-26        lucas       최초 생성
 */
public class PrintInjector extends ClassVisitor {
    protected PrintInjector(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,  name,  desc,  signature,  exceptions);
//        System.out.println(String.format("%s %s %s", name, desc, signature));
        if(name.equals("PrintInt"))
            mv = new PrintSingleIntParameter(Opcodes.ASM5, mv, access, name, desc);
        return mv;
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(new FileInputStream(new File("./app/src/main/java/Application/Print.class")));
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        PrintInjector pi = new PrintInjector(Opcodes.ASM5, cw);

        cr.accept(pi,  0);

        FileOutputStream fos = new FileOutputStream(new File("Print.class"));
        fos.write(cw.toByteArray());
        fos.flush();
        fos.close();

        System.out.println(Arrays.toString(TestBCIMethod.getParameterData()));
    }

    class PrintSingleIntParameter extends AdviceAdapter {

        private Type[] paramTypes;

        protected PrintSingleIntParameter(int api, MethodVisitor mv, int access,
                                          String name, String desc) {
            super(api, mv, access, name, desc);
            this.paramTypes = Type.getArgumentTypes(desc);
        }

        @Override
        public void visitCode() {
            int paramLength = paramTypes.length;
            mv.visitIntInsn(Opcodes.BIPUSH, paramLength);
            mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
            mv.visitVarInsn(Opcodes.ASTORE, paramLength);

            int i =0;
            for (Type tp : paramTypes) {
                mv.visitVarInsn(Opcodes.ALOAD, paramLength);
                mv.visitIntInsn(Opcodes.BIPUSH, i);

                if (tp.equals(Type.BOOLEAN_TYPE) || tp.equals(Type.BYTE_TYPE) || tp.equals(Type.CHAR_TYPE) || tp.equals(Type.SHORT_TYPE) || tp.equals(Type.INT_TYPE))
                mv.visitVarInsn(Opcodes.ILOAD, i);
                else if (tp.equals(Type.LONG_TYPE)) {
                    mv.visitVarInsn(Opcodes.LLOAD, i);
                    i++;
                }
                else if (tp.equals(Type.FLOAT_TYPE))
                    mv.visitVarInsn(Opcodes.FLOAD, i);
                else if (tp.equals(Type.DOUBLE_TYPE)) {
                    mv.visitVarInsn(Opcodes.DLOAD, i);
                    i++;
                }
                else
                    mv.visitVarInsn(Opcodes.ALOAD, i);

                mv.visitInsn(Opcodes.AASTORE);
                i++;
            }
            this.visitVarInsn(Opcodes.ALOAD, paramLength);

            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "Application/TestBCIMethod","MethodStats","([Ljava/lang/Object;)V",false);


            super.visitCode();
        }

        @Override
        protected void onMethodEnter() {

//            Label l0 = new Label();
//            mv.visitLabel(l0);
//            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//            mv.visitVarInsn(ILOAD, 0); //1 instead of 0 if PrintInt wasn't static
//            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
        }
    }
}
