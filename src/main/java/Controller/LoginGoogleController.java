/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import static Controller.LoginGoogleHandler.getToken;
import static Controller.LoginGoogleHandler.getUserInfo;
import DAOs.AccountDAO;
import Models.Customer;
import Models.UserGoogleDto;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class LoginGoogleController extends HttpServlet {

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
            out.println("<title>Servlet LoginGoogleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginGoogleController at " + request.getContextPath() + "</h1>");
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
//        LoginGoogleHandler lh = new LoginGoogleHandler();
//        UserGoogleDto user = lh.dataGoogle(request, response);
//        System.out.println(user);
//        String email = user.getEmail();
//
//        AccountDAO aDAO = new AccountDAO();
//        Customer cus = aDAO.checkLoginByGoogle(email);
//        if (cus != null) {
//            System.out.println(user.getEmail() + " " + user.getId());
//            Customer cusGoogle = aDAO.loginByGoogle(user.getEmail(), user.getId());
//
//            if (cusGoogle != null) {
//                String fullG = cusGoogle.getCus_fullname();
//                String emailG = cusGoogle.getCus_email();
//                String passG = cusGoogle.getCus_password();
//                Cookie emailC = new Cookie("emailC", emailG);
//                Cookie passC = new Cookie("passC", passG);
//                Cookie fullC = new Cookie("fullC", aDAO.encodeString(fullG));
//
//                emailC.setMaxAge((60 * 60 * 24) * 2);
//                passC.setMaxAge((60 * 60 * 24) * 2);
//                fullC.setMaxAge((60 * 60 * 24) * 2);
//
//                response.addCookie(passC);
//                response.addCookie(fullC);
//                response.addCookie(emailC);
//
//                Cookie cookies[] = request.getCookies();
//                if (cookies != null) {
//                    for (Cookie cooky : cookies) {
//                        System.out.println(cooky.getName() + " : " + cooky.getValue());
//                    }
//                } else {
//                    System.out.println("Add Cookie Fail");
//                }
//
//                response.sendRedirect("/");
//            }
//
//        } else {
//            UserGoogleDto userGoogle = aDAO.loginByGoogleButDontInDatabase(user);
//            if (userGoogle != null) {
//                Customer cusGoogle = aDAO.loginByGoogle(user.getEmail(), user.getId());
//                if (cusGoogle != null) {
//                    String fullG = cusGoogle.getCus_fullname();
//                    String emailG = cusGoogle.getCus_email();
//                    String passG = cusGoogle.getCus_password();
//                    Cookie emailC = new Cookie("emailC", emailG);
//                    Cookie passC = new Cookie("passC", passG);
//                    Cookie fullC = new Cookie("fullC", aDAO.encodeString(fullG));
//
//                    emailC.setMaxAge((60 * 60 * 24) * 2);
//                    passC.setMaxAge((60 * 60 * 24) * 2);
//                    fullC.setMaxAge((60 * 60 * 24) * 2);
//                    response.addCookie(passC);
//                    response.addCookie(fullC);
//                    response.addCookie(emailC);
//
//                    response.sendRedirect("/");
//                } else {
//                    response.sendRedirect("/LoginController");
//                }
//            }
//        }

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
        processRequest(request, response);
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
