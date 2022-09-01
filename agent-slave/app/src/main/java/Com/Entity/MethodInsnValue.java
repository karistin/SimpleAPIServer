package Com.Entity;

/**
 * The type Method insn value.
 */
public class MethodInsnValue {
    private String opcode;
    private String owner;
    private String name;

    private String descriptor;

    private boolean isInterface;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets opcode.
     *
     * @return the opcode
     */
    public String getOpcode() {
        return opcode;
    }

    /**
     * Sets opcode.
     *
     * @param opcode the opcode
     */
    public void setOpcode(String opcode) {
        this.opcode = opcode;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Gets descriptor.
     *
     * @return the descriptor
     */
    public String getDescriptor() {
        return descriptor;
    }

    /**
     * Sets descriptor.
     *
     * @param descriptor the descriptor
     */
    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Is interface boolean.
     *
     * @return the boolean
     */
    public boolean isInterface() {
        return isInterface;
    }

    /**
     * Sets interface.
     *
     * @param anInterface the an interface
     */
    public void setInterface(boolean anInterface) {
        isInterface = anInterface;
    }

    /**
     * Printset.
     */
    public void printset() {
        System.out.format("\t\t%30s %25s %30s \r\n", opcode, owner, name);
    }
}
