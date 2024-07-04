package br.com.ezblue.ezclientservices.domain.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {
}
