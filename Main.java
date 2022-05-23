package com.company;
import jdk.swing.interop.LightweightContentWrapper;

import java.util.ArrayList;
import java.util.List;

abstract class Logistic{
    String name;
    List<String> info = new ArrayList<>();
    public String toString(){
        return "Тип перевозки: " + name + "\n" + info;
    }
}

class Air extends Logistic{
    public Air(){
        name = "Воздушный";
        info.add("Тип транспорта: Самолёт");
        info.add("Грузоподъёмность: 5000 тонн");
        info.add("Стоимость 1 перевозки: 80.000$");
    }
}

class Truck extends Logistic{
    public Truck(){
        name = "Наземный";
        info.add("Тип транспорта: Грузовик");
        info.add("Грузоподъёмность: 1000 тонн");
        info.add("Стоимость 1 перевозки: 30.000$");
    }
}

class Ship extends Logistic{
    public Ship(){
        name = "Водный";
        info.add("Тип транспорта: Корабль");
        info.add("Грузоподъёмность: 10000 тонн");
        info.add("Стоимость 1 перевозки: 150.000$");
    }
}

class SighAir extends SignAContract{
    public Logistic signContract(){
        return new Air();
    }
}

class SighTruck extends SignAContract{
    public Logistic signContract(){
        return new Truck();
    }
}

class SighShip extends SignAContract{
    public Logistic signContract(){
        return new Ship();
    }
}

abstract class SignAContract{
    public abstract Logistic signContract();
}


public class Main {
    public static void main(String[] args) {
        SignAContract ship = new SighShip();
        SignAContract air = new SighAir();
        SignAContract truck = new SighTruck();
        Logistic myShip = ship.signContract();
        Logistic myAir = air.signContract();
        Logistic myTruck = truck.signContract();
        System.out.println(myShip + "\n");
        System.out.println(myAir + "\n");
        System.out.println(myTruck + "\n");
    }
}