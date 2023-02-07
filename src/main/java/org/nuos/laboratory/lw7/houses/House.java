package org.nuos.laboratory.lw7.houses;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class House {
    @JsonIgnore
    transient private int id;
    private static int tempId=1;
    private int numberOfApartment;
    private double square;
    private int floor;
    private int numberOfRooms;
    private String Street;

    public House(int numberOfApartment, double square, int floor, int numberOfRooms, String street) {
        this.id = tempId;
        this.numberOfApartment = numberOfApartment;
        this.square = square;
        this.floor = floor;
        this.numberOfRooms = numberOfRooms;
        Street = street;
        tempId++;
    }

    public House() {
        this(0,0.0,0,0,"");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberOfApartment() {
        return numberOfApartment;
    }

    public void setNumberOfApartment(int numberOfApartment) {
        this.numberOfApartment = numberOfApartment;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id && numberOfApartment == house.numberOfApartment && Double.compare(house.square, square) == 0 && floor == house.floor && numberOfRooms == house.numberOfRooms && Street.equals(house.Street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberOfApartment, square, floor, numberOfRooms, Street);
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", numberOfApartments=" + numberOfApartment +
                ", square=" + square +
                ", floor=" + floor +
                ", numberOfRooms=" + numberOfRooms +
                ", Street='" + Street + '\'' +
                '}';
    }
}
