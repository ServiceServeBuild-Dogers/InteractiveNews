package service.auth;

public interface Authentication {
    boolean authenticate(String username, String password);
}
