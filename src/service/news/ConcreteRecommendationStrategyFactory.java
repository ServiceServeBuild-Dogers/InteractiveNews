package service.news;

public class ConcreteRecommendationStrategyFactory implements RecommendationStrategyFactory{
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
