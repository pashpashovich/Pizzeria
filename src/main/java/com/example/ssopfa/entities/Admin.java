package com.example.ssopfa.entities;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Admin extends User implements Serializable {
    private static ArrayList<User> users;

    public Admin(String login, String password, boolean hasAccess) {
        super(login, password, hasAccess);
    }
//    public void addToList(PizzaList list){
//        list.addToList();
//    }
//    public void removeFromList(PizzaList list){
//        list.removeFromList();
//    }

    public static ArrayList<User> getUsers() {
        users = SerializationOfUsers.deserializator();
        return users;
    }

    public static ArrayList<User> getNowUsers() {
        return users;
    }

    public static List<Customer> getCustomers() {
        return getNowUsers().stream()
                .filter(user -> user instanceof Customer)
                .map(user -> (Customer) user)
                .collect(Collectors.toList());
    }


    public static User getExactUser(String login, String password) {
        getUsers();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    public static void setExactUser(String login, String password, boolean hasAccess) throws IOException {
        getUsers();
        for (User user : users) {
            if (user != null) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password) && (user instanceof Customer || user instanceof Admin)) {
                    user.setHasAccess(hasAccess);
                }
            }
        }
        SerializationOfUsers.serializator((ArrayList<User>) users);
    }

    public static void resetExactCustomer(Customer customer) throws IOException {
        getUsers();
        User delUser = null;
        for (User user : users) {
            if (user.getLogin().equals(customer.getLogin()) && user.getPassword().equals(customer.getPassword()))
                delUser = user;
        }
        users.remove(delUser);
        users.add(customer);
        SerializationOfUsers.serializator((ArrayList<User>) users);
    }
    public static boolean isUniqueLogin(String login) {
        users = (ArrayList<User>) getUsers();
        for (User user : users) {
            if (login.equals(user.getLogin()))
                return false;
        }
        return true;
    }

    public static void addUser(String login, String password, String FIO) throws IOException {
        users.add(new Customer(login, password, FIO, false,null));
        SerializationOfUsers.serializator((ArrayList<User>) users);
    }

    public static void delExactUser(String login, String password) throws IOException {
        getUsers();
        User delUser = null;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password))
                delUser = user;
        }
        users.remove(delUser);
        SerializationOfUsers.serializator((ArrayList<User>) users);
    }

    public static void setExactUserToAdmin(String login, String password, boolean hasAccess) throws IOException {
        getUsers();
        User delUser = null;
        Admin newAdmin = null;
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password) && user instanceof Customer) {
                delUser = user;
                newAdmin = new Admin(login, password, hasAccess);
            }
        }
        users.remove(delUser);
        users.add(newAdmin);
        SerializationOfUsers.serializator((ArrayList<User>) users);
    }

    public static void changeAccessExactUser(String login, String password) throws IOException {
        getUsers();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getLogin().equals(login) && user.getPassword().equals(password) && user.getClass() == Customer.class) {
                user.setHasAccess(!user.isHasAccess());
                iterator.remove();
                User chUser = new Customer(user.getLogin(), user.getPassword(), ((Customer) user).getFIO(), user.isHasAccess(),((Customer) user).getCart());
                users.add(chUser);
                SerializationOfUsers.serializator((ArrayList<User>) users);
                break;
            }
        }
    }

}
