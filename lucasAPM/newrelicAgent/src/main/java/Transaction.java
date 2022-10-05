import tracers.ClassMethodSignature;
import tracers.ClassMethodSignatures;

/**
 * packageName    : PACKAGE_NAME
 * fileName       : Transaction
 * author         : lucas
 * date           : 2022-10-05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-05        lucas       최초 생성
 */
public class Transaction {
    static final ClassMethodSignature REQUEST_INITIALIZED_CLASS_SIGNATURE = new ClassMethodSignature(
            "javax.servlet.ServletRequestListener", "requestInitialized", "(Ljavax/servlet/ServletRequestEvent;)V");
    static final int REQUEST_INITIALIZED_CLASS_SIGNATURE_ID = ClassMethodSignatures.get().add(REQUEST_INITIALIZED_CLASS_SIGNATURE);
    private static final ThreadLocal<Transaction> transactionHolder = new ThreadLocal<>();


}
