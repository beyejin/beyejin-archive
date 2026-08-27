import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CommerceSystem {
    private List<Category> categories;
    private Customer customer;

    public CommerceSystem(List<Category> categories, Customer customer) {
        this.categories = categories;
        this.customer = customer;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.KOREA);

        while (true) {
            System.out.println("\n[ 실시간 커머스 플랫폼 메인 ]");
            System.out.println("고객: " + customer.getName() + " | 등급: " + customer.getGrade());

            for (int i = 0; i < categories.size(); i++) {
                System.out.println((i + 1) + ". " + categories.get(i).getCategoryName());
            }
            System.out.println("0. 종료      | 프로그램 종료");
            System.out.print("번호를 입력하세요: ");

            int categoryChoice = scanner.nextInt();

            if (categoryChoice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            if (categoryChoice < 1 || categoryChoice > categories.size()) {
                System.out.println("올바른 번호를 입력해주세요.");
                continue;
            }

            Category selectedCategory = categories.get(categoryChoice - 1);

            while (true) {
                System.out.println("\n[ " + selectedCategory.getCategoryName() + " 카테고리 ]");

                List<Product> products = selectedCategory.getProductList();

                for (int i = 0; i < products.size(); i++) {
                    Product p = products.get(i);
                    System.out.printf("%d. %-15s | %,12d원 | %s%n",
                            i + 1,
                            p.getName(),
                            p.getPrice(),
                            p.getDescription());
                }

                System.out.println("0. 뒤로가기");
                System.out.print("번호를 입력하세요: ");

                int productChoice = scanner.nextInt();

                if (productChoice == 0) {
                    break;
                }

                if (productChoice < 1 || productChoice > products.size()) {
                    System.out.println("올바른 번호를 입력해주세요.");
                    continue;
                }

                Product selectedProduct = products.get(productChoice - 1);

                System.out.println("선택한 상품: "
                        + selectedProduct.getName() + " | "
                        + numberFormat.format(selectedProduct.getPrice()) + "원 | "
                        + selectedProduct.getDescription() + " | 재고: "
                        + selectedProduct.getStock() + "개");
            }
        }

        scanner.close();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        if (categories != null) {
            this.categories = categories;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
        }
    }
}