package form;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import perpustakaan.menuLogin;

/**
 *
 * @author oyeaaa
 */
public class formUtamaAnggota extends javax.swing.JFrame {

    /**
     * Creates new form formUtamaAnggota
     */
    public formUtamaAnggota() {
        
        initComponents();
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        lblGreet.setText("SELAMAT DATANG, " + formLoginAnggota.getNama());
    }
    
    // Method untuk logout
    private void do_logout() {
        menuLogin menuLogin = new menuLogin();
        
        menuLogin.setVisible(true);
        this.dispose();
        
        JOptionPane.showMessageDialog(null, "Anda berhasil logout");
    }
    
    // Method untuk menampilkan halaman my profile
    private void show_profile() {
        // Menghapus komponen lain di panel utama
        panelUtama.removeAll();
        panelUtama.repaint();
        
        // Instance class form my profile
        formProfile myProfile = new formProfile();
        
        // Mengambil ukuran layar utama dan layar form my profile
        Dimension layarUtama = this.getSize();
        Dimension layarProfile = myProfile.getSize();
        
        // Set posisi form my profile ke center
        myProfile.setLocation((layarUtama.width - layarProfile.width)/2, (layarUtama.height - layarProfile.height)/4);
         
        // Menambahkan halaman form my profile di panel utama
        panelUtama.add(myProfile);
        myProfile.setVisible(true);
        
        // Menambahkan kembali komponen yang ada di panel utama
        panelUtama.add(lblGreet);
        panelUtama.add(btnProfile);
        panelUtama.add(btnKoleksiBuku);
        panelUtama.add(btnPinjamBuku);
        panelUtama.add(btnDaftarPinjaman);
        panelUtama.add(btnHistoriPinjam);
        panelUtama.add(btnLogout);
    }
    
    // Method untuk menampilkan halaman koleksi buku
    private void show_buku() {
        // Menghapus komponen lain di panel utama
        panelUtama.removeAll();
        panelUtama.repaint();
        
        // Instance class form koleksi buku
        formKoleksiBuku koleksiBuku = new formKoleksiBuku();
        
        // Mengambil ukuran layar utama dan layar form koleksi buku
        Dimension layarUtama = this.getSize();
        Dimension layarKoleksi = koleksiBuku.getSize();
        
        // Set posisi form koleksi buku ke center
        koleksiBuku.setLocation((layarUtama.width - layarKoleksi.width)/2, (layarUtama.height - layarKoleksi.height)/4);
        
        // Menambahkan halaman form koleksi buku di panel utama
        panelUtama.add(koleksiBuku);
        koleksiBuku.setVisible(true);
        
        // Menambahkan kembali komponen yang ada di panel utama
        panelUtama.add(lblGreet);
        panelUtama.add(btnProfile);
        panelUtama.add(btnKoleksiBuku);
        panelUtama.add(btnPinjamBuku);
        panelUtama.add(btnDaftarPinjaman);
        panelUtama.add(btnHistoriPinjam);
        panelUtama.add(btnLogout);
    }
    
    // Method untuk menampilkan form pinjam buku
    private void show_pinjam_buku() {
        // Menghapus komponen lain di panel utama
        panelUtama.removeAll();
        panelUtama.repaint();
        
        // Instance class form pinjam buku
        formPinjamBuku pinjamBuku = new formPinjamBuku();
        
        // Mengambil ukuran layar utama dan layar form pinjam buku
        Dimension layarUtama = this.getSize();
        Dimension layarPinjam = pinjamBuku.getSize();
        
        // Set posisi form pinjam buku ke center
        pinjamBuku.setLocation((layarUtama.width - layarPinjam.width)/2, (layarUtama.height - layarPinjam.height)/4);
        
        // Menambahkan halaman form pinjam buku di panel utama
        panelUtama.add(pinjamBuku);
        pinjamBuku.setVisible(true);
        
        // Menambahkan kembali komponen yang ada di panel utama
        panelUtama.add(lblGreet);
        panelUtama.add(btnProfile);
        panelUtama.add(btnKoleksiBuku);
        panelUtama.add(btnPinjamBuku);
        panelUtama.add(btnDaftarPinjaman);
        panelUtama.add(btnHistoriPinjam);
        panelUtama.add(btnLogout);
    }
    
