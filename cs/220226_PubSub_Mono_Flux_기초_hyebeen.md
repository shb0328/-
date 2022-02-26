# PubSub 그리고 Mono&Flux 기초
발표일자: 2022.02.26 (토)<br>
발표자: 소혜빈

# 💚 배경지식

## 동기(synchronous)

synchronous : existing or occurring at the same time.

**동기**는 요청과 결과가 동시에 일어난다.

요청을 하면, 그 자리에서 결과가 주어져야 한다.

즉, 직접 결과를 받는다.

- 장점 : 설계가 간단하고 직관적
- 단점 : 요청한 작업이 끝날 때 까지 다른 작업을 못하고 대기

## **비동기**(**asynchronous**)

asynchronous : (of two or more objects or events) not existing or happening at the same time.

**비동기**는 요청과 결과가 동시에 일어나지 않는다.

결과는 간접적으로 받는다.

- 장점 : 요청한 작업이 실행 되는 동안 다른 작업 가능
- 단점 : 복잡한 설계

## Functional Interface

[https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html](https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html)

java 8 부터 람다식과 함께 도입

- 딱 1개의 abstract method를 가져야 한다.
- 여러 개의 default/static method 를 가질 수 있다.

`@FunctionalInterface` 어노테이션을 붙이면 컴파일 시점에 해당 interface가 Functional Interface의 조건을 만족하는 지 체크할 수 있다.
하지만 어노테이션이 없어도 위 조건이 만족하면, Functional Interface이다.

```java
@FunctionalInterface
interface MyFunctionalInterface {
    String myAbFunction(int num);

    default void printDefault() {
        System.out.println("default method");
    }
    default void printDefault2() {
        System.out.println("default method2");
    }
    static void printStatic() {
        System.out.println("this is static method!");
    }
    static void printStatic2() {
        System.out.println("this is static method2!");
    }
}
```

java에서 제공하는 FunctionalInterface 들의 종류를 잘 정리해놓은 블로그 : [https://bcp0109.tistory.com/313](https://bcp0109.tistory.com/313)

# 💚 Publisher & Subscriber

[https://www.reactive-streams.org/reactive-streams-1.0.3-javadoc/org/reactivestreams/package-summary.html](https://www.reactive-streams.org/reactive-streams-1.0.3-javadoc/org/reactivestreams/package-summary.html)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c6e63a18-d923-442e-92c7-97de1a06eb84/Untitled.png)

1. **Publisher**(생산자)가 **Subscriber**(소비자)를 **subscribe**(등록)한다.
-  Publisher는 여러개의 Subscriber를 subscribe(등록)할 수 있다. 
2. 동시에 **Subscriber**(소비자)가 **Subscription**(전달자)을 **onSubscribe**(등록)한다
3. **Subscriber**(소비자)는 필요할 때 **Subscribe**(전달자).**request**(요청)을 통해 **Publisher**에게 데이터를 요청한다.
4. **Publisher**(생산자)는 요청을 받으면 **생성한 데이터를 보낸다**
5. **Subscriber**는 `onNext`로 데이터를 받는다.
6. 모든 요청이 성공적으로 완료되면 `onComplete`을 호출하고 흐름을 종료한다.
7. 요청이 실패하면 `onError`를 호출하고 흐름을 종료한다.

# 💚 Mono

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ece3ca73-a1a6-4ea5-a232-ac1e2fefac22/Untitled.png)

[https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html)

# 💚 Flux

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d19b88e2-5159-4c75-8083-18ba6ea0524d/Untitled.png)

[https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html](https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html)


### [>> 테스트 코드](https://github.com/shb0328/react-basic-test-ex)

---

- Reference
    - [https://velog.io/@wonhee010/동기vs비동기-feat.-blocking-vs-non-blocking](https://velog.io/@wonhee010/%EB%8F%99%EA%B8%B0vs%EB%B9%84%EB%8F%99%EA%B8%B0-feat.-blocking-vs-non-blocking)
    - [https://bgpark.tistory.com/160](https://bgpark.tistory.com/160)
    - [https://projectreactor.io/docs/core/release/api/](https://projectreactor.io/docs/core/release/api/)
    - [https://docs.oracle.com/en/java/javase/17/docs/api/index.html](https://docs.oracle.com/en/java/javase/17/docs/api/index.html)
