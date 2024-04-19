package service.news;

import service.news.recommendation.RecommendationStrategy;

public interface RecommendationStrategyFactory {
    RecommendationStrategy createRecommendationStrategy(String type);
}
