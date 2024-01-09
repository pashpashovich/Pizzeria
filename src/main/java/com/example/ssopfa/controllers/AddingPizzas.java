package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class AddingPizzas {

    @FXML
    private Button toMain;

    @FXML
    private Button exitButton;

    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;


    @FXML
    private TableView<Pizza> pizzaTableView;

    @FXML
    private TableColumn<Pizza, String> nameColumn;

    @FXML
    private TableColumn<Pizza, Double> sizeColumn;

    @FXML
    private TableColumn<Pizza, Double> priceColumn;

    @FXML
    private TextField name;
    @FXML
    private TextField size;
    @FXML
    private TextField price;


    @FXML
    public void initialize() {
        PizzaList pizzaList = new PizzaList();
        List<Pizza> pizzas = PizzaList.getPizzaList();
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzas);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
    }

    @FXML
    private void onMain(ActionEvent event) {
        Main.showMainViewAdmin();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }

    @FXML
    private void deletePizza(ActionEvent event) throws IOException {
        Pizza selectedPizza = pizzaTableView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            PizzaList.removeFromList(selectedPizza.getName(), selectedPizza.getSize(), selectedPizza.getPrice());
            Alerts.showSuccessAlert("Пиццы " + selectedPizza.getName() + " успешно удалена!");
            Main.showAdminPizzas();
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одной пиццы!");
        }
    }

    @FXML
    private void addPizza() throws IOException {
        Pizza newPizza = null;
        try {
            if (Double.parseDouble(size.getText()) <= 0 || Double.parseDouble(price.getText()) <= 0)
                throw new NumberFormatException("Размер (цена) пиццы должен быть больше нуля");
            newPizza = new Pizza(name.getText(), Double.parseDouble(size.getText()), Double.parseDouble(price.getText()));
        } catch (NumberFormatException e) {
            name.clear();
            size.clear();
            price.clear();
            Alerts.showErrorAlert("Неверный ввод... Повторите попытку");
            return;
        }
        PizzaList.addPizza(newPizza);
        Alerts.showSuccessAlert("Пицца успешно добавлена в меню!");
        name.clear();
        size.clear();
        price.clear();
        Main.showAdminPizzas();
    }


}
