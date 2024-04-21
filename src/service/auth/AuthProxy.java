package service.auth;

public class AuthProxy implements Authentication {
    private AuthenticationService authService;

    public AuthProxy(AuthenticationService authService) {
        this.authService = authService;
    }

    @Override
    public boolean authenticate(String username, String password) {
        // 인증 전에 수행할 로직을 추가할 수 있습니다.
        System.out.println("Authenticating user: " + username);
        // 실제 인증 처리는 AuthenticationService에 위임
        return authService.authenticate(username, password);
    }
}
