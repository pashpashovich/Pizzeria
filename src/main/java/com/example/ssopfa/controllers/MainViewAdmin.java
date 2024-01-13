package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainViewAdmin {

    @FXML
    private Button exitButton;

    @FXML
    private Button toUsers;

    @FXML
    private Button toPizzas;

    @FXML
    private Button editButton;

    @FXML
    private Label name;

    @FXML
    public void initialize() {
        name.setText(Main.getAdmin().getLogin());
    }


    @FXML
    private void onUsers(ActionEvent event) {
        Main.showAdminUsers();
    }

    @FXML
    private void onPizzas(ActionEvent event) {
        Main.showAdminPizzas();
    }

    @FXML
    private void onEdit(ActionEvent event) {
        Main.showEditPizzas();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }
}
