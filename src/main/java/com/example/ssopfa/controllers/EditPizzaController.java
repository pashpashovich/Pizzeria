package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Alerts;
import com.example.ssopfa.entities.Pizza;
import com.example.ssopfa.entities.PizzaList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class EditPizzaController {

    @FXML
    private ComboBox<Pizza> pizzaComboBox;

    @FXML
    private TextField editNameField;

    @FXML
    private TextField editSizeField;

    @FXML
    private TextField editPriceField;

    @FXML
    private Button saveButton;

    @FXML
    private Button exitButton;
    private ObservableList<Pizza> pizzaList;

    public void setPizzaList(List<Pizza> pizzas) {
        pizzaList = FXCollections.observableArrayList(pizzas);
        pizzaComboBox.setItems(pizzaList);
    }

    @FXML
    private void saveChanges(ActionEvent event) {
        Pizza selectedPizza = pizzaComboBox.getValue();
        int selectedIndex = pizzaList.indexOf(selectedPizza);
        Pizza pizzaToUpdate = pizzaList.get(selectedIndex);
        try {
            if (Double.parseDouble(editSizeField.getText()) <= 0 || Double.parseDouble(editPriceField.getText()) <= 0)
                throw new NumberFormatException("Размер (цена) пиццы должен быть больше нуля");
            pizzaToUpdate = new Pizza(editNameField.getText(), Double.parseDouble(editSizeField.getText()), Double.parseDouble(editPriceField.getText()));
        } catch (NumberFormatException e) {
            editNameField.clear();
            editSizeField.clear();
            editPriceField.clear();
            Alerts.showErrorAlert("Неверный ввод... Повторите попытку");
            return;
        }
        Alerts.showSuccessAlert("Пицца успешно изменена!");
        editNameField.clear();
        editSizeField.clear();
        editPriceField.clear();
        Main.showEditPizzas();
    }


    @FXML
    private void onExit(ActionEvent event) {
        Main.showMainViewAdmin();
    }
}
