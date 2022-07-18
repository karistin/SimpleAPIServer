package Package;

import Entity.DataSet;
import org.checkerframework.checker.units.qual.C;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class GetClass {
    public static DataSet getClass(String ClassName) throws IOException {
        ClassReader classReader = new ClassReader(ClassName);
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
        classReader.accept(myClassVisitor, ClassReader.SKIP_DEBUG);
        return myClassVisitor.dataset;
    }
}
