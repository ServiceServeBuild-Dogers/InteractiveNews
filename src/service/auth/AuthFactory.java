package service.auth;

public interface AuthFactory {
    AuthStrategy createAuthStrategy(String type);
}
