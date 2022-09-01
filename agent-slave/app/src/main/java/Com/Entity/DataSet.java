package Com.Entity;

import Com.Type.AccessType;
import Com.Util.LogFormatter;

import java.io.Serializable;
import java.util.*;

/**
 * The type Data set.
 */
public class DataSet implements Serializable {


    private int access;

    private int Major_version;
    private  String class_name;
    private String Super_class;
    private String[] interfaces;
    private String source_name;
    private ArrayList<FieldValue> fieldValues = new ArrayList<>();

    private ArrayList<Methodvalue> methodvalues =new ArrayList<>();


    /**
     * Gets field values.
     *
     * @return the field values
     */
    public ArrayList<FieldValue> getFieldValues() {
        return fieldValues;
    }

    /**
     * Sets field values.
     *
     * @param fieldValue the field value
     */
    public void setFieldValues(FieldValue fieldValue) {
        this.fieldValues.add(fieldValue);
    }

    /**
     * Gets methodvalues.
     *
     * @return the methodvalues
     */
    public ArrayList<Methodvalue> getMethodvalues() {
        return methodvalues;
    }

    /**
     * Sets methodvalues.
     *
     * @param methodvalue the methodvalue
     */
    public void setMethodvalues(Methodvalue methodvalue) {
        this.methodvalues.add(methodvalue);
    }

    /**
     * Gets major version.
     *
     * @return the major version
     */
    public int getMajor_version() {
        return Major_version;
    }

    /**
     * Sets major version.
     *
     * @param major_version the major version
     */
    public void setMajor_version(int major_version) {
        Major_version = major_version;
    }

    /**
     * Gets class name.
     *
     * @return the class name
     */
    public String getClass_name() {
        return class_name;
    }

    /**
     * Sets class name.
     *
     * @param class_name the class name
     */
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    /**
     * Gets super class.
     *
     * @return the super class
     */
    public String getSuper_class() {
        return Super_class;
    }

    /**
     * Sets super class.
     *
     * @param super_class the super class
     */
    public void setSuper_class(String super_class) {
        Super_class = super_class;
    }

    /**
     * Gets source name.
     *
     * @return the source name
     */
    public String getSource_name() {
        return source_name;
    }

    /**
     * Sets source name.
     *
     * @param source_name the source name
     */
    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    /**
     * Get interfaces string [ ].
     *
     * @return the string [ ]
     */
    public String[] getInterfaces() {
        return interfaces;
    }


    /**
     * Sets interfaces.
     *
     * @param interfaces the interfaces
     */
    public void setInterfaces(String[] interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * Gets access.
     *
     * @return the access
     */
    public int getAccess() {
        return access;
    }

    /**
     * Sets access.
     *
     * @param access the access
     */
    public void setAccess(int access) {
        this.access = access;
    }

    /**
     * Print dataset.
     */
    public void printDataset(){
        System.out.println("************************\r\n");
        System.out.println("Access        : "+ AccessType.getaccessFlag(access));
        System.out.println("Class name    : " + class_name);
        System.out.println("Source name   : "+ LogFormatter.ANSI_RED+ source_name+LogFormatter.ANSI_WHITE);
        System.out.println("Major Version : " + Major_version);
        System.out.println("Super class   : " + Super_class);

        if(interfaces.length !=0)
        {
            System.out.println("Interface");
            for(String inter : interfaces){
                System.out.println("  "+ inter);
            }
        }

        if(fieldValues.size() !=0)
        {
            System.out.println("Field_value");
            System.out.format("%30s %25s \r\n","access", "name");
            for(FieldValue fieldValue:fieldValues){
                fieldValue.printset();
            }
        }
        if(methodvalues.size() != 0)
        {
            System.out.println("\r\nMethod_value");
            System.out.format("%30s %25s \r\n","access", "name");
            for(Methodvalue methodvalue:methodvalues){
                methodvalue.printset();
            }
        }

    }


}
