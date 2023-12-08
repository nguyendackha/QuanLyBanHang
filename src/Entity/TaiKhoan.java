package Entity;

public class TaiKhoan {
    private int MaTk;
    private String TaiKhoan;
    private String MatKhau;
    private String Email;
    private boolean LoaiTaiKhoan;

    public TaiKhoan() {
    }

    public TaiKhoan(int MaTk, String TaiKhoan, String MatKhau, String Email, boolean LoaiTaiKhoan) {
        this.MaTk = MaTk;
        this.TaiKhoan = TaiKhoan;
        this.MatKhau = MatKhau;
        this.Email = Email;
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public int getMaTk() {
        return MaTk;
    }

    public void setMaTk(int MaTk) {
        this.MaTk = MaTk;
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

    public boolean isLoaiTaiKhoan() {
        return LoaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(boolean LoaiTaiKhoan) {
        this.LoaiTaiKhoan = LoaiTaiKhoan;
    }

    public Object[] toDataRow() {
        return new Object[]{getMaTk(), getTaiKhoan(), getMatKhau(), getEmail(), isLoaiTaiKhoan()};
    }
}
