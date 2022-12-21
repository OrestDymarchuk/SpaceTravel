package space_travel.bot;

import space_travel.database_flyway_service.DatabaseFlyWayService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InformationCenterBot {
    public void startApp() {
        System.out.println("Welcome to SpaceTravel inc.");
        new InformationCenterBot().ask();
    }

    protected void ask() {
        System.out.println("To get information about clients, press 1;");
        System.out.println("To get information about planets, press 2;");
        System.out.println("To get information about tickets, press 3;");
        System.out.println("To start the database migration, press 4;");
        System.out.println("To exit the program, press 0;");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new InformationCenterBot().ask();
            }

            switch (request) {
                case 1 -> new ClientBot().clientService();
                case 2 -> new PlanetBot().planetService();
                case 3 -> new TicketBot().ticketService();
                case 4 -> new DatabaseFlyWayService().initFlyWayDb();
                case 0 -> System.exit(0);
                default -> {
                    System.err.println("Please enter a valid number");
                    new InformationCenterBot().ask();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
