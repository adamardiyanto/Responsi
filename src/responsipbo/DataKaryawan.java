/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package responsipbo;

/**
 *
 * @author ADAM ARDI
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class DataKaryawan extends JFrame{
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
    JButton btnShow = new JButton("Show");
    JLabel Footer = new JLabel("APLIKASI PERHITUNGAN GAJI PT. VETERAN JAYA");
    
    JTable tabel;
    DefaultTableModel tabelModel;
    JScrollPane scrollPane;
    Object namaKolom[] = {"ID","Nama","Posisi","Gaji Pokok","Jam Lembur","Tunjangan","Total Gaji"};
    
    
    public DataKaryawan(){
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
        
        tabelModel = new DefaultTableModel (namaKolom,0);
        tabel = new JTable(tabelModel);
        scrollPane = new JScrollPane(tabel);
        
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
        add(btnShow);
        btnShow.setBounds(350,400,120,70);
        
        add(scrollPane);
        scrollPane.setBounds(150,100,600,200);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(Footer);
        Footer.setBounds(250, 500, 600, 50);
        Footer.setFont(new Font("Arial",Font.CENTER_BASELINE, 15));
        
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
        
        btnShow.addActionListener((ActionEvent e) -> {
          String[][] dataKaryawan = this.readKaryawan();
          tabel.setModel(new JTable(dataKaryawan,namaKolom).getModel());
        });
    }
    
    
    int getBanyakData() {
        int jmlData = 0;
        try{
            statement = koneksi.createStatement();
            String query = "SELECT * from `karyawan`";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return 0;
        }
    }

String[][] readKaryawan() {
        try{
            int jmlData = 0;
            String data[][]=new String[getBanyakData()][7];
            String query = "Select `ID`,`Posisi`,`Nama`,`Gaji_pokok`,`Lembur`,`Tunjangan`,`Total_gaji` from `karyawan` ";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                data[jmlData][0] = resultSet.getString("ID");
                data[jmlData][1] = resultSet.getString("Nama");
                data[jmlData][2] = resultSet.getString("Posisi");
                data[jmlData][3] = resultSet.getString("Gaji_pokok");
                data[jmlData][4] = resultSet.getString("Lembur");
                data[jmlData][5] = resultSet.getString("Tunjangan");
                data[jmlData][6] = resultSet.getString("Total_gaji");
                jmlData++;
            }
            return data;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            System.out.println("SQL error");
            return null;
        }
    }
}
