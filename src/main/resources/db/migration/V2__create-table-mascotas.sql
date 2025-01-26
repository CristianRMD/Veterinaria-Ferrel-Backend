CREATE TABLE mascotas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    raza VARCHAR(100) NOT NULL,
    sexo VARCHAR(100) NOT NULL,
    edad INT NOT NULL,
    peso DECIMAL(5,2) NOT NULL,
    talla DECIMAL(5,2) NOT NULL,
    cliente_id BIGINT,
    CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
)

