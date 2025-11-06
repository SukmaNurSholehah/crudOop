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
public class user extends koneksi {
    private String userName, userEmail, userPassword, userFullName;
    private int userStatus;
    private final Connection koneksi;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public user(){
        koneksi = super.configDB();
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserFullName() {
        return userFullName;
    }
    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }
    public int getUserStatus() {
        return userStatus;
    }
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
    
    public void TambahUser(){
       query = "INSERT INTO user VALUES(?,?,MD5(?),?,?) "; 
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userEmail);
            ps.setString(3, userPassword);
            ps.setString(4, userFullName);
            ps.setInt(5, userStatus);
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan!!!");
        }
    }
    
    public void UbahUser(){
        if (userPassword.equals("")) { 
            query = "UPDATE user SET userEmail = ?, "
                    + "userFullName = ?, userStatus = ?"
                    + " WHERE userName = ? "; 
             
            try { 
            ps = koneksi.prepareStatement(query); 
            ps.setString(1, userEmail); 
            ps.setString(2, userFullName); 
            ps.setInt(3, userStatus);
            ps.setString(4, userName); 
             
            ps.executeUpdate(); 
            ps.close(); 
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!!!"); 
             
            } catch (SQLException e) { 
                JOptionPane.showMessageDialog(null, "Data gagal diubah!!!"); 
            } 
       
        }else{ 
        query = "UPDATE user SET userEmail = ?, userPassword = md5(?), "
                + "userFullName = ?, userStatus = ? "
                + "WHERE userName = ? "; 
             
            try { 
            ps = koneksi.prepareStatement(query); 
             
            ps.setString(1, userEmail); 
            ps.setString(2, userPassword); 
            ps.setString(3, userFullName); 
            ps.setInt(4, userStatus); 
            ps.setString(5, userName);
            
            ps.executeUpdate(); 
            ps.close(); 
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!!!"); 
            } catch (SQLException e) { 
                JOptionPane.showMessageDialog(null, "Data gagal diubah!!!"); 
            }     
        }
        /*query = "UPDATE user SET userEmail = ?, userPassword = md5(?), "
                + "userFullName = ?, userStatus = ? "
                + "WHERE userName = ? "; 
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userEmail);
            ps.setString(2, userFullName);
            ps.setInt(3, userStatus);
            ps.setString(4, userName);
            
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil diubah!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal diubah!!!");
        }*/
    }
    
    public void HapusUser(){
         query = "DELETE FROM user WHERE  userName = ?"; 
        try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
     
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal dihapus!!!");
        }
    }
    public ResultSet TampilUser(){
        query = "SELECT * FROM user";
        try {
            st = koneksi.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditampilkan!!!");
        }
        return rs;
    }
    
    public void login(){
         query = "SELECT * FROM user WHERE userName = ? "
                 + "AND userPassword = MD5(?)"; 
         try {
            ps = koneksi.prepareStatement(query);
            ps.setString(1, userName);
            ps.setString(2, userPassword);
           
            rs = ps.executeQuery();
             if (rs.next()) {
                 sesion.setUsername(rs.getString("userName"));
                 sesion.setEmail(rs.getString("userEmail"));
                 sesion.setFullname(rs.getString("userFullName"));
                 sesion.setStatus("Aktif");
                
             } else{
                sesion.setStatus("Tidak Aktif");
                JOptionPane.showMessageDialog(null, "Gagal Login!!!");
             }
            
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Gagal Login!!!");
             System.out.println(e);
        }
    }
    
    public void logout(){
        sesion.setUsername("");
        sesion.setEmail("");
        sesion.setFullname("");
        sesion.setStatus("");
        
    }
}
    
    

