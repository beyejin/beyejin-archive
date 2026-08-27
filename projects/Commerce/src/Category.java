import java.util.ArrayList;
import java.util.List;

/**
 * Product 클래스를 관리하는 클래스
 */
public class Category {
    //속성
    private String categoryName;
    private List<Product> productList;

    //생성자
    public Category(String categoryName, List<Product> productList) {
        this.categoryName = categoryName;
        this.productList = productList;
    }

    //기능
    // 카테고리에 상품 추가
    public void addProduct(Product product) {
        productList.add(product);
        System.out.println(product.getName() + " 이(가) " + categoryName + " 카테고리에 추가되었습니다.");
    }

    // 카테고리 별 목록 출력
    public void printCategory() {
        if (productList.isEmpty()) {
            System.out.println(categoryName + " 카테고리에 상품이 없습니다.");
            return;
        }

        System.out.println("=== " + categoryName + " 상품 목록 ===");

        for (Product product : productList) {
            product.printInfo(); // Product에서 만든 메서드 활용
            System.out.println("------------------");
        }
    }

    // 특정 상품 찾기 (이거 나중에 Cart에서 유용함)
    public Product findProductByName(String name) {
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // getter
    public String getCategoryName() {
        return categoryName;
    }

    //setter
    public void setCategoryName(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            this.categoryName = categoryName;
        }
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        if (productList != null) {
            this.productList = productList;
        }
    }
}
