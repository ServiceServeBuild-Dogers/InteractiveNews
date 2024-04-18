package platform;

import service.auth.AuthenticationService;
import service.news.NewsRecommendationService;
import service.search.SearchService;

public class Platform {

    private static Platform instance;
    private NewsRecommendationService newsService;
    private AuthenticationService authService;
    private SearchService searchService;

}
