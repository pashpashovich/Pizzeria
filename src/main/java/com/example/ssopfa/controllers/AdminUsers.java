package com.example.ssopfa.controllers;

import com.example.ssopfa.Main;
import com.example.ssopfa.entities.Admin;
import com.example.ssopfa.entities.Alerts;
import com.example.ssopfa.entities.User;
import javafx.beans.property.SimpleStringProperty;
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

public class AdminUsers {

    @FXML
    private Button exitButton;

    @FXML
    private TableView<User> userTableView;

    @FXML
    private TableColumn<User, String> loginColumn;

    @FXML
    private TableColumn<User, String> roleColumn;

    @FXML
    private TableColumn<User, Boolean> accessColumn;
    @FXML
    private Button delButton;

    @FXML
    private Button upButton;

    @FXML
    private Button chButton;
    @FXML
    private Button toMain;

    @FXML
    public void initialize() {
        List<User> usersList = Admin.getUsers();
        ObservableList<User> userObservableList = FXCollections.observableArrayList(usersList);
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        roleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClass().getSimpleName()));
        accessColumn.setCellValueFactory(new PropertyValueFactory<>("hasAccess"));
        userTableView.setItems(userObservableList);
    }

    @FXML
    private void onDel() throws IOException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (selectedUser.getClass() == Admin.class) {
                Alerts.showNotificationAlert("Вы не можете удалить администратора!");
                Main.showAdminUsers();
            } else {
                Admin.delExactUser(selectedUser.getLogin(), selectedUser.getPassword());
                Alerts.showSuccessAlert("Пользователь " + selectedUser.getLogin() + " успешно удалён!");
                Main.showAdminUsers();
            }
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одного пользователя!");
        }
    }

    @FXML
    private void onUpgrade() throws IOException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (selectedUser.getClass() == Admin.class) {
                Alerts.showNotificationAlert("Уже администратор!");
                Main.showAdminUsers();
            } else {
                Admin.setExactUserToAdmin(selectedUser.getLogin(), selectedUser.getPassword(), selectedUser.isHasAccess());
                Alerts.showSuccessAlert("Пользователь " + selectedUser.getLogin() + " успешно повышен до администратора!");
                Main.showAdminUsers();
            }
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одного пользователя!");
        }
    }

    @FXML
    private void onAccess() throws IOException {
        User selectedUser = userTableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (selectedUser.getClass() == Admin.class) {
                Alerts.showNotificationAlert("Нельзя забрать доступ у администратора!");
                Main.showAdminUsers();
            } else {
                Admin.changeAccessExactUser(selectedUser.getLogin(), selectedUser.getPassword());
                Alerts.showSuccessAlert("Режим доступа для пользователя " + selectedUser.getLogin() + " успешно изменен!");
                Main.showAdminUsers();
            }
        } else {
            Alerts.showNotificationAlert("Вы не выбрали ни одного пользователя!");
        }
    }

    @FXML
    private void onMain(ActionEvent event) {
        Main.showMainViewAdmin();
    }

    @FXML
    private void onExit(ActionEvent event) {
        Main.showSignIn();
    }
}
