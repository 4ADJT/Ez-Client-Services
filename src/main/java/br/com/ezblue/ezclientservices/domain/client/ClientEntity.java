package br.com.ezblue.ezclientservices.domain.client;

import br.com.ezblue.ezclientservices.domain.address.AddressEntity;
import br.com.ezblue.ezclientservices.domain.vehicle.VehicleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "client")
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "client_vehicle",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "vehicle_id")
    )
    private List<VehicleEntity> vehicles = new ArrayList<>();

    public ClientEntity(RegisterClient registerClient) {
        firstName = registerClient.firstName();
        lastName = registerClient.lastName();
        email = registerClient.email();
        phone = registerClient.phone();
        address = new AddressEntity(registerClient.address());
    }

    public ClientEntity(ClientEntity client) {
        id = client.id;
        firstName = client.firstName;
        lastName = client.lastName;
        email = client.email;
        phone = client.phone;
    }

    public void updateData(UpdateClient updateClient) {
        if (updateClient.email() != null)
            this.email = updateClient.email();
        if (updateClient.phone() != null)
            this.phone = updateClient.phone();
        if (updateClient.address() != null)
            this.address.updateData(updateClient.address());
    }

    public void SetAddress(AddressEntity address) {
        this.address = address;
    }

}
