package ru.interview.lesson1.exercise2;

//2. Описать ошибки в коде (см. задание в методичке) и предложить варианты оптимизации.

interface Moveable { // FIX -> Movable (поправить грамматику)
    void move();
}

interface Stopable { // FIX -> Stoppable (поправить грамматику)
    void stop();
}

class Engine {} // FIX -> добавить класс
abstract class Car {

    public Engine engine; // FIX ->  Engine  - не определен, определить или импортировать Engine,
                          // заменить public на private

    private String color;

    private String name;

    protected void start() { // FIX -> Лучше определить public void start()
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class LightWeightCar extends Car implements Moveable {
    @Override
    void open() {
        System.out.println("Car is open");
    }
    @Override
    public void move() {
        System.out.println("Car is moving");
    }
}

abstract class Lorry extends Car implements Moveable, Stopable {
    // FIX -> abstract class Lorry extends Car implements Moveable, Stopable
    public void move(){
        System.out.println("Car is moving");
    }
    public void stop(){
        System.out.println("Car is stop");
    }
}