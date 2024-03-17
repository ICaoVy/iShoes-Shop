/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Gallery;
import Models.Product;
import Models.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class ProductDAO {

    private PreparedStatement psTP;
    private ResultSet rsTP;
    Connection conn;

    public ProductDAO() {
        try {
            conn = DBConnect.DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Product GetProIdByQuan (int pro_id) {
        Product pro = null;
        String sql = "select * from Product p join Orders_Details od on p.pro_id = od.pro_id join Stock s on s.pro_id = od.pro_id join Orders o on od.order_id = o.order_id where p.pro_id = ?";
        try {
            psTP = conn.prepareStatement(sql);
            psTP.setInt(1, pro_id);          
            rsTP = psTP.executeQuery();
            if (rsTP.next()) {
                pro = new Product(
                        rsTP.getInt("pro_id"),
                        rsTP.getInt("cate_id"),
                        rsTP.getString("pro_code"),
                        rsTP.getString("pro_name"),
                        rsTP.getFloat("pro_price"),
                        rsTP.getFloat("pro_discount"),
                        rsTP.getString("pro_picture"),
                        rsTP.getInt("pro_size"),
                        rsTP.getString("pro_colour"),
                        rsTP.getString("pro_brand"),
                        rsTP.getString("pro_origin"),
                        rsTP.getString("pro_material"),
                        rsTP.getString("pro_description"),
                        rsTP.getDate("pro_create_at"),
                        rsTP.getDate("pro_update_at"),
                        rsTP.getInt("pro_delete_at"),
                        rsTP.getInt("stock_import")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pro;
    }

    public List getAllProductDECS() {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Product where pro_delete_at = 0");
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getAllProduct(String pro_code, int pro_id) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Gallery g on p.pro_id = p.pro_id where p.pro_code =? and p.pro_id =? and p.pro_delete_at = 0 ORDER BY p.pro_id DESC");
            ps.setString(1, pro_code);
            ps.setInt(2, pro_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ResultSet getGalley(int pro_id) {
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from Gallery where pro_id=?");
            ps.setInt(1, pro_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void deleteGallery(int gal_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("delete from Gallery where gal_id=?");
            ps.setInt(1, gal_id);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Gallery newGalerry(Gallery g, int pro_id) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Gallery values(?,?)");
            ps.setInt(1, pro_id);
            ps.setString(2, g.getGal_picture());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : g;
    }

    public int getStockOfProduct(int pro_id) {
        ResultSet rs = null;
        int quantity_product = 0;
        try {

            PreparedStatement ps = conn.prepareStatement("select * from Product p join Stock s on s.pro_id = p.pro_id where p.pro_id = ?");
            ps.setInt(1, pro_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity_product = (rs.getInt("stock_import") - rs.getInt("stock_export"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity_product;
    }

    public List getAllProduct() {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM Product where pro_delete_at = 0 ORDER BY pro_id DESC");
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));

                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProduceCode(String pro_code) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_delete_at = 0 and p.pro_code LIKE ? ORDER BY pro_id DESC");
            ps.setString(1, pro_code + "%");  // Thêm dấu % vào đây thay vì trong câu truy vấn
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProduceCodeEnd(String pro_code) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Stock c on c.pro_id = p.pro_id\n"
                    + "                    where p.pro_delete_at = 0 and p.pro_code LIKE ? ORDER BY p.pro_id DESC");
            ps.setString(1, pro_code + "%");  // Thêm dấu % vào đây thay vì trong câu truy vấn
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ResultSet getProduceCode1(String pro_code) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_delete_at = 0 and p.pro_code LIKE ? ORDER BY pro_id DESC");
            ps.setString(1, pro_code + "%");  // Thêm dấu % vào đây thay vì trong câu truy vấn
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List getProduceSearchName(String pro_name) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "                                      join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "                                     where p.pro_delete_at = 0 and p.pro_name like ? ORDER BY pro_id DESC");
            ps.setString(1, "%" + pro_name + "%");  // Thêm dấu % vào đây thay vì trong câu truy vấn
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ResultSet getProductByProCode(String pro_code) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product where pro_code = ? and pro_delete_at = 0");
            ps.setString(1, pro_code);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Stock getStockByProID(int pro_id, int pro_size) {
        Stock s = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Stock s join Product p on p.pro_id = s.pro_id where p.pro_id = ? and pro_size = ?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                s = new Stock(rs.getInt("stock_id"), rs.getInt("staff_id"), rs.getInt("pro_id"), rs.getInt("stock_import"), rs.getInt("stock_export"), rs.getDate("import_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return s;
    }

    public int getStockOfProduct(String pro_code, int pro_size) {
        ResultSet rs = null;
        int quantity_product = 0;
        try {

            PreparedStatement ps = conn.prepareStatement("select * from Product p join Stock s on s.pro_id = p.pro_id where p.pro_code = ? and p.pro_size = ?");
            ps.setString(1, pro_code);
            ps.setInt(2, pro_size);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity_product = (rs.getInt("stock_import") - rs.getInt("stock_export"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quantity_product;
    }

    public String subString(String pro_code) {
        String s[] = pro_code.split("-");
        return s[0];
    }

    public String subString1(String pro_code) {
        String s[] = pro_code.split("-");
        return s[1];
    }

    public ResultSet getGallery(String pro_code) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Gallery g join Product p on g.pro_id = p.pro_id where p.pro_code = ?");
            ps.setString(1, pro_code);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getProduct(int pro_id) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product where pro_id = ?");
            ps.setInt(1, pro_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public List getProductHigh_Low() {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product ORDER BY pro_price DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductLow_High() {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product ORDER BY pro_price DESC");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByCateName(String cate_name) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductBySize(int pro_size) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ? \n"
                    + "ORDER BY pro_id DESC");
            ps.setInt(1, pro_size);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByColour(String pro_colour) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_colour = ? \n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, pro_colour);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchNoPrice3(String cate_name, int pro_size, String pro_colour) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            ps.setString(3, pro_colour);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchSizeColor(int pro_size, String pro_colour) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setInt(1, pro_size);
            ps.setString(2, pro_colour);

            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchNameAndSize2(String cate_name, int pro_size) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchNameAndColor2(String cate_name, String pro_colour) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_colour = ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setString(2, pro_colour);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchFromTo(String cate_name, int pro_size, String pro_colour, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            ps.setString(3, pro_colour);
            ps.setFloat(4, pro_priceFrom);
            ps.setFloat(5, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceLow(String cate_name, int pro_size, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            ps.setString(3, pro_colour);
            ps.setFloat(4, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceHigh(String cate_name, int pro_size, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            ps.setString(3, pro_colour);
            ps.setFloat(4, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List getProductByAllSearchPriceColorFromTo2(String pro_colour, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_colour = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, pro_colour);
            ps.setFloat(2, pro_priceFrom);
            ps.setFloat(3, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceColorLow2(String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_colour = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, pro_colour);
            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceColorHigh2(String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_colour = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setString(1, pro_colour);
            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getProductByAllSearchPriceSizeFromTo2(int pro_size, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setInt(1, pro_size);
            ps.setFloat(2, pro_priceFrom);
            ps.setFloat(3, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceSizeLow2(int pro_size, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setInt(1, pro_size);

            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceSizeHigh2(int pro_size, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setInt(1, pro_size);

            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getProductByAllSearchNoSizeColorFromTo2(String cate_name, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setFloat(2, pro_priceFrom);
            ps.setFloat(3, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchNoSizeColorLow2(String cate_name, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchNoSizeColorHigh2(String cate_name, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);

            ps.setFloat(2, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getProductByAllSearchNoNameFromTo(int pro_size, String pro_colour, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setInt(1, pro_size);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_priceFrom);
            ps.setFloat(4, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceNoNameLow3(int pro_size, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setInt(1, pro_size);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceNoNameHigh3(int pro_size, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_size = ?\n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setInt(1, pro_size);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getProductByAllSearchFromTo3(String cate_name, int pro_size, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);
            ps.setFloat(3, pro_priceFrom);
            ps.setFloat(4, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceLow3(String cate_name, int pro_size, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);

            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceHigh3(String cate_name, int pro_size, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_size = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setInt(2, pro_size);

            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public List getProductByAllSearchNoSizeFromTo3(String cate_name, String pro_colour, float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_priceFrom);
            ps.setFloat(4, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceNoSizeLow3(String cate_name, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByAllSearchPriceNoSizeHigh3(String cate_name, String pro_colour, float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where c.cate_name = ? \n"
                    + "and p.pro_colour = ?\n"
                    + "and p.pro_price > ?\n"
                    + "ORDER BY pro_id DESC");
            ps.setString(1, cate_name);
            ps.setString(2, pro_colour);
            ps.setFloat(3, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List getProductByPriceLow1(float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setFloat(1, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByPriceHigh1(float pro_price) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_price >= ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setFloat(1, pro_price);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List getProductByPriceFromTo2(float pro_priceFrom, float pro_priceTo) {
        ResultSet rs = null;
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product p join Category c on c.cate_id = p.cate_id\n"
                    + "join Category_Details cd on cd.cateD_id = c.cateD_id\n"
                    + "where p.pro_price >= ? and p.pro_price < ?\n"
                    + "ORDER BY pro_id DESC");

            ps.setFloat(1, pro_priceFrom);
            ps.setFloat(2, pro_priceTo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    ///////////QUOC HAO///////////////////////////////////////////////////////////////
    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from Product p join Stock s on p.pro_id = s.pro_id where pro_delete_at = 0 ORDER BY p.pro_id DESC");
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Product getProductQH(int pro_id) {
        Product pro = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Product where pro_id=?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro = new Product(rs.getInt("pro_id"), rs.getInt("cate_id"), rs.getString("pro_code"), rs.getString("pro_name"), rs.getFloat("pro_price"), rs.getFloat("pro_discount"), rs.getString("pro_picture"), rs.getInt("pro_size"), rs.getString("pro_colour"), rs.getString("pro_brand"), rs.getString("pro_origin"), rs.getString("pro_material"), rs.getString("pro_description"), rs.getDate("pro_create_at"), rs.getDate("pro_update_at"), rs.getInt("pro_delete_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public Product addNew(Product kh) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into Product values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, kh.getCate_id());
            ps.setString(2, kh.getPro_code());
            ps.setString(3, kh.getPro_name());
            ps.setFloat(4, kh.getPro_price());
            ps.setFloat(5, kh.getPro_discount());
            ps.setString(6, kh.getPro_picture());
            ps.setInt(7, kh.getPro_size());
            ps.setString(8, kh.getPro_colour());
            ps.setString(9, kh.getPro_brand());
            ps.setString(10, kh.getPro_origin());
            ps.setString(11, kh.getPro_material());
            ps.setString(12, kh.getPro_description());
            ps.setDate(13, kh.getPro_create_at());
            ps.setDate(14, kh.getPro_update_at());
            ps.setInt(15, kh.getPro_delete_at());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : kh;
    }

    public Product getProductByCode(String pro_code,int pro_size) {
        
        Product rx = null;
        try {
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("select * from Product where pro_code = ? and pro_size=?");
            ps.setString(1, pro_code);
            ps.setInt(2, pro_size);
            ResultSet rs  = ps.executeQuery();
            if (rs.next()) {
                rx = new Product(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getFloat(6), rs.getString(7),
                        rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getDate(14), rs.getDate(15), rs.getInt(16));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rx;
    }

    public void getProductDelete(int pro_id) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Product SET pro_delete_at =? where pro_id =?");
            ps.setInt(1, 1);
            ps.setInt(2, pro_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Product getProductUpdate(int pro_id, Product newinfo) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement(" update Product set cate_id = ?, pro_code = ?, pro_name = ?, pro_price = ?, pro_discount = ?, pro_picture = ?, pro_size = ?, pro_colour = ?, pro_brand = ?, pro_origin=?, pro_material = ?, pro_description=?, pro_update_at=?, pro_delete_at=? where pro_id = ?");
            ps.setInt(1, newinfo.getCate_id());
            ps.setString(2, newinfo.getPro_code());
            ps.setString(3, newinfo.getPro_name());
            ps.setFloat(4, newinfo.getPro_price());
            ps.setFloat(5, newinfo.getPro_discount());
            ps.setString(6, newinfo.getPro_picture());
            ps.setInt(7, newinfo.getPro_size());
            ps.setString(8, newinfo.getPro_colour());
            ps.setString(9, newinfo.getPro_brand());
            ps.setString(10, newinfo.getPro_origin());
            ps.setString(11, newinfo.getPro_material());
            ps.setString(12, newinfo.getPro_description());
            ps.setDate(13, newinfo.getPro_update_at());
            ps.setInt(14, newinfo.getPro_delete_at());
            ps.setInt(15, pro_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : newinfo;
    }

    public ResultSet getCategory_Details() {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category_Details");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCategory_Details(int pro_id) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category_Details cd join Category c on cd.cateD_id = c.cateD_id join Product p on c.cate_id = p.cate_id where p.pro_id = ?");
            ps.setInt(1, pro_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCategoryLuxury() {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category where cateD_id = 1");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCategorySport() {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category where cateD_id = 2");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getCategorySandal() {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category where cateD_id = 3");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    ///////////////////
    ///////////THANH PHUC
    public LinkedList<Product> GetAllPro() {
        LinkedList<Product> list = new LinkedList<>();
        String sql = "select * from Product p join Stock s on p.pro_id = s.pro_id";
        try {
            psTP = conn.prepareStatement(sql);
            rsTP = psTP.executeQuery();
            while (rsTP.next()) {
                Product pro = new Product(rsTP.getInt("pro_id"),
                        rsTP.getInt("cate_id"), rsTP.getString("pro_code"), rsTP.getString("pro_name"), rsTP.getFloat("pro_price"), rsTP.getFloat("pro_discount"), rsTP.getString("pro_picture"),
                        rsTP.getInt("pro_size"), rsTP.getString("pro_colour"), rsTP.getString("pro_brand"), rsTP.getString("pro_origin"), rsTP.getString("pro_material"), rsTP.getString("pro_description"), rsTP.getDate("pro_create_at"),
                        rsTP.getDate("pro_update_at"), rsTP.getInt("pro_delete_at"), rsTP.getInt("stock_import"));
                list.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Product GetProById(int pro_id) {
        Product pro = null;
        String sql = "select *from Product p join Stock s on p.pro_id = s.pro_id where p.pro_id=?";
        try {
            psTP = conn.prepareStatement(sql);
            psTP.setInt(1, pro_id);
            rsTP = psTP.executeQuery();
            if (rsTP.next()) {
                pro = new Product(
                        rsTP.getInt("pro_id"),
                        rsTP.getInt("cate_id"),
                        rsTP.getString("pro_code"),
                        rsTP.getString("pro_name"),
                        rsTP.getFloat("pro_price"),
                        rsTP.getFloat("pro_discount"),
                        rsTP.getString("pro_picture"),
                        rsTP.getInt("pro_size"),
                        rsTP.getString("pro_colour"),
                        rsTP.getString("pro_brand"),
                        rsTP.getString("pro_origin"),
                        rsTP.getString("pro_material"),
                        rsTP.getString("pro_description"),
                        rsTP.getDate("pro_create_at"),
                        rsTP.getDate("pro_update_at"),
                        rsTP.getInt("pro_delete_at"),
                        rsTP.getInt("stock_import")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pro;
    }

public Product GetProId(int pro_id) {
        Product pro = null;
        String sql = "select * from Product p join Stock s on p.pro_id = s.pro_id join Orders_Details od on od.pro_id = s.pro_id where p.pro_id=?";
        try {
            psTP = conn.prepareStatement(sql);
            psTP.setInt(1, pro_id);
            rsTP = psTP.executeQuery();
            if (rsTP.next()) {
                pro = new Product(
                        rsTP.getInt("pro_id"),
                        rsTP.getInt("cate_id"),
                        rsTP.getString("pro_code"),
                        rsTP.getString("pro_name"),
                        rsTP.getFloat("pro_price"),
                        rsTP.getFloat("pro_discount"),
                        rsTP.getString("pro_picture"),
                        rsTP.getInt("pro_size"),
                        rsTP.getString("pro_colour"),
                        rsTP.getString("pro_brand"),
                        rsTP.getString("pro_origin"),
                        rsTP.getString("pro_material"),
                        rsTP.getString("pro_description"),
                        rsTP.getDate("pro_create_at"),
                        rsTP.getDate("pro_update_at"),
                        rsTP.getInt("pro_delete_at"),
                        rsTP.getInt("stock_import")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pro;
    }

    public int UpdateQuantity(int quantity, int pro_id, int subtractQuantity) {
        String sql = "UPDATE Stock SET stock_import=?, stock_export=stock_export + ? WHERE pro_id=?";
        int count = 0;
        try {
            psTP = conn.prepareStatement(sql);
            psTP.setInt(1, quantity);
            psTP.setInt(2, subtractQuantity);
            psTP.setInt(3, pro_id);
            count = psTP.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

   public int UpdateQuan(int pro_id, int detail_quantity) {
        int count = 0;
        try {
            // Check if there are any active orders for the product
            String selectOrdersSQL = "SELECT od.detail_quantity "
                    + "FROM Orders_Details od "
                    + "JOIN Orders o ON od.order_id = o.order_id "
                    + "WHERE od.pro_id = ? "; 
            PreparedStatement selectOrdersStmt = conn.prepareStatement(selectOrdersSQL);
            selectOrdersStmt.setInt(1, pro_id);
            ResultSet rs = selectOrdersStmt.executeQuery();

            int totalOrderedQuantity = 0;
            while (rs.next()) {
                totalOrderedQuantity += rs.getInt("detail_quantity");
            }

            // Calculate the new stock_import value
            int newStockImport = totalOrderedQuantity + detail_quantity;
// Construct the SQL query to update the stock quantity
            String updateStockSQL = "UPDATE Stock "
                    + "SET stock_import = stock_import + ?, stock_export = stock_export - ? "
                    + "WHERE pro_id = ?";

            // Prepare the statement
            PreparedStatement updateStockStmt = conn.prepareStatement(updateStockSQL);
            updateStockStmt.setInt(1, newStockImport); // Set the new stock_import value
            updateStockStmt.setInt(2, detail_quantity); // Set the stock_export value
            updateStockStmt.setInt(3, pro_id); // Set the product ID

            // Execute the update statement
            count = updateStockStmt.executeUpdate();

            // Close the statements and result set
            rs.close();
            selectOrdersStmt.close();
            updateStockStmt.close();
        } catch (SQLException ex) {
            // Handle any SQL exceptions
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    //////////////////////
}
