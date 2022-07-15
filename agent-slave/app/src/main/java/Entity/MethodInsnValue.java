package Entity;

public class MethodInsnValue {
    private String opcode;
    private String owner;
    private String name;

    private String descriptor;

    private boolean isInterface;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpcode() {
        return opcode;
    }

    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public boolean isInterface() {
        return isInterface;
    }

    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    public void printset() {
        System.out.format("\t\t%30s %25s %40s %50s %30s\r\n", opcode, owner, name, descriptor,isInterface);
    }
}
