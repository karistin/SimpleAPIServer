package Temp.Filter;

import java.util.List;

/**
 * packageName    : Temp.Filter
 * fileName       : Criteria
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public interface Criteria {
    public List<Person> meetCriteria(List<Person> persons);
}
