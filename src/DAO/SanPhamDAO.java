package DAO;

import Entity.SanPham;
import Helper.XJdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends ManagerDAO<SanPham, Integer> {

    private final String INSERT_SQL = "INSERT INTO SanPham(MaLoaiSP, TenSanPham, DonVi, GiaTien, TrangThai, ImageSanPham, MaDanhMuc) VALUES(?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE SanPham SET MaLoaiSP=?, TenSanPham=?, DonVi=?, GiaTien=?, TrangThai=?, ImageSanPham=?, MaDanhMuc=? WHERE MaSanPham=?";
    private final String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham=?";
    private final String TOP_5_SOLD_SQL = "SELECT TOP(5) SanPham.TenSanPham, COUNT(ChiTietHoaDon.SoLuong) AS SoLuongBan\n"
            + "FROM dbo.SanPham INNER JOIN dbo.ChiTietHoaDon ON dbo.SanPham.MaSanPham = dbo.ChiTietHoaDon.MaSanPham\n"
            + "INNER JOIN dbo.HoaDon ON dbo.ChiTietHoaDon.MaHoaDon = dbo.HoaDon.MaHoaDon\n"
            + "WHERE HoaDon.TrangThai = N'Đã thanh toán' GROUP BY TenSanPham ORDER BY SoLuongBan DESC";

    @Override
    public void insert(SanPham entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaLoaiSP(),
                entity.getTenSanPham(),
                entity.getDonVi(),
                entity.getGiaTien(),
                entity.getTrangThai(),
                entity.getImageSanPham(),
                entity.getMaDanhMuc());
    }

    @Override
    public void update(SanPham entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaLoaiSP(),
                entity.getTenSanPham(),
                entity.getDonVi(),
                entity.getGiaTien(),
                entity.getTrangThai(),
                entity.getImageSanPham(),
                entity.getMaDanhMuc(),
                entity.getMaSanPham());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public SanPham selectById(Integer id) {
        List<SanPham> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<SanPham> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    public List<SanPham> getAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    public void add(SanPham entity) {
        this.insert(entity);
    }

    public List<Object[]> getTop5SoldSanPham() {
        List<Object[]> top5List = new ArrayList<>();
        try (ResultSet rs = XJdbc.executeQuery(TOP_5_SOLD_SQL)) {
            while (rs.next()) {
                Object[] item = new Object[2];
                item[0] = rs.getString("TenSanPham");
                item[1] = rs.getInt("SoLuongBan");
                top5List.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return top5List;
    }

    @Override
    protected List<SanPham> selectBySQL(String sql, Object... args) {
        List<SanPham> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                SanPham entity = new SanPham(
                        rs.getInt("MaSanPham"),
                        rs.getInt("MaLoaiSP"),
                        rs.getString("TenSanPham"),
                        rs.getString("DonVi"),
                        rs.getInt("GiaTien"),
                        rs.getString("TrangThai"),
                        rs.getString("ImageSanPham"),
                        rs.getInt("MaDanhMuc"));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
