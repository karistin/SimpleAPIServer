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


    public int access;
    public String name;
    public String signature;
    public String superName;
    public String[] interfaces;
    public String annotation;



    public void set( int access, String name, String superName, String[] interfaces) {
        this.access = access;
        this.name = name;
        this.superName = superName;
        this.interfaces = interfaces;
    }
    public boolean checkSuperName(String superName)
    {
        return this.superName.equals(superName);
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
