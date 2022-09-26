package lucas.base;

import lucas.base.log.FileLogger;

import java.lang.instrument.Instrumentation;
import java.util.logging.Logger;

/**
 * packageName    : lucas.base
 * fileName       : JavaAgent
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class JavaAgent {
    static Logger logger = Logger.getLogger("Agent");
    private static final Object LOCK = new Object();
    private static Instrumentation instrumentation = null;

    public JavaAgent(){}

    public static void premain(String options , Instrumentation instrumentation) {
//        version checking
        JavaAgent.instrumentation = instrumentation;
        start(options, instrumentation);
    }

    private static void start(String options, Instrumentation instrumentation) {
        synchronized (LOCK){
            if (JavaAgent.instrumentation != null) {
                logger.info("Agent already start");
            }

            JavaAgent.instrumentation.addTransformer(new MainClassFileTransformer(instrumentation));
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JavaAgent.logger = logger;
    }

    public static Instrumentation getInstrumentation() {
        return instrumentation;
    }

    public static void setInstrumentation(Instrumentation instrumentation) {
        JavaAgent.instrumentation = instrumentation;
    }
}
