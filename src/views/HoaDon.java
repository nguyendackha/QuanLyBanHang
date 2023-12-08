
package views;

import DAO.ChiTietHoaDonDAO;
import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import Entity.ChiTietHoaDon;
import Entity.DisplayvalueModel;
import Entity.SanPham;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.util.DisplayValue;


/**
 *
 * @author ASUS
 */
public class HoaDon extends javax.swing.JPanel {

public DefaultTableModel dtm = new DefaultTableModel();
public ArrayList<ChiTietHoaDon> listChiTietHoaDon = new ArrayList<>();
public ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
public List<ChiTietHoaDonDAO> listChiTietHoaDonDAO;
public static void main(String[] args) {
        // Tạo một JFrame mới để chứa SanPhamPanel
        JFrame frame = new JFrame("Quản Lý Hóa Đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một đối tượng SanPhamPanel
        HoaDon hoaDon = new HoaDon();

        // Thêm SanPhamPanel vào JFrame
        frame.getContentPane().add(hoaDon);

        // Cấu hình JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Creates new form DatBan
     */
    public HoaDon() {
    initComponents();
    disabled();
    disabled2();
    listChiTietHoaDon = (ArrayList<ChiTietHoaDon>) chiTietHoaDonDAO.selectAll();
}

    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
    int hours = cal.get(Calendar.HOUR);
    int minutes = cal.get(Calendar.MINUTE);
    int second = cal.get(Calendar.SECOND);

   public void LayDuLieuHoaDon() {
    HoaDonDAO hoaDonDAO = new HoaDonDAO();
    List<Entity.HoaDon> hoaDonList = hoaDonDAO.selectChuaThanhToan();

    Object[] obj = new Object[]{"Mã hóa đơn", "Nhân viên", "Khách hàng", "Ngày tạo", "Ngày thanh toán", "Khuyến mãi", "Tổng tiền", "Trạng thái", "Chú thích"};
    DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
    tblHoaDon.setModel(tableModel);

    for (Entity.HoaDon hoaDon : hoaDonList) {
        Object[] item = new Object[9];
        item[0] = hoaDon.getMaHoaDon();
        item[1] = hoaDon.getMaNhanVien(); // Sửa thành getMaNhanVien() hoặc tùy theo cách bạn đã triển khai trong lớp HoaDon
        item[2] = hoaDon.getTenKhachHang();
        item[3] = hoaDon.getNgayTao();
        item[4] = hoaDon.getNgayThanhToan();
        item[5] = hoaDon.getMaKhuyenMai();
        item[6] = hoaDon.getTongTien();
        item[7] = hoaDon.getTrangThai();
        item[8] = hoaDon.getGhiChu();
        tableModel.addRow(item);
    }
}



    private void disabled() {
        cbbTenNV.setEnabled(false);
        txtTenKH.setEnabled(false);
        rdoChuaTT.setEnabled(false);
        rdoDaTT.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void disabled2() {
        cbb_SanPham.setEnabled(false);
        txt_SoLuong.setEnabled(false);
        btn_Xoa.setEnabled(false);
        btn_Luu.setEnabled(false);
        btn_Sua.setEnabled(false);
    }

    private void enabled() {
        cbbTenNV.setEnabled(true);
        txtTenKH.setEnabled(true);
        rdoChuaTT.setEnabled(true);
        rdoDaTT.setEnabled(true);
        btnLuu.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void enabled2() {
        cbb_SanPham.setEnabled(true);
        txt_SoLuong.setEnabled(true);
        btn_Xoa.setEnabled(true);
        btn_Luu.setEnabled(true);
        btn_Sua.setEnabled(true);
    }

    private void clear_hoadon() {
        txtMaHoaDon.setText("");
        txtNgayTao.setText("");
        txtTenKH.setText("");
        txtTongTien.setText("");
        cbbTenNV.setSelectedIndex(0);
        buttonGroup1.clearSelection();
    }

    public void clear_chitiethoadon() {
        txt_MaCTHD.setText("");

        cbb_SanPham.setSelectedIndex(0);
        txt_SoLuong.setText("");
        txt_ThanhTien.setText("");
    }
    private List<DisplayValue> layDuLieuTuSourceKhac() {
        List<DisplayValue> data = new ArrayList<>();
        // Thêm các DisplayValue vào danh sách
        return data;
    }


   public void LayDuLieuChiTietHoaDon(String MaHoaDon) {
    // Sử dụng ChiTietHoaDonDAO để lấy dữ liệu từ cơ sở dữ liệu
    String maHoaDon = MaHoaDon; // giữ nguyên kiểu String nếu MaHoaDon đã là String
    List<ChiTietHoaDon> chiTietHoaDonList = chiTietHoaDonDAO.selectByMaHoaDon(maHoaDon);
  

    Object[] obj = new Object[]{"Mã CTHD", "Mã HD", "Sản Phẩm", "Số Lượng", "Thành tiền"};
    DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
    tblDatHang.setModel(tableModel);

    for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonList) {
        Object[] item = new Object[5];
        item[0] = chiTietHoaDon.getMaCTHoaDon();
        item[1] = chiTietHoaDon.getMaHoaDon();
        
        // Thay thế getSanPham().getTenSanPham() bằng getSanPham() để lấy thông tin từ SanPhamDAO
        item[2] = chiTietHoaDon.getMaSanPham();

        item[3] = chiTietHoaDon.getSoLuong();
        item[4] = chiTietHoaDon.getThanhTien();
        tableModel.addRow(item);
    }
}


 public DefaultComboBoxModel layDuLieuCBB(String tenSanPham, String maSanPham, String maSanPham1) {
        DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();

        // Giả định có một danh sách DisplayValue
        List<DisplayValue> data = layDuLieuTuSourceKhac();

        for (DisplayValue displayValue : data) {
            cbbModel.addElement(displayValue);
        }
        return cbbModel;
    }
 

 public DefaultComboBoxModel layDuLieuCBB2(String tenSanPham) {
        DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();

        // Gọi phương thức layDuLieuTuSourceKhac từ cùng class HoaDon
        List<DisplayValue> data = layDuLieuTuSourceKhac();

        for (DisplayValue displayValue : data) {
            cbbModel.addElement(displayValue);
        }

        return cbbModel;
    }



    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(this, noiDungThongBao,
                tieuDeThongBao, icon);
    }

    public boolean KiemTraNhapHoaDon(int ts) {
    String MaHoaDon, MaNhanVien, TenKhachHang, NgayTao, TongTien, TrangThai, ThongBao = "";
    boolean kiemtra = false;

    MaHoaDon = txtMaHoaDon.getText();
    MaNhanVien = cbbTenNV.getSelectedItem().toString(); // Lấy giá trị từ cbbTenNV
    TenKhachHang = txtTenKH.getText();
    NgayTao = txtNgayTao.getText();
    TongTien = txtTongTien.getText();
    
    if (rdoDaTT.isSelected()) {
        TrangThai = "Đã thanh toán";
    } else {
        TrangThai = "Chưa thanh toán";
    }

    if (MaHoaDon.equals("") && ts == 1) {
        ThongBao += "Bạn chưa chọn Hóa Đơn để lấy Mã Hóa Đơn\n";
    }
    if (MaNhanVien.equals("")) {
        ThongBao += "Bạn chưa chọn Nhân Viên\n";
    }
    if (TenKhachHang.equals("")) {
        ThongBao += "Bạn chưa nhập Tên Khách Hàng\n";
    }
    if (NgayTao.equals("")) {
        ThongBao += "Bạn chưa nhập Ngày Lập\n";
    }
    if (TongTien.equals("")) {
        txtTongTien.setText("0");
    }

    if (ThongBao.equals("")) {
        kiemtra = true;
    } else {
        kiemtra = false;
        ThongBao(ThongBao, "Lỗi nhập liệu", 2);
    }

    return kiemtra;
}


   public boolean KiemTraNhapChiTietHoaDon(int ts) {
        String MaHoaDon, MaChiTietHoaDon, SanPham, SoLuong, ThongBao = "";
        boolean kiemtra = false;

        MaHoaDon = txt_MaHD.getText();
        MaChiTietHoaDon = txt_MaCTHD.getText();
        SanPham = cbb_SanPham.getSelectedItem().toString(); // Lấy giá trị được chọn từ cbb_SanPham
        SoLuong = txt_SoLuong.getText();
        if (MaChiTietHoaDon.equals("") && ts == 1) {
            ThongBao += "bạn chưa chọn Hóa Đơn để lấy  Mã Hóa Dơn\n";
        }
        if (MaHoaDon.equals("")) {
            ThongBao += "bạn chưa chọn trong hóa đơn nào cả\n";
        }
        if (SoLuong.equals("")) {
            ThongBao += "bạn chưa Nhập Số Lượng";
        }
        try {
            int bien = Integer.valueOf(SoLuong);
        } catch (Exception e) {
            ThongBao += "Số lượng phải nhập Bằng số";
        }
        if (ThongBao.equals("")) {
            kiemtra = true;
        } else {
            kiemtra = false;
            ThongBao(ThongBao, "lỗi nhập liệu", 2);
        }
        return kiemtra;
    }

public void setTongTien(String maHoaDon, String par) {
    HoaDonDAO hoaDonDAO = new HoaDonDAO();

    // Lấy giá trị kiểu BigDecimal từ phương thức getTongTienHienTai
    BigDecimal tongTienHienTai = new BigDecimal(hoaDonDAO.getTongTienHienTai(maHoaDon, 0));

    // Kiểm tra nếu tổng tiền hiện tại lớn hơn 0
    if (tongTienHienTai.compareTo(BigDecimal.ZERO) > 0) {
        // Hiển thị giá trị trong JTextField
        txt_ThanhTien.setText(tongTienHienTai.toString());

        // Cập nhật tổng tiền trong bảng HoaDon
        hoaDonDAO.updateTongTien(maHoaDon, tongTienHienTai);

        // Gọi phương thức LayDuLieuHoaDon(); ở đây nếu cần
        // LayDuLieuHoaDon();
    }
}
  public String getGiaSanPham(String maSanPham) {
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    String gia = ""; // Giá trị mặc định nếu không tìm thấy sản phẩm
    try {
        // Mã sản phẩm là chuỗi, không phải số nguyên
        SanPham sanPham = sanPhamDAO.selectById(maSanPham);

        // Kiểm tra xem sản phẩm có tồn tại không
        if (sanPham != null) {
            gia = String.valueOf(sanPham.getGiaTien());
        }
    } catch (NumberFormatException e) {
        // Xử lý nếu mã sản phẩm không phải là số nguyên
        e.printStackTrace();
    }
    return gia;
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lbThongtin3 = new javax.swing.JLabel();
        jScollpanel = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        rdoChuaTT = new javax.swing.JRadioButton();
        rdoDaTT = new javax.swing.JRadioButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Them = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        lbThongtin4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatHang = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        cbbTenNV = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_ThanhTien = new javax.swing.JTextField();
        txt_MaCTHD = new javax.swing.JTextField();
        txt_MaHD = new javax.swing.JTextField();
        txt_SoLuong = new javax.swing.JTextField();
        cbb_SanPham = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentShown(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbThongtin3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin3.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin3.setText("Đặt hàng");
        lbThongtin3.setToolTipText("");
        jPanel1.add(lbThongtin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 620, 50));

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Tên nhân viên", "Tên khách hàng", "Ngày tạo", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScollpanel.setViewportView(tblHoaDon);

        jPanel1.add(jScollpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 790, 250));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Tên Nhân viên");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Tên khách hàng");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(49, 139, 130));
        jLabel5.setText("Tổng tiền");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 160, -1, -1));

        txtTongTien.setEditable(false);
        jPanel1.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 180, -1));

        txtMaHoaDon.setEditable(false);
        jPanel1.add(txtMaHoaDon, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 180, -1));
        jPanel1.add(txtTenKH, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 180, -1));

        buttonGroup1.add(rdoChuaTT);
        rdoChuaTT.setText("Chưa thanh toán");
        jPanel1.add(rdoChuaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, -1, -1));

        buttonGroup1.add(rdoDaTT);
        rdoDaTT.setText("Đã thanh toán");
        jPanel1.add(rdoDaTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, -1));

        btn_Xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(49, 139, 130));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 550, 100, -1));

        btn_Them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(49, 139, 130));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 430, -1, -1));

        btn_Luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Luu.setForeground(new java.awt.Color(49, 139, 130));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Luu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 470, 100, -1));

        btn_Sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(49, 139, 130));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 510, 100, -1));

        lbThongtin4.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin4.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin4.setText("Hóa đơn");
        lbThongtin4.setToolTipText("");
        jPanel1.add(lbThongtin4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 620, 50));

        tblDatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã CTHD", "Mã HD", "Tên sản phẩm", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDatHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDatHang);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 790, 230));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(49, 139, 130));
        jLabel7.setText("Trạng thái");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, -1, -1));

        txtNgayTao.setEditable(false);
        jPanel1.add(txtNgayTao, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 260, 180, -1));

        jPanel1.add(cbbTenNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 180, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(49, 139, 130));
        jLabel9.setText("Mã CTHD");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(49, 139, 130));
        jLabel10.setText("Sản phẩm");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 500, -1, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(49, 139, 130));
        jLabel11.setText("Số lượng");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 530, -1, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(49, 139, 130));
        jLabel12.setText("Thành tiền");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 560, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(49, 139, 130));
        jLabel13.setText("Mã hóa đơn");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 460, -1, -1));

        txt_ThanhTien.setEditable(false);
        jPanel1.add(txt_ThanhTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 560, 190, -1));

        txt_MaCTHD.setEditable(false);
        jPanel1.add(txt_MaCTHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 430, 190, -1));

        txt_MaHD.setEditable(false);
        jPanel1.add(txt_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 460, 190, -1));

        txt_SoLuong.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_SoLuongKeyReleased(evt);
            }
        });
        jPanel1.add(txt_SoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 530, 190, -1));

        jPanel1.add(cbb_SanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 190, -1));

        btnThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThem.setForeground(new java.awt.Color(49, 139, 130));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel1.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 70, -1, -1));

        btnLuu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(49, 139, 130));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel1.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 110, 100, -1));

        btnSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnSua.setForeground(new java.awt.Color(49, 139, 130));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });
        jPanel1.add(btnSua, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 150, 100, -1));

        btnXoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(49, 139, 130));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel1.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 190, 100, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Chú thích");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 310, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(49, 139, 130));
        jLabel14.setText("Mã hóa đơn");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 139, 130));
        jLabel6.setText("Ngày tạo");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 260, -1, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 300, -1, -1));

        jTabbedPane1.addTab("Hóa Đơn", jPanel1);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 680));
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentShown

    }//GEN-LAST:event_jPanel1ComponentShown

    private void tblDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDatHangMouseClicked
        int row = tblDatHang.getSelectedRow();
        txt_MaCTHD.setText(tblDatHang.getValueAt(row, 0).toString());
        txt_MaHD.setText(tblDatHang.getValueAt(row, 1).toString());
        cbb_SanPham.setSelectedItem(tblDatHang.getValueAt(row, 2).toString());
        txt_SoLuong.setText(tblDatHang.getValueAt(row, 3).toString());
        txt_ThanhTien.setText(tblDatHang.getValueAt(row, 4).toString());
        LayDuLieuChiTietHoaDon(txtMaHoaDon.getText());
    }//GEN-LAST:event_tblDatHangMouseClicked

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
                int row = tblHoaDon.getSelectedRow();
        txtMaHoaDon.setText(tblHoaDon.getValueAt(row, 0).toString());
        cbbTenNV.setSelectedItem(tblHoaDon.getValueAt(row, 1).toString());
        txtTenKH.setText(tblHoaDon.getValueAt(row, 2).toString());
        txtNgayTao.setText(tblHoaDon.getValueAt(row, 3).toString());
        txtTongTien.setText(tblHoaDon.getValueAt(row, 6).toString());
        String tt = tblHoaDon.getValueAt(row, 7).toString();
        jTextArea1.setText(tblHoaDon.getValueAt(row, 8).toString());

        if (tt.equals("Chưa thanh toán")) {
            rdoChuaTT.setSelected(true);
            rdoDaTT.setSelected(false);
        } else {
            rdoDaTT.setSelected(true);
            rdoChuaTT.setSelected(false);
        }

        LayDuLieuChiTietHoaDon(txtMaHoaDon.getText());

        if (tblDatHang.getRowCount() > 0) {
            cbb_SanPham.setModel(layDuLieuCBB2("tenSanPham"));
            txt_MaCTHD.setText(tblDatHang.getValueAt(0, 0).toString());
            txt_MaHD.setText(tblDatHang.getValueAt(0, 1).toString());
            cbb_SanPham.setSelectedItem(tblDatHang.getValueAt(0, 2).toString());
            txt_SoLuong.setText(tblDatHang.getValueAt(0, 3).toString());
            txt_ThanhTien.setText(tblDatHang.getValueAt(0, 4).toString());
        } else {
            clear_chitiethoadon();
            txt_MaHD.setText(txtMaHoaDon.getText());
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        clear_hoadon();
        enabled();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
    String maCTHoaDon, maHoaDon, maSanPham, soLuong, thanhTien;
    maCTHoaDon = txt_MaCTHD.getText();
    maHoaDon = txt_MaHD.getText();
    maSanPham = cbb_SanPham.getSelectedItem().toString(); // Lấy giá trị từ cbb_SanPham
    soLuong = txt_SoLuong.getText();
    thanhTien = txt_ThanhTien.getText();

   if (checkValidateForm()) {
    ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();

    // Kiểm tra xem sản phẩm đã có trong hóa đơn chưa
    ChiTietHoaDon existingChiTiet = chiTietHoaDonDAO.selectById(maCTHoaDon);
    if (existingChiTiet != null) {
        JOptionPane.showMessageDialog(this, "Đã có sản phẩm trong hóa đơn");
    } else {
        // Tạo đối tượng ChiTietHoaDon mới và set giá trị
        ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCTHoaDon, maHoaDon, maSanPham, Integer.parseInt(soLuong), Integer.parseInt(thanhTien));

        // Thực hiện thêm ChiTietHoaDon
        chiTietHoaDonDAO.insert(chiTietHoaDon);
        
        // Hiển thị thông báo sau khi thêm thành công
        JOptionPane.showMessageDialog(this, "Đã Lưu thành công");

        // Cập nhật lại dữ liệu hiển thị
        LayDuLieuHoaDon();
    }
}


    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        String MaHoaDon, TenKH, TongTien, tt, ct;
        MaHoaDon = txtMaHoaDon.getText();
        TenKH = txtTenKH.getText();
        TongTien = txtTongTien.getText();
        ct = jTextArea1.getText();

        if (rdoDaTT.isSelected()) {
            tt = "Đã thanh toán";
        } else {
            tt = "Chưa thanh toán";
        }

            HoaDonDAO hoaDonDAO = new HoaDonDAO(); // Khởi tạo đối tượng DAO
         Entity.HoaDon hoaDonToUpdate = hoaDonDAO.selectById(MaHoaDon); // Lấy thông tin hóa đơn cần cập nhật

    if (hoaDonToUpdate != null) {
        // Cập nhật thông tin hóa đơn
        hoaDonToUpdate.setTenKhachHang(TenKH);
        hoaDonToUpdate.setTongTien(Integer.parseInt(TongTien));
        hoaDonToUpdate.setTrangThai(tt.equals("Đã thanh toán")); // Sửa ở đây
        hoaDonToUpdate.setGhiChu(ct);

        // Thực hiện cập nhật hóa đơn
        hoaDonDAO.update(hoaDonToUpdate);
        JOptionPane.showMessageDialog(this, "Đã sửa Thành Công");
        LayDuLieuHoaDon(); // Nếu cần, cập nhật giao diện hiển thị sau khi cập nhật dữ liệu
    } else {
        ThongBao("Không thể ", "Sửa Hóa Đơn", 2);
    }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
             // Trong class gọi phương thức xóa
        if (!txtMaHoaDon.getText().equals("")) {
            String maHoaDon = txtMaHoaDon.getText();

            // Tạo một đối tượng của lớp HoaDonDAO
            HoaDonDAO hoaDonDAO = new HoaDonDAO();

            // Gọi phương thức xoaHoaDon trên đối tượng vừa tạo
            hoaDonDAO.xoaHoaDon(maHoaDon);

            JOptionPane.showMessageDialog(this, "Đã xóa thành công");
            LayDuLieuHoaDon();
        } else {
            ThongBao("Bạn chưa chọn hóa đơn để xóa", "Xóa bằng niềm tin à! Khi không biết xóa cái nào", 2);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        clear_chitiethoadon();
        enabled2();
    }//GEN-LAST:event_btn_ThemActionPerformed
   public boolean checkValidateForm() {
    return !(
        txt_MaCTHD.getText().isEmpty() ||
        txt_MaHD.getText().isEmpty() ||
        cbb_SanPham.getSelectedItem() == null ||
        txt_SoLuong.getText().isEmpty() ||
        txt_ThanhTien.getText().isEmpty()
    );
}

