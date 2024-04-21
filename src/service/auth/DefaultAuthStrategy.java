package service.auth;

public class DefaultAuthStrategy implements AuthStrategy {
    @Override
    public boolean authenticate(String username, String password) {
        return true; // 여기에 실제 인증 로직 구현
    }
}
