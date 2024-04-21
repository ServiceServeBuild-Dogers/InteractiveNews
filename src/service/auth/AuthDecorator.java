package service.auth;

public class AuthDecorator implements Authentication {
    protected Authentication wrappedAuthentication;

    public AuthDecorator(AuthStrategy authStrategy) {
        this.wrappedAuthentication = new AuthStrategyAdapter(authStrategy); // AuthStrategy를 Authentication으로 변환
    }

    @Override
    public boolean authenticate(String username, String password) {
        return wrappedAuthentication.authenticate(username, password);
    }
}
