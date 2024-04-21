package service.auth;

public interface AuthStrategy {
    boolean authenticate(String username, String password);
}
