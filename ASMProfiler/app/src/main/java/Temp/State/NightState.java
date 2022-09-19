package Temp.State;

/**
 * packageName    : Application
 * fileName       : NightState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class NightState implements State{
    private static NightState singleton = new NightState();
    private NightState() {
    }

    public static State getInstance(){
        return singleton;
    }
    @Override
    public void print() {
        System.out.println("This is Night");
    }
}
