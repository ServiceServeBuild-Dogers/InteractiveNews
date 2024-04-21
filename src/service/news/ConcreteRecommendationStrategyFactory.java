package service.news;

import service.news.recommendation.RecommendationStrategy;
import service.news.recommendation.ContentBasedRecommendationStrategy;
import service.news.recommendation.UserBasedRecommendationStrategy;

public class ConcreteRecommendationStrategyFactory implements RecommendationStrategyFactory{

    //존재하지 않는 추천 타입 가져오면 에러 throw
    @Override
    public RecommendationStrategy createRecommendationStrategy(String type) {
        if ("UserBased".equalsIgnoreCase(type)) {
            return new UserBasedRecommendationStrategy();
        } else if ("ContentBased".equalsIgnoreCase(type)) {
            return new ContentBasedRecommendationStrategy();
        } else {
            throw new IllegalArgumentException("No such strategy for type: " + type);
        }
    }
}
