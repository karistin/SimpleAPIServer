package Com.Entity;

import Com.Type.AccessType;

import java.util.ArrayList;

public class Methodvalue {
    private int access;
    private String name;
    private String descriptor;
    private Long methodCount = 0L;
    private ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<>();

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



    public void printInsn() {
        System.out.format("\t\t%30s %25s %30s\r\n","opcode", "owner", "name");
        for(MethodInsnValue methodInsnValue: methodInsnValues){
            methodInsnValue.printset();
        }
    }

    public void printset(){
        if (methodInsnValues.size() !=0)
        {
            System.out.format("%30s "+"%25s \r\n", AccessType.getaccessFlag(access), name);
//            System.out.format("\t\t%30s %25s \r\n","opcode", "owner");
//            for(MethodInsnValue methodInsnValue: methodInsnValues){
//                methodInsnValue.printset();
//            }

        }
    }

}
