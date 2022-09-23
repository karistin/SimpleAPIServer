package lucas.agent;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Arrays;


/**
 * packageName    : lucas.exporter.lucas.agent
 * fileName       : TransforClass
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */
public class TransforClass implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

//        TestingServlet
        if(className.contains("service") && Filter.classFilering(className))
        {
            System.out.println(className);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            MyClassVisitor visitor = new MyClassVisitor(writer);

            reader.accept(visitor, ClassReader.EXPAND_FRAMES);

//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(new File("Print.class"));
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
