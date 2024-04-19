package service.news;

public class Test {

    public static void main(String args[]){

        NewsRecommendationService service = NewsRecommendationService.getInstance();
        ConcreteObserver observer1 = new ConcreteObserver("User1");
        ConcreteObserver observer2 = new ConcreteObserver("User2");

        // Observer 1 subscribes and requests recommendations
        service.subscribe(observer1);
        service.recommendNews("technology", observer1);

        // Observer 2 does not subscribe but attempts to request recommendations
        service.recommendNews("technology", observer2);

        // Unsubscribing observer1 and attempting to access recommendations
        service.unsubscribe(observer1);
        service.recommendNews("technology", observer1);


    }
}
