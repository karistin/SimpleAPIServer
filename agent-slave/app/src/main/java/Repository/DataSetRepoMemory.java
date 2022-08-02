package Repository;

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
    public int getSize(){return store.size();}

    @Override
    public Optional<DataSet> getClass(String class_name) {
        return Optional.ofNullable(store.get(class_name));
    }

    @Override
    public List<DataSet> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void print() {
        for (DataSet sto : store.values()) {
            sto.printDataset();
        }
    }

    @Override
    public Set<String> classList() {
        return  store.keySet();
    }

}
