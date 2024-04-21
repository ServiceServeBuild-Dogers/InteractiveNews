import service.news.ConcreteObserver;

public class Client {
    private String username;
    private String password;
    private String token;
    private String userPreferences;
    private String selectType;
    private ConcreteObserver observer;

    // Client 생성자
    public Client(String username, String password, String token, String userPreferences, String selectType) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.userPreferences = userPreferences;
        this.selectType = selectType;
        this.observer = new ConcreteObserver(username);
    }

    // Getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public String getUserPreferences() {
        return userPreferences;
    }

    public String getSelectType() {
        return selectType;
    }

    public ConcreteObserver getObserver() {
        return observer;
    }

    // Setter methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserPreferences(String userPreferences) {
        this.userPreferences = userPreferences;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public void setObserver(ConcreteObserver observer) {
        this.observer = observer;
    }
}
