package ra.prisentation;

import ra.business.ProductsBusiness;
import ra.entity.Products;

import java.util.List;
import java.util.Scanner;

public class ProductsManagement {
    public static void displayProductsManagement(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("************************PRODUCT-MENU*******************");
            System.out.println("1. Danh sách sản phẩm");
            System.out.println("2. Tạo mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Hiển thị danh sách sản phẩm theo ngày tạo giảm dần");
            System.out.println("6. Tìm kiếm sản phẩm theo khoảng giá bán.");
            System.out.println("7. Hiển thị top 3 sản phẩm có lợi nhuận cao nhất (lợi nhuận = giá bán - giá nhập)");
            System.out.println("8. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        deleteProduct(scanner);
                        break;
                    case 5:
                        break;
                    case 6:
                        findProductsByPriceAB(scanner);
                        break;
                    case 7:
                        break;
                    case 8:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng nhập từ 1-5");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (isExit);
    }

    public static void displayProducts() {
        List<Products> productsList = ProductsBusiness.findAllProducts();
        productsList.forEach(System.out::println);
    }

    public static void deleteProduct(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm cần xóa");
        try {
            int deletePrId = Integer.parseInt(scanner.nextLine());
            Products deleteProduct = ProductsBusiness.findProductById(deletePrId);
            if (deleteProduct != null) {
                boolean result = ProductsBusiness.deleteProductById(deletePrId);
                if (result) {
                    System.out.println("xóa sản phẩm thành công");
                } else {
                    System.err.println("Xóa sản phẩm thất bại");
                }
            } else {
                System.err.println("Mã sản phẩm không tồn tại!");
            }
        } catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập vào số nguyên");
        }
    }

    public static void findProductsByPriceAB(Scanner scanner) {
        try {
            System.out.println("Nhập vào giá A");
            double sellingPriceA = Double.parseDouble(scanner.nextLine());
            System.out.println("Nhập vào giá B");
            double sellingPriceB = Double.parseDouble(scanner.nextLine());
            List<Products> searchProduct = ProductsBusiness.findProductsBySellPrice(sellingPriceA,sellingPriceB);
            if (searchProduct != null) {
                searchProduct.forEach(System.out::println);
            }else {
                System.err.println("Không tìm thấy sản phẩm nào");
            }
        }catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập kiểu số thực");
        }



    }


}
