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
                initCustomer();
            } else {
                System.out.println("Login failed");
            }
        }
    }

    private static void initAdmin() {
        boolean flag = true;
        while (flag) {
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

    private static void initCustomer() {
        boolean flag = true;
        while (flag) {
            System.out.println("1. Exit/Log out");
            int selectedOption = sc.nextInt();
            switch (selectedOption) {
                case 1:
                    flag = false;
                    System.out.println("You are Logged out successfully....");
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
}