package Entity;

public class FieldValue {
    private int access;
    private String name;
    private String descriptor;
    private String signature;
    private Object value;


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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void printset(){
        System.out.format("%30s %25s %40s %30s\r\n",Flag.getaccessFlag(access), name , descriptor, value);
    }
}
