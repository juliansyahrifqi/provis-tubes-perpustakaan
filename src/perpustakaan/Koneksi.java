/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaan;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author oyeaaa
 */
public class Koneksi {
    
    Connection Koneksi = null;
    
    public static Connection koneksiDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/perpustakaan", "root", "");
            return koneksi;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Koneksi Ke Database" + e);
            return null;
        }
    } 
}
