package org.nuos.laboratory.lw8.logic;

import org.nuos.laboratory.lw8.houses.House;

import java.util.*;
import java.util.stream.Collectors;

public class Houses {

    //  A. список квартир, які мають задане число кімнат;
    //  B. список квартир, які мають задане число кімнат та розташовані на поверсі, який знаходиться в заданому проміжку;
    //  C. список квартир, які мають площу, що перевищує задану в порядку спадання площі. Якщо площа однакова – то в порядку зростання поверху;
    //  D. список всіх квартир, в порядку зростання площі;
    //  E. список поверхів на яких розташовані квартири, у порядку спадання;
    //  F. для кожного поверху визначити список квартир.
    private List<House> houses;

    public Houses() {
        houses = new ArrayList<>();
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void addHouse(int numberOfApartments, double square, int floor, int numberOfRooms, String street) {
        House house = new House(numberOfApartments, square, floor, numberOfRooms, street);
        houses.add(house);
    }

    public boolean remove(int id) {
        return houses.removeIf(house -> house.getId() == id);
    }

    public boolean removeAll() {
        houses.clear();
        return true;
    }

    public void findByNumberOfRooms(int numberOfRooms) {
        houses.stream()
                .filter(house -> house.getNumberOfRooms() == numberOfRooms)
                .forEach(System.out::println);
    }

    public void findByNumberOfRooms(int numberOfRooms, int minFloor, int maxFloor) {
        houses.stream()
                .filter(house -> house.getNumberOfRooms() == numberOfRooms && house.getFloor()>=minFloor && house.getFloor()<=maxFloor)
                .forEach(System.out::println);
    }

    public void filterBySquare(double square) {
        houses.stream()
                .filter(house -> house.getSquare()>square)
                .sorted(Comparator.comparingInt(House::getFloor))
                .sorted(Comparator.comparingDouble(House::getSquare).reversed())
                .forEach(System.out::println);
    }

    public void filterBySquare() {
        houses.stream()
                .sorted(Comparator.comparingDouble(House::getSquare))
                .forEach(System.out::println);

    }

    public void MapFloors() {
        houses.stream()
                .collect(Collectors.groupingBy(House::getFloor))
                .forEach((integer, houses1) ->{ System.out.println("Floor: "+ integer);
                    System.out.println(houses1);});
    }

    public void ListFloors() {
        houses.stream()
                .map(House::getFloor)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(o-> System.out.print(o+"\t"));
    }

    public void showAll() {
        if (houses.isEmpty()) {
            return;
        }
        houses.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Houses{" +
                "houses=" + houses +
                '}';
    }
}

