/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CartDAO;
import DAOs.ProductDAO;
import DAOs.SearchDAO;
import DAOs.StockDAO;
import Models.Carts;
import Models.Product;
import Models.Search;
import Models.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ProductController extends HttpServlet {

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
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/ProductController")) {
            request.getRequestDispatcher("/product.jsp").forward(request, response);
        } else if (path.startsWith("/ProductController/Details")) {
            request.getRequestDispatcher("/productDetails.jsp").forward(request, response);
        } else if (path.startsWith("/ProductController/Luxury")) {
            String s1[] = path.split("/");
            String nameD = s1[s1.length - 1];
            HttpSession session = request.getSession();
            session.setAttribute("nameD", nameD);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        } else if (path.startsWith("/ProductController/Sandal")) {
            String s1[] = path.split("/");
            String nameD = s1[s1.length - 1];
            HttpSession session = request.getSession();
            session.setAttribute("nameD", nameD);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
        } else if (path.startsWith("/ProductController/Sport")) {
            String s1[] = path.split("/");
            String nameD = s1[s1.length - 1];
            HttpSession session = request.getSession();
            session.setAttribute("nameD", nameD);
            request.getRequestDispatcher("/search.jsp").forward(request, response);
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

        HttpSession session = request.getSession();
        ProductDAO pdao = new ProductDAO();
        CartDAO cdao = new CartDAO();
        LinkedList<Carts> cart = new LinkedList<>();
        LinkedList<Carts> cart_list = (LinkedList<Carts>) session.getAttribute("cart_list");

        String cus_id = null;
        Cookie[] cookie = request.getCookies();
        if (cookie == null) {
            response.sendRedirect("/LoginController");
            return; // Chấm dứt việc xử lý nếu không có cookie tồn tại
        }

        for (Cookie c : cookie) {
            if (c.getName().equals("idC")) {
                cus_id = c.getValue();
                break;
            }
        }

        if (request.getParameter("AddtoCard") != null) {
            int pro_id = Integer.parseInt(request.getParameter("pro_id"));
            Product pro = pdao.GetProById(pro_id);
            String pro_name = request.getParameter("pro_name");
            String pro_priceString = request.getParameter("pro_price");
            String pro_sizeString = request.getParameter("sizeoption");
            String pro_detailquanString = request.getParameter("detail_quan");
            String pro_colour = request.getParameter("pro_colour");
            StockDAO sDAO = new StockDAO();
            Stock s = sDAO.getStockByProCodeAndSize(Integer.parseInt(pro_sizeString), request.getParameter("proCode"));
            if (s != null) {
                int quantity_current = (s.getStock_import() - s.getStock_export());
                int checkStock = quantity_current - Integer.parseInt(pro_detailquanString);
                if (checkStock < 0) {
                    HttpSession session1 = request.getSession();
                    session1.setAttribute("checkStock", "The product you just purchased is out of stock");
                }
            }
            int cusID = Integer.parseInt(cus_id);
            float pro_price = Float.parseFloat(pro_priceString);
            int pro_size = Integer.parseInt(request.getParameter("sizeoption"));
            int pro_detailquan = Integer.parseInt(pro_detailquanString);
//            int pro_detailquan = 3;

            // Check if the product is already in the cart
            boolean productExists = false;
            if (cart_list != null) {
                for (Carts c : cart_list) {
                    if (pro_id == c.getPro_id()) {
                        // Product already exists in the cart, update its quantity
                        c.setCart_quantity(c.getCart_quantity() + pro_detailquan);
                        cdao.UpdateQuan(c);
                        productExists = true;
                        break;
                    }
                }
            }

            if (!productExists) {
                // Product does not exist in the cart, add it
                Carts c = new Carts();
                c.setPro_id(pro_id);
                c.setCus_id(cusID);
                c.setPro_name(pro_name);
                c.setCart_price(pro_price);
                c.setCart_quantity(pro_detailquan); // Set initial quantity
                c.setCart_size(pro_size);
                c.setCart_colour(pro_colour);
                // Thêm sản phẩm vào giỏ hàng
                c = cdao.AddCart(c);
            }

            cart = cdao.GetListCartByAccID(cusID);
            session.setAttribute("cart_list", cart);
            response.sendRedirect("/ProductController");
            return; // Chấm dứt việc xử lý sau khi thêm sản phẩm vào giỏ hàng
        }

        if (request.getParameter("btn-search") != null) {
            String name_search = request.getParameter("search");
            session.setAttribute("search", name_search);
            response.sendRedirect("/ProductController");
        }

//        response.sendRedirect("/ProductController");
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
