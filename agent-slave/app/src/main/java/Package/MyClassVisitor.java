package Package;

import Entity.DataSet;
import Entity.FieldValue;
import Entity.Methodvalue;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;
import org.objectweb.asm.util.TraceMethodVisitor;

import java.io.PrintWriter;
import java.util.Arrays;


public class MyClassVisitor extends ClassVisitor implements Opcodes{
    DataSet dataset = new DataSet();
    public MyClassVisitor(){
        super(ASM9);
    }
    public MyClassVisitor(final ClassVisitor cv){
        super(ASM9, cv);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        dataset.setMajor_version(version);
        dataset.setAccess(access);
        dataset.setClass_name(name);
        dataset.setSuper_class(superName);
        dataset.setInterfaces(interfaces);
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(String source, String debug) {
        dataset.setSource_name(source);
        super.visitSource(source, debug);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

        FieldValue fieldValue = new FieldValue();
        fieldValue.setAccess(access);
        fieldValue.setName(name);
        fieldValue.setDescriptor(descriptor);
        fieldValue.setSignature(signature);
        fieldValue.setValue(value);
        dataset.setFieldValues(fieldValue);
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        if (!name.equals( "<init>") && !name.equals( "<clinit>")){

            MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

            if(mv != null){
                MyMethodVisitor myMethodVisitor = new MyMethodVisitor(mv);

                Methodvalue methodvalue = new Methodvalue();
                methodvalue.setAccess(access);
                methodvalue.setName(name);
                methodvalue.setDescriptor(descriptor);
                methodvalue.setMethodInsnValues(myMethodVisitor.methodInsnValues);

                dataset.setMethodvalues(methodvalue);

                return myMethodVisitor;
            }
            return null;
        }
        return null;
    }
}
