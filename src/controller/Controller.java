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
import model.User;

/**
 *
 * @author Aloysius
 */
public class Controller {

    DatabaseHandler conn = new DatabaseHandler();

    public boolean loginUser(String email, String password) {
        boolean dapat = true;
        conn.connect();
        String query = "SELECT * FROM user WHERE email='" + email + "' AND password = '" + password + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next() == false) {
                dapat = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dapat;
    }

    public User getUser(String email, String password) {
        User user = new User();
        conn.connect();
        String query = "SELECT * FROM user WHERE email='" + email + "' AND password = '" + password + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"), rs.getInt("idCategory"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public String[] getKategoriUser() {
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

    public int getIdKategory(String name) {
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

    public String getCategoryName(int id) {
        String name = "";
        conn.connect();
        String query = "SELECT * FROM categoryuser WHERE id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int getLastIdUser() {
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

    public boolean registrasiUser(String name, String email, String password, int integerKategoriUser) {
        User user = new User(getLastIdUser() + 1, name, email, password, integerKategoriUser);
        conn.connect();
        String query = "INSERT INTO user VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.con.prepareStatement(query);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getIntegerUserCategory());
            stmt.executeUpdate();
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public void lihatDataBerdKategori(int integerKategoriUser) {
        conn.connect();
        String query = "SELECT * FROM user WHERE idCategory='" + integerKategoriUser + "'";
        try {
            Statement stmt = conn.con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int counter = 1;
            System.out.println(getCategoryName(integerKategoriUser));
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                System.out.println(counter + ". " + user.toString() + '}');
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateUser(User user) {
        conn.connect();
        String query = "UPDATE user SET name='" + user.getName() + "', "
                + "email='" + user.getEmail() + "', "
                + "password='" + user.getPassword() + "', "
                + "idCategory='" + user.getIntegerUserCategory() + "' "
                + "WHERE id='" + user.getId() + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public boolean deleteUser(int id) {
        conn.connect();

        String query = "DELETE FROM user WHERE id='" + id + "'";
        try {
            Statement stmt = conn.con.createStatement();
            stmt.executeUpdate(query);
            return (true);
        } catch (SQLException e) {
            e.printStackTrace();
            return (false);
        }
    }

    public void exitDatabase() {
        conn.disconnect();
        System.out.println("Euy");
    }

}
