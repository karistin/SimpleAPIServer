package Temp.State;

/**
 * packageName    : Temp
 * fileName       : Main
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class Main {
    public static void main(String[] args) {
        SafeFrame frame = new SafeFrame();
        frame.print();
        frame.changeState(NightState.getInstance());
        frame.print();
    }
}
