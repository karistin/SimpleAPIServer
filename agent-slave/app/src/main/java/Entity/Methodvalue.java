package Entity;

import Type.AccessType;
import Util.LogFormatter;

import java.util.ArrayList;

public class Methodvalue {
    private int access;
    private String name;
    private String descriptor;

    private Long methodCount = 0L;

    private ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<MethodInsnValue>();

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public ArrayList<MethodInsnValue> getMethodInsnValues() {
        return methodInsnValues;
    }

    public void setMethodInsnValues(ArrayList<MethodInsnValue> methodInsnValues) {
        this.methodInsnValues = methodInsnValues;
    }


    public void printset(){
        if (methodInsnValues.size() !=0)
        {
            System.out.format("%30s "+ LogFormatter.ANSI_RED +"%25s"+LogFormatter.ANSI_WHITE+"  %80s \r\n", AccessType.getaccessFlag(access), name,descriptor);
            System.out.format("\t\t%30s %25s %40s %50s %30s\r\n","opcode", "owner","name","descriptor","isInterface");
            for(MethodInsnValue methodInsnValue: methodInsnValues){
                methodInsnValue.printset();
            }

        }
    }

}
