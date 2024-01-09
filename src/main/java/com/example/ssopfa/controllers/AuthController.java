package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Admin;
import com.example.ssopfa.entities.Alerts;
import com.example.ssopfa.entities.Customer;
import com.example.ssopfa.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AuthController {
    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authButton;

    @FXML
    private Button signUpButton;

    @FXML
    private void onAuthButtonClick(ActionEvent event) throws IOException {
        String login = loginField.getText();
        String password = passwordField.getText();

        if (login.isEmpty() || password.isEmpty()) {
            Alerts.showNotificationAlert("Введите логин и пароль");
            return;
        }
        User user = Admin.getExactUser(login, password);
        if (user == null) {
            Alerts.showErrorAlert("Неверный логин или пароль");
            return;
        }
        if (!user.isHasAccess()) {
            Alerts.showNotificationAlert("У вас нет доступа в систему(");
            return;
        }
        Admin.setExactUser(user.getLogin(),user.getPassword(),user.isHasAccess());
        if (user.getClass().equals(Admin.class)) {
            Main.setAdmin((Admin) user);
            Main.showMainViewAdmin();
        } else if (user.getClass().equals(Customer.class)) {
            Main.setCustomer((Customer) user);
            Main.showMainViewUser();
        } else Main.showMainViewUser();
    }

    @FXML
    private void onSignUpButtonClick(ActionEvent event) {
        Main.showSignUp();
    }
}
