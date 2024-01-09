package com.example.ssopfa.entities;

import com.example.ssopfa.Main;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PizzaList {
    private static List<Pizza> pizzaList = new ArrayList<>();
    private boolean flagForFiltering=false;
    private boolean flagForSorting=false;
    private List<Pizza> reserveCart;
    private List<Pizza> reserveCartforSorting;
    private Map<String, Predicate<Pizza>> appliedFiltres = new HashMap<>();

    private Sorting appliedSorting;


    public static List<Pizza> getPizzaList() {
        return pizzaList;
    }

    public PizzaList() {
        pizzaList=SerializationOfPizzas.deserializator();
    }

    public void printList() {
        if (!pizzaList.isEmpty()) {
            int i = 1;
            for (Pizza p : pizzaList) {
                System.out.println(i + ". " + p);
                i++;
            }
        } else System.out.println("Пиццы в асортименте нет");
    }


    public static boolean removeFromList(String name, double size, double price) throws IOException {
        if (!pizzaList.isEmpty()) {
            Pizza pizzaToRemove = null;
            for (Pizza pizza : pizzaList) {
                if (pizza.getName().equals(name) && pizza.getSize() == size && pizza.getPrice() == price) {
                    pizzaToRemove = pizza;
                    break;
                }
            }
            if (pizzaToRemove != null) {
                pizzaList.remove(pizzaToRemove);
                SerializationOfPizzas.serializator((ArrayList<Pizza>) pizzaList);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static  void addPizza (Pizza pizza) throws IOException {
        pizzaList.add(pizza);
        SerializationOfPizzas.serializator((ArrayList<Pizza>) pizzaList);
    }


    public void filter() {
        Scanner scanner;
        if (!pizzaList.isEmpty()) {
            if (flagForFiltering==false) reserveCart=new ArrayList<>(pizzaList);
            List<Integer> chooses = new ArrayList<>();
            int cont = 0, chose = 0;
            while (true) {
                try {
                    System.out.println("Выберите, по каким критериям проводить фильтрацию:\n1.По цене\n2.По размеру");
                    boolean flag3 = true;
                    while (flag3) {
                        try {
                            scanner = new Scanner(System.in);
                            chose = scanner.nextInt();
                            if (chose!=1 && chose!=2) {
                                System.out.println("Неверный выбор... Повторите попытку");
                                continue;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный выбор( Повторите попытку!");
                        }
                        if (chooses.contains(chose))
                            System.out.println("Вы уже выбирали данный критерий! Повторите попытку, пожалуйста)");
                        else {
                            chooses.add(chose);
                            flag3 = false;
                        }
                    }
                    if (chooses.size() == 2) {
                        System.out.println("Все два критерия выбраны!");
                        break;
                    }
                    boolean flag4 = true;
                    while (flag4) {
                        try {
                            System.out.println("Хотите ли добавить ещё критерий для фильтрации?\n1.Да\nДругое число - нет");
                            scanner = new Scanner(System.in);
                            cont = scanner.nextInt();
                            flag4 = false;
                        } catch (InputMismatchException e) {
                            System.out.println("Неверный ввод(... Попробуйте ещё раз)");
                        }
                    }
                    if (cont != 1) break;
                } catch (InputMismatchException e) {
                    System.out.println("Неверный ввод(... Попробуйте ещё раз)");
                }
            }
            double minPrice = 0, maxPrice = 0, minSize = 0, maxSize = 0;
            for (int x : chooses) {
                switch (x) {
                    case 1 -> {
                        while (true)
                        {
                            try {
                                System.out.println("Введите минимальную цену пиццы в рублях: ");
                                scanner = new Scanner(System.in);
                                minPrice = scanner.nextDouble();
                                System.out.println("Введите максимальную цену пиццы в рублях: ");
                                maxPrice = scanner.nextDouble();
                                if (maxPrice>minPrice) break;
                                else System.out.println("Вы ввели какой-то бред... Повторите попытку");
                            } catch (InputMismatchException e) {
                                System.out.println("Неверный ввод... Повторите попытку");
                            }
                        }
                        appliedFiltres.put("Цена", far(x, minPrice, maxPrice, minSize, maxSize));
                    }
                    case 2 -> {
                        while (true)
                        {
                            try {
                                System.out.println("Введите минимальный размер пиццы в см: ");
                                scanner = new Scanner(System.in);
                                minSize = scanner.nextDouble();
                                System.out.println("Введите максимальный размер пиццы в см: ");
                                maxSize = scanner.nextDouble();
                                if (maxSize>minSize) break;
                                else System.out.println("Вы ввели какой-то бред... Повторите попытку");
                            } catch (InputMismatchException e) {
                                System.out.println("Неверный ввод... Повторите попытку");
                            }
                        }
                        appliedFiltres.put("Размер", far(x, minPrice, maxPrice, minSize, maxSize));
                    }
                }
                pizzaList = pizzaList.stream()
                        .filter(far(x, minPrice, maxPrice, minSize, maxSize))
                        .collect(Collectors.toList());
                flagForFiltering=true;
            }
            printList();
        } else System.out.println("Вы не добавили ещё ни одной пиццы в корзину");
    }

    public void sorting() {
        if (!flagForSorting) {
            if (pizzaList.size()>reserveCart.size()) reserveCartforSorting=new ArrayList<>(pizzaList);
            else reserveCartforSorting=new ArrayList<>(reserveCart);
        }
        if (!pizzaList.isEmpty()) {
            Scanner scanner;
            int chose = 0;
            System.out.println("Выберите тип сортировки пицц:\n1.По возрастанию цены\n2.По убыванию цены");
            while (true) {
                try {
                    scanner = new Scanner(System.in);
                    chose = scanner.nextInt();
                    if (chose == 1 || chose == 2) {
                        break;
                    } else System.out.println("Неверный выбор... Повторите попытку");
                } catch (InputMismatchException e) {
                    System.out.println("Неверный выбор( Повторите попытку!");
                }
            }

            if (chose==1) {
                SortingPizzas thread = new SortingPizzas(pizzaList);
                thread.start();
                try {
                    thread.join();
                    System.out.println("Результат сортировки: ");
                    pizzaList = (ArrayList<Pizza>) thread.getAppliances();
                    printList();
                    appliedSorting = Sorting.INCREASING;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                DescendingPizzas thread2 = new DescendingPizzas(pizzaList);
                Thread myThread = new Thread(thread2);
                myThread.start();
                try {
                    myThread.join();
                    System.out.println("Результат сортировки: ");
                    pizzaList = (ArrayList<Pizza>) thread2.getAppliances();
                    printList();
                    appliedSorting = Sorting.DECREASING;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            flagForSorting=true;
        }
        else System.out.println("Вы не добавили ещё ни одного электроприбора");
    }

    public void delSorting() {
        Scanner scanner;
        int choice = 0;
        if (!pizzaList.isEmpty()) {
            System.out.print("Если хотите отменить сортировку,то нажмите 1\nЕсли нет, то нажмите любую другую клавишу\n");
            try {
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод... Повторите попытку");
            }
            if (choice == 1) {
                List<Pizza> sortedList = new ArrayList<>(reserveCartforSorting);
                if (!appliedFiltres.isEmpty()) {
                    for (Map.Entry<String, Predicate<Pizza>> predicate : appliedFiltres.entrySet()) {
                        sortedList = sortedList.stream().filter(predicate.getValue()).toList();
                    }
                }
                pizzaList.clear();
                pizzaList.addAll(sortedList);
                appliedSorting=null;
                System.out.println("Сортировка успешно отменена!");
                printList();
            } else {
                System.out.println("Все данные остались в неизменённом состоянии");
            }
        }
    }




    private Predicate<Pizza> far(int number, double minPrice, double maxPrice, double minSize, double maxSize) {
        switch (number) {
            case 1 -> {
                return (x -> x.getPrice() >= minPrice && x.getPrice() <= maxPrice);
            }
            case 2 -> {
                return (x -> x.getSize() >= minSize && x.getSize() <= maxSize);
            }
        }
        return null;
    }

    public void delFiltres() {
        Scanner scanner=new Scanner(System.in);
        if (!appliedFiltres.isEmpty()) {
            int action;
            int i = 1;
            System.out.println("Примененные фильтры:");
            for (Map.Entry<String, Predicate<Pizza>> t : appliedFiltres.entrySet()) {
                System.out.println(i + ". " + t.getKey());
                i++;
            }
            System.out.print("Выберите фильтр, который хотите отменить: ");
            while (true) {
                try {
                    action = scanner.nextInt();
                    if (action>0 && action<=appliedFiltres.size()) break;
                    else System.out.println("Неверный выбор... Повторите попытку");
                } catch (InputMismatchException e) {
                    System.out.println("Неверный ввод... Повторите попытку");
                }
            }
            int check = 0;
            for (Map.Entry<String, Predicate<Pizza>> t : appliedFiltres.entrySet()) {
                if (check == action - 1) {
                    appliedFiltres.remove(t.getKey());
                    break;
                }
                check++;
            }
            if (!appliedFiltres.isEmpty()) {
                for (Map.Entry<String, Predicate<Pizza>> predicate : appliedFiltres.entrySet()) {
                    pizzaList = reserveCart.stream().filter(predicate.getValue()).toList();
                }
            } else {
                flagForFiltering=true;
                pizzaList = new ArrayList<>(reserveCart);
            }
            if (appliedSorting!=null) {
                if(appliedSorting==Sorting.INCREASING) {
                    SortingPizzas thread = new SortingPizzas(new ArrayList<>(pizzaList));
                    thread.start();
                    try {
                        thread.join();
                        pizzaList = new ArrayList<>(thread.getAppliances());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    DescendingPizzas thread2 = new DescendingPizzas(new ArrayList<>(pizzaList));
                    Thread myThread = new Thread(thread2);
                    myThread.start();
                    try {
                        myThread.join();
                        pizzaList = new ArrayList<>(thread2.getAppliances());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        else {
            System.out.println("Фильтры не были применены");
        }
    }
}
