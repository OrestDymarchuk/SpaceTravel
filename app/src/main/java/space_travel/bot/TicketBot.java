package space_travel.bot;

import space_travel.ticket.TicketCrudService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicketBot {
    TicketCrudService ticketCrudService = new TicketCrudService();
    InformationCenterBot informationCenterBot = new InformationCenterBot();

    protected void ticketService() {
        TicketBot ticketBot = new TicketBot();

        System.out.println("""
                Please clarify your request;
                To get information about the ticket by ID, press 1;
                To update ticket's data, press 2;
                To get information about all tickets, press 3;
                To create a new ticket, press 4;
                To delete an existent ticket, press 5;
                To the main menu, press 0;""");

        try (Scanner scanner = new Scanner(System.in)) {
            int request = 0;
            try {
                request = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                ticketBot.ticketService();
            }

            switch (request) {
                case 1 -> ticketBot.getTicketByIdService();
                case 2 -> ticketBot.updateTicketByIdService();
                case 3 -> ticketBot.getAllTicketsService();
                case 4 -> ticketBot.createNewTicketService();
                case 5 -> ticketBot.deleteTicketByIdService();
                case 0 -> informationCenterBot.ask();
                default -> {
                    System.err.println("Please enter a valid number");
                    ticketBot.ticketService();
                }
            }
        }
    }

    private void getTicketByIdService() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the ticket id.");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new TicketBot().getTicketByIdService();
            }
            ticketCrudService.getTicketById(id);
            informationCenterBot.ask();
        }
    }

    private void updateTicketByIdService() {
        System.out.println("Please enter the ticket id.");
        try (Scanner scanner = new Scanner(System.in)) {
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new TicketBot().updateTicketByIdService();
            }
            System.out.println("Please enter the planet of origin id.");
            String planetFrom = scanner.next();
            System.out.println("Please enter the destination planet id.");
            String planetTo = scanner.next();
            ticketCrudService.updateTicket(id, planetFrom, planetTo);
            informationCenterBot.ask();
        }
    }

    private void getAllTicketsService() {
        ticketCrudService.getAllTickets().forEach(System.out::println);
        informationCenterBot.ask();
    }

    private void createNewTicketService() {
        System.out.println("Please enter the client id.");
        try (Scanner scanner = new Scanner(System.in)) {
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new TicketBot().createNewTicketService();
            }
            System.out.println("Please enter the planet of origin id.");
            String planetFrom = scanner.next();
            System.out.println("Please enter the destination planet id.");
            String planetTo = scanner.next();
            String currentDateAndTime = LocalDateTime.now().
                    format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    );
            ticketCrudService.createTicket(currentDateAndTime, id, planetFrom, planetTo);
            informationCenterBot.ask();
        }
    }

    private void deleteTicketByIdService() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the ticket id.");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException ex) {
                System.err.println("Please enter a valid number.");
                new TicketBot().deleteTicketByIdService();
            }
            ticketCrudService.deleteTicketById(id);
            informationCenterBot.ask();
        }
    }
}
