package temp.fileStream;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;

/**
 * packageName    : temp.fileStream
 * fileName       : ComparableWriter
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class ComparableWriter {

    public byte[] toByteArray() {

        ClassWriter cw = new ClassWriter(0);
        /*
            define class header

            arguments
                V1_5 : class version, Java 1.5
                ACC_XXX : java modifier
                "Comparable" : class name in internal form.
                null : generics
                "java/lang/Object" : super class in internal form.
                new String[0] : array of interfaces that are extended,
                                                    specified by their internal name

            ps.
                internal form or name : replace dot(.) with slash(/)
                new String[0] : same as null

        */
        cw.visit(Opcodes.V1_5, Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE + Opcodes.ACC_PUBLIC,
                "Comparable", null, "java/lang/Object", new String[0]);
        /*
            define field

            arguments
                ACC_XXX : java modifier
                "LESS" : field name
                "I" : type in type descriptor form, i.e. I = int
                null : generics
                new Integer(-1) : constant value only for final static field otherwise must be null

            ps.
                Since there are no annotations, call visitEnd method of the returned FieldVisitor,
                i.e. without any call to its visitAnnotation or visitAttribute methods.
         */
        cw.visitField(Opcodes.ACC_FINAL + Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(Opcodes.ACC_FINAL + Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "EQUAL", "I", null, new Integer(1)).visitEnd();
        cw.visitField(Opcodes.ACC_FINAL + Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC,
                "GREATER", "I", null, new Integer(1)).visitEnd();
        /*
            define compareTo method

            arguments
                ACC_XXX : java modifier
                "compareTo" : method name
                "(Ljava/lang/Object;)I" : descriptor of method
                null : generics
                null : an array of the exceptions that can be thrown by the method, specified by their internal name

            ps.
                internal name : replace dot(.) with slash(/)

                The visitMethod method returns a MethodVisitor,
                which can be used to define the method’s annotations and
                attributes, and most importantly the method’s code. Here, since there are no
                annotations and since the method is abstract, we call the visitEnd method
                of the returned MethodVisitor immediately.
         */
        cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();

        cw.visitEnd();
        return cw.toByteArray();
    }

    public static void main(String[] args) {
        ComparableWriter cwr = new ComparableWriter();

        FileOutputStream stream = null;

        try {
            stream = new FileOutputStream("Comparable.class");
            stream.write(cwr.toByteArray());
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}