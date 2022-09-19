package Com.UI.State;

import Com.UI.Context;
import Com.UI.PrintThread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * packageName    : Com.UI
 * fileName       : EventState
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class EventState implements State {
    private static EventState singleton = new EventState();
    private EventState() {
    }
    public static State getInstance(){return singleton; }
    @Override
    public void showScreen(Context context) {
        Scanner scanner = new Scanner(System.in);
        try(BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/agentLog.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
//                printindexing(line, index);
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        System.out.println("\r\nLog List(exit go to menu)");
        String className = scanner.nextLine();
//        System.out.println("Count : "+indexCount);
//        indexCount = 0;
//
//
//        if (className.contains("grep"))
//        {
//            index = List.of((className.split(" ")));
//            index = index.subList(1, index.size());
//        }
        if(className.equals("exit"))
        {
            context.changeState(MenuState.getInstance());
        }
    }
}
