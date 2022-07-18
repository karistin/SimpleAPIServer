

# Java asm Profiler and Instrumenter

### 목표

---

Agent Slave는 인턴쉅(7~8)기간 동안 제작하는 Java 프로그램이 실행되는 동안 프로그램의 함수 호출 빈도와 사용량을 분석 하는 동적 프로그램이다. 

프로파일링 방식은 ASM을 사용하여 ByteCode를 측정하며 이를 통해 프로그램의 과부화 영역을 확인하고 최적화와 엔지니어링에 도움을 줄 수 있다. 

---
### 파일 구조 


```linux 
.
├── CodeSample  코드 테스트 샘플용
├── README.md  
├── TestCase.jar  분석할 java program  
└── agent-slave  java profiler
    ...
       └──main
            ├── Entity
            ├── Java_agent
            ├── META-INF
            ├── Package
            ├── Sample
            ├── Temp
            └── repo
```

### 방식

---

- 개발 방식
    
    최소한의 핵심적인 기능만을 구현하고 여기서 이슈가 생기면 이를 매주 금요일 마다 알버트와 이야기 하면서 코드 리뷰를 하고 설계의 내용을 보강하면서 진행한다. 

    매일마다 노션의 프로젝트 보드에 진행과정을 등록하며 코드의 생산성을 높이고 이를 평가하며 보안해 나갈 것이다. 보드에 Progress에는 3개를 넘지 않으며 이번주에 할 일정은 Todo에 담아 둔다. 
   
    그 외에 추가로 알아보고 기능은 Backlog에 기록한다. 그외에 추가로 그 외에 프로젝트 중 리서치한 자료를 노션에 정리하며(짧게 정리하자!!) 예제 코드 또한 같이 깃랩에 푸시 한다.      
    

- 개발 핵심 라이브러리

  [ASM](https://asm.ow2.io/)
    
  [Java Instrumentation](https://docs.oracle.com/en/java/javase/11/docs/api/java.instrument/java/lang/instrument/Instrumentation.html)

    
- 정리한 내용
    
    - [ASM](https://living-light-8ce.notion.site/ASM-212f66937e974bf2ae0ab4d6ca73367b)

    - [Instrumetation](https://living-light-8ce.notion.site/Instrumentation-bd109436aed04f9bbd20f73230032da8)
 
    - [wikipedica](https://living-light-8ce.notion.site/Wikipedica-eng-3e57140d301c4893a7962bd96aa71a57)

    - [Notion](https://living-light-8ce.notion.site/Jennifer-Intern-c1a21d02736c4d5ea6785bb24cff3e0a)
  



### Agent-slave 실행 방법

---