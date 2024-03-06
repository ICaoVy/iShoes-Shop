/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author ASUS
 */
public class Gallery {
    private int gal;
    private int pro_id;
    private String gal_picture;

    public Gallery() {
    }

    public Gallery(int gal, int pro_id, String gal_picture) {
        this.gal = gal;
        this.pro_id = pro_id;
        this.gal_picture = gal_picture;
    }

    public int getGal() {
        return gal;
    }

    public void setGal(int gal) {
        this.gal = gal;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getGal_picture() {
        return gal_picture;
    }

    public void setGal_picture(String gal_picture) {
        this.gal_picture = gal_picture;
    }
    
    
}
