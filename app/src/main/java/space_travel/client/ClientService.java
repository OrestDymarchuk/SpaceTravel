package space_travel.client;

import java.util.List;

public interface ClientService {
    void getClientById(int id);
    void updateClientById(int id, String name);
    List<Client> getAllClients();
    void createNewClient(String name);
    void deleteClient(int id);

}
