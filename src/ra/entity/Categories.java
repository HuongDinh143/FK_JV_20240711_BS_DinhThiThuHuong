package ra.entity;

import ra.business.CategoriesBusiness;

import java.util.List;
import java.util.Scanner;

public class Categories {
    private int catId;
    private String catName;
    private boolean catStatus;

    public Categories() {
    }

    public Categories(int catId, String catName, boolean catStatus) {
        this.catId = catId;
        this.catName = catName;
        this.catStatus = catStatus;
    }

    public int getCatId() {
        return this.catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return this.catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isCatStatus() {
        return this.catStatus;
    }

    public void setCatStatus(boolean catStatus) {
        this.catStatus = catStatus;
    }
    public void inputData(Scanner scanner) {
        this.catName = intputName(scanner);
        this.catStatus = inputCatStatus(scanner);
    }
    public static String intputName(Scanner scanner) {
        List<Categories> categoriesList = CategoriesBusiness.findAll();
        System.out.println("Nhập vào tên danh mục");
        do{
            String catName = scanner.nextLine().trim();
            if (catName.isEmpty()){
                System.err.println("Tên danh mục không được trống vui lòng nhập lại");
                continue;
            }else if(categoriesList != null && !categoriesList.isEmpty()){
                boolean isExist = false;
                for (Categories c : categoriesList) {
                    if(c.getCatName().equals(catName)){
                        System.err.println("Tên danh mục không được trùng, vui lòng nhập lại");
                        isExist = true;
                        break;
                    }
                }
                if(!isExist){
                    return catName;
                }
            }else {
                return catName;
            }
        }while (true);
    }
    public static boolean inputCatStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục true: không hoạt động, false: hoạt động");
        do{
            try {
                return Boolean.parseBoolean(scanner.nextLine());
            }catch (NumberFormatException e){
                System.err.println("Vui lòng nhập true hoặc false");
            }
        }while (true);
    }
    @Override
    public String toString() {
        return "Categories{" +
                "Mã danh mục=" + this.catId +
                ", Tên danh mục='" + this.catName + '\'' +
                ", Trạng thái=" + this.catStatus +
                '}';
    }
}
