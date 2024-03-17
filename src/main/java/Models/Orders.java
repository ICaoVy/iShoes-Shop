/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;

/**
 *
 * @author Administrator
 */
public class Orders extends Order_Details {

    private int order_id;
    private int staff_id;
    private int cus_id;
    private String order_fullname;
    private String order_email;
    private String order_phone;
    private String order_address;
    private String order_note;
    private int order_status;
    private Date pay_create_at;
    private int pay_status;
    private String pro_name;
    private int detail_quantity;
    private String pro_picture;
    private float detail_price;
    private int detail_size;
    private String detail_colour;
    private int pro_id;
    


    public Orders(int order_id, int staff_id, int cus_id, String order_fullname, String order_email, String order_phone, String order_address, String order_note, int order_status, Date pay_create_at, int pay_status, String pro_name, int detail_quantity, String pro_picture, float detail_price, int detail_size, String detail_colour, int pro_id) {
        this.order_id = order_id;
        this.staff_id = staff_id;
        this.cus_id = cus_id;
        this.order_fullname = order_fullname;
        this.order_email = order_email;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.order_note = order_note;
        this.order_status = order_status;
        this.pay_create_at = pay_create_at;
        this.pay_status = pay_status;
        this.pro_name = pro_name;
        this.detail_quantity = detail_quantity;
        this.pro_picture = pro_picture;
        this.detail_price = detail_price;
        this.detail_size = detail_size;
        this.detail_colour = detail_colour;
        this.pro_id = pro_id;
    }
    
    
    
    public Orders(int order_id, int staff_id, int cus_id, String order_fullname, String order_email, String order_phone, String order_address, String order_note, Date pay_create_at, String pro_name, int detail_quantity, String pro_picture, float detail_price, int detail_size, String detail_colour, int pro_id) {
        this.order_id = order_id;
        this.staff_id = staff_id;
        this.cus_id = cus_id;
        this.order_fullname = order_fullname;
        this.order_email = order_email;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.order_note = order_note;
        this.order_status = 0;
        this.pay_create_at = pay_create_at;
        this.pay_status = 1;
        this.pro_name = pro_name;
        this.detail_quantity = detail_quantity;
        this.pro_picture = pro_picture;
        this.detail_price = detail_price;
        this.detail_size = detail_size;
        this.detail_colour = detail_colour;
        this.pro_id = pro_id;
    }
    

    @Override
    public float getDetail_price() {
        return detail_price;
    }

    @Override
    public void setDetail_price(float detail_price) {
        this.detail_price = detail_price;
    }

    @Override
    public int getDetail_size() {
        return detail_size;
    }

    @Override
    public void setDetail_size(int detail_size) {
        this.detail_size = detail_size;
    }

    @Override
    public String getDetail_colour() {
        return detail_colour;
    }

    @Override
    public void setDetail_colour(String detail_colour) {
        this.detail_colour = detail_colour;
    }

    @Override
    public int getPro_id() {
        return pro_id;
    }

    @Override
    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public Orders() {
        this.order_status = 0;
        this.pay_status = 1;
    }

    public Orders(int order_id, int staff_id, int cus_id, String order_fullname, String order_email, String order_phone, String order_address, String order_note, Date pay_create_at, String pro_name, int detail_quantity, String pro_picture, float detail_price, int detail_size, String detail_colour) {
        super();
        this.order_id = order_id;
        this.staff_id = staff_id;
        this.cus_id = cus_id;
        this.order_fullname = order_fullname;
        this.order_email = order_email;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.order_note = order_note;
        this.order_status = 0;
        this.pay_create_at = pay_create_at;
        this.pay_status = 1;
        this.pro_name = pro_name;
        this.detail_quantity = detail_quantity;
        this.pro_picture = pro_picture;
        this.detail_price = detail_price;
        this.detail_size = detail_size;
        this.detail_colour = detail_colour;
    }

    public Orders(int order_id, int staff_id, int cus_id, String order_fullname, String order_email, String order_phone, String order_address, String order_note, Date pay_create_at) {
        this.order_id = order_id;
        this.staff_id = staff_id;
        this.cus_id = cus_id;
        this.order_fullname = order_fullname;
        this.order_email = order_email;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.order_note = order_note;
        this.pay_create_at = pay_create_at;
        this.order_status = 0;
        this.pay_status = 1;

    }

    public Orders(int order_id, int staff_id, int cus_id, String order_fullname, String order_email, String order_phone, String order_address, String order_note, Date pay_create_at, String pro_name, int detail_quantity, String pro_picture) {
        super();
        this.order_id = order_id;
        this.staff_id = staff_id;
        this.cus_id = cus_id;
        this.order_fullname = order_fullname;
        this.order_email = order_email;
        this.order_phone = order_phone;
        this.order_address = order_address;
        this.order_note = order_note;
        this.pay_create_at = pay_create_at;
        this.order_status = 0;
        this.pay_status = 1;
        this.pro_name = pro_name;
        this.detail_quantity = detail_quantity;
        this.pro_picture = pro_picture;
    }

    @Override
    public String getPro_picture() {
        return pro_picture;
    }

    @Override
    public void setPro_picture(String pro_picture) {
        this.pro_picture = pro_picture;
    }

    @Override
    public int getDetail_quantity() {
        return detail_quantity;
    }

    @Override
    public void setDetail_quantity(int detail_quantity) {
        this.detail_quantity = detail_quantity;
    }

    @Override
    public String getPro_name() {
        return pro_name;
    }

    @Override
    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    @Override
    public int getOrder_id() {
        return order_id;
    }

    @Override
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public int getStaff_id() {
        return staff_id;
    }

    @Override
    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getOrder_fullname() {
        return order_fullname;
    }

    public void setOrder_fullname(String order_fullname) {
        this.order_fullname = order_fullname;
    }

    public String getOrder_email() {
        return order_email;
    }

    public void setOrder_email(String order_email) {
        this.order_email = order_email;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public void setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
    }

    public String getOrder_address() {
        return order_address;
    }

    public void setOrder_address(String order_address) {
        this.order_address = order_address;
    }

    public String getOrder_note() {
        return order_note;
    }

    public void setOrder_note(String order_note) {
        this.order_note = order_note;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public Date getPay_create_at() {
        return pay_create_at;
    }

    public void setPay_create_at(Date pay_create_at) {
        this.pay_create_at = pay_create_at;
    }

    public int getPay_status() {
        return pay_status;
    }

    public void setPay_status(int pay_status) {
        this.pay_status = pay_status;
    }

}
