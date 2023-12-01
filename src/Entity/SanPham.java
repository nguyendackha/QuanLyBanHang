    package Entity;

public class SanPham {
    private int maSanPham;
    private int maLoaiSP;
    private String tenSanPham;
    private String donVi;
    private int giaTien;
    private String trangThai;
    private String imageSanPham;

    // Thêm thuộc tính mới cho mã danh mục
    private int maDanhMuc;

    // Constructors
    public SanPham() {
    }

    public SanPham(int maSanPham, int maLoaiSP, String tenSanPham, String donVi, int giaTien, String trangThai, String imageSanPham, int maDanhMuc) {
        this.maSanPham = maSanPham;
        this.maLoaiSP = maLoaiSP;
        this.tenSanPham = tenSanPham;
        this.donVi = donVi;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
        this.imageSanPham = imageSanPham;
        this.maDanhMuc = maDanhMuc; 
    }

    // Getter and Setter methods
    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getMaLoaiSP() {
        return maLoaiSP;
    }

    public void setMaLoaiSP(int maLoaiSP) {
        this.maLoaiSP = maLoaiSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getImageSanPham() {
        return imageSanPham;
    }

    public void setImageSanPham(String imageSanPham) {
        this.imageSanPham = imageSanPham;
    }

    public int getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(int maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    // Thêm phương thức toDataRow2()
    public Object[] toDataRow2() {
        return new Object[]{getMaSanPham(), getMaLoaiSP(), getTenSanPham(), getDonVi(), getGiaTien(), getTrangThai(), getImageSanPham(), getMaDanhMuc()};
    }

    // Thêm phương thức getDanhMuc()
    public DanhMuc getDanhMuc() {
        // Thực hiện logic để trả về đối tượng DanhMuc tương ứng với mã danh mục
        // Trong trường hợp này, bạn có thể trả về một đối tượng DanhMuc hoặc null nếu không tìm thấy
        // Ví dụ:
        // return danhMucService.findById(this.maDanhMuc);
        return null; // Hoặc xử lý khác tùy thuộc vào yêu cầu của bạn
    }
}
