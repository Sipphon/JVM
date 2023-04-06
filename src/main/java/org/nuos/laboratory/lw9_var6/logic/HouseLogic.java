package org.nuos.laboratory.lw9_var6.logic;

import lombok.SneakyThrows;
import org.nuos.laboratory.lw9_var6.connection.ConnectToDB;
import org.nuos.laboratory.lw9_var6.entity.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HouseLogic {
    Connection connection = ConnectToDB.getConnection();
    ResultSet rs = null;
    PreparedStatement stmt = null;

    @SneakyThrows
    public List<House> showAllApartments() {
        ArrayList<House> houses = new ArrayList<>();

        stmt = connection.prepareStatement("SELECT * FROM  house");
        rs = stmt.executeQuery();
        while (rs.next()) {
            House house = new House(
                    rs.getInt("id"),
                    rs.getInt("numberOfApartment"),
                    rs.getDouble("square"),
                    rs.getInt("floor"),
                    rs.getInt("numberOfRooms"),
                    rs.getString("street"));
            houses.add(house);
        }
        return houses;
    }


    @SneakyThrows
    public List<House> findByNumberOfRooms(int rooms) {
        ArrayList<House> houses = new ArrayList<>();

        stmt = connection.prepareStatement("SELECT * FROM  house WHERE numberOfRooms=?");
        stmt.setInt(1, rooms);
        rs = stmt.executeQuery();
        while (rs.next()) {
            House house = new House(
                    rs.getInt("id"),
                    rs.getInt("numberOfApartment"),
                    rs.getDouble("square"),
                    rs.getInt("floor"),
                    rs.getInt("numberOfRooms"),
                    rs.getString("street"));
            houses.add(house);
        }
        return houses;
    }

    @SneakyThrows
    public List<House> findByNumberOfRooms(int rooms, int minFloor, int maxFloor) {
        ArrayList<House> houses = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM  house WHERE (numberOfRooms=? AND floor>=? AND floor<=?)");
        stmt.setInt(1, rooms);
        stmt.setInt(2, minFloor);
        stmt.setInt(3, maxFloor);
        rs = stmt.executeQuery();
        while (rs.next()) {
            House house = new House(
                    rs.getInt("id"),
                    rs.getInt("numberOfApartment"),
                    rs.getDouble("square"),
                    rs.getInt("floor"),
                    rs.getInt("numberOfRooms"),
                    rs.getString("street"));
            houses.add(house);
        }
        return houses;
    }

    @SneakyThrows
    public List<House> filterBySquare(double square) {
        ArrayList<House> houses = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM  house WHERE square>=? order by square DESC, floor;");
        stmt.setDouble(1, square);
        rs = stmt.executeQuery();
        while (rs.next()) {
            House house = new House(
                    rs.getInt("id"),
                    rs.getInt("numberOfApartment"),
                    rs.getDouble("square"),
                    rs.getInt("floor"),
                    rs.getInt("numberOfRooms"),
                    rs.getString("street"));
            houses.add(house);
        }
        return houses;
    }

    @SneakyThrows
    public List<House> filterBySquare() {
        ArrayList<House> houses = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT * FROM  house order by square;");
        rs = stmt.executeQuery();
        while (rs.next()) {
            House house = new House(
                    rs.getInt("id"),
                    rs.getInt("numberOfApartment"),
                    rs.getDouble("square"),
                    rs.getInt("floor"),
                    rs.getInt("numberOfRooms"),
                    rs.getString("street"));
            houses.add(house);
        }
        return houses;
    }

    @SneakyThrows
    public List<Integer> listFloors() {
        ArrayList<Integer> floors = new ArrayList<>();
        stmt = connection.prepareStatement("SELECT floor FROM  house;");
        rs = stmt.executeQuery();
        while (rs.next()) {
            floors.add(rs.getInt("floor"));
        }
        return floors;
    }

    @SneakyThrows
    public Map<Integer, List<Integer>> mapFloors() {

        Map<Integer, List<Integer>> floors = new HashMap<>() {
        };
        stmt = connection.prepareStatement("SELECT floor, GROUP_CONCAT(numberOfApartment) AS apartment_list FROM house GROUP BY floor;");
        rs = stmt.executeQuery();
        while (rs.next()) {
            List<Integer> apartmentList = new ArrayList<>();
            int floor = rs.getInt("floor");
            String apartments = rs.getString("apartment_list");
            String[] apartmentInFloors = apartments.split(",");
            for (String apartmentId : apartmentInFloors) {
                apartmentList.add(Integer.parseInt(apartmentId.trim()));
                floors.put(floor, apartmentList);
            }
        }
        return floors;
    }
public void closeConnection(){
    try {
        connection.close();
        rs.close();
        stmt.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
}

