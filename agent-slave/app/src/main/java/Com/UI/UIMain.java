package Com.UI;

import Com.UI.State.MenuState;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * packageName    : Com.UI
 * fileName       : UIMain
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class UIMain extends Thread{

    ScreenController sc = new ScreenController();
    SignalHandler signalHandler = new SignalHandler() {
        @Override
        public void handle(Signal sig) {
            sc.changeState(MenuState.getInstance());
        }
    };

    @Override
    public void run() {
        Signal.handle(new Signal("INT"),signalHandler);
        while(true){
            sc.showScreen(sc);
        }
    }
}
