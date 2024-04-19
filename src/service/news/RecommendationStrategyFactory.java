package service.news;

public interface RecommendationStrategyFactory {
    RecommendationStrategy createRecommendationStrategy(String type);
}
