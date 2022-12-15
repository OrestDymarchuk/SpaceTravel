package space_travel.planet;

import java.util.List;

public interface PlanetService {

    void getPlanetById(String id);
    void updatePlanetById(String id, String name);
    List<Planet> getAllPlanets();
    void createNewPlanet(String id, String name);
    void deletePlanet(String id);

}
