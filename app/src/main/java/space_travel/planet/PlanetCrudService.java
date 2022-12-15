package space_travel.planet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import space_travel.hibernate_util.HibernateUtil;

import java.util.List;

public class PlanetCrudService {
    HibernateUtil util = HibernateUtil.getInstance();

    public void getPlanetById(String id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Planet existingPlanet = session.get(Planet.class, id);
            if (existingPlanet == null) {
                System.out.println("The planet with id " + id + " does not exists.");
            } else {
                System.out.println(existingPlanet);
            }
        }
    }

    public void updatePlanetById(String id, String name) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet existingPlanet = session.get(Planet.class, id);
            if (existingPlanet == null) {
                System.out.println("The planet with id " + id + " does not exists.");
            } else {
                existingPlanet.setName(name);
                session.persist(existingPlanet);
                transaction.commit();
                System.out.println(existingPlanet);
            }
        }
    }

    public List<Planet> getAllPlanets() {
        List<Planet> planets;
        try (Session session = util.getSessionFactory().openSession()) {
            Query<Planet> planetQuery = session.createQuery(
                    "from Planet",
                    Planet.class
            );
            planets = planetQuery.list();
            return planets;
        }
    }

    public void createNewPlanet(String id, String name) {
        if (!id.matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else if (name.length() < 1) {
            System.out.println("The name of the planet must contain at least one character.");

        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Planet planet = session.get(Planet.class, id);
                if (planet != null) {
                    System.out.println("The planet with id " + id + " already exists.");
                } else {
                    Planet newPlanet = new Planet();
                    newPlanet.setId(id);
                    newPlanet.setName(name);
                    session.persist(newPlanet);
                    System.out.println(newPlanet);
                    transaction.commit();
                }
            }
        }
    }

    public void deletePlanet(String id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Planet planet = session.get(Planet.class, id);
            if (planet == null) {
                System.out.println("The planet with id " + id + " does not exists.");
            } else {
                session.remove(planet);
                transaction.commit();
                System.out.println("The planet with id " + id + " was deleted");
            }
        }
    }
}
