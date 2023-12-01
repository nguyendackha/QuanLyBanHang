    package DAO;

import Entity.DanhMuc;
import Helper.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhMucDAO extends ManagerDAO<DanhMuc, Integer> {

    private final String INSERT_SQL = "INSERT INTO DanhMuc(TenSanPham) VALUES(?)";
    private final String UPDATE_SQL = "UPDATE DanhMuc SET TenSanPham=? WHERE MaSanPham=?";
    private final String DELETE_SQL = "DELETE FROM DanhMuc WHERE MaSanPham=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM DanhMuc";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM DanhMuc WHERE MaSanPham=?";

    @Override
    public void insert(DanhMuc entity) {
        XJdbc.executeUpdate(INSERT_SQL, entity.getTenSanPham());
    }

    @Override
    public void update(DanhMuc entity) {
        XJdbc.executeUpdate(UPDATE_SQL, entity.getTenSanPham(), entity.getMaSanPham());
    }

    @Override
    public void delete(Integer id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public DanhMuc selectById(Integer id) {
        List<DanhMuc> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    public String getTenLoaiSPById(int id) {
        String selectByIdQuery = "SELECT TenSanPham FROM DanhMuc WHERE MaSanPham=?";
        List<String> result = XJdbc.singleColumnQuery(selectByIdQuery, String.class, id); 
        // Kiểm tra nếu danh sách kết quả không rỗng và có phần tử
        if (!result.isEmpty()) {
            return result.get(0);
        }
        return null; // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
    }
    @Override
     public List<DanhMuc> selectAll() {
       String selectAllQuery = "SELECT * FROM DanhMuc";
            return selectBySQL(selectAllQuery);
}
     public List<DanhMuc> getAll() {
        String sql = "SELECT * FROM DanhMuc";
        return selectBySQL(sql);
    }
    @Override
    protected List<DanhMuc> selectBySQL(String sql, Object... args) {
        List<DanhMuc> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                DanhMuc entity = new DanhMuc(
                        rs.getInt("MaSanPham"),
                        rs.getString("TenSanPham"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
