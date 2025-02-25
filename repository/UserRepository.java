package repository;

import entity.Transaction;
import entity.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepository {
    private static Set<User> users = new HashSet<>();
    private static List<Transaction> transactions = new ArrayList<>();
    private static Map<String, Boolean> chequeBookRequest = new HashMap<>();
    static {
        User user1 = new User("admin", "admin", "1234567890", "admin", 0.0);
        User user2 = new User("user1", "user1", "0987654321", "user", 1000.0);
        User user3 = new User("user2", "user2", "0987654322", "user", 1000.0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
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
            Transaction transaction = new Transaction(LocalDate.now(), receiverId, amount, "Debit", balance,
                    finalbalance,
                    senderId);
            transactions.add(transaction);
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
            Transaction transaction = new Transaction(LocalDate.now(), senderId, amount, "Credit", balance,
                    finalbalance,
                    receiverId);
            transactions.add(transaction);
            return users.add(user);
        }
        return false;
    }

    public void printTransactions(String username) {
        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getTransactionPerfomedBy().equals(username))
                .collect(Collectors.toList());
        System.out.println("Date\t\tUserId\t\tAmount\t\tType\t\tInitial Balance\t\tFinal Balance");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------");
        for (Transaction t : list) {
            System.out
                    .println(t.getDate() + "\t" + t.getTransactionUserId() + "\t\t" + t.getTransactionAmount()
                            + "\t\t"
                            + t.getTransactionType() + "\t\t" + t.getInitialBalance() + "\t\t\t" + t.getFinalBalance());
        }

    }

    public void raiseChequeBookRequest(String username) {
        chequeBookRequest.put(username, false);
    }

    public Map<String, Boolean> getAllChecqueBookRequest() {
        return chequeBookRequest;
    }

    public List<String> getUserIdForChequeBookRequest() {
        List<String> list = new ArrayList<>();
        for (var x : chequeBookRequest.entrySet()) {
            if (!x.getValue()) {
                list.add(x.getKey());
            }
        }
        return list;
    }

    public void approveChequeBookRequest(String userId) {
        chequeBookRequest.put(userId, true);
    }
}
