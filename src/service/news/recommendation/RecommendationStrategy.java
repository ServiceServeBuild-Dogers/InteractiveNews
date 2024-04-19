package service.news.recommendation;

import java.util.List;

public interface RecommendationStrategy {
    List<String> recommend(String userPreferences);
}
