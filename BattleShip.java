package com.mp1;

import java.util.Scanner;

public class BattleShip {


    static String[][] oceanMap = new String[10][10];

    public static void main(String[] args) {
        System.out.println("**** Welcome to BattleShip game ****");
        System.out.println();
        System.out.println("Right now, the sea is empty.");
        System.out.println();
        printMap();
        deployPlayerShip();
        printMap();
        deployComputerShip();
        printMap();
        battle();
        declareWinner();
    }

    public static void printMap() {
        printTopRow();
        printRows();
        printTopRow();

    }

    public static void printTopRow() {


//        System.out.println("  0123456789  ");
        System.out.print("  ");
        for (int j = 0; j < oceanMap[0].length; j++) {       // Col
            System.out.print(j);
        }
        System.out.println("  ");
    }

    public static void printRows() {
        // print the index from 0 to last index

        for (int i = 0; i < oceanMap.length; ++i) {      // Row
            System.out.print(i + "|");
            for (int j = 0; j < oceanMap[i].length; ++j) {       // Col
                if (oceanMap[i][j] == null)                  // Empty Location
                    System.out.print(" ");
                else if (oceanMap[i][j] == "1")            // Player's Ship Location
                    System.out.print("@");
                else if (oceanMap[i][j] == "2")          // Computer Ship Location
                    System.out.print(" ");               // System.out.print("C"); -- will show you all the ships that the computer deployed
                else
                    System.out.print(oceanMap[i][j]);


            }
            System.out.println("|" + i);
        }

    }

    public static void deployPlayerShip() {
        int shipCount = 1;
        System.out.println();
        System.out.println("Deploy your ships ");
        System.out.println();

        do {
            System.out.print("Enter x-coordinate for your " + shipCount + ". ship: ");
            int x = getLocation();
            System.out.print("Enter y-coordinate for your " + shipCount + ". ship: ");
            int y = getLocation();
            if (isFree(x, y))
                oceanMap[x][y] = "1";
            else
                System.out.println("The location is not free");
            ++shipCount;
        } while (shipCount < 6);
        shipCount++;
        System.out.println("-------------------------------------");
    }

    public static int getLocation() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static boolean isFree(int x, int y) {
        if (oceanMap[x][y] == null)
            return true;
        else
            return false;
    }

    public static void deployComputerShip() {
        System.out.println();
        System.out.println("Computer is deploying ships");
        int shipCount = 1;
        do {
            int x = (int) (Math.random() * 10);
            int y = (int) (Math.random() * 10);
            if (isFree(x, y)) {
                oceanMap[x][y] = "2";
            System.out.println(shipCount + ". ship DEPLOYED");
            ++shipCount;
        }else{
            System.out.println("The location is not free");
            }
        } while (shipCount < 6); // deploying 5 ships
        System.out.println("-------------------------------------");
    }

    static int playerShips = 5;
    static int computerShips = 5;
    public static void battle() {
        System.out.println();
        System.out.println("YOUR TURN");

        do {
            // Player Turn
            //1. guess computer's ship location
            System.out.print("Enter x-coordinate: ");
            int x = getLocation();
            System.out.print("Enter y-coordinate: ");
            int y = getLocation();

            if (oceanMap[x][y] == "2") {
                System.out.println("Boom! You sunk the ship!");
                oceanMap[x][y] = "!";

                computerShips = computerShips-1;
//                playerShips = playerShips+1;


            } else if (oceanMap[x][y] == "1") {
                System.out.println("Oh no, you sunk your own ship :( ");
                oceanMap[x][y] = "x";

                playerShips = playerShips-1;

            } else {
                System.out.println("Sorry, you missed");
                oceanMap[x][y] = String.valueOf('-');
            }


            // Computer Turn
            System.out.println("-------------------------------------");
            System.out.println("COMPUTER'S TURN");

            x = (int) (Math.random() * 10);
            y = (int) (Math.random() * 10);

            if (oceanMap[x][y] == "1") {
                System.out.println("The Computer sunk one of your ships!");
                oceanMap[x][y] = "x";

                playerShips = playerShips-1;
//                computerShips = computerShips+1;



            } else if (oceanMap[x][y] == "2") {
                System.out.println("The Computer sunk one of its own ships");
                oceanMap[x][y] = "!";
                computerShips = computerShips-1;


            } else {
                System.out.println("Computer missed");
                oceanMap[x][y] = String.valueOf('-');
            }

            printMap();
            System.out.println("Your ships: " + playerShips + " | " + "Computer ships: " + computerShips);
            System.out.println("-------------------------------------");
        } while (playerShips > 0 && computerShips > 0);


        // Player Ship
//        System.out.println();
//        printMap();
//        System.out.println();
//        System.out.println("Your ships: " + playerShips + " | " + "Computer ships: " + computerShips);
//        System.out.println("-------------------------------------");

//        if (playerShips != 0 || computerShips != 0); // As long as playerShips OR computerShips is not zero, the battle continues.
//        battle();

    }



    public static void declareWinner() {
        System.out.println();
        System.out.println("Your ships: " + playerShips + " | Computer ships: " + computerShips);
        if(playerShips > 0 && computerShips <= 0)
            System.out.println("Hooray! You won the battle :)");
        else
            System.out.println("Sorry, you lost the battle :(");
        System.out.println();

        }

}
