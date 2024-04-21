# 프로젝트 개요
뉴스 추천 서비스, 인증 서비스, 검색 서비스를 포함한 플랫폼을 구현합니다. 각 서비스는 특정 디자인 패턴을 활용하여 기능성, 유지보수성, 확장성을 향상시킵니다.

## 뉴스 추천 서비스: NewsRecommendationService 클래스 설명
- NewsRecommendationService는 뉴스 추천 서비스를 위한 싱글턴 객체로, 플랫폼에서 호출되어 사용자의 구독 상태에 따라 개인화된 뉴스를 추천합니다. 뉴스 추천 전략을 관리하고, 구독자들의 구독 상태를 추적하는 역할을 수행합니다.
### 주요 기능 및 메서드:
  1. Singleton Pattern Implementation:<br>
    - getInstance(): 이 메서드는 클래스의 인스턴스를 반환합니다. 싱글턴 패턴을 사용하여 이 클래스의 인스턴스가 하나만 생성되도록 보장합니다.
  2. Strategy Pattern:<br>
    - setStrategy(String type): 추천 전략을 설정합니다. 전략은 동적으로 변경할 수 있으며, 사용자의 선호나 다른 요인에 따라 추천 로직을 적용할 수 있습니다.
  3. Observer Pattern:<br>
    - subscribe(SubscribeObserver observer): 사용자를 구독자 목록에 추가합니다. 구독자는 뉴스 추천 서비스를 이용할 수 있습니다.<br>
    - unsubscribe(SubscribeObserver observer): 사용자를 구독자 목록에서 제거합니다.<br>
    - recommendNews(String userPreferences, SubscribeObserver observer): 구독자가 활성 상태일 경우, 추천 전략에 따라 뉴스를 추천합니다.<br>
### 구성 요소:
  - RecommendationStrategy: 뉴스를 추천하는 전략을 정의하는 인터페이스입니다. 다양한 전략을 구현하여 다양한 사용자 선호에 맞게 뉴스를 추천할 수 있습니다.
  - SubscribeObserver: 구독자의 상태 변경을 감지하는 옵저버 인터페이스입니다.
  - RecommendationStrategyFactory: 추천 전략을 생성하는 팩토리 인터페이스입니다. 이를 통해 필요에 따라 다양한 추천 전략 객체를 생성할 수 있습니다.
  - ConcreteObserver: SubscribeObserver 인터페이스의 구현체로, 구독자의 구체적인 행동을 정의합니다. 사용자의 이름과 구독 상태를 관리합니다.
### 사용 예시:
```java
    public static void main(String args[]){

        //서비스 인스턴스 획득
        //초기 추천 전략을 technology 분야를 추천하는 것으로 설정
        NewsRecommendationService service = NewsRecommendationService.getInstance();
        
        //사용자 생성
        ConcreteObserver observer1 = new ConcreteObserver("User1");
        ConcreteObserver observer2 = new ConcreteObserver("User2");

        // 사용자 구독
        service.subscribe(observer1);
        
        // 사용자 선호에 따라 뉴스 추천
        service.recommendNews("technology", observer1);
        
        // 사용자의 다른 선호도를 가져와 설정
        service.setStrategy("Sports");
        
        // 사용자 선호에 따라 뉴스 추천
        service.recommendNews("sports",observer1);

        // observer 2은 비구독 상태 & 그 상태에서 추천 요청 시도 -> 실패함
        service.recommendNews("technology", observer2);

        // observer1이 구독 취소 & 그 상태에서 추천 요청 시도 -> 실패함
        service.unsubscribe(observer1);
        service.recommendNews("technology", observer1);

    }
```

## 뉴스 검색 서비스
사용자로부터 키워드를 입력받거나 카테고리를 선택하여 검색할 수 있는 서비스입니다. 

### 주요 기능 및 메서드 :

1. **SearchServiceFacade.java**:
   - 이 인터페이스는 검색 서비스에 대한 Facade 역할을 정의합니다.
   - `search(String query)` 메서드는 키워드를 입력받아 검색을 수행합니다.
   - `searchByCategory(String category)` 메서드는 카테고리를 입력받아 해당 카테고리로 검색을 수행합니다.

2. **ElasticsearchService.java**:
   - 이 클래스는 Elasticsearch에서의 검색 기능을 구현합니다.
   - `search(String query)` 메서드는 주어진 키워드를 사용하여 Elasticsearch에서 검색을 수행합니다.
   - `searchByCategory(String category)` 메서드는 주어진 카테고리를 사용하여 Elasticsearch에서 카테고리별 검색을 수행합니다.

3. **MongoDBService.java**:
   - 이 클래스는 MongoDB에서의 검색 기능을 구현합니다.
   - `search(String query)` 메서드는 주어진 키워드를 사용하여 MongoDB에서 검색을 수행합니다.
   - `searchByCategory(String category)` 메서드는 주어진 카테고리를 사용하여 MongoDB에서 카테고리별 검색을 수행합니다.

4. **NLPLibrary.java**:
   - 이 클래스는 텍스트 분석 및 처리를 담당하는 NLP 라이브러리를 모사합니다.
   - `analyzeText(String text)` 메서드는 주어진 텍스트를 분석하여 키워드 및 카테고리를 추출합니다.

5. **SearchServiceFacadeImpl.java**:
   - 이 클래스는 SearchServiceFacade 인터페이스를 구현하여 검색 서비스의 Facade 역할을 수행합니다.
   - ElasticsearchService와 MongoDBService를 내부적으로 사용하여 검색 기능을 구현합니다.
   - `search(String query)` 메서드는 키워드를 입력받아 Elasticsearch와 MongoDB에서 검색을 수행하고, 결과를 합칩니다.
   - `searchByCategory(String category)` 메서드는 카테고리를 입력받아 Elasticsearch와 MongoDB에서 카테고리별 검색을 수행하고, 결과를 합칩니다.

### 사용예시:
```java
    public static void main(String[] args) {
        // 검색 서비스 Facade 생성
        SearchServiceFacade searchService = new SearchServiceFacadeImpl();

        // 키워드를 이용한 검색
        System.out.println("키워드 검색 결과:");
        List<String> keywordResults = searchService.search("키워드");
        printResults(keywordResults);

        // 카테고리를 선택하여 검색
        System.out.println("카테고리별 검색 결과:");
        List<String> categoryResults = searchService.searchByCategory("카테고리");
        printResults(categoryResults);
    }

    // 결과를 출력
    private static void printResults(List<String> results) {
        if(results.isEmpty()) {
            System.out.println("검색 결과가 존재하지 않습니다.");
        } else {
            for(String result : results) {
                System.out.println(result);
            }
        }
    }
```