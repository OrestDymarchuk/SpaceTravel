package space_travel;

import space_travel.bot.InformationCenterBot;

public class Main {
    public static void main(String[] args) {

        new InformationCenterBot().startApp();


//        new TicketCrudService().getTicketById(12);
//        new TicketCrudService().getAllTickets().forEach(System.out::println);
//        new ClientCrudService().getAllClients().forEach(System.out::println);
//        new TicketCrudService().updateTicket(12, "VEN", "JUP");
//        new TicketCrudService().deleteTicketById(11);
//        String currentDateAndTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        new TicketCrudService().createTicket(currentDateAndTime, 10, "MARS", "EARTH");

    }
}
