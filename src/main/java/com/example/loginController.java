package com.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class loginController {

    @FXML
    private TextField accountField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private RadioButton parentRadio;

    @FXML
    private RadioButton childRadio;

    private static final String ACCOUNT_FILE = "src/main/resources/account.json";
    private ObjectMapper mapper = new ObjectMapper();

    @FXML
    public void handleLogin(ActionEvent event) {
        String userId = accountField.getText();
        String password = passwordField.getText();
        boolean isParent = parentRadio.isSelected();
        boolean isChild = childRadio.isSelected();

        if (userId.isEmpty() || password.isEmpty() || (!isParent && !isChild)) {
            accountField.setPromptText("plz input valid content");
            return;
        }

        try {
            List<UserAccount> accounts = readAccounts();
            for (UserAccount account : accounts) {
                if (account.getUserId().equals(userId) && account.getPassword().equals(password)) {
                    if ((isParent && account.isUserRoot()) || (isChild && !account.isUserRoot())) {
                        switchToInformation(event);
                        return;
                    }
                }
            }
            accountField.setPromptText("plz input valid content");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSignup(ActionEvent event) {
        switchToSignup(event);
    }

    private List<UserAccount> readAccounts() throws IOException {
        File file = new File(ACCOUNT_FILE);
        if (file.exists()) {
            return mapper.readValue(file, new TypeReference<List<UserAccount>>() {});
        } else {
            throw new IOException("Account file not found");
        }
    }

    private void switchToInformation(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/information.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToSignup(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/signup.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
