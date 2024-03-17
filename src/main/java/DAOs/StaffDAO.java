/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DBConnect.DBConnection;
import Models.Staff;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sangn
 */
public class StaffDAO {

    PreparedStatement ps = null;
    ResultSet rs = null;
    Connection conn;

    public StaffDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteStaff(int staff_id, int staff_deleted) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Staff SET staff_deleted = ? where staff_id = ?");
            ps.setInt(1, staff_deleted);
            ps.setInt(2, staff_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> list = new ArrayList<>();
        String query = "select * from Staff where staff_email is not null";
        try {
            Connection conn = DBConnection.getConnection();//Open new connetion to Database
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Staff(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getInt(9)));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Staff getStaffByID(int cus_id) {
        Staff a = new Staff();
        String query = "SELECT * FROM Staff WHERE staff_id = ?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, cus_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Staff(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getInt(9));
            }
            conn.close();
        } catch (Exception e) {
        }
        return a;
    }

    public void insertnewStaff(int role_id, String staff_fullname, String staff_email, String staff_phone, String staff_password, String staff_address, Date staff_create_at, int staff_deleted) {
        String query = "INSERT INTO Staff (role_id, staff_fullname, staff_email, staff_phone, staff_password, staff_address, staff_create_at, staff_deleted) "
                + "     VALUES (?,?,?,?,?,?,?,?);";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, role_id);
            ps.setString(2, staff_fullname);
            ps.setString(3, staff_email);
            ps.setString(4, staff_phone);
            ps.setString(5, getMd5(staff_password));
            ps.setString(6, staff_address);
            ps.setDate(7, staff_create_at);
            ps.setInt(8, staff_deleted);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
        }
    }
    
    
//    public Staff insertnewStaff(Staff s) {
//        int count = 0;
//        try {
//            PreparedStatement ps = conn.prepareStatement("Insert into Staff VALUES (?,?,?,?,?,?,?,?)");
//            ps.setInt(1, s.getRole_id());
//            ps.setString(2, s.getStaff_fullname());
//            ps.setString(3, s.getStaff_email());
//            ps.setString(4, s.getStaff_phone());
//            ps.setString(5, getMd5(s.getStaff_password()));
//            ps.setString(6, s.getStaff_address());
//            ps.setDate(7, s.getStaff_create_at());
//            ps.setInt(8, s.getStaff_deleted());
//            count = ps.executeUpdate();
//
//        } catch (SQLException ex) {
//            Logger.getLogger(StaffDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return (count == 0) ? null : s;
//    }

    public String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void editStaff(int staff_id, int role_id, String staff_fullname, String staff_email, String staff_phone,
            String staff_password, String staff_address, Date staff_create_at, int staff_deleted) {
        String query = "UPDATE Staff SET \n"
                + "role_id= ?, staff_fullname=?, staff_email=?, staff_phone=?, \n"
                + "staff_password=?, staff_address=?, staff_create_at=?, staff_deleted=?\n"
                + "WHERE staff_id= ?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, role_id);
            ps.setString(2, staff_fullname);
            ps.setString(3, staff_email);
            ps.setString(4, staff_phone);
            ps.setString(5, staff_password);
            ps.setString(6, staff_address);
            ps.setDate(7, staff_create_at);
            ps.setInt(8, staff_deleted);
            ps.setInt(9, staff_id);
            ps.executeUpdate();
            conn.close();
        } catch (Exception e) {
        }
    }

    public Staff getStaffisNull() {
        Staff a = new Staff();
        String query = "SELECT * FROM Staff WHERE staff_email is null;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                a = new Staff(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getInt(9));
            }
            conn.close();
        } catch (Exception e) {
        }
        return a;

    }
}
