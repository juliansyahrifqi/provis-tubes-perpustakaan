package form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import perpustakaan.Koneksi;

/**
 *
 * @author oyeaaa
 */
public class formPinjamBuku extends javax.swing.JInternalFrame {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();

    // Mendapatkan tanggal sekarang
    String tanggalSekarang = dateFormat.format(date);
   
    /**
     * Creates new form formPinjamBuku
     */
    public formPinjamBuku() {
        initComponents();
        load_judul_buku();
        load_peminjaman_hari();
        
        // Tanggal pinjam set dengan tanggal sekarang
        txtTanggalPinjam.setText(tanggalSekarang);
        set_tanggal_kembali();
        
        // Session login anggota
        txtIdAnggota.setText(Integer.toString(formLoginAnggota.getId()));
        txtNamaAnggota.setText(formLoginAnggota.getNama());
        
        txtIdAnggota.setEditable(false);
        txtNamaAnggota.setEditable(false);
        txtTanggalPinjam.setEditable(false);
        txtTanggalKembali.setEditable(false);
        txtIdBuku.setEditable(false);
        txtPenulis.setEditable(false);
    }
    
    // Mendapatkan id peminjaman
    private int getID() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
                // Set variabel no ke 0
                int no=0;
                
                // Mempersiapkan statement
                Statement stmt = kon.createStatement();
                
                // Query sql
                String sql = "SELECT COUNT(*) as id_peminjaman from peminjaman ";
                
                // Eksekusi query dan menyimpan datanya
                ResultSet rs = stmt.executeQuery(sql);

                // Jika data ada, set id_peminjaman + 1;
                if (rs.next()) {
                    no = rs.getInt("id_peminjaman") + 1;
                }

