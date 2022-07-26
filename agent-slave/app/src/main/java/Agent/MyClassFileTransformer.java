package Agent;

import Package.GetClass;
import Entity.DataSet;
import Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassFileTransformer;

import static Agent.App.LOG;

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
            LOG.info("Lodding Class : " + className);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor vistor = new MyClassVisitor(writer);
            reader.accept(vistor, ClassReader.EXPAND_FRAMES);
//            vistor.getDataset().printDataset();
            App.taskRepository.save(vistor.getDataset());
            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}
