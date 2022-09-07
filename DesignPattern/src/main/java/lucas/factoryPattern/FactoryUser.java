package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : FactoryUser
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
class FactoryUser extends Factory {

    @Override
    public User createUser(String name, String job) {
        switch (job) {
            case "마피아":
                return new Mafia(name);
            case "경찰":
                return new Police(name);
            default:
                return new Citizen(name);
        }
    }
}
