package service.news.recommendation;

import service.news.recommendation.RecommendationStrategy;

import java.util.ArrayList;
import java.util.List;

public class TechnologyRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<String> recommend(String userPreferences) {
        List<String> recommendations = new ArrayList<>();
        recommendations.add(userPreferences +" : 최근 기술 뉴스");
        recommendations.add(userPreferences + " : 관련된 시각");
        return recommendations;
    }
}
