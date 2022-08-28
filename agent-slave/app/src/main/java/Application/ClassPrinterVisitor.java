//package Application;
//
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.Type;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public static class ClassPrinterVisitor extends ClassVisitor {
//    private String name;
//    private String desc;
//    private String signature;
//
//    private Type[] paramTypes;
//    private boolean isStatic;
//    private String className;
//    private String methodName;
//    private String methodDesc;
//    private String owner;
//    private int access;
//
//    public ClassPrinterVisitor(int api, ClassVisitor cv) {
//        super(api, cv);
//    }
//
//    public ClassPrinterVisitor(int api) {
//        super(api);
//    }
//
//
//    @Override
//    public MethodVisitor visitMethod(int access, String name, String desc,
//                                     String signature, String[] exceptions) {
//
//        MethodVisitor oriMv = new MethodVisitor(Opcodes.ASM4) {
//        };
//
//        final MethodVisitor instMv2 = new MethodPrinterVisitor(access, desc, oriMv, Type.getArgumentTypes(desc), (access & Opcodes.ACC_STATIC) != 0, className,
//                name, desc);
//        return instMv2;
//    }
//
//
//    private class MethodPrinterVisitor extends MethodVisitor {
//
//        List<Object> params = new ArrayList<>();
//        List<Object> params2 = new ArrayList<>();
//
//        private Type[] paramTypes;
//        private boolean isStatic;
//        private String className;
//        private String methodName;
//        private String methodDesc;
//
//        public MethodPrinterVisitor(int api, MethodVisitor mv) {
//            super(api, mv);
//        }
//
//
//        public MethodPrinterVisitor(int access, String desc, MethodVisitor mv, Type[] paramTypes, boolean isStatic, String classname,
//                                    String methodname, String methoddesc) {
//            super(Opcodes.ASM4, mv);
//            this.paramTypes = paramTypes;
//            this.isStatic = isStatic;
//            this.className = classname;
//            this.methodName = methodname;
//            this.methodDesc = methoddesc;
//
//        }
//
//
//        @Override
//        public void visitLdcInsn(Object var1) {
//            if (var1 != null) {
//                params.add(var1);
//                super.visitLdcInsn(var1);
//                System.out.printf("arg: %s %n", var1.toString());
//
//            }
//
//        }
//        @Override
//        public void visitInsn(int var1) {
//            if(this.mv != null) {
//                this.mv.visitInsn(var1);
//            }
//
//        }
//        @Override
//        public void visitIntInsn(int var1, int var2) {
//            if(this.mv != null) {
//                this.mv.visitIntInsn(var1, var2);
//            }
//
//        }
//        @Override
//        public void visitVarInsn(int var1, int var2) {
//            if(this.mv != null) {
//                this.mv.visitVarInsn(var1, var2);
//            }
//
//        }
//
//        @Override
//        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
//            Pattern pattern = Pattern.compile("[a-zA-Z0-9._]*");
//
//            System.out.printf("---------------------------%n");
//            System.out.printf("Class %s calls method %s from class %s%n", ClassPrinterVisitor.this.name, name, owner);
//            System.out.printf("Desc: %s signature: %s%n", ClassPrinterVisitor.this.desc, ClassPrinterVisitor.this.signature);
//            for (Object p : params) {
//                Matcher matcher = pattern.matcher(p.toString());
//                if (!p.toString().isEmpty() && !p.toString().startsWith(".") && matcher.matches()) {
//                    System.out.printf("visitLdcInsn: %s %n", p);
//                }
//            }
//            System.out.printf("---------------------------%n%n");
//            params = new ArrayList<>();
//        }
//
//        @Override
//        public void visitCode() {
//            int paramLength = paramTypes.length;
//
//            // Create array with length equal to number of parameters
//            mv.visitIntInsn(Opcodes.BIPUSH, paramLength);
//            mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
//            mv.visitVarInsn(Opcodes.ASTORE, paramLength);
//
//            // Fill the created array with method parameters
//            int i = 0;
//            for (Type tp : paramTypes) {
//                mv.visitVarInsn(Opcodes.ALOAD, paramLength);
//                mv.visitIntInsn(Opcodes.BIPUSH, i);
//
//                if (tp.equals(Type.BOOLEAN_TYPE)) {
//                    mv.visitVarInsn(Opcodes.ILOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Boolean", "valueOf", "(Z)Ljava/lang/Boolean;",false);
//                } else if (tp.equals(Type.BYTE_TYPE)) {
//                    mv.visitVarInsn(Opcodes.ILOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Byte", "valueOf", "(B)Ljava/lang/Byte;",false);
//                } else if (tp.equals(Type.CHAR_TYPE)) {
//                    mv.visitVarInsn(Opcodes.ILOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Character", "valueOf", "(C)Ljava/lang/Character;",false);
//                } else if (tp.equals(Type.SHORT_TYPE)) {
//                    mv.visitVarInsn(Opcodes.ILOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Short", "valueOf", "(S)Ljava/lang/Short;",false);
//                } else if (tp.equals(Type.INT_TYPE)) {
//                    mv.visitVarInsn(Opcodes.ILOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;",false);
//                } else if (tp.equals(Type.LONG_TYPE)) {
//                    mv.visitVarInsn(Opcodes.LLOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Long", "valueOf", "(J)Ljava/lang/Long;",false);
//                    i++;
//                } else if (tp.equals(Type.FLOAT_TYPE)) {
//                    mv.visitVarInsn(Opcodes.FLOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Float", "valueOf", "(F)Ljava/lang/Float;",false);
//                } else if (tp.equals(Type.DOUBLE_TYPE)) {
//                    mv.visitVarInsn(Opcodes.DLOAD, i);
//                    mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Double", "valueOf", "(D)Ljava/lang/Double;",false);
//                    i++;
//                } else
//                    mv.visitVarInsn(Opcodes.ALOAD, i);
//
//                mv.visitInsn(Opcodes.AASTORE);
//                i++;
//            }
//
//            // Load id, class name and method name
//            this.visitLdcInsn(new Integer(this.methodID));
//            this.visitLdcInsn(this.className);
//            this.visitLdcInsn(this.methodName);
//
//            // Load the array of parameters that we created
//            this.visitVarInsn(Opcodes.ALOAD, paramLength);
//
//
//            mv.visitMethodInsn(Opcodes.INVOKESTATIC, className, name, signature);
//            super.visitCode();
//        }
//
//
//    }
//
//}