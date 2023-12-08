package DAO;

import Entity.NhanVien;
import Helper.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends ManagerDAO<NhanVien, String> {

    private static final String INSERT_SQL = "INSERT INTO NhanVien(MaNhanVien, MaChucVu, TenNhanVien, NgaySinh, GioiTinh, SDT, DiaChi, imageNV) VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE NhanVien SET MaChucVu=?, TenNhanVien=?, NgaySinh=?, GioiTinh=?, SDT=?, DiaChi=?, imageNV=? WHERE MaNhanVien=?";
    private static final String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNhanVien=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE MaNhanVien=?";

    @Override
    public void insert(NhanVien entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaNhanVien(),
                entity.getMaChucVu(),
                entity.getTenNhanVien(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getSDT(),
                entity.getDiaChi(),
                entity.getImageNV());
    }

    @Override
    public void update(NhanVien entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaChucVu(),
                entity.getTenNhanVien(),
                entity.getNgaySinh(),
                entity.getGioiTinh(),
                entity.getSDT(),
                entity.getDiaChi(),
                entity.getImageNV(),
                entity.getMaNhanVien());
        System.out.println("Cập nhật nhân viên thành công.");
    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<NhanVien> getNhanVienByChucVu(String maChucVu) {
        String sql = "SELECT * FROM NhanVien WHERE MaChucVu = ?";
        return selectBySQL(sql, maChucVu);
    }

    public NhanVien getNhanVienByTaiKhoan(String taiKhoan) {
        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien IN (SELECT MaNhanVien FROM TaiKhoan WHERE TK = ?)";
        try {
            List<NhanVien> list = this.selectBySQL(sql, taiKhoan);
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien(
                        rs.getString("MaNhanVien"),
                        rs.getString("MaChucVu"),
                        rs.getString("TenNhanVien"),
                        rs.getDate("NgaySinh"),
                        rs.getBoolean("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("DiaChi"),
                        rs.getString("imageNV"),
                        null);  // ChucVu sẽ được set sau, ở đây để null và set sau
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean exists(String maNhanVien) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE MaNhanVien=?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maNhanVien);
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
