/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Customer;
import Models.Staff;
import Models.UserGoogleDto;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class AccountDAO {

    Connection conn;

    public AccountDAO() {
        try {
            conn = DBConnect.DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * hàm dùng để chuyển đổi chuổi sang md5
     *
     * @param input
     * @return
     */
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

    public Staff loginByStaff(String email, String password) {
        Staff staff = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Staff where staff_email = ? and staff_password = ? and staff_deleted = 0");
            ps.setString(1, email);
            ps.setString(2, getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                staff = new Staff(rs.getInt("staff_id"), rs.getInt("role_id"), rs.getString("staff_fullname"), rs.getString("staff_email"), rs.getString("staff_phone"), rs.getString("staff_password"), rs.getString("staff_address"), rs.getDate("staff_create_at"), rs.getInt("staff_deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return staff;
    }

    public Customer loginByCustomer(String email, String password) {
        Customer cus = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_email = ? and cus_password = ? and cus_deleted = 0");
            ps.setString(1, email);
            ps.setString(2, getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cus = new Customer(rs.getInt("cus_id"), rs.getString("cus_fullname"), rs.getString("cus_email"), rs.getString("cus_phone"), rs.getString("cus_password"), rs.getDate("cus_birthday"), rs.getString("cus_address"), rs.getDate("cus_create_at"), rs.getInt("cus_deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public Customer checkLoginByGoogle(String email) {
        Customer cus = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_email = ? and cus_deleted = 400");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cus = new Customer(rs.getInt("cus_id"), rs.getString("cus_fullname"), rs.getString("cus_email"), rs.getString("cus_phone"), rs.getString("cus_password"), rs.getDate("cus_birthday"), rs.getString("cus_address"), rs.getDate("cus_create_at"), rs.getInt("cus_deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public Customer loginByGoogle(String email, String password) {
        Customer cus = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_email = ? and cus_password = ? and cus_deleted = 400");
            ps.setString(1, email);
            ps.setString(2, getMd5(password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cus = new Customer(rs.getInt("cus_id"), rs.getString("cus_fullname"), rs.getString("cus_email"), rs.getString("cus_phone"), rs.getString("cus_password"), rs.getDate("cus_birthday"), rs.getString("cus_address"), rs.getDate("cus_create_at"), rs.getInt("cus_deleted"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cus;
    }

    public UserGoogleDto loginByGoogleButDontInDatabase(UserGoogleDto user) {
        int count = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Date cus_create_at = new Date(currentTimeMillis);
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, null);
            ps.setString(4, getMd5(user.getId()));
            ps.setDate(5, null);
            ps.setString(6, null);
            ps.setDate(7, cus_create_at);
            ps.setInt(8, 400);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : user;
    }

//    /**
//     * Hàm dùng để mã hóa chuổi để đẩy lên cookie
//     *
//     * @param input là chuổi được truyền vào
//     * @return chuổi được mã hóa
//     */
//    public String encodeString(String input) {
//        byte[] encodedBytes = Base64.getEncoder().encode(input.getBytes(StandardCharsets.UTF_8));
//        return new String(encodedBytes);
//    }
//
//    /**
//     * hàm dùng để giải mã chuổi đã được mã hóa khi lấy dữ liệu từ cookie xuống
//     *
//     * @param encodedString là chuổi được truyền vào
//     * @return chuổi đã được giải mã
//     */
//    public String decodeString(String encodedString) {
//        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes(StandardCharsets.UTF_8));
//        return new String(decodedBytes);
//    }
    // Hàm mã hóa văn bản thành Base64
    public static String encodeString(String text) {
        try {
            byte[] encodedBytes = Base64.getEncoder().encode(text.getBytes("UTF-8"));
            return new String(encodedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Hàm giải mã Base64 thành văn bản
    public static String decodeString(String encodedText) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedText.getBytes("UTF-8"));
            return new String(decodedBytes, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
