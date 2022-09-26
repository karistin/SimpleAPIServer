package lucas.base;



/**
 * packageName    : lucas.base
 * fileName       : Hooking
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class Hooking {
    private Transformer transformer;

    public static final Hooking INSTANCE = new Hooking();

    public static final ThreadLocal<ClassLoader> CONTEXT = new ThreadLocal<ClassLoader>();

    private Hooking() {}

    public void setTransformer(Transformer transformer)
    {
        this.transformer = transformer;
    }

    public Transformer getTransformer()
    {
        return transformer;
    }
}
