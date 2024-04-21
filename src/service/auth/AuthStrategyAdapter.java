package service.auth;

public class AuthStrategyAdapter implements Authentication {
    private AuthStrategy authStrategy;

    public AuthStrategyAdapter(AuthStrategy authStrategy) {
        this.authStrategy = authStrategy;
    }

    @Override
    public boolean authenticate(String username, String password) {
        return authStrategy.authenticate(username, password); // AuthStrategy의 인증 메소드 호출
    }
}
