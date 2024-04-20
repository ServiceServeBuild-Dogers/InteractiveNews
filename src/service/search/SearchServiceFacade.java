package service.search;

import java.util.List;

// Facade 인터페이스
public interface SearchServiceFacade {
    // 키워드를 통한 검색
    List<String> search(String query);
    // 카테고리를 선택하여 검색
    List<String> searchByCategory(String category);
}

