package com.example;

public class UserAccount {
    private String userId;
    private boolean userRoot;
    private String password;
    private int userPhone;
    private String bankLinked;

    // Constructors, getters, and setters

    public UserAccount() {}

    public UserAccount(String userId, boolean userRoot, String password, int userPhone, String bankLinked) {
        this.userId = userId;
        this.userRoot = userRoot;
        this.password = password;
        this.userPhone = userPhone;
        this.bankLinked = bankLinked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isUserRoot() {
        return userRoot;
    }

    public void setUserRoot(boolean userRoot) {
        this.userRoot = userRoot;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(int userPhone) {
        this.userPhone = userPhone;
    }

    public String getBankLinked() {
        return bankLinked;
    }

    public void setBankLinked(String bankLinked) {
        this.bankLinked = bankLinked;
    }
}
