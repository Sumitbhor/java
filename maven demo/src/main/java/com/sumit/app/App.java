package com.sumit.app;

import com.google.gson.Gson;

public class App {
    public static void main(String[] args) {
        Gson gson = new Gson();

        Student s = new Student("Sumit", 24);
        String json = gson.toJson(s);

        System.out.println("JSON: " + json);
    }
}

class Student {
    String name;
    int age;

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
