package Com.Agent;

import Com.Entity.MethodInsnValue;
import Com.Type.InvokeType;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;


public class MyMethodAdapter extends AdviceAdapter implements Opcodes {

    private final String className;
    private final String methodName;
    private final ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<MethodInsnValue>();

    public MyMethodAdapter(int api, MethodVisitor methodVisitor, int access, String methodName, String descriptor, String className) {
        super(api, methodVisitor, access, methodName, descriptor);
        this.className = className;
        this.methodName = methodName;
    }

//    NAWARRAY 정수형 오퍼랜드가 1개인것
    @Override
    public void visitIntInsn(int opcode, int operand) {
        if(opcode != Opcodes.NEWARRAY)
        {
            super.visitIntInsn(opcode, operand);
            return;
        }
        final int typeSize;

        switch(operand){
            case Opcodes.T_BOOLEAN:
            case Opcodes.T_BYTE:
                typeSize =1;
                break;
            case Opcodes.T_SHORT:
            case Opcodes.T_CHAR:
                typeSize =2;
                break;
            case Opcodes.T_INT:
            case Opcodes.T_FLOAT:
                typeSize =4;
                break;
            case Opcodes.T_LONG:
            case Opcodes.T_DOUBLE:
                typeSize =8;
                break;
            default:
                throw new IllegalStateException("Illegal op: to NEWARRAY seen: " + operand);
        }

        super.visitInsn(Opcodes.DUP);
        super.visitLdcInsn(typeSize);
        super.visitMethodInsn(Opcodes.INVOKESTATIC,"Com/Agent/CostAccounter", "recordArrayAllocation", "(II)V", true);
        super.visitIntInsn(opcode, operand);
    }

//  참조형
    @Override
    public void visitTypeInsn(int opcode, String type) {

        switch (opcode) {
            case Opcodes.NEW:
                super.visitLdcInsn(type);
                super.visitMethodInsn(Opcodes.INVOKESTATIC, "Com/Agent/CostAccounter", "reocrdAllocation", "(Ljava/lang/String;)V",true);
                break;
            case Opcodes.ANEWARRAY:
                super.visitInsn(Opcodes.DUP);
                super.visitLdcInsn(8);
                super.visitMethodInsn(Opcodes.INVOKESTATIC,"Com/Agent/CostAccounter", "recordArrayAllocation", "(II)V", true);
                break;
        }
        super.visitTypeInsn(opcode,type);


    }

    @Override
    public void visitJumpInsn(int opcode, Label label) {
        super.visitJumpInsn(opcode, label);
//        if(opcode == Opcodes.IF_ACMPEQ)
//            System.out.println("IF_ACMPEQ");
//        else if(opcode == Opcodes.IF_ICMPEQ)
//            System.out.println("IF_ICMPEQ");
//        else if(opcode == Opcodes.IF_ICMPGT)
//            System.out.println("IF_ICMPGT");
//        else if(opcode == Opcodes.IF_ACMPNE)
//            System.out.println("IF_ACMPNE");

//        System.out.println(Opcodes.IFopcode);
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
        mv.visitMethodInsn(INVOKESTATIC, "Com/Agent/MethodCount","start","()V",false);
        super.onMethodEnter();
    }

    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        if (!name.equals( "<init>") && !name.equals( "<clinit>") && !owner.contains("java")) {
            MethodInsnValue methodInsnValue = new MethodInsnValue();
            methodInsnValue.setOpcode(InvokeType.getInvokeMap(opcode));
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
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(methodName);
        mv.visitMethodInsn(INVOKESTATIC, "Com/Agent/MethodCount","end","(Ljava/lang/String;Ljava/lang/String;)V",false);

        super.onMethodExit(opcode);
    }

}
