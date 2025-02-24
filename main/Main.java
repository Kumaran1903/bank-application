package main;

import entity.User;
import java.util.Scanner;
import service.UserService;

class Main {
    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserService();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter your username ");
            String username = sc.next();
            System.out.println("Enter your password ");
            String password = sc.next();

            User user = userService.login(username, password);

            if (user != null && user.getRole().equals("admin")) {
                initAdmin();
            } else if (user != null && user.getRole().equals("user")) {
                initCustomer(user);
            } else {
                System.out.println("Login failed");
            }
        }
    }

    private static void initAdmin() {
        boolean flag = true;
        while (flag) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("1. Exit/Log out");
            System.out.println("2. Create a Customer account");
            int selectedOption = sc.nextInt();
            switch (selectedOption) {
                case 1:
                    flag = false;
                    System.out.println("You are Logged out successfully....");
                    break;
                case 2:
                    addCustomer();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void initCustomer(User user) {
        boolean flag = true;
        while (flag) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("1. Exit/Log out");
            System.out.println("2. Check Balance");
            System.out.println("3. Transfer Money");
            int selectedOption = sc.nextInt();
            switch (selectedOption) {
                case 1:
                    flag = false;
                    System.out.println("You are Logged out successfully....");
                    break;
                case 2:
                    Double balance = userService.checkBalance(user.getUsername());
                    if (balance != null) {
                        System.out.println("Your balance is " + balance);
                    } else {
                        System.out.println("Failed to get balance");
                    }
                    break;
                case 3:
                    transferMoney(user);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void addCustomer() {
        System.out.println("Enter username");
        String username = sc.next();
        System.out.println("Enter password");
        String password = sc.next();
        System.out.println("Enter contact number");
        String contactNumber = sc.next();
        boolean result = userService.addCustomer(username, password, contactNumber);
        if (result) {
            System.out.println("Customer creation is  successfully");
        } else {
            System.out.println("Customer creation is failed");
        }
    }

    private static void transferMoney(User userDetails) {
        System.out.println("Enter userId");
        String userId = sc.next();
        User user = userService.getUser(userId);
        if (user != null) {
            System.out.println("Enter amount");
            Double amount = sc.nextDouble();
            Double balance = userService.checkBalance(userDetails.getUsername());
            if (balance != null && balance >= amount) {
                boolean result = userService.transferMoney(userDetails.getUsername(), userId, amount);
                if (result) {
                    System.out.println("Money transfer is successfully");
                } else {
                    System.out.println("Money transfer is failed");
                }
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("User not found");
        }
    }
}