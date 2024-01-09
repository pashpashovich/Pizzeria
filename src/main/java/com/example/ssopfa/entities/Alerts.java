package com.example.ssopfa.entities;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class Alerts {

    private static final String ERROR_ICON_PATH = "/images/error.png";
    private static final String WARNING_ICON_PATH = "/images/warning.png";


    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        setAlertIcon(alert, ERROR_ICON_PATH);
        alert.setTitle("Ошибка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showNotificationAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        setAlertIcon(alert, WARNING_ICON_PATH);
        alert.setTitle("Предупреждение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        setAlertIcon(alert, WARNING_ICON_PATH);
        alert.setTitle("Удача");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private static void setAlertIcon(Alert alert, String iconPath) {
        StageStyle stageStyle = StageStyle.UNDECORATED;
        Image icon = new Image(Objects.requireNonNull(Alerts.class.getResourceAsStream(iconPath)));
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(icon);
        alert.initStyle(stageStyle);
    }
}

