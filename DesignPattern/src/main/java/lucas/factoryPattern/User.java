package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : User
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
abstract class User {
    String name;
    String skill;

    User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSkill() {
        return skill;
    }
}
