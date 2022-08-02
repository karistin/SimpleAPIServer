package agent;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.logging.Logger;

public class MyJavaAgent {

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("[Agent] In premain method");
        MyTransformer myTransformer = new MyTransformer();
        inst.addTransformer(myTransformer);
    }



    public static void agentmain(final String agnetArgs,final Instrumentation inst){
        System.out.println("[Agent] In agentmain method");
    }
}
