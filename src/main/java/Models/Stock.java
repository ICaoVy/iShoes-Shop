/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
public class Stock {
    private int stock_id;
    private int staff_id;
    private int pro_id;
    private int stock_import;
    private int stock_export;
    private Date import_at;

    public Stock() {
    }

    public Stock(int stock_id, int staff_id, int pro_id, int stock_import, int stock_export, Date import_at) {
        this.stock_id = stock_id;
        this.staff_id = staff_id;
        this.pro_id = pro_id;
        this.stock_import = stock_import;
        this.stock_export = stock_export;
        this.import_at = import_at;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public int getStock_import() {
        return stock_import;
    }

    public void setStock_import(int stock_import) {
        this.stock_import = stock_import;
    }

    public int getStock_export() {
        return stock_export;
    }

    public void setStock_export(int stock_export) {
        this.stock_export = stock_export;
    }

    public Date getImport_at() {
        return import_at;
    }

    public void setImport_at(Date import_at) {
        this.import_at = import_at;
    }

    
    
}
