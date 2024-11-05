package ra.business;

import ra.entity.Categories;
import util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesBusiness {
    public static List<Categories> findAllActive() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> lisCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_categories_active()}");
            ResultSet rs = callSt.executeQuery();
            lisCategories = new ArrayList<>();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCatId(rs.getInt("category_id"));
                category.setCatName(rs.getString("category_name"));
                category.setCatStatus(rs.getBoolean("category_status"));
                lisCategories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return lisCategories;
    }
    public static List<Categories> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> lisCategories = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_categories()}");
            ResultSet rs = callSt.executeQuery();
            lisCategories = new ArrayList<>();
            while (rs.next()) {
                Categories category = new Categories();
                category.setCatId(rs.getInt("category_id"));
                category.setCatName(rs.getString("category_name"));
                category.setCatStatus(rs.getBoolean("category_status"));
                lisCategories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return lisCategories;
    }

    public static boolean saveCategories(Categories category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call save_category(?,?)}");
            callSt.setString(1, category.getCatName());
            callSt.setBoolean(2,category.isCatStatus());
            callSt.executeUpdate();
             result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static boolean updateCategories(Categories category) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_category(?,?,?)}");
            callSt.setInt(1, category.getCatId());
            callSt.setString(2, category.getCatName());
            callSt.setBoolean(3,category.isCatStatus());
            callSt.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static Categories findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories category = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_categories_by_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                category = new Categories();
                category.setCatId(rs.getInt("category_id"));
                category.setCatName(rs.getString("category_name"));
                category.setCatStatus(rs.getBoolean("category_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return category;
    }
    public static boolean deleteCategories(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt=conn.prepareCall("{call delete_category(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            result = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static int staticCategories(int categoryId) {
        Connection conn = null;
        CallableStatement callSt = null;
        int cntProducts = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call statistic_product_by_cat(?,?)}");
            callSt.setInt(1, categoryId);
            callSt.registerOutParameter(2, Types.INTEGER);
            callSt.execute();
            cntProducts = callSt.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return cntProducts;
    }


}
