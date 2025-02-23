package main;

import java.util.Scanner;
import service.UserService;

class Main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserService userService = new UserService();
        // System.out.println("Enter your username ");
        // String username = sc.nextLine();
        // System.out.println("Enter your password ");
        // String password = sc.nextLine();
        userService.printUser();
    }
}