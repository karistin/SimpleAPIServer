package lucas.base.servlet;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * packageName    : lucas.base.servlet
 * fileName       : HttpServletRequest
 * author         : lucas
 * date           : 2022-10-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-06        lucas       최초 생성
 *  servlet request date in tomcat
 */
public class HttpServletRequest {

    private String uri;
    private HashMap<String, String> header;
    private String contentType;
    private String method;
    private String remoteAddr;
    private HashMap<String, String> attribute;
    private HashMap<String, String> Parameter;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HashMap<String, String> getHeader() {
        return header;
    }

    public void setHeader(HashMap<String, String> header) {
        this.header = header;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public HashMap<String, String> getAttribute() {
        return attribute;
    }

    public void setAttribute(HashMap<String, String> attribute) {
        this.attribute = attribute;
    }

    public HashMap<String, String> getParameter() {
        return Parameter;
    }

    public void setParameter(HashMap<String, String> parameter) {
        Parameter = parameter;
    }
}
