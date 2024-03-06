/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.ProductDAO;
import Models.Carts;
import Models.Customer;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedList;

/**
 *
 * @author ASUS
 */
public class CartController extends HttpServlet {

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
            out.println("<title>Servlet CartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartController at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/CartController")) {
            int cus_id = 0;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equals("idC")) {
                        cus_id = Integer.parseInt(cooky.getValue());
                    }
                }
            }
            CartDAO cd = new CartDAO();
            HttpSession session = request.getSession();
            LinkedList<Carts> cart = cd.GetListCartByAccID(cus_id);
            session.setAttribute("cart_list", cart);
            request.getRequestDispatcher("./customer/cart.jsp").forward(request, response);
        } else {
            int cusID;
            String cus_id = null;
            Cookie[] cookie = request.getCookies();
            if (cookie == null) {
                response.sendRedirect("/LoginController");
            } else {
                boolean flag = false;
                for (int i = 0; i < cookie.length; i++) {
                    if (cookie[i].getName().equals("idC")) {
                        cus_id = cookie[i].getValue();
                        flag = true;
                    }
                }
                if (!flag) {
                    response.sendRedirect("/LoginController");
                } else {
                    ProductDAO pdao = new ProductDAO();
                    CartDAO cdao = new CartDAO();
                    HttpSession session = request.getSession();
                    Customer cus = (Customer) session.getAttribute("cus_inf");
                    if (path.startsWith("/CartController/AddToCartAtPro")) {
                        try {
                            int pro_id = Integer.parseInt(request.getParameter("id")); // Lấy tham số id từ query string
                            cusID = Integer.parseInt(cus_id);
                            Product pro = pdao.GetProById(pro_id);
                            if (pro.getStock_import() > 0) {
                                LinkedList<Carts> cart = new LinkedList<>();
                                LinkedList<Carts> cart_list = (LinkedList<Carts>) session.getAttribute("cart_list");
                                if (cart_list.size() == 0) {
                                    Carts c = new Carts(1, cusID, pro.getPro_id(), pro.getPro_price(), pro.getStock_import(), pro.getPro_size(), pro.getPro_colour(), pro.getPro_picture(), pro.getPro_name());
                                    Carts add = cdao.AddCart(c);
                                    cart = cdao.GetListCartByAccID(cusID);
                                    session.setAttribute("cart_list", cart);
                                } else {
                                    boolean checkIdExist = false;
                                    for (Carts c : cart_list) {
                                        if (pro_id == c.getPro_id()) {
                                            checkIdExist = true;
                                            if (c.getCart_quantity() < pro.getStock_import()) {
                                                c.setCart_quantity(c.getCart_quantity() + 1);
                                                Carts update = cdao.UpdateQuan(c);
                                                cart = cdao.GetListCartByAccID(cusID);
                                                session.setAttribute("cart_list", cart);
                                                break;
                                            }
                                        }
                                    }

                                    if (!checkIdExist) {
                                        Carts c = new Carts(1, cusID, pro.getPro_id(), pro.getPro_price(), pro.getStock_import(), pro.getPro_size(), pro.getPro_colour(), pro.getPro_picture(), pro.getPro_name());
                                        Carts add = cdao.AddCart(c);
                                        cart = cdao.GetListCartByAccID(cusID);
                                        session.setAttribute("cart_list", cart);
                                    }
                                }
                            }

                            response.sendRedirect("/");
                        } catch (Exception e) {
                            e.printStackTrace();
                            response.sendRedirect("/");
                        }

                    } else if (path.startsWith("/CartController/IncQuan")) {
                        LinkedList<Carts> cart = (LinkedList<Carts>) session.getAttribute("cart_list");
                        String[] split = path.split("/");
                        cusID = Integer.parseInt(cus_id);
                        try {
                            int pro_id = Integer.parseInt(split[split.length - 1]);
                            Product p = pdao.GetProById(pro_id);
                            for (Carts c : cart) {
                                if (pro_id == c.getPro_id() && c.getCart_quantity() < p.getStock_import()) {
                                    int quantity = c.getCart_quantity();
                                    quantity++;
                                    if (quantity <= p.getStock_import()) {
                                        c.setCart_quantity(quantity);
                                        Carts update = cdao.UpdateQuan(c);
                                        cart = cdao.GetListCartByAccID(cusID);
                                        session.setAttribute("cart_list", cart);
                                        break;
                                    }
                                }
                            }

                            response.sendRedirect("/CartController");

                        } catch (Exception e) {
                            response.sendRedirect("/CartController");
                        }
                    } else if (path.startsWith("/CartController/DecQuan")) {
                        LinkedList<Carts> cart = (LinkedList<Carts>) session.getAttribute("cart_list");
                        String[] split = path.split("/");
                        cusID = Integer.parseInt(cus_id);
                        try {
                            int pro_id = Integer.parseInt(split[split.length - 1]);
                            Product p = pdao.GetProById(pro_id);
                            for (Carts c : cart) {
                                if (pro_id == c.getPro_id() && c.getCart_quantity() > 1) {
                                    int quantity = c.getCart_quantity();
                                    quantity--;
                                    if (quantity >= 1) {
                                        c.setCart_quantity(quantity);
                                        Carts update = cdao.UpdateQuan(c);
                                        cart = cdao.GetListCartByAccID(cusID);
                                        session.setAttribute("cart_list", cart);
                                        break;
                                    }
                                }
                            }

                            response.sendRedirect("/CartController");

                        } catch (Exception e) {
                            response.sendRedirect("/CartController");
                        }
                    } else if (path.startsWith("/CartController/Delete")) {
                        LinkedList<Carts> cart = (LinkedList<Carts>) session.getAttribute("cart_list");
                        try {
                            // Lấy thông tin đường dẫn sau "/CartController/Delete"
                            String[] pathParts = path.split("/");
                            // Lấy id từ phần tử cuối cùng của pathParts
                            int cart_id = Integer.parseInt(pathParts[pathParts.length - 1]);

                            cusID = Integer.parseInt(cus_id);
                            int delete = cdao.DeleteCart(cart_id);
                            LinkedList<Carts> updatedCart = cdao.GetListCartByAccID(cusID);
                            session.setAttribute("cart_list", updatedCart);
                            response.sendRedirect("/CartController");
                        } catch (Exception e) {
                            response.sendRedirect("/CartController");
                        }
                    } else {
                        // Xử lý các trường hợp khác nếu cần
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
        if(request.getParameter("AddtoCard")!= null){
            //Code here
        }
        
        if(request.getParameter("btnBuy") != null){
            //code here
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
