package Entity;

import java.math.BigDecimal;

/**
 *
 * @author ASUS
 */
public class HoaDon {

    private int MaHoaDon;
    private String MaNhanVien;
    private String TenKhachHang;
    private String NgayTao;
    private String NgayThanhToan;
    private BigDecimal TongTien;
    private String TrangThai;
    private String TenNhanVien;
    private String GhiChu; 
     private String MaKhuyenMai;

    public HoaDon(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public HoaDon() {
    }

    public HoaDon(int MaHoaDon, String MaNhanVien, String TenKhachHang, String NgayTao, String NgayThanhToan, String TrangThai, BigDecimal TongTien, String TenNhanVien, String GhiChu) {
        this.MaHoaDon = MaHoaDon;
        this.MaNhanVien = MaNhanVien;
        this.TenKhachHang = TenKhachHang;
        this.NgayTao = NgayTao;
        this.NgayThanhToan = NgayThanhToan;
        this.TongTien = TongTien;
        this.TrangThai = TrangThai;
        this.TenNhanVien = TenNhanVien;
        this.GhiChu = GhiChu;
    }

    public BigDecimal getTongTien() {
        return TongTien;
    }

    public void setTongTien(BigDecimal TongTien) {
        this.TongTien = TongTien;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
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

    public String getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(String NgayTao) {
        this.NgayTao = NgayTao;
    }

    public String getNgayThanhToan() {
        return NgayThanhToan;
    }

    public void setNgayThanhToan(String NgayThanhToan) {
        this.NgayThanhToan = NgayThanhToan;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }
    
}
