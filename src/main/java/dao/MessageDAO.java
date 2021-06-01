package dao;

import dbconnection.DatabaseConnection;
import model.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDAO {

    public static void newMessage(Message message) {
        DatabaseConnection cnx = new DatabaseConnection();
        try (Connection connection = cnx.get_connection()) {

            try {
                String query = "INSERT INTO messages(user_id, message, date) VALUES(?,?,?)";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, message.getUserId());
                ps.setString(2, message.getMessage());
                ps.setString(3, message.getDate());
                ps.executeUpdate();
                System.out.println("\nMessage created successfully");
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void readMessages() {
        DatabaseConnection cnx = new DatabaseConnection();
        try (Connection connection = cnx.get_connection()) {
            try {
                String query = "select m.message_id,m.message,m.date,u.u_full_name from messages m join users u on m.user_id = u.user_id";
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.print("\n[Id: " + rs.getInt("message_id") + " | ");
                    System.out.print("Message: " + rs.getString("message") + " | ");
                    System.out.print("Date: " + rs.getString("date") + " | ");
                    System.out.print("Autor: " + rs.getString("u_full_name") + " ]\n");
                }
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void deleteMessages(Message message) {
        DatabaseConnection cnx = new DatabaseConnection();
        try (Connection connection = cnx.get_connection()) {
            try {
                String query = "delete from messages where message_id = ? and user_id = ?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, message.getMessageId());
                ps.setInt(2, message.getUserId());
                ps.executeUpdate();
                System.out.println("\nMessage successfully deleted");
            } catch (SQLException e) {
                System.out.println("\nOops something went wrong!");
                System.out.println(e.getMessage());
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
