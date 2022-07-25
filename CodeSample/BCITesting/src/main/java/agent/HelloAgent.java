package agent;

import Repository.MemoryRepositoy;

import java.lang.instrument.Instrumentation;
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
}
