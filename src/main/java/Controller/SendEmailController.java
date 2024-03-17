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
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class SendEmailController extends HttpServlet {

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
            out.println("<title>Servlet SendEmailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendEmailController at " + request.getContextPath() + "</h1>");
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
        if (path.endsWith("/SendEmailController")) {
            request.getRequestDispatcher("/codeRegis.jsp").forward(request, response);
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
        if (request.getParameter("btn-register") != null) {
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            long currentTimeMillis = System.currentTimeMillis();
            Date cus_creat_at = new Date(currentTimeMillis);
            int cus_deleted = 0;
            CustomerDAO cDAO = new CustomerDAO();
            int count = cDAO.checkEmailExist(email);
            if (count != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("emailisExist", "");
                //email ton tai
                response.sendRedirect("/LoginController");
            } else {

                Email e = new Email();

                boolean flat = e.getSendEmail(email);
                System.out.println(e.getCode_email());
                if (flat == true) {
                    HttpSession session = request.getSession();
                    Customer cus = new Customer(count, fullname, email, phone, password, birthday, address, cus_creat_at, cus_deleted);
                    session.setAttribute("cus_register", cus);
                    session.setAttribute("code_email", e.getCode_email());
                    response.sendRedirect("/SendEmailController");
                } else {
                    //email gui khong thanh cong
                    response.sendRedirect("/LoginController");
                }
            }
        }

        if (request.getParameter("sendOTP") != null) {
            String OTP = request.getParameter("otp");

            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("cus_register");
            String code_email = (String) session.getAttribute("code_email");
            System.out.println("Form OTP: " + code_email);
            CustomerDAO cDAO = new CustomerDAO();
            if (OTP.equalsIgnoreCase(code_email)) {
                Customer c = cDAO.register(cus);
                if (c != null) {
                    session.setAttribute("registerSuccessfull", "");
                    response.sendRedirect("/LoginController");
                } else {
                    response.sendRedirect("/SendEmailController");
                }

            } else {
                response.sendRedirect("/SendEmailController");
            }
        }

        if (request.getParameter("btn-sendAgain") != null) {
            HttpSession session = request.getSession();
            Customer cus = (Customer) session.getAttribute("cus_register");
            Email e = new Email();
            e.getSendEmail(cus.getCus_email());
            response.sendRedirect("/SendEmailController");
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
