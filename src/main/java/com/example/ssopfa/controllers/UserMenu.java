package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class UserMenu {

    Customer customer = null;


    @FXML
    private Button exitButton;

    @FXML
    private TableView<Pizza> pizzaTableView;

    @FXML
    private TableColumn<Pizza, String> nameColumn;

    @FXML
    private TableColumn<Pizza, Double> sizeColumn;

    @FXML
    private TableColumn<Pizza, Double> priceColumn;

    @FXML
    private Button toMain;

    @FXML
    private Button addToBusket;

    @FXML
    public void initialize() {
        customer= (Customer) Main.getCustomer();
        PizzaList pizzaList = new PizzaList();
        List<Pizza> pizzas = PizzaList.getPizzaList();
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzas);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
    }

    @FXML
    private void toBusket(ActionEvent event) throws IOException {
        customer= Main.getCustomer();
        Pizza selectedPizza = pizzaTableView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            customer.addToCart(selectedPizza);
            Main.showMenu();
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одной пиццы!");
        }
    }

    @FXML
    private void onMain(ActionEvent event) {
        Main.showMainViewUser();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }

}
