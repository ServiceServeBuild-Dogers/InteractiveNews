package service.search;

import service.Service;

import java.util.List;

// Platform에서 호출하는 싱글턴 객체 클래스
public class SearchService {
    private static SearchService instance;
    private SearchServiceFacade searchServiceFacade;

    // Singleton 패턴을 적용하여 인스턴스 생성 제한
    private SearchService() {
        this.searchServiceFacade = new SearchServiceFacadeImpl();
    }

    // 싱글톤 인스턴스 반환 메서드
    public static synchronized SearchService getInstance() {
        if (instance == null) {
            instance = new SearchService();
        }
        return instance;
    }

    // 검색 기능 수행 - 키워드를 이용한 검색
    public List<String> search(String query) {
        return searchServiceFacade.search(query);
    }

    // 검색 기능 수행 - 카테고리를 이용한 검색
    public List<String> searchByCategory(String category) {
        return searchServiceFacade.searchByCategory(category);
    }
}
