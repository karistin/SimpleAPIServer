package Java_agent;

import Entity.DataSet;
import org.objectweb.asm.*;
import repo.DataSetRepo;
import repo.DataSetRepoMemory;

import java.sql.Array;
import java.util.*;

import static org.objectweb.asm.Opcodes.ASM4;
import static org.objectweb.asm.Opcodes.ASM9;

public class ClassPrinter extends ClassVisitor {

    DataSet dataset = new DataSet();
    public ClassPrinter(){
        super(ASM9);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {

        dataset.setMajor_version(version);
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
    public ModuleVisitor visitModule(String name, int access, String version) {
        return super.visitModule(name, access, version);
    }

    @Override
    public void visitNestHost(String nestHost) {
        super.visitNestHost(nestHost);
    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {
        System.out.println("Outer class: " + owner);
        super.visitOuterClass(owner, name, descriptor);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        System.out.println("Annotation: " + descriptor);
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    public AnnotationVisitor visitTypeAnnotation(int typeRef, TypePath typePath, String descriptor, boolean visible) {
        return super.visitTypeAnnotation(typeRef, typePath, descriptor, visible);
    }

    @Override
    public void visitAttribute(Attribute attribute) {
        System.out.println("Class Attribute: " + attribute.type);
        super.visitAttribute(attribute);
    }

    @Override
    public void visitNestMember(String nestMember) {
        super.visitNestMember(nestMember);
    }

    @Override
    public void visitPermittedSubclass(String permittedSubclass) {
        super.visitPermittedSubclass(permittedSubclass);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        System.out.println("Inner Class: " + innerName + " defined in " + outerName);
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public RecordComponentVisitor visitRecordComponent(String name, String descriptor, String signature) {
        return super.visitRecordComponent(name, descriptor, signature);
    }

    @Override
    public  FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {

        dataset.appendField_value(name, Arrays.asList(descriptor, value));
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

//        MethodPrinter mv = new MethodPrinter();
        dataset.appendMethod_value(name, descriptor);

        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
