package service.auth; // Update the package declaration

import service.auth.util.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtService {

    private static JwtService instance;
    private JwtUtil jwtUtil;

    private JwtService() {
        this.jwtUtil = new JwtUtil(); // 실제 구현에서는 주입 받거나 다른 방식으로 초기화할 수 있습니다.
    }

    // 싱글턴 인스턴스 반환 메서드
    public static JwtService getInstance() {
        if (instance == null) {
            synchronized (JwtService.class) {
                if (instance == null) {
                    instance = new JwtService();
                }
            }
        }
        return instance;
    }

    // JWT 생성 메서드
    public String generateToken(UserDetails userDetails) {
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    // JWT 검증 메서드
    public boolean validateToken(String token, UserDetails userDetails) {
        return jwtUtil.validateToken(token, userDetails.getUsername());
    }
}
