package service.search;

import java.util.ArrayList;
import java.util.List;

// Facade 구현
public class SearchServiceFacadeImpl implements SearchServiceFacade {
    private ElasticsearchService elasticsearchService = new ElasticsearchService();
    private MongoDBService mongoDBService = new MongoDBService();

    private NLPLibrary nlpLibrary = new NLPLibrary();

    @Override
    public List<String> search(String query) {
        // Elasticsearch와 MongoDB에서 키워드를 이용한 검색을 수행하고 결과를 합침
        List<String> results = new ArrayList<>();
        results.addAll(elasticsearchService.search(query));
        results.addAll(mongoDBService.search(query));
        // NLP 라이브러리를 이용한 텍스트 분석 결과를 합침
        results.addAll(nlpLibrary.analyzeText(query)); 
        return results;
    }

    @Override
    public List<String> searchByCategory(String category) {
        // Elasticsearch와 MongoDB에서 카테고리를 이용한 검색을 수행하고 결과를 합침
        List<String> results = new ArrayList<>();
        results.addAll(elasticsearchService.searchByCategory(category));
        results.addAll(mongoDBService.searchByCategory(category));
        return results;
    }
}

