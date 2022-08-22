package Com.UI;

import Com.UI.State.State;

/**
 * packageName    : Com.UI
 * fileName       : Context
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public interface Context {
    public abstract void changeState(State state);

}
