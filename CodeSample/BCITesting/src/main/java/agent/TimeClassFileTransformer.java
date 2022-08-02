package agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import util.Filter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * packageName    : agent
 * fileName       : TimeClassFileTransformer
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class TimeClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (Filter.classFilering(className)){
            System.out.println("Loaded classes " + className);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            reader.accept(new TimeClassVistor(writer), ClassReader.EXPAND_FRAMES);
            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}

