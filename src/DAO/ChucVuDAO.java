package DAO;

import Entity.ChucVu;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChucVuDAO extends ManagerDAO<ChucVu, String> {

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
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public ChucVu selectById(String id) {
        List<ChucVu> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ChucVu> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
 
   public String getTenChucVuById(String maChucVu) {
    String sql = "SELECT TenChucVu FROM ChucVu WHERE MaChucVu=?";
    try {
        ResultSet rs = XJdbc.executeQuery(sql, maChucVu);
        if (rs.next()) {
            return rs.getString("TenChucVu");
        }
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return null;
}

    @Override
    protected List<ChucVu> selectBySQL(String sql, Object... args) {
        List<ChucVu> list = new ArrayList<>();
        try {
            List<Object[]> results = (List<Object[]>) XJdbc.executeQuery(sql, args);
            for (Object[] result : results) {
                ChucVu entity = new ChucVu(
                        (String) result[0],
                        (String) result[1],
                        (int) result[2]);
                list.add(entity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
