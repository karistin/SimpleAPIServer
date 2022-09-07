package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : FactoryMethodPattern
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class FactoryMethodPattern {

    /*
    * 코드에 새로운 직업 추가시 쉅게 대처할수 있다.
    *  유지 보수의 간결성 추가
    *  Factory의 강력한 결합을 제거한다.
    *  하위 클래스에 위임하는 기술
    *  new 객채 선언은 interface를 두고 하자!!!
    *  https://yeah.tistory.com/m/4
    * */
    public static void main(String[] args) {
        Factory factory = new FactoryUser();
        User user1 = factory.create("성민", "마피아");
        User user2 = factory.create("영성", "경찰");
        User user3 = factory.create("승주", "시민");
        User user4 = factory.create("수지", "시민");

        System.out.println("이름 :  " + user1.getName() + ",  스킬 : " + user1.getSkill());
        System.out.println("이름 :  " + user2.getName() + ",  스킬 : " + user2.getSkill());
        System.out.println("이름 :  " + user3.getName() + ",  스킬 : " + user3.getSkill());
        System.out.println("이름 :  " + user4.getName() + ",  스킬 : " + user4.getSkill());
    }
}
