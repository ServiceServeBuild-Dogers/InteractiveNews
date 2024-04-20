package service.auth.controller;

import service.auth.service.AuthenticationService;

public class UserController {

    private AuthenticationService authService = AuthenticationService.getInstance();

    // 사용자 인증을 수행하고 JWT를 반환하는 메서드
    public String loginUser(String username, String password) {
        return authService.authenticate(username, password);
    }

    // JWT 검증 메서드
    public Boolean verifyUserToken(String token, String username) {
        return authService.verifyToken(token, username);
    }

    // 사용자 정보 조회 메서드
    public String getUserInfo(String username) {
        // 사용자 정보 조회 로직
        // 이 예제에서는 간단한 문자열을 반환합니다.
        return "User Info for " + username;
    }
}