public String checkLogin(String TenSanPham) {
    ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();

    for (ChiTietHoaDon chiTietHoaDon : chiTietHoaDonDAO.selectAll()) {
        String maSanPham = chiTietHoaDon.getMaSanPham();
        if (maSanPham.equals(TenSanPham)) {
            return maSanPham;
        }
    }
    return null;
}


    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
    String maCTHoaDon, maHoaDon, maSanPham;
    int soLuong, tongTien;
    maCTHoaDon = txt_MaCTHD.getText();
    maHoaDon = txt_MaHD.getText();
    maSanPham = (String) cbb_SanPham.getSelectedItem(); // Thay thế GetCbbSelected
    soLuong = Integer.parseInt(txt_SoLuong.getText()); // Chuyển đổi thành số nguyên
    tongTien = Integer.parseInt(txt_ThanhTien.getText()); // Chuyển đổi thành số nguyên

    if (checkValidateForm()) {
        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();

        // Kiểm tra xem sản phẩm đã có trong hóa đơn chưa
        String role = checkLogin(maSanPham);
        if (role != null) {
            JOptionPane.showMessageDialog(this, "Sản phẩm đã có trong hóa đơn");
        } else {
            // Tạo đối tượng ChiTietHoaDon mới và set giá trị
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon(maCTHoaDon, maHoaDon, maSanPham, soLuong, tongTien);

            // Thực hiện thêm ChiTietHoaDon
            chiTietHoaDonDAO.insert(chiTietHoaDon);

            // Hiển thị thông báo sau khi thêm thành công
            JOptionPane.showMessageDialog(this, "Đã Lưu thành công");
        }
    }

    // Cập nhật lại dữ liệu hiển thị và tổng tiền
    LayDuLieuChiTietHoaDon(maHoaDon);
    setTongTien(maHoaDon, null); // Hoặc bạn có thể truyền giá trị mặc định nếu cần thiết


    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
        String MaHoaDon, MaChiTietHoaDon, SanPham, SoLuong, TongTien;
        MaChiTietHoaDon = txt_MaCTHD.getText();
        MaHoaDon = txt_MaHD.getText();
        SanPham = (String) cbb_SanPham.getSelectedItem(); // Thay thế GetCbbSelected
        SoLuong = txt_SoLuong.getText();
        TongTien = txt_ThanhTien.getText();
        String cautruyvan = "update  ChiTietHoaDon set MaHoaDon=" + MaHoaDon + ",MaSanPham=" + SanPham + ",SoLuong=" + SoLuong + ",ThanhTien="
                + TongTien + " where MaCTHoaDon=" + MaChiTietHoaDon;
        boolean kiemtra = KiemTraNhapChiTietHoaDon(1);
        if (kiemtra && !MaChiTietHoaDon.equals("")) {
            // Bỏ phần thực hiện câu truy vấn
            // NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
            JOptionPane.showMessageDialog(this, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
        }
         LayDuLieuChiTietHoaDon(MaHoaDon);
          setTongTien(MaHoaDon, null); // Hoặc bạn có thể truyền giá trị mặc định nếu cần thiết

    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
          String MaHoaDon = txt_MaHD.getText();
        String MaChiTietHoaDon = txt_MaCTHD.getText();
        String cautruyvan = "delete ChiTietHoaDon where MaCTHoaDon=" + MaChiTietHoaDon;
        // Bỏ phần thực hiện câu truy vấn
        // application.NewClass.connection.ExcuteQueryUpdateDB(cautruyvan);
        JOptionPane.showMessageDialog(this, "Xóa thành công");
        // Bỏ tham số không cần thiết nếu SetTongTien không sử dụng cbbKM
         LayDuLieuChiTietHoaDon(MaHoaDon);
          setTongTien(MaHoaDon, null); // Hoặc bạn có thể truyền giá trị mặc định nếu cần thiết
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void txt_SoLuongKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SoLuongKeyReleased
         int SoLuong = 0;
    int Tien = 0;  // Chuyển Tien từ double sang int
    try {
        SoLuong = Integer.parseInt(txt_SoLuong.getText());
    } catch (NumberFormatException e) {
        e.printStackTrace();
    }

    // Bỏ GetGiaSanPham và GetCbbSelected
    // Giả sử GiaSanPham và MaSanPham là hai biến đã được định nghĩa trước đó
    String MaSanPham = "GiaTriCuaMaSanPham";
        String GiaSanPham = getGiaSanPham(MaSanPham);

    txt_ThanhTien.setText(String.valueOf(Tien));


    }//GEN-LAST:event_txt_SoLuongKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbTenNV;
    private javax.swing.JComboBox<String> cbb_SanPham;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScollpanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbThongtin3;
    private javax.swing.JLabel lbThongtin4;
    private javax.swing.JRadioButton rdoChuaTT;
    private javax.swing.JRadioButton rdoDaTT;
    private javax.swing.JTable tblDatHang;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTongTien;
    private javax.swing.JTextField txt_MaCTHD;
    private javax.swing.JTextField txt_MaHD;
    private javax.swing.JTextField txt_SoLuong;
    private javax.swing.JTextField txt_ThanhTien;
    // End of variables declaration//GEN-END:variables
}
