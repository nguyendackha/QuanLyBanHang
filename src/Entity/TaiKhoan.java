
package Entity;



import java.util.Vector;

public class TaiKhoan {
    private String maTk;
    private String tk;
    private String mk;
    private String email;
    private String loaiTK;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNV, String tk, String mk, String email, String loaiTK) {
        this.maTk = maTk;
        this.tk = tk;
        this.mk = mk;
        this.email = email;
        this.loaiTK = loaiTK;
    }

    public String getMaTk() {
        return maTk;
    }

    public void setMaTk(String maTk) {
        this.maTk = maTk;
    }

    public String getTk() {
        return tk;
    }

    public void setTk(String tk) {
        this.tk = tk;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoaiTK() {
        return loaiTK;
    }

    public void setLoaiTK(String loaiTK) {
        this.loaiTK = loaiTK;
    }

    // Các phương thức getter và setter đã có

    // Phương thức chuyển đổi thành Vector<Object>
    public Vector<Object> toDataRow() {
        Vector<Object> row = new Vector<>();
        row.add(maTk);
        row.add(tk);
        row.add(mk);
        row.add(email);
        row.add(loaiTK);
        return row;
    }
}
