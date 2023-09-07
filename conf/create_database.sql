create table movctacorredor
(
    idMovCtaCorredor int                                 not null
        primary key,
    tipo             tinyint                             not null,
    idOrigen         int                                 null,
    fecha            date                                not null,
    idBanca          smallint                            not null,
    idAgencia        smallint                            not null,
    idCorredor       smallint                            not null,
    importe          decimal(11, 2)                      not null,
    descripcion      varchar(80)                         null,
    detalle          text                                null,
    idSesion         int                                 not null,
    updTimestamp     timestamp default CURRENT_TIMESTAMP not null

);