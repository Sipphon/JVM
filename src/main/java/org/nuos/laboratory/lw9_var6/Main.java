package org.nuos.laboratory.lw9_var6;

import lombok.SneakyThrows;
import org.nuos.laboratory.lw9_var6.connection.ConnectToDB;
import org.nuos.laboratory.lw9_var6.entity.House;
import org.nuos.laboratory.lw9_var6.logic.HouseLogic;
import org.nuos.laboratory.lw9_var6.logic.for_enter_data.ForInput;
import org.nuos.laboratory.lw9_var6.logic.for_enter_data.Types;
import org.nuos.laboratory.lw9_var6.logic.menu.Menu;
import org.nuos.laboratory.lw9_var6.logic.menu.MenuItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
      Main main = new Main();
      main.run();
    }

    @SneakyThrows
    private void run() {
        HouseLogic houses = new HouseLogic();
        ForInput forInput = new ForInput();

        try (Connection connection = ConnectToDB.getConnection(); PreparedStatement stmt = connection.prepareStatement("SELECT * FROM  house"); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                House house = new House(rs.getInt("id"),
                        rs.getInt("numberOfApartment"),
                        rs.getDouble("square"),
                        rs.getInt("floor"),
                        rs.getInt("numberOfRooms"),
                        rs.getString("street"));
                houses.add(house);
            }
        }


        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Exit", () -> System.exit(0)),

                new MenuItem("List of all apartments", houses::showAll),

                new MenuItem("List of apartments which have these number of rooms", () -> {
                    int rooms = forInput.enterData("Enter the number of rooms in the apartment:", Types.INTEGER);
                    System.out.println(houses.findByNumberOfRooms(rooms));
                }),

                new MenuItem("List of apartments which have these number of rooms and locate between these floors", () -> {
                    int rooms = forInput.enterData("Enter the number of rooms in the apartment:", Types.INTEGER);
                    int minFloor = forInput.enterData("Enter the minimal floor of apartment:", Types.INTEGER);
                    int maxFloor = forInput.enterData("Enter the maximal floor of apartment:", Types.INTEGER);
                    System.out.println(houses.findByNumberOfRooms(rooms, minFloor, maxFloor));
                }),
                new MenuItem("List of apartments which have a square more then that one", () -> {
                    double square = forInput.enterData("Enter the square of apartment:", Types.DOUBLE);
                    System.out.println(houses.filterBySquare(square));
                }),
                new MenuItem("List of apartments which sorted by square", () -> System.out.println(houses.filterBySquare())),

                new MenuItem("List of floors which have an apartments:", () -> System.out.println(houses.listFloors())),

                new MenuItem("All apartments for each floor", () -> System.out.println(houses.mapFloors())));
        Menu menu = new Menu(menuItems);
        menu.run();
    }
}
