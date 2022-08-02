
## **Java Profiler**

프로그램의 공간(시간 복잡도), 특정 지시어(instructions)의 사용량, 함수 호출의 빈도와 사용량등을 조사하여 프로그램의 성능을 튜닝한다.
사용자가 원하는 함수나 클래스의 사용량을 분석하고 또한 그들의 내부 동작과정을 면밀히 보여줄 수 있어야 한다. Java의 BCI 기술을 사용하여 이러한 정보를 저장하고 가져올 것 이다.  
어떠한 정보를 저장하고 보여주는지에 대한 과정을 공부하면서 JavaAgent에 필요한 기능을 배우고 또한 이를 발전시켜 사용자가 쉅게 프로그래밍 튜닝을 하도록 한다. 내부적인 구조는 JVM에 같이 로딩되어 사용자 프로그램을 조사하고 전체적인 통계자료를 보여주는 방식과 특정 구간만을 분석하여 이를 표현하는 방식을 보여줄 것이다. 
사용자의 프로그램과 같이 로딩되는 경우 이를 새로운 창을 띄워서 이러한 데이터 정보를 표기한다. 그리고 특정 구간만을 분석하는 경우 어떤 구간의 함수를 지정하여 성능을 분석할 수 있도록한다. 
마지막으로 데이터간의 관계성을 넣어서 함수가 어떤 클래스의 함수인지 저장해야 되며 어떠한 방식으로 동작하는지 명확히 표현할수 있어야한다. 표현 방식은 아래와 같을 예정이다. 추후 쓰래드를 분석하여 Call-Stack을 찍어 사용자가 원하는 쓰래드를 분석하여 표현하도록 한다. 

2주 마다 핵심 기능을 구현한 프로토타입을 만들고 이를 기록한다.

