package lucas.base.asm.extend;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.io.InputStream;

/**
 * packageName    : lucas.base.asm.extend
 * fileName       : ClassReaderWrapper
 * author         : lucas
 * date           : 2022-09-26
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-09-26        lucas       최초 생성
 */
public class ClassReaderWrapper extends ClassReader {
    public ClassReaderWrapper(byte[] classBuffer)
    {
        super(classBuffer);
    }

    public ClassReaderWrapper(final String name)
    {
        super(readClass(name));
    }

    public ClassReaderWrapper(final Class clazz)
    {
        super(readClass(clazz));
    }

    public ClassReaderWrapper(final Object object)
    {
        super(readClass(object));
    }

    private static byte[] readClass(final Class clazz)
    {
        if (clazz == null)
        {
            return new byte[0];
        }

        return readClass(clazz.getClassLoader(), clazz.getName());
    }

    private static byte[] readClass(final Object object)
    {
        if (object == null || object.getClass() == null)
        {
            return new byte[0];
        }

        return readClass(object.getClass().getClassLoader(), object.getClass().getName());
    }

    private static byte[] readClass(final String name)
    {
        return readClass(Hooking.CONTEXT.get(), name);
    }

    private static byte[] readClass(ClassLoader loader, String name)
    {
        try
        {
            String internalClassName = getInternalName(name);

            if (loader == null)
            {
                return readClass(ClassLoader.getSystemResourceAsStream(internalClassName + ".class"), internalClassName);
            }

            return readClass(loader.getResourceAsStream(internalClassName + ".class"), internalClassName);
        }
        catch (Exception exception)
        {
            Logger.debug(LogCodeDef.D008, "Class Reader " + exception.getMessage());
        }

        return new byte[0];
    }

    private static byte[] readClass(InputStream is, String name) throws IOException
    {
        if (is == null)
        {
            throw new IOException("Class not found : " + name);
        }

        try
        {
            byte[] b = new byte[is.available()];
            int len = 0;
            while (true)
            {
                int n = is.read(b, len, b.length - len);
                if (n == -1)
                {
                    if (len < b.length)
                    {
                        byte[] c = new byte[len];
                        System.arraycopy(b, 0, c, 0, len);
                        b = c;
                    }

                    return b;
                }

                len += n;

                if (len == b.length)
                {
                    int last = is.read();
                    if (last < 0)
                    {
                        return b;
                    }

                    byte[] c = new byte[b.length + 1000];
                    System.arraycopy(b, 0, c, 0, len);
                    c[len++] = (byte) last;
                    b = c;
                }
            }
        }
        finally
        {
            is.close();
        }
    }

    public static boolean isValid(String className)
    {
        return mockRead(Hooking.CONTEXT.get(), className);
    }

    private static boolean mockRead(ClassLoader loader, String name)
    {
        try
        {
            String internalClassName = getInternalName(name);

            if (loader == null)
            {
                return mockRead(ClassLoader.getSystemResourceAsStream(internalClassName + ".class"));
            }

            return mockRead(loader.getResourceAsStream(internalClassName + ".class"));
        }
        catch (Exception exception)
        {
            System.out.println(exception);
        }

        return false;
    }

    private static boolean mockRead(InputStream is) {
        return is != null;
    }
}
