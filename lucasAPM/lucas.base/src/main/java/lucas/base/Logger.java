package lucas.base;

import lucas.base.util.DateUtil;
import lucas.base.util.IClose;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * packageName    : lucas.base
 * fileName       : Logger
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class Logger {

    public static class FileLog implements IClose {
        private PrintWriter out;

        public FileLog(String filename) {
            try {
                this.out = new PrintWriter(new FileWriter(filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void println(String message) {
            if(this.out == null)
                return;
//            this.out.println(DateUtil.);
        }
        @Override
        public void close() {

        }
    }
}
