package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserMenu {

    Customer customer = null;
    private List<Pizza> pizzaCart;
    private boolean flagForFilteringorSorting = false;
    private List<Pizza> reserveCart;

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
    private Button ascSorting;

    @FXML
    private Button descSorting;

    @FXML
    private TextField fromVal;

    @FXML
    private TextField toVal;

    @FXML
    private Button priceButton;

    @FXML
    private Button sizeButton;

    @FXML
    private Button back;

    @FXML
    public void initialize() {
        customer = (Customer) Main.getCustomer();
        PizzaList pizzaList = new PizzaList();
        pizzaCart = PizzaList.getPizzaList();
        reserveCart = new ArrayList<>(pizzaCart);
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzaCart);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
    }

    @FXML
    private void toBusket(ActionEvent event) throws IOException {
        customer = Main.getCustomer();
        Pizza selectedPizza = pizzaTableView.getSelectionModel().getSelectedItem();
        if (selectedPizza != null) {
            customer.addToCart(selectedPizza);
            Main.showMenu();
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одной пиццы!");
        }
    }


    @FXML
    public void handleSorting() throws IOException {
        if (!flagForFilteringorSorting) reserveCart = new ArrayList<>(pizzaCart);
        SortingPizzas thread = new SortingPizzas(pizzaCart);
        thread.start();
        try {
            thread.join();
            pizzaCart = thread.getAppliances();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flagForFilteringorSorting=true;
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzaCart);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
    }

    @FXML
    public void handleDesSorting() throws IOException {
        if (!flagForFilteringorSorting) reserveCart = new ArrayList<>(pizzaCart);
        DescendingPizzas thread = new DescendingPizzas(pizzaCart);
        Thread myThread = new Thread(thread);
        myThread.start();
        try {
            myThread.join();
            pizzaCart = thread.getAppliances();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flagForFilteringorSorting = true;
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzaCart);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
    }

    @FXML
    private void handlePriceFiltering() throws IOException {
        if (!flagForFilteringorSorting) reserveCart = new ArrayList<>(pizzaCart);
        pizzaCart.removeIf(Objects::isNull);
        try {
            if (Double.parseDouble(fromVal.getText()) <= 0 || Double.parseDouble(toVal.getText()) <= 0)
                throw new NumberFormatException("Размер (цена) пиццы должен быть больше нуля");
            double fromPrice = Double.parseDouble(fromVal.getText());
            double toPrice = Double.parseDouble(toVal.getText());
            if (fromPrice > toPrice) {
                Alerts.showErrorAlert("Неверный диапазон... Повторите попытку");
                fromVal.clear();
                toVal.clear();
                return;
            }
            pizzaCart = pizzaCart.stream()
                    .filter(пицца -> пицца.getPrice() >= fromPrice && пицца.getPrice() <= toPrice)
                    .collect(Collectors.toList());
            flagForFilteringorSorting = true;
            pizzaTableView.setItems(FXCollections.observableArrayList(pizzaCart));
        } catch (NumberFormatException e) {
            fromVal.clear();
            toVal.clear();
            Alerts.showErrorAlert("Неверный формат ввода... Повторите попытку");
        }
    }

    @FXML
    private void handleSizeFiltering() throws IOException {
        if (!flagForFilteringorSorting) reserveCart = new ArrayList<>(pizzaCart);
        pizzaCart.removeIf(Objects::isNull);
        try {
            if (Double.parseDouble(fromVal.getText()) <= 0 || Double.parseDouble(toVal.getText()) <= 0)
                throw new NumberFormatException("Размер (цена) пиццы должен быть больше нуля");
            double fromSize = Double.parseDouble(fromVal.getText());
            double toSize = Double.parseDouble(toVal.getText());
            if (fromSize > toSize) {
                Alerts.showErrorAlert("Неверный диапазон... Повторите попытку");
                fromVal.clear();
                toVal.clear();
                return;
            }
            pizzaCart = pizzaCart.stream()
                    .filter(пицца -> пицца.getSize() >= fromSize && пицца.getSize() <= toSize)
                    .collect(Collectors.toList());
            flagForFilteringorSorting = true;
            pizzaTableView.setItems(FXCollections.observableArrayList(pizzaCart));
        } catch (NumberFormatException e) {
            fromVal.clear();
            toVal.clear();
            Alerts.showErrorAlert("Неверный формат ввода... Повторите попытку");
        }
    }

    @FXML
    private void backInfo() throws IOException {
        customer = (Customer) Main.getCustomer();
        List<Pizza> pizzas = reserveCart;
        ObservableList<Pizza> pizzaObservableList = FXCollections.observableArrayList(pizzas);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        pizzaTableView.setItems(pizzaObservableList);
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
