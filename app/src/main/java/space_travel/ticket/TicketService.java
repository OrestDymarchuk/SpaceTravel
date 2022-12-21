package space_travel.ticket;

import java.util.List;

public interface TicketService {
    void getTicketById(int ticketId);
    void updateTicket(int ticketId, String planetFrom, String planetTo);
    List<Ticket> getAllTickets();
    void createTicket(String createdAt, int clientId, String planetFrom, String planetTo);
    void deleteTicketById(int ticketId);
}
