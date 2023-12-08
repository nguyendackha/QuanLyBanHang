package views;

import DAO.DanhMucDAO;
import DAO.SanPhamDAO;
import Entity.DanhMuc;
import Entity.SanPham;
import java.awt.Image;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author ASUS
 */
public class SanPhamPanel extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtm1 = new DefaultTableModel();
    private DefaultTableModel dtm2 = new DefaultTableModel();
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    private ArrayList<SanPham> listSanPham = new ArrayList<>();
    private ArrayList<DanhMuc> listDanhMuc = new ArrayList<>();
    private ArrayList<SanPham> listShow = new ArrayList<>();
    private ArrayList<DanhMuc> list = new ArrayList<>();
    private String strHinhAnh = null;
    private SanPhamDAO sanPhamDAO = new SanPhamDAO();
    private DanhMucDAO danhMucDAO = new DanhMucDAO(); 
    private int maSanPham;
    public static void main(String[] args) {
        // Tạo một JFrame mới để chứa SanPhamPanel
        JFrame frame = new JFrame("Quản Lý Sản Phẩm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một đối tượng SanPhamPanel
        SanPhamPanel sanPhamPanel = new SanPhamPanel();

        // Thêm SanPhamPanel vào JFrame
        frame.getContentPane().add(sanPhamPanel);

        // Cấu hình JFrame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
 public SanPhamPanel() {
    initComponents();

    // Lấy danh sách các đối tượng SanPham từ cơ sở dữ liệu
    List<Entity.SanPham> danhSachSanPham = sanPhamDAO.selectAll();

    // Lấy danh sách các đối tượng DanhMuc từ cơ sở dữ liệu
    List<DanhMuc> danhSachDanhMuc = danhMucDAO.getAll();

    // Đặt các danh sách để hiển thị dữ liệu
    listSanPham = new ArrayList<>();
    for (Entity.SanPham sanPhamEntity : danhSachSanPham) {
        // Tạo mới đối tượng SanPham từ đối tượng Entity.SanPham
        SanPham sanPham = new SanPham();
        // Copy các thuộc tính từ Entity sang SanPham
        // Ví dụ: sanPham.set... = sanPhamEntity.get...;
        listSanPham.add(sanPham);
    }

    listShow = new ArrayList<>(listSanPham);
    list = new ArrayList<>(danhSachDanhMuc);

    // Hiển thị dữ liệu cho SanPhamPanel
      showData(listSanPham);

    // Hiển thị dữ liệu cho DanhMuc
    showData1((ArrayList<DanhMuc>) danhSachDanhMuc);

    // Các phương thức Disable, Disable1 có thể tắt các thành phần giao diện người dùng
    // Load ComboBox với danh sách đối tượng DanhMuc
    loadCBB((ArrayList<DanhMuc>) danhSachDanhMuc);
}



    private void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }

 private void loadCBB(ArrayList<DanhMuc> list) {
    boxModel = (DefaultComboBoxModel) cbbMaSP.getModel();
    for (DanhMuc danhMuc : list) {
        cbbMaSP.addItem(danhMuc.getMaDanhMuc()+ " - " + danhMuc.getTenDanhMuc());
    }
}



    private void Disable() {
        txtTenSP.setEnabled(false);
        txtGiaTien.setEnabled(false);
        txtTenLoaiSP.setEnabled(false);
        txtDonVi.setEnabled(false);
        txtMaSP.setEnabled(false);
        rdoCon.setEnabled(false);
        rdoHet.setEnabled(false);
        cbbMaSP.setEnabled(false);
        btnLuu.setEnabled(false);
        btnSua.setEnabled(false);
        btnXoa.setEnabled(false);
    }

    private void Disable1() {
        txt_MaSP.setEnabled(false);
        txt_TenSP.setEnabled(false);
        btn_Luu.setEnabled(false);
        btn_Sua.setEnabled(false);
        btn_Xoa.setEnabled(false);
        btn_XoaSP.setEnabled(false);
    }

    private void Enabled() {
        txtTenSP.setEnabled(true);
        txtGiaTien.setEnabled(true);
        txtDonVi.setEnabled(true);
        txtMaSP.setEnabled(true);
        rdoCon.setEnabled(true);
        rdoHet.setEnabled(true);
        cbbMaSP.setEnabled(true);
        btnLuu.setEnabled(true);
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
    }

    private void Enable1() {
        txt_MaSP.setEnabled(true);
        txt_TenSP.setEnabled(true);
        btn_Luu.setEnabled(true);
        btn_Sua.setEnabled(true);
        btn_Xoa.setEnabled(true);
        btn_XoaSP.setEnabled(true);
    }

    private void Clear() {
        txtTenSP.setText("");
        txtGiaTien.setText("");
        txtGiaTien.setText("");
        txtDonVi.setText("");
        txtMaSP.setText("");
        buttonGroup1.clearSelection();
        cbbMaSP.setSelectedIndex(0);
        lbImages.setIcon(null);
    }

    private void Clear1() {
        txt_MaSP.setText("");
        txt_TenSP.setText("");
    }

  
     //Hiển thị dữ liệu của danh sách sản phẩm vào bảng.
    
     //listData Danh sách sản phẩm cần hiển thị.
   
    private void showData(ArrayList<SanPham> listData) {
        // Lấy mô hình bảng và đặt số dòng là 0 để xóa dữ liệu cũ.
        dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);

        // Duyệt qua danh sách sản phẩm và thêm từng sản phẩm vào bảng.
        for (SanPham sanPham : listData) {
            dtm.addRow(sanPham.toDataRow2());
        }
    }

  
     // Hiển thị dữ liệu của danh sách danh mục vào bảng.
    
    // Danh sách danh mục cần hiển thị.
    
    private void showData1(ArrayList<DanhMuc> listDanhMuc) {
        // Lấy mô hình bảng và đặt số dòng là 0 để xóa dữ liệu cũ.
        dtm1 = (DefaultTableModel) tbl_LoaiSanPham.getModel();
        dtm1.setRowCount(0);

        // Duyệt qua danh sách danh mục và thêm từng danh mục vào bảng.
        for (DanhMuc danhMuc : listDanhMuc) {
            dtm1.addRow(danhMuc.toDataRow());
        }
    }


     // Hiển thị dữ liệu của danh sách đối tượng (Object[]) vào bảng.

     // Danh sách đối tượng cần hiển thị.

    private void showData2(List<Object[]> listData) {
        // Lấy mô hình bảng và đặt số dòng là 0 để xóa dữ liệu cũ.
        dtm2 = (DefaultTableModel) tbl_SanPham.getModel();
        dtm2.setRowCount(0);

        // Duyệt qua danh sách đối tượng và thêm từng đối tượng vào bảng.
        for (Object[] rowData : listData) {
            dtm2.addRow(rowData);
        }
    }

