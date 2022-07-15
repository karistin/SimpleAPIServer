package Package;

import Entity.MethodInsnValue;
import org.objectweb.asm.Handle;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.HashMap;

public class MyMethodVisitor extends MethodVisitor implements Opcodes {
    public ArrayList<MethodInsnValue> methodInsnValues= new ArrayList<MethodInsnValue>();
    public MyMethodVisitor(final MethodVisitor methodVisitor) {
        super(ASM9, methodVisitor);
    }
    static final HashMap<Integer, String> invokeMap = new HashMap<>(){
        {
            put(Opcodes.INVOKEVIRTUAL,"INVOKEVIRTUAL");
            put(Opcodes.INVOKESPECIAL,"INVOKESPECIAL");
            put(Opcodes.INVOKESTATIC,"INVOKESTATIC");
            put(Opcodes.INVOKEINTERFACE,"INVOKEINTERFACE");
            put(Opcodes.INVOKEDYNAMIC,"INVOKEDYNAMIC");

        }
    };


    /*
     * 함수 호출 지시어 다른 함수를 불러온다.
     * 주의 ) lambda 의 경우 다른 곳에 있다.
     * opcode :  INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
     * INVOKEVIRTUAL  c1 : c1이 가르키는 메소드 가상 디스패치를 통해 호출  (정적 디스패치 / 동적 디스패치 )
     * INVOKESPECIAL c1 : c1이 가르키는 메소드 특별한 디스패치를 통해 호출 init / client
     * INVOKESTATIC c1 : c1이 가르키는 정적 메소드 호출
     * INVOKEINTERFACE c1 , count, 0: c1이 가르키는 인터페이스 메소들ㄹ 오프셋 룩업을 통해 가르킨다.
     * owner : 실행하는 놈의 주인 클라스 이름 보기
     * isInterface : owner가 interface인지
     * */
    @Override
    public void visitMethodInsn(int opcode, String owner, String name, String descriptor, boolean isInterface) {
        //System.out.println("\t "+"owner : "+owner +" opcode :" +
        //        invokeMap.get(opcode)+" name : "+name
        //        +" descriptor : "+ descriptor);
        if (!name.equals( "<init>") && !name.equals( "<clinit>")) {
            MethodInsnValue methodInsnValue = new MethodInsnValue();
            methodInsnValue.setOpcode(invokeMap.get(opcode));
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
        System.out.println("\t "+"owner : "+ bootstrapMethodArguments +" opcode :" +
                invokeMap.get(Opcodes.INVOKEDYNAMIC)+" name : "+name
                +" descriptor : " +descriptor);
        super.visitInvokeDynamicInsn(name, descriptor, bootstrapMethodHandle, bootstrapMethodArguments);
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
