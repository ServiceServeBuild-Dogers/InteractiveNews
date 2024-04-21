package service.news.recommendation;

import java.util.ArrayList;
import java.util.List;

public class UserBasedRecommendationStrategy implements RecommendationStrategy {

    @Override
    public List<String> recommend(String userPreferences) {
        List<String> recommendations = new ArrayList<>();
        recommendations.add("비슷한 관심사를 가진 사용자들이 선호하는 : " + userPreferences + " 관련 뉴스");
        recommendations.add("커뮤니티 추천 : " + userPreferences + " 관련 인기 기사");
        return recommendations;
    }
}
