// 개별 상품을 관리하는 클래스
public class Product {
    // 속성
    private String name;        // 상품명
    private int price;          // 가격
    private String description; // 설명
    private int stock;          // 재고수량

    // 생성자
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // 재고 증가
    public void addStock(int quantity) {
        if (quantity > 0) {
            stock += quantity;
        }
    }

    // 재고 감소
    public boolean removeStock(int quantity) {
        if (quantity <= 0) {
            System.out.println("수량은 1 이상이어야 합니다.");
            return false;
        }

        if (stock < quantity) {
            System.out.println("재고가 부족합니다.");
            return false;
        }

        stock -= quantity;
        return true;
    }

    // 상품 정보 출력
    public void printInfo() {
        System.out.println("상품명: " + name);
        System.out.println("가격: " + price + "원");
        System.out.println("설명: " + description);
        System.out.println("재고: " + stock + "개");
    }

    // getter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        if (price >= 0) {
            this.price = price;
        }
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        if (description != null && !description.isEmpty()) {
            this.description = description;
        }
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        if (stock >= 0) {
            this.stock = stock;
        }
    }
}