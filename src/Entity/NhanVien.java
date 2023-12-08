package Entity;

import java.util.Date;

/**
 * 
 * @author ASUS
 */
public class NhanVien {

    private String MaNhanVien;
    private int MaTk;
    private String MaChucVu;
    private String TenNhanVien;
    private Date NgaySinh;
    private boolean GioiTinh;
    private String SDT;
    private String DiaChi;
    private String imageNV;
    private ChucVu ChucVu; // Assume that ChucVu is another class with at least a TenChucVu field

    public NhanVien() {
    }

    public NhanVien(String MaNhanVien, String MaChucVu, String TenNhanVien, Date NgaySinh, boolean GioiTinh, String SDT, String DiaChi, String imageNV, ChucVu ChucVu) {
        this.MaNhanVien = MaNhanVien;
        this.MaTk = MaTk;
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

    public int getMaTk() {
        return MaTk;
    }

    public void setMaTk(int MaTk) {
        this.MaTk = MaTk;
    }

    public String getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(String MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean GioiTinh) {
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
        Object[] row = new Object[9]; // Số 9 là số cột trong bảng
        row[0] = getMaNhanVien();
        row[1] = getTenNhanVien();
        row[2] = getNgaySinh();
        row[3] = getGioiTinh();
        row[4] = getSDT();
        row[5] = getDiaChi();
        row[6] = getImageNV();

        // Kiểm tra null trước khi truy cập ChucVu
        if (getChucVu() != null) {
            row[7] = getChucVu().getTenChucVu(); // Điều này giả định rằng bạn muốn hiển thị tên chức vụ
        } else {
            row[7] = ""; // hoặc một giá trị mặc định khác nếu ChucVu là null
        }

        row[8] = getMaTk(); // Thêm MaTk vào mảng

        return row;
    }
}
