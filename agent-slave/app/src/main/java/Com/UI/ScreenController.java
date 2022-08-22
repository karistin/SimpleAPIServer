package Com.UI;

import Com.UI.State.MenuState;
import Com.UI.State.State;

/**
 * packageName    : Com.UI
 * fileName       : ScreenController
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public  class ScreenController  implements Context , State {
    private State state = MenuState.getInstance();
    @Override
    public void changeState(State state) {
        this.state = state;
    }

    @Override
    public void showScreen(Context context) {
        state.showScreen(this);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
