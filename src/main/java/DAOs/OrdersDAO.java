/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

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
 * @author binhtri
 */
public class OrdersDAO {

    PreparedStatement ps;
    Connection conn;
    ResultSet rs;

    public OrdersDAO() {
        try {
            conn = DBConnect.DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int editInforOrderById(int order_id, String order_email, String order_phone, String order_address, String order_note) {
        String sql = "update Orders set order_email=? , order_phone=?, order_address=?, order_note=?  where order_id=?";
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, order_email);
            ps.setString(2, order_phone);
            ps.setString(3, order_address);
            ps.setString(4, order_note);
            ps.setInt(5, order_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int changeOrderById(int order_id) {
        String sql = "Update Orders set order_status = 1 where order_id = ?";
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public void deleteOrderAndDetails(int order_id) {
        try {
            // Xóa dữ liệu từ bảng con Order_Details trước
            String deleteOrderDetailsSQL = "DELETE FROM Orders_Details WHERE order_id = ?";
            PreparedStatement deleteOrderDetailsStmt = conn.prepareStatement(deleteOrderDetailsSQL);
            deleteOrderDetailsStmt.setInt(1, order_id);
            deleteOrderDetailsStmt.executeUpdate();
            deleteOrderDetailsStmt.close();

            // Sau đó xóa dữ liệu từ bảng cha Orders
            String deleteOrderSQL = "DELETE FROM Orders WHERE order_id = ?";
            PreparedStatement deleteOrderStmt = conn.prepareStatement(deleteOrderSQL);
            deleteOrderStmt.setInt(1, order_id);
            deleteOrderStmt.executeUpdate();
            deleteOrderStmt.close();
        } catch (SQLException ex) {
            // Xử lý lỗi
            ex.printStackTrace();
        }
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from Orders o join Orders_Details od on o.order_id = od.order_id where order_status = 0 order by o.order_id DESC");
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int AddOrder(Orders order) {
        int order_id = 0;
        String sql = "insert into Orders values(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getStaff_id());
            ps.setInt(2, order.getCus_id());
            ps.setString(3, order.getOrder_fullname());
            ps.setString(4, order.getOrder_email());
            ps.setString(5, order.getOrder_phone());
            ps.setString(6, order.getOrder_address());
            ps.setString(7, order.getOrder_note());
            ps.setInt(8, order.getOrder_status());
            ps.setDate(9, order.getPay_create_at());
            ps.setInt(10, order.getPay_status());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                order_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order_id;
    }

    public LinkedList<Orders> getListOrderByCusId(int cus_id) {
        LinkedList<Orders> list = new LinkedList<>();
        String sql = "SELECT o.order_id, o.staff_id, o.cus_id, o.order_fullname, o.order_email, o.order_phone, "
                + "o.order_address, o.order_note, o.pay_create_at, p.pro_name, od.detail_quantity, p.pro_picture, "
                + "o.order_status, o.pay_status "
                + "FROM Orders o "
                + "JOIN Staff s ON o.staff_id = s.staff_id "
                + "JOIN Orders_Details od ON o.order_id = od.order_id "
                + "JOIN Product p ON od.pro_id = p.pro_id "
                + "WHERE o.cus_id = ? "
                + "ORDER BY o.order_id DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders(rs.getInt("order_id"), rs.getInt("staff_id"), rs.getInt("cus_id"),
                        rs.getString("order_fullname"), rs.getString("order_email"), rs.getString("order_phone"),
                        rs.getString("order_address"), rs.getString("order_note"), rs.getDate("pay_create_at"),
                        rs.getString("pro_name"), rs.getInt("detail_quantity"), rs.getString("pro_picture"));
                order.setOrder_status(rs.getInt("order_status"));
                order.setPay_status(rs.getInt("pay_status"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, "Error retrieving orders by customer ID", ex);
        }

        return list;
    }

    public Orders getOrderID_ProID(int order_id) {
        Orders order = null;
        String sql = "Select * from Orders o join Orders_Details od on o.order_id = od.order_id join Product p on p.pro_id = od.pro_id where o.order_id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = new Orders(rs.getInt("order_id"), rs.getInt("staff_id"), rs.getInt("cus_id"), rs.getString("order_fullname"), rs.getString("order_email"), rs.getString("order_phone"), rs.getString("order_address"), rs.getString("order_note"), rs.getDate("pay_create_at"), rs.getString("pro_name"), rs.getInt("detail_quantity"), rs.getString("pro_picture"), rs.getFloat("detail_price"), rs.getInt("detail_size"), rs.getString("detail_colour"), rs.getInt("pro_id"));
                order.setOrder_status(rs.getInt("order_status"));
                order.setPay_status(rs.getInt("pay_status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }

    public int editOrderById(int order_id, int status) {
        String sql = "update orders set order_status=? where o_id=?";

        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, order_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public LinkedList<Orders> GetListOrder() {
        LinkedList<Orders> list = new LinkedList<>();
        String sql = "select * from Orders";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Orders order = new Orders(rs.getInt("order_id"), rs.getInt("staff_id"), rs.getInt("cus_id"), rs.getString("order_fullname"), rs.getString("order_email"),
                        rs.getString("order_phone"), rs.getString("order_address"), rs.getString("order_note"), rs.getDate("pay_create_at"), rs.getString("pro_name"), rs.getInt("detail_quantity"), rs.getString("pro_picture"));
                order.setOrder_status(rs.getInt("order_status"));
                order.setPay_status(rs.getInt("pay_status"));
                list.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Orders GetOrderById(int order_id) {
        Orders order = null;
        String sql = "select * from Orders  o join Orders_Details od on o.order_id = od.order_id join Product p on od.pro_id = p.pro_id  where o.order_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = new Orders(rs.getInt("order_id"), rs.getInt("staff_id"), rs.getInt("cus_id"), rs.getString("order_fullname"), rs.getString("order_email"),
                        rs.getString("order_phone"), rs.getString("order_address"), rs.getString("order_note"), rs.getDate("pay_create_at"), rs.getString("pro_name"), rs.getInt("detail_quantity"), rs.getString("pro_picture"));
                order.setOrder_status(rs.getInt("order_status"));
                order.setPay_status(rs.getInt("pay_status"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return order;
    }

    public int editOrderById(int order_id, String status) {
        String sql = "update Orders set order_status=? , pay_status=?  where order_id=?";

        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, order_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int CountOrder() {
        String sql = "select COUNT(*) from Orders";
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
