package space_travel;

import space_travel.client.ClientCrudService;
import space_travel.planet.PlanetCrudService;

public class Main {
    public static void main(String[] args) {
        ClientCrudService clientCrudService = new ClientCrudService();
        PlanetCrudService planetCrudService = new PlanetCrudService();

        // Create a client with restrictions
        clientCrudService.createNewClient("");

        // Create a client without restrictions
        clientCrudService.createNewClient("Cat");

        // Delete a non-existent client
        clientCrudService.deleteClient(99);

        // Delete a existent client
        clientCrudService.deleteClient(11);


        // Create a existent ID planet
        planetCrudService.createNewPlanet("MARS", "Mars");

        // Create a planet with ID restrictions
        planetCrudService.createNewPlanet("Mars", "Mars");

        // Create a planet with ID restrictions
        planetCrudService.createNewPlanet("#$%^&", "Mars");

        // Create a planet with Name restrictions
        planetCrudService.createNewPlanet("TEST", "");

        // Create a planet without restrictions
        planetCrudService.createNewPlanet("TEST", "Test");

        // Delete a existent planet
        planetCrudService.deletePlanet("TEST");

        // Delete a non-existent planet
        planetCrudService.deletePlanet("TEST");

    }
}
