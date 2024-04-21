import service.news.ConcreteObserver;

public class Client {
    private String username;
    private String password;
    private String token;
    private String userPreferences;
    private String selectCategory;
    private String searchKeyword;


    private ConcreteObserver observer;

    // Client 생성자
    public Client(String username, String password, String token, String userPreferences) {
        this.username = username;
        this.password = password;
        this.token = token;
        this.userPreferences = userPreferences;
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

    public String getSelectCategory() {
        return selectCategory;
    }

    public ConcreteObserver getObserver() {
        return observer;
    }

    public String getSearchKeyword() {
        return searchKeyword;
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

    public void setSelectCategory(String selectCategory) {
        this.selectCategory = selectCategory;
    }

    public void setObserver(ConcreteObserver observer) {
        this.observer = observer;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
