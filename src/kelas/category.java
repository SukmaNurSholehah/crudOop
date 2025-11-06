/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kelas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Sukma Nur
 */
public class category extends koneksi{
    private int categoryId;
    private String categoryName;
    
    private final Connection koneksi ;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    public category(){
        koneksi = super.configDB();
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public void TambahCategory(){
      query = "INSERT INTO category VALUES(?,?)"; 
       try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setString(2, categoryName);
           
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan!!!");
        }
    }
    public void UbahCategory(){
     query = "UPDATE category SET " 
              + "categoryName = ?" 
              + "WHERE categoryId = ?"; 
             
        try { 
        ps = koneksi.prepareStatement(query); 
        ps.setString(1, categoryName); 
        ps.setInt(2, categoryId); 
        ps.executeUpdate(); 
        ps.close(); 
        JOptionPane.showMessageDialog(null, "Data berhasil Diubah"); 
        } catch (SQLException e) { 
        JOptionPane.showMessageDialog(null, "Data Gagal Diubah"); 
        }
    }
    public void HapusCategory(){
       query = "DELETE FROM category WHERE categoryId = ?"; 
        try { 
            ps = koneksi.prepareStatement(query); 
            ps.setInt(1, categoryId); 
             
            ps.executeUpdate(); 
            ps.close(); 
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus"); 
        } catch (SQLException sQLException) { 
            JOptionPane.showMessageDialog(null, "Data gagal dihapus"); 
        } 
    }
    public ResultSet TampilCategory(){
        query = "SELECT * FROM category"; 
        try { 
            st = koneksi.createStatement();             
            rs = st.executeQuery(query); 
        } catch (SQLException sQLException) { 
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil"); 
        }
        return rs;
    }
    
    public ResultSet tampilComBox(){ 
        query = "SELECT categoryName FROM category"; 
        try { 
        st = koneksi.createStatement();             
        rs = st.executeQuery(query); 
        } catch (SQLException sQLException) { 
        JOptionPane.showMessageDialog(null, "Data Gagal Tampil"); 
        } 
        return rs;     
    } 
    
    public ResultSet Konversi() { 
        query = "SELECT categoryId FROM category"
                + " WHERE categoryName = ?"; 
        try { 
        ps = koneksi.prepareStatement(query); 
        ps.setString(1, categoryName); 
        rs = ps.executeQuery();  
       
        } catch (SQLException sQLException) { 
        
        } 
        return rs; 
    } 
    
    public ResultSet autoID(){ 
        query = "SELECT categoryId AS ID FROM category "
                + "ORDER BY categoryId DESC LIMIT 1"; 
        try { 
        st = koneksi.createStatement();             
        rs = st.executeQuery(query); 
        } catch (SQLException sQLException) { 
            JOptionPane.showMessageDialog(null, "Data Gagal Tampil"); 
        } 
        return rs; 
    } 

}

