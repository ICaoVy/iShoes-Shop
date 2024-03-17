/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.CustomerDAO;
import Models.Customer;
import Models.Email;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class ForgotController extends HttpServlet {

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
            out.println("<title>Servlet ForgotController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotController at " + request.getContextPath() + "</h1>");
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

        if (path.endsWith("/ForgotController")) {
            request.getRequestDispatcher("/forgot.jsp").forward(request, response);
        } else if (path.endsWith("/ForgotController/Reset")) {
            request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
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
        if (request.getParameter("btn-forgot") != null) {
            String email = request.getParameter("email");
            CustomerDAO cDAO = new CustomerDAO();
            int count = cDAO.checkEmailExist(email);
            if (count == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("emailisExist", "");
                //email ton tai
                response.sendRedirect("/ForgotController");
            } else {
                Email e = new Email();
                boolean flat = e.getSendEmail(email);
                if (flat == true) {
                    HttpSession session = request.getSession();
                    session.setAttribute("email-forgot", email);
                    session.setAttribute("email-code-forgot", e.getCode_email());
                    request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/ForgotController");
                }
            }

        }

        if (request.getParameter("btn-ChangeForgot") != null) {
            String OTP = request.getParameter("otp");
            String newPass = request.getParameter("newPass");
            String confirm = request.getParameter("confirm");
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email-forgot");
            String email_code = (String) session.getAttribute("email-code-forgot");
            CustomerDAO cDAO = new CustomerDAO();
            if (email_code.equalsIgnoreCase(OTP)) {
                String check = cDAO.forgotPassword(confirm, email);
                if (check != null) {
                    session.setAttribute("forgotSuccessfull", "");
                    response.sendRedirect("/LoginController");
                } else {
                    response.sendRedirect("/ForgotController");
                }
            } else {
                response.sendRedirect("/ForgotController");
            }
        }

        if (request.getParameter("btn-sendAgain") != null) {
            HttpSession session = request.getSession();
            String email = (String) session.getAttribute("email-forgot");
            Email e = new Email();
            boolean flat = e.getSendEmail(email);
            if (flat == true) {
                request.getRequestDispatcher("/newPassword.jsp").forward(request, response);
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
