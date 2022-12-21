package space_travel.ticket;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import space_travel.client.Client;
import space_travel.hibernate_util.HibernateUtil;
import space_travel.planet.Planet;

import java.util.List;

public class TicketCrudService implements TicketService {

    HibernateUtil util = HibernateUtil.getInstance();

    @Override
    public void getTicketById(int ticketId) {
        try (Session session = util.getSessionFactory().openSession()) {
            Ticket existingTicket = session.get(Ticket.class, ticketId);
            if (existingTicket == null) {
                System.out.println("The ticket with id " + ticketId + " does not exists.");
            } else {
                System.out.println(existingTicket);
            }
        }
    }

    @Override
    public void updateTicket(int ticketId, String planetFromId, String planetToId) {
        if (!planetFromId.matches("[A-Z0-9]*$") || !planetToId.matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Ticket existingTicket = session.get(Ticket.class, ticketId);
                Planet planetFrom = session.get(Planet.class, planetFromId);
                Planet planetTo = session.get(Planet.class, planetToId);
                if (existingTicket == null) {
                    System.out.println("The client with id " + ticketId + " does not exists.");
                } else if (planetFrom == null) {
                    System.out.println("The planet of origin with id " + planetFromId + " does not exists.");
                } else if (planetTo == null) {
                    System.out.println("The destination planet with id " + planetToId + " does not exists.");
                } else {
                    existingTicket.setPlanetFrom(planetFrom);
                    existingTicket.setPlanetTo(planetTo);
                    session.persist(existingTicket);
                    System.out.println("The ticket with id " + ticketId + " was updated.");
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets;
        try (Session session = util.getSessionFactory().openSession()) {
            Query<Ticket> ticketQuery = session.createQuery(
                    "from Ticket ",
                    Ticket.class
            );
            tickets = ticketQuery.list();
            return tickets;
        }
    }

    @Override
    public void createTicket(String createdAt, int clientId, String planetFromId, String planetToId) {
        if (!planetFromId.matches("[A-Z0-9]*$") || !planetToId.matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client existingClient = session.get(Client.class, clientId);
                Planet planetFrom = session.get(Planet.class, planetFromId);
                Planet planetTo = session.get(Planet.class, planetToId);
                if (existingClient == null) {
                    System.out.println("The client with id " + clientId + " does not exists.");
                } else if (planetFrom == null) {
                    System.out.println("The planet of origin with id " + planetFromId + " does not exists.");
                } else if (planetTo == null) {
                    System.out.println("The destination planet with id " + planetToId + " does not exists.");
                } else {
                    Ticket newTicket = new Ticket();
                    newTicket.setCreatedAt(createdAt);
                    newTicket.setClient(existingClient);
                    newTicket.setPlanetFrom(planetFrom);
                    newTicket.setPlanetTo(planetTo);
                    session.persist(newTicket);
                    System.out.println("The new ticket has been created.\n" + newTicket);
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public void deleteTicketById(int ticketId) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, ticketId);
            if (ticket == null) {
                System.out.println("The ticket with id " + ticketId + " does not exists.");
            } else {
                session.remove(ticket);
                transaction.commit();
                System.out.println("The ticket with id " + ticketId + " was deleted.");
            }
        }
    }
}
