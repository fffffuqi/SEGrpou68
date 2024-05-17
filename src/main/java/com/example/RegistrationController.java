package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegistrationController {

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField bankLinkedField;

    private static final String ACCOUNT_FILE = "src/main/resources/account.json";
    private static final String TRANSACTION_FILE = "src/main/resources/transaction.json";
    private ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void register() {
        String userId = userIdField.getText();
        String password = passwordField.getText();
        int phone = Integer.parseInt(phoneField.getText());
        String bankLinked = bankLinkedField.getText();

        UserAccount newUser = new UserAccount(userId, false, password, phone, bankLinked);
        Transaction newTransaction = new Transaction(userId, 0.0, 0.0); // 初始化 transactionAmount 为 0.0

        try {
            List<UserAccount> accounts = readAccounts();
            accounts.add(newUser);
            mapper.writeValue(new File(ACCOUNT_FILE), accounts);

            List<Transaction> transactions = readTransactions();
            transactions.add(newTransaction);
            mapper.writeValue(new File(TRANSACTION_FILE), transactions);

            System.out.println("User registered successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserAccount> readAccounts() throws IOException {
        File file = new File(ACCOUNT_FILE);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<UserAccount>>() {});
        } else {
            return new ArrayList<>();
        }
    }

    private List<Transaction> readTransactions() throws IOException {
        File file = new File(TRANSACTION_FILE);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<Transaction>>() {});
        } else {
            return new ArrayList<>();
        }
    }
}
