/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.Order_DetailDAO;
import DAOs.OrdersDAO;
import DAOs.ProductDAO;
import DAOs.StaffDAO;
import Models.Carts;
import Models.Customer;
import Models.Order_Details;
import Models.Orders;
import Models.Product;
import Models.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class OrderController extends HttpServlet {

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
            out.println("<title>Servlet OrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderController at " + request.getContextPath() + "</h1>");
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
        OrdersDAO odao = new OrdersDAO();
        Order_DetailDAO oddao = new Order_DetailDAO();
        ProductDAO pdao = new ProductDAO();
        int cusID = 0;
        HttpSession session = request.getSession();
        String cus_id = null;
        Cookie[] cookie = null;
        cookie = request.getCookies();
        if (cookie == null) {
            response.sendRedirect("/LoginController");
        } else {//neu mang khong rong
            boolean flag = false;//dat bien kiem tra
            for (int i = 0; i < cookie.length; i++) {
                if (cookie[i].getName().equals("idC")) {
                    cus_id = cookie[i].getValue();
                    flag = true;
                    break;

                }
            }
            if (!flag) {// !flag tuc la flag=false, nghia la nguoi dung chua dang nhap
                response.sendRedirect("/LoginController");//chuyen den trang login va bat nguoi dung login lai
            } else {
                String path = request.getRequestURI();
                if (path.endsWith("/OrderController")) {
                    request.getRequestDispatcher("./customer/pay.jsp").forward(request, response);
                } else {
                    if (path.endsWith("/OrderController/Ordered")) {
                        cusID = Integer.parseInt(cus_id);
                        LinkedList<Orders> order = odao.getListOrderByCusId(cusID);
                        session.setAttribute("orders", order);
                        request.getRequestDispatcher("../customer/purchasehistory.jsp").forward(request, response);

                    } else if (path.startsWith("/OrderController/Ordered/cancel/")) {
                        String[] split = path.trim().split("/");
                        try {
                            int order_id = Integer.parseInt(split[split.length - 1]);

                            Orders o = odao.GetOrderById(order_id);

                            if (o.getOrder_status() == 0) {
                                LinkedList<Order_Details> orderDetails = oddao.GetListDetailByOId(order_id);

                                for (Order_Details od : orderDetails) {
                                    int pro_id = od.getPro_id();
                                    int detail_quantity = od.getDetail_quantity();
                                    int updateQuan = pdao.UpdateQuan(pro_id, detail_quantity);
                                }

                                odao.deleteOrderAndDetails(order_id);
                            }

                            response.sendRedirect("/OrderController/Ordered");
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("/OrderController/Ordered");
                        }
                    } else if (path.endsWith("/OrderController/Creditcard")) {
                        request.getRequestDispatcher("../customer/creditcard.jsp").forward(request, response);
                    } else if (path.startsWith("/OrderController/Ordered/detail")) {
                        String[] split = path.trim().split("/");
//request.getRequestDispatcher("/customer/viewOrdersDetails.jsp").forward(request, response);
                        try {
                            int order_id = Integer.parseInt(split[split.length - 1]);
                            Orders o = odao.GetOrderById(order_id);
                            if (o.getOrder_status() == 0 || o.getOrder_status() == 1) {
                                LinkedList<Order_Details> oDetail = oddao.GetListViewDetailByOId(order_id);
                                session.setAttribute("oDetail", oDetail);
                            }
                            request.getRequestDispatcher("/customer/viewOrdersDetails.jsp").forward(request, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("/OrderController/Ordered");
                        }
                        return;
                    } else if (path.startsWith("/OrderController/Ordered/editinfo")) {
                        request.getRequestDispatcher("/customer/editInforOrder.jsp").forward(request, response);
                        String[] split = path.trim().split("/");
                        try {
                            int order_id = Integer.parseInt(split[split.length - 1]);
                            Orders o = odao.GetOrderById(order_id);
                            if (o.getOrder_status() == 0) {
                                LinkedList<Order_Details> oDetail = oddao.GetListViewDetailByOId(order_id);
                                session.setAttribute("oDetail", oDetail);
                            }
                            response.sendRedirect("/OrderController/Ordered");
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("/OrderController/Ordered");
                        }
                        return;
                    } else if (path.startsWith("/OrderController/Ordered/BuyAgain")) {
//                        request.getRequestDispatcher("/customer/cart.jsp").forward(request, response);
                        CartDAO cdao = new CartDAO();
                        String[] split = path.trim().split("/");
                        LinkedList<Carts> cart = new LinkedList<>();
                        LinkedList<Carts> cart_list = (LinkedList<Carts>) session.getAttribute("cart_list");
                        cusID = Integer.parseInt(cus_id);
                        try {
                            int order_id = Integer.parseInt(split[split.length - 1]);
                            Orders o = odao.getOrderID_ProID(order_id);
                            Product p = pdao.GetProIdByQuan(o.getPro_id());
                            Order_Details od = oddao.GetDetailID(order_id);
                            if (cart_list == null) {
                                
                                Carts c = new Carts(1, cusID, od.getPro_id(), od.getDetail_price(), od.getDetail_quantity(), od.getDetail_size(), od.getDetail_colour(), od.getPro_picture(), od.getPro_name());
                                Carts add = cdao.AddCart(c);
                                cart = cdao.GetListCartByAccID(cusID);
                                session.setAttribute("cart_list", cart);
                            } else {
                                boolean checkIdExist = false;
                                for (Carts c : cart_list) {
                                    if (p.getPro_id() == c.getPro_id()) {
                                        checkIdExist = true;
                                        if (c.getCart_quantity() < p.getStock_import()) {
                                            c.setCart_quantity(c.getCart_quantity() + 1);
                                            Carts update = cdao.UpdateQuan(c);
                                            cart = cdao.GetListCartByAccID(cusID);
                                            session.setAttribute("cart_list", cart);
                                            break;
                                        }
                                    }
                                }

                                if (!checkIdExist) {
                                    Carts c = new Carts(1, cusID, od.getPro_id(), od.getDetail_price(), od.getDetail_quantity(), od.getDetail_size(), od.getDetail_colour(), od.getPro_picture(), od.getPro_name());
                                    Carts add = cdao.AddCart(c);
                                    cart = cdao.GetListCartByAccID(cusID);
                                    session.setAttribute("cart_list", cart);
                                }
                            }
                            response.sendRedirect("/CartController");
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("/OrderController/Ordered");
                        }
                        return;
                    }

                }

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
        CartDAO cdao = new CartDAO();
        ProductDAO pdao = new ProductDAO();
        OrdersDAO odao = new OrdersDAO();
        Order_DetailDAO od_dao = new Order_DetailDAO();
        HttpSession session = request.getSession();
        if (request.getParameter("btnBuy") != null) {
            LinkedList<Carts> cart_buy = new LinkedList<>();
            LinkedList<Carts> cart_out = new LinkedList<>();//list pro out of stock
            String[] list_cart_id = request.getParameterValues("checkbox");
            for (String s : list_cart_id) {
                int cartId = Integer.parseInt(s);
                Carts c = cdao.GetCartById(cartId);
                Product p = pdao.GetProById(c.getPro_id());
                int quanPro = p.getStock_import();
                int catPro = c.getCart_quantity();
                if (c.getCart_quantity() <= p.getStock_import()) {
                    cart_buy.add(c);

                } else {
                    cart_out.add(c);
                }

            }
//            for (Carts cart : cart_buy) {
//                cdao.DeleteItemCart(cart.getCart_id());
//            }
            Float tong_tien = 0.0f;
            if (cart_buy.size() >= 1) {
                for (Carts c : cart_buy) {
                    Float total = c.getTotal_cart();
                    tong_tien = tong_tien + total;
                }
            } else {
                for (Carts c : cart_buy) {
                    Float total = c.getTotal_cart();
                    tong_tien = total;
                }
            }

            session.setAttribute("cart_out", cart_out);

            session.setAttribute("tong_tien", tong_tien);
            session.setAttribute("cart_buy", cart_buy);
            response.sendRedirect("/OrderController");
        } else if (request.getParameter("btnCheckOut") != null) {
            LinkedList<Carts> cart_buy = (LinkedList<Carts>) session.getAttribute("cart_buy");
            if (cart_buy == null) {
                // Handle the case where cart_buy is null
                response.sendRedirect("/CartController");
                return;
            }

            for (Carts cart : cart_buy) {
                cdao.DeleteItemCart(cart.getCart_id());
            }
            LinkedList<Carts> buy = (LinkedList<Carts>) session.getAttribute("cart_buy");

//            Customer cus = (Customer) session.getAttribute("cus_inf");
            Cookie cookies[] = request.getCookies();
            int cus_id = 0;
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equalsIgnoreCase("idC")) {
                        cus_id = Integer.parseInt(cooky.getValue());
                    }
                }
            }
            // Kiểm tra xem có thông tin khách hàng trong session hay không
//            if (cus == null) {
//                // Nếu không có, chuyển hướng đến trang đăng nhập
//                response.sendRedirect("/LoginController");
//                return; // Kết thúc xử lý để không tiếp tục thực hiện các hành động khác
//            }
            // Lấy thông tin từ form
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String note = request.getParameter("note");
            Float tong_tien = (Float) session.getAttribute("tong_tien");
            long currentTimeMillis = System.currentTimeMillis();
            Date pay_create_at = new Date(currentTimeMillis);

            // Tạo đơn hàng mới
            StaffDAO sdao = new StaffDAO();
            Staff s = sdao.getStaffisNull();
            Orders o = new Orders(1, s.getStaff_id(), cus_id, fullname, email, phone, address, note, pay_create_at);
            int order_id = odao.AddOrder(o); // Thêm đơn hàng và nhận order id

            // Thêm các chi tiết đơn hàng
            for (Carts c : buy) {
                Order_Details od = new Order_Details(1, c.getPro_id(), order_id, c.getCart_price(), c.getCart_quantity(), c.getCart_size(), c.getCart_colour(), c.getPro_picture());
                od_dao.AddOrderDetail(od);

                // Cập nhật số lượng sản phẩm
                Product update = pdao.GetProId(od.getPro_id());
                pdao.UpdateQuantity(update.getStock_import() - od.getDetail_quantity(), update.getPro_id(), od.getDetail_quantity());
            }

            // Lấy danh sách đơn hàng và đặt vào session
            LinkedList<Orders> order = odao.getListOrderByCusId(cus_id);
            session.setAttribute("orders", order);
            // Chuyển hướng đến trang đã đặt hàng thành công
            response.sendRedirect("/OrderController/Ordered");
        } else if (request.getParameter("btn-edit") != null) {
            //Lấy thông tin từ form
            int id = Integer.parseInt(request.getParameter("orderID"));
            String name = request.getParameter("orderFullname");
            String email = request.getParameter("orderEmail");
            String phone = request.getParameter("orderPhone");
            String address = request.getParameter("orderAddress");
            String note = request.getParameter("orderNote");
            //Gọi hàm và cập nhật lại thông tin
            int rs = odao.editInforOrderById(id, email, phone, address, note);
            if (rs != 0) {
                //Gọi hàm để setattribute lại sau đó trả về trang view detail và show ra thông tin đã cập nhật
                LinkedList<Order_Details> oDetail = od_dao.GetListViewDetailByOId(id);
                session.setAttribute("oDetail", oDetail);
                response.sendRedirect("/OrderController/Ordered/detail/" + id);
            }
        } else if (request.getParameter("btn-cancel") != null) {
            int id = Integer.parseInt(request.getParameter("orderID"));
            response.sendRedirect("/OrderController/Ordered/detail/" + id);
        }

        // Create an Order object
// Assuming OrdersDAO has a method to save the order
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
//Sửa hết.
