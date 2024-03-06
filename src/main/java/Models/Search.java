/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.util.Comparator;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Search {

    private int pro_id;
    private int cate_id;
    private String pro_code;
    private String pro_name;
    private float pro_price;
    private float pro_discount;
    private String pro_picture;
    private int pro_size;
    private String pro_colour;
    private String pro_brand;
    private String pro_origin;
    private String pro_material;
    private String pro_description;
    private Date pro_create_at;
    private Date pro_update_at;
    private int pro_delete_at;
    private String cate_name;
    private int cateD_id;
    private String cateD_name;

    public Search() {
    }

    public Search(int pro_id, int cate_id, String pro_code, String pro_name, float pro_price, float pro_discount, String pro_picture, int pro_size, String pro_colour, String pro_brand, String pro_origin, String pro_material, String pro_description, Date pro_create_at, Date pro_update_at, int pro_delete_at, String cate_name, int cateD_id, String cateD_name) {
        this.pro_id = pro_id;
        this.cate_id = cate_id;
        this.pro_code = pro_code;
        this.pro_name = pro_name;
        this.pro_price = pro_price;
        this.pro_discount = pro_discount;
        this.pro_picture = pro_picture;
        this.pro_size = pro_size;
        this.pro_colour = pro_colour;
        this.pro_brand = pro_brand;
        this.pro_origin = pro_origin;
        this.pro_material = pro_material;
        this.pro_description = pro_description;
        this.pro_create_at = pro_create_at;
        this.pro_update_at = pro_update_at;
        this.pro_delete_at = pro_delete_at;
        this.cate_name = cate_name;
        this.cateD_id = cateD_id;
        this.cateD_name = cateD_name;
    }





    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getPro_code() {
        return pro_code;
    }

    public void setPro_code(String pro_code) {
        this.pro_code = pro_code;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public float getPro_price() {
        return pro_price;
    }

    public void setPro_price(float pro_price) {
        this.pro_price = pro_price;
    }

    public float getPro_discount() {
        return pro_discount;
    }

    public void setPro_discount(float pro_discount) {
        this.pro_discount = pro_discount;
    }

    public String getPro_picture() {
        return pro_picture;
    }

    public void setPro_picture(String pro_picture) {
        this.pro_picture = pro_picture;
    }

    public int getPro_size() {
        return pro_size;
    }

    public void setPro_size(int pro_size) {
        this.pro_size = pro_size;
    }

    public String getPro_colour() {
        return pro_colour;
    }

    public void setPro_colour(String pro_colour) {
        this.pro_colour = pro_colour;
    }

    public String getPro_brand() {
        return pro_brand;
    }

    public void setPro_brand(String pro_brand) {
        this.pro_brand = pro_brand;
    }

    public String getPro_origin() {
        return pro_origin;
    }

    public void setPro_origin(String pro_origin) {
        this.pro_origin = pro_origin;
    }

    public String getPro_material() {
        return pro_material;
    }

    public void setPro_material(String pro_material) {
        this.pro_material = pro_material;
    }

    public String getPro_description() {
        return pro_description;
    }

    public void setPro_description(String pro_description) {
        this.pro_description = pro_description;
    }

    public Date getPro_create_at() {
        return pro_create_at;
    }

    public void setPro_create_at(Date pro_create_at) {
        this.pro_create_at = pro_create_at;
    }

    public Date getPro_update_at() {
        return pro_update_at;
    }

    public void setPro_update_at(Date pro_update_at) {
        this.pro_update_at = pro_update_at;
    }

    public int getPro_delete_at() {
        return pro_delete_at;
    }

    public void setPro_delete_at(int pro_delete_at) {
        this.pro_delete_at = pro_delete_at;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCateD_id() {
        return cateD_id;
    }

    public void setCateD_id(int cateD_id) {
        this.cateD_id = cateD_id;
    }

    public String getCateD_name() {
        return cateD_name;
    }

    public void setCateD_name(String cateD_name) {
        this.cateD_name = cateD_name;
    }



}
