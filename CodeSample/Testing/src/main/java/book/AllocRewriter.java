package book;

import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 *
 * @author kittylyst
 */
// tag::ALLOC_RECORDER[]
public class AllocRewriter implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, 
      Class<?> classBeingRedefined, ProtectionDomain protectionDomain, 
      byte[] originalClassContents) throws IllegalClassFormatException {
        final ClassReader reader = new ClassReader(originalClassContents);
        final ClassWriter writer = new ClassWriter(reader, 
            ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        final ClassVisitor coster = new ClassVisitor(Opcodes.ASM5, writer) {
            @Override
            public MethodVisitor visitMethod(final int access, final String name, 
                final String desc, final String signature, 
                final String[] exceptions) {
                if (!name.equals("<clinit>") && !name.equals("<init>"))
                {
                    final MethodVisitor baseMethodVisitor =
                            super.visitMethod(access, name, desc, signature, exceptions);
                    return new AllocationRecordingMethodVisitor(baseMethodVisitor,
                            access, name, desc);
                }
                return null;
            }
        };
        reader.accept(coster, ClassReader.EXPAND_FRAMES);
        return writer.toByteArray();
    }

}
// end::ALLOC_RECORDER[]
