package service.auth.jwt.util;

import java.util.Date;
import java.util.function.Function;

public class JwtUtil {

    private String secretKey = "SECRET"; // 비밀 키 (원래는 환경 변수 등으로 숨겨야 함)

    private class Claims {
        // Dummy class for Claims

        public String getSubject(Object obj) {
            // Logic to extract subject from claims
            return "subject";
        }
    }

    public String generateToken(String username) {
        long expirationTime = 1000 * 60 * 60 * 10; // 토큰 유효 시간 (10시간)
        // Generate token logic here
        return "generated_token";
    }

    public Boolean validateToken(String token, String username) {
        final Claims claims = new Claims(); // Create an instance of Claims
        final String extractedUsername = extractClaim(token, claims::getSubject); // Change method reference to instance method call
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        // Logic to check if the token is expired
        return false; // Replace with actual implementation
    }


    private <T> T extractClaim(String token, Function<Object, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        // Logic to extract all claims from the token
        return new Claims();
    }
}
