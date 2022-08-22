package Com.UI;

import Com.UI.State.State;

/**
 * packageName    : Com.UI
 * fileName       : ClassState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class ClassState implements State {
    private static ClassState singleton = new ClassState();
    private ClassState() {
    }

    public static State getInstance(){
        return singleton;
    }
    @Override
    public void showScreen(Context context) {

    }
}
