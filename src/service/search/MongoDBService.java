package service.search;

import java.util.Arrays;
import java.util.List;

// MongoDB 검색 로직 구현
public class MongoDBService {
    // 키워드를 이용한 검색
    public List<String> search(String query) {
        System.out.println("MongoDB에서 키워드를 이용한 검색: " + query);
        // 검색 결과 반환
        return Arrays.asList("MongoDB 키워드 검색 결과 1", "MongoDB 키워드 검색 결과 2");
    }
    
    // 카테고리를 선택하여 검색
    public List<String> searchByCategory(String category) {
        System.out.println("MongoDB에서 카테고리를 선택하여 검색: " + category);
        // 카테고리별 검색 결과 반환
        return Arrays.asList("MongoDB 카테고리별 검색 결과 1", "MongoDB 카테고리별 검색 결과 2");
    }
}
