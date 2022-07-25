package Repository;

import Entity.DataSet;

import java.util.List;
import java.util.Optional;

public interface DataSetRepo {
    DataSet save(DataSet dataset);
    Optional <DataSet> find_class_data(String class_name);
    int get_size();

    List<DataSet> find_all();

}
