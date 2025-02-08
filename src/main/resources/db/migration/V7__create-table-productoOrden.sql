CREATE TABLE ProductoOrden (
    id_orden INT,
    id_producto INT,
    cantidad INT,
    PRIMARY KEY (id_orden, id_producto),
    FOREIGN KEY (id_orden) REFERENCES OrdenDePago(id_orden),
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto)
);