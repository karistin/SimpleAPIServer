import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : com.lucas.generic
 * fileName       : mainGe
 * author         : lucas
 * date           : 2022-11-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-16        lucas       최초 생성
 */
public class mainGe {
    @Getter
    @AllArgsConstructor
    enum osTypeData{
        WINDOW( "Window"),
        LINUX("Linux"),
        UNIX("Unix"),
        OTHERS("Others");

        String msg;
    }
    public static void main(String[] args) {
        man<String> man1 = new man<>();
        man1.setName("King");
        man1.setBloodtype("A");
        //선언시 스트링 타입으로 선언하여 스트링 데이터를 입력하였다.
//
//        System.out.println(man1.getName());
//        System.out.println(man1.getBloodtype());
//        System.out.println(System.currentTimeMillis());
        List<osTypeData> osTypeDataList = new ArrayList<>();
        osTypeDataList.add(osTypeData.WINDOW);
        for (osTypeData osTypeData : osTypeDataList) {
            System.out.println(osTypeData.msg);
        }

    }
}
