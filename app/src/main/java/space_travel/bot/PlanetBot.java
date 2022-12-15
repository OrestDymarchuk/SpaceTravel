package space_travel.bot;

import space_travel.planet.PlanetCrudService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlanetBot {
    PlanetCrudService planetCrudService = new PlanetCrudService();
    InformationCenterBot informationCenterBot = new InformationCenterBot();
    protected void planetService() {
        PlanetBot planetBot = new PlanetBot();
        System.out.println("""
                Please clarify your request;
                To get information about the planet by ID, press 1;
                To update planet's name, press 2;
                To get information about all planets, press 3;
                To create a new planet, press 4;
                To delete an existent planet, press 5;
                To the main menu, press 0;""");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                planetBot.planetService();
            }

            switch (request) {
                case 1 -> planetBot.getPlanetByIdService();
                case 2 -> planetBot.updatePlanetByIdService();
                case 3 -> planetBot.getAllPlanetsService();
                case 4 -> planetBot.createNewPlanetService();
                case 5 -> planetBot.deletePlanetByIdService();
                case 0 -> informationCenterBot.ask();
                default -> {
                    System.err.println("Please enter a valid number");
                    planetBot.planetService();
                }
            }
        }
    }

    private void getPlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            planetCrudService.getPlanetById(id);
            informationCenterBot.ask();
        }
    }

    private void updatePlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            System.out.println("Please enter the planet name.");
            String name = scanner.next();
            planetCrudService.updatePlanetById(id, name);
            informationCenterBot.ask();
        }
    }

    private void getAllPlanetsService(){
        planetCrudService.getAllPlanets().forEach(System.out::println);
        informationCenterBot.ask();
    }

    private void createNewPlanetService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            System.out.println("Please enter the planet name.");
            String name = scanner.next();
            planetCrudService.createNewPlanet(id, name);
            informationCenterBot.ask();
        }
    }

    private void deletePlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            planetCrudService.deletePlanet(id);
            informationCenterBot.ask();
        }
    }
}
