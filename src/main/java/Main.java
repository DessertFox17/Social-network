import model.User;
import service.MessageService;
import service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("\n----------------------");
            System.out.println("    Social Network");
            System.out.println("----------------------");
            System.out.println("1. Sing up");
            System.out.println("2. Sign in");
            System.out.println("3. Exit");
            System.out.println("----------------------");
            System.out.print("-> ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    UserService.newUser();
                    break;
                case 2:
                    User user = UserService.singIn();
                    if(user.getUserId() > 0){
                        menuLogged(user);
                    }
                    break;
                case 3:
                    System.out.println("\nThanks for visiting us!");
                    break;
                default:
                    System.out.println("\nPlease, choose a valid option");
                    break;
            }
        } while (option != 3);
    }

    public static void menuLogged(User user){
        Scanner sc = new Scanner(System.in);
        int option = 0;
        do{
            System.out.println("\n----------------------");
            System.out.println("    Social Network");
            System.out.println("----------------------");
            System.out.printf("Welcome %s%n",user.getFullName());
            System.out.println("1. Type a message");
            System.out.println("2. Read messages");
            System.out.println("3. Delete messages");
            System.out.println("4. Edit profile");
            System.out.println("5. Check users");
            System.out.println("6. Logout");
            System.out.println("----------------------");
            System.out.print("-> ");
            option = sc.nextInt();

            switch (option){
                case 1:
                    MessageService.newMessage(user);
                    break;
                case 2:
                    MessageService.readMessages();
                    break;
                case 3:
                    MessageService.deleteMessages(user);
                    break;
                case 4:
                    UserService.updateUser(user);
                    break;
                case 5:
                    UserService.getUsers();
                    break;
                case 6:
                    mainMenu();
                    break;
                default:
                    System.out.println("\nPlease, choose a valid option");
                    break;
            }
        }while(option != 6);
    }
}
