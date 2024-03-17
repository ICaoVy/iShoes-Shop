/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class Email {

    private String code_email;

    public Email() {
    }

    public String getCode_email() {
        return code_email;
    }

    public void setCode_email(String code_email) {
        this.code_email = code_email;
    }

    // Email: icaovy2001@gmail.com
    //Password: omux rgvi uwqk jyza
    public boolean getSendEmail(String to) {
        final String from = "icaovy2001@gmail.com";
        final String password = "omux rgvi uwqk jyza";

        //Properties khai bao thuoc tinh
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        };

        //Phien lam viec
        Session session = Session.getInstance(props, auth);

        //Gui email
        //Tao 1 tin nhan
        MimeMessage msg = new MimeMessage(session);

        try {
            //kieu noi dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            //nguoi gui
            msg.setFrom(new InternetAddress(from));
            //nguoi nhan
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            long currentTimeMillis = System.currentTimeMillis();
            Date currentDate = new Date(currentTimeMillis);

            // Tieu De Email
            msg.setSubject("Ishoes Store " + currentDate.toString());
            //Quy dinh ngay gui
            msg.setSentDate(new Date());
            //Quy dinh email phan hoi
            //msg.setReplyTo(internetAddress.parse(from, false));
            code_email = generateRandomString(6);
            //Noi Dung
            msg.setContent("<html>\n"
                    + "<body>\n"
                    + "\n"
                    + "<p>Your Email verification code: <strong>" + getCode_email() + "</strong></p>\n"
                    + "\n"
                    + "<p><strong>Note</strong>: Do not share your code outside of our system. The code is important information and revealing it can pose a security risk. Please keep it safe and only share with necessary people. Thank you for your cooperation to protect project and personal information.</p>\n"
                    + "</body>\n"
                    + "</html>", "text/html");
//            msg.setContent("Yêu Pé Thùy <3","UTF-8");

            //Gui Email
            Transport.send(msg);
            System.out.println("Gui Thanh Cong");
            return true;
        } catch (MessagingException ex) {
            System.out.println("Gui That Bai");
            ex.printStackTrace();
            return false;
        }

    }

    public static String generateRandomString(int length) {
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int randomNumber = random.nextInt(10); // Số nguyên ngẫu nhiên từ 0 đến 9
            randomString.append(randomNumber);
        }

        return randomString.toString();
    }
}
