CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY,
    dni BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    telefono VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    activo BOOLEAN
);