    // Method untuk menampilkan histori peminjaman
    private void show_histori_peminjaman() {
        // Menghapus komponen lain di panel utama
        panelUtama.removeAll();
        panelUtama.repaint();
        
        // Instance class form histori peminjaman
        formHistoriPeminjaman historiPeminjaman = new formHistoriPeminjaman();
        
        // Mengambil ukuran layar utama dan layar form histori peminjaman
        Dimension layarUtama = this.getSize();
        Dimension layarHistori = historiPeminjaman.getSize();
        
        // Set posisi form histori peminjaman ke center
        historiPeminjaman.setLocation((layarUtama.width - layarHistori.width)/2, (layarUtama.height - layarHistori.height)/4);
        
        // Menambahkan halaman form histori peminjaman di panel utama
        panelUtama.add(historiPeminjaman);
        historiPeminjaman.setVisible(true);
        
        // Menambahkan kembali komponen yang ada di panel utama
        panelUtama.add(lblGreet);
        panelUtama.add(btnProfile);
        panelUtama.add(btnKoleksiBuku);
        panelUtama.add(btnPinjamBuku);
        panelUtama.add(btnDaftarPinjaman);
        panelUtama.add(btnHistoriPinjam);
        panelUtama.add(btnLogout);
    }
    
    // Method untuk menampilkan data pinjaman saya
    private void show_data_pinjaman() {
        // Menghapus komponen lain di panel utama
        panelUtama.removeAll();
        panelUtama.repaint();
        
        // Instance class form pinjaman saya
        formPinjamanSaya pinjamanSaya = new formPinjamanSaya();
        
        // Mengambil ukuran layar utama dan layar form pinjaman saya
        Dimension layarUtama = this.getSize();
        Dimension layarPinjamanSaya = pinjamanSaya.getSize();
        
        // Set posisi form pinjaman saya ke center
        pinjamanSaya.setLocation((layarUtama.width - layarPinjamanSaya.width)/2, (layarUtama.height - layarPinjamanSaya.height)/4);
        
        // Menambahkan halaman form pinjaman saya di panel utama
        panelUtama.add(pinjamanSaya);
        pinjamanSaya.setVisible(true);
        
        // Menambahkan kembali komponen yang ada di panel utama
        panelUtama.add(lblGreet);
        panelUtama.add(btnProfile);
        panelUtama.add(btnKoleksiBuku);
        panelUtama.add(btnPinjamBuku);
        panelUtama.add(btnDaftarPinjaman);
        panelUtama.add(btnHistoriPinjam);
        panelUtama.add(btnLogout);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUtama = new javax.swing.JDesktopPane();
        btnProfile = new javax.swing.JButton();
        btnKoleksiBuku = new javax.swing.JButton();
        btnPinjamBuku = new javax.swing.JButton();
        btnDaftarPinjaman = new javax.swing.JButton();
        btnHistoriPinjam = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        lblGreet = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuAkun = new javax.swing.JMenu();
        menuProfile = new javax.swing.JMenuItem();
        menuLogout = new javax.swing.JMenuItem();
        menuData = new javax.swing.JMenu();
        menuKoleksiBuku = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        formPeminjaman = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        formHistoriPeminjaman = new javax.swing.JMenuItem();
        menuPinjamBuku = new javax.swing.JMenu();
        formPinjamBuku = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MENU UTAMA ANGGOTA");

        panelUtama.setPreferredSize(new java.awt.Dimension(1366, 632));

        btnProfile.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnProfile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/anggota.png"))); // NOI18N
        btnProfile.setText("My Profile");
        btnProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProfileActionPerformed(evt);
            }
        });

        btnKoleksiBuku.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnKoleksiBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/buku.png"))); // NOI18N
        btnKoleksiBuku.setText("Daftar Koleksi Perpustakaan");
        btnKoleksiBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKoleksiBukuActionPerformed(evt);
            }
        });

        btnPinjamBuku.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnPinjamBuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add-book.png"))); // NOI18N
        btnPinjamBuku.setText("Pinjam Buku");
        btnPinjamBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPinjamBukuActionPerformed(evt);
            }
        });

        btnDaftarPinjaman.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnDaftarPinjaman.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pinjaman-buku.png"))); // NOI18N
        btnDaftarPinjaman.setText("Pinjaman Buku Saya");
        btnDaftarPinjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaftarPinjamanActionPerformed(evt);
            }
        });

        btnHistoriPinjam.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnHistoriPinjam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/histori.png"))); // NOI18N
        btnHistoriPinjam.setText("Histori Peminjaman Buku");
        btnHistoriPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoriPinjamActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        lblGreet.setFont(new java.awt.Font("American Captain", 0, 48)); // NOI18N
        lblGreet.setForeground(new java.awt.Color(255, 255, 255));
        lblGreet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGreet.setPreferredSize(new java.awt.Dimension(1366, 15));

        panelUtama.setLayer(btnProfile, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(btnKoleksiBuku, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(btnPinjamBuku, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(btnDaftarPinjaman, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(btnHistoriPinjam, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(btnLogout, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelUtama.setLayer(lblGreet, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelUtamaLayout = new javax.swing.GroupLayout(panelUtama);
        panelUtama.setLayout(panelUtamaLayout);
        panelUtamaLayout.setHorizontalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGreet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addGap(310, 310, 310)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnProfile, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(btnPinjamBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(btnHistoriPinjam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKoleksiBuku, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDaftarPinjaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelUtamaLayout.setVerticalGroup(
            panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUtamaLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblGreet, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKoleksiBuku, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPinjamBuku, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(btnDaftarPinjaman, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45)
                .addGroup(panelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHistoriPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57))
        );

        menuAkun.setText("Akun");

        menuProfile.setText("My Profile");
        menuProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuProfileActionPerformed(evt);
            }
        });
        menuAkun.add(menuProfile);

        menuLogout.setText("Logout");
        menuLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuLogoutActionPerformed(evt);
            }
        });
        menuAkun.add(menuLogout);

        menuBar.add(menuAkun);

        menuData.setText("Data");

        menuKoleksiBuku.setText("Daftar Koleksi Perpustakaan");
        menuKoleksiBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuKoleksiBukuActionPerformed(evt);
            }
        });
        menuData.add(menuKoleksiBuku);
        menuData.add(jSeparator1);

        formPeminjaman.setText("Data Buku Yang Dipinjam");
        formPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formPeminjamanActionPerformed(evt);
            }
        });
        menuData.add(formPeminjaman);
        menuData.add(jSeparator2);

        formHistoriPeminjaman.setText("Histori Peminjaman");
        formHistoriPeminjaman.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formHistoriPeminjamanActionPerformed(evt);
            }
        });
        menuData.add(formHistoriPeminjaman);

        menuBar.add(menuData);

        menuPinjamBuku.setText("Pinjam");
        menuPinjamBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPinjamBukuActionPerformed(evt);
            }
        });

        formPinjamBuku.setText("Pinjam Buku");
        formPinjamBuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formPinjamBukuActionPerformed(evt);
            }
        });
        menuPinjamBuku.add(formPinjamBuku);

        menuBar.add(menuPinjamBuku);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLogoutActionPerformed
        // Jika pengguna melakukan logout, lanjutkan ke menu login
        
        do_logout();
    }//GEN-LAST:event_menuLogoutActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed

        do_logout();
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void menuKoleksiBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuKoleksiBukuActionPerformed
        show_buku();
    }//GEN-LAST:event_menuKoleksiBukuActionPerformed

    private void menuProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuProfileActionPerformed
        show_profile();
    }//GEN-LAST:event_menuProfileActionPerformed

    private void btnProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProfileActionPerformed
        show_profile();
    }//GEN-LAST:event_btnProfileActionPerformed

    private void btnKoleksiBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKoleksiBukuActionPerformed
        show_buku();
    }//GEN-LAST:event_btnKoleksiBukuActionPerformed

    private void menuPinjamBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPinjamBukuActionPerformed
        show_pinjam_buku();
    }//GEN-LAST:event_menuPinjamBukuActionPerformed

    private void btnPinjamBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPinjamBukuActionPerformed
        show_pinjam_buku();
    }//GEN-LAST:event_btnPinjamBukuActionPerformed

    private void btnDaftarPinjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaftarPinjamanActionPerformed
        show_data_pinjaman();
    }//GEN-LAST:event_btnDaftarPinjamanActionPerformed

    private void btnHistoriPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoriPinjamActionPerformed
        show_histori_peminjaman();
    }//GEN-LAST:event_btnHistoriPinjamActionPerformed

    private void formHistoriPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formHistoriPeminjamanActionPerformed
        show_histori_peminjaman();
    }//GEN-LAST:event_formHistoriPeminjamanActionPerformed

    private void formPeminjamanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formPeminjamanActionPerformed
        show_data_pinjaman();
    }//GEN-LAST:event_formPeminjamanActionPerformed

    private void formPinjamBukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formPinjamBukuActionPerformed
        show_pinjam_buku();
    }//GEN-LAST:event_formPinjamBukuActionPerformed

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
            java.util.logging.Logger.getLogger(formUtamaAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formUtamaAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formUtamaAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formUtamaAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formUtamaAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDaftarPinjaman;
    private javax.swing.JButton btnHistoriPinjam;
    private javax.swing.JButton btnKoleksiBuku;
    public javax.swing.JButton btnLogout;
    private javax.swing.JButton btnPinjamBuku;
    private javax.swing.JButton btnProfile;
    private javax.swing.JMenuItem formHistoriPeminjaman;
    private javax.swing.JMenuItem formPeminjaman;
    private javax.swing.JMenuItem formPinjamBuku;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lblGreet;
    private javax.swing.JMenu menuAkun;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuData;
    private javax.swing.JMenuItem menuKoleksiBuku;
    private javax.swing.JMenuItem menuLogout;
    private javax.swing.JMenu menuPinjamBuku;
    private javax.swing.JMenuItem menuProfile;
    private javax.swing.JDesktopPane panelUtama;
    // End of variables declaration//GEN-END:variables
}
