/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnection;
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

    public ResultSet getLuxyry() {
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
