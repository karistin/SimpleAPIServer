package Com.Agent;

import Com.Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileWriter;
import java.io.IOException;
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

    public MyClassFileTransformer() {
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //bootLoader erroring 방지
        if(Filter.classFilering(className) && !className.equals("Com/Entity/MethodInstr") && !className.equals("Com/Agent/MethodCount") && !className.equals("Com/Agent/CostAccounter"))
        {
            LOG.info("Lodding Class : " + className);
//            className = className.substring(className.lastIndexOf("/")+1);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor vistor = new MyClassVisitor(writer, className);
            reader.accept(vistor, ClassReader.EXPAND_FRAMES);
            App.taskRepository.save(vistor.getDataset());


            System.out.println(Arrays.toString(writer.toByteArray()));
//            String file = System.getProperty("user.dir") + className;
//            if(Files.exists(Paths.get(file))) {
//                try {
//                    Files.delete(Paths.get(file));
//                    FileWriter filewriter = new FileWriter(file);
////                    filewriter.write(String.valueOf(writer.toByteArray()));
////                    LOG.info(String.valueOf(writer.toByteArray()));
//                } catch (IOException e) {
//                    System.out.println("Writing Exception Interrupt ");
//                }
//            }


            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}
