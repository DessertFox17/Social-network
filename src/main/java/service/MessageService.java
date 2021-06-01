package service;

import dao.MessageDAO;
import model.Message;
import model.User;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MessageService {
    public static void newMessage(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType the message here");
        System.out.print("-> ");
        String newMessage = sc.nextLine();
        Message message = new Message(0,newMessage,String
                .valueOf(LocalDateTime.now()), user.getUserId(), user.getFullName());
        MessageDAO.newMessage(message);
    }

    public static void readMessages() {
        MessageDAO.readMessages();
    }

    public static void deleteMessages(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType the id of the message to delete");
        System.out.print("-> ");
        int message_id = sc.nextInt();
        Message message = new Message(message_id,"", String.
                valueOf(LocalDateTime.now()),user.getUserId(),user.getFullName());
        MessageDAO.deleteMessages(message);
    }
}
