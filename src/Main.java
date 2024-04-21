import platform.Platform;

import java.lang.*;

public class Main {
    public static void main(String[] args) {
        // 플랫폼 인스턴스 생성
        Platform platform = new Platform();

        // 두 명의 클라이언트 생성
        Client client1 = new Client("user1", "1234", "token123", "sports");
        Client client2 = new Client("user2", "5678", "token456", "politics");

        // 각 클라이언트별 기능 수행
        // 클라이언트 1 로그인 및 토큰 검증
        if (platform.login(client1.getUsername(), client1.getPassword())) {
            System.out.println("Client 1 logged in successfully.");
            if (platform.verifyUserToken(client1.getToken(), client1.getUsername())) {
                System.out.println("Client 1 token is valid.");
            }
        }

        // 클라이언트 1 뉴스 구독
        platform.subscribeToNews(client1.getObserver());
        // 클라이언트 1 뉴스 추천
        platform.recommendNews(client1.getUserPreferences(), client1.getObserver());

        // 클라이언트 2 로그인 및 토큰 검증
        if (platform.login(client2.getUsername(), client2.getPassword())) {
            System.out.println("Client 2 logged in successfully.");
            if (platform.verifyUserToken(client2.getToken(), client2.getUsername())) {
                System.out.println("Client 2 token is valid.");
            }
        }

        // 클라이언트 2 뉴스 구독 및 추천
        platform.subscribeToNews(client2.getObserver());
        platform.recommendNews(client2.getUserPreferences(), client2.getObserver());
        // 클라이언트 2 구독 취소
        platform.unsubscribeFromNews(client2.getObserver());

        // 클라이언트 1 키워드 기반 검색
        client1.setSearchKeyword("AI");
        platform.printResult(platform.searchCategory(client1.getSearchKeyword()));

        // 클라이언트 1 카테고리 기반 검색
        client1.setSelectCategory("technology");
        platform.printResult(platform.searchCategory(client1.getSelectCategory()));

    }
}