//Đổ dữ liệu danh mục từ danh sách danh mục vào các trường dữ liệu trên giao diện.

private void fillData(String index) {
    try {
        // Tạo đối tượng SanPhamDAO để truy xuất dữ liệu từ cơ sở dữ liệu.
        SanPhamDAO sanPhamDAO = new SanPhamDAO();

        // Lấy thông tin sản phẩm tại vị trí index trong danh sách sản phẩm.
        SanPham sanPham = sanPhamDAO.selectById(listSanPham.get(Integer.parseInt(index)).getMaSanPham());
        // Đổ dữ liệu vào các trường trên giao diện.
        txtTenSP.setText(sanPham.getTenSanPham());
        cbbMaSP.setSelectedItem(sanPham.getMaDanhMuc());
        // Lấy thông tin danh mục của sản phẩm và hiển thị trên giao diện.
        String maDanhMuc = sanPham.getMaDanhMuc();
        DanhMucDAO danhMucDAO = new DanhMucDAO();
        DanhMuc danhMuc = danhMucDAO.selectById(maDanhMuc);
        if (danhMuc != null) {
            txtTenLoaiSP.setText(danhMuc.getTenDanhMuc());
        } else {
            txtTenLoaiSP.setText("");
        }
        txtDonVi.setText(String.valueOf(sanPham.getDonVi()));
        txtGiaTien.setText(String.valueOf(sanPham.getGiaTien()));
        txtMaSP.setText(sanPham.getMaSanPham());

        // Kiểm tra trạng thái và thiết lập ô radio tương ứng
        if ("Còn".equals(sanPham.getTrangThai())) {
            rdoCon.setSelected(true);
        } else {
            rdoHet.setSelected(true);
        }
        // Cập nhật hình ảnh sản phẩm trên giao diện.
        UpdateHinh(sanPham.getImageSanPham());
    } catch (Exception ex) {
        ex.printStackTrace();
        // Xử lý lỗi
    }
}


