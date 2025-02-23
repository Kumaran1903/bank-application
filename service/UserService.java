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

}
