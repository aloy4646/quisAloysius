/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import controller.DateLabelFormatter;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.CategoryUser;

/**
 *
 * @author Aloysius
 */
public class Menu {
    JFrame frameMainMenu = new JFrame(), frameLogin = new JFrame(), frameRegistrasi = new JFrame(), frameLihatData = new JFrame(), frameMenuPengguna = new JFrame();
    Controller controller = new Controller();

    public Menu() {
        createFrame();

        JButton login = new JButton("LOGIN");
        login.setBounds(240, 125, 120, 30);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMainMenu.setVisible(false);
                tampilanFrameLogin();
            }
        });

        JButton registrasi = new JButton("REGISTRASI");
        registrasi.setBounds(240, 165, 120, 30);
        registrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMainMenu.setVisible(false);
                tampilanFrameRegistrasi();
            }
        });

        JButton lihatData = new JButton("LIHAT DATA");
        lihatData.setBounds(240, 205, 120, 30);
        lihatData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilanFrameLihatData();
            }
        });

        frameMainMenu.add(login);
        frameMainMenu.add(registrasi);
        frameMainMenu.add(lihatData);
    }
    
    void createFrame() {
        frameMainMenu.setTitle("Menu");
        frameMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMainMenu.setResizable(false);
        frameMainMenu.setSize(600, 400);
        frameMainMenu.setLayout(null);
        frameMainMenu.setVisible(true);
        frameMainMenu.getContentPane().setBackground(new Color(255, 112, 112));

        frameLogin.setTitle("Form LOGIN");
        frameLogin.setResizable(false);
        frameLogin.setSize(350, 300);
        frameLogin.setLayout(null);
        frameLogin.setVisible(false);
        frameLogin.getContentPane().setBackground(new Color(255, 112, 112));
        windowClosingListener(frameLogin);

        frameRegistrasi.setTitle("REGISTRASI");
        frameRegistrasi.setResizable(false);
        frameRegistrasi.setSize(350, 250);
        frameRegistrasi.setLayout(null);
        frameRegistrasi.setVisible(false);
        frameRegistrasi.getContentPane().setBackground(new Color(175, 219, 227));
        windowClosingListener(frameRegistrasi);

        frameLihatData.setTitle("LIHAT DATA");
        frameLihatData.setResizable(false);
        frameLihatData.setSize(300, 150);
        frameLihatData.setLayout(null);
        frameLihatData.setVisible(false);
        frameLihatData.getContentPane().setBackground(new Color(175, 219, 227));
        windowClosingListener(frameLihatData);
        
        frameMenuPengguna.setTitle("MENU PENGGUNA");
        frameMenuPengguna.setResizable(false);
        frameMenuPengguna.setSize(300, 150);
        frameMenuPengguna.setLayout(null);
        frameMenuPengguna.setVisible(false);
        frameMenuPengguna.getContentPane().setBackground(new Color(175, 219, 227));
        windowClosingListener(frameMenuPengguna);
    }
    
    void windowClosingListener(JFrame frame) {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                frame.setVisible(false);
                frameMainMenu.setVisible(true);
            }
        });
    }
    
    public void tampilanFrameLogin(){
        frameLogin.getContentPane().removeAll();
        frameLogin.setVisible(true);
        
        ImageIcon logo = new ImageIcon(new ImageIcon("C:\\Users\\Aloysius\\Documents\\ITHB\\Semester 3\\Prak PBO\\PBO\\Aloysius Quiz 2\\images\\logo.jpg").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        JLabel labelLogo = new JLabel(logo);
        labelLogo.setBounds(120, 5, 100, 100);
        
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(10, 105, 50, 30);
        JTextField textEmail = new JTextField();
        textEmail.setBounds(80, 108, 200, 20);
        
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(10, 140, 90, 30);
        JPasswordField textPassword = new JPasswordField();
        textPassword.setBounds(80, 143, 200, 20);

        JButton login = new JButton("LOGIN");
        login.setBounds(10, 180, 100, 30);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringPassword = String.valueOf(textPassword.getPassword());
                if (controller.loginUser(textEmail.getText(), stringPassword)) {
                    frameLogin.setVisible(false);
                    tampilanFrameMenuPengguna(textEmail.getText(), stringPassword);
                } else {
                    JOptionPane.showMessageDialog(null, "LOGIN GAGAL");
                }
            }
        });
        
        frameLogin.add(labelLogo);
        frameLogin.add(labelEmail);
        frameLogin.add(textEmail);
        frameLogin.add(labelPassword);
        frameLogin.add(textPassword);
        frameLogin.add(login);
        
    }
    
    public void tampilanFrameRegistrasi(){
        frameRegistrasi.getContentPane().removeAll();
        frameRegistrasi.setVisible(true);
        
        JLabel labelName = new JLabel("Name");
        labelName.setBounds(10, 5, 50, 30);
        JTextField textName = new JTextField();
        textName.setBounds(80, 8, 200, 20);
        
        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(10, 40, 50, 30);
        JTextField textEmail = new JTextField();
        textEmail.setBounds(80, 43, 200, 20);
        
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(10, 80, 90, 30);
        JPasswordField textPassword = new JPasswordField();
        textPassword.setBounds(80, 83, 200, 20);
        
        JLabel labelKategori = new JLabel("Kategori");
        labelKategori.setBounds(10, 120, 200, 30);
        String[] listKategori = controller.getKategoriUser();
        JComboBox comboKategori = new JComboBox(listKategori);
        comboKategori.setBounds(120, 123, 150, 25);
        
        JButton registrasi = new JButton("REGISTRASI");
        registrasi.setBounds(10, 160, 120, 30);
        registrasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stringPassword = String.valueOf(textPassword.getPassword());
                if(stringPassword.length() < 8)
                    JOptionPane.showMessageDialog(null, "Password harus lebih dari 8 karakter");
                else{
                    if(controller.registrasiUser(textName.getText(), textEmail.getText(), stringPassword, controller.getIdKategory(String.valueOf(comboKategori.getSelectedItem())))){
                        JOptionPane.showMessageDialog(null, "Registrasi berhasil");
                        frameRegistrasi.setVisible(false);
                        tampilanFrameMenuPengguna(textEmail.getText(), stringPassword);
                    }else{
                        JOptionPane.showMessageDialog(null, "Registrasi Gagal");
                    }
                }
            }
        });
        
        JButton back = new JButton("BACK");
        back.setBounds(150, 160, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMainMenu.setVisible(true);
                frameRegistrasi.setVisible(false);
            }
        });
        
        
        frameRegistrasi.add(labelName);
        frameRegistrasi.add(textName);
        frameRegistrasi.add(labelEmail);
        frameRegistrasi.add(textEmail);
        frameRegistrasi.add(labelPassword);
        frameRegistrasi.add(textPassword);
        frameRegistrasi.add(labelKategori);
        frameRegistrasi.add(comboKategori);
        frameRegistrasi.add(registrasi);
        frameRegistrasi.add(back);
    }
    
    public void tampilanFrameLihatData(){
        frameLihatData.getContentPane().removeAll();
        frameLihatData.setVisible(true);
        
        JLabel labelKategori = new JLabel("Kategori");
        labelKategori.setBounds(10, 5, 200, 30);
        String[] listKategori = controller.getKategoriUser();
        JComboBox comboKategori = new JComboBox(listKategori);
        comboKategori.setBounds(120, 8, 150, 25);
        
        JButton lihat = new JButton("LIHAT");
        lihat.setBounds(10, 40, 100, 30);
        lihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.lihatDataBerdKategori(controller.getIdKategory(String.valueOf(comboKategori.getSelectedItem())));
                frameMainMenu.setVisible(true);
                frameLihatData.setVisible(false);
            }
        });
        
        JButton back = new JButton("BACK");
        back.setBounds(130, 40, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMainMenu.setVisible(true);
                frameLihatData.setVisible(false);
            }
        });
        
        frameLihatData.add(labelKategori);
        frameLihatData.add(comboKategori);
        frameLihatData.add(lihat);
        frameLihatData.add(back);
    }
    
    public void tampilanFrameMenuPengguna(String email, String password){
        frameMenuPengguna.getContentPane().removeAll();
        frameMenuPengguna.setVisible(true);
        
        JButton back = new JButton("BACK");
        back.setBounds(10, 5, 100, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameMainMenu.setVisible(true);
                frameMenuPengguna.setVisible(false);
            }
        });
        
        frameMenuPengguna.add(back);
    }
}
