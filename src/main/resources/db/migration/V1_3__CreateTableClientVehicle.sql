CREATE TABLE client_vehicle
(
    client_id  UUID,
    vehicle_id UUID,
    PRIMARY KEY (client_id, vehicle_id),
    FOREIGN KEY (client_id) REFERENCES client (id),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);
