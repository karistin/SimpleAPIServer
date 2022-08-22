package Com.UI.State;

import Com.UI.Context;

/**
 *packageName    : Com.UI
 * fileName       : MethodState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */public class MethodState implements State{
    private static MethodState singleton = new MethodState();
    private MethodState(){
    }
    public static State getInstance(){
        return singleton;
    }
    @Override
    public void showScreen(Context context) {



    }

}
