package form;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import perpustakaan.Koneksi;

/**
 *
 * @author oyeaaa
 */
public class formKelolaBuku extends javax.swing.JInternalFrame {
    
    // Format tanggal
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    Date date = new Date();
    
    /**
     * Creates new form formKelolaBuku
     */
    public formKelolaBuku() {
        initComponents();
        load_data_buku();
        
        // Set tanggal sekarang
        txtTanggal.setText(dateFormat.format(date));
        
        // Set id admin sesuai dengan yang login
        txtIdAdmin.setText(Integer.toString(formLoginAdmin.getId()));
        txtNamaAdmin.setText(formLoginAdmin.getNama());
        
        // Tanggal, id admin dan nama admin tidak bisa diedit
        txtTanggal.setEditable(false);
        txtIdAdmin.setEditable(false);
        txtNamaAdmin.setEditable(false);
        
        // Edit dan Hapus data tidak bisa sebelum memilih data yang akan dihapus
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        
    }
    
    // Method menampilkan semua data buku
    private void load_data_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Nama header tabel
        Object tableHeader[] = {"ID BUKU", "JUDUL BUKU", "PENERBIT", "PENULIS", "DESKRIPSI", "TAHUN TERBIT", "TANGGAL DITAMBAHKAN", "STOK", "DITAMBAHKAN", "STATUS"};
        DefaultTableModel data = new DefaultTableModel(null, tableHeader);
        
        // Tambahkan data ke tabel Buku
        tblBuku.setModel(data);
            
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "SELECT id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, "
                    + "stok, nama_admin, status "
                    + " FROM buku "
                    + " INNER JOIN admin ON buku.id_admin = admin.id_admin"
                    + " ORDER BY id_buku";
                    
            // Eksekusi query ke database
            ResultSet rs = stmt.executeQuery(sql);
            
