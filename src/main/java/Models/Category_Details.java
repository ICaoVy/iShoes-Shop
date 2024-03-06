/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author ASUS
 */
public class Category_Details {
    private int cateD_id;
    private int cateD_name;

    public Category_Details() {
    }

    public Category_Details(int cateD_id, int cateD_name) {
        this.cateD_id = cateD_id;
        this.cateD_name = cateD_name;
    }

    public int getCateD_id() {
        return cateD_id;
    }

    public void setCateD_id(int cateD_id) {
        this.cateD_id = cateD_id;
    }

    public int getCateD_name() {
        return cateD_name;
    }

    public void setCateD_name(int cateD_name) {
        this.cateD_name = cateD_name;
    }
    
    
}
