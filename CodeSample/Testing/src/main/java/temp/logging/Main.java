package temp.logging;

import java.io.IOException;
import java.util.logging.*;

/**
 * packageName    : temp.log
 * fileName       : Main
 * author         : lucas
 * date           : 2022-07-20
 * description    : log study http://www.gisdeveloper.co.kr/?p=5174  https://sdesigner.tistory.com/100
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
public class Main {
    private final static Logger LOG = Logger.getGlobal();

    public static void main(String[] args) throws IOException {
//        LOG.setLevel(Level.INFO);
//
//        LOG.severe("severe Log");
//        LOG.warning("warning log");
//        LOG.info("ingof Log");

        Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        if (handlers[0] instanceof ConsoleHandler) {
            rootLogger.removeHandler(handlers[0]);

        }

        //LOG in console
//        LOG.setLevel(Level.INFO);
//
//
//        Handler handler = new ConsoleHandler();
//        CustomLogFormatter formatter = new CustomLogFormatter();
//        handler.setFormatter(formatter);
//        LOG.addHandler(handler);
//
//        LOG.severe("severe Log");
//        LOG.warning("warning Log");
//        LOG.info("info Log");

        LOG.setLevel(Level.INFO);

        Handler handler = new FileHandler("message.log", true);

        CustomLogFormatter formatter = new CustomLogFormatter();
        handler.setFormatter(formatter);
        LOG.addHandler(handler);

        LOG.severe("severe Log");
        LOG.warning("warning Log");
        LOG.info("info Log");
    }
}
