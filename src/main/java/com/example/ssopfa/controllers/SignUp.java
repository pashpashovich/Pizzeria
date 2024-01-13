package com.example.ssopfa.controllers;


import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Admin;
import com.example.ssopfa.entities.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUp {

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private TextField fio;

    @FXML
    private Button signUpButton;

    @FXML
    private Button signInButton;

    @FXML
    private Button exit;


    @FXML
    private void onSigUpButtonClick(ActionEvent event) throws IOException {
        String inLogin = login.getText();
        String inPassword = password.getText();
        String inFio = fio.getText();
        if (inLogin.isEmpty() || inPassword.isEmpty() || inFio.isEmpty()) {
            Alerts.showNotificationAlert("Заполните все поля");
            return;
        }

        if (!Admin.isUniqueLogin(inLogin)) {
            Alerts.showNotificationAlert("Такой логин уже существует");
            return;
        }
        Admin.addUser(inLogin, inPassword, inFio);
        Alerts.showSuccessAlert("Регистрация прошла успешно!");
        Main.showSignIn();

    }


    @FXML
    private void onSigInButtonClick(ActionEvent event) throws IOException {
        Main.showSignIn();
    }

    @FXML
    private void toExit(ActionEvent event) throws IOException {
        System.exit(0);
    }

}
