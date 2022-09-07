package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : Police
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
public class Police extends User{
    Police(String name) {
        super(name);
        setSkill();
    }

    void setSkill() {
        this.skill = "마피아를 찾을수 있다.";
    }
}
