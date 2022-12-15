package space_travel.information_printer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InformationCenter {
    public void startApp() {
        System.out.println("Welcome to SpaceTravel inc.");
        new InformationCenter().ask();
    }

    protected void ask() {
        System.out.println("To get information about clients, press 1");
        System.out.println("To get information about planets, press 2");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new InformationCenter().ask();
            }

            switch (request) {
                case 1 -> new ClientInformationService().clientService();
                case 2 -> new PlanetInformationService().planetService();
            }
        }
    }
}
