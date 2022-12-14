package space_travel.planet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import space_travel.hibernate_util.HibernateUtil;

public class PlanetCrudService {
    HibernateUtil util = HibernateUtil.getInstance();

    private String selectPlanetById(String id) {
        try (Session session = util.getSessionFactory().openSession();) {
            Planet planet;
            Query<Planet> planetQuery = session.createQuery(
                    "from Planet where id = :id",
                    Planet.class
            );

            planetQuery.setParameter("id", id);
            if (planetQuery.list().isEmpty()) {
                return null;
            } else {
                planet = planetQuery.getSingleResult();
                return planet.getId();
            }
        }
    }

    public void createNewPlanet(String id, String name) {
        if(id.equals(new PlanetCrudService().selectPlanetById(id))){
            System.out.println("The planet already exists.");
        }
        else if(!id.matches("[A-Z0-9]*$")){
            System.out.println("The planet ID must contain capital letters without special characters.");
        }
        else if (name.length() < 1) {
            System.out.println("The name of the planet must contain at least one character.");

        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Planet newPlanet = new Planet();
                newPlanet.setId(id);
                newPlanet.setName(name);
                session.persist(newPlanet);
                System.out.println(newPlanet);
                transaction.commit();
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
