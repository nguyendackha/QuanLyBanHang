/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author ASUS
 */
public class NhanVien {

    private String MaNhanVien;
    private int MaChucVu;
    private String TenNhanVien;
    private String NgaySinh;
    private String GioiTinh;
    private String SDT;
    private String DiaChi;
    private String imageNV;
    private ChucVu ChucVu;

    public NhanVien() {
    }

    public NhanVien(String MaNhanVien, int MaChucVu, String TenNhanVien, String NgaySinh, String GioiTinh, String SDT, String DiaChi, String imageNV, ChucVu ChucVu) {
        this.MaNhanVien = MaNhanVien;
        this.MaChucVu = MaChucVu;
        this.TenNhanVien = TenNhanVien;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SDT = SDT;
        this.DiaChi = DiaChi;
        this.imageNV = imageNV;
        this.ChucVu = ChucVu;
    }

    

    public String getMaNhanVien() {
        return MaNhanVien;
    }

    public void setMaNhanVien(String MaNhanVien) {
        this.MaNhanVien = MaNhanVien;
    }

    public int getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(int MaChucVu) {
        this.MaChucVu = MaChucVu;
    }


    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getImageNV() {
        return imageNV;
    }

    public void setImageNV(String imageNV) {
        this.imageNV = imageNV;
    }

    public ChucVu getChucVu() {
        return ChucVu;
    }

    public void setChucVu(ChucVu ChucVu) {
        this.ChucVu = ChucVu;
    }
    public Object[] toDataRow() {
        Object[] row = new Object[8]; // Số 8 là số cột trong bảng

        row[0] = getMaNhanVien();
        row[1] = getTenNhanVien();
        row[2] = getNgaySinh();
        row[3] = getGioiTinh();
        row[4] = getSDT();
        row[5] = getDiaChi();
        row[6] = getImageNV();
        row[7] = getChucVu().getTenChucVu(); // Điều này giả định rằng bạn muốn hiển thị tên chức vụ

        return row;
    }

    
}
