package space_travel.bot;

import space_travel.client.ClientCrudService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientBot {
    ClientCrudService clientCrudService = new ClientCrudService();
    InformationCenterBot informationCenterBot = new InformationCenterBot();

    protected void clientService() {
        ClientBot clientBot = new ClientBot();
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
                clientBot.clientService();
            }

            switch (request) {
                case 1 -> clientBot.getClientByIdService();
                case 2 -> clientBot.updateClientByIdService();
                case 3 -> clientBot.getAllClientsService();
                case 4 -> clientBot.createNewClientService();
                case 5 -> clientBot.deleteClientByIdService();
                case 0 -> informationCenterBot.ask();
                default -> {
                    System.err.println("Please enter a valid number");
                    clientBot.clientService();
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
                new ClientBot().getClientByIdService();
            }
            clientCrudService.getClientById(id);
            informationCenterBot.ask();
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
                new ClientBot().updateClientByIdService();
            }
            System.out.println("Please enter the name.");
            String name = scanner.next();
            clientCrudService.updateClientById(id, name);
            informationCenterBot.ask();
        }
    }

    private void getAllClientsService(){
        clientCrudService.getAllClients().forEach(System.out::println);
        informationCenterBot.ask();
    }

    private void createNewClientService(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the name.");
            String name = scanner.next();
            clientCrudService.createNewClient(name);
            informationCenterBot.ask();
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
                new ClientBot().getClientByIdService();
            }
            clientCrudService.deleteClient(id);
            informationCenterBot.ask();
        }
    }
}
