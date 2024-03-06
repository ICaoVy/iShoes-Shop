/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class DBConnection {
        public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //ket noi co so du lieu (khai bao driver)
        //khai bao driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        //tao đối tượng connection thogn qua drivermanager
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://CAOVY-1902T\\MSSQLSERVERICAO:1433;databaseName=Shop_iShoes_Group5;user=sa;password=1;encrypt=true;trustServerCertificate=true;");
        return conn;
    }
}
