package Temp.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : Temp.Filter
 * fileName       : CriteriaFemale
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class CriteriaFemale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> femalePersons = new ArrayList<>();

        for(Person person: persons){
            if (person.getGender().equalsIgnoreCase("FEAMELE")) {
                femalePersons.add(person);
            }
        }

        return femalePersons;
    }
}
