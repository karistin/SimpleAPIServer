package agent;

import Repository.MemoryRepositoy;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : agent
 * fileName       : HelloAgent
 * author         : lucas
 * date           : 2022-07-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-22        lucas       최초 생성
 */
public class HelloAgent {
    public static List<MemoryRepositoy> memoryRepositoys = new ArrayList<>();
    public static void premain(String args, Instrumentation instrumentation) {
        instrumentation.addTransformer(new TimeClassFileTransformer());
    }

    public static void agentmain(String args, Instrumentation instrumentation) throws ClassNotFoundException, UnmodifiableClassException {
        if (args == null) {
            return;

        }

        int index = args.lastIndexOf(".");
        if (index != -1) {
            String className = args.substring(0, index);
            String methodName = args.substring(index + 1);

            instrumentation.addTransformer(new TraceClassFileTransformer(className.replace(".","/"), methodName), true);
            instrumentation.retransformClasses(Class.forName(className));
        }
    }

    private static class TraceClassFileTransformer implements ClassFileTransformer {
        private String traceClassName;
        private String traceMethodName;
        public TraceClassFileTransformer(String traceClassName, String traceMethodName) {
            this.traceClassName = traceClassName;
            this.traceMethodName = traceMethodName;
        }

        @Override
        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

            if (className.startsWith("java") || className.startsWith("jdk") || className.startsWith("javax") || className.startsWith("sun")
                    || className.startsWith("com/sun") || className.startsWith("org/xunche/agent") || !className.equals(traceClassName))
            {
                return null;
            }

            ClassReader reader = new ClassReader(classfileBuffer);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            reader.accept(new TraceVistor(className, traceMethodName, writer),ClassReader.EXPAND_FRAMES);

            return writer.toByteArray();
        }

        private class TraceVistor extends ClassVisitor {
            public TraceVistor(String className, String traceMethodName, ClassWriter writer) {
                super();
            }
        }
    }
}
