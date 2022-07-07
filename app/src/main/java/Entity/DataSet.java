package Entity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DataSet {


    private int Major_version;
    private  String class_name;
    private String Super_class;
    private String[] interfaces;
    private String source_name;

    private Map<String, List> Field_value = new LinkedHashMap<String, List>();

    private Map<String, String> Method_value = new LinkedHashMap<String, String>();


    public int getMajor_version() {
        return Major_version;
    }

    public void setMajor_version(int major_version) {
        Major_version = major_version;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getSuper_class() {
        return Super_class;
    }

    public void setSuper_class(String super_class) {
        Super_class = super_class;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public Map<String, List> getField_value() {
        return Field_value;
    }

    public void appendField_value(String key, List value) {
        Field_value.put(key, value);
    }

    public Map<String, String > getMethod_value() {
        return Method_value;
    }

    public void appendMethod_value(String key, String value){
        Method_value.put(key, value);
    }

    public String[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }

    public void print_dataset(){

        System.out.println("class name: " + class_name);
        System.out.println("Source name: "+ source_name);
        System.out.println("Major Version: " + Major_version);
        System.out.println("Super class: " + Super_class);


        System.out.println("interface: ");
        for(String inter : interfaces){
            System.out.println("  "+ inter);
        }

        System.out.println("Field_value: ");
        for(Map.Entry<String, List> entry : Field_value.entrySet()){
            System.out.println("  value:"+ entry.getKey() +" " + entry.getValue());
        }

        System.out.println("Method_value: ");
        for(Map.Entry<String, String> entry : Method_value.entrySet()){
            System.out.println("   method name:"+ entry.getKey() +" " + entry.getValue());
        }

    }
}
