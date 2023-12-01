package ru.sladkov.otus.spring.hw15;

public class Util {

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
