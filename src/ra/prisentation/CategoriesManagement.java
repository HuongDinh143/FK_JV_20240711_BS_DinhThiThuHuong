package ra.prisentation;

import ra.business.CategoriesBusiness;
import ra.entity.Categories;

import java.util.List;
import java.util.Scanner;

public class CategoriesManagement {
    public static void displayCategoriesManagement(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**********************CATEGORY-MENU*******************");
            System.out.println("1. Danh sách danh mục");
            System.out.println("2. Tạo mới danh mục");
            System.out.println("3. Cập nhật danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Thống kê số lượng sản phẩm theo danh mục");
            System.out.println("6. Thoát");
            System.out.println("Lựa chọn của bạn");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayCategories();
                        break;
                    case 2:
                        createCategory(scanner);
                        break;
                    case 3:
                        updateCategory(scanner);
                        break;
                    case 4:
                        deleteCategory(scanner);
                        break;
                        case 5:
                            statisticProductByCategoryId(scanner);
                            break;
                    case 6:
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

    public static void displayCategories() {
        List<Categories> categoriesList = CategoriesBusiness.findAllActive();
        if (categoriesList != null) {
            categoriesList.forEach(System.out::println);
        } else {
            System.err.println("Không tồn tại danh mục sản phẩm");
        }

    }

    public static void createCategory(Scanner scanner) {
        Categories category = new Categories();
        category.inputData(scanner);
        boolean result = CategoriesBusiness.saveCategories(category);
        if (result) {
            System.out.println("Thêm mới thành công");
        } else {
            System.err.println("Thêm mới thất bại");
        }
    }

    public static void updateCategory(Scanner scanner) {

        System.out.println("Nhập vào mã danh mục cần cập nhật");
        try {
            int updateCatId = Integer.parseInt(scanner.nextLine());
            Categories updateCategory = CategoriesBusiness.findById(updateCatId);
            if (updateCategory != null) {
                boolean isExit = true;
                do {
                    System.out.println("1. Cập nhật tên");
                    System.out.println("2. Cập nhật trạng thái");
                    System.out.println("3. Thoát");
                    System.out.println("Lựa chọn của bạn");
                    int choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập vào tên danh mục mới");
                            updateCategory.setCatName(scanner.nextLine());
                            break;
                        case 2:
                            System.out.println("Nhập vào trạng thái");
                            updateCategory.setCatStatus(Boolean.parseBoolean(scanner.nextLine()));
                            break;
                        case 3:
                            isExit = false;
                            break;
                        default:
                            System.err.println("Vui lòng nhập từ 1-4");
                    }

                } while (isExit);
                boolean result = CategoriesBusiness.updateCategories(updateCategory);
                if (result) {
                    System.out.println("Cập nhật thành công");
                }else {
                    System.err.println("Cập nhật thất bại");
                }
            } else {
                System.err.println("Không có mã danh mục nào");
            }


        } catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập vào số nguyên");
        }
    }
    public static void deleteCategory(Scanner scanner){
        System.out.println("Nhập vào mã danh mục cần xóa");
        try {
            int deleteCatId = Integer.parseInt(scanner.nextLine());
            Categories deleteCategory = CategoriesBusiness.findById(deleteCatId);
            int countProduct = CategoriesBusiness.staticCategories(deleteCatId);
            if (deleteCategory != null) {
                if (countProduct==0){
                    boolean result = CategoriesBusiness.deleteCategories(deleteCatId);
                    if (result) {
                        System.out.println("Xóa thành công");
                    }else {
                        System.err.println("Xóa thất bại");
                    }
                }else {
                    System.err.println("Đã tồn tại sản phẩm trong danh mục không thể xóa");
                }

            }else {
                System.err.println("Mã danh mục không tồn tại");
            }
        } catch (NumberFormatException e) {
            System.err.println("Nhập vào không đúng định dạng dữ liệu");
        }
    }
    public static void statisticProductByCategoryId(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần thống kê sản phẩm");
        try {
            int categoryId = Integer.parseInt(scanner.nextLine());
            Categories category = CategoriesBusiness.findById(categoryId);
            if (category != null) {
                int countProduct = CategoriesBusiness.staticCategories(categoryId);
                System.out.printf("Có %d sản phẩm trong danh mục %s\n",
                        countProduct, CategoriesBusiness.findById(categoryId).getCatName());
            }else {
                System.err.println("Mã danh mục không tồn tại");
            }

        }catch (NumberFormatException e) {
            System.err.println("Vui lòng nhập vào số nguyên");
        }
    }
}
