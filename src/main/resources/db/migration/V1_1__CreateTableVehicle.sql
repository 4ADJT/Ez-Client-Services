CREATE TABLE vehicle
(
    id           uuid primary key,
    manufacturer varchar(255),
    model        varchar(255),
    year         int,
    license_plate varchar(255)
);