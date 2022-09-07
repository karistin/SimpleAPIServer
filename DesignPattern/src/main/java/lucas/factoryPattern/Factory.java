package lucas.factoryPattern;

/**
 * packageName    : lucas.factoryPattern
 * fileName       : Factory
 * author         : lucas
 * date           : 2022-09-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-07        lucas       최초 생성
 */
abstract class Factory {
    public final User create(String name, String job) {
        return this.createUser(name, job);
    }

    abstract public User createUser(String name, String job);
}
