package service.auth.service;
import service.auth.util.JwtUtil;


public class AuthenticationService {

    private static AuthenticationService instance;
    private JwtUtil jwtUtil = new JwtUtil(); // JwtUtil 인스턴스

    private AuthenticationService() {}

    // 싱글턴 인스턴스 반환 메서드
    public static AuthenticationService getInstance() {
        if (instance == null) {
            synchronized (AuthenticationService.class) {
                if (instance == null) {
                    instance = new AuthenticationService();
                }
            }
        }
        return instance;
    }

    // 사용자 인증 메서드
    public String authenticate(String username, String password) {
        // 사용자 인증 로직 구현
        // 이 예제에서는 하드코딩된 사용자 이름과 비밀번호를 사용합니다.
        // 실제 구현에서는 데이터베이스 검증 로직을 추가해야 합니다.
        if ("admin".equals(username) && "password".equals(password)) {
            return jwtUtil.generateToken(username); // 인증 성공시 JWT 생성
        }
        return null; // 인증 실패시 null 반환
    }

    // JWT 토큰 검증 메서드
    public Boolean verifyToken(String token, String username) {
        return jwtUtil.validateToken(token, username);
    }
}
