package DAOs;

import Models.Carts;
import Models.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public CartDAO() {

        try {
            conn = DBConnect.DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getCountCartByCusID(int cus_id) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("select count(*) as cart_all from Carts where cus_id = ?");
            ps.setInt(1, cus_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("cart_all");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public LinkedList<Carts> GetListCartByAccID(int cus_id) {
        LinkedList<Carts> list = new LinkedList<>();
        String sql = "SELECT * FROM Carts c join Stock s on c.pro_id = s.pro_id join Product p on p.pro_id = s.pro_id WHERE cus_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cus_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Carts cart = new Carts(
                        rs.getInt("cart_id"),
                        rs.getInt("cus_id"),
                        rs.getInt("pro_id"),
                        rs.getFloat("cart_price"),
                        rs.getInt("cart_quantity"),
                        rs.getInt("cart_size"),
                        rs.getString("cart_colour"),
                        rs.getString("pro_picture"),
                        rs.getString("pro_name")
                );
                list.add(cart);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ResultSet getAll() {
        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            rs = st.executeQuery("select * from Carts c join Product p on c.pro_id = p.pro_id");
        } catch (SQLException ex) {
            Logger.getLogger(OrdersDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public Carts AddCart(Carts c) {
        int count = 0;
        String sql = "INSERT INTO Carts VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCus_id());
            ps.setInt(2, c.getPro_id());
            ps.setFloat(3, c.getCart_price());
            ps.setInt(4, c.getCart_quantity());
            ps.setInt(5, c.getCart_size());
            ps.setString(6, c.getCart_colour());
            count = ps.executeUpdate();
            if (count == 0) {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public int DeleteCart(int cart_id) {
        String sql = "DELETE FROM Carts WHERE cart_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cart_id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Carts UpdateQuan(Carts c) {
        String sql = "UPDATE Carts SET cart_quantity=? WHERE cart_id=? AND pro_id=? and cus_id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCart_quantity());
            ps.setInt(2, c.getCart_id());
            ps.setInt(3, c.getPro_id());
            ps.setInt(4, c.getCus_id());
            int count = ps.executeUpdate();
            if (count > 0) {
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Carts GetCartById(int id) {
        Carts c = null;
        String sql = "select * from Carts c join Product p on c.pro_id = p.pro_id where cart_id =?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Carts(
                        rs.getInt("cart_id"),
                        rs.getInt("cus_id"),
                        rs.getInt("pro_id"),
                        rs.getFloat("cart_price"),
                        rs.getInt("cart_quantity"),
                        rs.getInt("cart_size"),
                        rs.getString("cart_colour"),
                        rs.getString("pro_picture"),
                        rs.getString("pro_name")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public void DeleteItemCart(int cart_id) {
        String sql = "DELETE FROM Carts WHERE cart_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, cart_id);
            ps.executeUpdate();
            ps.close(); // Close PreparedStatement instead of statement
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    public static void main(String[] args) {
        CartDAO dao = new CartDAO();

        try {
            LinkedList<Carts> cart = dao.GetListCartByAccID(1);
            System.out.println(cart.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
