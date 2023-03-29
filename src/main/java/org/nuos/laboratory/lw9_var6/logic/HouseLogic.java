package org.nuos.laboratory.lw9_var6.logic;

import org.nuos.laboratory.lw9_var6.entity.House;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HouseLogic {
    private List<House> apartments;

    public HouseLogic() {
        apartments = new ArrayList<>();
    }

    public List<House> getApartments() {
        return apartments;
    }

    public void add(House house){
        apartments.add(house);
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

