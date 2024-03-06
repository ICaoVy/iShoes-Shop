/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author xuan
 */

public class Carts extends Product {
   
    private int cart_id;
    private int cus_id;
    private int pro_id;
    private float cart_price;
    private int cart_quantity;
    private int cart_size;
    private String cart_colour;
    private String pro_picture;
    private String pro_name;

    public Carts() {
    }
    
    
    


    public Carts(int cart_id, int cus_id, int pro_id, float cart_price, int cart_quantity, int cart_size, String cart_colour, String pro_picture, String pro_name) {
        super();
        this.cart_id = cart_id;
        this.cus_id = cus_id;
        this.pro_id = pro_id;
        this.cart_price = cart_price * cart_quantity;
        this.cart_quantity = cart_quantity;
        this.cart_size = cart_size;
        this.cart_colour = cart_colour;
        this.pro_picture = pro_picture;
        this.pro_name = pro_name;
       
       
    }

    public String getPro_picture() {
        return pro_picture;
    }

    public void setPro_picture(String pro_picture) {
        this.pro_picture = pro_picture;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }
    
    

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public float getCart_price() {
        return cart_price;
    }

    public void setCart_price(float cart_price) {
        this.cart_price = cart_price;
    }

    public int getCart_quantity() {
        return cart_quantity;
    }

    public void setCart_quantity(int cart_quantity) {
        this.cart_quantity = cart_quantity;
    }

    public int getCart_size() {
        return cart_size;
    }

    public void setCart_size(int cart_size) {
        this.cart_size = cart_size;
    }

    public String getCart_colour() {
        return cart_colour;
    }

    public void setCart_colour(String cart_colour) {
        this.cart_colour = cart_colour;
    }
    
    public Float getTotal_cart() {
        return cart_price * cart_quantity ;
    }
    
}
