package org.nuos.laboratory.lw9_var6.logic.menu;

import org.nuos.laboratory.lw9_var6.logic.HouseLogic;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private boolean running = false;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Menu(List<MenuItem> menuItems) {

        this.menuItems.addAll(menuItems);
    }

    public void show() {

        for (int i = 0; i < menuItems.size(); i++) {

            System.out.println(i + " - " + menuItems.get(i).getName());
        }

    }

    public int getInput() {

        int select = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chose function: ");

        try {

            select = scanner.nextInt();
            scanner.nextLine();

            if (select > menuItems.size() - 1 || select < 0) {

                System.out.println("Incorrect number of function!");
                run();
            }

        } catch (InputMismatchException err) {

            System.out.println("Incorrect number of function!");
            run();
        }

        return select;
    }

    public void perform(int select) {

        running = true;
        this.menuItems.get(select).runMethod();

        running = quit();
        if (!running){

            System.exit(0);
        }

    }

    public boolean quit() {

        int choose = 0;
        System.out.println("Do u want continue? 1 - yes, 0 - no");
        Scanner scanner = new Scanner(System.in);

        try {

            choose = scanner.nextInt();
            scanner.nextLine();

            while (choose < 0 || choose > 1) {

                System.out.println("Entered incorrect data!");
                System.out.println("Do u want continue? 1 - yes, 0 - no");
                choose = scanner.nextInt();
                scanner.nextLine();
            }

        } catch (InputMismatchException err) {

            System.out.println("Entered incorrect data!");
            run();
        }

        if (choose == 1) {

            return true;
        } else {
            HouseLogic houseLogic=new HouseLogic();
            houseLogic.closeConnection();
            return false;
        }
    }

    public void run() {

        int select;

        do {

            show();
            select = getInput();
            perform(select);

        } while (running);
    }
}
