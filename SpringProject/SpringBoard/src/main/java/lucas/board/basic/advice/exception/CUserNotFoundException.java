package lucas.board.basic.advice.exception;

/**
 * packageName    : lucas.board.basic.advice.exception
 * fileName       : CUserNotFoundException
 * author         : lucas
 * date           : 2022-11-04
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-04        lucas       최초 생성
 */
public class CUserNotFoundException extends RuntimeException{
    public CUserNotFoundException(String msg, Throwable t)
    {
        super(msg, t);
    }

    public CUserNotFoundException(String msg) {
        super(msg);
    }

    public CUserNotFoundException()
    {
        super();
    }
}
