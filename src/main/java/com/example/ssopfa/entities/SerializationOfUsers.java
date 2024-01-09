package com.example.ssopfa.entities;


import java.io.*;
import java.util.ArrayList;

public class SerializationOfUsers {
    public static void serializator(ArrayList<User> arrayList) throws IOException {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("Users");
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

    public static ArrayList<User> deserializator() {
        try(FileInputStream fileInputStream = new FileInputStream("Users");
            ObjectInputStream objectInputStream= new ObjectInputStream(fileInputStream)
        ) {
            return (ArrayList<User>) objectInputStream.readObject();
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

