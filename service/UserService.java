package service;

import repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public void printUser() {
        userRepository.printUser();
    }
}
