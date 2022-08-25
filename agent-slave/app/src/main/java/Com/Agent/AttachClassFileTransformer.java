package Com.Agent;

import Com.Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.ProtectionDomain;

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
public class AttachClassFileTransformer implements ClassFileTransformer {

    /** The internal form class name of the class to transform */
    private String targetClassName;
    /** The class loader of the class we want to transform */
    private ClassLoader targetClassLoader;


    public AttachClassFileTransformer(String targetClassName, ClassLoader targetClassLoader) {
        this.targetClassName = targetClassName;
        this.targetClassLoader = targetClassLoader;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        //bootLoader erroring 방지
        String finalTargetClassName = this.targetClassName
                .replaceAll("\\.", "/");


        if (className.equals(finalTargetClassName) && loader.equals(targetClassLoader))
        {
            LOG.info("Lodding Class : " + className);
//            className = className.substring(className.lastIndexOf("/")+1);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor vistor = new MyClassVisitor(writer, className);
            reader.accept(vistor, ClassReader.EXPAND_FRAMES);
            vistor.getDataset().printDataset();
            App.taskRepository.save(vistor.getDataset());


//            String file = System.getProperty("user.dir") + className;
//            if(Files.exists(Paths.get(file))) {
//                try {
//                    Files.delete(Paths.get(file));
//                    FileWriter filewriter = new FileWriter(file);
//                    filewriter.write(String.valueOf(writer.toByteArray()));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }

            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}
