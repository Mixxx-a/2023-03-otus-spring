package ru.sladkov.otus.spring.hw02.model;

public class Student {
    private final String name;

    private final String surname;

    private int points = 0;

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPoints() {
        return points;
    }

    public void addPoint() {
        points++;
    }
}
