package DAO;

import Entity.HoaDon;
import Helper.XJdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO extends ManagerDAO<HoaDon, String> {

    private final String INSERT_SQL = "INSERT INTO HoaDon(MaHoaDon, MaNhanVien, TenKhachHang, NgayTao, NgayThanhToan, MaKhuyenMai, TongTien, TrangThai, GhiChu, Ngay) VALUES(?,?,?,?,?,?,?,?,?,?)";
    private final String UPDATE_SQL = "UPDATE HoaDon SET MaNhanVien=?, TenKhachHang=?, NgayTao=?, NgayThanhToan=?, TongTien=?, TrangThai=?, MaKhuyenMai=?, GhiChu=?, Ngay=? WHERE MaHoaDon=?";
    private final String DELETE_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    private final String SELECT_ALL_SQL = "SELECT * FROM HoaDon";
    private final String XOA_HOA_DON_SQL = "DELETE FROM HoaDon WHERE MaHoaDon=?";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM HoaDon WHERE MaHoaDon=?";
    private final String SELECT_CHUA_THANH_TOAN_SQL = "SELECT MaHoaDon, TenNhanVien, TenKhachHang, NgayTao, NgayThanhToan, MaKhuyenMai, TongTien, TrangThai, GhiChu FROM HoaDon WHERE TrangThai = N'Chưa thanh toán'";
    private final String UPDATE_HOADON_SQL = "UPDATE HoaDon SET MaKhuyenMai=?, TrangThai=?, NgayThanhToan=?, Ngay=? WHERE MaHoaDon=?";
    private final String SELECT_BY_NGAY_SQL = "SELECT MaHoaDon, TenNhanVien, TenKhachHang, NgayTao, NgayThanhToan, MaKhuyenMai, TongTien, TrangThai, GhiChu FROM HoaDon WHERE NgayThanhToan BETWEEN ? AND ?";
    private final String GET_TONG_TIEN_HIEN_TAI_SQL = "SELECT (TongTien - ?) as TongTienHienTai FROM HoaDon WHERE MaHoaDon=?";
    private final String UPDATE_TONG_TIEN_SQL = "UPDATE HoaDon SET TongTien=? WHERE MaHoaDon=?";
    private final String LAY_DANH_SACH_HOA_DON_SQL = "SELECT dbo.HoaDon.MaHoaDon, dbo.NhanVien.TenNhanVien, dbo.HoaDon.TenKhachHang, "
            + "dbo.HoaDon.NgayTao, dbo.HoaDon.NgayThanhToan, dbo.HoaDon.MaKhuyenMai, dbo.HoaDon.TongTien, "
            + "dbo.HoaDon.TrangThai, dbo.HoaDon.GhiChu FROM dbo.HoaDon INNER JOIN dbo.NhanVien "
            + "ON dbo.HoaDon.MaNhanVien = dbo.NhanVien.MaNhanVien";

    @Override
    public void insert(HoaDon entity) {
        XJdbc.executeUpdate(INSERT_SQL,
                entity.getMaHoaDon(),
                entity.getMaNhanVien(),
                entity.getTenKhachHang(),
                entity.getNgayTao(),
                entity.getNgayThanhToan(),
                entity.getMaKhuyenMai(),
                entity.getTongTien(),
                entity.getTrangThai(),
                entity.getGhiChu(),
                entity.getNgay());
    }

    @Override
    public void update(HoaDon entity) {
        XJdbc.executeUpdate(UPDATE_SQL,
                entity.getMaNhanVien(),
                entity.getTenKhachHang(),
                entity.getNgayTao(),
                entity.getNgayThanhToan(),
                entity.getTongTien(),
                entity.getTrangThai(),
                entity.getMaKhuyenMai(),
                entity.getGhiChu(),
                entity.getNgay(),
                entity.getMaHoaDon());
    }

    @Override
    public void delete(String id) {
        XJdbc.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public HoaDon selectById(String id) {
        List<HoaDon> list = this.selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectAll() {
        return this.selectBySQL(SELECT_ALL_SQL);
    }

    public List<HoaDon> selectChuaThanhToan() {
        return this.selectBySQL(SELECT_CHUA_THANH_TOAN_SQL);
    }

    public void updateHoaDon(String maHoaDon, String maKhuyenMai, String trangThai, String ngayThanhToan, String ngay) {
        XJdbc.executeUpdate(UPDATE_HOADON_SQL, maKhuyenMai, trangThai, ngayThanhToan, ngay, maHoaDon);
    }

    public void xoaHoaDon(String maHoaDon) {
        XJdbc.executeUpdate(XOA_HOA_DON_SQL, maHoaDon);
    }

    public List<HoaDon> layDuLieuHoaDonDaThanhToan() {
        return this.selectBySQL("SELECT * FROM HoaDon WHERE TrangThai = N'Đã thanh toán'");
    }

    public List<HoaDon> layDuLieuHoaDonDaThanhToanTrongKhoangNgay(String ngay1, String ngay2) {
        return this.selectBySQL(SELECT_BY_NGAY_SQL, ngay1, ngay2);
    }

    public List<HoaDon> layDuLieuHoaDonTrongKhoangNgay(String ngayBatDau, String ngayKetThuc) {
        return this.selectBySQL(SELECT_BY_NGAY_SQL, ngayBatDau, ngayKetThuc);
    }

    public double getTongTienDaThanhToan() throws SQLException {
        String sql = "SELECT sum(TongTien) as TongTien FROM HoaDon WHERE TrangThai = N'Đã thanh toán'";
        ResultSet rs = XJdbc.executeQuery(sql);
        if (rs.next()) {
            return rs.getDouble("TongTien");
        }
        return 0; // Trả về 0 nếu không có dữ liệu
    }

    public int getSoLuongHoaDonDaThanhToan() throws SQLException {
        String sql = "SELECT count(MaHoaDon) as SoLuongHoaDon FROM HoaDon WHERE TrangThai = N'Đã thanh toán'";
        ResultSet rs = XJdbc.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt("SoLuongHoaDon");
        }
        return 0; // Trả về 0 nếu không có dữ liệu
    }

    public int getTongTienHienTai(String maHoaDon, int tienGiam) {
        ResultSet rs = XJdbc.executeQuery(GET_TONG_TIEN_HIEN_TAI_SQL, tienGiam, maHoaDon);
        try {
            if (rs.next()) {
                return rs.getInt("TongTienHienTai");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void updateTongTien(String maHoaDon, BigDecimal tongTienHienTai) {
        XJdbc.executeUpdate(UPDATE_TONG_TIEN_SQL, tongTienHienTai, maHoaDon);
    }

    public List<HoaDon> layDanhSachHoaDon() {
        return this.selectBySQL(LAY_DANH_SACH_HOA_DON_SQL);
    }

    @Override
    protected List<HoaDon> selectBySQL(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.executeQuery(sql, args);
            while (rs.next()) {
                HoaDon entity = new HoaDon(
                        rs.getString("MaHoaDon"),
                        rs.getString("MaNhanVien"),
                        rs.getString("TenKhachHang"),
                        rs.getDate("NgayTao"),
                        rs.getDate("NgayThanhToan"),
                        rs.getString("MaKhuyenMai"),
                        rs.getInt("TongTien"),
                        rs.getString("TrangThai"),
                        rs.getString("GhiChu"),
                        rs.getDate("Ngay"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
