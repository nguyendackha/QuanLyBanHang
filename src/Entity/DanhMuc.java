package Entity;

public class DanhMuc {
    private int MaSanPham;
    private String TenSanPham;
    
    public DanhMuc(int MaSanPham, String TenSanPham) {
        this.MaSanPham = MaSanPham;
        this.TenSanPham = TenSanPham;
    }

    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public Object[] toDataRow() {
        // Trả về một mảng Object chứa dữ liệu để hiển thị trong bảng
        return new Object[]{getMaSanPham(), getTenSanPham()};
    }
}
