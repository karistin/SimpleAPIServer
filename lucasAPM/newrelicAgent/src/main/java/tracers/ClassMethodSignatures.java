package tracers;

import util.InsertOnlyArray;

/**
 * packageName    : tracers
 * fileName       : ClassMethodSignatures
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class ClassMethodSignatures {
    private static final ClassMethodSignatures INSTANCE = new ClassMethodSignatures();

    private final InsertOnlyArray<ClassMethodSignature> signatures;

    ClassMethodSignatures() {
        this(1000);
    }

    ClassMethodSignatures(int capacity) {
        signatures = new InsertOnlyArray<>(capacity);
    }

    public static ClassMethodSignatures get() {
        return INSTANCE;
    }

    public ClassMethodSignature get(int index) {
        return signatures.get(index);
    }

    public int add(ClassMethodSignature signature) {
        return signatures.add(signature);
    }

    public int getIndex(ClassMethodSignature signature) {
        return signatures.getIndex(signature);
    }
}
