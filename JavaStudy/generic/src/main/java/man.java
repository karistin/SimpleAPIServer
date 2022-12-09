/**
 * packageName    : com.lucas.generic
 * fileName       : man
 * author         : lucas
 * date           : 2022-11-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-16        lucas       최초 생성
 */

public class man <T>{

    private T name;
    private T bloodtype;

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public T getBloodtype() {
        return bloodtype;
    }

    public void setBloodtype(T bloodtype) {
        this.bloodtype = bloodtype;
    }
}
