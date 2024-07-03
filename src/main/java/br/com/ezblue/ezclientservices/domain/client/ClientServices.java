package br.com.ezblue.ezclientservices.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository clientRepository;

    public ResponseEntity<DetailClient> register(RegisterClient registerClient, UriComponentsBuilder componentsBuilder) {
        var client = new ClientEntity(registerClient);
        clientRepository.save(client);
        var uri = componentsBuilder.path("client/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailClient(client));
    }

    public ResponseEntity<Page<SimpleClient>> findAll(Pageable pageable) {
        return ResponseEntity.ok(clientRepository.findAll(pageable).map(SimpleClient::new));
    }

    public ResponseEntity<DetailClient> findById(UUID id) {
        return clientRepository.findById(id).map(client ->
                ResponseEntity.ok(new DetailClient(client))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<DetailClient> getReferenceById(UpdateClient updateGuardian, UUID id) {
        var guardian = clientRepository.getReferenceById(id);
        guardian.updateData(updateGuardian);
        return ResponseEntity.ok(new DetailClient(guardian));
    }

    public ResponseEntity deleteById(UUID id) {
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
