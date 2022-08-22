package Temp.State;

/**
 * packageName    : Application
 * fileName       : MidNightState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class MidNightState implements State{

    private static MidNightState singleton = new MidNightState();
    private MidNightState(){}
    public static State getInstance(){
        return  singleton;
    }

    @Override
    public void print() {
        System.out.println("MidNight");
    }

    public void NightTime(){
        System.out.println("Night Time !!!");
    }
}
