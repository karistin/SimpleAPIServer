package temp.Loader;

/**
 * packageName    : temp.Loader
 * fileName       : classloader
 * author         : lucas
 * date           : 2022-07-20
 * description    : ClassLoader study https://hbase.tistory.com/174
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-20        lucas       최초 생성
 */
public class Classloader extends ClassLoader{
    /*
    * JVM이 클래스 파일 참조시 동적으로 읽어서 메모리에 로드되면서 JVM에 링크 된다.
    * ClassLoader => 클래스들을 동적으로 메모리에 로딩하는 역활을 담당한다.
    * Loding : .class 파일을 읽고 그 내용에 따른 적절한 바이너리 데이터를 만들고 메소드 영역에 저장하는 동작 수행
    * Linking :
    *  1. verify : 바이너리 데이터 유효한지 확인한다. 다소 오버해드 발생 -Xverify:noe으로 검증을 피할 수 있다.
    *  2. prepare : 클래스의 static 변수와 기본값에 필요한 메모리 공간 준비
    *  3. resolution : Constant Pool의 심볼릭 레퍼런스를 다이렉트 레퍼런스 실제 메모리 주소값으로 변경한다.
    * Initalization : 메모리 영역에 클래스의 static 할당 및 SuperClass 초기화와 해당 클래스 초기화 진행
    *
    * 로더의 순서
    * Bootstrap ClassLoader : 최상위 로더 네이티브로 구현
    *   /jre/lib/rt.jar를 로드함
    *
    * Extension ClassLoader : jre/lib/ext 에 포함된 클래스 파일들 로드
    *   java.ext.dirs 환경변수로 지정된 클래스 파일들 로딩 Java로 구현되어 있고 sun.misc.Lancher 클래스안에 정적 클래스로 구현
    *   URLClassLoader를 상속한다.
    *
    * Application ClassLoader
    *   애플리케이션의 클래스 패스에서 클래스를 읽어 로드함
    *   -cp 경로나 JAR 파일 안에 있는 Manifest 파일에 있는 클래스 로딩
    *   Java로 구현되어 있고 sun.misc.Lancher 클래스안에 정적 클래스로 구현
    *   URLClassLoader를 상속한다.
    *   사용자가 작성한 클래스를 로드하는 클래스
    *
    *
    * Delegation ? 클래스 로더 원칙
    *
    * */

    private final String rootDir;

    public Classloader(String rootDir) {
        this.rootDir = rootDir;
    }

    public static void main(String[] args) {
        String test = "com.jennifersoft.springbootdemo." +
                "SpringBootDemoApplication$$EnhancerBySpringCGLIB$$78a5554e.setBeanFactory";
        if (test.contains("."))
        {
            System.out.println(test.substring(test.lastIndexOf(".")+1));
        }
    }
}
