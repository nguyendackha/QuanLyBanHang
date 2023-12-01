package Entity;

import java.util.Date;

public class ThongKe {
    private int maThongKe;
    private Date ngayThongKe;
    private int soLuongSanPham;
    private double doanhThu;
    private String MaSP; 

    // Constructors, getters, and setters

    public ThongKe(int maThongKe, Date ngayThongKe, int soLuongSanPham, double doanhThu, String string) {
        this.maThongKe = maThongKe;
        this.ngayThongKe = ngayThongKe;
        this.soLuongSanPham = soLuongSanPham;
        this.doanhThu = doanhThu;
        this.MaSP = MaSP;
    }

    public int getMaThongKe() {
        return maThongKe;
    }

    public void setMaThongKe(int maThongKe) {
        this.maThongKe = maThongKe;
    }

    public Date getNgayThongKe() {
        return ngayThongKe;
    }

    public void setNgayThongKe(Date ngayThongKe) {
        this.ngayThongKe = ngayThongKe;
    }

    public int getSoLuongSanPham() {
        return soLuongSanPham;
    }

    public void setSoLuongSanPham(int soLuongSanPham) {
        this.soLuongSanPham = soLuongSanPham;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }
}
