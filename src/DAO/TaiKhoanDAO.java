package DAO;

import Entity.TaiKhoan;
import Helper.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class TaiKhoanDAO extends ManagerDAO<TaiKhoan, String> { 
    private static final String INSERT_SQL = "INSERT INTO TaiKhoan(MaTk, TaiKhoan, MatKhau, Email, LoaiTaiKhoan) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE TaiKhoan SET TaiKhoan=?, MatKhau=?, Email=?, LoaiTaiKhoan=? WHERE MaTk=?";
    private static final String DELETE_SQL = "DELETE FROM TaiKhoan WHERE MaTk=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE MaTk=?";
    private static final String SELECT_TEN_NHANVIEN_BY_TK = "SELECT dbo.NhanVien.TenNhanVien "
            + "FROM dbo.NhanVien INNER JOIN dbo.TaiKhoan ON dbo.NhanVien.MaNhanVien = dbo.TaiKhoan.MaNhanVien "
            + "WHERE TaiKhoan.TaiKhoan = ?";

    @Override
    public void insert(TaiKhoan entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaTk(),
                entity.getTk(),
                entity.getMk(),
                entity.getEmail(),
                entity.getLoaiTK());
    }

    @Override
    public void update(TaiKhoan entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTk(),
                entity.getMk(),
                entity.getEmail(),
                entity.getLoaiTK(),
                entity.getMaTk());
    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public TaiKhoan selectById(String id) {
        List<TaiKhoan> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectAll() {
        String selectAllQuery = "SELECT * FROM TaiKhoan";
        return selectBySQL(selectAllQuery);
    }

    public static List<TaiKhoan> getAll() {
        TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
        return taiKhoanDAO.selectAll();
    }

    @Override
    protected List<TaiKhoan> selectBySQL(String sql, Object... args) {
        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan(
                        rs.getString("MaTk"),
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("Email"),
                        rs.getString("LoaiTaiKhoan"));
                list.add(entity);
            }
//            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Phương thức mới để lấy tên nhân viên từ tài khoản
    public String getTenNhanVienByTaiKhoan(String taiKhoan) {
        try {
            ResultSet rs = XJdbc.executeQuery(SELECT_TEN_NHANVIEN_BY_TK, taiKhoan);
            if (rs.next()) {
                return rs.getString("TenNhanVien");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Phương thức chuyển đổi danh sách thành Vector<Vector<Object>>
    public Vector<Vector<Object>> toDataVector(List<TaiKhoan> list) {
        Vector<Vector<Object>> dataVector = new Vector<>();
        for (TaiKhoan taiKhoan : list) {
            dataVector.add(taiKhoan.toDataRow());
        }
        return dataVector;
    }
}


