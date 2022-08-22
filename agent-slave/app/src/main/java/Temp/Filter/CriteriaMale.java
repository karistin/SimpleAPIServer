package Temp.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : Temp.Filter
 * fileName       : CriteriaMale
 * author         : lucas
 * date           : 2022-08-22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-08-22        lucas       최초 생성
 */
public class CriteriaMale implements Criteria{
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
//        equalsIgnoreCase : 대소문자 구분 x
        for(Person person: persons){
            if (person.getGender().equalsIgnoreCase("Male")) {
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
