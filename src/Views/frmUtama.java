/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Modules.mdlUtama;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class frmUtama extends Modules.mdlUtama {
    
    private static Modules.ConnectionBuilder cn = new Modules.ConnectionBuilder();
    private static Modules.OutputSuara os = new Modules.OutputSuara();
    private static Modules.StretchIcon si;
    

    /**
     * Creates new form utama
     */
    public frmUtama() {
        initComponents();
        lblUser.setIcon(new javax.swing.ImageIcon("Resources/user fix.png"));
        lblLogo.setIcon(new javax.swing.ImageIcon("Resources/logo.png"));
        btnTambah.setIcon(new javax.swing.ImageIcon("Resources/tambah benar.png"));
        btnEdit.setIcon(new javax.swing.ImageIcon("Resources/edit benar.jpg"));
        btnHapus.setIcon(new javax.swing.ImageIcon("Resources/hapus.png"));
        btnRefresh.setIcon(new javax.swing.ImageIcon("Resources/refresh.png"));
        RefreshData();
    }
    
    public void RefreshData() {
        try {
            DefaultTableModel mdl = new DefaultTableModel();
            ResultSet rs = cn.executeSelect("SELECT * FROM siswa_baru ORDER BY namalengkap ASC");
            mdl.addColumn("NISN");
            mdl.addColumn("Nama Lengkap");
            mdl.addColumn("Tempat Tanggal Lahir");
            mdl.addColumn("Jenis Kelamin");
            mdl.addColumn("Nama Ayah");
            mdl.addColumn("No. Telepon");
            while (rs.next()) {
                Object[] obj = new Object[6];
                    obj[0] = rs.getString("nisn");
                    obj[1] = rs.getString("namalengkap");
                    obj[2] = rs.getString("tempatlhr") + ", " + new SimpleDateFormat("d MMMM yyyy").format(rs.getDate("tanggallhr"));
                    obj[3] = (rs.getString("jeniskelamin").equalsIgnoreCase("L") ? "Laki - Laki" : "Perempuan");
                    obj[4] = rs.getString("namaayah");
                    obj[5] = rs.getString("handphone");
                
                mdl.addRow(obj);
            }
            
            txtRincian.setText("");
            tblSiswa.setModel(mdl);
            tblSiswa.getColumn("NISN").setPreferredWidth(100);
            tblSiswa.getColumn("Nama Lengkap").setPreferredWidth(200);
            tblSiswa.getColumn("Tempat Tanggal Lahir").setPreferredWidth(150);
            tblSiswa.getColumn("Jenis Kelamin").setPreferredWidth(100);
            tblSiswa.getColumn("Nama Ayah").setPreferredWidth(150);
            tblSiswa.getColumn("No. Telepon").setPreferredWidth(150);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cmpMain(), "Terjadi kesalahan saat memperbarui data.\nException: " + ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(mdlUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RefreshData(String filter) {
        try {
            DefaultTableModel mdl = new DefaultTableModel();
            ResultSet rs = cn.executeSelect("SELECT * FROM siswa_baru WHERE nisn LIKE \"%" + filter + "%\" OR namalengkap LIKE \"%" + filter + "%\" ORDER BY namalengkap ASC");
            mdl.addColumn("NISN");
            mdl.addColumn("Nama Lengkap");
            mdl.addColumn("Tempat Tanggal Lahir");
            mdl.addColumn("Jenis Kelamin");
            mdl.addColumn("Nama Ayah");
            mdl.addColumn("No. Telepon");
            while (rs.next()) {
                Object[] obj = new Object[6];
                    obj[0] = rs.getString("nisn");
                    obj[1] = rs.getString("namalengkap");
                    obj[2] = rs.getString("tempatlhr") + ", " + new SimpleDateFormat("d MMMM yyyy").format(rs.getDate("tanggallhr"));
                    obj[3] = (rs.getString("jeniskelamin").equalsIgnoreCase("L") ? "Laki - Laki" : "Perempuan");
                    obj[4] = rs.getString("namaayah");
                    obj[5] = rs.getString("handphone");
                
                mdl.addRow(obj);
            }
            
            txtRincian.setText("");
            tblSiswa.setModel(mdl);
            tblSiswa.getColumn("NISN").setPreferredWidth(100);
            tblSiswa.getColumn("Nama Lengkap").setPreferredWidth(200);
            tblSiswa.getColumn("Tempat Tanggal Lahir").setPreferredWidth(150);
            tblSiswa.getColumn("Jenis Kelamin").setPreferredWidth(100);
            tblSiswa.getColumn("Nama Ayah").setPreferredWidth(150);
            tblSiswa.getColumn("No. Telepon").setPreferredWidth(150);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(cmpMain(), "Terjadi kesalahan saat memperbarui data.\nException: " + ex.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(mdlUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void showRincian(String filter) {
        if (!filter.isEmpty()) {
            try {
                ResultSet rs = cn.executeSelect("SELECT * FROM siswa_baru WHERE nisn = \"" + filter + "\"");
                if (rs.next()) {
                    txtRincian.setText("RINCIAN BIODATA PESERTA DIDIK BERNAMA " + rs.getString("namalengkap").toUpperCase() + ":\n"
                            + "\n"
                            + "NISN: " + rs.getString("nisn") + "\n"
                            + "Nama Lengkap: " + rs.getString("namalengkap") + "\n"
                            + "Jenis Kelamin: " + (rs.getString("jeniskelamin").equals("L") ? "Laki - laki" : "Perempuan") + "\n"
                            + "Tempat, Tanggal Lahir: " + rs.getString("tempatlhr") + ", " + new SimpleDateFormat("d MMMM yyyy").format(rs.getDate("tanggallhr")) + "\n"
                            + "Alamat: " + rs.getString("alamat") + "\n"
                            + "Agama: " + rs.getString("agama") + "\n"
                            + "Nama Ayah: " + rs.getString("namaayah") + "\n"
                            + "Pekerjaan (Ayah): " + rs.getString("pekerjaanayah") + "\n"
                            + "Nomor Telepon (Ayah): " + rs.getString("teleponayah") + "\n"
                            + "Alamat (Ayah): " + rs.getString("alamatayah") + "\n"
                            + "Nama Ibu: " + rs.getString("namaibu") + "\n"
                            + "Pekerjaan (Ibu): " + rs.getString("pekerjaanibu") + "\n"
                            + "Nomor Telepon (Ibu): " + rs.getString("teleponibu") + "\n"
                            + "Alamat (Ibu): " + rs.getString("alamatibu") + "\n"
                            + "Status dalam Keluarga: " + rs.getString("status") + "\n"
                            + "Nomor Telepon/HP: " + rs.getString("handphone"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void logout() {
        if (JOptionPane.showConfirmDialog(this, "Yakin ingin keluar dari Program Pendaftaran Peserta Didik Baru?", "Logout?", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            os.logout();
            JOptionPane.showMessageDialog(this, "Terimakasih telah menggunakan Program Pendaftaran Peserta Didik Baru!");
            this.dispose();
            new frmLogin().setVisible(true);
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblUser = new javax.swing.JLabel();
        txtUsername = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtEmail = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSiswa = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        txtFilter = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRincian = new javax.swing.JTextArea();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Program Penerimaan Peserta Didik Baru 2019");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("jLabel1");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("SMP NEGERI 1 CONTOHBRO");

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("JALAN SOMETHING NO. 212 KECAMATAN SAMPEL KABUPATEN CONTOH, 43123");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        lblUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUser.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        txtUsername.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        txtUsername.setForeground(new java.awt.Color(255, 255, 255));
        txtUsername.setText("%NAMA_USER%");

        jButton1.setText("LOG OUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtEmail.setForeground(java.awt.Color.white);
        txtEmail.setText("%email%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel9.setForeground(java.awt.Color.white);
        jLabel9.setText("PROGRAM PENERIMAAN PESERTA DIDIK BARU");

        jLabel10.setForeground(java.awt.Color.white);
        jLabel10.setText("No. Telp. 088222203881");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 87, Short.MAX_VALUE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Data PPDB"));

        tblSiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblSiswa.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblSiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSiswaMouseReleased(evt);
            }
        });
        tblSiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblSiswaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSiswa);

        jButton7.setText("Cari");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFilterActionPerformed(evt);
            }
        });

        jLabel6.setText("Cari Peserta Didik:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Rincian"));

        txtRincian.setEditable(false);
        txtRincian.setColumns(20);
        txtRincian.setFont(new java.awt.Font("Courier", 0, 14)); // NOI18N
        txtRincian.setRows(5);
        txtRincian.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane3.setViewportView(txtRincian);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
        );

        btnTambah.setBackground(new java.awt.Color(255, 255, 255));
        btnTambah.setToolTipText("Tambahkan peserta didik baru...");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(255, 255, 255));
        btnEdit.setToolTipText("Ubah rincian peserta didik yang dipilih....");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(255, 255, 255));
        btnHapus.setToolTipText("Hapus peserta didik terpilih...");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton5.setText("Cetak");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Tentang");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setText("Copyright (c) 2019. Kelompok PPDB PBO. All Rights Reserved.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRefresh.setBackground(java.awt.Color.white);
        btnRefresh.setForeground(java.awt.Color.white);
        btnRefresh.setToolTipText("Segarkan tabel");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jMenu1.setText("Berkas");

        jMenuItem5.setText("Pengaturan Akun");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem1.setText("Keluar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Basis Data");

        jMenuItem6.setText("Manajemen Pengguna");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);
        jMenu2.add(jSeparator1);

        jMenuItem2.setText("Segarkan");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Ubah Informasi Konektivitas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Bantuan");

        jMenuItem4.setText("Tentang");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnHapus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        super.showTambah();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        try {
            if (tblSiswa.isRowSelected(tblSiswa.getSelectedRow())) {
                frmEdit frmEdit = new frmEdit();
                frmEdit.setNisn(String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)));
                frmEdit.getData();
                frmEdit.setVisible(true);
            } else {
                os.error();
                JOptionPane.showMessageDialog(this.cmpMain(), "Pihih dulu peserta mana yang mau diubah biodatanya!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mdlUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        // TODO add your handling code here:
        this.RefreshData();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        if (tblSiswa.isRowSelected(tblSiswa.getSelectedRow())) {
            if (tblSiswa.getSelectedRowCount() == 1) {
                if (JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus peserta bernama " + String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 1)) + " dengan NISN " + String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)) + "?", "Really?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    try {
                        String pesnama = String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 1));
                        cn.executeUpdate("DELETE FROM siswa_baru WHERE nisn = \"" + String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)) + "\"");
                        this.RefreshData();
                        os.input_ok();
                        JOptionPane.showMessageDialog(this, "Berhasil menghapus peserta bernama " + pesnama + "!", "Removed Successfully", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (tblSiswa.getSelectedRowCount() > 1) {
                int total = tblSiswa.getSelectedRowCount();
                if (JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus " + tblSiswa.getSelectedRowCount() + " peserta sekaligus?", "Really?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    //DefaultTableModel model = (DefaultTableModel) tblSiswa.getModel();
                    int arr[] = tblSiswa.getSelectedRows();
                    String nisn = "";
                    
                    for (int i = 0; i < arr.length; i++) {
                        nisn += "\"" + String.valueOf(tblSiswa.getValueAt(arr[i], 0)) + "\"";
                        if (i < arr.length - 1) nisn += ",";
                    }
                    
                    try {
                        String pesnama = String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 1));
                        cn.executeUpdate("DELETE FROM siswa_baru WHERE nisn IN (" + nisn + ")");
                        this.RefreshData();
                        os.input_ok();
                        JOptionPane.showMessageDialog(this, "Berhasil menghapus " + total + " peserta sekaligus!", "Removed Successfully", JOptionPane.INFORMATION_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    /*while (row != -1)
                    {
                    int modelRow = tblSiswa.convertRowIndexToModel( row );
                    System.out.println(String.valueOf(tblSiswa.getValueAt(modelRow, 1)));
                    model.removeRow( modelRow );
                    row = tblSiswa.getSelectedRow();
                    }*/
                }
            }
        } else {
            os.error();
            JOptionPane.showMessageDialog(this, "Pilih dulu peserta mana yang akan Anda hapus!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFilterActionPerformed
        // TODO add your handling code here:
        RefreshData(txtFilter.getText());
    }//GEN-LAST:event_txtFilterActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        RefreshData(txtFilter.getText());
    }//GEN-LAST:event_jButton7ActionPerformed

    private void tblSiswaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSiswaMouseReleased
        // TODO add your handling code here:
        this.showRincian(String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)));
    }//GEN-LAST:event_tblSiswaMouseReleased

    private void tblSiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblSiswaKeyReleased
        // TODO add your handling code here:
        this.showRincian(String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)));
    }//GEN-LAST:event_tblSiswaKeyReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        logout();
    }//GEN-LAST:event_formWindowClosing

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        super.showTentang();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        try {
            ResultSet rs = cn.executeSelect("SELECT * FROM user WHERE username = \"" + txtEmail.getText() + "\"");
            if (rs.next()) {
                frmAkun frm = new frmAkun();
                frm.txtNama.setText(rs.getString("nama"));
                frm.txtEmail.setText(rs.getString("email"));
                frm.txtUsername.setText(rs.getString("username"));
                frm.setVisible(true);
            } else {
                System.out.println(super.getUserLogin());
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        RefreshData(txtFilter.getText());
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "PERHATIAN!\n"
                + "Mengubah konfigurasi ini akan menyebabkan program tidak dapat terhubung ke database sebagaimana mestinya\n"
                + "apabila salah mengonfigurasikannya.\n\n"
                + "Yakin ingin mengubah pengaturan ini? Anda akan logout apabila mengonfirmasi ini.", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            new frmKonektivitas().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        super.showUserMan(txtEmail.getText());
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        frmPrint frm = null;
        if (tblSiswa.isRowSelected(tblSiswa.getSelectedRow())) {
            if (tblSiswa.getSelectedRowCount() > 1) {
                os.error();
                JOptionPane.showMessageDialog(this, "Anda hanya dapat memilih 1 peserta saja untuk dicetak!!!", "Out of Bounds", JOptionPane.ERROR_MESSAGE);
            } else {
                frm = new frmPrint(true);
                frm.setNisn(String.valueOf(tblSiswa.getValueAt(tblSiswa.getSelectedRow(), 0)));
            }
        } else {
            frm = new frmPrint(false);
        }
        frm.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        super.showTentang();
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUser;
    public javax.swing.JTable tblSiswa;
    public javax.swing.JLabel txtEmail;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextArea txtRincian;
    public javax.swing.JLabel txtUsername;
    // End of variables declaration//GEN-END:variables
}
