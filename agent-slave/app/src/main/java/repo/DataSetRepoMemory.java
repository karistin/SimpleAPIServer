package repo;

import Entity.DataSet;

import java.util.*;

public class DataSetRepoMemory implements DataSetRepo{

    private static Map<String, DataSet> store = new LinkedHashMap<>();


    @Override
    public DataSet save(DataSet dataset) {
        store.put(dataset.getClass_name(), dataset);
        return null;
    }

    @Override
    public int get_size(){return store.size();}

    @Override
    public Optional<DataSet> find_class_data(String class_name) {
        return Optional.ofNullable(store.get(class_name));
    }

    @Override
    public List<DataSet> find_all() {
        return new ArrayList<>(store.values());
    }
}
