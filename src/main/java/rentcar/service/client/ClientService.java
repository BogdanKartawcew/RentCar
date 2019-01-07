package rentcar.service.client;

import java.util.List;

import rentcar.model.Client;


public interface ClientService {
	
	Client findByPesel(String pesel);
	
	void saveClient(Client client);
	
	void updateClient(Client client);
	
	void deleteClientByPesel(String pesel);

	List<Client> findAllClients();
	
	boolean isPeselUnique(Integer clientId, String pesel);

	long countAllByPage();

	List<Client> getAllByPage(int pageNumber, int rowsOnPage);

}