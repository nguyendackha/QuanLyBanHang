/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package views;

import DAO.ChucVuDAO;
import DAO.NhanVienDAO;
import Entity.ChucVu;
import Entity.NhanVien;
import java.util.Date;
import java.awt.Image;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import com.toedter.calendar.JDateChooser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author ASUS
 */
public class QLNV extends javax.swing.JPanel {
private DefaultTableModel dtm = new DefaultTableModel();
private DefaultTableModel dtm2 = new DefaultTableModel();
private DefaultTableModel dtm3 = new DefaultTableModel();
private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
private List<NhanVien> listNhanVien = new ArrayList<>();
private List<ChucVu> listChucVu = new ArrayList<>();
private List<ChucVu> list = new ArrayList<>();
private NhanVienDAO nhanVienDAO = new NhanVienDAO();
private ChucVuDAO chucVuDAO = new ChucVuDAO();
private JDateChooser txtNgaySinh;
private String strHinhAnh = null;

    /**
     * Creates new form NewJPanel
     */
public QLNV() {
       initComponents();    
       JDateChooser dateChooser = new JDateChooser();
       Date selectedDate = dateChooser.getDate();
        // Lấy danh sách NhanVien và ChucVu từ CSDL
        listNhanVien = nhanVienDAO.selectAll();
        listChucVu = chucVuDAO.selectAll();
        showData((ArrayList<NhanVien>) listNhanVien);
        showData2((ArrayList<ChucVu>) listChucVu);
        loadCBB((ArrayList<ChucVu>) listChucVu);
        txtTenChucVu.setEnabled(false);
        disabled();
        disabled2();
    }
    private void clear() {
        txtMaNV.setText("");
        txtTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        cbbMaCV.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        lbImages.setIcon(null);
        txtNgaySinh.setDate(null);

    }

    private void clear2() {
        txt_Luong.setText("");
        txt_MaCV.setText("");
        txt_TenCV.setText("");

    }

    private void disabled() {
        txtTen.setEnabled(false);
        txtMaNV.setEnabled(false);
        txtSDT.setEnabled(false);
        txtDiaChi.setEnabled(false);
        cbbMaCV.setEnabled(false);
        rdoNam.setEnabled(false);
        rdoNu.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);

    }

    private void enabled() {
        txtTen.setEnabled(true);
        txtMaNV.setEnabled(true);
        txtSDT.setEnabled(true);
        txtDiaChi.setEnabled(true);
        cbbMaCV.setEnabled(true);
        rdoNam.setEnabled(true);
        rdoNu.setEnabled(true);
        btnLuu.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void enabled2() {
        btn_Luu.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
        txt_Luong.setEnabled(true);
        txt_TenCV.setEnabled(true);
        txt_MaCV.setEnabled(true);
    }

    private void disabled2() {
        btn_Luu.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
        txt_Luong.setEnabled(false);
        txt_TenCV.setEnabled(false);
        txt_MaCV.setEnabled(false);
    }

    private void loadCBB(ArrayList<ChucVu> list) {
    boxModel = (DefaultComboBoxModel) cbbMaCV.getModel();
    for (ChucVu c : list) {
        cbbMaCV.addItem(c.getMaChucVu() + "");
    }
}


    private void showData(ArrayList<NhanVien> listNhanVien) {
    dtm = (DefaultTableModel) tblNhanVien.getModel();
    dtm.setRowCount(0);
    for (NhanVien n : listNhanVien) {
        dtm.addRow(n.toDataRow());
    }
}


 public void showData2(List<ChucVu> dataList) {
    dtm2 = (DefaultTableModel) tbl_ChucVu.getModel();
    dtm2.setRowCount(0);
    for (ChucVu c : dataList) {
        dtm2.addRow(c.toDataRow1());
    }
}



    private void showData3(ArrayList<NhanVien> listNhanVien) {
    dtm3 = (DefaultTableModel) tbl_NhanVien.getModel();
    dtm3.setRowCount(0);

    for (NhanVien n : listNhanVien) {
        dtm3.addRow(n.toDataRow());
    }
}

  private void fillData(int index) {
    NhanVien n = listNhanVien.get(index);

    // Convert String to Date
    try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date ngaySinhDate = dateFormat.parse(n.getNgaySinh());
        txtNgaySinh.setDate(ngaySinhDate);
    } catch (ParseException ex) {
        ex.printStackTrace(); // Xử lý ngoại lệ theo ý 
    }

    if (n.getGioiTinh().equals("Nam")) {
        rdoNam.setSelected(true);
    } else {
        rdoNu.setSelected(true);
    }
    txtTen.setText(n.getTenNhanVien());
    txtMaNV.setText(n.getMaNhanVien());
    txtSDT.setText(n.getSDT());
    txtDiaChi.setText(n.getDiaChi());
    cbbMaCV.setSelectedItem(n.getMaChucVu());
    UpdateHinh(n.getImageNV());
}



   private void fillData2(int index) {
    ChucVu c = listChucVu.get(index); 
    txt_MaCV.setText(c.getMaChucVu() + "");
    txt_TenCV.setText(c.getTenChucVu());
    txt_Luong.setText(c.getLuong() + "");
}


   private void fillData3(int index) {
    NhanVien n = listNhanVien.get(index); 
    txt_MaNV.setText(n.getMaNhanVien());
}

    public void UpdateHinh(String image) {
        ImageIcon img = new ImageIcon(getClass().getResource("/HinhAnh/" + image));
        Image anh = img.getImage();
        ImageIcon icon = new ImageIcon(anh.getScaledInstance(lbImages.getWidth(),
                lbImages.getHeight(), anh.SCALE_SMOOTH));
        lbImages.setIcon(icon);
    }

   private NhanVien getData() {
    NhanVien n = new NhanVien();
    if (txtMaNV.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
        return null;
    }
    n.setMaNhanVien(txtMaNV.getText());
    if (txtTen.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Tên nhân viên không được để trống");
        return null;
    }
    n.setTenNhanVien(txtTen.getText());
    if (txtDiaChi.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Địa chỉ nhân viên không được để trống");
        return null;
    }
    n.setDiaChi(txtDiaChi.getText());
    if (txtSDT.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "SĐT nhân viên không được để trống");
        return null;
    }
    n.setSDT(txtSDT.getText());
    n.setMaChucVu((int) cbbMaCV.getSelectedItem());
    
    // Kiểm tra ngày sinh
     Date ngaySinhDate = txtNgaySinh.getDate();
    if (ngaySinhDate == null) {
    JOptionPane.showMessageDialog(this, "Ngày sinh nhân viên không được để trống");
    return null;
}
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày theo mong muốn
    String ngaySinh = dateFormat.format(ngaySinhDate);
    if (ngaySinh.isEmpty()) {
    JOptionPane.showMessageDialog(this, "Ngày sinh nhân viên không được để trống");
    return null;
}
   n.setNgaySinh(ngaySinh);
