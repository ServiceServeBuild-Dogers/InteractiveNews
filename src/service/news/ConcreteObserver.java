package service.news;

public class ConcreteObserver implements SubscribeObserver{

    private String username;

    public ConcreteObserver(String username) {
       this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    @Override
    public void update(boolean isActive) {
        System.out.println(username + (isActive ? ": 구독한 사용자입니다." : " : 구독하지 않은 사용자입니다."));

    }
}
