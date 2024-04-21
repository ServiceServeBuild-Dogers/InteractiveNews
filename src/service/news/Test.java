package service.news;

public class Test {


    public static void main(String args[]){

        NewsRecommendationService service = NewsRecommendationService.getInstance();
        ConcreteObserver observer1 = new ConcreteObserver("User1");
        ConcreteObserver observer2 = new ConcreteObserver("User2");

        // Observer 1은 구독 상태 & 추천 전략 요청
        service.subscribe(observer1);
        service.recommendNews("tech", observer1);
        service.setStrategy("UserBased");
        service.recommendNews("sports",observer1);

//        // Observer 2은 비구독 상태 & 그 상태에서 추천 요청 시도
//        service.recommendNews("technology", observer2);
//
//        // observer1이 구독 취소 & 그 상태에서 추천 요청 시도
//        service.unsubscribe(observer1);
//        service.recommendNews("technology", observer1);


    }
}
