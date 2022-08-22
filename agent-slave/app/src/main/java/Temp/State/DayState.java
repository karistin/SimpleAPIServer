package Temp.State;

/**
 * packageName    : Application
 * fileName       : DayState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class DayState implements State{
    private static DayState singleton = new DayState();
    private DayState() {
    }

    public static State getInstance(){
        return singleton;
    }

    @Override
    public void print() {
        System.out.println("This is Day");

    }

}
