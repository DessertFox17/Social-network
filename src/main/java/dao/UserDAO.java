package dao;

import dbconnection.DatabaseConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    public static void newUser(User user) {
        DatabaseConnection cnx = new DatabaseConnection();

        try (Connection connection = cnx.get_connection()) {
            try {
                String query = "insert into social_network.users(u_email,u_password,u_full_name) values (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.executeUpdate();
                System.out.println("\nUser registered successfully, now you can login");
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getUsers() {
        DatabaseConnection cnx = new DatabaseConnection();

        try (Connection connection = cnx.get_connection()) {

            try {
                String query = "select * from users";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.print("\n[Id: " + rs.getString("user_id") + " | ");
                    System.out.print("email: " + rs.getString("u_email") + " | ");
                    System.out.print("name: " + rs.getString("u_full_name") + " ] ");
                }
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateUser(User user){
        DatabaseConnection cnx = new DatabaseConnection();
        try (Connection connection = cnx.get_connection()) {
            try {
                String query="update users set u_email = ?, u_password = ?, u_full_name = ? where user_id = ?";
                PreparedStatement ps=connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getFullName());
                ps.setInt(4, user.getUserId());
                ps.executeUpdate();
                System.out.println("\nUser updated successfully");
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static User signInDB(User user) {
        DatabaseConnection cnx = new DatabaseConnection();

        try (Connection connection = cnx.get_connection()) {

            try {
                String query = "select * from users where u_email=? and u_password= ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getPassword());
                ResultSet rs = ps.executeQuery();
                User login = new User();

                if (rs.next()) {
                    System.out.println("\nLogin successful");
                    login.setUserId(rs.getInt("user_id"));
                    login.setEmail(rs.getString("u_email"));
                    login.setFullName(rs.getString("u_full_name"));
                } else {
                    System.out.println("\nemail or password are incorrect");
                }

                return login;
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
}
