package Temp.State;

/**
 * packageName    : Application
 * fileName       : Context
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 *
 *  모든 상태 관리
 */
public interface Context {
    public abstract void changeState(State state);
}
