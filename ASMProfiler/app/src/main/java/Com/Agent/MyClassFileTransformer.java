package Com.Agent;

import Com.Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.*;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProtectionDomain;
import java.lang.instrument.ClassFileTransformer;
import java.util.Arrays;

import static Com.Agent.App.LOG;

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
    public int classCount =0;

    public MyClassFileTransformer() {
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //bootLoader erroring 방지
        classCount +=1;
        if(Filter.classFilering(className) && !className.equals("Com/Entity/MethodInstr") && !className.equals("Com/Agent/MethodCount") && !className.equals("Com/Agent/MethodParameter"))
        {
            LOG.info("Lodding Class : " + className);
//            className = className.substring(className.lastIndexOf("/")+1);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor vistor = new MyClassVisitor(writer, className);
            reader.accept(vistor, ClassReader.EXPAND_FRAMES);
            App.taskRepository.save(vistor.getDataset());

            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(new File("Print.class"));
                fos.write(writer.toByteArray());
                fos.flush();
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(new File(className+".class"));
//                fos.write(writer.toByteArray());
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }

            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}
