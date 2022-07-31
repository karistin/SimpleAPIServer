package Agent;

import Entity.MethodInsnValue;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;

import static Type.InvokeType.getInvokeMap;


public class MyMethodAdapter extends AdviceAdapter implements Opcodes {

    private final String packageName;
    private final String className;
    private final String methodName;
    private final ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<MethodInsnValue>();

    public MyMethodAdapter(int api, MethodVisitor methodVisitor, int access, String name, String descriptor, String className, String packageName) {
        super(api, methodVisitor, access, name, descriptor);
        this.packageName = packageName;
        this.className = className;
        this.methodName = name;
    }

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<MethodInsnValue> getMethodInsnValues() {
        return methodInsnValues;
    }

    @Override
    protected void onMethodEnter() {

//      다른 클래스에 같은 메소드의 경우 존재
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(INVOKESTATIC, "Agent/MyBCIMethod","start","(Ljava/lang/String;)V",false);
        super.onMethodEnter();
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (!name.equals( "<init>") && !name.equals( "<clinit>")) {
            MethodInsnValue methodInsnValue = new MethodInsnValue();
            methodInsnValue.setOpcode(getInvokeMap(opcode));
            methodInsnValue.setOwner(owner);
            methodInsnValue.setName(name);
            methodInsnValue.setDescriptor(descriptor);
            methodInsnValue.setInterface(isInterface);
            methodInsnValues.add(methodInsnValue);
        }
        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface);
    }

    @Override
    public void visitInvokeDynamicInsn(String name, String descriptor, Handle bootstrapMethodHandle, Object... bootstrapMethodArguments) {
        MethodInsnValue methodInsnValue = new MethodInsnValue();
        methodInsnValue.setOpcode("INVOKEDYNAMIC");
        methodInsnValue.setOwner("Lamda");
        methodInsnValue.setName(name);
        methodInsnValue.setDescriptor(descriptor);
        methodInsnValue.setInterface(false);
        methodInsnValues.add(methodInsnValue);

        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    protected void onMethodExit(int opcode) {
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(INVOKESTATIC, "Agent/MyBCIMethod","end","(Ljava/lang/String;)V",false);
        super.onMethodExit(opcode);
    }

}
