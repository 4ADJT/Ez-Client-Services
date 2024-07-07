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
    private Integer year;
    private String licensePlate;

    @ManyToMany(mappedBy = "vehicles")
    private List<ClientEntity> clients = new ArrayList<>();

    /**
     * Construtor que inicializa um objeto {@code VehicleEntity} com os dados fornecidos.
     *
     * @param registerVehicle O objeto contendo as informações do veículo a serem registradas.
     */
    public VehicleEntity(RegisterVehicle registerVehicle) {
        this.manufacturer = registerVehicle.manufacturer();
        this.model = registerVehicle.model();
        this.year = registerVehicle.year();
        this.licensePlate = registerVehicle.licensePlate();
    }

    /**
     * Método que atualiza os dados do veículo com as informações fornecidas.
     *
     * @param updateVehicle O objeto contendo as novas informações do veículo.
     */
    public void updateData(UpdateVehicle updateVehicle) {
        if (updateVehicle.manufacturer() != null)
            this.manufacturer = updateVehicle.manufacturer();
        if (updateVehicle.model() != null)
            this.model = updateVehicle.model();
        if (updateVehicle.year() != null)
            this.year = updateVehicle.year();
        if (updateVehicle.licensePlate() != null)
            this.licensePlate = updateVehicle.licensePlate();
    }
}
