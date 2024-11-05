package ra.business;

import ra.entity.Products;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsBusiness {
    public static List<Products> findAllProducts() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Products> products = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_all_product()}");
            products = new ArrayList<Products>();
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setStock(rs.getInt("stock"));
                product.setCostPrice(rs.getDouble("cost_price"));
                product.setSellingPrice(rs.getDouble("selling_price"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setCatId(rs.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }
    public static Products findProductById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Products product = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call find_product_by_id(?)}");
            callSt.setInt(1, productId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setStock(rs.getInt("stock"));
                product.setCostPrice(rs.getDouble("cost_price"));
                product.setSellingPrice(rs.getDouble("selling_price"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setCatId(rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return product;
    }
    public static boolean deleteProductById(int productId) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            callSt.setInt(1, productId);
            callSt.executeQuery();
            result = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
    public static List<Products> findProductsBySellPrice(double sellingPriceA, double sellingPriceB ) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Products> products = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findProductByPrice(?,?)}");
            callSt.setDouble(1, sellingPriceA);
            callSt.setDouble(2, sellingPriceB);
            ResultSet rs = callSt.executeQuery();
            products = new ArrayList<>();
            while (rs.next()) {
                Products product = new Products();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setStock(rs.getInt("stock"));
                product.setCostPrice(rs.getDouble("cost_price"));
                product.setSellingPrice(rs.getDouble("selling_price"));
                product.setCreatedAt(rs.getTimestamp("created_at"));
                product.setCatId(rs.getInt("category_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return products;
    }
}
