
##  Java asm Profiler and Instrumenter  

---

자바 asm을 이용한 클래스 바이트 코드 분석 및 Profiling  

notion:
https://living-light-8ce.notion.site/Java-ASM-c1a21d02736c4d5ea6785bb24cff3e0a
---

### 개발 환경 

1. git clone 
2. JDK > 11(추후 8로 수정)
3. gradle > 
4. intellij setting >

---

### 실행 

```linux
jennifer>>> JAVA -jar agent.class 

FILE SERCHING 
[====================================-----]
complete! 
file list:
  Java_agent/SoccerPlayer
  Java_agent/KoreaSoccerPlayer
  Java_agent/App
  ......  
  >> SoccerPlayer(Enter)

```

---


### cli result

```linux
class name: Java_agent/SoccerPlayer
  Source name: SoccerPlayer.java
  Major Version: 55
  Super class: java/lang/Object
  interface: 
    Java_agent/Soccer
    Java_agent/Soccer2
  Field_value: 
    value:name [Ljava/lang/String;, null]
    value:num [I, null]
  Method_value: 
     method name:<init> (Ljava/lang/String;I)V
     method name:setName (Ljava/lang/String;)V
      INVOKESPECIAL Java_agent/SoccerPlayer.setName (Ljava/lang/String;)V
     method name:setNum (I)V
      INVOKESPECIAL Java_agent/SoccerPlayer.setNum (I)V
     method name:getName ()Ljava/lang/String;
     method name:getNum ()I

```

---


###  개발 일정 (8 weeks 7/4 ~ 8/31)
각 주의 마지막 날 마다 리뷰 


Feature List

- [ ] make CLI program
  - apache cli
- [ ] make agent using Instrumenter
  - java agent
- [ ] make UML GUI


1주차  
    
    asm를 활용한 bytecode 분석 및 데이터 셋 만들기
- [X] java grammar 
- [ ] Jvm file format study  
- [X] study how to use asm
  - vistmethod 처리 
- [ ] class bytecode analyze
    - [X] class_info
    - [X] field_info
    - [ ] method_info
- [X] make data set  


2주차


---

참고 자료 모음집 
https://visualvm.github.io/
