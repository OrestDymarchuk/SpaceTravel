package space_travel.client;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import space_travel.hibernate_util.HibernateUtil;

import java.util.List;

public class ClientCrudService {
    HibernateUtil util = HibernateUtil.getInstance();

    public void getClientById(int id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Client existingClient = session.get(Client.class, id);
            if (existingClient == null) {
                System.out.println("The client with id " + id + " does not exists.");
            } else {
                System.out.println(existingClient);
            }
        }
    }

    public void updateClientById(int id, String name) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existingClient = session.get(Client.class, id);
            if (existingClient == null) {
                System.out.println("The client with id " + id + " does not exists.");
            } else {
                existingClient.setName(name);
                session.persist(existingClient);
                transaction.commit();
                System.out.println("The client's name with id " + id + " was updated to " + name +
                        "\n" + existingClient);
            }
        }
    }

    public List<Client> getAllClients() {
        List<Client> clients;
        try (Session session = util.getSessionFactory().openSession()) {
            Query<Client> clientQuery = session.createQuery(
                    "from Client ",
                    Client.class
            );
            clients = clientQuery.list();
            return clients;
        }
    }

    public void createNewClient(String name) {
        if (name.length() < 3) {
            System.out.println("A new client cannot be created due to the restrictions," +
                    " the name must have at least 2 characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client newClient = new Client();
                newClient.setName(name);
                session.persist(newClient);
                transaction.commit();
                System.out.println(newClient);
            }
        }
    }

    public void deleteClient(int id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client == null) {
                System.out.println("The client with id " + id + " does not exists.");
            } else {
                session.remove(client);
                transaction.commit();
                System.out.println("The client with id " + id + " was deleted");
            }
        }
    }
}
