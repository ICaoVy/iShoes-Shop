/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnection;
import Models.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class CategoryDAO {

    Connection conn;

    public CategoryDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Category addNewCate(Category kh) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into Category values(?,?)");
            ps.setInt(1, kh.getCateD_id());
            ps.setString(2, kh.getCate_name());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : kh;
    }
    
    public Category getUpdateCate(int cate_id, Category newCate) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Category set cateD_id = ?, cate_name = ? where cate_id = ?");
            ps.setInt(1, newCate.getCateD_id());
            ps.setString(2, newCate.getCate_name());
            ps.setInt(3, newCate.getCate_id());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : newCate;
    }
    
    public Category getCategoryID(int cate_id) {
        Category ca = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category where cate_id=?");
            ps.setInt(1, cate_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ca = new Category(rs.getInt("cate_id"), rs.getInt("cateD_id"), rs.getString("cate_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ca;
    }
    
    public ResultSet getCategory_DetailsWhereCateID(int cate_id) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category_Details cd join Category c on cd.cateD_id = c.cateD_id where c.cate_id=?");
            ps.setInt(1, cate_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getLuxury() {
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category c join Category_Details cd on c.cateD_id = cd.cateD_id where c.cateD_id = 1");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getSport() {
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category c join Category_Details cd on c.cateD_id = cd.cateD_id where c.cateD_id = 2");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ResultSet getSandal() {
        ResultSet rs = null;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from Category c join Category_Details cd on c.cateD_id = cd.cateD_id where c.cateD_id = 3");
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

}
