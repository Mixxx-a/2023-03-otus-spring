package ru.sladkov.otus.spring.hw18.service.impl;

import java.util.Random;

public class Util {

    public static void sleepRandomly() {
        Random rand = new Random();
        int randomNum = rand.nextInt(3) + 1;
        if (randomNum == 3) {
            System.out.println("Demonstrating Hystrix");
            try {
                System.out.println("Start sleeping...." + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted...." + System.currentTimeMillis());
            }
        }
    }
}
