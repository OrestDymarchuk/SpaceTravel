package space_travel.information_printer;

import space_travel.planet.PlanetCrudService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PlanetInformationService {
    PlanetCrudService planetCrudService = new PlanetCrudService();
    InformationCenter informationCenter = new InformationCenter();
    protected void planetService() {
        PlanetInformationService planetInformationService = new PlanetInformationService();
        System.out.println("""
                Please clarify your request;
                To get information about the planet by ID, press 1;
                To update planet's name, press 2;
                To get information about all planets, press 3;
                To create a new planet, press 4;
                To delete an existent planet, press 5;""");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                planetInformationService.planetService();
            }

            switch (request) {
                case 1 -> planetInformationService.getPlanetByIdService();
                case 2 -> planetInformationService.updatePlanetByIdService();
                case 3 -> planetInformationService.getAllPlanetsService();
                case 4 -> planetInformationService.createNewPlanetService();
                case 5 -> planetInformationService.deletePlanetByIdService();
            }
        }
    }

    private void getPlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            planetCrudService.getPlanetById(id);
            informationCenter.ask();
        }
    }

    private void updatePlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            System.out.println("Please enter the planet name.");
            String name = scanner.next();
            planetCrudService.updatePlanetById(id, name);
            informationCenter.ask();
        }
    }

    private void getAllPlanetsService(){
        planetCrudService.getAllPlanets().forEach(System.out::println);
        informationCenter.ask();
    }

    private void createNewPlanetService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            System.out.println("Please enter the planet name.");
            String name = scanner.next();
            planetCrudService.createNewPlanet(id, name);
            informationCenter.ask();
        }
    }

    private void deletePlanetByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the planet ID.");
            String id = scanner.next();
            planetCrudService.deletePlanet(id);
            informationCenter.ask();
        }
    }
}
