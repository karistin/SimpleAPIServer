package lucas.base;



import lucas.base.asm.HttpServiceASM;
import lucas.base.asm.IASM;
import lucas.base.asm.JDBCDriverASM;
import lucas.base.asm.util.AsmUtil;
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

import static lucas.base.constant.Constants.START_WITH_AGENT;
import static lucas.base.constant.ProductConstants.NON_CONVERT_START_PACKAGE;

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
    public static boolean writing = false;
//    asm List

    private final static String INSTRUMENT_CLASS = "C:\\Users\\seong\\Desktop\\AgentSalve\\lucasAPM\\BCIResult\\";
    private final static String INSTRUMENT_PATH_HOME = "C:\\Users\\seong\\Desktop\\agent-slave\\lucasAPM\\BCIResult\\";
    private String[] filteringList = {"java", "javax", "org/apache", "org/eclipse","sun","jdk","com/sun", "com/google", "org/jcp" , "org/xml", "org/mariadb"};

    static{
        reload();
    }

    public static void reload(){
        List<IASM> temp = new ArrayList<>();
        temp.add(new HttpServiceASM());
//        temp.add(new JDBCDriverASM());
        asms = temp;
    }

    public MainClassFileTransformer(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {


//        class filering
//        if (ignoreTarget(loader, className, classfileBuffer) || classnameFiltering(className)) {
//            return classfileBuffer;
//        }

        if (ignoreTarget(loader, className, classfileBuffer)) {
            return classfileBuffer;
        }


        ClassReader classReader = new ClassReader(classfileBuffer);
        final ClassDesc classDesc = new ClassDesc();
//        classDesc.set(classReader.getAccess(), classReader.getClassName());

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
                if(writing){
                    byteWriting(classfileBuffer, className);
                    writing = false;
                }
            }
        }


        return classfileBuffer;
    }


//    Must Filering Classed
    private boolean classnameFiltering(String className) {
        for (String filtering : filteringList) {
            if (className.toLowerCase().startsWith(filtering)) {
                return true;
            }
        }
        return false;
    }


//    className, error
    private boolean ignoreTarget(ClassLoader loader, String className, byte[] classfileBuffer)
    {
        return classfileBuffer == null || className == null || className.startsWith(NON_CONVERT_START_PACKAGE) //
        || className.startsWith("$") || className.contains("$")|| (loader != null && loader.getClass().getName().contains(START_WITH_AGENT));
    }

    private void byteWriting(byte[] bytes, String className) {



        String [] sp = className.split("/");
        String fileName =  sp[sp.length -1];
//        String pathName =INSTRUMENT_CLASS+fileName+".class";
        String pathName =INSTRUMENT_PATH_HOME+fileName+".class";

        File file = new File(pathName);
        if(file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File not create");
                throw new RuntimeException(e);
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
