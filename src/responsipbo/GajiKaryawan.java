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



/**
 *
 * @author ADAM ARDI
 */
public class GajiKaryawan extends JFrame {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/responsi_pbo";
    static final String USER = "root";
    static final String PASS = "qwerty123";
    
    Connection koneksi;
    Statement statement;
    
    JButton btnHome = new JButton("Home");
    JButton btnGaji = new JButton("Gaji");
    JButton btnData = new JButton("Data");
    JButton btnPetunjuk= new JButton("Petunjuk");
    JButton btnAdmin = new JButton("Admin");
    JButton btnHitung = new JButton("Hitung");
    JButton btnSimpan = new JButton("Simpan");
    JLabel Footer = new JLabel("APLIKASI PERHITUNGAN GAJI PT. VETERAN JAYA");
    
    JLabel lID = new JLabel("ID Pegawai");
    JTextField tfID = new JTextField();
    JLabel lNama = new JLabel("Nama");
    JTextField tfNama = new JTextField();
    JLabel lPosisi = new JLabel("Posisi ");
    String[] namaPosisi =
            {"Direktur","Manager","Programmer","Marketing","Surveyor"};
    JComboBox cmbPosisi= new JComboBox(namaPosisi);
    JLabel lAlamat = new JLabel("alamat");
    JTextField tfAlamat = new JTextField();
    JLabel lNo_hp = new JLabel("NO HP");
    JTextField tfNo_hp = new JTextField();
    JLabel lGaji = new JLabel("Gaji Pokok");
    JTextField tfGaji = new JTextField();
    JLabel lLembur = new JLabel("Jam Lembur");
    JTextField tfLembur = new JTextField();
    JLabel lTunjangan = new JLabel("Tunjangan");
    JTextField tfTunjangan = new JTextField();
    JLabel lPajak = new JLabel("Pajak");
    JTextField tfPajak = new JTextField();
    JLabel lTotal = new JLabel("Total Gaji");
    JTextField tfTotal = new JTextField();
    
    
    
    
    
    
    public GajiKaryawan(){
        
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
        add(btnGaji);
        btnGaji.setBounds(10,100,120,70);
        add(btnData);
        btnData.setBounds(10,180,120,70);
        add(btnPetunjuk);
        btnPetunjuk.setBounds(10,260,120,70);
        add(btnAdmin);
        btnAdmin.setBounds(700,20,120,70);
        add(btnHitung);
        btnHitung.setBounds(700,380,120,70);
        add(btnSimpan);
        btnSimpan.setBounds(700,450,120,70);
        
        
        add(Footer);
        Footer.setBounds(250, 500, 600, 50);
        Footer.setFont(new Font("Arial",Font.CENTER_BASELINE, 15));
        
        add(lID);
        lID.setBounds(150,50,90,20);
        add(tfID);
        tfID.setBounds(250,50,200,20);
        add(lNama);
        lNama.setBounds(150,80,90,20);
        add(tfNama);
        tfNama.setBounds(250,80,200,20);
        add(lPosisi);
        lPosisi.setBounds(150,110,100,20);
        add(cmbPosisi);
        cmbPosisi.setBounds(250,110,100,20);
        add(lAlamat);
        lAlamat.setBounds(150,140,90,20);
        add(tfAlamat);
        tfAlamat.setBounds(250,140,200,20);
        add(lNo_hp);
        lNo_hp.setBounds(150,170,90,20);
        add(tfNo_hp);
        tfNo_hp.setBounds(250,170,200,20);
        add(lGaji);
        lGaji.setBounds(150,200,90,20);
        add(tfGaji);
        tfGaji.setBounds(250,200,200,20);
        add(lLembur);
        lLembur.setBounds(150,230,90,20);
        add(tfLembur);
        tfLembur.setBounds(250,230,200,20);
        add(lTunjangan);
        lTunjangan.setBounds(150,260,90,20);
        add(tfTunjangan);
        tfTunjangan.setBounds(250,260,200,20);
        add(lPajak);
        lPajak.setBounds(150,290,90,20);
        add(tfPajak);
        tfPajak.setBounds(250,290,200,20);
        add(lTotal);
        lTotal.setBounds(150,320,90,20);
        add(tfTotal);
        tfTotal.setBounds(250,320,200,20);
        
        btnHome.addActionListener((ActionEvent e) -> {
          HomeKaryawan a = new HomeKaryawan();
           dispose();
        });
        btnGaji.addActionListener((ActionEvent e) -> {
          GajiKaryawan b = new GajiKaryawan();
           dispose();
        });
        btnData.addActionListener((ActionEvent e) -> {
          DataKaryawan c = new DataKaryawan();
           dispose();
        });
        btnPetunjuk.addActionListener((ActionEvent e) -> {
          PetunjukKaryawan d = new PetunjukKaryawan();
           dispose();
        });
        btnAdmin.addActionListener((ActionEvent e) -> {
          Admin d = new Admin();
           dispose();
        });
        
        btnSimpan.addActionListener((ActionEvent e) -> {
            if (tfID.getText().equals("") ) {
                JOptionPane.showMessageDialog(null, "Field tidak boleh kosong");
            } else {
                String id = tfID.getText();
                String nama = tfNama.getText();
                String posisi = (String) cmbPosisi.getSelectedItem();
                String alamat = tfAlamat.getText();
                String no_hp = tfNo_hp.getText();
                long gaji = Long.parseLong(tfGaji.getText());
                long lembur = Long.parseLong(tfLembur.getText());
                long tunjangan = Long.parseLong(tfTunjangan.getText());
                long pajak = Long.parseLong(tfPajak.getText());
                long total = Long.parseLong(tfTotal.getText());
                
                
                this.insertKaryawan(id,nama,posisi,alamat,no_hp,gaji,lembur,tunjangan,total);
                
            }
        });
        
        
        btnHitung.addActionListener((ActionEvent e) -> {
                long gaji = Long.parseLong(tfGaji.getText());
                long lembur = Long.parseLong(tfLembur.getText());
                try {
                      long tunjangan = lembur*15000;
                      long pajak = gaji/100;
                      long total = gaji+tunjangan - pajak ;
                      
                      tfTunjangan.setText(Long.toString(tunjangan));
                      tfPajak.setText(Long.toString(pajak));
                      tfTotal.setText(Long.toString(total));
                      
                } catch (Exception a) {
                    System.out.println(a.getMessage());
                }


                   });
        
    }

   public void insertKaryawan(String id ,String nama, String posisi,String alamat, String no_hp, long gaji, long lembur, long tunjangan, long total) {
        try{
            String query = "INSERT INTO `karyawan`(`ID`,`Nama`,`Posisi`,`Alamat`,`No_hp`,`Gaji_pokok`,`Lembur`,`Tunjangan`,`Total_gaji`) VALUES ('"+id+"','"+nama+"','"+posisi+"','"+alamat+"','"+no_hp+"','"+gaji+"','"+lembur+"','"+tunjangan+"','"+total+"')";
        statement = (Statement) koneksi.createStatement();
        statement.executeUpdate(query);
        JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan");
        }catch(Exception sql){
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
        
    }
}
