package DAO;

import Entity.ChiTietHoaDon;
import Helper.XJdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO extends ManagerDAO<ChiTietHoaDon, Integer> {

    private final String INSERT_SQL = "INSERT INTO ChiTietHoaDon(MaHoaDon, MaSanPham, SoLuong, ThanhTien) VALUES(?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE ChiTietHoaDon SET MaHoaDon=?, MaSanPham=?, SoLuong=?, ThanhTien=? WHERE MaCTHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM ChiTietHoaDon WHERE MaCTHoaDon=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM ChiTietHoaDon";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietHoaDon WHERE MaCTHoaDon=?";
    private final String SELECT_BY_MAHOADON_SQL = "SELECT * FROM ChiTietHoaDon WHERE MaHoaDon=?";

    @Override
    public void insert(ChiTietHoaDon entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getThanhTien());
    }

    @Override
    public void update(ChiTietHoaDon entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaHoaDon(),
                entity.getMaSanPham(),
                entity.getSoLuong(),
                entity.getThanhTien(),
                entity.getMaCTHoaDon());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ChiTietHoaDon selectById(Integer id) {
        List<ChiTietHoaDon> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<ChiTietHoaDon> selectByMaHoaDon(int maHoaDon) {
        return this.selectBySQL(SELECT_BY_MAHOADON_SQL, maHoaDon);
    }

    @Override
    protected List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChiTietHoaDon entity = new ChiTietHoaDon(
                        rs.getInt("MaCTHoaDon"),
                        rs.getInt("MaHoaDon"),
                        rs.getInt("MaSanPham"),
                        rs.getInt("SoLuong"),
                        rs.getBigDecimal("ThanhTien"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
