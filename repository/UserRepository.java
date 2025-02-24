package repository;

import entity.Transaction;
import entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepository {
    private static Set<User> users = new HashSet<>();
    private static List<Transaction> transactions = new ArrayList<>();
    static {
        User user1 = new User("admin", "admin", "1234567890", "admin", 0.0);
        User user2 = new User("juli", "juli", "0987654321", "user", 1000.0);
        User user3 = new User("luffy", "luffy", "0987654322", "user", 500.0);
        // User user4 = new User("user4", "user3", "0987654322", "user", 2000.0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        // users.add(user4);
    }

    public void printUser() {
        System.out.println(users);
    }

    public User login(String username, String password) {
        List<User> list = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public boolean addCustomer(String username, String password, String contactNumber) {
        User user = new User(username, password, contactNumber, "user", 500.0);
        return users.add(user);
    }

    public Double checkBalance(String username) {
        List<User> list = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            return list.get(0).getAccountBalance();
        } else {
            return null;
        }
    }

    public User getUser(String username) {
        List<User> list = users.stream()
                .filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public boolean transferMoney(String senderId, String receiverId, Double amount) {
        boolean isDebit = debit(senderId, amount, receiverId);
        boolean isCredit = credit(receiverId, amount, senderId);
        return isDebit && isCredit;
    }

    public boolean debit(String senderId, Double amount, String receiverId) {
        List<User> list = users.stream()
                .filter(user -> user.getUsername().equals(senderId))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            User user = list.get(0);
            Double balance = user.getAccountBalance();
            Double finalbalance = balance - amount;
            users.remove(user);
            user.setAccountBalance(finalbalance);
            Transaction transaction = new Transaction(LocalDate.now(), senderId, amount, "debit", balance, finalbalance,
                    receiverId);
            transactions.add(transaction);
            System.out.println(transaction);
            return users.add(user);
        }
        return false;
    }

    public boolean credit(String receiverId, Double amount, String senderId) {
        List<User> list = users.stream()
                .filter(user -> user.getUsername().equals(receiverId))
                .collect(Collectors.toList());
        if (!list.isEmpty()) {
            User user = list.get(0);
            Double balance = user.getAccountBalance();
            Double finalbalance = balance + amount;
            users.remove(user);
            user.setAccountBalance(finalbalance);
            Transaction transaction = new Transaction(LocalDate.now(), receiverId, amount, "credit", balance,
                    finalbalance,
                    senderId);
            transactions.add(transaction);
            System.out.println(transaction);
            return users.add(user);
        }
        return false;
    }
}
