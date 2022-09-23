package Com.Agent;

import Com.Entity.MethodInsnValue;
import Com.Type.InvokeType;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;
import java.util.Arrays;


public class MyMethodAdapter extends AdviceAdapter implements Opcodes {

    private final String className;
    private final String methodName;
    private final ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<MethodInsnValue>();
    private Type[] paramTypes;

    public MyMethodAdapter(int api, MethodVisitor methodVisitor, int access, String methodName, String descriptor, String className) {
        super(api, methodVisitor, access, methodName, descriptor);
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = Type.getArgumentTypes(descriptor);
    }

    @Override
    public void visitCode() {

//        if (this.methodName.equals("configue")) {
//            super.visitCode();
//            return;
//        }
//        int paramLength = paramTypes.length;
//        mv.visitIntInsn(Opcodes.BIPUSH, paramLength);
//        mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
//        mv.visitVarInsn(Opcodes.ASTORE, paramLength);
////
////        // Fill the created array with method parameters
//        int i = 0;
//
//        for (Type tp : paramTypes) {
//            mv.visitVarInsn(Opcodes.ALOAD, paramLength);
//            mv.visitIntInsn(Opcodes.BIPUSH, i);
//
//            if (tp.equals(Type.BOOLEAN_TYPE) || tp.equals(Type.BYTE_TYPE) || tp.equals(Type.CHAR_TYPE) || tp.equals(Type.SHORT_TYPE) || tp.equals(Type.INT_TYPE))
//                mv.visitVarInsn(Opcodes.ILOAD, i);
//            else if (tp.equals(Type.LONG_TYPE)) {
//                mv.visitVarInsn(Opcodes.LLOAD, i);
//                i++;
//            }
//            else if (tp.equals(Type.FLOAT_TYPE))
//                mv.visitVarInsn(Opcodes.FLOAD, i);
//            else if (tp.equals(Type.DOUBLE_TYPE)) {
//                mv.visitVarInsn(Opcodes.DLOAD, i);
//                i++;
//            }
//            else
//                mv.visitVarInsn(Opcodes.ALOAD, i);
//
//            mv.visitInsn(Opcodes.AASTORE);
//            i++;
//        }


//        // Load id, class name and method name
////        this.visitLdcInsn(new Integer(this.methodID));
//        this.visitLdcInsn(this.className);
//        this.visitLdcInsn(this.methodName);
//
//        // Load the array of parameters that we created


//        mv.visitLdcInsn(this.methodName);
//        mv.visitVarInsn(Opcodes.ALOAD, paramLength);
//
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC,
//                    "Com/Agent/MethodParameter",
//                    "MethodStats",
//                    "(Ljava/lang/String;[Ljava/lang/Object;)V",false);


        super.visitCode();
    }

    //    NAWARRAY 정수형 오퍼랜드가 1개인것
//    @Override
//    public void visitIntInsn(int opcode, int operand) {
//
//        if(opcode != Opcodes.NEWARRAY)
//        {
//            super.visitIntInsn(opcode, operand);
//            return;
//        }
//        final int typeSize;
//
//        switch(operand){
//            case Opcodes.T_BOOLEAN:
//            case Opcodes.T_BYTE:
//                typeSize =1;
//                break;
//            case Opcodes.T_SHORT:
//            case Opcodes.T_CHAR:
//                typeSize =2;
//                break;
//            case Opcodes.T_INT:
//            case Opcodes.T_FLOAT:
//                typeSize =4;
//                break;
//            case Opcodes.T_LONG:
//            case Opcodes.T_DOUBLE:
//                typeSize =8;
//                break;
//            default:
//                throw new IllegalStateException("Illegal op: to NEWARRAY seen: " + operand);
//        }
////        super.visitInsn(Opcodes.DUP);
////        super.visitInsn(Opcodes.ALOAD);
//
//        System.out.println("NEWARRAY "+typeSize);
////        System.out.println(typeSize);
////        super.visitInsn(Opcodes.DUP);
//        mv.visitLdcInsn(typeSize);
//        mv.visitMethodInsn(Opcodes.INVOKESTATIC,"Com/Agent/CostAccounter", "recordArrayAllocation", "(I)V", true);
//        super.visitIntInsn(opcode, operand);
//    }
//
////  참조형
//    @Override
//    public void visitTypeInsn(int opcode, String type) {
//        switch (opcode) {
//            case Opcodes.NEW:
////                187
//                System.out.println("NEW " + type);
////                super.visitLdcInsn(type);
////                super.visitMethodInsn(Opcodes.INVOKESTATIC, "Com/Agent/CostAccounter","recordAllocation","(Ljava/lang/String;)V",true);
//                break;
//            case Opcodes.ANEWARRAY:
////                189
////                Opcodes.H_NEWINVOKESPECIAL
//                System.out.println("ANEWARRAY "+ type);
//                break;
//        }
////        switch (opcode) {
////            case Opcodes.NEW:
////                super.visitLdcInsn(type);
////                super.visitMethodInsn(Opcodes.INVOKESTATIC, "Com/Agent/CostAccounter", "reocrdAllocation", "(Ljava/lang/String;)V",true);
////                break;
////            case Opcodes.ANEWARRAY:
////                super.visitInsn(Opcodes.DUP);
////                super.visitLdcInsn(8);
////                super.visitMethodInsn(Opcodes.INVOKESTATIC,"Com/Agent/CostAccounter", "recordArrayAllocation", "(II)V", true);
////                break;
////        }
//        super.visitTypeInsn(opcode,type);
//
//
//    }

//    @Override
//    public void visitJumpInsn(int opcode, Label label) {
//
//
//        mv.visitInsn(Opcodes.POP);
//        if(opcode == Opcodes.IF_ACMPEQ)
//            System.out.println("refer equal");
//        else if(opcode == Opcodes.IF_ACMPNE)
//            System.out.println("refer not equal");
//        else if(opcode == Opcodes.IF_ICMPEQ)
//            System.out.println("Int equal");
//        else if(opcode == Opcodes.IF_ICMPGE)
//            System.out.println("Value1 greater or equal than Value2");
//        else if(opcode == Opcodes.IF_ICMPGT)
//            System.out.println("Value1 greater than Value2");
//        else if(opcode == Opcodes.IF_ICMPLE)
//            System.out.println("value1 is less than or equal to value2");
//        else if(opcode == Opcodes.IF_ICMPLT)
//            System.out.println("value1 is less than value2");
//        else if(opcode == Opcodes.IF_ICMPNE)
//            System.out.println("ints are not equal");
//
//        super.visitJumpInsn(opcode, label);
//    }


    public String getMethodName() {
        return methodName;
    }

    public ArrayList<MethodInsnValue> getMethodInsnValues() {
        return methodInsnValues;
    }

    @Override
    protected void onMethodEnter() {
        try {
            Class.forName("Com/Agent/MethodCount");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        Label l0 = new Label();
//        mv.visitLabel(l0);
//        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        mv.visitVarInsn(ILOAD, 0); //1 instead of 0 if PrintInt wasn't static
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
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
