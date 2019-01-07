package rentcar.service.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rentcar.dao.client.ClientDao;
import rentcar.model.Client;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService{

	@Autowired
	private ClientDao clientDao;

	public Client findByPesel(String pesel) {
		return clientDao.findByPesel(pesel);
	}

	public void saveClient(Client client) {
		clientDao.save(client);
	}

	public void updateClient(Client client) {
		Client entity = clientDao.findById(client.getClientId());
		if(entity!=null){
			entity.setClientId(client.getClientId());
			entity.setPesel(client.getPesel());
			entity.setClientFirstName(client.getClientFirstName());
			entity.setClientLastName(client.getClientLastName());
			entity.setClientEmail(client.getClientEmail());
			entity.setClientCompanyName(client.getClientCompanyName());
			entity.setClientGender(client.getClientGender());
		}
	}

	public void deleteClientByPesel(String pesel) {
		clientDao.deleteByPesel(pesel);
	}

	public List<Client> findAllClients() {
		return clientDao.getAll();
	}

	public boolean isPeselUnique(Integer clientId, String pesel) {
		Client client = findByPesel(pesel);
		//return ( client == null || ((clientId != null) && (client.getClientId() == clientId)));
		return ( client == null || client.getClientId().equals(clientId));
	}

	@Override
	public long countAllByPage() {
		return clientDao.countAllByPage();
	}

	@Override
	public List<Client> getAllByPage(int pageNumber, int rowsOnPage) {
		return clientDao.getAllByPage(pageNumber, rowsOnPage);
	}

}
