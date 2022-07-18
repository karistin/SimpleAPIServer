

# Java asm Profiler and Instrumenter

목표

---

Agent Slave는 인턴쉅(7~8)기간 동안 제작하는 Java 프로그램이 실행되는 동안 프로그램의 함수 호출 빈도와 사용량을 분석 하는 동적 프로그램이다. 

프로파일링 방식은 ASM을 사용하여 ByteCode를 측정하며 이를 통해 프로그램의 과부화 영역을 확인하고 최적화와 엔지니어링에 도움을 줄 수 있다. 

방식

---

- 개발 방식
    
    최소한의 핵심적인 기능만을 구현하고 여기서 이슈가 생기면 이를 매주 금요일 마다 알버트와 이야기 하면서 코드 리뷰를 하고 설계의 내용을 보강하면서 진행한다. 

  매일마다 노션의 프로젝트 보드에 진행과정을 등록하며 코드의 생산성을 높이고 이를 평가하며 보안해 나갈 것이다. 보드에 Progress에는 3개를 넘지 않으며 이번주에 할 일정은 Todo에 담아 둔다. 
   
    그 외에 추가로 알아보고 기능은 Backlog에 기록한다. 그외에 추가로 그 외에 프로젝트 중 리서치한 자료를 노션에 정리하며(짧게 정리하자!!) 예제 코드 또한 같이 깃랩에 푸시 한다.      
    

- 개발 라이브러리
    
    ASM
    
    Java Instrumentation

    
- 정리한 내용
    
    - ASM
        - **ASM 공식 문서 정리 모음**
            
            [ASM Library GuideLinech.1 ](https://www.notion.so/ASM-Library-GuideLinech-1-f06545a4e7164fb2a8fd5931954eb402)
            
            [ASM Library GuideLine ch.2](https://www.notion.so/ASM-Library-GuideLine-ch-2-dc09a2b177b640bca2660197fdf3801b)

        [java asm](https://www.notion.so/java-asm-2699a6d241304a358f60fc316c3778bf)
        
        [AST](https://www.notion.so/AST-1c72603ff17c4dde8a97d8140255d16c)

    - Instrumetation
        
        [Instrumentation](https://www.notion.so/Instrumentation-bd109436aed04f9bbd20f73230032da8)
        
        [**Guide to Java Instrumentation**](https://www.notion.so/Guide-to-Java-Instrumentation-facd144d5c284f95b015dcdf98502902)
        
        [jvm](https://www.notion.so/jvm-025e6117c25a42e48181820f703e58ec)
        
    - wikipedica
        
        [Profiling](https://www.notion.so/Profiling-e5eb2f7a0197406195478dafd62b7860)

    [참고 문헌](https://www.notion.so/2ff3c550eb174c18a90de2aa042ffe32)
    
  - Notion
    https://living-light-8ce.notion.site/Jennifer-Intern-c1a21d02736c4d5ea6785bb24cff3e0a
  
Agent-slave 실행 방법

---