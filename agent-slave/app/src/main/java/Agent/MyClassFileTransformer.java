package Agent;

import Package.GetClass;
import Entity.DataSet;
import Util.Filter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        if(Filter.classFilering(className) && !className.equals("Entity/MethodInstr"))
        {
            LOG.info("Lodding Class : " + className);
//            className = className.substring(className.lastIndexOf("/")+1);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor vistor = new MyClassVisitor(writer, className);
            reader.accept(vistor, ClassReader.EXPAND_FRAMES);
            App.taskRepository.save(vistor.getDataset());
//            try
//            {
//                Path path = Paths.get("classes/"+className+".class");
//                Files.write(path, classfileBuffer);
//                System.out.println(path.toAbsolutePath());
//            }catch (IOException e){
//                System.out.println(e);
//            }

            return writer.toByteArray();
        }
        return classfileBuffer;
    }
}
