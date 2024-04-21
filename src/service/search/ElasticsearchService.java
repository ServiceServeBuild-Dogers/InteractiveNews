package service.search;

import java.util.Arrays;
import java.util.List;

// Elasticsearch 검색
public class ElasticsearchService {
    // 키워드를 이용한 검색
    public List<String> search(String query) {
        System.out.println("Elasticsearch에서 키워드를 이용한 검색: " + query);
        // 검색 결과 반환
        return Arrays.asList("Elasticsearch 키워드 검색 결과 1", "Elasticsearch 키워드 검색 결과 2");
    }
    
    // 카테고리를 선택하여 검색
    public List<String> searchByCategory(String category) {
        System.out.println("Searching in Elasticsearch with category: " + category);
        // 카테고리별 검색 결과 반환
        return Arrays.asList("Elasticsearch 카테고리별 검색 결과 1", "Elasticsearch 카테고리별 검색 결과 2");
    }
}
