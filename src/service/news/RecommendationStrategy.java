package service.news;

import java.util.List;

public interface RecommendationStrategy {
    List<String> recommend(String userPreferences);
}
