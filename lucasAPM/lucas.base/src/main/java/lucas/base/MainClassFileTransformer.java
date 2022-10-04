package lucas.base;



import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.List;

import static lucas.base.Constants.START_WITH_AGENT;
import static lucas.base.ProductConstants.NON_CONVERT_START_PACKAGE;
import static lucas.base.ProductConstants.START_PACKAGE;

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

    public MainClassFileTransformer(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException
    {

//        class filering
        if (ignoreTarget(loader, className, classfileBuffer)) {
            return classfileBuffer;
        }

        long now = System.currentTimeMillis();




//        if (Hooking.INSTANCE.getTransformer() != null)
//        {
//            byte[] buf = Hooking.INSTANCE.getTransformer().transform(loader, className, classBeingRedefined, classfileBuffer);
//
//            if (buf != null)
//            {
//                return buf;
//            }
//        }

        return classfileBuffer;
    }

    private boolean ignoreTarget(ClassLoader loader, String className, byte[] classfileBuffer)
    {
        return classfileBuffer == null || className == null || className.startsWith(NON_CONVERT_START_PACKAGE) //
        || className.startsWith("$") || (loader != null && loader.getClass().getName().contains(START_WITH_AGENT));
    }
}
