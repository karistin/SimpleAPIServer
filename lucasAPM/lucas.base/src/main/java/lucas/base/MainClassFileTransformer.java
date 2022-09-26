package lucas.base;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

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
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (Hooking.INSTANCE.getTransformer() != null)
        {
            byte[] buf = Hooking.INSTANCE.getTransformer().transform(loader, className, classBeingRedefined, classfileBuffer);

            if (buf != null)
            {
                return buf;
            }
        }
        return classfileBuffer;
    }
}
