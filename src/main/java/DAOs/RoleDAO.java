/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;


import DBConnect.DBConnection;
import Models.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sangn
 */
public class RoleDAO {
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<Role> getAllRole() {
        List<Role> list = new ArrayList<>();
        String query = "select * from Role";
        try {
            Connection conn = DBConnection.getConnection();//Open new connetion to Database
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Role(rs.getInt(1),
                                  rs.getString(2)));
            }
            conn.close();
        } catch (Exception e) {
        }
        return list;
    }
    
    public Role getCustomerByID(int cus_id) {
        Role a = new Role();
        String query = "SELECT * FROM Staff WHERE staff_id = ?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, cus_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Role(rs.getInt(1),
                             rs.getString(2));
            }
            conn.close();
        } catch (Exception e) {
        }
        return a;
    }
    
}
