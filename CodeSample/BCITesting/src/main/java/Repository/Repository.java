package Repository;

import Entity.DataEntity;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : Repository
 * fileName       : Repository
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public interface Repository {
    DataEntity save(DataEntity dataset);
    Optional<DataEntity> find_class_data(String class_name);
    int get_size();
    List<DataEntity> find_all();

}
