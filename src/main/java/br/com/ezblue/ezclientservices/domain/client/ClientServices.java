package br.com.ezblue.ezclientservices.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public DetailClient register(RegisterClient registerClient) {
        var client = new ClientEntity(registerClient);
        clientRepository.save(client);
        return new DetailClient(client);
    }

    public Page<SimpleClient> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(SimpleClient::new);
    }

    public Optional<ClientEntity> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public DetailClient getReferenceById(UpdateClient updateClient, UUID id) {
        var client = clientRepository.getReferenceById(id);
        client.updateData(updateClient);
        return new DetailClient(client);
    }

    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }
}
