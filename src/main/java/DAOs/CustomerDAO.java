/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnection;
import Models.Customer;
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
            rs = st.executeQuery("select * from Customer c join Orders o on c.cus_id = o.cus_id");
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
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
}
