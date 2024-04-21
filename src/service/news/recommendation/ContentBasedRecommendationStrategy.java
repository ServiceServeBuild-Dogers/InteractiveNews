package service.news.recommendation;

import java.util.ArrayList;
import java.util.List;

public class ContentBasedRecommendationStrategy implements RecommendationStrategy {
    @Override
    public List<String> recommend(String userPreferences) {
        List<String> recommendations = new ArrayList<>();
        recommendations.add(userPreferences +" : 최근 뉴스");
        recommendations.add(userPreferences + " : 관련된 분석 기사");
        return recommendations;
    }
}
