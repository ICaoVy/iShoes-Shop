/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Administrator
 */
public class Order_Details extends Product {

    private int detail_id;
    private int pro_id;
    private int order_id;
    private float detail_price;
    private int detail_quantity;
    private int detail_size;
    private String detail_colour;
    private String pro_picture;

    public Order_Details() {
    }

    public Order_Details(int detail_id, int pro_id, int order_id, float detail_price, int detail_quantity, int detail_size, String detail_colour, String pro_picture) {
        super();
        this.detail_id = detail_id;
        this.pro_id = pro_id;
        this.order_id = order_id;
        this.detail_price = detail_price;
        this.detail_quantity = detail_quantity;
        this.detail_size = detail_size;
        this.detail_colour = detail_colour;
        this.pro_picture = pro_picture;
    }

    public Order_Details(int detail_id, int pro_id, int order_id, float detail_price, int detail_quantity, int detail_size, String detail_colour) {

        this.detail_id = detail_id;
        this.pro_id = pro_id;
        this.order_id = order_id;
        this.detail_price = detail_price;
        this.detail_quantity = detail_quantity;
        this.detail_size = detail_size;
        this.detail_colour = detail_colour;

    }

    @Override
    public String getPro_picture() {
        return pro_picture;
    }

    @Override
    public void setPro_picture(String pro_picture) {
        this.pro_picture = pro_picture;
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    @Override
    public int getPro_id() {
        return pro_id;
    }

    @Override
    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public float getDetail_price() {
        return detail_price;
    }

    public void setDetail_price(float detail_price) {
        this.detail_price = detail_price;
    }

    public int getDetail_quantity() {
        return detail_quantity;
    }

    public void setDetail_quantity(int detail_quantity) {
        this.detail_quantity = detail_quantity;
    }

    public int getDetail_size() {
        return detail_size;
    }

    public void setDetail_size(int detail_size) {
        this.detail_size = detail_size;
    }

    public String getDetail_colour() {
        return detail_colour;
    }

    public void setDetail_colour(String detail_colour) {
        this.detail_colour = detail_colour;
    }

}
