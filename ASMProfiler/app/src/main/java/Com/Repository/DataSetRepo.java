package Com.Repository;

import Com.Entity.DataSet;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DataSetRepo {
    DataSet save(DataSet dataset);


    int getSize();

    Optional<DataSet> getClass(String class_name);

    List<DataSet> findAll();

    void print();

    Set<String> classList();
}
