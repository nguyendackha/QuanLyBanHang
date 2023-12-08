package Entity;

public class SanPham {
    private String MaSanPham;
    private String TenSanPham;
    private String MaDanhMuc;
    private int DonVi;
    private int GiaTien;
    private String TrangThai;
    private String imageSanPham;

    public SanPham() {
    }

    public SanPham(String MaSanPham, String TenSanPham, String MaDanhMuc, int DonVi, int GiaTien, String TrangThai, String imageSanPham) {
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
        this.MaDanhMuc = MaDanhMuc;
        this.DonVi = DonVi;
        this.GiaTien = GiaTien;
        this.TrangThai = TrangThai;
        this.imageSanPham = imageSanPham;
    }

    public String getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(String MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getMaDanhMuc() {
        return MaDanhMuc;
    }

    public void setMaDanhMuc(String MaDanhMuc) {
        this.MaDanhMuc = MaDanhMuc;
    }

    public int getDonVi() {
        return DonVi;
    }

    public void setDonVi(int DonVi) {
        this.DonVi = DonVi;
    }

    public int getGiaTien() {
        return GiaTien;
    }

    public void setGiaTien(int GiaTien) {
        this.GiaTien = GiaTien;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(String imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    // Các phương thức getter và setter

    // Thêm phương thức toDataRow2()
    public Object[] toDataRow2() {
        return new Object[]{getMaSanPham(), getTenSanPham(), getDonVi(), getGiaTien(), getTrangThai(), getImageSanPham(), getMaDanhMuc()};
    }
}
