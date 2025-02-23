package repository;

import entity.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRepository {
    private static Set<User> users = new HashSet<>();
    static {
        User user1 = new User("admin", "admin", "1234567890", "admin", 0.0);
        User user2 = new User("user2", "user2", "0987654321", "user", 1000.0);
        User user3 = new User("user3", "user3", "0987654322", "user", 2000.0);
        User user4 = new User("user4", "user3", "0987654322", "user", 2000.0);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
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
}
