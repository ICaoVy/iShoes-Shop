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
public class Staff {

    private int staff_id;
    private int role_id;
    private String staff_fullname;
    private String staff_email;
    private String staff_phone;
    private String staff_password;
    private String staff_address;
    private Date staff_create_at;
    private int staff_deleted;

    public Staff() {
    }

    public Staff(int staff_id, int role_id, String staff_fullname, String staff_email, String staff_phone, String staff_password, String staff_address, Date staff_create_at, int staff_deleted) {
        this.staff_id = staff_id;
        this.role_id = role_id;
        this.staff_fullname = staff_fullname;
        this.staff_email = staff_email;
        this.staff_phone = staff_phone;
        this.staff_password = staff_password;
        this.staff_address = staff_address;
        this.staff_create_at = staff_create_at;
        this.staff_deleted = staff_deleted;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getStaff_fullname() {
        return staff_fullname;
    }

    public void setStaff_fullname(String staff_fullname) {
        this.staff_fullname = staff_fullname;
    }

    public String getStaff_email() {
        return staff_email;
    }

    public void setStaff_email(String staff_email) {
        this.staff_email = staff_email;
    }

    public String getStaff_phone() {
        return staff_phone;
    }

    public void setStaff_phone(String staff_phone) {
        this.staff_phone = staff_phone;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public String getStaff_address() {
        return staff_address;
    }

    public void setStaff_address(String staff_address) {
        this.staff_address = staff_address;
    }

    public Date getStaff_create_at() {
        return staff_create_at;
    }

    public void setStaff_create_at(Date staff_create_at) {
        this.staff_create_at = staff_create_at;
    }

    public int getStaff_deleted() {
        return staff_deleted;
    }

    public void setStaff_deleted(int staff_deleted) {
        this.staff_deleted = staff_deleted;
    }
    
    
}
