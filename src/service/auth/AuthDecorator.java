package service.auth;

public class AuthDecorator implements Authentication {
    private Authentication wrappedAuth;

    public AuthDecorator(Authentication wrappedAuth) {
        this.wrappedAuth = wrappedAuth;
    }

    @Override
    public boolean authenticate(String username, String password) {
        boolean result = wrappedAuth.authenticate(username, password);
        if (!result) {
            System.out.println("First attempt failed for: " + username + ". Retrying...");
            result = wrappedAuth.authenticate(username, password);
        }
        return result;
    }
}
