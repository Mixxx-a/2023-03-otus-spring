package ru.sladkov.otus.spring.hw15.domain;

public class Car {

    private Engine engine;

    private CarBody body;

    private final CarOrderSpecification carOrderSpecification;

    public Car(CarOrderSpecification carOrderSpecification) {
        this.carOrderSpecification = carOrderSpecification;
    }

    public CarOrderSpecification getCarOrderSpecification() {
        return carOrderSpecification;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setBody(CarBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Car[Engine=" + engine + ", Body=" + body + ", Spec=" + carOrderSpecification + "]";
    }
}
