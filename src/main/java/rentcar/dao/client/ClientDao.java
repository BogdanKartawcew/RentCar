package rentcar.dao.client;

import java.util.List;

import rentcar.model.Client;

public interface ClientDao {

    Client findById(int id);

    Client findByPesel(String pesel);

    void save(Client client);

    void deleteByPesel(String pesel);

    List<Client> getAll();
}

