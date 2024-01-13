package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Alerts;
import com.example.ssopfa.entities.Customer;
import com.example.ssopfa.entities.Pizza;
import com.example.ssopfa.entities.PizzaList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Basket {
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
    private Label sum;

    @FXML
    private Button delButton;


    @FXML
    public void initialize() {
        customer = (Customer) Main.getCustomer();
        List<Pizza> cart = customer.getCart();
        if (cart == null) cart = new ArrayList<>();
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(cart);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
        sum.setText(String.valueOf(customer.calculateCartSum()));
    }


    @FXML
    private void onMain(ActionEvent event) {
        Main.showMainViewUser();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }

    @FXML
    private void deletePizza(ActionEvent event) throws IOException {
        customer = (Customer) Main.getCustomer();
        Pizza selectedPizza = pizzaTableView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            customer.removeFromCart(selectedPizza.getName(), selectedPizza.getSize(), selectedPizza.getPrice());
            Alerts.showSuccessAlert("Пицца " + selectedPizza.getName() + " успешно удалена из корзины!");
            Main.showBasket();
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одной пиццы!");
        }
    }
}
