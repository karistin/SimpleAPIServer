package Entity;

import java.util.ArrayList;

public class Methodvalue {
    private int access;
    private String name;
    private String descriptor;

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
        System.out.format("%30s %25s %80s \r\n",Flag.getaccessFlag(access), name,descriptor);
        //if (methodInsnValues.size() !=0)
        //{
        //    System.out.format("\t\t%30s %25s %40s %50s %30s\r\n","opcode", "owner","name","descriptor","isInterface");
        //    for(MethodInsnValue methodInsnValue: methodInsnValues){
        //        methodInsnValue.printset();
        //    }
        //    System.out.println("\t\t\t\t============================================================================================================================================================================");
        //}
    }

}
