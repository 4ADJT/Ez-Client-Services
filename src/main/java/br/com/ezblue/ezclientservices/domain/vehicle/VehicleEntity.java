package br.com.ezblue.ezclientservices.domain.vehicle;

import br.com.ezblue.ezclientservices.domain.client.ClientEntity;
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
@Entity(name = "vehicle")
@Table(name = "vehicle")
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String manufacturer;
    private String model;
    private int year;
    private String licensePlate;

    @ManyToMany(mappedBy = "vehicles")
    private List<ClientEntity> clients = new ArrayList<>();

    public VehicleEntity(RegisterVehicle registerVehicle) {
        this.manufacturer = registerVehicle.manufacturer();
        this.model = registerVehicle.model();
        this.year = registerVehicle.year();
        this.licensePlate = registerVehicle.licensePlate();
    }

}
