package rentcar.dao.client;

import java.util.List;

import rentcar.model.support.Client;

public interface ClientDao {

    Client findById(int id);

    Client findByPesel(String pesel);

    void save(Client client);

    void deleteByPesel(String pesel);

    List<Client> getAll();

    List<Client> getAllByPage(int pageNumber, int rowsOnPage);

    long countAllByPage();
}

