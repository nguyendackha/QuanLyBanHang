/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import DAO.HoaDonDAO;
import DAO.SanPhamDAO;
import DAO.ThongKeDAO;
import Entity.SanPham;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author ASUS
 */
public final class ThongKe extends javax.swing.JPanel {

     private DefaultTableModel dtm = new DefaultTableModel();
    private ArrayList<ThongKe> listThongKe = new ArrayList<>(); 
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private ThongKeDAO thongKeDAO = new ThongKeDAO();
    public static void main(String[] args) {
        // Tạo một JFrame mới để chứa QLNV
        JFrame frame = new JFrame("Quản Lý Thống Kê");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một đối tượng QLNV
        ThongKe thongKe = new ThongKe();

        // Thêm QLNV vào JFrame
        frame.getContentPane().add(thongKe);

        // Cấu hình JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /**
     * Creates new form DoanhThu
     */
 public ThongKe() {
    initComponents();

    thongKeDAO = new ThongKeDAO(); // Khởi tạo đối tượng ThongKeDAO
    sanPhamDAO = new SanPhamDAO(); // Khởi tạo đối tượng SanPhamDAO

    // Lấy danh sách ThongKe và SanPham từ cơ sở dữ liệu
    List<Entity.ThongKe> listThongKe = thongKeDAO.selectAll();
    List<SanPham> listSanPham = sanPhamDAO.selectAll();

    // Nạp dữ liệu vào ComboBox
    loadCBB(listSanPham);

    // Tắt khả năng chỉnh sửa của TextBox txtTenSP
    txtTenSP.setEnabled(false);

    // Hiển thị thông tin khác cần thiết

    ShowHoaDon();
    top5();
}

// Sửa đổi phương thức loadCBB để chấp nhận danh sách SanPham
private void loadCBB(List<SanPham> list) {
    // Đưa dữ liệu vào ComboBox ở đây
    // ...
}



        public void top5() {
    // Thay thế NewClass.connection bằng SanPhamDAO
    SanPhamDAO sanPhamDAO = new SanPhamDAO();

    // Sử dụng SanPhamDAO để lấy top 5 sản phẩm bán chạy
    List<Object[]> top5List = sanPhamDAO.getTop5SoldSanPham();

    // Tạo DefaultTableModel và đặt cho bảng tbl_Top5
    Object[] obj = new Object[]{"Tên sản phẩm", "Số lượng bán được"};
    DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
    tbl_Top5.setModel(tableModel);

    // Đổ dữ liệu từ top5List vào DefaultTableModel
    for (Object[] item : top5List) {
        tableModel.addRow(item);
    }
}


   private void loadCBB(ArrayList<SanPham> listSanPham) {
    boxModel = (DefaultComboBoxModel) cbbMaSP.getModel();
    for (SanPham sanPham : listSanPham) {
        cbbMaSP.addItem(sanPham.getMaSanPham() + "");
    }
}


   public void traCuu(String MaSP) {
       ThongKeDAO thongKeDAO = new ThongKeDAO(); // Khởi tạo đối tượng ThongKeDAO
       // Thực hiện truy vấn bằng cách sử dụng ThongKeDAO
       List<Entity.ThongKe> listThongKe = thongKeDAO.traCuuByMaSP(MaSP);
       Object[] obj = new Object[]{"Mã thống kê", "Ngày thống kê", "Số lượng sản phẩm", "Doanh thu"};
       DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
       tbldoanhThuMon.setModel(tableModel);
       // Nạp dữ liệu vào bảng từ danh sách ThongKe
       for (Entity.ThongKe thongKe : listThongKe) {
           Object[] item = new Object[4];
           item[0] = thongKe.getMaThongKe();
           item[1] = thongKe.getNgayThongKe();
           item[2] = thongKe.getSoLuongSanPham();
           item[3] = thongKe.getDoanhThu();
           tableModel.addRow(item);
       }
}



   public void showTongTien() throws SQLException {
       HoaDonDAO hoaDonDAO = new HoaDonDAO(); // Khởi tạo đối tượng HoaDonDAO
       // Thực hiện truy vấn bằng cách sử dụng HoaDonDAO
       double tongTien = hoaDonDAO.getTongTienDaThanhToan();
       // Hiển thị tổng tiền
       lbTongTien.setText(String.valueOf(tongTien));
}


   public void ShowHoaDon() {
    try {
        HoaDonDAO hoaDonDAO = new HoaDonDAO(); // Khởi tạo đối tượng HoaDonDAO

        // Thực hiện truy vấn bằng cách sử dụng HoaDonDAO
        int soLuongHoaDon = hoaDonDAO.getSoLuongHoaDonDaThanhToan();

        // Hiển thị số lượng hóa đơn đã thanh toán
        lbSoHD.setText(String.valueOf(soLuongHoaDon));
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cbbMaSP = new javax.swing.JComboBox<>();
        btnTraCuu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbSoHD = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbldoanhThuMon = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        JscrollPane = new javax.swing.JScrollPane();
        tbl_Top5 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jpnView2 = new javax.swing.JPanel();
        jpnview3 = new javax.swing.JPanel();

        jLabel1.setText("jLabel1");

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Chọn tên Sản Phẩm");

        cbbMaSP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cbbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaSPActionPerformed(evt);
            }
        });

