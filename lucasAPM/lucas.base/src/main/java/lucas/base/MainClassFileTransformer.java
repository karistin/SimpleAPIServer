package lucas.base;



import lucas.base.asm.IASM;
import lucas.base.asm.util.AsmUtil;
import lucas.base.visitor.filtervisitor.FilterVisitor;
import lucas.base.visitor.servletvisitor.ServletVistior;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
    protected static List<IASM> asms = new ArrayList<>();

//    private Logger.

    private String[] filteringList = {"java", "javax", "org/apache", "org/eclipse","sun","jdk","com/sun", "com/google", "org/jcp" , "org/xml", "org/mariadb"};

    static{
        reload();
    }

    public static void reload(){
        List<IASM> temp = new ArrayList<>();
        temp.add(new HttpServiceASM());


        asms = temp;
    }

    public MainClassFileTransformer(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


//        class filering
        if (ignoreTarget(loader, className, classfileBuffer) || classnameFiltering(className)) {
            return classfileBuffer;
        }

//        checking classDesc
        final ClassDesc classDesc = new ClassDesc();
        ClassReader classReader = new ClassReader(classfileBuffer);
        classReader.accept(new ClassVisitor(Opcodes.ASM9) {
            @Override
            public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
                classDesc.set(version, access, name, signature, superName, interfaces);
                super.visit(version, access, name, signature, superName, interfaces);
            }

            @Override
            public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
                classDesc.annotation += descriptor;
                return super.visitAnnotation(descriptor, visible);
            }
        }, 0 );


        if (AsmUtil.isInterface(classDesc.access)) {
            return classfileBuffer;
        }

        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassVisitor classvisitor = classWriter;
        List<IASM> workAsms = asms;

        for (int i = workAsms.size() - 1; i >= 0; i--) {
            classvisitor = workAsms.get(i).transform(classvisitor, className, classDesc);
            if (classvisitor != classWriter) {
                classReader = new ClassReader(classfileBuffer);
                classReader.accept(classvisitor, ClassReader.EXPAND_FRAMES);
                classfileBuffer = classWriter.toByteArray();

            }
        }







        List<String> interfaces = List.of(classReader.getInterfaces());


//        httpServlet
        if (classReader.getSuperName().equals("javax/servlet/http/HttpServlet")) {
            System.out.println("Servlet Class: " + className);
            classvisitor = new ServletVistior(classWriter, className);


        }
//        Filter
        else if (!interfaces.isEmpty()) {
            for (String interfaceName : interfaces) {
                if (interfaceName.equals("javax/servlet/Filter")) {
                    System.out.println("Filer Class: " + className);
                    classvisitor = new FilterVisitor(classWriter, className);
                }
            }
        }
//        FileOutputStream fos = null;
//            try {
////                System.out.println(System.getProperties());
//                fos = new FileOutputStream(new File("Print.class"));
//                fos.write(classWriter.toByteArray());
//
//                fos.flush();
//                fos.close();
//            } catch (IOException e) {
//                System.out.println("Class Writing Error ");
//                throw new RuntimeException(e);
//            }


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