TODO / Roadmap (Detail in [Notion](https://living-light-8ce.notion.site/Jennifer-Intern-c1a21d02736c4d5ea6785bb24cff3e0a))

- 2주차(1.0) : [ClassFile Filter](https://git.jennifersoft.com:6443/rnd/agent-slave/-/tree/1.0)  
  
  - 클래스 파일의 변수 및 함수 바이트 코드를 분석해서 이를 메모리에 저장한다. 


- 4주차(1.1) : [Profiling Dynamic](https://sourceware.org/binutils/docs/gprof/Call-Graph.html) 메소드 단위 프로파일링
  - 메소드를 호출하는 클래스를 누군지 알아야 한다. 
  - 메소드의 호출횟수, 실행 시간 및 메소드내의 바이트 코드 파악 
  -  UI 설계 및 제작 


- 6주차(1.2) : [AgentSlave]() 
  - UI 제작 완성
    - Searching and Filtering 
    - UI 분리
  - Stack Tracing 
  - UI 설명서 제작 
  

- 8주차(1.3) : [AgentSlave]()
  - Thread 단위 
  - Client 분리 
  - 코드 리뷰 및 최적화 

---

### 파일 구조 


```linux 
.
├── CodeSample  => Testing code 작성용
├── README.md  
├── TestCase.jar  => 분석할 Java program ( Spring Demo)  
└── agent-slave  => Java profiler
    ...
       └──main
            ├── Entity  => data Entitiy  
            ├── Java_agent  => java Agnet 
            ├── META-INF => 
            ├── Type  => Opcodes to String hash   
            ├── Util  =>  Log, Printer , Filter ..etc
            └── repo  => Memory data controller
```

### 방식

---

- 개발 방식
  최소한의 핵심적인 기능만을 구현하고 여기서 이슈가 생기면 이를 매주 금요일 마다 알버트와 이야기 하면서 코드 리뷰를 하고 설계의 내용을 보강하면서 진행한다.
  매일마다 노션의 프로젝트 보드에 진행과정을 등록하며 코드의 생산성을 높이고 이를 평가하며 보안해 나갈 것이다. 보드에 Progress에는 3개를 넘지 않으며 이번주에 할 일정은 Todo에 담아 둔다.  
  그 외에 추가로 알아보고 기능은 Backlog에 기록한다. 그외에 추가로 그 외에 프로젝트 중 리서치한 자료를 노션에 정리하며예제 코드 또한 같이 깃랩에 푸시 한다.


- 개발 핵심 라이브러리

  [ASM](https://asm.ow2.io/)
    
  [Java Instrumentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.instrument/java/lang/instrument/Instrumentation.html)


- 내용 정리 및 전체적인 진행사항 
  - [Notion](https://living-light-8ce.notion.site/Jennifer-Intern-c1a21d02736c4d5ea6785bb24cff3e0a)
    
    - [ASM](https://living-light-8ce.notion.site/ASM-212f66937e974bf2ae0ab4d6ca73367b)

    - [Instrumetation](https://living-light-8ce.notion.site/Instrumentation-bd109436aed04f9bbd20f73230032da8)

    - [wikipedica](https://living-light-8ce.notion.site/Wikipedica-eng-3e57140d301c4893a7962bd96aa71a57)

  



### Agent-slave 실행 방법

---
```linux
  cd agent-slave 
  java -javaagent:app/build/libs/app.jar -Dspring.main.banner-mode=off -Dlogging.pattern.console= -jar TestCase.jar
```
### Usage
- Dectect mode : 3초마다 지금까지 실행된 메소드들 감지 및 실행시간을 계산하여 Average Time 순으로 정렬하여 사용자에게 보여준다. Ctrl + c 입력시 Class 검색 모드로 갈 수 있다.
  - Method Name : 함수 이름
  - Class Name : 해당 함수 클래스
  - Package Name : 해당 클래스 패키지
  - Count : 호출 횟수
  - Average : 평균 호출 시간(누적 호출 시간/ 횟수)
  - Cultiv : 누적 호출 시간


- ClassSearch mode : 현재까지 로드된 클래스들을 볼 수 있다. skip 입력시 다시 Dectect mode로 변환 , exit 시 프로그램 종료
  - 로딩된 클래스목록이 나오고 사용자가 원하는 클래스를 입력하여 이에 세부 정보 관찰 가능
  - 프로그램 종료 및 Dectect mode로 전환 가능

  #### Output Sample
  ```linux
    Sorting by Average CallTime(Dectect Mode)
    **************************************************************************************************************************************************************************************************************
    Method Name                       ClassName                                                         PackageName                                        Count        Average CallTime(ms)        CultivTime(ms)
    **************************************************************************************************************************************************************************************************************
    main                              SpringBootDemoApplication                                         com/jennifersoft/springbootdemo/                   1            4774ms                      4774ms
    CGLIB$STATICHOOK1                 SpringBootDemoApplication$$EnhancerBySpringCGLIB$$5aaa731b        com/jennifersoft/springbootdemo/                   1            4ms                         4ms
    configureSSL                      SslConfig                                                         com/jennifersoft/springbootdemo/config/            1            1ms                         1ms
    CGLIB$STATICHOOK2                 SpringBootDemoApplication$$EnhancerBySpringCGLIB$$5aaa731b        com/jennifersoft/springbootdemo/                   1            1ms                         1ms
    ping                              IndexController                                                   com/jennifersoft/springbootdemo/controller/        21           0ms                         0ms
    CGLIB$STATICHOOK4                 SslConfig$$EnhancerBySpringCGLIB$$c70eec83                        com/jennifersoft/springbootdemo/config/            1            0ms                         0ms
    getContent                        HealthCheckResult                                                 com/jennifersoft/springbootdemo/                   18           0ms                         0ms
    setBeanFactory                    SpringBootDemoApplication$$EnhancerBySpringCGLIB$$5aaa731b        com/jennifersoft/springbootdemo/                   2            0ms                         1ms
    getaccessFlag                     AccessType                                                        Type/                                              3            0ms                         0ms
    CGLIB$BIND_CALLBACKS              SpringBootDemoApplication$$EnhancerBySpringCGLIB$$5aaa731b        com/jennifersoft/springbootdemo/                   1            0ms                         0ms
    redirectToApiDoc                  IndexController                                                   com/jennifersoft/springbootdemo/controller/        16           0ms                         2ms
    getStatusCode                     HealthCheckResult                                                 com/jennifersoft/springbootdemo/                   18           0ms                         0ms
    CGLIB$STATICHOOK3                 SslConfig$$EnhancerBySpringCGLIB$$c70eec83                        com/jennifersoft/springbootdemo/config/            1            0ms                         0ms
    CGLIB$SET_STATIC_CALLBACKS        SpringBootDemoApplication$$EnhancerBySpringCGLIB$$5aaa731b        com/jennifersoft/springbootdemo/                   1            0ms                         0ms
    getHealth                         HealthCheckResult                                                 com/jennifersoft/springbootdemo/                   18           0ms                         0ms
    getUri                            HealthCheckResult                                                 com/jennifersoft/springbootdemo/                   18           0ms                         0ms
    setHealth                         HealthCheckResult                                                 com/jennifersoft/springbootdemo/                   18           0ms                         0ms
  ```

  ```linux
    ClassSearch mode
    com/jennifersoft/springbootdemo/SpringBootDemoApplication
    com/jennifersoft/springbootdemo/config/SslConfig
    com/jennifersoft/springbootdemo/controller/IndexController
    com/jennifersoft/springbootdemo/SpringBootDemoApplication$$EnhancerBySpringCGLIB$$cba829fd
    com/jennifersoft/springbootdemo/config/SslConfig$$EnhancerBySpringCGLIB$$380ca365
    org/ietf/jgss/GSSException
    com/jennifersoft/springbootdemo/HealthCheckResult
    
    Writing ClassName(write skip will be auto skip)(exit to end program)
    
    com/jennifersoft/springbootdemo/controller/IndexController
    ************************
    
    Access : [PUBLIC, TRANSITIVE]
    Class name: com/jennifersoft/springbootdemo/controller/IndexController
    Source name: IndexController.java
    Major Version: 52
    Super class: java/lang/Object
    
    Method_value
                          access                      name                                                                             desc
                        [PUBLIC]          redirectToApiDoc  (Lorg/springframework/web/servlet/mvc/support/RedirectAttributes;)Lorg/springframework/web/servlet/view/RedirectView;
                        [PUBLIC]                      ping                                       ()Lorg/springframework/http/ResponseEntity;
  ```
  
