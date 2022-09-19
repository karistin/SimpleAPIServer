package Temp.State;

/**
 * packageName    : Application
 * fileName       : SafeFrame
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 * UI 구현체
 */
public class SafeFrame implements Context, State{

//    init Day
    private State state  = DayState.getInstance();

    @Override
    public void changeState(State state) {
        System.out.println(this.state+ " 에서 "+state +" 로 상태가 변경했습니다.");
        this.state = state;
    }

    @Override
    public void print() {
        state.print();
    }
}
