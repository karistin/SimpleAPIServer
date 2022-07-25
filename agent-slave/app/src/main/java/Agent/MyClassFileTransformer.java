package Agent;

import Package.GetClass;
import Entity.DataSet;
import Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassFileTransformer;

/**
 * packageName    : Agent
 * fileName       : ClassFileTransformer
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public class MyClassFileTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(Filter.classFilering(className))
        {
            System.out.println("Lodding Class : " + className);
            DataSet data = null;
            ClassReader classReader = null;
            try {
                classReader = new ClassReader(className);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            MyClassVisitor myClassVisitor = new MyClassVisitor(classWriter);
            classReader.accept(myClassVisitor, 0);
            data = myClassVisitor.dataset;
            App.taskRepository.save(data);
            data.printDataset();
//            System.out.println(App.taskRepository);
            return classfileBuffer;
        }
        return classfileBuffer;
    }
}
