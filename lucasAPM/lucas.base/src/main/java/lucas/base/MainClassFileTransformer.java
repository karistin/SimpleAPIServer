package lucas.base;



import lucas.base.visitor.filtervisitor.FilterVisitor;
import lucas.base.visitor.servletvisitor.ServletVistior;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;

import static lucas.base.Constants.START_WITH_AGENT;
import static lucas.base.JavaAgent.logger;
import static lucas.base.ProductConstants.NON_CONVERT_START_PACKAGE;

/**
 * packageName    : lucas.base
 * fileName       : MainClassFileTransformer
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class MainClassFileTransformer implements ClassFileTransformer {

    private final Instrumentation instrumentation;

    private String[] filteringList = {"java", "javax", "org/apache", "org/eclipse","sun","jdk","com/sun", "com/google", "org/jcp" , "org/xml", "org/mariadb"};
    public MainClassFileTransformer(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

//        class filering
        if (ignoreTarget(loader, className, classfileBuffer) || classnameFiltering(className)) {
            return classfileBuffer;
        }

//      pacakge/pa/class
        ClassReader classReader = new ClassReader(classfileBuffer);
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassVisitor classvisitor = null;

//        httpServlet
        if (classReader.getSuperName().equals("javax/servlet/http/HttpServlet")) {
            logger.info("Servlet Class: " + className);
            classvisitor = new ServletVistior(classWriter, className);
        }

//        Filter
        List<String> interfaces = List.of(classReader.getInterfaces());
        if (!interfaces.isEmpty()) {
            for (String interfaceName : interfaces) {
                if (interfaceName.equals("javax/servlet/Filter")) {
                    logger.info("Filer Class: " + className);
                    classvisitor = new FilterVisitor(classWriter, className);
                }
            }

        }

        classReader.accept(classvisitor, ClassReader.EXPAND_FRAMES);

        return classWriter.toByteArray();
    }

    private boolean classnameFiltering(String className) {
        for (String filtering : filteringList) {
            if (className.toLowerCase().startsWith(filtering)) {
                return true;
            }
        }
        return false;
    }

    private boolean ignoreTarget(ClassLoader loader, String className, byte[] classfileBuffer)
    {
        return classfileBuffer == null || className == null || className.startsWith(NON_CONVERT_START_PACKAGE) //
        || className.startsWith("$") || className.contains("$")|| (loader != null && loader.getClass().getName().contains(START_WITH_AGENT));
    }
}