        btnTraCuu.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnTraCuu.setForeground(new java.awt.Color(49, 139, 130));
        btnTraCuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_28px.png"))); // NOI18N
        btnTraCuu.setText("Tra cứu");
        btnTraCuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTraCuuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Tổng số hóa đơn(Chiếc)");

        lbSoHD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbSoHD.setText("0 Chiếc");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(49, 139, 130));
        jLabel6.setText("Tổng tiền hóa đơn(NVĐ)");

        lbTongTien.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTongTien.setText("0 VNĐ");

        txtTenSP.setEditable(false);
        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbMaSP, 0, 273, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtTenSP)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(btnTraCuu)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTraCuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbSoHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lbTongTien))
                .addGap(62, 62, 62))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tbldoanhThuMon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Tên sản phẩm", "Số lượng", "Tổng tiền", "Ngày bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbldoanhThuMon);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/DTfood.png"))); // NOI18N
        jLabel2.setText("Doanh Thu theo Sản Phẩm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(281, 281, 281)
                .addComponent(jLabel2)
                .addContainerGap(658, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel2);

        JscrollPane.setEnabled(false);

        tbl_Top5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JscrollPane.setViewportView(tbl_Top5);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 1439, Short.MAX_VALUE)
            .addComponent(JscrollPane)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JscrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Top 5 sản phẩm bán chạy", jPanel11);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(49, 139, 130));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Chart.png"))); // NOI18N
        jLabel8.setText("BIỂU ĐỒ SỐ LƯỢNG SẢN PHẨM BÁN ĐƯỢC");

        javax.swing.GroupLayout jpnView2Layout = new javax.swing.GroupLayout(jpnView2);
        jpnView2.setLayout(jpnView2Layout);
        jpnView2Layout.setHorizontalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnView2Layout.setVerticalGroup(
            jpnView2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 264, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnview3Layout = new javax.swing.GroupLayout(jpnview3);
        jpnview3.setLayout(jpnview3Layout);
        jpnview3Layout.setHorizontalGroup(
            jpnview3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpnview3Layout.setVerticalGroup(
            jpnview3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 351, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jpnView2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jLabel8)
                        .addGap(0, 464, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jpnview3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnview3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel10);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTraCuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTraCuuActionPerformed
        traCuu(cbbMaSP.getSelectedItem().toString());
    }//GEN-LAST:event_btnTraCuuActionPerformed

    private void cbbMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaSPActionPerformed
      String sql = "SELECT TenSanPham FROM SanPham WHERE MaSanPham = ?";
try {
    String maSanPhamString = (String) cbbMaSP.getSelectedItem();
    
    // Khởi tạo đối tượng SanPhamDAO
    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    
    // Sử dụng phương thức selectById để lấy thông tin sản phẩm dựa trên mã sản phẩm
    SanPham sanPham = sanPhamDAO.selectById(Integer.parseInt(maSanPhamString));
    
    if (sanPham != null) {
        txtTenSP.setText(sanPham.getTenSanPham());
    }
} catch (Exception e) {
    e.printStackTrace();
}

    }//GEN-LAST:event_cbbMaSPActionPerformed

    private void txtTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JscrollPane;
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnView2;
    private javax.swing.JPanel jpnview3;
    private javax.swing.JLabel lbSoHD;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JTable tbl_Top5;
    private javax.swing.JTable tbldoanhThuMon;
    private javax.swing.JTextField txtTenSP;
    // End of variables declaration//GEN-END:variables
}
