package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Alerts;
import com.example.ssopfa.entities.Pizza;
import com.example.ssopfa.entities.PizzaList;
import com.example.ssopfa.entities.SerializationOfPizzas;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
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

    private List<Pizza> pizzas;

    @FXML
    public void initialize() {
        PizzaList pizzaList1=new PizzaList();
        pizzas=PizzaList.getPizzaList();
        setPizzaList(pizzas);
    }

    public void setPizzaList(List<Pizza> pizzas) {
        pizzaList = FXCollections.observableArrayList(pizzas);
        pizzaComboBox.setItems(pizzaList);
    }

    @FXML
    private void saveChanges(ActionEvent event) throws IOException {
        Pizza selectedPizza = pizzaComboBox.getValue();
        int selectedIndex = pizzaList.indexOf(selectedPizza);
        try {
            if (Double.parseDouble(editSizeField.getText()) <= 0 || Double.parseDouble(editPriceField.getText()) <= 0)
                throw new NumberFormatException("Размер (цена) пиццы должен быть больше нуля");
            pizzaList.get(selectedIndex).setName(editNameField.getText());
            pizzaList.get(selectedIndex).setSize(Double.parseDouble(editSizeField.getText()));
            pizzaList.get(selectedIndex).setPrice(Double.parseDouble(editPriceField.getText()));
        } catch (NumberFormatException e) {
            editNameField.clear();
            editSizeField.clear();
            editPriceField.clear();
            Alerts.showErrorAlert("Неверный ввод... Повторите попытку");
            return;
        }
        Alerts.showSuccessAlert("Пицца "+pizzaList.get(selectedIndex).getName()+ " успешно изменена!");
        pizzas=new ArrayList<>(pizzaList);
        SerializationOfPizzas.serializator((ArrayList<Pizza>) pizzas);
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
