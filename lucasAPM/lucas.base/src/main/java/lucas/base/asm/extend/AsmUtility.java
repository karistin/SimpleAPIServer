package lucas.base.asm.extend;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.MethodNode;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * packageName    : lucas.base.asm.extend
 * fileName       : AsmUtility
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class AsmUtility {
    private static final String START_HTTP_SIGNATURE = "(Ljavax/servlet/ServletRequest;Ljavax/servlet/ServletResponse;";

    private AsmUtility() {}

    public static String convertForAgent(String fromAsm) {
        if (fromAsm != null) {
            return fromAsm.replace('/', '.');
        }

        return null;
    }

    public static String getInternalName(String name) {
        if (name != null) {
            return name.replace('.', '/');
        }

        return null;
    }

    public static String getClassName(String className) {
        return convertForAgent(className);
    }

    public static String getName(MethodWrapper methodWrapper) {
        return methodWrapper.method.name;
    }

    public static String getSignature(MethodWrapper methodWrapper) {
        return getSignature(methodWrapper.method);
    }

    public static String getSignature(MethodNode methodNode) {
        return methodNode.desc;
    }

    public static String[] getInterfaceNames(ClassWrapper clazz) {
        String[] r = clazz.interfaces.toArray(new String[clazz.interfaces.size()]);
        for (int i = 0; i < r.length; i++) {
            r[i] = convertForAgent(r[i]);
        }

        return r;
    }

    /**
     * Super 클래스 뿐 아니라 인터페이스의 Super까지 포함하여
     * 클래스의 모든 인터페이스의 이름을 가져온다.
     *
     * @param clazz 대상 클래스
     * @return 인터페이스 이름
     */
    public static String[] getAllInterfaceNames(ClassWrapper clazz) {
        LinkedList<ClassWrapper> queue = new LinkedList<ClassWrapper>();
        Set<String> allInterfaceNames = new TreeSet<String>();
        queue.addLast(clazz);

        while (!queue.isEmpty()) {
            ClassWrapper classInQueue = queue.removeFirst();
            String superClassName = classInQueue.getSuperClassName();
            List<ClassWrapper> interfacesOfClassInQueue = getInterfacesTypeOfClassWrapper(classInQueue);

            if (classInQueue.isInterface()) {
                allInterfaceNames.add(classInQueue.getClassName());
            } else {
                if (superClassName != null && !superClassName.equals("java.lang.Object")) {
                    ClassWrapper classNode = getClassWrapperUsingClassName(superClassName);

                    if (classNode != null) {
                        queue.addLast(classNode);
                    }
                }
            }

            for (ClassWrapper classWrapperOfInterface : interfacesOfClassInQueue) {
                queue.addLast(classWrapperOfInterface);
            }
        }

        return allInterfaceNames.toArray(new String[0]);
    }

    public static String[] getAllSuperClassesNames(ClassWrapper clazz) {
        LinkedList<ClassWrapper> queue = new LinkedList<ClassWrapper>();
        Set<String> allSuperClassesNames = new TreeSet<String>();
        queue.addLast(clazz);

        while (!queue.isEmpty()) {
            ClassWrapper classInQueue = queue.removeFirst();
            String superClassName = classInQueue.getSuperClassName();

            if (superClassName == null) {
                break;
            }

            ClassWrapper superClassesOfClassInQueue = getSuperClassTypeOfClassWrapper(classInQueue);

            if (superClassesOfClassInQueue != null && !superClassesOfClassInQueue.getClassName()
                    .equals("java.lang.Object")) {
                allSuperClassesNames.add(superClassesOfClassInQueue.getClassName());

                queue.addLast(superClassesOfClassInQueue);
            }
        }

        return allSuperClassesNames.toArray(new String[0]);
    }

    private static List<ClassWrapper> getInterfacesTypeOfClassWrapper(ClassWrapper clazz) {
        String[] interfaceNames = clazz.getInterfaceNames();
        List<ClassWrapper> interfaces = new ArrayList<ClassWrapper>();

        for (String interfaceName : interfaceNames) {
            ClassWrapper classWrapper = getClassWrapperUsingClassName(interfaceName);

            if (classWrapper != null) {
                interfaces.add(classWrapper);
            }
        }

        return interfaces;
    }

    private static ClassWrapper getSuperClassTypeOfClassWrapper(ClassWrapper clazz) {
        String superClassName = clazz.getSuperClassName();

        return getClassWrapperUsingClassName(superClassName);
    }

    private static ClassWrapper getClassWrapperUsingClassName(String className) {
        if (ClassReaderWrapper.isValid(className)) {
            ClassWrapper classNode = new ClassWrapper();
            ClassReader cr = new ClassReaderWrapper(className);
            cr.accept(classNode, new Attribute[0], 0);

            return classNode;
        }

        return null;
    }

    public static ClassWrapper parse(byte[] classfileBuffer) {
        if (classfileBuffer == null || classfileBuffer.length == 0) {
            return null;
        }

        return parse(classfileBuffer, 0);
    }

    public static ClassWrapper parse(byte[] classfileBuffer, int flags) {
        ClassWrapper classWrapper = new ClassWrapper();
        ClassReader reader = new ClassReaderWrapper(classfileBuffer);

        // reader.accept(clazz, new Attribute[0], flags);
        reader.accept(useJSRInlinerAdapter(classWrapper), new Attribute[0], flags);

        return classWrapper;
    }

    public static ClassWrapper parse(Object obj) {
        ClassWrapper classWrapper = new ClassWrapper();

        ClassReader reader = new ClassReaderWrapper(obj.getClass().getName());

        // reader.accept(clazz, new Attribute[0], 0);
        reader.accept(useJSRInlinerAdapter(classWrapper), new Attribute[0], 0);

        return classWrapper;
    }

    public static ClassWrapper parse(Class<?> clazz) {
        ClassWrapper classWrapper = new ClassWrapper();

        ClassReader reader = new ClassReaderWrapper(clazz);

        // reader.accept(clazz, new Attribute[0], 0);
        reader.accept(useJSRInlinerAdapter(classWrapper), new Attribute[0], 0);

        return classWrapper;
    }

    private static ClassVisitor useJSRInlinerAdapter(ClassVisitor visitor) {
        return new ClassVisitor(Opcodes.ASM9, visitor) {
            @Override
            public MethodVisitor visitMethod(int access,
                                             String name,
                                             String desc,
                                             String signature,
                                             String[] exceptions) {
                return new JSRInlinerAdapter(super.visitMethod(access, name, desc, signature, exceptions),
                        access,
                        name,
                        desc,
                        signature,
                        exceptions);
            }
        };
    }

    public static byte[] toBytes(Class<?> clazz) {
        return toBytes(parse(clazz));
    }

    public static byte[] toBytes(Object obj) {
        return toBytes(parse(obj));
    }

    public static byte[] toBytes(ClassWrapper clazz) {
        return toBytes(clazz, false);
    }

    public static byte[] toBytes(ClassWrapper clazz, boolean checkVerify) {
        return toBytes(clazz, checkVerify, false);
    }

    public static byte[] toBytes(ClassWrapper clazz, boolean checkVerify, boolean isLwst) {
        int flags = ClassWriter.COMPUTE_MAXS;

        if (clazz.version > Opcodes.V1_5) {
            flags |= ClassWriter.COMPUTE_FRAMES;
        }

        ClassWriter classWriter;

        if (isLwst) {
            classWriter = new ClassWriter(flags);
        } else {
            classWriter = new ClassWriterWrapper(flags);
        }

        // clazz.accept(classWriter);
        clazz.accept(useJSRInlinerAdapter(classWriter));

        // FIXME Verify 체크를 먼저 할 수 있을까? 현재는 반반임...
        if (checkVerify) {
            isValidBinary(clazz.getClassName(), classWriter.toByteArray());
        }

        return classWriter.toByteArray();
    }

    public static byte[] toBytesWithValidation(ClassWrapper clazz) {
        byte[] r = toBytes(clazz);
        if (!isValidBinary(r)) {
            throw new RuntimeException();
        }

        return r;
    }

    // FIXME ASM8_EXPERIMENTAL can only be used by classes compiled with --enable-preview
    public static boolean isValidBinary(byte[] bytes) {
        return isValidBinary(null, bytes);
    }

    public static boolean isValidBinary(String className, byte[] bytes) {
        PrintWriter printWriter = null;

        try {
            StringWriter resultWriter = new StringWriter();
            printWriter = new PrintWriter(resultWriter);

            //            CheckClassAdapter.verify(new ClassReaderWrapper(bytes), false, printWriter);
            CheckClassAdapter.verify(new ClassReaderWrapper(bytes), Hooking.CONTEXT.get(), false, printWriter);

            String s = resultWriter.toString();

            if (s != null && s.length() > 0) {
                if (className != null) {
                    Logger.debug(I001, className);
                }

                Logger.trace(I001, resultWriter.toString());

                return false;
            }

            return true;
        } catch (Exception exception) {
            if (className != null) {
                Logger.debug(I001, className);
            }

            Logger.debug(I001, exception);
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }

        return false;
    }

    public static String getTypeName(String description) {
        if (description != null && description.length() > 2) {
            return description.substring(1, description.length() - 1).replace('/', '.');
        }

        return description;
    }

    public static String[] getAnnotations(ClassWrapper clazz) {
        String[] result = null;

        List<AnnotationNode> annotations = clazz.visibleAnnotations;
        if (annotations != null) {
            result = new String[annotations.size()];

            for (int i = 0; i < annotations.size(); i++) {
                result[i] = getTypeName(annotations.get(i).desc);
            }
        }

        return result;
    }

    public static final String TYPE_B = "B";
    public static final String TYPE_C = "C";
    public static final String TYPE_I = "I";
    public static final String TYPE_S = "S";
    public static final String TYPE_J = "J";
    public static final String TYPE_F = "F";
    public static final String TYPE_D = "D";
    public static final String TYPE_Z = "Z";
    public static final String TYPE_V = "V";
    public static final String TYPE_L = "L";
    public static final String TYPE_A = "[";

    public static int descriptionToOpcode(String description) {
        // "Z", "C", "F", "D", "B", "S", "I", "J",
        if (TYPE_Z.equals(description) || TYPE_B.equals(description) || TYPE_C.equals(description) || TYPE_S.equals(
                description) || TYPE_I.equals(description)) // boolean, byte, char, short, int
        {
            return Opcodes.ILOAD;
        }

        if (TYPE_J.equals(description)) // long
        {
            return Opcodes.LLOAD;
        }

        if (TYPE_F.equals(description)) // float
        {
            return Opcodes.FLOAD;
        }

        if (TYPE_D.equals(description)) // double
        {
            return Opcodes.DLOAD;
        }

        return Opcodes.ALOAD;
    }

    public static int returnToOpcode(MethodNode method) {
        return returnToOpcode(method.desc);
    }

    public static int returnToOpcode(String description) {
        Type type = Type.getReturnType(description);

        if (type == null || Type.VOID_TYPE == type) {
            return Opcodes.RETURN;
        } else if (Type.BOOLEAN_TYPE == type || Type.CHAR_TYPE == type || Type.BYTE_TYPE == type || Type.SHORT_TYPE == type || Type.INT_TYPE == type) {
            return Opcodes.IRETURN;
        } else if (Type.LONG_TYPE == type) {
            return Opcodes.LRETURN;
        } else if (Type.FLOAT_TYPE == type) {
            return Opcodes.FRETURN;
        } else if (Type.DOUBLE_TYPE == type) {
            return Opcodes.DRETURN;
        } else {
            return Opcodes.ARETURN;
        }
    }

    public static Type getType(ClassWrapper clazz, boolean useToClassDesc) {
        return getType(clazz.name, useToClassDesc);
    }

    public static Type getType(String className, boolean useToClassDesc) {
        if (useToClassDesc) {
            return Type.getType(toClassDesc(className));
        }

        return Type.getType(className);
    }

    public static String toClassDesc(String className) {
        return "L" + className.replace('.', '/') + ";";
    }

    public static <T> String toClassDesc(Class<T> clazz) {
        return toClassDesc(clazz.getName());
    }

    public static FieldNode findField(ClassWrapper clazz, String target, FieldNode def) {
        for (FieldNode fn : clazz.fields) {
            if (fn.name.equals(target)) {
                return fn;
            }
        }

        return def;
    }

    public static FieldNode ensureField(ClassWrapper clazz, String fieldName) {
        FieldNode field = findField(clazz, fieldName, null);

        if (field == null) {
            new AsmField().addField(clazz, fieldName, Type.getType(Object.class));

            field = findField(clazz, fieldName, null);
        }

        return field;
    }

    public static FieldInsnNode createPUTSTATIC(Type classType, String fieldName, String fieldDescription) {
        return new FieldInsnNode(Opcodes.PUTSTATIC, classType.getInternalName(), fieldName, fieldDescription);
    }

    public static FieldInsnNode createPUTFIELD(Type classType, FieldNode field) {
        return new FieldInsnNode(Opcodes.PUTFIELD, classType.getInternalName(), field.name, field.desc);
    }

    public static FieldInsnNode createGETFIELD(ClassWrapper clazz, FieldNode field) {
        return new FieldInsnNode(Opcodes.GETFIELD, clazz.name, field.name, field.desc);
    }

    public static FieldInsnNode createGETFIELD(ClassWrapper clazz, String fieldName, String fieldDescription) {
        return new FieldInsnNode(Opcodes.GETFIELD, clazz.name, fieldName, fieldDescription);
    }

    public static FieldInsnNode createGETFIELD(Type classType, FieldNode field) {
        return new FieldInsnNode(Opcodes.GETFIELD, classType.getInternalName(), field.name, field.desc);
    }

    public static FieldInsnNode createPUTFIELD(ClassWrapper clazz, FieldNode field) {
        return new FieldInsnNode(Opcodes.PUTFIELD, clazz.name, field.name, field.desc);
    }

    public static boolean containsAccess(ClassWrapper classNode, int accessCode) {
        return (classNode.access & accessCode) != 0;
    }

    public static boolean containsAccess(InnerClassNode innerClass, int accessCode) {
        return (innerClass.access & accessCode) != 0;
    }

    public static boolean containsAccess(MethodNode method, int accessCode) {
        return (method.access & accessCode) != 0;
    }

    public static boolean containsAccess(FieldNode field, int accessCode) {
        return (field.access & accessCode) != 0;
    }

    public static boolean containsAccess(int access, int opcode) {
        return (access & opcode) != 0;
    }


    public static int hash(MethodNode method) {
        return HashUtil.hash(method.name + method.desc);
    }

    public static boolean hasBody(MethodNode method) {
        return method.instructions.size() > 0;
    }

    public static boolean isAbstract(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_ABSTRACT);
    }

    public static boolean isPublic(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_PUBLIC);
    }

    public static boolean isPrivate(InnerClassNode clazz) {
        return containsAccess(clazz, Opcodes.ACC_PRIVATE);
    }

    public static boolean isProtected(InnerClassNode clazz) {
        return containsAccess(clazz, Opcodes.ACC_PROTECTED);
    }

    public static boolean isPublic(InnerClassNode clazz) {
        return containsAccess(clazz, Opcodes.ACC_PUBLIC);
    }

    public static boolean isFinal(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_FINAL);
    }

    public static boolean isStatic(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_STATIC);
    }

    public static boolean isProtected(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_PROTECTED);
    }

    public static boolean isPrivate(ClassWrapper clazz) {
        return containsAccess(clazz, Opcodes.ACC_PRIVATE);
    }

    public static boolean isAbstract(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_ABSTRACT);
    }

    public static boolean isNative(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_NATIVE);
    }

    public static boolean isPublic(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_PUBLIC);
    }

    public static boolean isProtected(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_PROTECTED);
    }

    public static boolean isPrivate(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_PRIVATE);
    }

    public static boolean isFinal(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_FINAL);
    }

    public static boolean isSynchronized(MethodNode method) {
        return containsAccess(method, Opcodes.ACC_SYNCHRONIZED);
    }

    public static boolean isPublic(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_PUBLIC);
    }

    public static boolean isProtected(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_PROTECTED);
    }

    public static boolean isPrivate(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_PRIVATE);
    }

    public static boolean isFinal(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_FINAL);
    }

    public static boolean isStatic(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_STATIC);
    }

    public static boolean isSynchronized(FieldNode field) {
        return containsAccess(field, Opcodes.ACC_SYNCHRONIZED);
    }

    public static boolean isPublic(int access) {
        return containsAccess(access, Opcodes.ACC_PUBLIC);
    }

    public static boolean isStatic(int access) {
        return containsAccess(access, Opcodes.ACC_STATIC);
    }

    public static boolean isStatic(MethodNode m) {
        return containsAccess(m, Opcodes.ACC_STATIC);
    }

    public static boolean isStaticField(AbstractInsnNode ins) {
        return ins.getOpcode() == Opcodes.GETSTATIC;
    }

    public static boolean isReturnNode(AbstractInsnNode ins) {
        switch (ins.getOpcode()) {
            case Opcodes.IRETURN:
            case Opcodes.LRETURN:
            case Opcodes.FRETURN:
            case Opcodes.DRETURN:
            case Opcodes.ARETURN:
            case Opcodes.RETURN:
                return true;
            default:
                return false;
        }
    }

    public static boolean isPutFieldNode(AbstractInsnNode ins) {
        return ins.getOpcode() == Opcodes.PUTFIELD;
    }

    public static boolean checkTarget(MethodNode method) {
        return !isNative(method) && !isAbstract(method) && hasBody(method);
    }

    // method 이름에 $ 가 붙는 경우가 있나?
    @Deprecated
    public static boolean checkTargetWithInner(MethodNode method) {
        return checkTarget(method) && !method.name.contains("$");
    }

    public static boolean checkTargetAndStaticWithInner(MethodNode method) {
        if (!checkTarget(method) || isStatic(method)) {
            return false;
        }

        return !method.name.contains("$");
    }

    public static boolean checkTargetWithLookup(MethodNode method) {
        if (!checkTarget(method) || isStatic(method)) {
            return false;
        }

        return "lookup".equals(method.name);
    }

    public static int findFirstArgumentTypes(MethodNode methodNode, String target) {
        Type[] args = Type.getArgumentTypes(methodNode.desc);

        if (target == null || args.length == 0) {
            return -1;
        }

        for (int i = 0; i < args.length; i++) {
            if (target.equals(args[i].getDescriptor())) {
                if (!isStatic(methodNode)) {
                    return 1 + i;
                }

                return i;
            }
        }

        return -1;
    }

    public static VarInsnNode createALOAD(LocalVariableNode localVar) {
        return createALOAD(localVar.index);
    }

    public static VarInsnNode createALOAD(int index) {
        return new VarInsnNode(Opcodes.ALOAD, index);
    }

    public static VarInsnNode createILOAD(LocalVariableNode localVariableNode) {
        return createILOAD(localVariableNode.index);
    }

    public static VarInsnNode createILOAD(int index) {
        return new VarInsnNode(Opcodes.ILOAD, index);
    }

    public static LdcInsnNode createLDC(String value) {
        return new LdcInsnNode(value);
    }

    public static InsnNode createI2C() {
        return new InsnNode(Opcodes.I2C);
    }

    public static InsnNode createRETURN() {
        return new InsnNode(Opcodes.RETURN);
    }

    public static InsnNode createIRETURN() {
        return new InsnNode(Opcodes.IRETURN);
    }

    public static InsnNode createDUP() {
        return new InsnNode(Opcodes.DUP);
    }

    public static InsnNode createPOP() {
        return new InsnNode(Opcodes.POP);
    }

    public static VarInsnNode createISTORE(LocalVariableNode r) {
        return new VarInsnNode(Opcodes.ISTORE, r.index);
    }

    public static VarInsnNode createASTORE(LocalVariableNode r) {
        return createASTORE(r.index);
    }

    public static VarInsnNode createASTORE(int index) {
        return new VarInsnNode(Opcodes.ASTORE, index);
    }

    public static InsnNode createARETURN() {
        return new InsnNode(Opcodes.ARETURN);
    }

    public static InsnNode createATHROW() {
        return new InsnNode(Opcodes.ATHROW);
    }

    public static VarInsnNode createFSTORE(LocalVariableNode r) {
        return new VarInsnNode(Opcodes.FSTORE, r.index);
    }

    public static VarInsnNode createFLOAD(LocalVariableNode r) {
        return createFLOAD(r.index);
    }

    public static VarInsnNode createFLOAD(int index) {
        return new VarInsnNode(Opcodes.FLOAD, index);
    }

    public static InsnNode createI2D() {
        return new InsnNode(Opcodes.I2D);
    }

    public static InsnNode createDRETURN() {
        return new InsnNode(Opcodes.DRETURN);
    }

    public static InsnNode createI2F() {
        return new InsnNode(Opcodes.I2F);
    }

    public static InsnNode createFRETURN() {
        return new InsnNode(Opcodes.FRETURN);
    }

    public static VarInsnNode createDLOAD(LocalVariableNode r) {
        return createDLOAD(r.index);
    }

    public static VarInsnNode createDLOAD(int index) {
        return new VarInsnNode(Opcodes.DLOAD, index);
    }

    public static VarInsnNode createDSTORE(LocalVariableNode r) {
        return new VarInsnNode(Opcodes.DSTORE, r.index);
    }

    public static InsnNode createI2S() {
        return new InsnNode(Opcodes.I2S);
    }

    public static VarInsnNode createLLOAD(LocalVariableNode r) {
        return createLLOAD(r.index);
    }

    public static VarInsnNode createLLOAD(int index) {
        return new VarInsnNode(Opcodes.LLOAD, index);
    }

    public static VarInsnNode createLSTORE(LocalVariableNode r) {
        return new VarInsnNode(Opcodes.LSTORE, r.index);
    }

    public static InsnNode createI2L() {
        return new InsnNode(Opcodes.I2L);
    }

    public static InsnNode createLRETURN() {
        return new InsnNode(Opcodes.LRETURN);
    }

    public static TypeInsnNode createCHECKCAST(Type type) {
        return new TypeInsnNode(Opcodes.CHECKCAST, type.getInternalName());
    }

    public static InsnNode createI2B() {
        return new InsnNode(Opcodes.I2B);
    }

    public static int getArgumentIndex(MethodNode method, int argIndex) {
        return getFixedArgumentIndices(method)[argIndex];
    }

    public static InsnNode createACONST_NULL() {
        return new InsnNode(Opcodes.ACONST_NULL);
    }

    public static TypeInsnNode createNewNode(String className) { // TODO replace 말고 Type의 getInternalName을 써야한다. 고치자.
        return new TypeInsnNode(Opcodes.NEW, className.replace('.', '/'));
    }

    public static MethodInsnNode createINVOKESTATIC(String className, String methodName, String methodDescription) {
        return createMethodInsn(Opcodes.INVOKESTATIC, className, methodName, methodDescription);
    }

    public static MethodInsnNode createINVOKESPECIALC(String className, String methodName, String methodDescription) {
        return createMethodInsn(Opcodes.INVOKESPECIAL, className, methodName, methodDescription);
    }

    public static MethodInsnNode createINVOKEVIRTUAL(String className, String methodName, String methodDesc) {
        return createMethodInsn(Opcodes.INVOKEVIRTUAL, className, methodName, methodDesc);
    }

    public static FieldNode createField(int access, Type type, String fieldName) {
        return new FieldNode(access, fieldName, type.getDescriptor(), null, null);
    }

    public static FieldNode createPublicObjectField(String fieldName) {
        return createField(Opcodes.ACC_TRANSIENT | Opcodes.ACC_PUBLIC, Type.getType(Object.class), fieldName);
    }

    public static LocalVariableNode addNewLocalVariable(MethodNode m,
                                                        String name,
                                                        Type type,
                                                        LabelNode start,
                                                        LabelNode end) {
        LocalVariableNode r = new LocalVariableNode(name, type.getDescriptor(), null, start, end, m.maxLocals);
        m.maxLocals += type.getSize();
        m.localVariables.add(r);

        return r;
    }

    public static MethodInsnNode createMethodInsn(int opcode, String className, String methodName, String desc) {
        return new MethodInsnNode(opcode,
                className.replace('.', '/'),
                methodName,
                desc,
                opcode == Opcodes.INVOKEINTERFACE);
    }

    public static int[] getFixedArgumentIndices(MethodNode methodNode) {
        Type[] args = Type.getArgumentTypes(methodNode.desc);
        int[] r;
        if (!isStatic(methodNode)) {
            r = new int[args.length + 1];
            r[0] = 0;
            int size = 1;
            for (int i = 0; i < args.length; i++) {
                r[1 + i] = size;
                size += args[i].getSize();
            }
        } else {
            r = new int[args.length];
            int size = 0;
            for (int i = 0; i < args.length; i++) {
                r[i] = size;
                size += args[i].getSize();
            }
        }

        return r;
    }

    public static AbstractInsnNode createPushNode(int value) {
        if (value == -1) {
            return new InsnNode(Opcodes.ICONST_M1);
        } else if (value == 0) {
            return new InsnNode(Opcodes.ICONST_0);
        } else if (value == 1) {
            return new InsnNode(Opcodes.ICONST_1);
        } else if (value == 2) {
            return new InsnNode(Opcodes.ICONST_2);
        } else if (value == 3) {
            return new InsnNode(Opcodes.ICONST_3);
        } else if (value == 4) {
            return new InsnNode(Opcodes.ICONST_4);
        } else if (value == 5) {
            return new InsnNode(Opcodes.ICONST_5);
        } else if ((value >= -128) && (value <= 127)) {
            return new IntInsnNode(Opcodes.BIPUSH, value);
        } else if ((value >= -32768) && (value <= 32767)) {
            return new IntInsnNode(Opcodes.SIPUSH, value);
        } else {
            return new LdcInsnNode(value);
        }
    }

    public static AbstractInsnNode createPushNode(Object value) {
        return (value == null) ? new InsnNode(Opcodes.ACONST_NULL) : new LdcInsnNode(value);
    }

    public static boolean isHttp(String signature) {
        return signature.startsWith(START_HTTP_SIGNATURE);
    }
}
