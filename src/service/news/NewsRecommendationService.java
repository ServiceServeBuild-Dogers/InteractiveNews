package service.news;

import service.Service;

import java.util.ArrayList;
import java.util.List;

//Platform 에서 호출하는 싱글턴 객체 클래스
public class NewsRecommendationService implements Service {

    private static NewsRecommendationService instance;
    private RecommendationStrategy strategy;
    private List<SubscribeObserver> observers = new ArrayList<>();

    private NewsRecommendationService() {
        // 초기 추천 전략 설정
        this.strategy = new TechnologyRecommendationStrategy(); // 기본 전략
    }

    public static synchronized NewsRecommendationService getInstance() {
        if (instance == null) {
            instance = new NewsRecommendationService();
        }
        return instance;
    }

    public void setStrategy(RecommendationStrategy strategy) {
        this.strategy = strategy;
    }

    public void recommendNews(String userPreferences) {
        if (this.strategy != null) {
            List<String> recommendations = this.strategy.recommend(userPreferences);
            recommendations.forEach(System.out::println);
        }
    }

    public void subscribe(SubscribeObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(SubscribeObserver observer) {
        observers.remove(observer);
    }

    public void notifySubscribers(boolean isSubscribed) {
        for (SubscribeObserver observer : observers) {
            observer.update(isSubscribed);
        }
    }

    // 예를 들어 구독 상태 변경을 처리
    public void updateSubscriptionStatus(boolean active) {
        notifySubscribers(active);
    }
}
