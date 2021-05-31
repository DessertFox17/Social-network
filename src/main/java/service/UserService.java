package service;

import dao.UserDAO;
import model.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Scanner;

public class UserService {
    public static void newUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType your full name");
        System.out.print("-> ");
        String fullName = sc.nextLine();

        System.out.println("Type your email");
        System.out.print("-> ");
        String email = sc.nextLine();

        System.out.println("Type your password");
        System.out.print("-> ");
        String password = sc.nextLine();
        password = codePassword(password);

        User newUSer = new User(0, email, password, fullName);
        UserDAO.newUser(newUSer);
    }

    public static void getUsers(){
        UserDAO.getUsers();
    }

    public static void updateUser(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType your full name");
        System.out.print("-> ");
        String fullName = sc.nextLine();
        System.out.println("Type your email");
        System.out.print("-> ");
        String email = sc.nextLine();
        System.out.println("Type your password");
        System.out.print("-> ");
        String password = sc.nextLine();
        password = codePassword(password);

        User updateUser = new User(user.getUserId(), email, password, fullName);
        UserDAO.updateUser(updateUser);
    }

    public static User singIn() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nType your email");
        System.out.print("-> ");
        String email = sc.nextLine();

        System.out.println("Type your password");
        System.out.print("-> ");
        String password = sc.nextLine();

        password = codePassword(password);
        User logIn = new User(0,email,password,"");
        User result = UserDAO.signInDB(logIn);

        return result;
    }


    public static String codePassword(String password) {
        String md5Hex = DigestUtils.md5Hex(password);
        return md5Hex;
    }
}
