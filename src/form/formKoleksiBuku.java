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
public class formKoleksiBuku extends javax.swing.JInternalFrame {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    
    // Mendapatkan tanggal sekarang
    String tanggalSekarang = dateFormat.format(date);
    
    /**
     * Creates new form formKoleksiBuku
     */
    public formKoleksiBuku() {
        initComponents();
        
        load_koleksi_buku();
        btnPinjam.setEnabled(false);
    }
    
    // Method untuk menentukan tanggal kembali peminjaman
    private String set_tanggal_kembali() {
        
        Calendar cal = Calendar.getInstance();
        
        // Set variabel cal ke tanggal sekarang
        cal.setTime(date);
        
        // Set variabel cal menjadi 5 hari kedepan dari tanggal sekarang
        cal.add(Calendar.DATE, 5);

        // Mendapatkan tanggal kembali
        Date expirationDate = cal.getTime();
        
        // Format tanggal ke string
        String tanggal_kembali = dateFormat.format(expirationDate);

        // Kembalikan nilai tanggal_kembali
        return tanggal_kembali;
    }
    
    // Mendapatkan id_peeminjaman
    private int getID() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
                // Set no / id ke 0
                int no=0;
                
                // Mempersiapkan statement
                Statement stmt = kon.createStatement();
                
                // Query sql
                String sql = "SELECT COUNT(*) as id_peminjaman from peminjaman ";
                
                // Mengeksekusi query sql
                ResultSet rs = stmt.executeQuery(sql);

                // Tambahkan no + 1;
                if (rs.next()) {
                    no = rs.getInt("id_peminjaman") + 1;
                }

                // Kembalikan nilai no
                return no;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return 0;
        }
    }
    
    private void load_koleksi_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        // Nama header tabel
        Object tableHeader[] = {"ID BUKU", "JUDUL BUKU", "PENERBIT", "PENULIS", "DESKRIPSI", "TAHUN TERBIT"};
        DefaultTableModel data = new DefaultTableModel(null, tableHeader);
        
        // Tambahkan data ke tabel Buku
        tblKoleksiBuku.setModel(data);
            
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql
            String sql = "SELECT id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit"
                    + " FROM buku "
                    + " WHERE status = 'ADA' AND stok > 0"
                    + " ORDER BY id_buku ";
                    
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
                
                // Tambahkan data dari database ke tabel
                String d[] = {id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit};
                data.addRow(d);
            }
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e);
            formUtamaAdmin utamaAdmin = new formUtamaAdmin();
            utamaAdmin.setVisible(true);
            this.dispose();
        }
    }
    
    // Method untuk menampilkan data hasil pencarian
    private void cari_koleksi_buku() {
        
        // Koneksi ke database
       Connection kon = Koneksi.koneksiDB();

       // Nama header tabel
       Object tableHeader[] = {"ID BUKU", "JUDUL BUKU", "PENERBIT", "PENULIS", "DESKRIPSI", "TAHUN TERBIT"};
       DefaultTableModel data = new DefaultTableModel(null, tableHeader);

       // Tambahkan data ke tabel Buku
       tblKoleksiBuku.setModel(data);

       try {
           // Mempersiapkan statement
           Statement stmt = kon.createStatement();

           // Query sql
           String sql = "SELECT id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit"
                   + " FROM buku"
                   + " WHERE judul_buku LIKE '%"+txtCari.getText()+"%' AND status = 'ADA' AND stok > 0"
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

               // Tambahkan data dari database ke tabel
               String d[] = {id_buku, judul_buku, penerbit, penulis, deskripsi, tahun_terbit};
               data.addRow(d);
           } 
           
           // Eksekusi query kedua
           ResultSet rs2 = stmt.executeQuery(sql);
           
           // Jika query kedua tidak ada datanya tampilkan pesan
           if(!rs2.next()) {
               JOptionPane.showMessageDialog(null, "Data tidak ditemukan!");
               load_koleksi_buku();
           }
       } catch(Exception e) {
           JOptionPane.showMessageDialog(null, e);
           load_koleksi_buku();
       }
    }
    
    // Method untuk pinjam buku dari menu koleksi buku
    private void pinjam_buku() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            
            // Mendapatkan baris yang dipilih 
            int baris = tblKoleksiBuku.getSelectedRow();
        
            // Set variabel yang diambil dari baris tabel yang diklik
            int id_peminjaman = getID();
            String id_buku = tblKoleksiBuku.getValueAt(baris, 0).toString();
            String tanggal_pinjam = tanggalSekarang;
            String tanggal_kembali = set_tanggal_kembali();
            
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query sql pertama
            String sql = "INSERT INTO peminjaman VALUES (default, '"+formLoginAnggota.getId()+"')";
                 
            // Query sql kedua
            String sql2 = "INSERT INTO detail_peminjaman "
                    + " VALUES ('"+id_peminjaman+"','"+id_buku
                    +"','"+tanggal_pinjam
                    +"','"+tanggal_kembali
                    +"', 'DIPINJAM');";
            
            // Update stok buku
            
            String sql3 = "UPDATE buku SET stok = stok - 1 WHERE judul_buku = '"+tblKoleksiBuku.getValueAt(baris, 1).toString()+"'";

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
    
    // Cek pinjaman buku sudah ada atau belum
    
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
                    + "AND id_buku = '"+tblKoleksiBuku.getValueAt(tblKoleksiBuku.getSelectedRow(), 0).toString()+"'  AND status = 'DIPINJAM'";
                 
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

        lblTitle = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKoleksiBuku = new javax.swing.JTable();
        btnPinjam = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        txtCari = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setTitle("DAFTAR KOLEKSI BUKU PERPUSTAKAAN");
        setPreferredSize(new java.awt.Dimension(1366, 632));

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("DAFTAR KOLEKSI BUKU PERPUSTAKAAN");
        lblTitle.setPreferredSize(new java.awt.Dimension(1366, 15));

        tblKoleksiBuku.setModel(new javax.swing.table.DefaultTableModel(
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
        tblKoleksiBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKoleksiBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKoleksiBuku);

        btnPinjam.setText("PINJAM BUKU");
        btnPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinjamActionPerformed(evt);
            }
        });

        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

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

        btnRefresh.setText("REFRESH");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(134, 134, 134))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(549, 549, 549))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1352, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cari_koleksi_buku();
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCariMouseClicked
        txtCari.setText("");
    }//GEN-LAST:event_txtCariMouseClicked

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        load_koleksi_buku();
        btnPinjam.setEnabled(false);
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinjamActionPerformed
        
        // Konfirmasi pinjam buku
        // Jika buku yang akan dipinjam sudah dipinjam
        if(cek_peminjaman()) {
            JOptionPane.showMessageDialog(null, "Buku yang dipilih sudah dipinjam oleh anda");
        } else {
            int pinjam = JOptionPane.showOptionDialog(this, 
                "Anda yakin pinjam buku ini?", null, JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, null, null);
            
            if(pinjam == JOptionPane.YES_OPTION) {
                pinjam_buku();  
            }
        }   
    }//GEN-LAST:event_btnPinjamActionPerformed

    private void tblKoleksiBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKoleksiBukuMouseClicked
        btnPinjam.setEnabled(true);
    }//GEN-LAST:event_tblKoleksiBukuMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPinjam;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTable tblKoleksiBuku;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
