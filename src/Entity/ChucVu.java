/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class ChucVu {
    private int MaChucVu;
    private String TenChucVu;
    private BigDecimal Luong;

    public ChucVu() {
    }

    public ChucVu(int MaChucVu, String TenChucVu, BigDecimal Luong) {
        this.MaChucVu = MaChucVu;
        this.TenChucVu = TenChucVu;
        this.Luong = Luong;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int MaChucVu) {
        this.MaChucVu = MaChucVu;
    }
 

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String TenChucVu) {
        this.TenChucVu = TenChucVu;
    }

    public BigDecimal getLuong() {
        return Luong;
    }

    public void setLuong(BigDecimal Luong) {
        this.Luong = Luong;
    }
    public Object[] toDataRow1() {
        Object[] row = new Object[3]; // Số 3 là số cột trong bảng

        row[0] = getMaChucVu();
        row[1] = getTenChucVu();
        row[2] = getLuong();

        return row;
    }


    
}
