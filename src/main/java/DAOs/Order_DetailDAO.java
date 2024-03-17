/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Order_Details;
import Models.Orders;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Order_DetailDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Order_DetailDAO() {
        try {
            conn = DBConnect.DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Order_DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Order_DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Order_Details AddOrderDetail(Order_Details order) {
        int order_id = 0;
        String sql = "insert into Orders_Details values(?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getPro_id());
            ps.setInt(2, order.getOrder_id());
            ps.setFloat(3, order.getDetail_price());
            ps.setInt(4, order.getDetail_quantity());
            ps.setInt(5, order.getDetail_size());
            ps.setString(6, order.getDetail_colour());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
    
    public LinkedList<Order_Details> GetListViewDetailByOId(int order_id) {
        LinkedList<Order_Details> list = new LinkedList<>();
        String sql = "select * from Orders_Details od join Orders o on od.order_id = o.order_id join Product p on p.pro_id = od.pro_id where od.order_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order_d = new Orders(rs.getInt("order_id"), rs.getInt("staff_id"), rs.getInt("cus_id"), rs.getString("order_fullname"), rs.getString("order_email"), rs.getString("order_phone"), rs.getString("order_address"), rs.getString("order_note"), rs.getDate("pay_create_at"), rs.getString("pro_name"), rs.getInt("detail_quantity"), rs.getString("pro_picture"), rs.getFloat("detail_price"), rs.getInt("detail_size"), rs.getString("detail_colour"));
                order_d.setOrder_status(rs.getInt("order_status"));
                order_d.setPay_status(rs.getInt("pay_status"));
                list.add(order_d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Order_Details GetDetailID(int order_id) {
        Order_Details od = null;
        String sql = "select * from Orders_Details od join Product p on od.pro_id = p.pro_id where od.order_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                od = new Order_Details(rs.getInt("detail_id"), rs.getInt("pro_id"), rs.getInt("order_id"), rs.getFloat("detail_price"), rs.getInt("detail_quantity"), rs.getInt("detail_size"), rs.getString("detail_colour"), rs.getString("pro_picture"), rs.getString("pro_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return od;
    }

    public LinkedList<Order_Details> GetListDetailByOId(int order_id) {
        LinkedList<Order_Details> list = new LinkedList<>();
        String sql = "select * from Orders_Details od join Product p on od.pro_id  = p.pro_id  where od.order_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order_Details order_d = new Order_Details(rs.getInt("detail_id"), rs.getInt("pro_id"),
                        rs.getInt("order_id"), rs.getFloat("detail_price"), rs.getInt("detail_quantity"),
                        rs.getInt("detail_size"), rs.getString("detail_colour"), rs.getString("pro_picture"));
                list.add(order_d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Order_DetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        Order_DetailDAO dao = new Order_DetailDAO();
        LinkedList<Order_Details> list = dao.GetListDetailByOId(5);
        System.out.println("list size:" + list.size());
    }
}
