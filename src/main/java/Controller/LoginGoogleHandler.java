/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAOs.AccountDAO;
import Models.Constants;
import Models.Customer;
import Models.UserGoogleDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.fluent.Form;
import org.apache.hc.client5.http.fluent.Request;

/**
 *
 * @author ASUS
 */
public class LoginGoogleHandler extends HttpServlet {

    public LoginGoogleHandler() {
    }

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
        System.out.println("Show here");
        System.out.println(request.getParameter("code"));
            String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDto user = getUserInfo(accessToken);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userGoogle", user);
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.get(link).execute().returnContent().asString();

        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);

        return googlePojo;
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
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserGoogleDto user = getUserInfo(accessToken);
        System.out.println(user);
        String email = user.getEmail();

        AccountDAO aDAO = new AccountDAO();
        Customer cus = aDAO.checkLoginByGoogle(email);
        if (cus != null) {
            System.out.println(user.getEmail() + " " + user.getId());
            Customer cusGoogle = aDAO.loginByGoogle(user.getEmail(), user.getId());

            if (cusGoogle != null) {
                String fullG = cusGoogle.getCus_fullname();
                String emailG = cusGoogle.getCus_email();
                String passG = cusGoogle.getCus_password();
                int id = cusGoogle.getCus_id();
                Cookie idC = new Cookie("idC", String.valueOf(id));
                Cookie emailC = new Cookie("emailC", emailG);
                Cookie passC = new Cookie("passC", passG);
                Cookie fullC = new Cookie("fullC", aDAO.encodeString(fullG));
                idC.setMaxAge((60 * 60 * 24) * 2);
                emailC.setMaxAge((60 * 60 * 24) * 2);
                passC.setMaxAge((60 * 60 * 24) * 2);
                fullC.setMaxAge((60 * 60 * 24) * 2);
                idC.setPath("/");
                emailC.setPath("/");
                passC.setPath("/");
                fullC.setPath("/");
                response.addCookie(idC);
                response.addCookie(passC);
                response.addCookie(fullC);
                response.addCookie(emailC);

                Cookie cookies[] = request.getCookies();
                if (cookies != null) {
                    for (Cookie cooky : cookies) {
                        System.out.println(cooky.getName() + " : " + cooky.getValue());
                    }
                } else {
                    System.out.println("Add Cookie Fail");
                }

                response.sendRedirect("/");
            }

        } else {
            UserGoogleDto userGoogle = aDAO.loginByGoogleButDontInDatabase(user);
            if (userGoogle != null) {
                Customer cusGoogle = aDAO.loginByGoogle(user.getEmail(), user.getId());
                if (cusGoogle != null) {
                    String fullG = cusGoogle.getCus_fullname();
                    String emailG = cusGoogle.getCus_email();
                    String passG = cusGoogle.getCus_password();
                    int id = cusGoogle.getCus_id();
                    Cookie idC = new Cookie("idC", String.valueOf(id));
                    Cookie emailC = new Cookie("emailC", emailG);
                    Cookie passC = new Cookie("passC", passG);
                    Cookie fullC = new Cookie("fullC", aDAO.encodeString(fullG));
                    idC.setMaxAge((60 * 60 * 24) * 2);
                    emailC.setMaxAge((60 * 60 * 24) * 2);
                    passC.setMaxAge((60 * 60 * 24) * 2);
                    fullC.setMaxAge((60 * 60 * 24) * 2);
                    idC.setPath("/");
                    emailC.setPath("/");
                    passC.setPath("/");
                    fullC.setPath("/");
                    response.addCookie(idC);
                    response.addCookie(passC);
                    response.addCookie(fullC);
                    response.addCookie(emailC);

                    response.sendRedirect("/");
                } else {
                    response.sendRedirect("/LoginController");
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
        doGet(request, response);
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
