package Package;

import Agent.MyClassVisitor;
import Entity.DataSet;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class GetClass {
    public static DataSet getClass(String ClassName) throws IOException {
//        ClassReader classReader = new ClassReader(ClassName);
//        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
//        MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
//        classReader.accept(myClassVisitor, 0);
//        return myClassVisitor.dataset;
        return null;
    }
}
