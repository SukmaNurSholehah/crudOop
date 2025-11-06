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
public class product extends koneksi{
    private int productId, productCategory, productPrice;
    private String productName, productDescription;
    private final Connection koneksi ;
    private PreparedStatement ps;
    private Statement st;
    private ResultSet rs;
    private String query;
    
    public product(){
        koneksi = super.configDB();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(int productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
   
    public void TambahProduct(){
     query = "INSERT INTO product VALUES(?,?,?,?,?)"; 
        try {
            ps = koneksi.prepareStatement(query);
            ps.setInt(1, productId);
            ps.setString(2, productName);
            ps.setInt(3, productCategory);
            ps.setString(4, productDescription);
            ps.setInt(5, productPrice);
           
            
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!!!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan!!!");
        }
    }
   
    public void UbahProduct(){
      query = "UPDATE product " 
                    + "SET productName = ?, " 
                    + "productCategory = ?, " 
                    + "productDescription = ?, " 
                    + "productPrice= ? " 
                    + "WHERE productId = ?"; 
             
        try { 
            ps = koneksi.prepareStatement(query); 
            ps.setString(1, productName); 
            ps.setInt(2, productCategory); 
            ps.setString(3, productDescription); 
            ps.setInt(4, productPrice); 
            ps.setInt(5, productId); 
            ps.executeUpdate(); 
            ps.close(); 
            JOptionPane.showMessageDialog(null, "Data berhasil Diubah"); 
            } catch (SQLException sQLException) { 
            JOptionPane.showMessageDialog(null, "Data Gagal Diubah"); 
            } 
    } 

    public void HapusProduct(){
        query = "DELETE FROM product WHERE productId = ?"; 
        try { 
            ps = koneksi.prepareStatement(query); 
            ps.setInt(1, productId); 
             
            ps.executeUpdate(); 
            ps.close(); 
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus"); 
        } catch (SQLException sQLException) { 
            JOptionPane.showMessageDialog(null, "Data gagal dihapus"); 
        }
    }
   
    public ResultSet TampilProduct(){
        query = "SELECT p.productId AS ID, "
                + "p.productName AS Name, "
                + "c.categoryName AS Category, "
                + "p.productDescription AS Description, "
                + "p.productPrice AS Price "
                + "FROM product p "
                + "JOIN category c ON p.productCategory = c.categoryId";
         try { 
             st = koneksi.createStatement();             
             rs = st.executeQuery(query); 
         } catch (SQLException sQLException) { 
             JOptionPane.showMessageDialog(null, "Data Gagal Tampil"); 
         }

        return rs;
    }
    public ResultSet autoID(){ 
        query = "SELECT productId AS ID FROM product"
                + " ORDER BY productId DESC LIMIT 1"; 
            try { 
            st = koneksi.createStatement();             
            rs = st.executeQuery(query); 
            } catch (SQLException sQLException) { 
                JOptionPane.showMessageDialog(null, "Data Gagal Tampil"); 
            } 
            return rs; 
    }     
 } 

    
    