                // Kembalikan variabel no
                return no;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }    
    
    
    // Method untuk menset tanggal kembali peminjaman
    private void set_tanggal_kembali() {
        
        Calendar cal = Calendar.getInstance();
        
        // Set waktu ke tanggal sekarang
        cal.setTime(date);
        
        // Tambahkan 5 hari dari tanggal sekarang
        cal.add(Calendar.DATE, 5);

        // Mendapatkan waktu 5 hari ke depan
        Date expirationDate = cal.getTime();
        
        // Format dari date menjadi string
        String tanggal_kembali = dateFormat.format(expirationDate);

        // Set textfield tanggal kembali otomatis dengan tanggal 5 hari kedepan
        txtTanggalKembali.setText(tanggal_kembali);
    }
    
    // Method untuk menampilkan judul buku ke dalam combo box
    private void load_judul_buku() {
        // Koneksi Database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();

            // Query sql
            String sql = "SELECT * FROM buku WHERE status = 'ADA' AND stok > 0";
            
            // Mengeksekusi query dan menyimpan datanya
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                cbJudulBuku.addItem(rs.getString("judul_buku"));
            }
            
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Method untuk menampilkan penulis buku dari judul buku yang dipilih
    private void load_penulis_buku() {
        
        // Koneksi database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();

            // Query sql
            String sql = "SELECT * FROM buku WHERE judul_buku = '"+cbJudulBuku.getSelectedItem()+"'";
            
            // Mengeksekusi query, dan menyimpan datanya
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                txtPenulis.setText(rs.getString("penulis"));
                txtIdBuku.setText(rs.getString("id_buku"));
            }
            
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Method untuk pinjam buku
    private void pinjam_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql pertama
            String sql = "INSERT INTO peminjaman VALUES (default, '"+txtIdAnggota.getText()+"')";
                   
            // Query sql kedua
            String sql2 = "INSERT INTO detail_peminjaman "
                    + " VALUES ('"+getID()+"','"+txtIdBuku.getText()
                    +"','"+txtTanggalPinjam.getText()
                    +"','"+txtTanggalKembali.getText()
                    +"', 'DIPINJAM');";
            
            String sql3 = "UPDATE buku SET stok = stok - 1 WHERE id_buku = '"+txtIdBuku.getText()+"'"; 
            
            // Eksekusi query pertama dan kedua
            int baris1 = stmt.executeUpdate(sql);
            int baris2 = stmt.executeUpdate(sql2);
            int baris3 = stmt.executeUpdate(sql3);
            
            if(baris1 > 0 && baris2 > 0 && baris3 > 0) {
                JOptionPane.showMessageDialog(null, "Pinjam Buku Berhasil!");
            } else {
                JOptionPane.showMessageDialog(null, "Pinjam Buku Gagal!");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);       
        }
    }
    
    // Menampilkan data buku yang dipinjam pada hari ini
    private void load_peminjaman_hari() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Nama header tabel
        Object tableHeader[] = {"ID PEMINJAMAN", "NAMA PEMINJAM", "JUDUL BUKU", "PENULIS", "TANGGAL PINJAM", "TANGGAL KEMBALI", "STATUS"};
        DefaultTableModel data = new DefaultTableModel(null, tableHeader);
        
        tblPeminjaman.setModel(data);
        
        try {
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();

            // Query sql
            String sql = "SELECT detail_peminjaman.id_peminjaman, nama_anggota, judul_buku, penulis, tanggal_pinjam, "
                    + "tanggal_kembali, detail_peminjaman.status "
                    + "FROM detail_peminjaman "
                    + "INNER JOIN buku ON detail_peminjaman.id_buku = buku.id_buku "
                    + "INNER JOIN peminjaman ON detail_peminjaman.id_peminjaman = peminjaman.id_peminjaman "
                    + "INNER JOIN anggota ON peminjaman.id_anggota = anggota.id_anggota "
                    + "WHERE tanggal_pinjam = '"+tanggalSekarang+"' AND anggota.id_anggota = '"+formLoginAnggota.getId()
                    + "' ORDER BY id_peminjaman";
            
            // Mengeksekusi query sql
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                String id_peminjaman = rs.getString(1);
                String nama_anggota = rs.getString(2);
                String judul_buku = rs.getString(3);
                String penulis = rs.getString(4);
                String tanggal_pinjam = rs.getString(5);
                String tanggal_kembali = rs.getString(6);
                String status = rs.getString(7);
                 
                String d[] = {id_peminjaman, nama_anggota, judul_buku, penulis, tanggal_pinjam, tanggal_kembali, status};
                data.addRow(d);
            }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    // Cari data peminjaman pada hari ini
    private void cari_peminjaman_hari() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Nama header tabel
        Object tableHeader[] = {"ID PEMINJAMAN", "NAMA PEMINJAM", "JUDUL BUKU", "PENULIS", "TANGGAL PINJAM", "TANGGAL KEMBALI", "STATUS"};
        DefaultTableModel data = new DefaultTableModel(null, tableHeader);
        
        tblPeminjaman.setModel(data);
        
        try {
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();

            // Query sql
            String sql = "SELECT detail_peminjaman.id_peminjaman, nama_anggota, judul_buku, penulis, tanggal_pinjam, tanggal_kembali, detail_peminjaman.status "
                    + "FROM detail_peminjaman "
                    + "INNER JOIN buku ON detail_peminjaman.id_buku = buku.id_buku "
                    + "INNER JOIN peminjaman ON detail_peminjaman.id_peminjaman = peminjaman.id_peminjaman "
                    + "INNER JOIN anggota ON peminjaman.id_anggota = anggota.id_anggota "
                    + "WHERE tanggal_pinjam = '"+tanggalSekarang+"' AND anggota.id_anggota = '"+formLoginAnggota.getId()
                    +"' AND judul_buku LIKE '%"+txtCari.getText()+"%'"
                    + "ORDER BY detail_peminjaman.id_peminjaman";
            
            // Mengeksekusi query
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()) {
                String id_peminjaman = rs.getString(1);
                String nama_anggota = rs.getString(2);
                String judul_buku = rs.getString(3);
                String penulis = rs.getString(4);
                String tanggal_pinjam = rs.getString(5);
                String tanggal_kembali = rs.getString(6);
                String status = rs.getString(7);
                
                String d[] = {id_peminjaman, nama_anggota, judul_buku, penulis, tanggal_pinjam, tanggal_kembali, status};
                data.addRow(d);
            }
            
            // Eksekusi query kedua
            ResultSet rs2 = stmt.executeQuery(sql);
           
           // Jika query kedua tidak ada datanya tampilkan pesan
           if(!rs2.next()) {
               JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
               load_peminjaman_hari();
           }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private boolean cek_peminjaman() {
        
        boolean cek = false;
        // Membuat koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "SELECT id_buku, peminjaman.id_anggota "
                    + "FROM detail_peminjaman "
                    + "INNER JOIN peminjaman ON peminjaman.id_peminjaman = detail_peminjaman.id_peminjaman"
                    + " WHERE id_anggota = '"+formLoginAnggota.getId()+"'"
                    + "AND id_buku = '"+txtIdBuku.getText()+"'  AND status = 'DIPINJAM'";
                 
            // Eksekusi query
            ResultSet rs = stmt.executeQuery(sql);
            
            // Jika data ada
            if(rs.next()) {
                cek = true;
            }
            
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return cek;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnKembali = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblIDAnggota = new javax.swing.JLabel();
        lblJudulBuku = new javax.swing.JLabel();
        lblPenulis = new javax.swing.JLabel();
        lblTanggalPinjam = new javax.swing.JLabel();
        lblTanggalKembali = new javax.swing.JLabel();
        txtIdAnggota = new javax.swing.JTextField();
        txtNamaAnggota = new javax.swing.JTextField();
        cbJudulBuku = new javax.swing.JComboBox<>();
        txtIdBuku = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        txtTanggalPinjam = new javax.swing.JTextField();
        txtTanggalKembali = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        btnReset = new javax.swing.JButton();
        btnPinjam = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        lblTitleTabel = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1366, 632));

        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FORM PINJAM BUKU PERPUSTAKAAN");
        lblTitle.setPreferredSize(new java.awt.Dimension(1366, 15));

        lblIDAnggota.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblIDAnggota.setText("ID Anggota");

        lblJudulBuku.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblJudulBuku.setText("Judul Buku");

        lblPenulis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPenulis.setText("Penulis Buku");

        lblTanggalPinjam.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTanggalPinjam.setText("Tanggal Peminjaman");

        lblTanggalKembali.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTanggalKembali.setText("Tanggal Dikembalikan");

        txtIdAnggota.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cbJudulBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJudulBukuActionPerformed(evt);
            }
        });

        txtIdBuku.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPeminjaman);

        btnReset.setText("RESET");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnPinjam.setText("PINJAM BUKU");
        btnPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinjamActionPerformed(evt);
            }
        });

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        lblTitleTabel.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        lblTitleTabel.setText("Peminjaman Buku Hari Ini");

        txtCari.setText("Cari judul buku disini");
        txtCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCariMouseClicked(evt);
            }
        });

        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTanggalPinjam)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblJudulBuku)
                                    .addComponent(lblIDAnggota))))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbJudulBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPenulis))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNamaAnggota))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 142, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPenulis)
                            .addComponent(lblTanggalKembali))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPinjam)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitleTabel)
                        .addGap(303, 303, 303)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTitleTabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblIDAnggota))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJudulBuku)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbJudulBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtIdBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPenulis))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTanggalPinjam)
                            .addComponent(txtTanggalPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTanggalKembali)
                            .addComponent(txtTanggalKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54))
        );

        cbJudulBuku.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbJudulBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJudulBukuActionPerformed
        load_penulis_buku();
    }//GEN-LAST:event_cbJudulBukuActionPerformed

    private void btnPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinjamActionPerformed
        
        // Konfirmasi pinjam buku
        
        if(cek_peminjaman()) {
            JOptionPane.showMessageDialog(null, "Buku yang dipilih sudah dipinjam!");
        } else {
            int pinjam = JOptionPane.showOptionDialog(this, 
                "Anda yakin pinjam buku ini?", null, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            if(pinjam == JOptionPane.YES_OPTION) {
                pinjam_buku(); 
            }
        }
        
        
    }//GEN-LAST:event_btnPinjamActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        cbJudulBuku.setSelectedItem(0);
        txtPenulis.setText("");
        txtIdBuku.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        load_peminjaman_hari();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void txtCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCariMouseClicked
        txtCari.setText("");
    }//GEN-LAST:event_txtCariMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cari_peminjaman_hari();
    }//GEN-LAST:event_btnCariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPinjam;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cbJudulBuku;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIDAnggota;
    private javax.swing.JLabel lblJudulBuku;
    private javax.swing.JLabel lblPenulis;
    private javax.swing.JLabel lblTanggalKembali;
    private javax.swing.JLabel lblTanggalPinjam;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleTabel;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtIdAnggota;
    private javax.swing.JTextField txtIdBuku;
    private javax.swing.JTextField txtNamaAnggota;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtTanggalKembali;
    private javax.swing.JTextField txtTanggalPinjam;
    // End of variables declaration//GEN-END:variables
}
