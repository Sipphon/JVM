package org.nuos.laboratory.lw8;

import org.nuos.laboratory.lw8.file.FileProcessor;
import org.nuos.laboratory.lw8.logic.for_enter_data.ForInput;
import org.nuos.laboratory.lw8.logic.Houses;
import org.nuos.laboratory.lw8.logic.for_enter_data.Types;
import org.nuos.laboratory.lw8.menu.Menu;
import org.nuos.laboratory.lw8.menu.MenuItem;

import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Houses houses = new Houses();

        ForInput forInput = new ForInput();

        FileProcessor fileProcessor = new FileProcessor();

        houses.setApartments(fileProcessor.readFile());

        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem("Exit", () -> System.exit(0)),

                new MenuItem("Add the apartment", () -> {
                    int value = forInput.enterData("Enter the number of apartment:", Types.INTEGER);
                    double square = forInput.enterData("Enter the square of apartment:", Types.DOUBLE);
                    int rooms = forInput.enterData("Enter the number of rooms in the apartment:", Types.INTEGER);
                    int floor = forInput.enterData("Enter the floor of apartment:", Types.INTEGER);
                    String street = forInput.enterData("Enter the street:", Types.STRING);
                    houses.addHouse(value, square, floor, rooms, street);
                    fileProcessor.writeFile(houses);
                }),
                new MenuItem("List of all apartments", houses::showAll),

                new MenuItem("Clear the list", () -> {
                    houses.removeAll();
                    fileProcessor.writeFile(houses);
                }),

                new MenuItem("Remove the apartment by id", () -> {
                    int id = forInput.enterData("Enter the id of apartment:", Types.INTEGER);
                    houses.remove(id);
                    fileProcessor.writeFile(houses);
                }),
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
                    System.out.println( houses.filterBySquare(square));
                }),
                new MenuItem("List of apartments which sorted by square", ()-> System.out.println(houses.filterBySquare())),

                new MenuItem("List of floors which have an apartments:",()-> System.out.println(houses.listFloors())),

                new MenuItem("All apartments for each floor", ()-> System.out.println(houses.mapFloors())));


        Menu menu = new Menu(menuItems);
        menu.run();
    }
}
