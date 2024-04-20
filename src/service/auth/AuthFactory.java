package service.auth;

import service.auth.service.AuthenticationService;

public class AuthFactory {

    // AuthServiceFactory를 통해 AuthenticationService 인스턴스를 생성하여 반환합니다.
    // 필요한 경우 인증 방식을 변경하거나 다른 인증 서비스로 교체하기 쉽습니다.
    public static AuthenticationService getAuthService() {
        return AuthenticationService.getInstance();
    }
}
