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

퍼사드 패턴 - 검색 서비스
뉴스 검색 서비스는 키워드나 카테고리 기반으로 뉴스를 검색하는 작업을 수행합니다. 이러한 검색 서비스에서 퍼사드 패턴을 적용하면 퍼사드 인터페이스를 통해 복잡한 검색 로직을 간결하고 단순한 형태로 외부로 노출함으로써, 사용자는 검색 서비스의 내부 동작을 몰라도 편리하게 검색 기능을 이용할 수 있습니다. 또한  MongoDB에서의 검색과 Elasticsearch 검색엔진 등 다양한 검색 엔진을 유연하게 교체하거나 새로운 검색 엔진을 추가할 수 있어 시스템이 확장성과 변경에 대한 유연성을 지니게 됩니다. 퍼사트 패턴을 통해 공통된 검색 기능을 한 곳에서 관리하여 코드 중복을 최소화할 수 있어 코드의 가독성과 유지보수성을 높여줍니다. 

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

## 인증 서비스: AuthenticationService 클래스 설명

- AuthenticationService는 인증 서비스를 위한 싱글턴 객체로, 플랫폼에서 호출되어 다양한 인증 전략을 통해 사용자 인증을 관리합니다. 이 서비스는 사용자의 인증 요청을 처리하고, 토큰 기반 검증을 포함할 수 있습니다.
### 주요 기능 및 메서드:
1. Singleton Pattern Implementation:
    - getInstance(): 이 메서드는 클래스의 인스턴스를 반환합니다. 싱글턴 패턴을 사용하여 클래스의 인스턴스가 하나만 생성되도록 보장합니다.
2. Factory Pattern:
    - createAuthStrategy(String type): 인증 전략을 동적으로 생성합니다. 이 메서드는 AuthFactory 인터페이스를 통해 다양한 인증 전략(Default, OAuth)을 생성할 수 있도록 합니다.
3. Strategy Pattern:
    - 인증 전략 (AuthStrategy): 다양한 인증 방식을 추상화하고, 구체적인 인증 방식은 별도의 클래스(DefaultAuthStrategy, OAuthStrategy)에서 구현합니다. 이를 통해 사용자 인증 로직을 유연하게 변경할 수 있습니다.
### 구성 요소:
- AuthFactory: 인증 전략 객체를 생성하는 팩토리 인터페이스입니다. 이를 통해 필요에 따라 다양한 인증 전략 객체를 생성할 수 있습니다.
- AuthStrategy: 인증을 수행하는 전략을 정의하는 인터페이스입니다. 다양한 전략을 구현하여, 사용자의 인증 요청을 처리합니다.
- ConcreteAuthFactory: AuthFactory 인터페이스의 구현체로, 실제로 인증 전략을 생성하는 클래스입니다.
- DefaultAuthStrategy, OAuthStrategy: 각각 기본 인증과 OAuth 인증을 구현한 전략 클래스입니다.
### 사용 예시:
```java
public static void main(String[] args) {
    // 인증 서비스 인스턴스 획득
    AuthenticationService authService = AuthenticationService.getInstance();

    // 기본 인증 전략 설정
    authService.createAuthStrategy("Default");

    // 사용자 인증 시도
    boolean isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status: " + isAuthenticated);

    // OAuth 인증 전략으로 변경
    authService.createAuthStrategy("OAuth");

    // OAuth를 사용한 사용자 인증 시도
    isAuthenticated = authService.authenticate("username", "password");
    System.out.println("Authentication status with OAuth: " + isAuthenticated);
}
```

## **Platform 클래스**

**`Platform`** 클래스는 서비스 (**`AuthenticationService`**, **`NewsRecommendationService`**, **`SearchService`**)를 통합 관리하는 클래스입니다. 이 클래스는 각 서비스에 대한 싱글톤 인스턴스를 생성하고 유지하여, 전체 애플리케이션에서 서비스의 일관된 접근성을 보장하고 일관된 방식으로 서비스를 제공하는 역할을 수행합니다.

### **주요 기능 및 설계 목적:**

1. **서비스 인스턴스 관리**: **`Platform`** 클래스는 각 서비스의 싱글톤 인스턴스를 관리합니다. 이는 메모리 사용 최적화 및 서비스 인스턴스의 중복 생성을 방지하는 데 중요한 역할을 합니다.
2. **일관된 서비스 접근 제공**: 플랫폼을 통해 제공되는 모든 서비스는 전역적으로 하나의 인스턴스를 통해 접근되므로, 모든 사용자와 컴포넌트가 동일한 서비스 상태와 기능을 경험할 수 있습니다.
3. **통합된 인터페이스 제공**: 이 클래스는 다양한 기능을 통합하여 하나의 인터페이스를 통해 액세스할 수 있도록 디자인되었습니다.

### **이점:**

- **확장성**: **`Platform`** 클래스는 새로운 서비스를 쉽게 추가하고 관리할 수 있는 구조로 설계되었습니다. 각 서비스는 독립적으로 개발하고 통합할 수 있어, 시스템의 확장성이 향상됩니다.
- **유지보수의 용이성**: 싱글톤 패턴을 적용함으로써 인스턴스의 생명 주기를 중앙에서 관리할 수 있고, 이는 전체 시스템의 유지보수를 간편하게 만듭니다.
- **성능 최적화**: 중복 인스턴스 생성을 방지하고, 메모리와 리소스 사용을 최적화하여 시스템의 전반적인 성능을 향상시킵니다.

### 디자인 패턴 적용:

- 싱글톤 패턴 : **`Platform`** 클래스에서 적용된 싱글톤 디자인 패턴은 각 서비스의 인스턴스를 중복 생성하지 않고 전체 애플리케이션에서 단일 인스턴스만을 유지하도록 하여, 시스템 리소스의 효율적 관리와 일관된 서비스 상태 유지를 목적으로 하며, 멀티 스레드 환경에서의 데이터 무결성 보장과 성능 최적화를 위해 적용되었습니다.

## Client 클래스

**`Client`** 클래스는 사용자 정보와 상호작용을 관리하기 위해 설계된 클래스입니다. 이 클래스는 플랫폼의 다양한 기능을 사용하는 사용자 역할을 수행하며, 사용자 인증, 뉴스 추천, 검색 기능 등에 필요한 정보를 저장하고 관리합니다.

