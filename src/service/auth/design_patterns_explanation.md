###AuthenticationService
이 클래스는 사용자 인증을 담당합니다. 주요 기능은 사용자의 자격 증명을 확인하고 올바른 경우 JWT 토큰을 생성하는 것입니다. 여기에는 사용자의 이름과 비밀번호를 검증하는 로직이 포함되어 있으며, 검증이 성공하면 JwtUtil을 사용해 JWT 토큰을 생성합니다.

싱글턴 패턴 적용: AuthenticationService는 싱글턴 디자인 패턴을 적용하여 애플리케이션 내에 단 하나의 인스턴스만 존재하도록 보장합니다. 이는 모든 인증 요청이 동일한 인증 서비스 인스턴스를 통해 처리되어야 함을 의미합니다. 이 방식은 인스턴스의 생성 비용이 크거나, 일관된 상태 관리가 필요할 때 유용합니다.
```java

private static AuthenticationService instance;

public static AuthenticationService getInstance() {
    if (instance == null) {
        synchronized (AuthenticationService.class) {
            if (instance == null) {
                instance = new AuthenticationService();
            }
        }
    }
    return instance;
}
```

###AuthDecorator.java
적용된 패턴: 데코레이터 패턴
설명: AuthDecorator 클래스는 Authentication 인터페이스를 구현하는 객체에 추가적인 기능을 동적으로 부여합니다. 이 패턴은 기존 코드를 수정하지 않고도 객체의 행동을 확장할 수 있게 해줍니다. 예를 들어, 로그 작성, 접근 제한, 요청 사전 처리 등이 이에 해당합니다.

```java
public class AuthDecorator implements Authentication {
    private Authentication wrappedAuth;

    public AuthDecorator(Authentication wrappedAuth) {
        this.wrappedAuth = wrappedAuth;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // 추가 기능 구현
        return wrappedAuth.authenticate(username, password);
    }
}
```
###AuthFactory.java
적용된 패턴: 팩토리 패턴
설명: AuthFactory는 AuthenticationService 객체의 생성을 캡슐화하고, 인스턴스 생성 로직을 클라이언트로부터 숨깁니다. 이 패턴은 객체 생성에 관한 유연성을 제공하며, 필요에 따라 다른 타입의 객체를 생성할 수 있는 가능성을 열어줍니다.

```java
public class AuthFactory {
    public static AuthenticationService getAuthService() {
        return AuthenticationService.getInstance();
    }
}
```
###AuthProxy.java
적용된 패턴: 프록시 패턴
설명: AuthProxy는 AuthenticationService에 대한 접근을 제어하는 프록시 역할을 합니다. 이는 보안 강화, 지연 로딩, 접근 제한 등 다양한 상황에서 유용합니다.

```java
public class AuthProxy implements Authentication {
    private AuthenticationService authService;

    public AuthProxy(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // 접근 제어나 사전 처리 로직
        return authService.authenticate(username, password);
    }
}
```
