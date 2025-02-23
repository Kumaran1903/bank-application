package entity;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String contactNumber;
    private String role;
    private Double accountBalance;

    // Constructor
    public User(String username, String password, String contactNumber, String role, Double accountBalance) {
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.role = role;
        this.accountBalance = accountBalance;
    }

    // getter and setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", role='" + role + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, contactNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return (username != null ? username.equals(user.username) : user.username == null) &&
                (password != null ? password.equals(user.password) : user.password == null) &&
                (contactNumber != null ? contactNumber.equals(user.contactNumber) : user.contactNumber == null);
    }
}
