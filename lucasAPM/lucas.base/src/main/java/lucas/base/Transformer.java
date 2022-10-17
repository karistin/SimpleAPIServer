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
 * MainTransformer을 제외한 다양한 Transformer의 정의를 위한 인터페이스
 */
public interface Transformer {
    byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, byte[] classfileBuffer);
}
