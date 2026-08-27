import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 전자제품 상품
        List<Product> electronicsProducts = new ArrayList<>();
        electronicsProducts.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10));
        electronicsProducts.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 8));
        electronicsProducts.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 5));
        electronicsProducts.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 15));

        // 의류 상품
        List<Product> clothesProducts = new ArrayList<>();
        clothesProducts.add(new Product("후드티", 59000, "편안한 오버핏 후드티", 40));
        clothesProducts.add(new Product("청바지", 79000, "기본 데님 팬츠", 35));
        clothesProducts.add(new Product("운동화", 129000, "가벼운 러닝화", 25));

        // 식품 상품
        List<Product> foodProducts = new ArrayList<>();
        foodProducts.add(new Product("사과", 12000, "신선한 사과 1박스", 60));
        foodProducts.add(new Product("우유", 3000, "국내산 신선 우유", 80));
        foodProducts.add(new Product("라면", 4500, "인기 봉지라면 5개입", 100));

        // 카테고리 생성
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("전자제품", electronicsProducts));
        categories.add(new Category("의류", clothesProducts));
        categories.add(new Category("식품", foodProducts));

        // 고객 생성
        Customer customer = new Customer("한예진", "yejin@example.com", "GOLD");

        // 커머스 시스템 실행
        CommerceSystem commerceSystem = new CommerceSystem(categories, customer);
        commerceSystem.start();
    }
}