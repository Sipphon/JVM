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
    private List<House> apartments;

    public Houses() {
        apartments = new ArrayList<>();
    }

    public List<House> getApartments() {
        return apartments;
    }

    public void setApartments(List<House> apartments) {
        this.apartments = apartments;
    }

    public void addHouse(int numberOfApartments, double square, int floor, int numberOfRooms, String street) {
        House house = new House(numberOfApartments, square, floor, numberOfRooms, street);
        apartments.add(house);
    }

    public boolean remove(int id) {
        return apartments.removeIf(house -> house.getId() == id);
    }

    public boolean removeAll() {
        apartments.clear();
        return true;
    }

    public List<House> findByNumberOfRooms(int numberOfRooms) {
        return apartments.stream()
                .filter(house -> house.getNumberOfRooms() == numberOfRooms)
                .toList();
    }

    public List<House> findByNumberOfRooms(int numberOfRooms, int minFloor, int maxFloor) {
       return apartments.stream()
                .filter(house -> house.getNumberOfRooms() == numberOfRooms && house.getFloor() >= minFloor && house.getFloor() <= maxFloor)
                .toList();
    }

    public List<House> filterBySquare(double square) {
       return apartments.stream()
                .filter(house -> house.getSquare() > square)
                .sorted(Comparator.comparingDouble(House::getSquare).reversed().thenComparingInt(House::getFloor))
                .toList();
    }

    public List<House> filterBySquare() {
       return apartments.stream()
                .sorted(Comparator.comparingDouble(House::getSquare))
                .toList();

    }

    public Map<Integer, List<House>> mapFloors() {
       return apartments.stream()
                .collect(Collectors.groupingBy(House::getFloor));
    }

    public List<Integer> listFloors() {
       return apartments.stream()
                .map(House::getFloor)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    public void showAll() {
        if (apartments.isEmpty()) {
            return;
        }
        apartments.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "Houses{" +
                "houses=" + apartments +
                '}';
    }
}

