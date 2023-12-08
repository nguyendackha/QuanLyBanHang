package Entity;

public class KhuyenMai {
    private String MaKhuyenMai;
    private String TenSuKien;
    private int TienGiam;

    public KhuyenMai() {
    }

    public KhuyenMai(String MaKhuyenMai, String TenSuKien, int TienGiam) {
        this.MaKhuyenMai = MaKhuyenMai;
        this.TenSuKien = TenSuKien;
        this.TienGiam = TienGiam;
    }

    public String getMaKhuyenMai() {
        return MaKhuyenMai;
    }

    public void setMaKhuyenMai(String MaKhuyenMai) {
        this.MaKhuyenMai = MaKhuyenMai;
    }

    public String getTenSuKien() {
        return TenSuKien;
    }

    public void setTenSuKien(String TenSuKien) {
        this.TenSuKien = TenSuKien;
    }

    public int getTienGiam() {
        return TienGiam;
    }

    public void setTienGiam(int TienGiam) {
        this.TienGiam = TienGiam;
    }

    public Object[] toDataRow() {
        // Trả về một mảng Object chứa dữ liệu để hiển thị trong bảng
        return new Object[]{getMaKhuyenMai(), getTenSuKien(), getTienGiam()};
    }
}