private void fillData1(int index) {
    // Lấy thông tin danh mục tại vị trí index trong danh sách danh mục.
    DanhMuc danhMuc = listDanhMuc.get(index);
    // Hiển thị thông tin danh mục trên giao diện.
    txt_TenSP.setText(danhMuc.getTenDanhMuc());
    txt_MaSP.setText(String.valueOf(danhMuc.getMaDanhMuc()));
}


public void UpdateHinh(String image) {
    // Tạo ImageIcon từ tệp ảnh và thay đổi kích thước ảnh để vừa với JLabel.
    ImageIcon img = new ImageIcon(getClass().getResource("/QuanAoPNG/" + image));
    Image anh = img.getImage();
    ImageIcon icon = new ImageIcon(anh.getScaledInstance(lbImages.getWidth(),
            lbImages.getHeight(), Image.SCALE_SMOOTH));
    lbImages.setIcon(icon);
}

    private SanPham getData() {
        // Tạo đối tượng SanPham và đặt giá trị các trường từ trên giao diện.
        SanPham sanPham = new SanPham();
        sanPham.setMaDanhMuc((String) cbbMaSP.getSelectedItem());
        if (txtDonVi.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Đơn vị sản phẩm không được để trống");
        return null;
    }

    try {
        int donVi = Integer.parseInt(txtDonVi.getText());
        sanPham.setDonVi(donVi);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập một giá trị đơn vị hợp lệ");
        return null;
    }

    String tt = "Còn";
    if (rdoCon.isSelected()) {
        sanPham.setTrangThai(tt);
    } else {
        tt = "Hết";
        sanPham.setTrangThai(tt);
    }

    if (txtGiaTien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Giá sản phẩm không được để trống");
        return null;
    }

    try {
        // Kiểm tra xem giá tiền có phải là số hay không.
        double bien = Double.valueOf(txtGiaTien.getText());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Giá sản phẩm không được Nhập chữ");
    }
    if (Double.parseDouble(txtGiaTien.getText()) <= 0) {
        JOptionPane.showMessageDialog(this, "Giá sản phẩm không được nhỏ hơn không");
        return null;
    }
   sanPham.setGiaTien((int) Double.parseDouble(txtGiaTien.getText()));
    if (strHinhAnh == null) {
        sanPham.setImageSanPham("ios_photos_100px.png");
    } else {
        sanPham.setImageSanPham(strHinhAnh);
    }
    return sanPham;
}

private void kiemtra() {
    // Các xử lý kiểm tra
}

