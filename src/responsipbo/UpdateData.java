/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsipbo;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;



/**
 *
 * @author ADAM ARDI
 */
public class UpdateData extends JFrame {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/responsi_pbo";
    static final String USER = "root";
    static final String PASS = "qwerty123";
    
    Connection koneksi;
    Statement statement;
    
    JButton btnHome = new JButton("Home");
    JButton btnTambah = new JButton("Gaji");
    JButton btnData = new JButton("Data");
    JButton btnPetunjuk= new JButton("Petunjuk");
    JButton btnKaryawan = new JButton("karyawan");
   
    JButton btnSimpan = new JButton("Simpan");
    JLabel Footer = new JLabel("APLIKASI PERHITUNGAN GAJI PT. VETERAN JAYA");
    
    JLabel lID = new JLabel("ID Pegawai");
    JTextField tfID = new JTextField();
    JLabel lNama = new JLabel("Nama");
    JTextField tfNama = new JTextField();
    JLabel lAlamat = new JLabel("alamat");
    JTextField tfAlamat = new JTextField();
    JLabel lNo_hp = new JLabel("NO HP");
    JTextField tfNo_hp = new JTextField();
    JLabel lGaji = new JLabel("Gaji Pokok");
    JTextField tfGaji = new JTextField();
    JLabel lPosisi = new JLabel("Jam Lembur");
    JTextField tfPosisi = new JTextField();
   
    
    
    
    
    
    
    public UpdateData(String ID){
        
         try{
            Class.forName(JDBC_DRIVER);
            koneksi = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Koneksi Berhasil");
        }catch(ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
        setSize(850,580);
        setLocation(225,75);
        
        add(btnHome);
        btnHome.setBounds(10,20,120,70);
        add(btnTambah);
        btnTambah.setBounds(10,100,120,70);
        add(btnData);
        btnData.setBounds(10,180,120,70);
        add(btnPetunjuk);
        btnPetunjuk.setBounds(10,260,120,70);
        add(btnKaryawan);
        btnKaryawan.setBounds(700,20,120,70);
       
        add(btnSimpan);
        btnSimpan.setBounds(700,450,120,70);
        
        
        add(Footer);
        Footer.setBounds(250, 500, 600, 50);
        Footer.setFont(new Font("Arial",Font.CENTER_BASELINE, 15));
        
        add(lID);
        lID.setBounds(150,50,90,20);
        add(tfID);
        tfID.setBounds(250,50,90,20);
        tfID.setText(ID);
        add(lNama);
        lNama.setBounds(150,80,90,20);
        add(tfNama);
        tfNama.setBounds(250,80,100,20);
        add(lAlamat);
        lAlamat.setBounds(150,110,90,20);
        add(tfAlamat);
        tfAlamat.setBounds(250,110,100,20);
        add(lNo_hp);
        lNo_hp.setBounds(150,140,90,20);
        add(tfNo_hp);
        tfNo_hp.setBounds(250,140,100,20);
        add(lPosisi);
        lPosisi.setBounds(150,170,90,20);
        add(tfPosisi);
        tfPosisi.setBounds(250,170,100,20);
        add(lGaji);
        lGaji.setBounds(150,200,90,20);
        add(tfGaji);
        tfGaji.setBounds(250,200,100,20);
        
        
            try {
            String query = "SELECT * FROM `admin` WHERE `ID_A` = '" + ID + "'";
            statement = koneksi.createStatement();
            ResultSet resultSet = statement.executeQuery(query); 
            while (resultSet.next()) { 
                tfNama.setText(resultSet.getString("Nama_A")); 
                tfAlamat.setText(resultSet.getString("Alamat_A")); 
                tfNo_hp.setText(resultSet.getString("No_hp_A")); 
                tfPosisi.setText(resultSet.getString("Posisi_A"));
                tfGaji.setText(resultSet.getString("Gaji_A")); 

            
                }
            } catch (SQLException sql) {
                System.out.println(sql.getMessage());
            }
        
        
        
       
        
        
        btnHome.addActionListener((ActionEvent e) -> {
          HomeAdmin a = new HomeAdmin();
           dispose();
        });
        btnTambah.addActionListener((ActionEvent e) -> {
          TambahData b = new TambahData();
           dispose();
        });
        btnData.addActionListener((ActionEvent e) -> {
           DataAdmin b = new DataAdmin();
           dispose();
        });
        btnPetunjuk.addActionListener((ActionEvent e) -> {
          PetunjukAdmin d = new PetunjukAdmin();
           dispose();
        });
        btnKaryawan.addActionListener((ActionEvent e) -> {
         HomeKaryawan d = new HomeKaryawan();
           dispose();
        });
        
        
   
    
        btnSimpan.addActionListener((ActionEvent e) -> {
            if (tfID.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String id = tfID.getText();
                String nama = tfNama.getText();
                String posisi = tfPosisi.getText();
                String alamat = tfAlamat.getText();
                String no_hp = tfNo_hp.getText();
                long gaji = Long.parseLong(tfGaji.getText());
                
                
                
                this.updateAdmin(id,nama,alamat,no_hp,posisi,gaji);
                DataAdmin b = new DataAdmin();
                dispose();
            }
        });
        
        
        


                 
        
    }

    
    
   public void updateAdmin(String id,String nama, String alamat, String no_hp ,String posisi, long gaji) {
        try{
            String query = "UPDATE `admin` SET `Nama_A`='"+nama+"', `Alamat_A`='"+alamat+"', `No_hp_A`='"+no_hp+"', `Posisi_A`='"+posisi+"', `Gaji_A`='"+gaji+"' WHERE `ID`='"+id+"' ";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }
}