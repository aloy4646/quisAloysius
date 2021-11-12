/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.CategoryUser;
import model.User;

/**
 *
 * @author Aloysius
 */
public class Controller {
    DatabaseHandler conn = new DatabaseHandler();
    
//    public boolean cariPenduduk(String nik){
//        boolean dapat = true;
//        conn.connect();
//        String query = "SELECT * FROM penduduk WHERE NIK='" + nik + "'";
//        try {
//            Statement stmt = conn.con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            if(rs.next() == false)
//                dapat = false;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return dapat;
//    }
    
    
    public boolean loginUser(String email, String password){
        boolean dapat = true;
        conn.connect();
        String query = "SELECT * FROM user WHERE email='" + email + "' AND password = '" + password + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next() == false)
                dapat = false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return dapat;
    }
    
    
    public String[] getKategoriUser(){
        String listKategori[] = new String[3];
        conn.connect();
        String query = "SELECT * FROM categoryuser";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                listKategori[i] = rs.getString("name");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listKategori;
    }
    
    public int getIdKategory(String name){
        int id = 0;
        conn.connect();
        String query = "SELECT * FROM categoryuser WHERE name='" + name + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public int getLastIdUser(){
        int id = 0;
        conn.connect();
        String query = "SELECT * FROM user ORDER by id desc LIMIT 1";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public boolean registrasiUser(String name, String email, String password, int integerKategoriUser){
        User user = new User(getLastIdUser(), name, email, password, integerKategoriUser);
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getIntegerKategoriUser());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }
    
    public void lihatDataBerdKategori(int integerKategoriUser){
        conn.connect();
        String query = "SELECT * FROM user WHERE idCategory='" + integerKategoriUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setIntegerKategoriUser(integerKategoriUser);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void exitDatabase(){
        conn.disconnect();
        System.out.println("Euy");
    }
//    public IdentitasKTP getPenduduk (String nik) {
//        conn.connect();
//        String query = "SELECT * FROM penduduk WHERE NIK='" + nik + "'";
//        IdentitasKTP identitas = new IdentitasKTP();
//        try {
//            Statement stmt = conn.con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while (rs.next()) {
//                identitas.setNik(rs.getString("NIK"));
//                identitas.setNama(rs.getString("nama"));
//                identitas.setTempatLahir(rs.getString("tempatLahir"));
//                identitas.setJenisKelamin(rs.getString("jenisKelamin"));
//                identitas.setGolDarah(rs.getString("golDarah"));
//                identitas.setAlamat(rs.getString("alamat"));
//                identitas.setRtRw(rs.getString("rtRw"));
//                identitas.setKelDes(rs.getString("kelDes"));
//                identitas.setKecamatan(rs.getString("kecamatan"));
//                identitas.setAgama(rs.getString("agama"));
//                identitas.setStatusKawin(rs.getString("statusKawin"));
//                identitas.setPekerjaan(rs.getString("pekerjaan"));
//                identitas.setKewarganegaraan(rs.getString("kewarganegaraan"));
//                identitas.setKotaPembuatan(rs.getString("kotaPembuatan"));
//                identitas.setAlamatFoto(rs.getString("alamatFoto"));
//                identitas.setAlamatTandaTangan(rs.getString("alamatTandaTangan"));
//                identitas.setTanggalLahir(rs.getDate("tanggalLahir"));
//                identitas.setBerlakuHingga(rs.getDate("berlakuHingga"));
//                identitas.setTanggalPembuatan(rs.getDate("tanggalPembuatan"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return identitas;
//    }
//    
//    public boolean insertPenduduk(IdentitasKTP identitas){
//        conn.connect();
//        String query = "INSERT INTO penduduk VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//        try {
//            PreparedStatement stmt = conn.con.prepareStatement(query);
//            stmt.setString(1, identitas.getNik());
//            stmt.setString(2, identitas.getNama());
//            stmt.setString(3, identitas.getTempatLahir());
//            stmt.setString(4, identitas.getJenisKelamin());
//            stmt.setString(5, identitas.getGolDarah());
//            stmt.setString(6, identitas.getAlamat());
//            stmt.setString(7, identitas.getRtRw());
//            stmt.setString(8, identitas.getKelDes());
//            stmt.setString(9, identitas.getKecamatan());
//            stmt.setString(10, identitas.getAgama());
//            stmt.setString(11, identitas.getStatusKawin());
//            stmt.setString(12, identitas.getPekerjaan());
//            stmt.setString(13, identitas.getKewarganegaraan());
//            stmt.setString(14, identitas.getKotaPembuatan());
//            stmt.setString(15, identitas.getAlamatFoto());
//            stmt.setString(16, identitas.getAlamatTandaTangan());
//            stmt.setDate(17, convertDate(identitas.getTanggalLahir()));
//            stmt.setDate(18, convertDate(identitas.getBerlakuHingga()));
//            stmt.setDate(19, convertDate(identitas.getTanggalPembuatan()));
//            stmt.executeUpdate();
//            return (true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return (false);
//        }
//    }
//    
//    public boolean updateUser(IdentitasKTP identitas) {
//        conn.connect();
//        String query = "UPDATE penduduk SET nama='" + identitas.getNama() + "', "
//                + "tempatLahir='" + identitas.getTempatLahir() + "', "
//                + "golDarah='" + identitas.getGolDarah() + "', "
//                + "alamat='" + identitas.getAlamat() + "', "
//                + "rtRw='" + identitas.getRtRw() + "', "
//                + "kelDes='" + identitas.getKelDes() + "', "
//                + "kecamatan='" + identitas.getKecamatan() + "', "
//                + "agama='" + identitas.getAgama() + "', "
//                + "statusKawin='" + identitas.getStatusKawin() + "', "
//                + "pekerjaan='" + identitas.getPekerjaan() + "', "
//                + "kewarganegaraan='" + identitas.getKewarganegaraan() + "', "
//                + "kotaPembuatan='" + identitas.getKotaPembuatan() + "', "
//                + "alamatFoto='" + identitas.getAlamatFoto() + "', "
//                + "alamatTandaTangan='" + identitas.getAlamatTandaTangan() + "', "
//                + "tanggalLahir='" + convertDate(identitas.getTanggalLahir()) + "', "
//                + "berlakuHingga='" + convertDate(identitas.getBerlakuHingga()) + "', "
//                + "tanggalPembuatan='" + convertDate(identitas.getTanggalPembuatan()) + "' "
//                + "WHERE NIK='" + identitas.getNik() + "'";
//        try {
//            Statement stmt = conn.con.createStatement();
//            stmt.executeUpdate(query);
//            return (true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return (false);
//        }
//    }
//    
//    public boolean deleteUser(String nik) {
//        conn.connect();
//        String query = "DELETE FROM penduduk WHERE NIK='" + nik + "'";
//        try {
//            Statement stmt = conn.con.createStatement();
//            stmt.executeUpdate(query);
//            return (true);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return (false);
//        }
//    }
//    
//    private java.sql.Date convertDate(java.util.Date date){
//        java.sql.Date convertedDate = new java.sql.Date(date.getTime());
//        return convertedDate;
//    }
//    
//    public boolean cekPekerjaan(String pekerjaanYangDicari, String pekerjaan){
//        boolean cek = false;
//        String[] pekerjaanSplit = pekerjaan.split(", ");
//        for (String hasilSplit : pekerjaanSplit) {
//            if(hasilSplit.equals(pekerjaanYangDicari))
//                cek = true;
//        }
//        return cek;
//    }
}
