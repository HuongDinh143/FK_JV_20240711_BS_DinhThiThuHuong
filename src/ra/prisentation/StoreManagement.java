package ra.prisentation;

import java.util.Scanner;

public class StoreManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("******************STORE-MANAGEMENT******************");
            System.out.println("1. Quản lý danh mục");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        CategoriesManagement.displayCategoriesManagement(scanner);
                        break;
                    case 2:
                        ProductsManagement.displayProductsManagement(scanner);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-3");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }

        } while (true);
    }
}
