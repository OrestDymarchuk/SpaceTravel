package space_travel.information_printer;

import space_travel.client.ClientCrudService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientInformationService {
    ClientCrudService clientCrudService = new ClientCrudService();
    InformationCenter informationCenter = new InformationCenter();

    protected void clientService() {
        ClientInformationService clientInformationService = new ClientInformationService();
        System.out.println("""
                Please clarify your request;
                To get information about the client by ID, press 1;
                To update client's name, press 2;
                To get information about all clients, press 3;
                To create a new client, press 4;
                To delete an existent client, press 5;
                To the main menu, press 0;""");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                clientInformationService.clientService();
            }

            switch (request) {
                case 1 -> clientInformationService.getClientByIdService();
                case 2 -> clientInformationService.updateClientByIdService();
                case 3 -> clientInformationService.getAllClientsService();
                case 4 -> clientInformationService.createNewClientService();
                case 5 -> clientInformationService.deleteClientByIdService();
                case 0 -> informationCenter.ask();
                default -> {
                    System.err.println("Please enter a valid number");
                    clientInformationService.clientService();
                }
            }
        }
    }

    private void getClientByIdService() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the client id.");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new ClientInformationService().getClientByIdService();
            }
            clientCrudService.getClientById(id);
            informationCenter.ask();
        }
    }

    private void updateClientByIdService() {
        System.out.println("Please enter the client id.");
        try (Scanner scanner = new Scanner(System.in)) {
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new ClientInformationService().updateClientByIdService();
            }
            System.out.println("Please enter the name.");
            String name = scanner.next();
            clientCrudService.updateClientById(id, name);
            informationCenter.ask();
        }
    }

    private void getAllClientsService(){
        clientCrudService.getAllClients().forEach(System.out::println);
        informationCenter.ask();
    }

    private void createNewClientService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the name.");
            String name = scanner.next();
            clientCrudService.createNewClient(name);
            informationCenter.ask();
        }
    }

    private void deleteClientByIdService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the client id.");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new ClientInformationService().getClientByIdService();
            }
            clientCrudService.deleteClient(id);
            informationCenter.ask();
        }
    }
}
