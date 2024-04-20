package service.news;

import service.Service;
import service.news.recommendation.RecommendationStrategy;
import service.news.recommendation.TechnologyRecommendationStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Platform 에서 호출하는 싱글턴 객체 클래스
public class NewsRecommendationService implements Service {

    private static NewsRecommendationService instance;
    private RecommendationStrategy strategy;
    private Map<SubscribeObserver, Boolean> observers = new HashMap<>();
    private RecommendationStrategyFactory recommendationStrategyFactory;

    private NewsRecommendationService() {

        this.recommendationStrategyFactory = new ConcreteRecommendationStrategyFactory();

        // 초기 추천 전략 설정 - 기본 전략은 Tech
        this.strategy = recommendationStrategyFactory.createRecommendationStrategy("Technology");
    }

    // 싱글톤 사용 위함 - 한 번에 하나의 스레드에서만 호출
    public static synchronized NewsRecommendationService getInstance() {
        if (instance == null) {
            instance = new NewsRecommendationService();
        }
        return instance;
    }

    // 추천 전략 바꾸기
    public void setStrategy(String type) {
        this.strategy = recommendationStrategyFactory.createRecommendationStrategy(type);
    }


    // 구독자만 뉴스 추천 기능을 이용할 수 있음
    public void recommendNews(String userPreferences, SubscribeObserver observer) {

        if (observers.getOrDefault(observer, false)) { //observer 키가 없으면 false로 설정
            List<String> recommendations = this.strategy.recommend(userPreferences);
            System.out.println("[Recommendations for " + ((ConcreteObserver)observer).getUsername() + "] : ");
            recommendations.forEach(System.out::println);
        } else {
            System.out.println("[Access denied]. " + ((ConcreteObserver)observer).getUsername() + " : 구독하지 않은 사용자입니다.");
        }

    }

    // 구독
    public void subscribe(SubscribeObserver observer) {
        observers.put(observer, true);
        observer.update(true);
    }

    // 구취
    public void unsubscribe(SubscribeObserver observer) {
        if (observers.containsKey(observer)) {
            observers.put(observer, false);
            observer.update(false);
        }
    }

}
