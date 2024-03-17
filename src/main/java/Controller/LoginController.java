/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.AccountDAO;
import Models.Customer;
import Models.Staff;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class LoginController extends HttpServlet {

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
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/LoginController")) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
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

        if (request.getParameter("signIn") != null) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email.contains("@gmail")) {
                AccountDAO ad = new AccountDAO();
                Customer cus = ad.loginByCustomer(email, password);
                if (cus != null) {
                    String fullname = cus.getCus_fullname();
                    int cus_id = cus.getCus_id();
                    int id = cus.getCus_id();
                    Cookie idC = new Cookie("idC", String.valueOf(id));
                    Cookie fullC = new Cookie("fullC", ad.encodeString(fullname));
                    Cookie emailC = new Cookie("emailC", email);
                    Cookie passC = new Cookie("passC", password);
//                  Cookie idC = new Cookie("idC", )
                    idC.setMaxAge((60 * 60 * 24) * 2);
                    emailC.setMaxAge((60 * 60 * 24) * 2);
                    passC.setMaxAge((60 * 60 * 24) * 2);
                    fullC.setMaxAge((60 * 60 * 24) * 2);
                    response.addCookie(passC);
                    response.addCookie(fullC);
                    response.addCookie(emailC);
                    response.addCookie(idC);
                    HttpSession session = request.getSession();
                    session.setAttribute("cus_inf", cus);

                    response.sendRedirect("/");
                    System.out.println("Add Cookie Successfull");
                } else {
                    String message = "Account or password is incorrect";
                    HttpSession session = request.getSession();
                    session.setAttribute("checkLoginMess", message);
                    response.sendRedirect("/LoginController");
                }
            } else if (email.contains("@ishoes")) {
                AccountDAO ad = new AccountDAO();
                Staff staff = ad.loginByStaff(email, password);
                if (staff != null) {
                    String fullname = staff.getStaff_fullname();
                    int staff_id = staff.getStaff_id();
                    int role_id = staff.getRole_id();
                    Cookie fullC = new Cookie("fullC", ad.encodeString(fullname));
                    Cookie emailC = new Cookie("emailC", email);
                    Cookie roleC = new Cookie("roleC", String.valueOf(role_id));
                    Cookie staff_idC = new Cookie("staff_idC", String.valueOf(staff_id));
                    Cookie passC = new Cookie("passC", password);
//                  Cookie idC = new Cookie("idC", )
                    emailC.setMaxAge((60 * 60 * 24) * 2);
                    passC.setMaxAge((60 * 60 * 24) * 2);
                    fullC.setMaxAge((60 * 60 * 24) * 2);
                    roleC.setMaxAge((60 * 60 * 24) * 2);
                    staff_idC.setMaxAge((60 * 60 * 24) * 2);
                    response.addCookie(passC);
                    response.addCookie(fullC);
                    response.addCookie(emailC);
                    response.addCookie(roleC);
                    response.addCookie(staff_idC);
                    response.sendRedirect("/AdminController");
                } else {
                    String message = "Account or password is incorrect";
                    HttpSession session = request.getSession();
                    session.setAttribute("checkLoginMess", message);
                    response.sendRedirect("/LoginController");
                }
            } else {
                String message = "Account or password is incorrect";
                HttpSession session = request.getSession();
                session.setAttribute("checkLoginMess", message);
                response.sendRedirect("/LoginController");
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
