package Application;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.ansi.UnixTerminal;
import com.googlecode.lanterna.terminal.virtual.DefaultVirtualTerminal;

import java.io.IOException;

/**
 * packageName    : Application
 * fileName       : Lanterna
 * author         : lucas
 * date           : 2022-08-03
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-03        lucas       최초 생성
 */
public class Lanterna {
    public static void main(String[] args) throws IOException {
//        Terminal terminal = null;
        Screen screen = new DefaultTerminalFactory().createScreen();
        screen.startScreen();

        screen.setCharacter(10, 5, new TextCharacter('!', TextColor.ANSI.RED, TextColor.ANSI.GREEN));
        TextGraphics textGraphics = screen.newTextGraphics();

        textGraphics.setForegroundColor(TextColor.ANSI.RED);
        textGraphics.setBackgroundColor(TextColor.ANSI.GREEN);
        textGraphics.putString(10, 5, "Hello Lanterna!");

        screen.refresh();
// use GUI here until the GUI wants to exit

    }
}
