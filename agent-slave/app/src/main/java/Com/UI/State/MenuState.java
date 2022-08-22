package Com.UI.State;

import Com.UI.ClassState;
import Com.UI.Context;

import java.util.Scanner;

/**
 * packageName    : Com.UI
 * fileName       : MenuState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class MenuState implements State{
    private static MenuState singleton = new MenuState();
    private MenuState(){
    }
    public static State getInstance(){
        return singleton;
    }

    @Override
    public void showScreen(Context context) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("AgentSlve Menu");
        System.out.println("1. MethodProfiling");
        System.out.println("2. SearchClssinfo");
        System.out.println("3. EventLog");
        System.out.println("4. Searching");
        System.out.println("5. JVM Summary");
        System.out.print("6. Exit\r\n=>");
        String answer = scanner.nextLine();

        switch (answer){
            case "1":
                context.changeState(MethodState.getInstance());
                break;
            case "2":
                context.changeState(ClassState.getInstance());
                break;
            case "3":
                context.changeState(EventState.getInstance());
                break;
            case "4":
                context.changeState(MethodState.getInstance());
                break;
            case "5":
                context.changeState(SummaryState.getInstance());
                break;
            case "6":
                System.exit(0);
                break;
            default:
        }
    }
}
