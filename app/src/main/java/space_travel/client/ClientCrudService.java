package space_travel.client;

import org.hibernate.Session;
import org.hibernate.Transaction;
import space_travel.hibernate_util.HibernateUtil;

public class ClientCrudService {
    HibernateUtil util = HibernateUtil.getInstance();
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
