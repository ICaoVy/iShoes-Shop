/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Customer {
    private int cus_id;
    private String cus_fullname;
    private String cus_email;
    private String cus_phone;
    private String cus_password;
    private Date cus_birthday;
    private String cus_address;
    private Date cus_create_at;
    private int cus_deleted;

    public Customer() {
    }

    public Customer(int cus_id, String cus_fullname, String cus_email, String cus_phone, String cus_password, Date cus_birthday, String cus_address, Date cus_create_at, int cus_deleted) {
        this.cus_id = cus_id;
        this.cus_fullname = cus_fullname;
        this.cus_email = cus_email;
        this.cus_phone = cus_phone;
        this.cus_password = cus_password;
        this.cus_birthday = cus_birthday;
        this.cus_address = cus_address;
        this.cus_create_at = cus_create_at;
        this.cus_deleted = cus_deleted;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_fullname() {
        return cus_fullname;
    }

    public void setCus_fullname(String cus_fullname) {
        this.cus_fullname = cus_fullname;
    }

    public String getCus_email() {
        return cus_email;
    }

    public void setCus_email(String cus_email) {
        this.cus_email = cus_email;
    }

    public String getCus_phone() {
        return cus_phone;
    }

    public void setCus_phone(String cus_phone) {
        this.cus_phone = cus_phone;
    }

    public String getCus_password() {
        return cus_password;
    }

    public void setCus_password(String cus_password) {
        this.cus_password = cus_password;
    }

    public Date getCus_birthday() {
        return cus_birthday;
    }

    public void setCus_birthday(Date cus_birthday) {
        this.cus_birthday = cus_birthday;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
    }

    public Date getCus_create_at() {
        return cus_create_at;
    }

    public void setCus_create_at(Date cus_create_at) {
        this.cus_create_at = cus_create_at;
    }

    public int getCus_deleted() {
        return cus_deleted;
    }

    public void setCus_deleted(int cus_deleted) {
        this.cus_deleted = cus_deleted;
    }

    
    
}
