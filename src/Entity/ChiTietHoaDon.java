package Entity;


public class ChiTietHoaDon {
    private String maCTHoaDon;
    private String maHoaDon;
    private String maSanPham;
    private int soLuong;
    private int thanhTien;

    // Constructors
    public ChiTietHoaDon(String maCTHoaDon, String maHoaDon, String maSanPham, int soLuong, int thanhTien) {
        this.maCTHoaDon = maCTHoaDon;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    // Getter and Setter methods
    public String getMaCTHoaDon() {
        return maCTHoaDon;
    }

    public void setMaCTHoaDon(String maCTHoaDon) {
        this.maCTHoaDon = maCTHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
