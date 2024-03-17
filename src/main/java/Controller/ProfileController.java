/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.AccountDAO;
import DAOs.CustomerDAO;
import Models.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/ProfileController")) {
            request.getRequestDispatcher("./customer/profile.jsp").forward(request, response);
        } else if (path.endsWith("/ProfileController/Security")) {
            request.getRequestDispatcher("/customer/changePass.jsp").forward(request, response);
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
        if (request.getParameter("btn-save") != null) {
            String email = request.getParameter("email");
            String name = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            Date date = Date.valueOf(request.getParameter("date"));
            String address = request.getParameter("address");
            int cus_id = 0;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equalsIgnoreCase("idC")) {
                        cus_id = Integer.parseInt(cooky.getValue());
                    }
                }
            }
            AccountDAO ad = new AccountDAO();
            CustomerDAO cDAO = new CustomerDAO();
            Customer customer = new Customer(cus_id, name, email, phone, date, address);
            Customer cus = cDAO.updateProfile(customer, cus_id);
            if (cus != null) {
                if (cookies != null) {
                    for (Cookie cooky : cookies) {
                        if (cooky.getName().equalsIgnoreCase("fullC")) {
                            cooky.setValue(ad.encodeString(name));
                            response.addCookie(cooky);
                        }
                    }
                }

                response.sendRedirect("/ProfileController");
            } else {
                response.sendRedirect("/ProfileController");
            }
        }

        if (request.getParameter("btn-change") != null) {
            String email = request.getParameter("email");
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            String confirm = request.getParameter("confirm");
            int cus_id = 0;
            Cookie cookies[] = request.getCookies();
            if (cookies != null) {
                for (Cookie cooky : cookies) {
                    if (cooky.getName().equalsIgnoreCase("idC")) {
                        cus_id = Integer.parseInt(cooky.getValue());
                    }
                }
            }
            CustomerDAO cDAO = new CustomerDAO();

            String checkPass = cDAO.checkPassToChange(oldPass, cus_id);
            if (checkPass != "") {
                if (newPass.equals(confirm)) {
                    String check = cDAO.updatePass(confirm, cus_id);
                    if (check != null) {
                        response.sendRedirect("/");
                    } else {
                        response.sendRedirect("/ProfileController/Security");
                    }
                }
            } else {
                response.sendRedirect("/ProfileController/Security");
            }
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
