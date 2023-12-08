package Entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class HoaDon {

    private String MaHoaDon;
    private String MaNhanVien;
    private String TenKhachHang;
    private Date NgayTao;
    private Date NgayThanhToan;
    private String MaKhuyenMai;
    private int TongTien;
    private boolean TrangThai;
    private String GhiChu;
    private Date Ngay;

    public HoaDon(String MaKhuyenMai, String string1, String string2, Date date, Date date1, String string3, int aInt, String string4, String string5, Date date2) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public HoaDon() {
    }

    public HoaDon(String MaHoaDon, String MaNhanVien, String TenKhachHang, Date NgayTao, Date NgayThanhToan, String TrangThai, int TongTien, String GhiChu, Date Ngay) {
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.TenKhachHang = TenKhachHang;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.MaKhuyenMai = MaKhuyenMai;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai.equals("1");
        this.GhiChu = GhiChu;
        this.Ngay = Ngay;
    }

    public String getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(String MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }

    public void setTenKhachHang(String TenKhachHang) {
        this.TenKhachHang = TenKhachHang;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(Date NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int TongTien) {
        this.TongTien = TongTien;
    }

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }

    public Date getNgay() {
        return Ngay;
    }

    public void setNgay(Date Ngay) {
        this.Ngay = Ngay;
    }
}
