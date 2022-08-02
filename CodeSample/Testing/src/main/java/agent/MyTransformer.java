package agent;


import agent.util.Filter;
import org.objectweb.asm.*;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;
import java.util.Base64;

/**
 * packageName    : agent
 * fileName       : MyClassVisitor
 * author         : lucas
 * date           : 2022-07-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
public class MyTransformer implements ClassFileTransformer{

/*
 * @param loader                the defining loader of the class to be transformed,
 *                              may be {@code null} if the bootstrap loader
 * @param className             the name of the class in the internal form of fully
 *                              qualified class and interface names as defined in
 *                              <i>The Java Virtual Machine Specification</i>.
 *                              For example, <code>"java/util/List"</code>.
 * @param classBeingRedefined   if this is triggered by a redefine or retransform,
 *                              the class being redefined or retransformed;
 *                              if this is a class load, {@code null}
 * @param protectionDomain      the protection domain of the class being defined or redefined
 * @param classfileBuffer       the input byte buffer in class file format - must not be modified
 **/

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


        if (Filter.classFilering(className)) {
//            System.out.println("className:"+className);
            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS|ClassWriter.COMPUTE_FRAMES);
            MyClassVisitor visitor = new MyClassVisitor(writer);

            reader.accept(visitor,ClassReader.EXPAND_FRAMES);




            return writer.toByteArray();
        }

        return classfileBuffer;
    }

}
