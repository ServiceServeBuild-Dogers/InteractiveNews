package service.auth;

public class AuthenticationService implements Authentication {
    private static AuthenticationService instance;
    private AuthStrategy authStrategy;
    private AuthFactory authFactory;

    private AuthenticationService() {
        this.authFactory = new ConcreteAuthFactory(); // 구체적인 팩토리 클래스 사용
        this.authStrategy = authFactory.createAuthStrategy("Default"); // 기본 전략 설정
    }

    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authStrategy.authenticate(username, password); // 전략을 사용한 인증
    }

    public boolean verifyToken(String token, String username) {
        // 토큰 검증 로직
        return true;
    }
}
