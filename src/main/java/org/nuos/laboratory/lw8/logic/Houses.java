package org.nuos.laboratory.lw8.logic;

import org.nuos.laboratory.lw8.houses.House;

import java.util.*;

public class Houses {

    //  A. список квартир, які мають задане число кімнат;
    //  B. список квартир, які мають задане число кімнат та розташовані на поверсі, який знаходиться в заданому проміжку;
    //  C. список квартир, які мають площу, що перевищує задану в порядку спадання площі. Якщо площа однакова – то в порядку зростання поверху;
    //  D. список всіх квартир, в порядку зростання площі;
    //  E. список поверхів на яких розташовані квартири, у порядку спадання;
    //  F. для кожного поверху визначити список квартир.
    private List<House> houses;

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public Houses() {
        houses = new ArrayList<>();
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

    public List<House> findByNumberOfRooms(int numberOfRooms) {
        if (houses.isEmpty()) {
            return null;
        }
        List<House> tempList = new ArrayList<>();
        houses.forEach(house1 -> {
            if (house1.getNumberOfRooms() == numberOfRooms) {
                tempList.add(house1);
            }
        });
        return tempList;
    }

    public List<House> findByNumberOfRooms(int numberOfRooms, int minFloor, int maxFloor) {
        if (houses.isEmpty()) {
            return null;
        }
        List<House> tempList = new ArrayList<>();
        houses.forEach(house1 -> {
            if (house1.getNumberOfRooms() == numberOfRooms && (house1.getFloor() >= minFloor && house1.getFloor() <= maxFloor)) {
                tempList.add(house1);
            }
        });
        return tempList;
    }

    public List<House> filterBySquare(double square) {
        if (houses.isEmpty()) {
            return null;
        }
        List<House> tempList = new ArrayList<>();
        houses.forEach(house1 -> {
            if (house1.getSquare() > square) {
                tempList.add(house1);
            }
            tempList.sort((o1,o2)->{
                if (o1.getSquare() == o2.getSquare()) {

                    return Double.compare(o1.getFloor(), o2.getFloor());
                }

                return Double.compare(o2.getSquare(), o1.getSquare());
            });
        });
        return tempList;
    }

    public List<House> filterBySquare() {
        if (houses.isEmpty()) {
            return null;
        }
        List<House> tempList = new ArrayList<>();
        houses.forEach(house1 -> {
            tempList.add(house1);
            tempList.sort(Comparator.comparing(House::getSquare));
        });
        return tempList;
    }

    public Map<Integer, List<House>> MapFloors() {
        Map<Integer, List<House>> listFloors = new HashMap<>();
        houses.forEach(house -> {
            if(!listFloors.containsKey(house.getFloor())){
            listFloors.putIfAbsent(house.getFloor(),new ArrayList<>());
            }
            listFloors.get(house.getFloor()).add(house);
        });
        return listFloors;
    }
    public List<Integer> ListFloors(){
        Set<Integer> listFloors=new TreeSet<>();
        houses.forEach(house -> listFloors.add(house.getFloor()));
        List<Integer> list=new ArrayList<>(listFloors);
        list.sort(Comparator.reverseOrder());

        return list;
    }
    public void showAll(){
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

