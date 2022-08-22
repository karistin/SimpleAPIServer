package Temp.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : Temp.Filter
 * fileName       : CriteriaSingle
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class CriteriaSingle implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> singlePersons = new ArrayList<>();

        for (Person person : persons) {
            if (person.getMaritalStatus().equalsIgnoreCase("SINGLE")) {
                singlePersons.add(person);
            }
        }

        return singlePersons;
    }
}
