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

    /**
     * Registrar um cliente.
     *
     * @param registerClient Objeto do formulário de cadastro do cliente.
     * @return DetailClient objeto contem todas as informações do cliente.
     */
    public DetailClient register(RegisterClient registerClient) {
        var client = new ClientEntity(registerClient);
        clientRepository.save(client);
        return new DetailClient(client);
    }

    /**
     * Listar todos os clientes.
     *
     * @param pageable Objeto de paginação.
     * @return Page<SimpleClient> objeto contem lista de todos os clientes com as informações básicas.
     */
    public Page<SimpleClient> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(SimpleClient::new);
    }

    /**
     * Detalhar um cliente.
     *
     * @param id O ID do cliente.
     * @return DetailClient objeto contem todas as informações do cliente.
     * @throws RuntimeException caso cliente não exista
     */
    public DetailClient findById(UUID id) {
        return clientRepository.findById(id).map(DetailClient::new)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    /**
     * Detalhar um cliente.
     *
     * @param id O ID do cliente.
     * @return Optional<ClientEntity> objeto contem todas as informações do cliente.
     * @throws RuntimeException caso cliente não exista
     * @deprecated este método não é recomendado.
     * Use {@link #findById(UUID)}.
     */
    @Deprecated
    public Optional<ClientEntity> optionalFindById(UUID id) {
        return clientRepository.findById(id);
    }

    /**
     * Atualizar um cliente.
     *
     * @param updateClient formulário de atualização do cliente.
     * @param id           O ID do cliente a ser atualizado.
     * @return DetailClient objeto contem todas as informações do cliente.
     */
    public DetailClient getReferenceById(UpdateClient updateClient, UUID id) {
        var client = clientRepository.getReferenceById(id);
        client.updateData(updateClient);
        return new DetailClient(client);
    }

    /**
     * Exclusão física, exclui o cliente permanentemente.
     *
     * @param id O ID do cliente a ser excluído.
     */
    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }
}
