package Repository;

import Entity.DataEntity;

import java.util.*;

/**
 * packageName    : Repository
 * fileName       : MemoryRepositoy
 * author         : lucas
 * date           : 2022-07-25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-25        lucas       최초 생성
 */
public class MemoryRepositoy implements Repository{

    private static Map<String, DataEntity> store = new LinkedHashMap<>();


    @Override
    public DataEntity save(DataEntity dataset) {
        store.put(dataset.getClassName(), dataset);
        return null;
    }

    @Override
    public Optional<DataEntity> find_class_data(String class_name) {
        return Optional.ofNullable(store.get(class_name));
    }

    @Override
    public int get_size() {
        return store.size();
    }

    @Override
    public List<DataEntity> find_all() {
        return new ArrayList<>(store.values());
    }
}
