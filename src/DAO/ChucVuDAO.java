package DAO;

import Entity.ChucVu;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAO extends ManagerDAO<ChucVu, Integer> {

    private final String INSERT_SQL = "INSERT INTO ChucVu(TenChucVu, Luong) VALUES(?, ?)";
    private final String UPDATE_SQL = "UPDATE ChucVu SET TenChucVu=?, Luong=? WHERE MaChucVu=?";
    private final String DELETE_SQL = "DELETE FROM ChucVu WHERE MaChucVu=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM ChucVu";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM ChucVu WHERE MaChucVu=?";

    @Override
    public void insert(ChucVu entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getTenChucVu(),
                entity.getLuong());
    }

    @Override
    public void update(ChucVu entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getTenChucVu(),
                entity.getLuong(),
                entity.getMaChucVu());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ChucVu selectById(Integer id) {
        List<ChucVu> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChucVu> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    public String getTenChucVuById(int maChucVu) {
    String sql = "SELECT TenChucVu FROM ChucVu WHERE MaChucVu=?";
    try {
        ResultSet rs = XJdbc.executeQuery(sql, maChucVu);
        if (rs.next()) {
            return rs.getString("TenChucVu");
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return null;
}

    @Override
    protected List<ChucVu> selectBySQL(String sql, Object... args) {
        List<ChucVu> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                ChucVu entity = new ChucVu(
                        rs.getInt("MaChucVu"),
                        rs.getString("TenChucVu"),
                        rs.getBigDecimal("Luong"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
