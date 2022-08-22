package Com.UI.State;

import Com.UI.Context;

/**
 * packageName    : Com.UI
 * fileName       : SearchingState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class SearchingState implements State{
    private static SearchingState singleton = new SearchingState();
    private SearchingState() {
    }
    public static State getInstance(){return singleton;}
    @Override
    public void showScreen(Context context) {

    }
}
