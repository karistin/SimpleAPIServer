package lucas.base;

/**
 * packageName    : lucas.base
 * fileName       : ClassDesc
 * author         : lucas
 * date           : 2022-10-07
 * description    :
 * 어떤 ClassFileTransformer를 사용해야 되는지 결정하는 부분
 * JavaAgent가 시작하고 MainClassFileTransformer에서 사용한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-07        lucas       최초 생성
 */
public class ClassDesc {

    public int version;
    public int access;
    public String name;
    public String signature;
    public String superName;
    public String[] interfaces;
    public String annotation;
    public Class classBeingRedefined;
    public boolean isMapImpl;

    /**
     * Set.
     *
     * @param version    java version
     * @param access     access
     * @param name       class name
     * @param signature
     * @param superName  parent
     * @param interfaces interfaces
     */
    public void set(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.version = version;
        this.access = access;
        this.name = name;
        this.signature = signature;
        this.superName = superName;
        this.interfaces = interfaces;
    }
    public boolean checkSuperName(String superName)
    {
        return this.superName == superName ? true: false;
    }

    public boolean checkInterfaces(String interfaceName) {
        if (interfaces.length != 0) {
            for(String interfaceNames: interfaces)
                if(interfaceName.equals(interfaceName))
                    return true;
        }
        return false;
    }
}
