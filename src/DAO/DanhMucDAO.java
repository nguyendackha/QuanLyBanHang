package DAO;

import Entity.DanhMuc;
import Helper.XJdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO extends ManagerDAO<DanhMuc, String> {

    private final String INSERT_SQL = "INSERT INTO DanhMuc(MaDanhMuc, TenDanhMuc) VALUES(?, ?)";
    private final String UPDATE_SQL = "UPDATE DanhMuc SET MaDanhMuc=?, TenDanhMuc=? WHERE MaDanhMuc=?";
    private final String DELETE_SQL = "DELETE FROM DanhMuc WHERE MaDanhMuc=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM DanhMuc";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM DanhMuc WHERE MaDanhMuc=?";
    
    @Override
    public void insert(DanhMuc entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getMaDanhMuc(), entity.getTenDanhMuc());
    }

    @Override
    public void update(DanhMuc entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getMaDanhMuc(), entity.getTenDanhMuc());
    }

    @Override
    public void delete(String maDanhMuc) {
        XJdbc.executeUpdate(DELETE_SQL, maDanhMuc);
    }

    @Override
    public DanhMuc selectById(String maDanhMuc) {
        List<DanhMuc> list = this.selectBySQL(SELECT_BY_ID_SQL, maDanhMuc);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DanhMuc> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }
    public List<DanhMuc> getAll() {
        String sql = "SELECT * FROM DanhMuc";
        try {
            List<Object[]> results = (List<Object[]>) XJdbc.executeQuery(sql);
            List<DanhMuc> list = new ArrayList<>();

            for (Object[] result : results) {
                DanhMuc danhMuc = new DanhMuc("MaDanhMuc", "TenDanhMuc");
                danhMuc.setMaDanhMuc((String) result[0]);
                danhMuc.setTenDanhMuc((String) result[1]);
                list.add(danhMuc);
            }

            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected List<DanhMuc> selectBySQL(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DanhMuc entity = new DanhMuc(
                        rs.getString("MaDanhMuc"),
                        rs.getString("TenDanhMuc"));
                list.add(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
