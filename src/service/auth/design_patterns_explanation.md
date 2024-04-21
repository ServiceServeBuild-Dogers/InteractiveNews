## 인증 서비스: AuthenticationService 클래스 설명

- AuthenticationService는 인증 서비스를 위한 싱글턴 객체로, 플랫폼에서 호출되어 다양한 인증 전략을 통해 사용자 인증을 관리합니다. 이 서비스는 사용자의 인증 요청을 처리하고, 토큰 기반 검증을 포함할 수 있습니다.
### 주요 기능 및 메서드:
1. Singleton Pattern Implementation:
    - getInstance(): 이 메서드는 클래스의 인스턴스를 반환합니다. 싱글턴 패턴을 사용하여 클래스의 인스턴스가 하나만 생성되도록 보장합니다.
2. Factory Pattern:
    - createAuthStrategy(String type): 인증 전략을 동적으로 생성합니다. 이 메서드는 AuthFactory 인터페이스를 통해 다양한 인증 전략(Default, OAuth)을 생성할 수 있도록 합니다.
3. Strategy Pattern:
    - 인증 전략 (AuthStrategy): 다양한 인증 방식을 추상화하고, 구체적인 인증 방식은 별도의 클래스(DefaultAuthStrategy, OAuthStrategy)에서 구현합니다. 이를 통해 사용자 인증 로직을 유연하게 변경할 수 있습니다.
### 구성 요소:
- AuthFactory: 인증 전략 객체를 생성하는 팩토리 인터페이스입니다. 이를 통해 필요에 따라 다양한 인증 전략 객체를 생성할 수 있습니다.
- AuthStrategy: 인증을 수행하는 전략을 정의하는 인터페이스입니다. 다양한 전략을 구현하여, 사용자의 인증 요청을 처리합니다.
- ConcreteAuthFactory: AuthFactory 인터페이스의 구현체로, 실제로 인증 전략을 생성하는 클래스입니다.
- DefaultAuthStrategy, OAuthStrategy: 각각 기본 인증과 OAuth 인증을 구현한 전략 클래스입니다.
### 사용 예시:
```java
public static void main(String[] args) {
    // 인증 서비스 인스턴스 획득
    AuthenticationService authService = AuthenticationService.getInstance();

    // 기본 인증 전략 설정
    authService.createAuthStrategy("Default");

    // 사용자 인증 시도
    boolean isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status: " + isAuthenticated);

    // OAuth 인증 전략으로 변경
    authService.createAuthStrategy("OAuth");

    // OAuth를 사용한 사용자 인증 시도
    isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status with OAuth: " + isAuthenticated);
}
```