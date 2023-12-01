package DAO;

import Entity.ThongKe;
import Helper.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO extends ManagerDAO<ThongKe, Integer> {
    private static final String INSERT_SQL = "INSERT INTO ThongKe(NgayThongKe, SoLuongSanPham, DoanhThu, MaSP) VALUES(?,?,?,?)";
    private static final String UPDATE_SQL = "UPDATE ThongKe SET NgayThongKe=?, SoLuongSanPham=?, DoanhThu=?, MaSP=? WHERE MaThongKe=?";
    private static final String DELETE_SQL = "DELETE FROM ThongKe WHERE MaThongKe=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM ThongKe";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM ThongKe WHERE MaThongKe=?";
    private static final String SELECT_BY_MA_SP_SQL = "SELECT * FROM ThongKe WHERE MaSP=?";

    @Override
    public void insert(ThongKe entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getNgayThongKe(),
                entity.getSoLuongSanPham(),
                entity.getDoanhThu(),
                entity.getMaSP());
    }

    @Override
    public void update(ThongKe entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getNgayThongKe(),
                entity.getSoLuongSanPham(),
                entity.getDoanhThu(),
                entity.getMaSP(),
                entity.getMaThongKe());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ThongKe selectById(Integer id) {
        List<ThongKe> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ThongKe> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<ThongKe> traCuuByMaSP(String maSP) {
        return this.selectBySQL(SELECT_BY_MA_SP_SQL, maSP);
    }
    

    @Override
    protected List<ThongKe> selectBySQL(String sql, Object... args) {
        List<ThongKe> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ThongKe entity = new ThongKe(
                        rs.getInt("MaThongKe"),
                        rs.getDate("NgayThongKe"),
                        rs.getInt("SoLuongSanPham"),
                        rs.getDouble("DoanhThu"),
                        rs.getString("MaSP")
                );
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
