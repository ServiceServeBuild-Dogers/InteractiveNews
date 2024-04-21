package service.auth.service;
import service.auth.Authentication;
import service.auth.AuthFactory;
import service.auth.AuthStrategy;
import service.auth.ConcreteAuthFactory;
import service.auth.AuthDecorator; // 데코레이터 클래스를 임포트

public class AuthenticationService implements Authentication {
    private static AuthenticationService instance;
    private AuthStrategy authStrategy;
    private AuthFactory authFactory;
    private Authentication authDecorator;  // Authentication 인터페이스를 사용한 데코레이터

    private AuthenticationService() {
        this.authFactory = new ConcreteAuthFactory(); // 구체적인 팩토리 클래스 사용
        this.authStrategy = authFactory.createAuthStrategy("Default"); // 기본 전략 설정
        this.authDecorator = new AuthDecorator(authStrategy);  // 기본 데코레이터 사용하여 멤버 변수에 할당
    }

    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authDecorator.authenticate(username, password); // 데코레이터를 사용한 인증
    }

    public boolean verifyToken(String token, String username) {
        // 토큰 검증 로직
        return true; // 이 부분은 추가 구현이 필요할 수 있습니다
    }
}