private DanhMuc getData2() {
    DanhMuc danhMuc;
    if (txt_TenSP.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống");
        return null;
    }
    // Truyền giá trị cho constructor có tham số của DanhMuc
    danhMuc = new DanhMuc(/* MaDanhMuc */ "", txt_TenSP.getText());
    return danhMuc;
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
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        lbThongtin4 = new javax.swing.JLabel();
        lblMaSP = new javax.swing.JLabel();
        lblLoaiSP = new javax.swing.JLabel();
        lblTenSP = new javax.swing.JLabel();
        lblGia = new javax.swing.JLabel();
        lbGiaTien = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        txtDonVi = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        cbbMaSP = new javax.swing.JComboBox<>();
        txtMaSP = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem3 = new javax.swing.JButton();
        lblTenLoaiSP = new javax.swing.JLabel();
        txtTenLoaiSP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        rdoHet = new javax.swing.JRadioButton();
        rdoCon = new javax.swing.JRadioButton();
        lbThongtin1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        lbImages = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lbThongtin2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_LoaiSanPham = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_MaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_TenSP = new javax.swing.JTextField();
        btn_Them = new javax.swing.JButton();
        btn_Sua = new javax.swing.JButton();
        btn_Luu = new javax.swing.JButton();
        lbThongtin3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_SanPham = new javax.swing.JTable();
        btn_XoaSP = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_Show = new javax.swing.JButton();
        txt_MSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblSanPham.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblSanPham.setForeground(new java.awt.Color(49, 139, 130));
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Loại sản phẩm", "Tên sản phẩm", "Đơn vị", "Trạng thái", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setSelectionForeground(new java.awt.Color(49, 139, 130));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        jPanel9.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, 540, 560));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        lbThongtin4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbThongtin4.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin4.setText("Thông tin sản phẩm:");
        lbThongtin4.setToolTipText("");

        lblMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMaSP.setForeground(new java.awt.Color(49, 139, 130));
        lblMaSP.setText("Mã sản phẩm:");
        lblMaSP.setToolTipText("");

        lblLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblLoaiSP.setForeground(new java.awt.Color(49, 139, 130));
        lblLoaiSP.setText("Mã loại sản phẩm:");
        lblLoaiSP.setToolTipText("");

        lblTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenSP.setForeground(new java.awt.Color(49, 139, 130));
        lblTenSP.setText("Tên sản phẩm:");
        lblTenSP.setToolTipText("");

        lblGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblGia.setForeground(new java.awt.Color(49, 139, 130));
        lblGia.setText("Đơn vị tính:");
        lblGia.setToolTipText("");

        lbGiaTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbGiaTien.setForeground(new java.awt.Color(49, 139, 130));
        lbGiaTien.setText("Giá tiền:");
        lbGiaTien.setToolTipText("");

        txtGiaTien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtGiaTien.setToolTipText("");

        txtDonVi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDonVi.setToolTipText("");

        txtTenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenSP.setToolTipText("");

        cbbMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbbMaSP.setToolTipText("");
        cbbMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaSPActionPerformed(evt);
            }
        });

        txtMaSP.setEditable(false);
        txtMaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtMaSP.setToolTipText("");
        txtMaSP.setEnabled(false);

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTimKiem.setForeground(new java.awt.Color(49, 139, 130));
        txtTimKiem.setToolTipText("");
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
        });

        btnTimKiem3.setForeground(new java.awt.Color(49, 139, 130));
        btnTimKiem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/search_28px.png"))); // NOI18N
        btnTimKiem3.setText("Tìm");
        btnTimKiem3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnTimKiem3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        lblTenLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTenLoaiSP.setForeground(new java.awt.Color(49, 139, 130));
        lblTenLoaiSP.setText("Tên loại:");
        lblTenLoaiSP.setToolTipText("");

        txtTenLoaiSP.setEditable(false);
        txtTenLoaiSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtTenLoaiSP.setToolTipText("");
        txtTenLoaiSP.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(49, 139, 130));
        jLabel1.setText("Trạng Thái");

        buttonGroup1.add(rdoHet);
        rdoHet.setText("Hết");

        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbThongtin4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(118, 118, 118))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiem3))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenSP)
                                    .addComponent(txtMaSP)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDonVi))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(lblTenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblLoaiSP)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbbMaSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(rdoCon)
                                        .addGap(32, 32, 32)
                                        .addComponent(rdoHet))
                                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbThongtin4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenSP)
                    .addComponent(lblTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTenLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTenLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDonVi)
                    .addComponent(lblGia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGiaTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoHet)
                    .addComponent(jLabel1)
                    .addComponent(rdoCon))
                .addGap(0, 36, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, -1, 390));

        lbThongtin1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin1.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin1.setText("Quản lý sản phẩm");
        lbThongtin1.setToolTipText("");
        jPanel9.add(lbThongtin1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btnThem.setForeground(new java.awt.Color(49, 139, 130));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa))
        );

        jPanel9.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 464, -1, -1));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbImages.setForeground(new java.awt.Color(49, 139, 130));
        lbImages.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbImages.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/ios_photos_100px.png"))); // NOI18N
        lbImages.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbImages.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbImages.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lbImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbImagesMouseClicked(evt);
            }
        });
        jPanel6.add(lbImages, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 190, 190));

        jPanel9.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 464, -1, -1));

        jTabbedPane1.addTab("Quản lý sản phẩm", jPanel9);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lbThongtin2.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin2.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin2.setText("Quản lý loại sản phẩm");
        lbThongtin2.setToolTipText("");

        jScrollPane1.setForeground(new java.awt.Color(49, 139, 130));

        tbl_LoaiSanPham.setForeground(new java.awt.Color(49, 139, 130));
        tbl_LoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã loại sản phẩm", "Tên loại sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_LoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LoaiSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_LoaiSanPham);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(49, 139, 130));
        jLabel2.setText("Mã loại sản phẩm");

        txt_MaSP.setEditable(false);
        txt_MaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(49, 139, 130));
        jLabel3.setText("Tên loại sản phẩm");

        txt_TenSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btn_Them.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Them.setForeground(new java.awt.Color(49, 139, 130));
        btn_Them.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/add_28px.png"))); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_Sua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Sua.setForeground(new java.awt.Color(49, 139, 130));
        btn_Sua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/edit_28px.png"))); // NOI18N
        btn_Sua.setText("Sửa");
        btn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SuaActionPerformed(evt);
            }
        });

        btn_Luu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Luu.setForeground(new java.awt.Color(49, 139, 130));
        btn_Luu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/save_28px.png"))); // NOI18N
        btn_Luu.setText("Lưu");
        btn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LuuActionPerformed(evt);
            }
        });

        lbThongtin3.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        lbThongtin3.setForeground(new java.awt.Color(49, 139, 130));
        lbThongtin3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbThongtin3.setText("Sản phẩm");
        lbThongtin3.setToolTipText("");

        jScrollPane2.setForeground(new java.awt.Color(49, 139, 130));

        tbl_SanPham.setForeground(new java.awt.Color(49, 139, 130));
        tbl_SanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tên loại sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_SanPham);

        btn_XoaSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_XoaSP.setForeground(new java.awt.Color(49, 139, 130));
        btn_XoaSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_XoaSP.setText("Xóa sản phẩm trước");
        btn_XoaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaSPActionPerformed(evt);
            }
        });

        btn_Xoa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Xoa.setForeground(new java.awt.Color(49, 139, 130));
        btn_Xoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Delete_28px.png"))); // NOI18N
        btn_Xoa.setText("Xóa nếu rỗng");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_Show.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_Show.setForeground(new java.awt.Color(49, 139, 130));
        btn_Show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/reset_28px.png"))); // NOI18N
        btn_Show.setText("Show sản phẩm");
        btn_Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ShowActionPerformed(evt);
            }
        });

        txt_MSP.setEditable(false);
        txt_MSP.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(49, 139, 130));
        jLabel4.setText("Mã sản phẩm");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_MSP, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(23, 23, 23)
                                .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(btn_Luu)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_Sua))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(btn_Them)
                            .addComponent(btn_Show)
                            .addComponent(btn_XoaSP)
                            .addComponent(btn_Xoa)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbThongtin2, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lbThongtin3, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbThongtin2)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(lbThongtin3))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_TenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them)
                            .addComponent(btn_Sua)
                            .addComponent(btn_Luu))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_Xoa)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_MSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addComponent(btn_Show)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_XoaSP)
                        .addGap(72, 72, 72))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(228, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản lý loại sản phẩm", jPanel11);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 810));
        jTabbedPane1.getAccessibleContext().setAccessibleName("Loại món");
    }// </editor-fold>//GEN-END:initComponents

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
    int selectedRowIndex = tblSanPham.getSelectedRow();
    String selectedRowString = String.valueOf(selectedRowIndex);
    fillData(selectedRowString);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void lbImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbImagesMouseClicked
           try {
        // Tạo một đối tượng JFileChooser để chọn tệp hình ảnh từ hệ thống.
        JFileChooser jfc = new JFileChooser("C:\\Users\\ASUS\\OneDrive\\Desktop\\PRO1041\\DUAN1\\src\\QuanAoPNG");

        // Hiển thị hộp thoại chọn tệp và lấy tệp được chọn.
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();

        // Đọc hình ảnh từ tệp và gán vào đối tượng Image.
        Image img = ImageIO.read(file);

        // Lấy tên tệp hình ảnh và lưu vào biến strHinhAnh.
        strHinhAnh = file.getName();

        // Đặt văn bản của JLabel lbImages là rỗng.
        lbImages.setText("");

        // Lấy kích thước của JLabel lbImages.
        int width = lbImages.getWidth();
        int height = lbImages.getHeight();

        // Đặt hình ảnh vào JLabel và thay đổi kích thước theo kích thước của JLabel.
        lbImages.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
    } catch (Exception e) {
        // In ra thông báo lỗi nếu có vấn đề khi đọc hoặc hiển thị hình ảnh.
        e.printStackTrace();
    }

    }//GEN-LAST:event_lbImagesMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Enabled();
        Clear();
        btnSua.setEnabled(true);
        btnXoa.setEnabled(true);
        btnLuu.setEnabled(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
    // Thêm sản phẩm mới vào cơ sở dữ liệu bằng cách gọi phương thức insert của đối tượng SanPhamDAO.
    sanPhamDAO.insert(getData());

    // Hiển thị thông báo thành công sau khi thêm sản phẩm vào cơ sở dữ liệu.
    String mess = "Thêm sản phẩm thành công";
    JOptionPane.showMessageDialog(this, mess);

    // Lấy danh sách sản phẩm từ cơ sở dữ liệu sau khi thêm mới và cập nhật danh sách hiển thị trên giao diện.
    listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll(); 
    showData(listSanPham);
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
                // Kiểm tra xem ô nhập mã sản phẩm có trống hay không.
    if (txtMaSP.getText().equals("")) {
        // Hiển thị thông báo nếu không có mã sản phẩm được chọn để sửa.
        JOptionPane.showMessageDialog(this, "Chọn sản phẩm để sửa");
        return;
    }

    // Lấy mã sản phẩm từ trường mã sản phẩm trên giao diện.
    maSanPham = Integer.parseInt(txtMaSP.getText());

    // Gọi phương thức getData() để lấy thông tin sản phẩm từ các trường dữ liệu trên giao diện.
    SanPham sanPhamToUpdate = getData();

    if (sanPhamToUpdate != null) {
        // Gán mã sản phẩm cho đối tượng SanPham cần cập nhật.
        sanPhamToUpdate.setMaSanPham(String.valueOf((char) Integer.parseInt(txtMaSP.getText())));
        // Gọi phương thức update của đối tượng SanPhamDAO để cập nhật thông tin sản phẩm trong cơ sở dữ liệu.
        sanPhamDAO.update(sanPhamToUpdate);

        // Hiển thị thông báo thành công sau khi sửa sản phẩm.
        String mess = "Sửa sản phẩm thành công";
        JOptionPane.showMessageDialog(this, mess);

        // Cập nhật danh sách sản phẩm sau khi sửa và hiển thị trên giao diện.
        listSanPham = new ArrayList<>(sanPhamDAO.getAll());
        showData(listSanPham);
    } else {
        // Hiển thị thông báo nếu không tìm thấy sản phẩm để sửa.
        JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm để sửa");
    }

    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
         if (txtMaSP.getText().equals("")) {
           JOptionPane.showMessageDialog(this, "Chọn mã sản phẩm để xóa");
           return;
        }
        String maSanPham = (txtMaSP.getText());
        sanPhamDAO.delete(maSanPham); // Sửa đoạn này để xóa sản phẩm
         String mess = "Xóa sản phẩm thành công";
         JOptionPane.showMessageDialog(this, mess);
          listSanPham = (ArrayList<SanPham>) sanPhamDAO.getAll();
          showData(listSanPham);

    }//GEN-LAST:event_btnXoaActionPerformed

    private void cbbMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaSPActionPerformed
          // Lấy giá trị maDanhMucString từ cbbMaSP
    String maDanhMucString = (String) cbbMaSP.getSelectedItem();

    try {
        // Chuyển đổi maDanhMucString từ String sang int
        int maDanhMuc = Integer.parseInt(maDanhMucString);

        // Sử dụng DanhMucDAO để lấy tên danh mục sản phẩm
        DanhMucDAO danhMucDAO = new DanhMucDAO();
        DanhMuc danhMuc = danhMucDAO.selectById(String.valueOf(maDanhMuc));

        // Kiểm tra xem danh mục có tồn tại không
        if (danhMuc != null) {
            // Lấy tên danh mục và đặt giá trị cho txtTenLoaiSP
            String tenLoaiSP = danhMuc.getTenDanhMuc();
            txtTenLoaiSP.setText(tenLoaiSP);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy danh mục với mã " + maDanhMuc);
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Mã danh mục không hợp lệ");
    }

    }//GEN-LAST:event_cbbMaSPActionPerformed

    private void tbl_LoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LoaiSanPhamMouseClicked
        fillData1(tbl_LoaiSanPham.getSelectedRow());
    }//GEN-LAST:event_tbl_LoaiSanPhamMouseClicked

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        Enable1();
        Clear1();
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ShowActionPerformed
           // Kiểm tra xem ô nhập mã sản phẩm có trống hay không.
    if (txt_MaSP.getText().equals("")) {
        // Hiển thị thông báo nếu không có mã sản phẩm được chọn để hiển thị thông tin.
        JOptionPane.showMessageDialog(this, "Chọn mã sản phẩm để show");
        return;
    }

    try {
        // Lấy mã sản phẩm từ trường mã sản phẩm trên giao diện.
        String maSanPham = (txt_MaSP.getText());

        // Gọi phương thức selectById của đối tượng SanPhamDAO để lấy thông tin sản phẩm theo mã.
        SanPham sanPham = sanPhamDAO.selectById(maSanPham);

        if (sanPham == null) {
            // Hiển thị thông báo nếu không tìm thấy sản phẩm với mã đã nhập.
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm với mã " + maSanPham);
        } else {
            // Gọi phương thức showData2 để hiển thị thông tin sản phẩm trên giao diện.
            showData2((List<Object[]>) sanPham);
        }
    } catch (NumberFormatException e) {
        // Hiển thị thông báo lỗi nếu người dùng nhập mã sản phẩm không phải là số nguyên.
        JOptionPane.showMessageDialog(this, "Nhập mã sản phẩm là một số nguyên");
    }

    }//GEN-LAST:event_btn_ShowActionPerformed

    private void btn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LuuActionPerformed
           danhMucDAO.insert(getData2());
            String mess = "Thêm danh mục thành công";
            JOptionPane.showMessageDialog(this, mess);
            listDanhMuc = new ArrayList<>(danhMucDAO.getAll());
            showData1(listDanhMuc);
    }//GEN-LAST:event_btn_LuuActionPerformed

    private void btn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SuaActionPerformed
            if (txt_MaSP.getText().equals("")) {
        JOptionPane.showMessageDialog(this, "Chọn mã sản phẩm để sửa");
        return;
    }
    int maSanPham = Integer.parseInt(txt_MaSP.getText());
    // Lấy thông tin danh mục từ giao diện người dùng
    DanhMuc danhMuc = getData2();
    // Kiểm tra xem danhMuc có null không và gán mã sản phẩm vào danhMuc
    if (danhMuc != null) {
        danhMuc.setMaDanhMuc(String.valueOf(maSanPham));
        danhMucDAO.update(danhMuc);
        String mess = "Cập nhật danh mục thành công";
        JOptionPane.showMessageDialog(this, mess);
        listDanhMuc = new ArrayList<>(danhMucDAO.getAll());
        showData1(listDanhMuc);
    } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy danh mục để cập nhật");
    }
    }//GEN-LAST:event_btn_SuaActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
            if (txt_MaSP.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Chọn mã sản phẩm để xóa");
                return;
            }
            String maSanPham = (txt_MaSP.getText());
            danhMucDAO.delete(maSanPham);
            String mess = "Xóa danh mục thành công";
            JOptionPane.showMessageDialog(this, mess);
            listDanhMuc = new ArrayList<>(danhMucDAO.getAll());
            showData1(listDanhMuc);

    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_XoaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaSPActionPerformed
        if (txt_MSP.getText().equals("")) {
         JOptionPane.showMessageDialog(this, "Chọn mã san phẩm để xóa");
          return;
        }
        String maSanPham = (txt_MSP.getText());
        sanPhamDAO.delete(maSanPham);
        String mess = "Xóa sản phẩm thành công";
        JOptionPane.showMessageDialog(this, mess);
        listSanPham = new ArrayList<>(sanPhamDAO.getAll());
        showData(listSanPham);
    }//GEN-LAST:event_btn_XoaSPActionPerformed

    private void tbl_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SanPhamMouseClicked
        int row = tbl_SanPham.getSelectedRow();
        txt_MSP.setText(tbl_SanPham.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbl_SanPhamMouseClicked

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        String query = txtTimKiem.getText();
        filter(query);
    }//GEN-LAST:event_txtTimKiemKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem3;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_Luu;
    private javax.swing.JButton btn_Show;
    private javax.swing.JButton btn_Sua;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JButton btn_XoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbMaSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbGiaTien;
    private javax.swing.JLabel lbImages;
    private javax.swing.JLabel lbThongtin1;
    private javax.swing.JLabel lbThongtin2;
    private javax.swing.JLabel lbThongtin3;
    private javax.swing.JLabel lbThongtin4;
    private javax.swing.JLabel lblGia;
    private javax.swing.JLabel lblLoaiSP;
    private javax.swing.JLabel lblMaSP;
    private javax.swing.JLabel lblTenLoaiSP;
    private javax.swing.JLabel lblTenSP;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tbl_LoaiSanPham;
    private javax.swing.JTable tbl_SanPham;
    private javax.swing.JTextField txtDonVi;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtTenLoaiSP;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txt_MSP;
    private javax.swing.JTextField txt_MaSP;
    private javax.swing.JTextField txt_TenSP;
    // End of variables declaration//GEN-END:variables
}
