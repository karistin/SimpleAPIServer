package temp.generics;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * packageName    : temp.generics
 * fileName       : Main
 * author         : lucas
 * date           : 2022-07-20
 * description    : Generic에 관한 공부
 * https://st-lab.tistory.com/153
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
class ClassName<E>{

    private E element;

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }
}

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> list1 = new ArrayList<>();
        LinkedList<Double> list2 = new LinkedList<>();
//        형식을 명시해야 된다.
        Box<String, Integer> box = new Box<>();
        /*
         * <>  참조 타임만 가능하다
         * 사용자가 정의한 클래스 타입 포함
         * */

        ClassName<String> str = new ClassName<>();
        ClassName<Integer> num = new ClassName<>();

        str.setElement("10");
        num.setElement(10);

        System.out.println("str data : " + str.getElement());
        // 반환된 변수의 타입 출력
        System.out.println("str E Type : " + str.getElement().getClass().getName());

        System.out.println();
        System.out.println("num data : " + num.getElement());
        // 반환된 변수의 타입 출력
        System.out.println("num E Type : " + num.getElement().getClass().getName());
    }
}
