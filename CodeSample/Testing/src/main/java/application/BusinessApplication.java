package application;

/**
 * packageName    : application
 * fileName       : BusinessApplication
 * author         : lucas
 * date           : 2022-07-20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
public class BusinessApplication {

    public static void businessMethod(int count) throws Exception{
        int sum = 0;
        for(int i=0; i<count; i++){
            sum += i;
            //테스트를 위해 200ms를 쉬는 로직을 강제로 입력했습니다.
            Thread.sleep(200);
        }
        System.out.println("======================== businessMethod");
        System.out.println("[result] : "+sum);
    }
}

