package service.auth;

public class OAuthStrategy implements AuthStrategy {
    @Override
    public boolean authenticate(String username, String password) {
        return true; // OAuth 인증 로직 구현
    }
}
