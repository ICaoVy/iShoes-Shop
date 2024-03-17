/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CategoryDAO;
import DAOs.CustomerDAO;
import DAOs.OrdersDAO;
import DAOs.ProductDAO;
import DAOs.RoleDAO;
import DAOs.StaffDAO;
import DAOs.StockDAO;
import Models.Category;
import Models.Customer;
import Models.Gallery;
import Models.Product;
import Models.Role;
import Models.Staff;
import Models.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
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
@MultipartConfig(
        location = "C:\\NEWPROJECT\\Back_End_iShoes_Shop_Group5\\Back_End_iShoes_Shop_Group5\\src\\main\\webapp\\images",
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 11
)
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
        } else if (path.endsWith("/AdminController/UpdateCategory")) {
            request.getRequestDispatcher("/admin/updateCategory.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/CreateCategory")) {
            request.getRequestDispatcher("/admin/addCategory.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/MyStore")) {
            request.getRequestDispatcher("/admin/myStore.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/Analytics")) {
            request.getRequestDispatcher("/admin/analytics.jsp").forward(request, response);
        } else if (path.endsWith("/AdminController/Gallery")) {
            request.getRequestDispatcher("/admin/gallery.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/GANEW/Delete")) {
            int pro_id = Integer.parseInt(request.getParameter("pro_id"));
            int gal_id = Integer.parseInt(request.getParameter("gal_id"));
            String pro_code = request.getParameter("pro_code");
            ProductDAO pDAO = new ProductDAO();
            pDAO.deleteGallery(gal_id);
            response.sendRedirect("/AdminController/GANEW?pro_code=" + pro_code + "&pro_id=" + pro_id);
        } else if (path.startsWith("/AdminController/GANEW")) {
            request.getRequestDispatcher("/admin/ganew.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Details")) {
            request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Create")) {//require trang create
            request.getRequestDispatcher("/admin/create.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Delete")) {
            request.getRequestDispatcher("/admin/deleteProduct.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Update")) {
            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/MyCategory")) {
            request.getRequestDispatcher("/admin/myCategory.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/ListOrder")) {
            request.getRequestDispatcher("/admin/listorder.jsp").forward(request, response);
        }else if (path.startsWith("/AdminController/ListCustomerOrder")) {
            request.getRequestDispatcher("/admin/listcustomerorder.jsp").forward(request, response);
        } else if (path.startsWith("/AdminController/Import")) {
            request.getRequestDispatcher("/admin/importproductquantity.jsp").forward(request, response);
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
//        String path = request.getRequestURI();
//        if (path.endsWith("/AdminController")) {
//            request.getRequestDispatcher("./admin/adminHome.jsp").forward(request, response);
//        } else if (path.endsWith("/AdminController/MyStore")) {
//            request.getRequestDispatcher("/admin/myStore.jsp").forward(request, response);
//        } else if (path.endsWith("/AdminController/Analytics")) {
//            request.getRequestDispatcher("/admin/analytics.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/Details")) {
//            request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/Create")) {//require trang create
//            request.getRequestDispatcher("/admin/create.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/Delete")) {
//            request.getRequestDispatcher("/admin/deleteProduct.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/Update")) {
//            request.getRequestDispatcher("/admin/updateProduct.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/Import")) {
//            request.getRequestDispatcher("/admin/importproductquantity.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/ListOrder")) {
//            request.getRequestDispatcher("/admin/listorder.jsp").forward(request, response);
//        } else if (path.startsWith("/AdminController/ManegerUser")) {
//            response.setCharacterEncoding("UTF-8");
//            request.setCharacterEncoding("UTF-8");
//
//            //khai báo
//            CustomerDAO cusdao = new CustomerDAO();
//            List<Customer> listCus = cusdao.getAllCustomer();
//
//            StaffDAO staffdao = new StaffDAO();
//            List<Staff> listStaff = staffdao.getAllStaff();
//            List<Staff> listStaff1 = new ArrayList<Staff>();
//            for (Staff ls : listStaff) {
//
//                String[] arrOfStr = ls.getStaff_email().split("@", -1);
////                int staff_id, int role_id, String staff_fullname, String staff_email, String staff_phone, String staff_password, String staff_address, Date staff_create_at, int staff_deleted
//                listStaff1.add(new Staff(ls.getStaff_id(), ls.getRole_id(), ls.getStaff_fullname(), arrOfStr[0],
//                        ls.getStaff_phone(), ls.getStaff_password(), ls.getStaff_address(), ls.getStaff_create_at(), ls.getStaff_deleted()));
//            }
//
//            RoleDAO Roledao = new RoleDAO();
//            List<Role> listRole = Roledao.getAllRole();
//
//            //load data to jsp
//            request.setAttribute("listCus", listCus);
//            request.setAttribute("listStaff", listStaff);
//            request.setAttribute("listStaff1", listStaff1);
//            request.setAttribute("listRole", listRole);
//            request.getRequestDispatcher("/admin/manageUserView.jsp").forward(request, response);
//        }
//Edit status

        if (path.startsWith("/AdminController/ChangeStatus/")) {
            String[] split = path.trim().split("/");
            OrdersDAO odao = new OrdersDAO();
            try {
                int id = Integer.parseInt(split[split.length - 1]);
                int o = odao.changeOrderById(id);
                response.sendRedirect("/AdminController/ListOrder");
            } catch (Exception e) {
                response.sendRedirect("/AdminController/ListOrder");
            }
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

        if (request.getParameter("btn-anganew") != null) {
            int pro_id = Integer.parseInt(request.getParameter("pro_id"));
            Part filePart = request.getPart("file-picture");
            String gal_picture = getFileName(filePart);
            ProductDAO pDAO = new ProductDAO();
            Gallery g = pDAO.newGalerry(new Gallery(0, pro_id, gal_picture), pro_id);
            if (g != null) {
                try {
                    Part part2 = request.getPart("file-picture");
                    filePart.write(getFileName(part2));
                    String pro_code = request.getParameter("pro_code");
                    response.sendRedirect("/AdminController/GANEW?pro_code=" + pro_code + "&pro_id=" + pro_id);
                } catch (Exception e) {
                    response.sendRedirect("/AdminController/Gallery");

                }
            } else {
                response.sendRedirect("/AdminController/Gallery");
            }
        }

        if (request.getParameter("btn-backCateU") != null) {
            response.sendRedirect("/AdminController/MyCategory");
        }

        if (request.getParameter("btn_backCa") != null) {
            response.sendRedirect("/AdminController/MyCategory");
        }

        if (request.getParameter("btn-backA") != null) {
            response.sendRedirect("/AdminController/MyStore");
        }
        if (request.getParameter("btn-add") != null) {
            int cateD_id = Integer.parseInt(request.getParameter("cateD_id"));
            int cate_idL = Integer.parseInt(request.getParameter("cate_idL"));
            int cate_idS = Integer.parseInt(request.getParameter("cate_idS"));
            int cate_idSP = Integer.parseInt(request.getParameter("cate_idSP"));
            String pro_code = request.getParameter("proCode");
            String pro_name = request.getParameter("proName");
            float pro_price = Float.parseFloat(request.getParameter("proPrice"));
            int stock_import = Integer.parseInt(request.getParameter("proQuantity"));
            float pro_discount = Float.parseFloat(request.getParameter("proDiscount"));
            Part filePart = request.getPart("proPicture");
            String pro_picture = getFileName(filePart);
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
            Product newProduct = null;
            if (cateD_id == 1) {
                newProduct = new Product(0, cate_idL, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            } else if (cateD_id == 2) {
                newProduct = new Product(0, cate_idSP, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            } else if (cateD_id == 3) {
                newProduct = new Product(0, cate_idS, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            }
            ProductDAO pDAO = new ProductDAO();
            Product rs = pDAO.addNew(newProduct);
            if (rs == null) {//them that bai
                response.sendRedirect("/AdminController/Create");
            } else {
                try {
                    int staff_id = 1;
                    Cookie cookies[] = request.getCookies();
                    if (cookies != null) {
                        for (Cookie cooky : cookies) {
                            if (cooky.getName().equalsIgnoreCase("staff_idC")) {
                                staff_id = Integer.parseInt(cooky.getValue());
                            }
                        }
                    }
                    Part part2 = request.getPart("proPicture");
                    filePart.write(getFileName(part2));
                    Product rx = pDAO.getProductByCode(pro_code, pro_size);
                    Stock newQuantity = new Stock(0, staff_id, rx.getPro_id(), stock_import, 0, pro_create_at);
                    StockDAO stockDAO = new StockDAO();
                    Stock st = stockDAO.addNew(newQuantity);
                    if (st != null) {
                        response.sendRedirect("/AdminController/MyStore");
                    } else {
                        response.sendRedirect("/AdminController/Create");
                    }
                } catch (Exception e) {
                    response.sendRedirect("/AdminController/Create");
                }

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
            int cateD_id = Integer.parseInt(request.getParameter("cateD_id"));
            int cate_idL = Integer.parseInt(request.getParameter("cate_idL"));
            int cate_idS = Integer.parseInt(request.getParameter("cate_idS"));
            int cate_idSP = Integer.parseInt(request.getParameter("cate_idSP"));
            String pro_code = request.getParameter("proCode");
            String pro_name = request.getParameter("proName");
            float pro_price = Float.parseFloat(request.getParameter("proPrice"));
            float pro_discount = Float.parseFloat(request.getParameter("proDiscount"));
            Part filePart = request.getPart("proPicture");
            String pro_picture = getFileName(filePart);
            int pro_size = Integer.parseInt(request.getParameter("proSize"));
            String pro_colour = request.getParameter("proColour");
            String pro_brand = request.getParameter("proBrand");
            String pro_origin = request.getParameter("proOrigin");
            String pro_material = request.getParameter("proMaterial");
            String pro_description = request.getParameter("proDes");
            long currentTimeMillis = System.currentTimeMillis();
            Date pro_create_at = null;
            Date pro_update_at = new Date(currentTimeMillis);
            int pro_delete_at = 0;
            int stock_import = Integer.parseInt(request.getParameter("proQuan"));
            Product newProduct = null;
            if (cateD_id == 1) {
                newProduct = new Product(0, cate_idL, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            } else if (cateD_id == 2) {
                newProduct = new Product(0, cate_idSP, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            } else if (cateD_id == 3) {
                newProduct = new Product(0, cate_idS, pro_code, pro_name, pro_price, pro_discount, pro_picture, pro_size, pro_colour, pro_brand, pro_origin, pro_material, pro_description, pro_create_at, pro_update_at, pro_delete_at);
            }
            ProductDAO pDAO = new ProductDAO();
            Product rs = pDAO.getProductUpdate(pro_id, newProduct);
            if (rs != null) {//update thanh cong
                try {
                    Part part2 = request.getPart("proPicture");
                    filePart.write(getFileName(part2));

                    response.sendRedirect("/AdminController/MyStore");
                } catch (Exception e) {
                    Product rx = pDAO.getProductByCode(pro_code, pro_size);
                    Stock newQuantity = new Stock(1, 1, rx.getPro_id(), stock_import, stock_import, pro_update_at);
                    StockDAO stockDAO = new StockDAO();
                    Stock st = stockDAO.getStockUpdate(pro_id, newQuantity);
                    response.sendRedirect("/AdminController/Update?id=" + pro_id);
                }

            } else {
                Product rx = pDAO.getProductByCode(pro_code, pro_size);
                Stock newQuantity = new Stock(1, 1, rx.getPro_id(), stock_import, stock_import, pro_update_at);
                StockDAO stockDAO = new StockDAO();
                Stock st = stockDAO.getStockUpdate(pro_id, newQuantity);
                response.sendRedirect("/AdminController/Update?id=" + pro_id);
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

        if (request.getParameter("btn-addC") != null) {
            int cateD_id = Integer.parseInt(request.getParameter("cateD_id"));
            String cate_name = request.getParameter("cate_name");
            Category newCate = new Category(0, cateD_id, cate_name);
            CategoryDAO cateDAO = new CategoryDAO();
            Category rs = cateDAO.addNewCate(newCate);
            if (rs == null) {
                response.sendRedirect("/AdminController/CreateCategory");
            } else {
                response.sendRedirect("/AdminController/MyCategory");
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
            staffDao.insertnewStaff(2, fullname, email, phone, password, address, staff_create_at, 0);
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

        if (request.getParameter("deleteStaff") != null) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String id = request.getParameter("staff_id");
            StaffDAO td = new StaffDAO();
            td.deleteStaff(Integer.parseInt(id), 1);
            response.sendRedirect("/AdminController/ManegerUser");
        }

        if (request.getParameter("unblockCustomer-btn") != null) {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");

            String id = request.getParameter("cus_id");

            CustomerDAO cusdao = new CustomerDAO();
            cusdao.unblockCustomer(Integer.parseInt(id), 0);
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

            staffDao.editStaff(Integer.parseInt(id), 2, fullname, email, phone, password, address, create_at, 0);

            response.sendRedirect("/AdminController/ManegerUser");
        }

        if (request.getParameter("btn-upCate") != null) {
            int cate_id = Integer.parseInt(request.getParameter("id"));
            int cateD_id = Integer.parseInt(request.getParameter("cateD_id"));
            String cate_name = request.getParameter("newCate");

            CategoryDAO cDAO = new CategoryDAO();
            Category c = cDAO.getUpdateCate(cate_id, new Category(cate_id, cateD_id, cate_name));
            if (c != null) {
                response.sendRedirect("/AdminController/MyCategory");
            } else {
                response.sendRedirect("/AdminController/UpdateCategory?id=" + cate_id);
            }
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        if (!contentDisposition.contains("filename=")) {
            return null;
        }

        int beginIndex = contentDisposition.indexOf("filename=") + 10;
        int endIndex = contentDisposition.length() - 1;
        return contentDisposition.substring(beginIndex, endIndex);
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
