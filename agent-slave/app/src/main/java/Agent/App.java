/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Agent;

import Repository.DataSetRepo;
import Repository.DataSetRepoMemory;
import Util.LogFormatter;

import java.lang.instrument.*;
import java.util.logging.*;


public class App {

    public static DataSetRepo taskRepository = new DataSetRepoMemory();
    public final static Logger LOG = Logger.getGlobal();

//     java -javaagent:app/build/libs/app.jar -Dspring.main.banner-mode=off -Dlogging.pattern.console= -jar TestCase.jar

    public static void premain(String args, Instrumentation instrumentation) {

        LOG.setLevel(Level.INFO);
        LOG.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        Formatter formatter = new LogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);

        PrintThread printThread = new PrintThread();
        printThread.start();
//        LOG.info(System.getProperty("sun.java.command"));
        LOG.info("[Premain Agnet Start]");
        instrumentation.addTransformer(new MyClassFileTransformer());
    }

}

