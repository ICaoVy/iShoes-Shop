/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnection;
import Models.Customer;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author binhtri
 */
public class CustomerDAO {

    Connection conn;

    public CustomerDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from Customer c join Orders o on c.cus_id = o.cus_id join Orders_Details od on o.order_id = od.order_id where o.order_status = 1 order by o.order_id DESC");
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    
        public void unblockCustomer(int cus_id, int cus_deleted) {
        String query = "UPDATE Customer SET cus_deleted=? WHERE cus_id=?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            psLS = conn.prepareStatement(query);
            psLS.setInt(1, cus_deleted);
            psLS.setInt(2, cus_id);
            psLS.executeUpdate();
            conn.close();
        } catch (Exception e) {
        }
    }
        
    public int checkEmailExist(String email) {
        int count = 0;

        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_deleted = 0 and cus_email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count += 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public ResultSet getCustomerByID1(int cus_id) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_id = ?");
            ps.setInt(1, cus_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public Customer updateProfile(Customer cus, int pro_id) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Customer set cus_fullname = ? , cus_phone = ?, cus_birthday = ?, cus_address = ? where cus_id = ?");
            ps.setString(1, cus.getCus_fullname());
            ps.setString(2, cus.getCus_phone());
            ps.setDate(3, cus.getCus_birthday());
            ps.setString(4, cus.getCus_address());
            ps.setInt(5, pro_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : cus;
    }

    PreparedStatement psLS = null;
    ResultSet rsLS = null;

    public List<Customer> getAllCustomer() {
        List<Customer> list = new ArrayList<>();
        String query = "select * from Customer";
        try {
            Connection conn = DBConnection.getConnection();//Open new connetion to Database
            psLS = conn.prepareStatement(query);
            rsLS = psLS.executeQuery();
            while (rsLS.next()) {
                list.add(new Customer(rsLS.getInt(1),
                        rsLS.getString(2),
                        rsLS.getString(3),
                        rsLS.getString(4),
                        rsLS.getString(5),
                        rsLS.getDate(6),
                        rsLS.getString(7),
                        rsLS.getDate(8),
                        rsLS.getInt(9)));
            }
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public Customer getCustomerByID(int cus_id) {
        Customer a = new Customer();
        String query = "SELECT * FROM Customer WHERE cus_id = ?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            psLS = conn.prepareStatement(query);
            psLS.setInt(1, cus_id);
            rsLS = psLS.executeQuery();
            while (rsLS.next()) {
                a = new Customer(rsLS.getInt(1),
                        rsLS.getString(2),
                        rsLS.getString(3),
                        rsLS.getString(4),
                        rsLS.getString(5),
                        rsLS.getDate(6),
                        rsLS.getString(7),
                        rsLS.getDate(8),
                        rsLS.getInt(9));
            }
            conn.close();
        } catch (Exception e) {
        }
        return a;
    }

    public void blockCustomer(int cus_id, int cus_deleted) {
        String query = "UPDATE Customer SET cus_deleted=? WHERE cus_id=?;";
        try {
            Connection conn = DBConnection.getConnection();//mo ket noi voi sql
            psLS = conn.prepareStatement(query);
            psLS.setInt(1, cus_deleted);
            psLS.setInt(2, cus_id);
            psLS.executeUpdate();
            conn.close();
        } catch (Exception e) {
        }
    }

    public ResultSet setChangePassword(int cus_id) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_id = ?");
            ps.setInt(1, cus_id);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

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

    public String updatePass(String cus_password, int cus_id) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Customer SET cus_password = ? where cus_id = ?");
            ps.setString(1, getMd5(cus_password));
            ps.setInt(2, cus_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : cus_password;
    }

    public String checkPassToChange(String password, int cus_id) {
        String checkPass = "";
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Customer where cus_password = ? and cus_id = ?");
            ps.setString(1, getMd5(password));
            ps.setInt(2, cus_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                checkPass = rs.getString("cus_password");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkPass;
    }

    public Customer register(Customer cus) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("insert into Customer values(?,?,?,?,?,?,?,?)");
            ps.setString(1, cus.getCus_fullname());
            ps.setString(2, cus.getCus_email());
            ps.setString(3, cus.getCus_phone());
            ps.setString(4, getMd5(cus.getCus_password()));
            ps.setDate(5, cus.getCus_birthday());
            ps.setString(6, cus.getCus_address());
            ps.setDate(7, cus.getCus_create_at());
            ps.setInt(8, cus.getCus_deleted());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : cus;
    }
    
        public String forgotPassword(String new_password, String email) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE Customer SET cus_password = ? where cus_email = ? and cus_deleted = 0");
            ps.setString(1, getMd5(new_password));
            ps.setString(2, email);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : new_password ;
    }
}
