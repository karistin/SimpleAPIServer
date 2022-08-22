package Com.Entity;

import Com.Type.AccessType;
import Com.Util.MultiColumnPrinter;

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
        if (methodInsnValues.size()>0)
        {

            int col =3;
            int gap = 8;
            String[] row = new String[col];
            MultiColumnPrinter printInsn = new MultiColumnPrinter(3,8,"*",0,false) {
                @Override
                public void doPrint(String str) {
                    System.out.print(str);
                }

                @Override
                public void doPrintln(String str) {
                    System.out.println("");
                }
            };
            row[0] = "opcode";
            row[1] = "owner";
            row[2] = "name";
            printInsn.addTitle(row);

            for(MethodInsnValue methodInsnValue: methodInsnValues){
                row = new String[col];
                row[0] = methodInsnValue.getOpcode();
                row[1] = methodInsnValue.getOwner();
                row[2] = methodInsnValue.getName();
                printInsn.add(row);
            }
            printInsn.print();
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
