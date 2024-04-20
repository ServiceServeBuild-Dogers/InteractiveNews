package service.news;

import service.news.recommendation.RecommendationStrategy;
import service.news.recommendation.SportsRecommendationStrategy;
import service.news.recommendation.TechnologyRecommendationStrategy;

public class ConcreteRecommendationStrategyFactory implements RecommendationStrategyFactory{

    //존재하지 않는 추천 타입 가져오면 에러 throw
    @Override
    public RecommendationStrategy createRecommendationStrategy(String type) {
        if ("Technology".equalsIgnoreCase(type)) {
            return new TechnologyRecommendationStrategy();
        } else if ("Sports".equalsIgnoreCase(type)) {
            return new SportsRecommendationStrategy();
        } else {
            throw new IllegalArgumentException("No such strategy for type: " + type);
        }
    }
}
