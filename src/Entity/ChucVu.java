package Entity;

/**
 *
 * @author ASUS
 */
public class ChucVu {
    private String MaChucVu;
    private String TenChucVu;
    private int Luong;

    public ChucVu() {
    }

    public ChucVu(String MaChucVu, String TenChucVu, int Luong) {
        this.MaChucVu = MaChucVu;
        this.TenChucVu = TenChucVu;
        this.Luong = Luong;
    }

    public String getMaChucVu() {
        return MaChucVu;
    }

    public void setMaChucVu(String MaChucVu) {
        this.MaChucVu = MaChucVu;
    }

    public String getTenChucVu() {
        return TenChucVu;
    }

    public void setTenChucVu(String TenChucVu) {
        this.TenChucVu = TenChucVu;
    }

    public int getLuong() {
        return Luong;
    }

    public void setLuong(int Luong) {
        this.Luong = Luong;
    }

    public Object[] toDataRow1() {
        Object[] row = new Object[3]; // Số 3 là số cột trong bảng
        row[0] = getMaChucVu();
        row[1] = getTenChucVu();
        row[2] = getLuong();
        return row;
    }
}
