package service;

import entity.User;
import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void printUser() {
        userRepository.printUser();
    }

    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    public boolean addCustomer(String username, String password, String contactNumber) {
        return userRepository.addCustomer(username, password, contactNumber);
    }

    public Double checkBalance(String username) {
        return userRepository.checkBalance(username);
    }
    public User getUser(String username) {
        return userRepository.getUser(username);
    }
    public boolean transferMoney(String senderId, String receiverId, Double amount) {
        return userRepository.transferMoney(senderId, receiverId, amount);
    }
}
