package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DepositController {

    @FXML
    private TextField payerAccount;

    @FXML
    private TextField recipientAccount;

    @FXML
    private TextField payee;

    @FXML
    private TextField transAmount;

    @FXML
    private Button submitApply;

    @FXML
    private Button infoButton;

    @FXML
    private Button depButton;

    @FXML
    private Button tranButton;

    @FXML
    private Button taskButton;

    @FXML
    private Button leadButton;

    @FXML
    private Button finButton;

    @FXML
    private Button loutButton;

    private static final String ACCOUNT_FILE = "src/main/resources/account.json";
    private static final String TRANSACTION_FILE = "src/main/resources/transaction.json";
    private ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void handleSubmitApply() {
        String payer = payerAccount.getText();
        String recipient = recipientAccount.getText();
        String payeeName = payee.getText();
        String amountStr = transAmount.getText();

        if (payer.isEmpty() || recipient.isEmpty() || payeeName.isEmpty() || amountStr.isEmpty() ||
                !payer.matches("\\d{11}") || !recipient.matches("\\d{11}") || payeeName.length() >= 15 || !amountStr.matches("\\d+")) {
            setInvalidInput();
            return;
        }

        try {
            List<UserAccount> accounts = readAccounts();
            UserAccount currentUser = accounts.get(0); // 假设第一个用户是当前登录的用户
            if (!payer.equals(currentUser.getBankLinked())) {
                setInvalidInput();
                return;
            }

            int amount = Integer.parseInt(amountStr);
            List<Transaction> transactions = readTransactions();
            Transaction currentTransaction = transactions.stream()
                    .filter(t -> t.getUserId().equals(currentUser.getUserId()))
                    .findFirst()
                    .orElse(null);

            if (currentTransaction == null || amount > currentTransaction.getBalance()) {
                setInvalidInput();
                return;
            }

            // 更新余额
            currentTransaction.setBalance(currentTransaction.getBalance() - amount);
            mapper.writeValue(new File(TRANSACTION_FILE), transactions);

            showAlert("Transaction successful", "The transaction was completed successfully.", AlertType.INFORMATION);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setInvalidInput() {
        payerAccount.setPromptText("invalid input, please re-enter");
        recipientAccount.setPromptText("invalid input, please re-enter");
        payee.setPromptText("invalid input, please re-enter");
        transAmount.setPromptText("invalid input, please re-enter");
    }

    private List<UserAccount> readAccounts() throws IOException {
        File file = new File(ACCOUNT_FILE);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<UserAccount>>() {});
        } else {
            throw new IOException("Account file not found");
        }
    }

    private List<Transaction> readTransactions() throws IOException {
        File file = new File(TRANSACTION_FILE);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<Transaction>>() {});
        } else {
            throw new IOException("Transaction file not found");
        }
    }

    private void showAlert(String title, String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void switchToInformation(ActionEvent event) {
        switchScene(event, "/information.fxml");
    }

    @FXML
    private void switchToDeposit(ActionEvent event) {
        switchScene(event, "/deposit.fxml");
    }

    @FXML
    private void switchToTransaction(ActionEvent event) {
        switchScene(event, "/transaction.fxml");
    }

    @FXML
    private void switchToTasks(ActionEvent event) {
        switchScene(event, "/tasks.fxml");
    }

    @FXML
    private void switchToLeaderboard(ActionEvent event) {
        switchScene(event, "/leaderboard.fxml");
    }

    @FXML
    private void switchToFinancing(ActionEvent event) {
        switchScene(event, "/financing.fxml");
    }

    @FXML
    private void switchToLogin(ActionEvent event) {
        switchScene(event, "/login.fxml");
    }

    private void switchScene(ActionEvent event, String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

