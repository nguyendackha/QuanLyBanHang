package DAO;

import Entity.ChiTietHoaDon;
import Helper.XJdbc;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO extends ManagerDAO<ChiTietHoaDon, String> {

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
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ChiTietHoaDon selectById(String id) {
        List<ChiTietHoaDon> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<ChiTietHoaDon> selectByMaHoaDon(String maHoaDon) {
        return this.selectBySQL(SELECT_BY_MAHOADON_SQL, maHoaDon);
    }

    public String getMaSanPhamByMaCTHoaDon(String maCTHoaDon) {
        ChiTietHoaDon chiTietHoaDon = selectById(maCTHoaDon);
        if (chiTietHoaDon != null) {
            return chiTietHoaDon.getMaSanPham();
        }
        return null;
     
    }

    @Override
    protected List<ChiTietHoaDon> selectBySQL(String sql, Object... args) {
        List<ChiTietHoaDon> list = new ArrayList<>();
        try {
            XJdbc.executeUpdate(sql, args);
            try (var rs = XJdbc.executeQuery(sql, args)) {
                while (rs.next()) {
                    ChiTietHoaDon entity = new ChiTietHoaDon(
                            rs.getString("MaCTHoaDon"),
                            rs.getString("MaHoaDon"),
                            rs.getString("MaSanPham"),
                            rs.getInt("SoLuong"),
                            rs.getInt("ThanhTien"));
                    list.add(entity);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
