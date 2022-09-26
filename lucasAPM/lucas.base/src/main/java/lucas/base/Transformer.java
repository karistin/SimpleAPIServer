package lucas.base;

/**
 * packageName    : lucas.base
 * fileName       : Transformer
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public interface Transformer {
    byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, byte[] classfileBuffer);
}
