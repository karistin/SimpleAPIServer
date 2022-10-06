package lucas.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * packageName    : lucas.base.servlet
 * fileName       : ServiceContext
 * author         : lucas
 * date           : 2022-10-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-06        lucas       최초 생성
 */
public interface ServiceContext {
    int getType();

    void setType(int type);

    ActiveObject getActiveObject();

    void setActiveObject(ActiveObject activeObject);


}
