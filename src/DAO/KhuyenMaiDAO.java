package DAO;

import Entity.KhuyenMai;
import Helper.XJdbc;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhuyenMaiDAO extends ManagerDAO<KhuyenMai, Integer> {

    private final String INSERT_SQL = "INSERT INTO KhuyenMai(TenSuKien, TienGiam) VALUES(?,?)";
    private final String UPDATE_SQL = "UPDATE KhuyenMai SET TenSuKien=?, TienGiam=? WHERE MaKhuyenMai=?";
    private final String DELETE_SQL = "DELETE FROM KhuyenMai WHERE MaKhuyenMai=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM KhuyenMai";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM KhuyenMai WHERE MaKhuyenMai=?";

    @Override
    public void insert(KhuyenMai entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getTenSuKien(),
                entity.getTienGiam());
    }

    @Override
    public void update(KhuyenMai entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTenSuKien(),
                entity.getTienGiam(),
                entity.getMaKhuyenMai());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public KhuyenMai selectById(Integer id) {
        List<KhuyenMai> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhuyenMai> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<KhuyenMai> getAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

    public Integer getMaKhuyenMai(KhuyenMai entity) {
        return entity.getMaKhuyenMai();
    }

    public BigDecimal getTienGiamByMaKhuyenMai(String maKhuyenMai) {
    String sql = "SELECT TienGiam FROM KhuyenMai WHERE MaKhuyenMai=?";
    try {
        ResultSet rs = XJdbc.executeQuery(sql, maKhuyenMai);
        if (rs.next()) {
            return rs.getBigDecimal("TienGiam");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return BigDecimal.ZERO; // hoặc giá trị mặc định khác nếu không tìm thấy
}

    public BigDecimal getTienGiam(String maKhuyenMai) {
        String sql = "SELECT TienGiam FROM KhuyenMai WHERE MaKhuyenMai=?";
        try {
            ResultSet rs = XJdbc.executeQuery(sql, maKhuyenMai);
            if (rs.next()) {
                return rs.getBigDecimal("TienGiam");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return BigDecimal.ZERO; // hoặc giá trị mặc định khác nếu không tìm thấy
    }

    @Override
    protected List<KhuyenMai> selectBySQL(String sql, Object... args) {
        List<KhuyenMai> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                KhuyenMai entity = new KhuyenMai(
                        rs.getInt("MaKhuyenMai"),
                        rs.getString("TenSuKien"),
                        rs.getInt("TienGiam"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