// Tiếp tục xử lý

    String gt = "Nam";
    if (rdoNam.isSelected()) {
        n.setGioiTinh(gt);
    } else {
        gt = "Nữ";
        n.setGioiTinh(gt);
    }
    if (strHinhAnh == null) {
        n.setImageNV("Loc.jpg");
    } else {
        n.setImageNV(strHinhAnh);
    }
    return n;
}


    private ChucVu getData2() {
        ChucVu c = new ChucVu();
        if (txt_TenCV.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Tên chức vụ không được để trống");
            return null;
        }
        c.setTenChucVu(txt_TenCV.getText());
        if (txt_Luong.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Lương chức vụ không được để trống");
            return null;
        }
        try{
            double bien = Double.valueOf(txt_Luong.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Lương chức vụ không được Nhập chữ");
        }
        if(Double.parseDouble(txt_Luong.getText()) <=0){
            JOptionPane.showMessageDialog(this, "Lương chức vụ không được nhỏ hơn không");
            return null;
        }
        double l = Double.valueOf(txt_Luong.getText());
        c.setLuong(BigDecimal.valueOf(l));
        return c;
    }
    private void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
        tblNhanVien.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
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
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        lbMaNV15 = new javax.swing.JLabel();
        lbHoten5 = new javax.swing.JLabel();
        lbGioitinh5 = new javax.swing.JLabel();
        lbNgaysinh5 = new javax.swing.JLabel();
        lbSDT5 = new javax.swing.JLabel();
        lbDiachi5 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel28 = new javax.swing.JLabel();
        lbImages = new javax.swing.JLabel();
        lbMaNV16 = new javax.swing.JLabel();
        cbbMaCV = new javax.swing.JComboBox<>();
        lbMaNV17 = new javax.swing.JLabel();
        txtTenChucVu = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jPanel23 = new javax.swing.JPanel();
        lbThongtin2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_ChucVu = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_Luong = new javax.swing.JTextField();
        txt_MaNV = new javax.swing.JTextField();
        txt_TenCV = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        lbThongtin3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_NhanVien = new javax.swing.JTable();
        btn_Luu = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txt_MaCV = new javax.swing.JTextField();
        btn_Show = new javax.swing.JButton();
        btn_XoaSP = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Họ và Tên", "Tên chức vụ", "Giới tính", "Ngày sinh", "Số ĐT", "Địa chỉ", "Lương"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVientblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhanVien);

        jPanel22.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 1090, 240));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setForeground(new java.awt.Color(49, 139, 130));

        lbMaNV15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV15.setForeground(new java.awt.Color(49, 139, 130));
        lbMaNV15.setText("Mã nhân viên:");

        lbHoten5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbHoten5.setForeground(new java.awt.Color(49, 139, 130));
        lbHoten5.setText("Họ và tên:");

        lbGioitinh5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGioitinh5.setForeground(new java.awt.Color(49, 139, 130));
        lbGioitinh5.setText("Giới tính:");

        lbNgaysinh5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbNgaysinh5.setForeground(new java.awt.Color(49, 139, 130));
        lbNgaysinh5.setText("Ngày sinh:");

        lbSDT5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbSDT5.setForeground(new java.awt.Color(49, 139, 130));
        lbSDT5.setText("Số điện thoại:");

        lbDiachi5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbDiachi5.setForeground(new java.awt.Color(49, 139, 130));
        lbDiachi5.setText("Địa chỉ:");

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNam.setForeground(new java.awt.Color(49, 139, 130));
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdoNu.setForeground(new java.awt.Color(49, 139, 130));
        rdoNu.setText("Nữ");

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(49, 139, 130));
        jLabel28.setText("Thông tin:");

        lbImages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImages.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImageslbImagesMouseClicked(evt);
            }
        });

        lbMaNV16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV16.setForeground(new java.awt.Color(49, 139, 130));
        lbMaNV16.setText("Mã chức vụ");

        cbbMaCV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaCVcbbMaCVActionPerformed(evt);
            }
        });

        lbMaNV17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbMaNV17.setForeground(new java.awt.Color(49, 139, 130));
        lbMaNV17.setText("Tên chức vụ");

        txtTenChucVu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMaNV15)
                            .addComponent(lbHoten5))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addComponent(lbGioitinh5)
                            .addGap(50, 50, 50)
                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel21Layout.createSequentialGroup()
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lbSDT5)
                                .addComponent(lbDiachi5, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNgaysinh5))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtDiaChi)
                                .addGroup(jPanel21Layout.createSequentialGroup()
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))))
                .addGap(4, 4, 4)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(lbMaNV16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(lbMaNV17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(lbImages, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 842, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbMaNV16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbMaCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lbMaNV15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbMaNV17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbHoten5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addComponent(lbNgaysinh5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbSDT5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi)
                            .addComponent(lbDiachi5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNu)
                            .addComponent(rdoNam)
                            .addComponent(lbGioitinh5))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lbImages, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel22.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 900, 300));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnLuu.setForeground(new java.awt.Color(49, 139, 130));
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnSua.setForeground(new java.awt.Color(49, 139, 130));
        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(49, 139, 130));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setToolTipText("");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnThem.setForeground(new java.awt.Color(49, 139, 130));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnLuu)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 470, 50));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        btnTimKiem.setForeground(new java.awt.Color(49, 139, 130));
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_28px.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem)
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimKiem)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 310, 440, 50));

        jTabbedPane1.addTab("Quản lý NV", jPanel22);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbThongtin2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin2.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin2.setText("Nhân viên");
        lbThongtin2.setToolTipText("");
        jPanel23.add(lbThongtin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 620, -1));

        tbl_ChucVu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã chức vụ", "Tên chức vụ", "Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_ChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ChucVuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_ChucVu);

        jPanel23.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 620, 160));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(49, 139, 130));
        jLabel1.setText("Lương");
        jPanel23.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Mã nhân viên");
        jPanel23.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 320, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Tên chức vụ");
        jPanel23.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 100, -1, -1));

        txt_Luong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel23.add(txt_Luong, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 130, 150, -1));

        txt_MaNV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel23.add(txt_MaNV, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 320, 150, -1));

        txt_TenCV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel23.add(txt_TenCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 150, -1));

        btn_Them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(49, 139, 130));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });
        jPanel23.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, -1, -1));

        lbThongtin3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin3.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin3.setText("Quản lý chức vụ nhân viên");
        lbThongtin3.setToolTipText("");
        jPanel23.add(lbThongtin3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 620, -1));

        tbl_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Tên chức vụ"
            }
        ));
        tbl_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_NhanVien);

        jPanel23.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 620, 210));

        btn_Luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Luu.setForeground(new java.awt.Color(49, 139, 130));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });
        jPanel23.add(btn_Luu, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, -1, -1));

        btn_Sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(49, 139, 130));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });
        jPanel23.add(btn_Sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 170, -1, -1));

        btn_Xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(49, 139, 130));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_Xoa.setText("Xóa nếu rỗng");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });
        jPanel23.add(btn_Xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Mã chức vụ");
        jPanel23.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, -1, -1));

        txt_MaCV.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel23.add(txt_MaCV, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 150, -1));

        btn_Show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Show.setForeground(new java.awt.Color(49, 139, 130));
        btn_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reset_28px.png"))); // NOI18N
        btn_Show.setText("Show Nhân viên");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });
        jPanel23.add(btn_Show, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 370, -1, -1));

        btn_XoaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_XoaSP.setForeground(new java.awt.Color(49, 139, 130));
        btn_XoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_XoaSP.setText("Xóa Nhân viên trước");
        btn_XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPActionPerformed(evt);
            }
        });
        jPanel23.add(btn_XoaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, -1, -1));

        jTabbedPane1.addTab("Quản lý chức vụ", jPanel23);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 720));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1090, 720));
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVientblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVientblNhanVienMouseClicked
        fillData(tblNhanVien.getSelectedRow());
    }//GEN-LAST:event_tblNhanVientblNhanVienMouseClicked

    private void lbImageslbImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImageslbImagesMouseClicked
        ChucVuDAO chucVuDAO = new ChucVuDAO();
           int maChucVu = 1; // Đây là mã chức vụ cần tìm
        String tenChucVu = chucVuDAO.getTenChucVuById(maChucVu);
           System.out.println("Tên chức vụ: " + tenChucVu);


    }//GEN-LAST:event_lbImageslbImagesMouseClicked

    private void cbbMaCVcbbMaCVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaCVcbbMaCVActionPerformed
        try {
    int maChucVu = (int) cbbMaCV.getSelectedItem(); // Chắc chắn rằng cbbMaCV là JComboBox<Integer>
    
        ChucVuDAO chucVuDAO = new ChucVuDAO();
        String tenChucVu = chucVuDAO.getTenChucVuById(maChucVu);
    
        txtTenChucVu.setText(tenChucVu);
         } catch (Exception e) {
           // Xử lý ngoại lệ nếu cần
}
    }//GEN-LAST:event_cbbMaCVcbbMaCVActionPerformed

    private void tbl_ChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ChucVuMouseClicked
        fillData2(tbl_ChucVu.getSelectedRow());
    }//GEN-LAST:event_tbl_ChucVuMouseClicked

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
      NhanVienDAO nhanVienDAO = new NhanVienDAO();
      NhanVien nhanVien = getData();
       if (nhanVien != null) {
          nhanVienDAO.insert(nhanVien);
     JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
    // Lấy danh sách nhân viên từ cơ sở dữ liệu
     List<NhanVien> listNhanVien = nhanVienDAO.selectAll();
    // Hiển thị danh sách nhân viên
    showData((ArrayList<NhanVien>) listNhanVien);
} else {
    JOptionPane.showMessageDialog(this, "Không thể thêm nhân viên");}

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
       if (txtMaNV.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
        return;
    }
       NhanVienDAO nhanVienDAO = new NhanVienDAO();
       NhanVien existingNhanVien = nhanVienDAO.selectById(txtMaNV.getText());
      if (existingNhanVien == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã " + txtMaNV.getText());
        return;
    } 
      // Lấy thông tin từ giao diện
      NhanVien updatedNhanVien = getData();
        // Thực hiện cập nhật
      nhanVienDAO.update(updatedNhanVien);
      JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");

