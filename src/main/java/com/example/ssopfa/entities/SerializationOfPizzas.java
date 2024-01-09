package com.example.ssopfa.entities;

import java.io.*;
import java.util.ArrayList;

public class SerializationOfPizzas {

    public static void serializator(ArrayList<Pizza> arrayList) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("Pizzas");
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            fileOutputStream.close();
            objectOutputStream.close();
        }
    }

    public static ArrayList<Pizza> deserializator() {
        try(FileInputStream fileInputStream = new FileInputStream("Pizzas");
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream)
        ) {
            return (ArrayList<Pizza>) objectInputStream.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Файл не найден");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        return new ArrayList<>();
    }
}
