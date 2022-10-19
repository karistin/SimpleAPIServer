package lucas.base.proxy;

import lucas.base.JavaAgent;
import lucas.base.util.HashUtil;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

/**
 * packageName    : lucas.base.proxy
 * fileName       : LoaderManager
 * author         : lucas
 * date           : 2022-10-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-10-17        lucas       최초 생성
 * 연결 싷패
 */
public class LoaderManager {
//    private static ClassLoader toolsLoader;
    private static ClassLoader[] loaders;
//
//    public synchronized static ClassLoader getToolsLoader() {
//        if (toolsLoader == null) {
//            try {
//                if (JavaAgent.isJava9plus()) {
//                    toolsLoader = ClassLoader.getSystemClassLoader();
//                } else {
//                    File tools = ManifestUtil.getToolsFile();
//                    toolsLoader = new URLClassLoader(new URL[] { tools.toURI().toURL() });
//                }
//            } catch (Throwable e) {
//                Logger.println("A134", e);
//            }
//        }
//
//
//        return createLoader(toolsLoader, "scouter.tools");
//    }
    public static ClassLoader getHttpLoader(ClassLoader parent) {
        return createLoader(parent, "scouter.http");
    }
//
//    //TODO del
//    public static ClassLoader getJdbcLoader(ClassLoader parent) {
//        return createLoader(parent, "scouter.jdbc");
//    }
//
//    public static ClassLoader getDB2Loader(ClassLoader parent) {
//        return createLoader(parent, "scouter.jdbc");
//    }
//
//    public static ClassLoader getHttpClient(ClassLoader parent) {
//        return createLoader(parent, "scouter.httpclient");
//    }
//
//    public static ClassLoader getKafkaClient(ClassLoader parent) {
//        return createLoader(parent, "scouter.kafka");
//    }
//
//    public static ClassLoader getRedisClient(ClassLoader parent) {
//        return createLoader(parent, "scouter.redis");
//    }
//
//    public static ClassLoader getReactiveClient(ClassLoader parent) {
//        return createLoader(parent, "scouter.reactive");
//    }
//
//    public static ClassLoader getOnlyForJava8Plus(ClassLoader parent) {
//        return createLoader(parent, "scouter.java8");
//    }
//
    private synchronized static ClassLoader createLoader(ClassLoader parent, String key) {
//        loader == null 인경우 0 or hash code
        int hashKey = (parent == null ? 0 : System.identityHashCode(parent));
        hashKey = hashKey ^ HashUtil.hash(key);
//        XOR
//        기존에 등록된 로더에서 찾거나
//        Set map을 만들어 주어야 한다 .... 골치아픔
//        ClassLoader loader = loaders.get(hashKey);
        ClassLoader loader = null;
        if (loader == null) {
            try {
                byte[] bytes = deployJarBytes(key);
                if (bytes != null) {
//                    loader = new BytesClassLoader(bytes, parent);
//                    loaders.put(hashKey, loader);
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return null;
    }

//    public static ClassLoader appendToSystemOrBootLoader(String key) {
//        if (JavaAgent.isJava9plus()) {
//            appendToSystemLoader(JavaAgent.getInstrumentation(), key);
//            return ClassLoader.getSystemClassLoader();
//        } else {
//            appendToBootLoader(JavaAgent.getInstrumentation(), key);
//            return null;
//        }
//    }
//
//    private static void appendToSystemLoader(Instrumentation inst, String key) {
//        byte[] bytes = deployJarBytes(key);
//        try {
//            File tempJar = FileUtil.saveAsTemp(key, ".jar", bytes);
//            inst.appendToSystemClassLoaderSearch(new JarFile(tempJar));
//        } catch (IOException e) {
//            Logger.println("A138", "Error on load " + key + ".jar " + e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static void appendToBootLoader(Instrumentation inst, String key) {
//        byte[] bytes = deployJarBytes(key);
//        try {
//            File tempJar = FileUtil.saveAsTemp(key, ".jar", bytes);
//            inst.appendToBootstrapClassLoaderSearch(new JarFile(tempJar));
//        } catch (IOException e) {
//            Logger.println("A138", "Error on load " + key + ".jar " + e);
//            throw new RuntimeException(e);
//        }
//    }
//
    private static byte[] deployJarBytes(String jarname) {
        try {
            InputStream is = JavaAgent.class.getResourceAsStream("/" + jarname + ".jar");
            System.out.println(JavaAgent.class.getResourceAsStream("/" + jarname + ".jar"));
            byte[] newBytes = null;
//            byte[] newBytes = FileUtil.readAll(is);
//            is.close();
//            Logger.println("NONE", "LoadJarBytes " + jarname + " " + ArrayUtil.len(newBytes) + " bytes");
            return newBytes;
        } catch (Exception e) {
//            Logger.println("NONE", "fail to load jar bytes " + jarname);
            return null;
        }
    }
}
