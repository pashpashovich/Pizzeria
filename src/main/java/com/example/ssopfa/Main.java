package com.example.ssopfa;

import com.example.ssopfa.entities.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main  extends Application  {

    private static Admin admin;

    private static Customer customer;
    private static Stage primaryStage;
    public static Admin getAdmin() {
        return admin;
    }

    public static Customer getCustomer() {
        return customer;
    }
    public static void setAdmin(Admin admin) {
        Main.admin = admin;
    }

    public static void setCustomer(Customer customer) {
        Main.customer = customer;
    }


    @Override
    public void start(Stage stage) throws IOException {
//        List<User> arrayList = new ArrayList<>();
//        arrayList.add(new Admin("pashpashovich","pasha22",true));
//        arrayList.add(new Admin("pasha1","pasha12",true));
//        arrayList.add(new Admin("pasha2","pasha12",true));
//        arrayList.add(new Customer("pasha3","pasha13","Косович Павел Владимирович", true,null));
//        arrayList.add(new Customer("pasha4","pasha13","Косович ВАпро ТоПАв",false,null));
//        arrayList.add(new Customer("pasha5","pasha13","Косович Вjhпро ТоПАв",false,null));
//        SerializationOfUsers.serializator((ArrayList<User>) arrayList);
//        PizzaList pizzaList = new PizzaList();
//        SerializationOfPizzas.serializator((ArrayList<Pizza>) PizzaList.getPizzaList());
        primaryStage = stage;
        primaryStage.setResizable(false);
        Image image = new Image(getClass().getResource("/images/logo.png").toExternalForm());
        stage.getIcons().add(image);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Main.class.getResource("/com/example/ssopfa/style.css").toExternalForm());
        stage.setTitle("Авторизация");
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showMainViewAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view-admin.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Администратор");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMainViewUser() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view-user.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Пользователь");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("sign-up.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Регистрация");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showSignIn() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("authorization.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Авторизация");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAdminUsers() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("users.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Пользователи");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAdminPizzas() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("addPizzas.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Добавление пиццы");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("userMenu.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Меню");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showBasket() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("basket.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Корзина");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
