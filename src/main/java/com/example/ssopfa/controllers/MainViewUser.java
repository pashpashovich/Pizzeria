package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainViewUser {

    @FXML
    private Button toMenu;

    @FXML
    private Button toBasket;

    @FXML
    private Button exitButton;

    @FXML
    private Label name;

    @FXML
    public void initialize() {
        name.setText(Main.getCustomer().getLogin());
        System.out.println(Main.getCustomer().getFIO());
        System.out.println(Main.getCustomer().getCart());
    }

    @FXML
    private void onMenu(ActionEvent event) {
        Main.showMenu();
    }

    @FXML
    private void onBasket(ActionEvent event) {
        Main.showBasket();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }

}
