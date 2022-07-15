
##  Java asm Profiler and Instrumenter  

---

자바 asm을 이용한 클래스 바이트 코드 분석 및 Profiling  

notion:
https://living-light-8ce.notion.site/Java-ASM-c1a21d02736c4d5ea6785bb24cff3e0a
---

### 개발 환경 


---



### cli result

```linux
Access : [PUBLIC, FINAL, TRANSITIVE]
Class name: java/lang/String
Source name: null
Major Version: 55
Super class: java/lang/Object
interface
  java/io/Serializable
  java/lang/Comparable
  java/lang/CharSequence
Field_value
                        access                      name                                     desc                          value
              [PRIVATE, FINAL]                     value                                       [B                           null
              [PRIVATE, FINAL]                     coder                                        B                           null
                     [PRIVATE]                      hash                                        I                           null
      [PRIVATE, STATIC, FINAL]          serialVersionUID                                        J           -6849794470754667710
               [STATIC, FINAL]           COMPACT_STRINGS                                        Z                           null
      [PRIVATE, STATIC, FINAL]    serialPersistentFields             [Ljava/io/ObjectStreamField;                           null
       [PUBLIC, STATIC, FINAL]    CASE_INSENSITIVE_ORDER                   Ljava/util/Comparator;                           null
               [STATIC, FINAL]                    LATIN1                                        B                              0
               [STATIC, FINAL]                     UTF16                                        B                              1

Method_value
                        access                      name                                                                             desc 
             [PRIVATE, STATIC]                rangeCheck                                                           ([CII)Ljava/lang/Void; 
                      [PUBLIC]                    length                                                                              ()I 
                      [PUBLIC]                   isEmpty                                                                              ()Z 
                      [PUBLIC]                    charAt                                                                             (I)C 
                      [PUBLIC]               codePointAt                                                                             (I)I 
                      [PUBLIC]           codePointBefore                                                                             (I)I 
                      [PUBLIC]            codePointCount                                                                            (II)I 
                      [PUBLIC]        offsetByCodePoints                                                                            (II)I 
                      [PUBLIC]                  getChars                                                                         (II[CI)V 
                      [PUBLIC]                  getBytes                                                                         (II[BI)V 
                      [PUBLIC]                  getBytes                                                           (Ljava/lang/String;)[B 
                      [PUBLIC]                  getBytes                                                   (Ljava/nio/charset/Charset;)[B 
                      [PUBLIC]                  getBytes                                                                             ()[B 
                      [PUBLIC]                    equals                                                            (Ljava/lang/Object;)Z 
                      [PUBLIC]             contentEquals                                                      (Ljava/lang/StringBuffer;)Z 
                     [PRIVATE]      nonSyncContentEquals                                             (Ljava/lang/AbstractStringBuilder;)Z 
                      [PUBLIC]             contentEquals                                                      (Ljava/lang/CharSequence;)Z 
                      [PUBLIC]          equalsIgnoreCase                                                            (Ljava/lang/String;)Z 
                      [PUBLIC]                 compareTo                                                            (Ljava/lang/String;)I 
                      [PUBLIC]       compareToIgnoreCase                                                            (Ljava/lang/String;)I 
                      [PUBLIC]             regionMatches                                                         (ILjava/lang/String;II)Z 
                      [PUBLIC]             regionMatches                                                        (ZILjava/lang/String;II)Z 
                      [PUBLIC]                startsWith                                                           (Ljava/lang/String;I)Z 
                      [PUBLIC]                startsWith                                                            (Ljava/lang/String;)Z 
                      [PUBLIC]                  endsWith                                                            (Ljava/lang/String;)Z 
                      [PUBLIC]                  hashCode                                                                              ()I 
                      [PUBLIC]                   indexOf                                                                             (I)I 
                      [PUBLIC]                   indexOf                                                                            (II)I 
                      [PUBLIC]               lastIndexOf                                                                             (I)I 
                      [PUBLIC]               lastIndexOf                                                                            (II)I 
                      [PUBLIC]                   indexOf                                                            (Ljava/lang/String;)I 
                      [PUBLIC]                   indexOf                                                           (Ljava/lang/String;I)I 
                      [STATIC]                   indexOf                                                       ([BBILjava/lang/String;I)I 
                      [PUBLIC]               lastIndexOf                                                            (Ljava/lang/String;)I 
                      [PUBLIC]               lastIndexOf                                                           (Ljava/lang/String;I)I 
                      [STATIC]               lastIndexOf                                                       ([BBILjava/lang/String;I)I 
                      [PUBLIC]                 substring                                                            (I)Ljava/lang/String; 
                      [PUBLIC]                 substring                                                           (II)Ljava/lang/String; 
                      [PUBLIC]               subSequence                                                     (II)Ljava/lang/CharSequence; 
                      [PUBLIC]                    concat                                           (Ljava/lang/String;)Ljava/lang/String; 
                      [PUBLIC]                   replace                                                           (CC)Ljava/lang/String; 
                      [PUBLIC]                   matches                                                            (Ljava/lang/String;)Z 
                      [PUBLIC]                  contains                                                      (Ljava/lang/CharSequence;)Z 
                      [PUBLIC]              replaceFirst                         (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; 
                      [PUBLIC]                replaceAll                         (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String; 
                      [PUBLIC]                   replace             (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String; 
                      [PUBLIC]                     split                                         (Ljava/lang/String;I)[Ljava/lang/String; 
                      [PUBLIC]                     split                                          (Ljava/lang/String;)[Ljava/lang/String;

```

---

