package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : Mafia
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class Mafia extends User{
    Mafia(String name) {
        super(name);
        setSkill();
    }

    void setSkill() {
        this.skill = "시민을 죽일수 있다.";
    }
}
