package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class InformationController {

    @FXML
    private Label linkedAccountLabel;

    @FXML
    private Label accountBalanceLabel;

    @FXML
    private Label userIdLabel;

    @FXML
    private Label accountRootLabel;

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

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String ACCOUNT_FILE = "src/main/resources/account.json";
    private static final String TRANSACTION_FILE = "src/main/resources/transaction.json";
    private ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void initialize() {
        try {
            // Assume the first user is the currently logged-in user for simplicity
            UserAccount currentUser = readAccounts().get(0);
            Transaction currentTransaction = readTransactions().stream()
                    .filter(t -> t.getUserId().equals(currentUser.getUserId()))
                    .findFirst()
                    .orElse(null);

            linkedAccountLabel.setText(currentUser.getBankLinked());
            accountBalanceLabel.setText(currentTransaction != null ? String.valueOf(currentTransaction.getBalance()) : "0.0");
            userIdLabel.setText(currentUser.getUserId());
            accountRootLabel.setText(currentUser.isUserRoot() ? "parents" : "kids");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<UserAccount> readAccounts() throws IOException {
        File file = Paths.get(ACCOUNT_FILE).toFile();
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<UserAccount>>() {});
        } else {
            throw new IOException("Account file not found");
        }
    }

    private List<Transaction> readTransactions() throws IOException {
        File file = Paths.get(TRANSACTION_FILE).toFile();
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<Transaction>>() {});
        } else {
            throw new IOException("Transaction file not found");
        }
    }

    @FXML
    public void switchToInformation(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/information.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToDeposit(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/deposit.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToTransaction(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/transaction.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToTasks(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/tasks.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToLeaderboard(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/leaderboard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToFinancing(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/financing.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
