package Com.Agent;

import Com.Entity.DataSet;
import Com.Entity.FieldValue;
import Com.Entity.Methodvalue;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class MyClassVisitor extends ClassVisitor implements Opcodes{
    private DataSet dataset = new DataSet();
    private String className = "";
    public MyClassVisitor(final ClassVisitor cv, String className){
        super(ASM9, cv);
        this.className = className;
    }
    public DataSet getDataset() {
        return dataset;
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
                MyMethodAdapter myMethodVisitor = new MyMethodAdapter(ASM9, mv, access, name, descriptor, this.className);
                Methodvalue methodvalue = new Methodvalue();
                methodvalue.setAccess(access);
                methodvalue.setName(name);
                methodvalue.setDescriptor(descriptor);
                methodvalue.setMethodInsnValues(myMethodVisitor.getMethodInsnValues());
                dataset.setMethodvalues(methodvalue);

                return myMethodVisitor;
            }
        }
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
