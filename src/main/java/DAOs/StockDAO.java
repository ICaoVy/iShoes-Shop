/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DBConnect.DBConnection;
import Models.Product;
import Models.Stock;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class StockDAO {

    Connection conn;
    private PreparedStatement psBT;
    private ResultSet rs;

    public StockDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stock addNew(Stock kh) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("Insert into Stock values(?,?,?,?,?)");
            ps.setInt(1, kh.getStaff_id());
            ps.setInt(2, kh.getPro_id());
            ps.setInt(3, kh.getStock_import());
            ps.setInt(4, kh.getStock_export());
            ps.setDate(5, kh.getImport_at());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : kh;
    }

    public Stock getStock(int pro_id) {
        Stock st = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Stock where pro_id=?");
            ps.setInt(1, pro_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                st = new Stock(rs.getInt("stock_id"), rs.getInt("staff_id"), rs.getInt("pro_id"), rs.getInt("stock_import"), rs.getInt("stock_export"), rs.getDate("import_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }
    
        public Stock getStockByProCodeAndSize(int pro_size,String pro_code) {
        Stock st = null;
        try {
            PreparedStatement ps = conn.prepareStatement("select * from Stock s join Product p on s.pro_id = p.pro_id where p.pro_size=? and p.pro_code=?");
            ps.setInt(1, pro_size);
            ps.setString(2, pro_code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                st = new Stock(rs.getInt("stock_id"), rs.getInt("staff_id"), rs.getInt("pro_id"), rs.getInt("stock_import"), rs.getInt("stock_export"), rs.getDate("import_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    public Stock getStockUpdate(int pro_id, Stock newinfo) {
        int count = 0;
        try {
            PreparedStatement ps = conn.prepareStatement("update Stock set stock_import = ? where pro_id = ?");
            ps.setInt(1, newinfo.getStock_import());
            ps.setInt(2, pro_id);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (count == 0) ? null : newinfo;
    }

    public LinkedList<Stock> GetListStockByAccID(int stock_id) {
        LinkedList<Stock> list = new LinkedList<>();
        String sql = "select * from Stock s join Product p on s.pro_id = p.pro_id where stock_id=?";
        try {
            psBT = conn.prepareStatement(sql);
            psBT.setInt(1, stock_id);
            rs = psBT.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock(rs.getInt("stock_id"), rs.getInt("staff_id"),
                        rs.getInt("pro_id"), rs.getInt("stock_import"), rs.getInt("stock_export"), rs.getDate("import_at"));
                list.add(stock);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Stock GetStockByProId(int pro_id) {
        Stock c = null;
        String sql = "select * from Stock where pro_id=?";
        try {
            psBT = conn.prepareStatement(sql);
            psBT.setInt(1, pro_id);
            rs = psBT.executeQuery();
            if (rs.next()) {
                c = new Stock(rs.getInt("stock_id"), rs.getInt("staff_id"),
                        rs.getInt("pro_id"), rs.getInt("stock_import"), rs.getInt("stock_export"), rs.getDate("import_at"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    public int UpdateQuan(int pro_id, int quantity) {
        int count = 0;
        long currentTimeMillis = System.currentTimeMillis();
        Date pro_create_at = new Date(currentTimeMillis);

        String sql = "update Stock set stock_import=?, import_at=? where pro_id=?";
        try {
            psBT = conn.prepareStatement(sql);
            psBT.setInt(1, quantity);
            psBT.setDate(2, pro_create_at);
            psBT.setInt(3, pro_id);
            count = psBT.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StockDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (count == 0) ? null : count;
    }

}
