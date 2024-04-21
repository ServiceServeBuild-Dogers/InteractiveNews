## 인증 서비스: AuthenticationService 클래스 설명

- AuthenticationService는 인증 서비스를 위한 싱글턴 객체로, 플랫폼에서 호출되어 다양한 인증 전략을 통해 사용자 인증을 관리합니다. 이 서비스는 사용자의 인증 요청을 처리하고, 토큰 기반 검증을 포함할 수 있습니다.
### 주요 기능 및 메서드:
1. Singleton Pattern Implementation:
    - getInstance(): 이 메서드는 클래스의 인스턴스를 반환합니다. 싱글턴 패턴을 사용하여 클래스의 인스턴스가 하나만 생성되도록 보장합니다.
2. Factory Pattern:
    - createAuthStrategy(String type): 인증 전략을 동적으로 생성합니다. 이 메서드는 AuthFactory 인터페이스를 통해 다양한 인증 전략(Default, OAuth)을 생성할 수 있도록 합니다.
3. Strategy Pattern:
    - 인증 전략 (AuthStrategy): 다양한 인증 방식을 추상화하고, 구체적인 인증 방식은 별도의 클래스(DefaultAuthStrategy, OAuthStrategy)에서 구현합니다. 이를 통해 사용자 인증 로직을 유연하게 변경할 수 있습니다.
4. Decorator Pattern:
    - AuthDecorator: Authentication 인터페이스를 구현하며, AuthStrategy 객체를 Authentication으로 변환하는 AuthStrategyAdapter를 사용하여 데코레이터 패턴을 적용합니다. 이 패턴을 통해 추가적인 기능 (예: 로깅, 유효성 검증)을 동적으로 추가할 수 있습니다.
### 구성 요소:
- AuthFactory: 인증 전략 객체를 생성하는 팩토리 인터페이스입니다. 이를 통해 필요에 따라 다양한 인증 전략 객체를 생성할 수 있습니다.
- AuthStrategy: 인증을 수행하는 전략을 정의하는 인터페이스입니다. 다양한 전략을 구현하여, 사용자의 인증 요청을 처리합니다.
- ConcreteAuthFactory: AuthFactory 인터페이스의 구현체로, 실제로 인증 전략을 생성하는 클래스입니다.
- DefaultAuthStrategy, OAuthStrategy: 각각 기본 인증과 OAuth 인증을 구현한 전략 클래스입니다.
- AuthDecorator: AuthStrategy를 Authentication 인터페이스로 적응시키는 데코레이터 클래스입니다.
- AuthStrategyAdapter: AuthStrategy를 Authentication 인터페이스로 변환하는 어댑터 클래스입니다.

### 디자인 패턴 적용


1. 싱글톤 패턴 - AuthenticationService
의도와 타당성:
인증 서비스는 시스템 내에서 중요한 보안 관련 작업을 중앙에서 처리합니다. 이 서비스는 사용자의 인증 상태를 관리하고, 보안 토큰을 발급 및 검증하는 데 필요한 민감한 정보와 알고리즘을 포함합니다. 싱글톤 패턴을 사용하면 인증 서비스 인스턴스가 한 번만 생성되고 애플리케이션의 어디서든 이 인스턴스에 접근할 수 있습니다. 이로써 모든 인증 요청이 일관된 상태에서 처리되어 보안의 일관성을 유지할 수 있습니다. 또한, 싱글톤 패턴은 중복 인스턴스 생성을 방지하여 메모리 사용을 최적화하고, 인증 로직에 대한 변경이 필요할 때 즉각적으로 적용할 수 있는 중앙 집중식 관리의 이점을 제공합니다. 이는 시스템의 보안 정책을 효과적으로 유지관리 할 수 있는 환경을 조성합니다.

2. 팩토리 패턴 - AuthFactory와 ConcreteAuthFactory
의도와 타당성:
인증 방식은 시스템 요구사항과 외부 환경에 따라 다양하게 변경될 수 있습니다. 팩토리 패턴을 적용하여 AuthFactory 인터페이스와 이를 구현하는 ConcreteAuthFactory 클래스를 도입함으로써, 인증 전략을 유연하게 교체할 수 있는 구조를 마련했습니다. 이 구조는 AuthStrategy를 생성하는 책임을 갖고, 필요에 따라 "Default", "OAuth" 등 다양한 인증 전략을 생성할 수 있습니다. 이 패턴의 사용은 새로운 인증 방식의 추가나 기존 방식의 수정을 간단히 할 수 있게 하며, 인증 로직의 변경이 시스템의 다른 부분에 미치는 영향을 최소화합니다. 결과적으로, 애플리케이션의 확장성과 유지보수성이 크게 향상됩니다.

3. 전략 패턴 - AuthStrategy
의도와 타당성:
전략 패턴은 인증 서비스에서 다양한 인증 전략을 캡슐화하고, 실행 중에 이들을 교체할 수 있게 합니다. 이는 AuthStrategy 인터페이스를 통해 구현되며, DefaultAuthStrategy 및 OAuthStrategy와 같은 다양한 인증 메커니즘을 동적으로 선택할 수 있도록 합니다. 이 패턴의 적용은 인증 서비스가 다양한 사용자 환경과 보안 요구 사항에 탄력적으로 대응할 수 있게 해줍니다. 예를 들어, 보안 요구 사항이 변경되거나 새로운 인증 기술이 도입되어도 기존 코드를 전면적으로 수정하지 않고도 해당 인증 전략만 교체하거나 추가하면 됩니다. 이는 시스템의 유연성을 극대화하며, 장기적으로 기술 부채를 줄이는 효과를 가져옵니다.

4. 데코레이터 패턴 (AuthDecorator):
의도와 타당성: 인증 프로세스에 추가적인 기능을 동적으로 삽입할 수 있도록 합니다. 이 패턴은 시스템의 확장성을 향상시키며, 새로운 기능을 쉽게 추가하거나 기존 기능을 변경할 수 있는 유연성을 제공합니다.

### 사용 예시:
```java
public static void main(String[] args) {
    // 인증 서비스 인스턴스 획득
    AuthenticationService authService = AuthenticationService.getInstance();

    // 플랫폼에서 AuthDecorator를 사용해 기능 확장
    AuthStrategy basicStrategy = authService.createAuthStrategy("Default");
    AuthStrategy adaptedStrategy = new AuthStrategyAdapter(basicStrategy);
    authService.setAuthentication(new AuthDecorator(adaptedStrategy));

    // 사용자 인증 시도
    boolean isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status: " + isAuthenticated);

    // OAuth 인증 전략으로 변경
    authService.createAuthStrategy("OAuth");
    adaptedStrategy = new AuthStrategyAdapter(authService.getAuthStrategy());
    authService.setAuthentication(new AuthDecorator(adaptedStrategy));

    // OAuth를 사용한 사용자 인증 시도
    isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status with OAuth: " + isAuthenticated);
}

```