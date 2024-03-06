/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CustomerDAO;
import DAOs.ProductDAO;
import DAOs.RoleDAO;
import DAOs.StaffDAO;
import DAOs.StockDAO;
import Models.Customer;
import Models.Product;
import Models.Role;
import Models.Staff;
import Models.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class AdminController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        if (path.endsWith("/AdminController")) {
            request.getRequestDispatcher("./admin/adminHome.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/MyStore")) {
            request.getRequestDispatcher("/admin/myStore.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/Analytics")) {
            request.getRequestDispatcher("/admin/analytics.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Details")) {
            request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Create")) {//require trang create
            request.getRequestDispatcher("/admin/create.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Delete")) {
            request.getRequestDispatcher("/admin/deleteProduct.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Update")) {
            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Import")) {
            request.getRequestDispatcher("/admin/importproductquantity.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/ListOrder")) {
            request.getRequestDispatcher("/admin/listorder.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/ManegerUser")) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            //khai báo
            CustomerDAO cusdao = new CustomerDAO();
            List<Customer> listCus = cusdao.getAllCustomer();

            StaffDAO staffdao = new StaffDAO();
            List<Staff> listStaff = staffdao.getAllStaff();
            List<Staff> listStaff1 = new ArrayList<Staff>();
            for (Staff ls : listStaff) {
                
                String[] arrOfStr = ls.getStaff_email().split("@", -1);
//                int staff_id, int role_id, String staff_fullname, String staff_email, String staff_phone, String staff_password, String staff_address, Date staff_create_at, int staff_deleted
                listStaff1.add(new Staff(ls.getStaff_id(), ls.getRole_id(), ls.getStaff_fullname(), arrOfStr[0], 
                        ls.getStaff_phone(), ls.getStaff_password(), ls.getStaff_address(), ls.getStaff_create_at(), ls.getStaff_deleted()));
            }
            

            RoleDAO Roledao = new RoleDAO();
            List<Role> listRole = Roledao.getAllRole();

            //load data to jsp
            request.setAttribute("listCus", listCus);
            request.setAttribute("listStaff", listStaff);
            request.setAttribute("listStaff1", listStaff1);
            request.setAttribute("listRole", listRole);
            request.getRequestDispatcher("/admin/manageUserView.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btn-backA") != null) {
            response.sendRedirect("/AdminController/MyStore");
        }
        if (request.getParameter("btn-add") != null) {
            int cate_id = Integer.parseInt(request.getParameter("proCate"));
            String pro_code = request.getParameter("proCode");
            String pro_name = request.getParameter("proName");
            float pro_price = Float.parseFloat(request.getParameter("proPrice"));
            int stock_import = Integer.parseInt(request.getParameter("proQuantity"));
            float pro_discount = Float.parseFloat(request.getParameter("proDiscount"));
            String pro_picture = request.getParameter("proPicture");
            String pro_colour = request.getParameter("proColour");
            int pro_size = Integer.parseInt(request.getParameter("proSize"));
            String pro_brand = request.getParameter("proBrand");
            String pro_origin = request.getParameter("proOrigin");
            String pro_material = request.getParameter("proMaterial");
            String pro_description = request.getParameter("proDescription");
            long currentTimeMillis = System.currentTimeMillis();
            Date pro_create_at = new Date(currentTimeMillis);
            Date pro_update_at = null;
            int pro_delete_at = 0;

            Product newProduct = new Product(0, cate_id, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            ProductDAO pDAO = new ProductDAO();
            Product rs = pDAO.addNew(newProduct);
            if (rs == null) {//them that bai
                response.sendRedirect("/AdminController/Create");
            } else {
                Product rx = pDAO.getProductByCode(pro_code);
                Stock newQuantity = new Stock(0, 1, rx.getPro_id(), stock_import, stock_import, pro_update_at);
                StockDAO stockDAO = new StockDAO();
                Stock st = stockDAO.addNew(newQuantity);
                response.sendRedirect("/AdminController/MyStore");
            }
        }
        if (request.getParameter("btn-cancelID") != null) {
            response.sendRedirect("/AdminController/MyStore");
        }
        if (request.getParameter("btn-deleteD") != null) {
            int pro_id = Integer.parseInt(request.getParameter("id"));
            ProductDAO pDAO = new ProductDAO();
            pDAO.getProductDelete(pro_id);
            response.sendRedirect("/AdminController/MyStore");
        }

        if (request.getParameter("btn-cancel") != null) {
            response.sendRedirect("/AdminController/MyStore");
        }
        if (request.getParameter("btn-update") != null) {
            int pro_id = Integer.parseInt(request.getParameter("id"));
            int cate_id = Integer.parseInt(request.getParameter("proCate"));
            String pro_code = request.getParameter("proCode");
            String pro_name = request.getParameter("proName");
            float pro_price = Float.parseFloat(request.getParameter("proPrice"));
            float pro_discount = Float.parseFloat(request.getParameter("proDiscount"));
            String pro_picture = request.getParameter("proPicture");
            int pro_size = Integer.parseInt(request.getParameter("proSize"));
            String pro_colour = request.getParameter("proColour");
            String pro_brand = request.getParameter("proBrand");
            String pro_origin = request.getParameter("proOrigin");
            String pro_material = request.getParameter("proMaterial");
            String pro_description = request.getParameter("proDes");
            long currentTimeMillis = System.currentTimeMillis();
            Date pro_create_at = new Date(currentTimeMillis);
            Date pro_update_at = null;
            int pro_delete_at = 0;
            int stock_import = Integer.parseInt(request.getParameter("proQuan"));

            Product newProduct = new Product(0, cate_id, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            ProductDAO pDAO = new ProductDAO();
            Product rs = pDAO.getProductUpdate(pro_id, newProduct);
            if (rs == null) {//update that bai
                response.sendRedirect("/AdminController/MyStore");
            } else {
                Product rx = pDAO.getProductByCode(pro_code);
                Stock newQuantity = new Stock(1, 1, rx.getPro_id(), stock_import, stock_import, pro_update_at);
                StockDAO stockDAO = new StockDAO();
                Stock st = stockDAO.getStockUpdate(pro_id, newQuantity);
                response.sendRedirect("/AdminController/Update" + pro_id);
            }
        }

        if (request.getParameter("btn-add-quantity") != null) {
            int pro_code = Integer.parseInt(request.getParameter("id"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            StockDAO sDAO = new StockDAO();
            Stock stock = sDAO.GetStockByProId(pro_code);
            if (stock != null) {
                int currentQuantity = stock.getStock_import();
                int count = sDAO.UpdateQuan(pro_code, (quantity + currentQuantity));
                if (count != 0) {
                    response.sendRedirect("/AdminController/MyStore");
                } else {
                    response.sendRedirect("/AdminController/Import");

                }
            } else {
                response.sendRedirect("/AdminController/Import");

            }
        }

        if (request.getParameter("addStaffbtn") != null) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            //lấy thời gian bắt đầu làm bài
            //khai báo đối tượng current thuộc class LocalDateTime
//        LocalDateTime current = LocalDateTime.now();
//        //sử dụng class DateTimeFormatter để định dạng ngày giờ theo kiểu pattern
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        //sử dụng phương thức format() để định dạng ngày giờ hiện tại rồi gán cho chuỗi formatted
//        String formatted = LocalDateTime.now().format(formatter);
            long currentTimeMillis = System.currentTimeMillis();
            Date staff_create_at = new Date(currentTimeMillis);

            String fullname = request.getParameter("addStaff_fullname");

            String email = request.getParameter("addStaff_email");

            String phone = request.getParameter("addStaff_phone");

            String password = request.getParameter("addStaff_password");

            String address = request.getParameter("addStaff_address");

            StaffDAO staffDao = new StaffDAO();
//         public Staff(int staff_id, int role_id, String staff_fullname, String staff_email, String staff_phone, String staff_password, String staff_address, Date staff_create_at, int staff_deleted) {
//    
            staffDao.insertnewStaff(2, fullname, email+"@ishoes.vn", phone, password, address, staff_create_at, 0);
            response.sendRedirect("/AdminController/ManegerUser");
        }

        if (request.getParameter("delete-btn") != null) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String id = request.getParameter("cus_id");

            CustomerDAO cusdao = new CustomerDAO();
            cusdao.blockCustomer(Integer.parseInt(id), 1);
            response.sendRedirect("/AdminController/ManegerUser");
        }
        
        if (request.getParameter("editStaff-btn") != null) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            StaffDAO staffDao = new StaffDAO();

//        //lấy thời gian bắt đầu làm bài
//        //khai báo đối tượng current thuộc class LocalDateTime
//        LocalDateTime current = LocalDateTime.now();
//        //sử dụng class DateTimeFormatter để định dạng ngày giờ theo kiểu pattern
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        //sử dụng phương thức format() để định dạng ngày giờ hiện tại rồi gán cho chuỗi formatted
//        String formatted = LocalDateTime.now().format(formatter);
        
        String id = request.getParameter("editStaff_id");
        
        Date create_at = staffDao.getStaffByID(Integer.parseInt(id)).getStaff_create_at();
        
        String fullname = request.getParameter("editStaff_fullname");
        
        String email = request.getParameter("editStaff_email");
        
        String phone = request.getParameter("editStaff_phone");
        
        String password = request.getParameter("editStaff_password");
        
        String address = request.getParameter("editStaff_address");
        
        Staff st = staffDao.getStaffByID(Integer.parseInt(id));
        
            if (!fullname.equals(st.getStaff_fullname())||!email.equals(st.getStaff_email())||!phone.equals(st.getStaff_phone())||!password.equals(st.getStaff_password())||!address.equals(st.getStaff_address())) {
                staffDao.editStaff(Integer.parseInt(id), 2, fullname, email+"@ishoes.vn", phone, password, address, create_at, 0);
            }else {
                JFrame frame = new JFrame("JOptionPane showMessageDialog example");
                JOptionPane.showMessageDialog(frame,"Please fill in information different from the information already have!","Error Edit Staff",JOptionPane.ERROR_MESSAGE);   
            }
            
        
        
        response.sendRedirect("/AdminController/ManegerUser");
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