// Hiển thị danh sách nhân viên sau khi cập nhật
      List<NhanVien> listNhanVien = nhanVienDAO.selectAll();
        ArrayList<NhanVien> arrayListNhanVien = new ArrayList<>(listNhanVien);
          showData(arrayListNhanVien);     
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (txtMaNV.getText().trim().equals("")) {
           JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
            return;
}
      NhanVienDAO nhanVienDAO = new NhanVienDAO();
      NhanVien existingNhanVien = nhanVienDAO.selectById(txtMaNV.getText());
      if (existingNhanVien == null) {
         JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã " + txtMaNV.getText());
         return;
}
// Thực hiện xóa
      nhanVienDAO.delete(txtMaNV.getText());
// Cập nhật danh sách nhân viên sau khi xóa
      List<NhanVien> listNhanVien = nhanVienDAO.selectAll();
// Hiển thị danh sách
     showData((ArrayList<NhanVien>) listNhanVien);
        JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        enabled();
        clear();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
       if (txt_MaCV.getText().trim().equals("")) {
    JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
    return;
}

       ChucVuDAO chucVuDAO = new ChucVuDAO();
       ChucVu existingChucVu = chucVuDAO.selectById(Integer.parseInt(txt_MaCV.getText()));

       if (existingChucVu == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy chức vụ với mã " + txt_MaCV.getText());
            return;
}