            // Selama hasil ada ( true )
            while(rs.next()) {
                String id_buku = rs.getString(1);
                String judul_buku = rs.getString(2);
                String penerbit = rs.getString(3);
                String penulis = rs.getString(4);
                String deskripsi = rs.getString(5);
                String tahun_terbit = rs.getString(6);
                String date_created = rs.getString(7);
                String stok = rs.getString(8);
                String nama_admin = rs.getString(9);
                String status = rs.getString(10);
                
                // Tambahkan data dari database ke tabel
                String d[] = {id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, stok, nama_admin, status};
                data.addRow(d);
            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e);
            formUtamaAdmin utamaAdmin = new formUtamaAdmin();
            utamaAdmin.setVisible(true);
            this.dispose();
        }
    }
    
    // Method menampilkan data buku berdasarkan statusnya ( filter )
    private void load_buku_filter(String x) {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Nama header tabel
        Object tableHeader[] = {"ID BUKU", "JUDUL BUKU", "PENERBIT", "PENULIS", "DESKRIPSI", "TAHUN TERBIT", "TANGGAL DITAMBAHKAN","STOK", "DITAMBAHKAN", "STATUS"};
        DefaultTableModel data = new DefaultTableModel(null, tableHeader);
        
        // Tambahkan data ke tabel Buku
        tblBuku.setModel(data);
            
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "SELECT id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, "
                    + "stok, nama_admin, status "
                    + " FROM buku "
                    + " INNER JOIN admin ON buku.id_admin = admin.id_admin"
                    + " WHERE status = '"+x+"'"
                    + " ORDER BY id_buku";
                    
            // Eksekusi query ke database
            ResultSet rs = stmt.executeQuery(sql);
            
            // Selama hasil ada ( true )
            while(rs.next()) {
                String id_buku = rs.getString(1);
                String judul_buku = rs.getString(2);
                String penerbit = rs.getString(3);
                String penulis = rs.getString(4);
                String deskripsi = rs.getString(5);
                String tahun_terbit = rs.getString(6);
                String date_created = rs.getString(7);
                String stok = rs.getString(8);
                String nama_admin = rs.getString(9);
                String status = rs.getString(10);
                
                // Tambahkan data dari database ke tabel
                String d[] = {id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, stok, nama_admin, status};
                data.addRow(d);
            } 
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e);
            formUtamaAdmin utamaAdmin = new formUtamaAdmin();
            utamaAdmin.setVisible(true);
            this.dispose();
        }
    }
    
    // Menampilkan data yang dicari
    private void load_buku_cari() {
        
       // Koneksi ke database
       Connection kon = Koneksi.koneksiDB();

       // Nama header tabel
       Object tableHeader[] = {"ID BUKU", "JUDUL BUKU", "PENERBIT", "PENULIS", "DESKRIPSI", "TAHUN TERBIT", "TANGGAL DITAMBAHKAN","STOK", "DITAMBAHKAN", "STATUS"};
       DefaultTableModel data = new DefaultTableModel(null, tableHeader);

       // Tambahkan data ke tabel Buku
       tblBuku.setModel(data);

       try {
           // Mempersiapkan statement
           Statement stmt = kon.createStatement();

           // Query sql
           String sql = "SELECT id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, "
                   + "stok, nama_admin, status "
                   + " FROM buku "
                   + " INNER JOIN admin ON buku.id_admin = admin.id_admin"
                   + " WHERE judul_buku LIKE '%"+txtCari.getText()+"%'"
                   + " ORDER BY id_buku";

           // Eksekusi query ke database
           ResultSet rs = stmt.executeQuery(sql);

           // Selama hasil ada ( true )
           while(rs.next()) {
               String id_buku = rs.getString(1);
               String judul_buku = rs.getString(2);
               String penerbit = rs.getString(3);
               String penulis = rs.getString(4);
               String deskripsi = rs.getString(5);
               String tahun_terbit = rs.getString(6);
               String date_created = rs.getString(7);
               String stok = rs.getString(8);
               String nama_admin = rs.getString(9);
               String status = rs.getString(10);

               // Tambahkan data dari database ke tabel
               String d[] = {id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit, date_created, stok, nama_admin, status};
               data.addRow(d);
           } 
           
           // Hasil query kedua
           ResultSet rs2 = stmt.executeQuery(sql);
           
           // Jika datanya tidak ada
           if(!rs2.next()) {
               JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
               load_data_buku();
           }
       } catch(Exception e) {
           JOptionPane.showMessageDialog(null, e);
           load_data_buku();
       }

    }
    
    // Method tambah data buku
    private void tambah_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {    
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "INSERT INTO buku"
                    + " VALUES (default,'"+txtJudulBuku.getText()
                    +"','"+txtPenerbit.getText()
                    +"','"+txtPenulis.getText()
                    +"','"+txtDeskripsi.getText()
                    +"','"+cbTahun.getSelectedItem()
                    +"','"+txtTanggal.getText()
                    +"','"+Integer.parseInt(txtStok.getText())
                    +"','"+txtIdAdmin.getText()
                    +"', 'ADA');";
            
            // Eksekusi ke database
            int baris = stmt.executeUpdate(sql);
            
            // Jika baris / data bertambah
            // Tampilkan data terbaru
            if(baris > 0) {
                JOptionPane.showMessageDialog(null, "Tambah Data Berhasil!");
                load_data_buku();
            } else {
                JOptionPane.showMessageDialog(null, "Tambah Data Gagal!");
            }
           
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: "+e);
        } catch(Exception er) {
            JOptionPane.showMessageDialog(null, "Error :"+er);
        } 
    }
    
    // Method untuk edit / ubah data buku
    private void edit_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "UPDATE buku SET judul_buku = '"+txtJudulBuku.getText()
                    +"', penerbit = '"+txtPenerbit.getText()
                    +"', penulis = '"+txtPenulis.getText()
                    +"', deskripsi = '"+txtDeskripsi.getText()
                    +"', tahun_terbit = '"+cbTahun.getSelectedItem()
                    +"', stok = '"+txtStok.getText()
                    +"', date_created = '"+txtTanggal.getText()
                    +"', id_admin = '"+txtIdAdmin.getText()
                    +"', status = 'ADA' "
                    + "WHERE id_buku = '"+tblBuku.getValueAt(tblBuku.getSelectedRow(), 0).toString()+"'";
            
            // Eksekusi query ke database
            int baris = stmt.executeUpdate(sql);
            
            // Jika data berpengaruh
            // Tampilkan pesan berhasil
            if(baris>0) {
                JOptionPane.showMessageDialog(null, "Data Buku Berhasil Diupdate!");
            } else {
                JOptionPane.showMessageDialog(null, "Data Buku Gagal Diupdate!");
            }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error :"+e);
        }
    }
    
    // Method untuk hapus data buku
    private void hapus_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "UPDATE buku SET stok = 0, status = 'DIHAPUS' "
                    + "WHERE id_buku = '"+tblBuku.getValueAt(tblBuku.getSelectedRow(), 0).toString()+"'";
            
            // Eksekusi query ke database
            int baris = stmt.executeUpdate(sql);
            
            // Jika database berpengaruh
            // Tampilkan pesan berhasil dan sebaliknya
            if(baris > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil Dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal Dihapus!");

            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error :"+e);
        }
    }
    
    // Clear semua field
    private void clear_data() {
        
        // Unset semua field
        txtJudulBuku.setText("");
        txtPenerbit.setText("");
        txtPenulis.setText("");
        txtDeskripsi.setText("");
        cbTahun.setSelectedItem(1);
        txtStok.setText("");
    }
    
    // Method untuk mendapatkan id admin
    private int getId(String x) {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Set id_admin = 0
        int id_admin = 0;
        
        try {
            
            // Membuat statement
            Statement stmt = kon.createStatement();
      
            // Query sql
            String sql = "SELECT id_admin FROM admin WHERE nama_admin='"+x+"'";
            
            // Eksekusi query ke database
            ResultSet rs = stmt.executeQuery(sql);
            
            // Mendapatkan hasil dari query
            // Id admin diisi dari hasil query
            while(rs.next()) {
                id_admin = rs.getInt("id_admin");
            }
                    
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        // Kembalikan nilai id_admin
        return id_admin;
    }
    
    // Method untuk mengecek validasi apakah semua field sudah diisi
    private boolean cek_validasi() {
        
        // Set nilai validasi = false
        boolean validasi = false;
        
        // Jika semua field belum diisi, tampilkan pesan
        if(txtJudulBuku.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Judul Buku wajib diisi!");
            txtJudulBuku.requestFocus();
        } else if(txtPenerbit.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penerbit Buku wajib diisi!");
            txtPenerbit.requestFocus();
        } else if(txtPenulis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Penulis harus diisi!");
            txtPenulis.requestFocus();
        } else if(txtDeskripsi.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Deskripsi buku wajib diisi!");
            txtDeskripsi.requestFocus();
        } else if(txtStok.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Stok wajib diisi!");
            txtStok.requestFocus();
        } else {
            
            // Jika semua field sudah diisi set validasi menjadi true
            validasi = true;
        }
           
        // Kembalikan nilai validasi
        return validasi;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblJudul = new javax.swing.JLabel();
        lblPenerbit = new javax.swing.JLabel();
        lblPenulis = new javax.swing.JLabel();
        lblDeskripsi = new javax.swing.JLabel();
        lblTahunTerbit = new javax.swing.JLabel();
        lblTanggalDitambahkan = new javax.swing.JLabel();
        lblIDAdmin = new javax.swing.JLabel();
        txtJudulBuku = new javax.swing.JTextField();
        txtPenerbit = new javax.swing.JTextField();
        txtPenulis = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDeskripsi = new javax.swing.JTextArea();
        cbTahun = new javax.swing.JComboBox<>();
        txtTanggal = new javax.swing.JTextField();
        txtIdAdmin = new javax.swing.JTextField();
        txtNamaAdmin = new javax.swing.JTextField();
        btnKembali = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBuku = new javax.swing.JTable();
        btnSemua = new javax.swing.JButton();
        btnAda = new javax.swing.JButton();
        btnDihapus = new javax.swing.JButton();
        btnPrintBuku = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        txtStok = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(1366, 632));
        setPreferredSize(new java.awt.Dimension(1366, 632));

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FORM PENGELOLAAN DATA BUKU");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setPreferredSize(new java.awt.Dimension(1320, 43));

        lblJudul.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblJudul.setText("Judul Buku");

        lblPenerbit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPenerbit.setText("Penerbit");

        lblPenulis.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPenulis.setText("Penulis");

        lblDeskripsi.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblDeskripsi.setText("Deskripsi");

        lblTahunTerbit.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTahunTerbit.setText("Tahun Terbit");

        lblTanggalDitambahkan.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblTanggalDitambahkan.setText("Tanggal Ditambahkan");

        lblIDAdmin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblIDAdmin.setText("ID Admin");

        txtDeskripsi.setColumns(20);
        txtDeskripsi.setRows(5);
        jScrollPane1.setViewportView(txtDeskripsi);

        cbTahun.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020" }));

        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnTambah.setText("TAMBAH");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("EDIT");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("HAPUS");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        tblBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBukuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblBuku);

        btnSemua.setText("SEMUA");
        btnSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSemuaActionPerformed(evt);
            }
        });

        btnAda.setText("ADA");
        btnAda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdaActionPerformed(evt);
            }
        });

        btnDihapus.setText("DIHAPUS");
        btnDihapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDihapusActionPerformed(evt);
            }
        });

        btnPrintBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/print.png"))); // NOI18N
        btnPrintBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintBukuActionPerformed(evt);
            }
        });

        txtCari.setText("Cari Nama Buku disini");
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

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Stok");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblDeskripsi)
                                        .addComponent(lblTahunTerbit)
                                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(29, 29, 29)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtPenerbit)
                                                        .addComponent(txtPenulis)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtJudulBuku)))))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(119, 119, 119)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGap(21, 21, 21))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblTanggalDitambahkan)
                                        .addComponent(lblIDAdmin))
                                    .addGap(41, 41, 41)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(27, 27, 27)
                                            .addComponent(txtNamaAdmin))
                                        .addComponent(txtTanggal))))
                            .addComponent(lblPenulis)
                            .addComponent(lblPenerbit)
                            .addComponent(lblJudul))
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSemua)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDihapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPrintBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 789, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJudul, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtJudulBuku, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPenerbit)
                            .addComponent(txtPenerbit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPenulis)
                            .addComponent(txtPenulis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDeskripsi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTahunTerbit)
                            .addComponent(cbTahun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTanggalDitambahkan)
                            .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDAdmin)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtIdAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNamaAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnPrintBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCari, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSemua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDihapus, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                        .addGap(22, 22, 22))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // Jika semua field sudah diisi
        if(cek_validasi()) {
            tambah_buku();
            load_data_buku();
            clear_data();
        }     
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // Hapus jinternalframe dan kembali ke menu utama admin
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void tblBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBukuMouseClicked

        // Mendapatkan baris yang dipilih 
        int baris = tblBuku.getSelectedRow();
        
        // Set variabel yang diambil dari baris tabel yang diklik
        String judul_buku = tblBuku.getValueAt(baris, 1).toString();
        String penerbit = tblBuku.getValueAt(baris, 2).toString();
        String penulis = tblBuku.getValueAt(baris, 3).toString();
        String deskripsi = tblBuku.getValueAt(baris, 4).toString();
        String tahun_terbit = tblBuku.getValueAt(baris, 5).toString();
        String date_created = tblBuku.getValueAt(baris, 6).toString();
        String stok = tblBuku.getValueAt(baris, 7).toString();
        String nama_admin = tblBuku.getValueAt(baris, 8).toString();
        
        // Mengisi field pada form dengan data tabel yang dipilih
        txtJudulBuku.setText(judul_buku);
        txtPenerbit.setText(penerbit);
        txtPenulis.setText(penulis);
        txtDeskripsi.setText(deskripsi);
        cbTahun.setSelectedItem(tahun_terbit);
        txtStok.setText(stok);
        txtTanggal.setText(date_created);
        txtIdAdmin.setText(Integer.toString(getId(nama_admin)));
        txtNamaAdmin.setText(nama_admin);
       
        // Tombol tambah data dimatikan
        btnTambah.setEnabled(false);
        btnEdit.setEnabled(true);
        btnHapus.setEnabled(true);
    }//GEN-LAST:event_tblBukuMouseClicked

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // Jika field sudah disii semua
        String status = tblBuku.getValueAt(tblBuku.getSelectedRow(), 9).toString();
        
        if(cek_validasi()) {
            if(status.equals("DIHAPUS")) {
                JOptionPane.showMessageDialog(null, "Buku sudah dihapus sebelumnya! Tidak bisa diedit!");
            } else {
                edit_buku();
                load_data_buku();
                clear_data();
            } 
        } 
        
        // Tombol tambah dinyalakan
        btnTambah.setEnabled(true);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // Get status
        String status = tblBuku.getValueAt(tblBuku.getSelectedRow(), 9).toString();
        
        // Jika buku sudah dihapus
        if(status.equals("DIHAPUS")) {
            JOptionPane.showMessageDialog(null, "Buku anda sudah dihapus sebelumnya");
        } else {
            // Konfirmasi hapus
            int hapus = JOptionPane.showOptionDialog(this, 
                "Apakah anda yakin untuk menghapus data ini ?", null, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
        
            if(hapus == JOptionPane.YES_OPTION) {
                if(cek_validasi()) {
                hapus_buku();
                load_data_buku();
                clear_data();
                btnHapus.setEnabled(false);
                }
            }
        }
        
    }//GEN-LAST:event_btnHapusActionPerformed

    private void txtCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCariMouseClicked
        
        txtCari.setText("");
    }//GEN-LAST:event_txtCariMouseClicked

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        // Load data ke tabel berdasarkan pencarian
        load_buku_cari();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSemuaActionPerformed
        // Load data tabel sesuai dengan filter ( semua )
        load_data_buku();
    }//GEN-LAST:event_btnSemuaActionPerformed

    private void btnAdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdaActionPerformed
        
        // Load data tabel sesuai dengan filter ( status = ada )
        load_buku_filter("ADA");
    }//GEN-LAST:event_btnAdaActionPerformed

    private void btnDihapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDihapusActionPerformed
        // Load data tabel sesuai dengan filter ( dihapus )
        load_buku_filter("DIHAPUS");
    }//GEN-LAST:event_btnDihapusActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        clear_data();
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        btnTambah.setEnabled(true);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPrintBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintBukuActionPerformed

        // Koneksi ke database
        
        Connection kon = Koneksi.koneksiDB();
       
        try {
            // Mendapatkan path dari file laporan
            String laporanDir = "/laporan/buku/laporanBuku.jasper"; 

            InputStream fileLaporan = null;
            fileLaporan = getClass().getResourceAsStream(laporanDir);

            // Parameter
            HashMap param = new HashMap(); 

            JasperPrint print = JasperFillManager.fillReport(fileLaporan, param, kon);
            JasperViewer.viewReport(print, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
  
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btnPrintBukuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAda;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnDihapus;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPrintBuku;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSemua;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cbTahun;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDeskripsi;
    private javax.swing.JLabel lblIDAdmin;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblPenerbit;
    private javax.swing.JLabel lblPenulis;
    private javax.swing.JLabel lblTahunTerbit;
    private javax.swing.JLabel lblTanggalDitambahkan;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblBuku;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextArea txtDeskripsi;
    private javax.swing.JTextField txtIdAdmin;
    private javax.swing.JTextField txtJudulBuku;
    private javax.swing.JTextField txtNamaAdmin;
    private javax.swing.JTextField txtPenerbit;
    private javax.swing.JTextField txtPenulis;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables
}
