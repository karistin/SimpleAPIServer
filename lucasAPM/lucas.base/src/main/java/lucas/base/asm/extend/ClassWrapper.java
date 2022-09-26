package lucas.base.asm.extend;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * packageName    : lucas.base.asm
 * fileName       : ClassWrapper
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class ClassWrapper extends ClassNode {
    public List<MethodWrapper> methodWrappers;

    private String packageName;
    private String className;
    private String superClassName;

    public ClassWrapper()
    {
        this(Opcodes.ASM9);
    }

    public ClassWrapper(int api) {
        super(api);
        // Vendor Wrapper 에서 ConcurrentModificationException 발생함.
        methodWrappers = new CopyOnWriteArrayList<MethodWrapper>();
    }

    public boolean isInterface()
    {
        return ((access & Opcodes.ACC_INTERFACE) != 0);
    }

    // class name 은 pattern 값이 들어 갈 수 있다.
    public String getClassName()
    {
        if (className == null)
        {
            className = AsmUtility.convertForAgent(name);
        }

        return className;
    }

    public String getSuperClassName()
    {
        if (superName != null)
        {
            superClassName = AsmUtility.convertForAgent(superName);
        }

        return superClassName;
    }

    public String[] getAllSuperClassNames()
    {
        return AsmUtility.getAllSuperClassesNames(this);
    }

    public String[] getInterfaceNames()
    {
        return AsmUtility.getInterfaceNames(this);
    }

    public String[] getAllInterfaceNames()
    {
        return AsmUtility.getAllInterfaceNames(this);
    }

    public String getPackageName()
    {
        if (packageName == null)
        {
            int index = getClassName().lastIndexOf('.');
            if (index > -1)
            {
                packageName = getClassName().substring(0, index);
            }
        }

        return packageName;
    }

    public boolean contains(String name, String description)
    {
        for (MethodNode method : methods)
        {
            if (method.name.equals(name) && method.desc.equals(description))
            {
                return true;
            }
        }

        return false;
    }

}
