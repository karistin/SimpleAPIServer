package lucas.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

/**
 * packageName    : lucas.exporter.lucas.agent
 * fileName       : permain
 * author         : lucas
 * date           : 2022-09-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-16        lucas       최초 생성
 */
public class permain {

    public static void premain(String args, Instrumentation instrumentation) throws IOException{

        instrumentation.addTransformer(new TransforClass());
    }
}
