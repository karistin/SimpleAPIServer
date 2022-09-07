package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : Citizen
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class Citizen extends User{
    Citizen(String name) {
        super(name);
        setSkill();
    }

    void setSkill() {
        this.skill = "투표를 행사할 수 있다.";
    }
}
