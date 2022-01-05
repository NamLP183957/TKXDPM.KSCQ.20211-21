CREATE DATABASE ECOBIKE;

create table stations
(
    id                              int auto_increment not null,
    name                            varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    address                         varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    area                            varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    num_of_curr_bike                int                                                    DEFAULT NULL,
    num_of_blank_bike               int                                                    DEFAULT NULL,
    num_of_blank_electric_bike      int                                                    DEFAULT NULL,
    num_of_blank_twin_bike          int                                                    DEFAULT NULL,
    num_of_blank_electric_twin_bike int                                                    DEFAULT NULL,
    PRIMARY KEY (id)
);

create table parking_slots
(
    id           int auto_increment not null,
    station_id   int                                                   DEFAULT NULL,
    type         int                                                   DEFAULT NULL,
    code         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    status       int                                                   DEFAULT NULL,
    booking_time bigint                                                DEFAULT NULL,
    PRIMARY KEY (id)
);

create table vehicles
(
    id              int auto_increment not null,
    parking_slot_id int                             DEFAULT NULL,
    type            int                             DEFAULT NULL,
    license_plate   varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
    battery         int                             DEFAULT NULL,
    max_time        int                             DEFAULT NULL,
    status          int                             DEFAULT NULL,
    PRIMARY KEY (id)
);

create table cards
(
    id           int auto_increment not null,
    card_code    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL,
    owner        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    date_expired bigint                                                 DEFAULT NULL,
    cvv_code     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  DEFAULT NULL,
    status       int                                                    DEFAULT NULL,
    PRIMARY KEY (id)
);

create table payment_transactions
(
    id         int auto_increment not null,
    card_id    int                                                    DEFAULT NULL,
    method     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    content    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
    created_at bigint                                                 DEFAULT NULL,
    PRIMARY KEY (id)
);

create table invoices
(
    id              int auto_increment not null,
    vehicle_id      int    DEFAULT NULL,
    transaction_id  int    DEFAULT NULL,
    start_time      bigint DEFAULT NULL,
    restart_time    bigint DEFAULT NULL,
    total_rent_time int    DEFAULT NULL,
    status          int    DEFAULT NULL,
    PRIMARY KEY (id)
)


