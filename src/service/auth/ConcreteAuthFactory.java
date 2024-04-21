package service.auth;

public class ConcreteAuthFactory implements AuthFactory {
    @Override
    public AuthStrategy createAuthStrategy(String type) {
        switch (type) {
            case "Default":
                return new DefaultAuthStrategy(); // 기본 인증 전략
            case "OAuth":
                return new OAuthStrategy(); // OAuth 인증 전략
            default:
                throw new IllegalArgumentException("Unknown authentication type: " + type);
        }
    }
}
