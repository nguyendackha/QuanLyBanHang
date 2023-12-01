package Entity;

import java.math.BigDecimal;

public class ChiTietHoaDon {
    private int maCTHoaDon;
    private int maHoaDon;
    private int maSanPham; 
    private int soLuong;
    private BigDecimal thanhTien;

    // Constructors
    public ChiTietHoaDon(int maCTHoaDon, int maHoaDon, int maSanPham, int soLuong, BigDecimal thanhTien) {
        this.maCTHoaDon = maCTHoaDon;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    // Getter and Setter methods
    public int getMaCTHoaDon() {
        return maCTHoaDon;
    }

    public void setMaCTHoaDon(int maCTHoaDon) {
        this.maCTHoaDon = maCTHoaDon;
    }

    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
}
