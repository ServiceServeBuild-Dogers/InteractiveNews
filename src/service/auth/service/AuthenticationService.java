package service.auth.service;
import service.auth.Authentication; // Import the missing Authentication interface


public class AuthenticationService implements Authentication {
    private static AuthenticationService instance;
    private AuthStrategy authStrategy;
    private AuthFactory authFactory;
    
    private AuthenticationService() {
        this.authFactory = new ConcreteAuthFactory(); // 구체적인 팩토리 클래스
        this.authStrategy = authFactory.createAuthStrategy("Default");
    }
    public static AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }
    
    @Override
    public boolean authenticate(String username, String password) {
        return authStrategy.authenticate(username, password);
    }
    
    public boolean verifyToken(String token, String username) {
        // 토큰 검증 로직
        return true; 
    }
}

// AuthFactory 인터페이스
interface AuthFactory {
    AuthStrategy createAuthStrategy(String type);
}

// 구체적인 팩토리 클래스
class ConcreteAuthFactory implements AuthFactory {
    @Override
    public AuthStrategy createAuthStrategy(String type) {
        switch (type) {
            case "Default":
                return new DefaultAuthStrategy();
            case "OAuth":
                return new OAuthStrategy();
            default:
                throw new IllegalArgumentException("Unknown authentication type: " + type);
        }
    }
}

// 인증 전략 인터페이스
interface AuthStrategy {
    boolean authenticate(String username, String password);
}

// 기본 인증 전략
class DefaultAuthStrategy implements AuthStrategy {
    @Override
    public boolean authenticate(String username, String password) {
        // 기본 인증 로직 구현
        return true;
    }
}

// OAuth 인증 전략
class OAuthStrategy implements AuthStrategy {
    @Override
    public boolean authenticate(String username, String password) {
        // OAuth 인증 로직 구현
        return true;
    }
}

