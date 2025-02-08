CREATE TABLE Producto (
    id_producto BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(30),
    precio_unitario DECIMAL(10,2),
    stock INT
);