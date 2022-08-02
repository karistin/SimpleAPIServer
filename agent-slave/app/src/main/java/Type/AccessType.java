package Type;

import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccessType {
    private static final HashMap<Integer, String> accessFlag = new HashMap<>() {
        {

        /*  // Access flags values, defined in
          // - https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.1-200-E.1
          // - https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.5-200-A.1
          // - https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.6-200-A.1
          // - https://docs.oracle.com/javase/specs/jvms/se9/html/jvms-4.html#jvms-4.7.25
          */
        put(Opcodes.ACC_PUBLIC, "PUBLIC"); // class, field, method
        put(Opcodes.ACC_PRIVATE, "PRIVATE"); // class, field, method
        put(Opcodes.ACC_PROTECTED, "PROTECTED"); // class, field, method
        put(Opcodes.ACC_STATIC, "STATIC"); //  field, method
        put(Opcodes.ACC_FINAL, "FINAL"); // class, field, method, parameter
        put(Opcodes.ACC_SUPER, "SUPER"); // class
        put(Opcodes.ACC_SYNCHRONIZED, "SYNCHRONIZED");
        put(Opcodes.ACC_OPEN, "PUBLIC");
        put(Opcodes.ACC_TRANSITIVE, "TRANSITIVE");
        put(Opcodes.ACC_VOLATILE, "VOLATILE");
        put(Opcodes.ACC_BRIDGE, "BRIDGE");
        put(Opcodes.ACC_STATIC_PHASE, "STATIC_PHASE");
        put(Opcodes.ACC_VARARGS, "VARARGS");
        put(Opcodes.ACC_TRANSIENT, "TRANSIENT");
        put(Opcodes.ACC_NATIVE, "NATIVE");
        put(Opcodes.ACC_INTERFACE, "INTERFACE");
        put(Opcodes.ACC_ABSTRACT, "ABSTRACT");
        put(Opcodes.ACC_STRICT, "STRICT");
        put(Opcodes.ACC_SYNTHETIC, "SYNTHETIC");
        put(Opcodes.ACC_ANNOTATION, "ANNOTATION");
        put(Opcodes.ACC_ENUM, "ENUM");
        put(Opcodes.ACC_MANDATED, "MANDATED");
        put(Opcodes.ACC_MODULE, "MODULE");

        // ASM specific access flags.
        // WARNING: the 16 least significant bits must NOT be used, to avoid conflicts with standard
        // access flags, and also to make sure that these flags are automatically filtered out when
        // written in class files (because access flags are stored using 16 bits only).

        //put(Opcodes.ACC_RECORD, "RECORD");
        //put(Opcodes.ACC_DEPRECATED, "DEPRECATED");

        }
    };
    public static List<String> getaccessFlag(int flagInt) {
 
        List<String> flags = new ArrayList<String>();
        for (int ACC:accessFlag.keySet())
        {

            if ((flagInt & ACC) == ACC){
                flags.add(accessFlag.get(ACC));
            }
        }
        if (flags.size()==0){
            flags.add("VOID");
        }
        return flags;
    }

}
