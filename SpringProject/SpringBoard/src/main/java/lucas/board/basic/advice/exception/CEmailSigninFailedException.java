package lucas.board.basic.advice.exception;

/**
 * packageName    : lucas.board.basic.advice.exception
 * fileName       : CEmailSigninFailedException
 * author         : lucas
 * date           : 2022-11-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-23        lucas       최초 생성
 */
public class CEmailSigninFailedException extends RuntimeException{
    public CEmailSigninFailedException() {
        super();
    }

    public CEmailSigninFailedException(String message) {
        super(message);
    }

    public CEmailSigninFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
