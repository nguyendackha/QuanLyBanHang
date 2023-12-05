package Entity;

import java.util.Vector;

public class TaiKhoan {
    private String maTk;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private String LoaiTaiKhoan;

    // Constructor không tham số
    public TaiKhoan() {
        // Khởi tạo một số giá trị mặc định nếu cần
    }

    public TaiKhoan(String maTk, String TaiKhoan, String MatKhau, String Email, String LoaiTaiKhoan) {
        this.maTk = maTk;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public String getMaTk() {
        return maTk;
    }

    public void setMaTk(String maTk) {
        this.maTk = maTk;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public void setTaiKhoan(String TaiKhoan) {
        this.TaiKhoan = TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(String LoaiTaiKhoan) {
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public Vector<Object> toDataRow() {
        Vector<Object> row = new Vector<>();
        row.add(maTk);
        row.add(TaiKhoan);
        row.add(MatKhau);
        row.add(Email);
        row.add(LoaiTaiKhoan);
        return row;
    }
}
