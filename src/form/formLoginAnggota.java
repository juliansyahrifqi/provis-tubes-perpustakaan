package form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import perpustakaan.Koneksi;
import perpustakaan.menuLogin;

/**
 *
 * @author oyeaaa
 */
public class formLoginAnggota extends javax.swing.JFrame {

    static int idAnggota;
    static String namaAnggota;
    static String status;
    
    /**
     * Creates new form formLoginAnggota
     */
    public formLoginAnggota() {
        initComponents();
        
        txtUsername.requestFocus();
    }
    
    // Method untuk melakukan login
    public void do_login() {
        
        // Koneksi ke database
        Connection kon = Koneksi.koneksiDB();
        
        try {
            // Mempersiapkan statement
            Statement stmt = kon.createStatement();
            
            // Query database / sql
            String sql = "SELECT * FROM anggota "
                    + "WHERE username = '"+txtUsername.getText()
                    +"' AND password = '"+txtPassword.getText()+"'";
            
            // Eksekusi query ke database
            ResultSet rs = stmt.executeQuery(sql);
            
            // Jika data ditemukan, lanjutkan ke menu utama anggota dan statusnya aktif
            if(rs.next()) {
                idAnggota = rs.getInt("id_anggota");
                namaAnggota = rs.getString("nama_anggota");
                status = rs.getString("status");
                
                if(status.equals("TIDAK AKTIF")) {
                    JOptionPane.showMessageDialog(null, "Akun anda sudah tidak aktif!");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    
                    txtUsername.requestFocus();
                } else {
                    formUtamaAnggota formAnggota = new formUtamaAnggota();
                    formAnggota.show();
                    this.dispose();
                } 
            } else {   
                JOptionPane.showMessageDialog(null, "Username atau password anda salah!");
                txtUsername.setText("");
                txtPassword.setText("");
                txtUsername.requestFocus();
            }
        } catch(SQLException e) {
            JOptionPane.showConfirmDialog(null, "Error: "+e);
        } catch(Exception er) {
            JOptionPane.showMessageDialog(null, er);
        }
    }
    
    // Method untuk mendapatkan id anggota yang login ( session )
    public static int getId() {
        return idAnggota;
    }
    
    // Method untuk mendapatkan nama anggota yang login 
    public static String getNama() {
        return namaAnggota;
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
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN ANGGOTA");

        lblTitle.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        lblTitle.setText("LOGIN ANGGOTA");

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUsername.setText("Username");

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblPassword.setText("Password");

        btnLogin.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        btnKembali.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblTitle))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblPassword)
                            .addComponent(lblUsername)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword))))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitle)
                .addGap(27, 27, 27)
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        // Validasi username dan password
        // Jika field username dan password masih kosong tampilkan pesan
        if(txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username Wajib Diisi!");
            txtUsername.requestFocus();
        } else if(txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password Wajib Diisi!");
            txtPassword.requestFocus();
        } else {
            do_login();
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
       
        // Kembali ke menu login
        menuLogin menuLogin = new menuLogin();
        
        menuLogin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

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
            java.util.logging.Logger.getLogger(formLoginAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLoginAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLoginAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLoginAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLoginAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}