package platform;

import service.auth.AuthenticationService;
import service.news.NewsRecommendationService;
import service.news.SubscribeObserver;
import service.search.SearchService;

import java.util.List;

public class Platform {

    // 서비스 인스턴스 선언
    private AuthenticationService authenticationService;
    private NewsRecommendationService newsRecommendationService;
    private SearchService searchService;

    // 각 서비스의 싱글톤 인스턴스 할당
    public Platform() {
        this.authenticationService = AuthenticationService.getInstance();
        this.newsRecommendationService = NewsRecommendationService.getInstance();
        this.searchService = SearchService.getInstance();
    }

    // AuthenticationService 관련 서비스

    // 사용자 로그인
    // 사용자 이름과 비밀번호를 받아 인증을 수행
    public boolean login(String username, String password) {
        return authenticationService.authenticate(username, password);
    }

    // 사용자 토큰 검증
    // 토큰과 사용자 이름을 이용하여 토큰이 유효한지 확인
    public Boolean verifyUserToken(String token, String username) {
        return authenticationService.verifyToken(token, username);
    }

    // NewsRecommendationService 관련 서비스

    // 뉴스 구독
    // SubscribeObserver 객체를 통해 사용자 구독자 추가
    public void subscribeToNews(SubscribeObserver observer) {
        newsRecommendationService.subscribe(observer);
    }

    // 뉴스 구독 취소
    // SubscribeObserver 객체를 통해 사용자 구독자 취소
    public void unsubscribeFromNews(SubscribeObserver observer) {
        newsRecommendationService.unsubscribe(observer);
    }

    // 뉴스 추천
    // 사용자의 선호도에 따라 뉴스를 추천
    public void recommendNews(String userPreferences, SubscribeObserver observer) {
        newsRecommendationService.recommendNews(userPreferences, observer);
    }

    // 추천 전략 변경
    // 새로운 뉴스 추천 전략 설정
    public void changeRecommendationStrategy(String type){
        newsRecommendationService.setStrategy(type);
    }

    // SearchService 관련 서비스

    // 키워드 기반 검색
    // 입력받은 키워드로 뉴스를 검색하고 결과를 리스트로 반환
    public List<String> searchKeyWord(String query) {
        return searchService.search(query);
    }

    // 카테고리 기반 검색
    // 입력받은 카테고리로 뉴스를 검색하고 결과를 리스트로 반환
    public List<String> searchCategory(String category){
        return searchService.searchByCategory(category);
    }

    // 검색 결과 출력
    // 검색된 결과를 사용자에게 출력
    public void printResult(List<String> results){
        if(results.isEmpty()){
            System.out.println("검색 결과가 존재하지 않습니다.");
        }else {
            for(String result : results){
                System.out.println(result);
            }
        }
    }

}