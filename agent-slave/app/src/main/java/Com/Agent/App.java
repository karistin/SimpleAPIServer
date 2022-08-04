/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Com.Agent;

import Com.Agent.Print.PrintThread;
import Com.Repository.DataSetRepo;
import Com.Repository.DataSetRepoMemory;
import Com.Util.LogFormatter;

import java.io.IOException;
import java.lang.instrument.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.*;


public class App {

    public static DataSetRepo taskRepository = new DataSetRepoMemory();
    public final static Logger LOG = Logger.getGlobal();
    static Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println("Uncaught exception "+ e);
            System.exit(0);
        }
    };

//     java -javaagent:app/build/libs/app.jar -Dspring.main.banner-mode=off -Dlogging.pattern.console= -jar TestCase.jar
//      java -javaagent:app/build/libs/app.jar -jar app/build/libs/app.jar
    public static void premain(String args, Instrumentation instrumentation) throws IOException {


        LOG.setLevel(Level.INFO);
        LOG.setUseParentHandlers(false);

        String logfile = System.getProperty("user.dir")+"/agentLog.txt";
        Files.delete(Paths.get(logfile));
        Handler textHandler = new FileHandler(logfile,true);
//        ConsoleHandler handler = new ConsoleHandler();

        Formatter formatter = new LogFormatter();
        textHandler.setFormatter(formatter);
        LOG.addHandler(textHandler);

        PrintThread printThread = new PrintThread();
        printThread.setDaemon(true);
        printThread.setUncaughtExceptionHandler(exceptionHandler);
        printThread.start();
//        LOG.info(System.getProperty("sun.java.command"));
        LOG.info("[Premain Agnet Start]");
        instrumentation.addTransformer(new MyClassFileTransformer());
    }

}

