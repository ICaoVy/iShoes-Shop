/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author asus
 */
public class Category {

    private int cate_id;
    private int cateD_id;
    private String cate_name;

    public Category() {
    }

    public Category(int cate_id, int cateD_id, String cate_name) {
        this.cate_id = cate_id;
        this.cateD_id = cateD_id;
        this.cate_name = cate_name;
    }

    public int getCateD_id() {
        return cateD_id;
    }

    public void setCateD_id(int cateD_id) {
        this.cateD_id = cateD_id;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

}