// Lấy danh sách nhân viên theo chức vụ
      List<NhanVien> listNhanVien = nhanVienDAO.getNhanVienByChucVu(existingChucVu.getMaChucVu());

// Hiển thị danh sách
      showData3((ArrayList<NhanVien>) listNhanVien);

    }//GEN-LAST:event_btn_ShowActionPerformed

    private void tbl_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_NhanVienMouseClicked
        fillData3(tbl_NhanVien.getSelectedRow());
    }//GEN-LAST:event_tbl_NhanVienMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        enabled2();
        clear2();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
         if (txt_MaCV.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Mã chức vụ không được để trống");
        return;
    }

    ChucVuDAO chucVuDAO = new ChucVuDAO();
    ChucVu existingChucVu = chucVuDAO.selectById(Integer.parseInt(txt_MaCV.getText()));
    if (existingChucVu == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy chức vụ với mã " + txt_MaCV.getText());
        return;
    }

    // Lấy thông tin từ giao diện
    ChucVu updatedChucVu = getData2();

    // Thực hiện cập nhật
    chucVuDAO.update(updatedChucVu);

    // Hiển thị thông tin sau khi cập nhật
    list = chucVuDAO.selectAll();
    JOptionPane.showMessageDialog(this, "Cập nhật chức vụ thành công");
    showData2(list);
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
          ChucVuDAO chucVuDAO = new ChucVuDAO();

    // Lấy thông tin từ giao diện
    ChucVu newChucVu = getData2();

    // Thực hiện thêm mới
    chucVuDAO.insert(newChucVu);

    // Hiển thị thông tin sau khi thêm mới
    list = chucVuDAO.selectAll();
    JOptionPane.showMessageDialog(this, "Thêm mới chức vụ thành công");
    showData2(list);

    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
      if (txt_MaCV.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Mã chức vụ không được để trống");
        return;
    }

    ChucVuDAO chucVuDAO = new ChucVuDAO();
    ChucVu existingChucVu = chucVuDAO.selectById(Integer.parseInt(txt_MaCV.getText()));
    if (existingChucVu == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy chức vụ với mã " + txt_MaCV.getText());
        return;
    }

    // Thực hiện xóa
    chucVuDAO.delete(existingChucVu.getMaChucVu());

    // Hiển thị thông tin sau khi xóa
    list = chucVuDAO.selectAll();
    JOptionPane.showMessageDialog(this, "Xóa chức vụ thành công");
    showData2(list);

    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPActionPerformed
      if (txt_MaNV.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Mã nhân viên không được để trống");
        return;
    }

    NhanVienDAO nhanVienDAO = new NhanVienDAO();
    NhanVien existingNhanVien = nhanVienDAO.selectById(txt_MaNV.getText());
    if (existingNhanVien == null) {
        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên với mã " + txt_MaNV.getText());
        return;
    }

    // Thực hiện xóa
    nhanVienDAO.delete(existingNhanVien.getMaNhanVien());

    // Hiển thị thông tin sau khi xóa
    listNhanVien = nhanVienDAO.selectAll();
        JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công");
        showData((ArrayList<NhanVien>) listNhanVien);
    }//GEN-LAST:event_btn_XoaSPActionPerformed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String query = txtTimKiem.getText();
        filter(query);
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbMaCV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lbDiachi5;
    private javax.swing.JLabel lbGioitinh5;
    private javax.swing.JLabel lbHoten5;
    private javax.swing.JLabel lbImages;
    private javax.swing.JLabel lbMaNV15;
    private javax.swing.JLabel lbMaNV16;
    private javax.swing.JLabel lbMaNV17;
    private javax.swing.JLabel lbNgaysinh5;
    private javax.swing.JLabel lbSDT5;
    private javax.swing.JLabel lbThongtin2;
    private javax.swing.JLabel lbThongtin3;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tbl_ChucVu;
    private javax.swing.JTable tbl_NhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenChucVu;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_Luong;
    private javax.swing.JTextField txt_MaCV;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_TenCV;
    // End of variables declaration//GEN-END:variables

}
